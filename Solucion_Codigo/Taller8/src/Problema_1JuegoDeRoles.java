
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
        boolean gana = guerrero.ataque(mago);
        if (gana){
            guerrero.experiencia += 1;
            guerrero.batallasGanadas += 1;
            mago.vidas -=1 ;
        }else{
            mago.experiencia += 1;
            mago.batallasGanadas += 1;
            guerrero.vidas -=1;
        }
    System.out.println("Guerrero: " + guerrero + "Mago: " + mago);
    }
    
}
abstract class Personaje{
    public int vidas;
    public int experiencia;
    public int batallasGanadas;

    public Personaje(int vidas) {
        this.vidas = vidas;
    }
    
    public abstract boolean ataque(Personaje personaje);
    public abstract int defensa();

    @Override
    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + '}';
    }
    
}

class Guerrero extends Personaje{
    public String habilidades;

    public Guerrero(String habilidades, int vidas) {
        super(vidas);
        this.habilidades = habilidades;
    }
    
    public boolean ataque(Personaje personaje){
        int bandera = (int) (Math.random() * 2);
        boolean gana = (bandera == 1) ? true : false;
        return gana;  
    }

    @Override
    public String toString() {
        return "Guerrero{" + "habilidades=" + habilidades + super.toString() +'}';
    }
    
    public  int defensa(){
        
        return 0;
    }
}

class Mago extends Personaje{
    public String estrategia;

    public Mago(String estrategia, int vidas) {
        super(vidas);
        this.estrategia = estrategia;
    }
    
    public boolean ataque(Personaje personaje){
        int bandera = (int) (Math.random() * 1);
        boolean gana = (bandera == 1) ? true : false;
        return gana;
    }
    
    public  int defensa(){
        return 0;
    }
}

class Arquero extends Personaje{
    
    public String atributos;

    public Arquero(String atributos, int vidas) {
        super(vidas);
        this.atributos = atributos;
    }
    
    public boolean ataque(Personaje personaje){
        int bandera = (int) (Math.random() * 2);
        boolean gana = (bandera == 1) ? true : false;
        return gana;
    }
    
    public  int defensa(){
        
        return 0;
    }
}