
/**
 *
 * @author franc
 */
public class Problema_1JuegoDeRoles {
    static Personaje guerrero;
    static Personaje mago;
    static Personaje arquero;

    public static void main(String[] args) {
        guerrero = new Guerrero("Gigante", 3);
        mago = new Mago("Invisible", 3);
        arquero = new Arquero("Premonicion", 3);

        System.out.println("COMIENZAN LOS COMBATES ENTRE LOS 3 PERSONAJES\n");

        
        System.out.println("️ Guerrero vs Mago");
        pelear(guerrero, mago);

        
        System.out.println("️ Mago vs Arquero");
        pelear(mago, arquero);

        
        System.out.println("⚔️ Arquero vs Guerrero");
        pelear(arquero, guerrero);

        System.out.println("\n ESTADO FINAL DE LOS PERSONAJES:");
        System.out.println("Guerrero: " + guerrero);
        System.out.println("Mago: " + mago);
        System.out.println("Arquero: " + arquero);
    }

    public static void pelear(Personaje atacante, Personaje defensor) {
        boolean gana = atacante.ataque(defensor);
        if (gana) {
            atacante.experiencia += 1;
            atacante.batallasGanadas += 1;
            defensor.vidas -= 1;
            System.out.println(atacante.getClass().getSimpleName() + " gana el combate.");
        } else {
            defensor.experiencia += 1;
            defensor.batallasGanadas += 1;
            atacante.vidas -= 1;
            System.out.println(defensor.getClass().getSimpleName() + " gana el combate.");
        }
        System.out.println();
    }
}

abstract class Personaje {
    public int vidas;
    public int experiencia;
    public int batallasGanadas;

    public Personaje(int vidas) {
        this.vidas = vidas;
        this.experiencia = 0;
        this.batallasGanadas = 0;
    }

    public abstract boolean ataque(Personaje personaje);
    public abstract int defensa();

    @Override
    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + '}';
    }
}

class Guerrero extends Personaje {
    public String habilidades;

    public Guerrero(String habilidades, int vidas) {
        super(vidas);
        this.habilidades = habilidades;
    }

    public boolean ataque(Personaje personaje) {
        int ataque = (int)(Math.random() * 10 + 1) + experiencia;
        int defensa = personaje.defensa();
        return ataque > defensa;
    }

    public int defensa() {
        return (int)(Math.random() * 5 + 6);
    }

    @Override
    public String toString() {
        return "Guerrero{" + "habilidades='" + habilidades + '\'' + ", " + super.toString() + '}';
    }
}

class Mago extends Personaje {
    public String estrategia;

    public Mago(String estrategia, int vidas) {
        super(vidas);
        this.estrategia = estrategia;
    }

    public boolean ataque(Personaje personaje) {
        int ataque = (int)(Math.random() * 10 + 1) + experiencia;
        int defensa = personaje.defensa();
        return ataque > defensa;
    }

    public int defensa() {
        return (int)(Math.random() * 6 + 3);
    }

    @Override
    public String toString() {
        return "Mago{" + "estrategia='" + estrategia + '\'' + ", " + super.toString() + '}';
    }
}

class Arquero extends Personaje {
    public String atributos;

    public Arquero(String atributos, int vidas) {
        super(vidas);
        this.atributos = atributos;
    }

    public boolean ataque(Personaje personaje) {
        int ataque = (int)(Math.random() * 10 + 1) + experiencia;
        int defensa = personaje.defensa();
        return ataque > defensa;
    }

    public int defensa() {
        return (int)(Math.random() * 10 + 1);
    }

    @Override
    public String toString() {
        return "Arquero{" + "atributos='" + atributos + '\'' + ", " + super.toString() + '}';
    }
}

