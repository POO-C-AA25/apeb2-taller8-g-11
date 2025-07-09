
public class Problema_3_EstadisticasFutbol {
    public static void main(String[] args) {
        Jugador[] equipo = new Jugador[4];

        equipo[0] = new Atacante("Luis ", 7, "12.345.678-9", 2, 20, 5);
        equipo[1] = new Defensor("Carlos ", 4, "23.456.789-0", 0, 15, 10);
        equipo[2] = new Portero("Pedro ", 1, "11.111.111-1", 0, 8);
        equipo[3] = new Atacante("Mbappe", 10, "33.333.333-3", 3, 25, 7);

        for (Jugador j : equipo) {
            j.mostrarDatos();
            System.out.println("Valoracion: " + j.calcularValor() + "\n");
        }
    }
}

class Jugador {
    public String nombre;
    public int dorsal;
    public String rut;
    public int goles;

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Dorsal: " + dorsal);
        System.out.println("RUT: " + rut);
        System.out.println("Goles: " + goles);
    }

    public int calcularValor() {
        return goles * 30;
    }
}

class Atacante extends Jugador {
    public int pases;
    public int recuperaciones;

    public Atacante(String nombre, int dorsal, String rut, int goles, int pases, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pases = pases;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Pases: " + pases);
        System.out.println("Recuperaciones: " + recuperaciones);
        System.out.println("Tipo: Atacante");
    }

    @Override
    public int calcularValor() {
        return super.calcularValor() + recuperaciones * 3;
    }
}

class Defensor extends Jugador {
    public int pases;
    public int recuperaciones;

    public Defensor(String nombre, int dorsal, String rut, int goles, int pases, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pases = pases;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Pases: " + pases);
        System.out.println("Recuperaciones: " + recuperaciones);
        System.out.println("Tipo: Defensor");
    }

    @Override
    public int calcularValor() {
        return super.calcularValor() + recuperaciones * 4;
    }
}

class Portero extends Jugador {
    public int atajadas;

    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Atajadas: " + atajadas);
        System.out.println("Tipo: Portero");
    }

    @Override
    public int calcularValor() {
        return super.calcularValor() + atajadas * 5;
    }
}
