import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problema4_SistemaMonitoreoCambioClimatico {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE MONITOREO AMBIENTAL ===");
        
        final Dispositivo costa = FabricaDispositivos.crearDispositivo("costa", "D-COSTA-001", "Playas");
        final Dispositivo sierra = FabricaDispositivos.crearDispositivo("sierra", "D-SIERRA-001", "Quito");
        final Dispositivo oriente = FabricaDispositivos.crearDispositivo("oriente", "D-ORIENTE-001", "Tena");

        costa.recolectarDatos();
        sierra.recolectarDatos();
        oriente.recolectarDatos();

        final Reporte reporteCosta = new Reporte(costa);
        reporteCosta.agregarRiesgo("Erosión costera");
        reporteCosta.agregarRiesgo("Aumento del nivel del mar");
        
        final Reporte reporteSierra = new Reporte(sierra);
        reporteSierra.agregarRiesgo("Sequías prolongadas");
        reporteSierra.agregarRiesgo("Radiación UV extrema");
        
        final Reporte reporteOriente = new Reporte(oriente);
        reporteOriente.agregarRiesgo("Deforestación acelerada");
        reporteOriente.agregarRiesgo("Contaminación del aire");

        System.out.println("\n=== REPORTES REGIONALES ===");
        System.out.println(reporteCosta);
        System.out.println(reporteSierra);
        System.out.println(reporteOriente);

        System.out.println("\n=== ANÁLISIS POLIMÓRFICO ===");
        final Dispositivo[] dispositivos = {costa, sierra, oriente};
        for (final Dispositivo dispositivo : dispositivos) {
            System.out.println("\nDispositivo: " + dispositivo);
            System.out.println("Alerta: " + dispositivo.generarAlerta());
            System.out.println("Datos: " + dispositivo.procesarDatos());
        }
    }
}

abstract class Dispositivo {
    protected final String identificacion;
    protected final String ubicacion;
    protected final List<Float> mediciones;
    
    public Dispositivo(String identificacion, String ubicacion) {
        this.identificacion = identificacion;
        this.ubicacion = ubicacion;
        this.mediciones = new ArrayList<>();
    }
    
    public abstract void recolectarDatos();
    public abstract Map<String, Float> procesarDatos();
    public abstract String generarAlerta();
    
    @Override
    public String toString() {
        return String.format("%s [%s] - Ubicación: %s", 
                           getClass().getSimpleName(), 
                           identificacion, 
                           ubicacion);
    }
}

final class DispositivoCosta extends Dispositivo {
    private float salinidad;
    private float nivelMar;
    
    public DispositivoCosta(String identificacion, String ubicacion) {
        super(identificacion, ubicacion);
    }
    
    @Override
    public void recolectarDatos() {
        salinidad = (float) (Math.random() * 50);
        nivelMar = (float) (Math.random() * 10);
        mediciones.add(salinidad);
        mediciones.add(nivelMar);
    }
    
    @Override
    public Map<String, Float> procesarDatos() {
        return Map.of(
            "Salinidad (ppm)", salinidad,
            "Nivel del mar (m)", nivelMar
        );
    }
    
    @Override
    public String generarAlerta() {
        if (nivelMar > 7.5f) return "Alerta: Inundación costera inminente";
        if (salinidad > 35f) return "Alerta: Salinidad crítica";
        return "Condiciones normales";
    }
}

final class DispositivoSierra extends Dispositivo {
    private float radiacionUV;
    private float humedad;
    
    public DispositivoSierra(String identificacion, String ubicacion) {
        super(identificacion, ubicacion);
    }
    
    @Override
    public void recolectarDatos() {
        radiacionUV = (float) (Math.random() * 15);
        humedad = (float) (Math.random() * 100);
        mediciones.add(radiacionUV);
        mediciones.add(humedad);
    }
    
    @Override
    public Map<String, Float> procesarDatos() {
        return Map.of(
            "Radiación UV (UVI)", radiacionUV,
            "Humedad relativa (%)", humedad
        );
    }
    
    @Override
    public String generarAlerta() {
        if (radiacionUV > 11f) return "Alerta: Radiación UV peligrosa";
        if (humedad < 30f) return "Alerta: Sequía severa";
        return "Condiciones normales";
    }
}

final class DispositivoOriente extends Dispositivo {
    private float calidadAire;
    private float indiceDeforestacion;
    
    public DispositivoOriente(String identificacion, String ubicacion) {
        super(identificacion, ubicacion);
    }
    
    @Override
    public void recolectarDatos() {
        calidadAire = (float) (Math.random() * 500);
        indiceDeforestacion = (float) (Math.random() * 100);
        mediciones.add(calidadAire);
        mediciones.add(indiceDeforestacion);
    }
    
    @Override
    public Map<String, Float> procesarDatos() {
        return Map.of(
            "Calidad del aire (AQI)", calidadAire,
            "Deforestación (%)", indiceDeforestacion
        );
    }
    
    @Override
    public String generarAlerta() {
        if (calidadAire > 300f) return "Alerta: Contaminación del aire extrema";
        if (indiceDeforestacion > 70f) return "Alerta: Deforestación acelerada";
        return "Condiciones normales";
    }
}

final class Reporte {
    private final Dispositivo dispositivo;
    private final String fecha;
    private final List<String> riesgos;
    
    public Reporte(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
        this.fecha = java.time.LocalDate.now().toString();
        this.riesgos = new ArrayList<>();
    }
    
    public void agregarRiesgo(String riesgo) {
        riesgos.add(riesgo);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n--- REPORTE AMBIENTAL ---\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append(dispositivo).append("\n\n");
        
        sb.append("Mediciones:\n");
        dispositivo.procesarDatos().forEach((k, v) -> 
            sb.append("- ").append(k).append(": ").append(v).append("\n"));
        
        sb.append("\nAlerta: ").append(dispositivo.generarAlerta()).append("\n");
        
        if (!riesgos.isEmpty()) {
            sb.append("\nRiesgos identificados:\n");
            riesgos.forEach(r -> sb.append("- ").append(r).append("\n"));
        }
        
        sb.append("-----------------------");
        return sb.toString();
    }
}

final class FabricaDispositivos {
    public static Dispositivo crearDispositivo(String tipo, String id, String ubicacion) {
        return switch (tipo.toLowerCase()) {
            case "costa" -> new DispositivoCosta(id, ubicacion);
            case "sierra" -> new DispositivoSierra(id, ubicacion);
            case "oriente" -> new DispositivoOriente(id, ubicacion);
            default -> throw new IllegalArgumentException("Región no válida: " + tipo);
        };
    }
}