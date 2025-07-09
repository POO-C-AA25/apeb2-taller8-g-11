import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problema6_SimuladorConflictos {
    public static void main(String[] args) {
        Simulador simulador = new Simulador();
        
        // Crear naciones
        Nacion usa = new NacionDesarrollada("Estados Unidos", 331000000, 25000000, 95, true);
        Nacion china = new NacionDesarrollada("China", 1412000000, 18000000, 90, false);
        Nacion rusia = new NacionDesarrollada("Rusia", 146000000, 1700000, 85, true);
        Nacion india = new NacionEnDesarrollo("India", 1380000000, 3200000, 70);
        Nacion brasil = new NacionEnDesarrollo("Brasil", 213000000, 1600000, 60);

        usa.agregarAliado(rusia);
        china.agregarAliado(india);
        
        simulador.agregarNacion(usa);
        simulador.agregarNacion(china);
        simulador.agregarNacion(rusia);
        simulador.agregarNacion(india);
        simulador.agregarNacion(brasil);

        simulador.iniciarSimulacion(5);

        simulador.generarReporte();
    }
}

abstract class Nacion {
    protected final String nombre;
    protected int habitantes;
    protected double recursosEconomicos;
    protected int poderMilitar;
    protected boolean enConflicto;
    protected final List<Nacion> aliados;
    
    public Nacion(String nombre, int habitantes, double recursosEconomicos, int poderMilitar) {
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.recursosEconomicos = recursosEconomicos;
        this.poderMilitar = Math.min(100, Math.max(1, poderMilitar));
        this.enConflicto = false;
        this.aliados = new ArrayList<>();
    }
    
    public abstract double calcularImpacto();
    
    public void participarEnConflicto() {
        this.enConflicto = true;
    }
    
    public void sufrirConsecuencias(double perdidaRecursos, double perdidaPoblacion) {
        this.recursosEconomicos *= (1 - perdidaRecursos);
        this.habitantes *= (1 - perdidaPoblacion);
        this.enConflicto = false;
    }
    
    public void agregarAliado(Nacion aliado) {
        if (!aliados.contains(aliado)) {
            aliados.add(aliado);
        }
    }
    
    public double getPoderMilitarConAliados() {
        double poderTotal = poderMilitar;
        for (Nacion aliado : aliados) {
            poderTotal += aliado.poderMilitar * 0.1;
        }
        return Math.min(100, poderTotal);
    }
    
    @Override
    public String toString() {
        return String.format("%s [Habitantes: %,d, Recursos: $%,.2f, Poder Militar: %d, Conflicto: %s]",
                           nombre, habitantes, recursosEconomicos, poderMilitar, enConflicto ? "Sí" : "No");
    }
}

class NacionDesarrollada extends Nacion {
    private final boolean tieneTecnologiaAvanzada;
    
    public NacionDesarrollada(String nombre, int habitantes, double recursosEconomicos, 
                             int poderMilitar, boolean tieneTecnologiaAvanzada) {
        super(nombre, habitantes, recursosEconomicos, poderMilitar);
        this.tieneTecnologiaAvanzada = tieneTecnologiaAvanzada;
    }
    
    @Override
    public double calcularImpacto() {
        double impacto = getPoderMilitarConAliados();
        if (tieneTecnologiaAvanzada) {
            impacto *= 1.3;
        }
        return Math.min(100, impacto);
    }
}

class NacionEnDesarrollo extends Nacion {
    public NacionEnDesarrollo(String nombre, int habitantes, double recursosEconomicos, int poderMilitar) {
        super(nombre, habitantes, recursosEconomicos, poderMilitar);
    }
    
    @Override
    public double calcularImpacto() {
        double impacto = getPoderMilitarConAliados();
        double recursosPorHabitante = recursosEconomicos / habitantes;
        impacto *= Math.min(1, recursosPorHabitante / 1000);
        return Math.max(1, impacto);
    }
}

class Conflicto {
    private final Nacion nacion1;
    private final Nacion nacion2;
    private static int totalConflictos = 0;
    
    public Conflicto(Nacion nacion1, Nacion nacion2) {
        this.nacion1 = nacion1;
        this.nacion2 = nacion2;
        totalConflictos++;
    }
    
    public void simular() {
        nacion1.participarEnConflicto();
        nacion2.participarEnConflicto();
        
        double impacto1 = nacion1.calcularImpacto();
        double impacto2 = nacion2.calcularImpacto();
        
        System.out.printf("\nCONFLICTO ENTRE %s (%.1f) vs %s (%.1f)\n",
                        nacion1.nombre, impacto1, nacion2.nombre, impacto2);
        
        calcularConsecuencias(impacto1, impacto2);
    }
    
    private void calcularConsecuencias(double impacto1, double impacto2) {
        double diferencia = Math.abs(impacto1 - impacto2);
        double perdidaPoblacion = diferencia * 0.05;
        
        if (impacto1 > impacto2) {
            nacion2.sufrirConsecuencias(0.10, perdidaPoblacion);
            System.out.printf("%s derrota a %s\n", nacion1.nombre, nacion2.nombre);
        } else if (impacto2 > impacto1) {
            nacion1.sufrirConsecuencias(0.10, perdidaPoblacion);
            System.out.printf("%s derrota a %s\n", nacion2.nombre, nacion1.nombre);
        } else {
            nacion1.sufrirConsecuencias(0.05, 0);
            nacion2.sufrirConsecuencias(0.05, 0);
            System.out.println("Conflicto terminó en empate");
        }
    }
    
    public static int getTotalConflictos() {
        return totalConflictos;
    }
}

class Simulador {
    private final List<Nacion> naciones;
    private final Random random;
    
    public Simulador() {
        this.naciones = new ArrayList<>();
        this.random = new Random();
    }
    
    public void agregarNacion(Nacion nacion) {
        naciones.add(nacion);
    }
    
    public void iniciarSimulacion(int numConflictos) {
        System.out.println("=== INICIANDO SIMULACIÓN DE CONFLICTOS ===");
        
        for (int i = 0; i < numConflictos; i++) {
            if (naciones.size() < 2) break;

            int index1 = random.nextInt(naciones.size());
            int index2;
            do {
                index2 = random.nextInt(naciones.size());
            } while (index1 == index2);
            
            Nacion n1 = naciones.get(index1);
            Nacion n2 = naciones.get(index2);
            
            Conflicto conflicto = new Conflicto(n1, n2);
            conflicto.simular();
        }
    }
    
    public void generarReporte() {
        System.out.println("\n=== REPORTE FINAL ===");
        System.out.println("Total de conflictos simulados: " + Conflicto.getTotalConflictos());
        System.out.println("\nEstado actual de las naciones:");
        
        for (Nacion nacion : naciones) {
            System.out.println(nacion);
        }
    }
}