import java.util.ArrayList;
import java.util.List;
public class Problema_5_Emprendimiento {
    
    public static void main(String[] args) {
        
        Emprendimiento e1 = new EmprendimientoTec("LojaTech", "Innovar con software", "loja@tech.com");
        Emprendimiento e2 = new EmprendimientoGast("Sabores de Loja", "Rescatar la cocina tradicional", "sabores@loja.com");
        Emprendimiento e3 = new EmprendimientoAgri("EcoAgro", "Fomentar la agricultura organica", "eco@loja.com");

       
        Mentor m1 = new Mentor("Andrea ", "Marketing");
        Mentor m2 = new Mentor("Juan ", "Desarrollo de Software");
        Mentor m3 = new Mentor("Lucía ", "Contabilidad");

        
        e1.registrarProducto("App movil de turismo");
        e1.registrarProducto("Sistema de reservas");

        e2.registrarProducto("Tamal lojano");
        e2.registrarProducto("Roscones artesanales");

        e3.registrarProducto("Cafe organico");
        e3.registrarProducto("Miel natural");

        
        e1.agregarMentor(m1);
        e1.agregarMentor(m2);
        e2.agregarMentor(m3);

        
        System.out.println(e1.participarEnFeria());
        System.out.println(e2.participarEnFeria());
        System.out.println(e3.participarEnFeria());

        
        e1.evolucionar();
        e2.evolucionar();

        
        System.out.println("\nEstado actual de los emprendimientos");
        System.out.println(e1);
        System.out.println();
        System.out.println(e2);
        System.out.println();
        System.out.println(e3);
    }
}


class Mentor {
    public String nombre;
    public String especialidad;

    public Mentor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}



class Emprendimiento {
    public String nombre;
    public String tipo;
    public String mision;
    public String contacto;
    public List<String> productos;
    public List<Mentor> mentores;

    public Emprendimiento(String nombre, String tipo, String mision, String contacto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.mision = mision;
        this.contacto = contacto;
        this.productos = new ArrayList<>();
        this.mentores = new ArrayList<>();
    }

    public void registrarProducto(String producto) {
        productos.add(producto);
    }

    public void agregarMentor(Mentor mentor) {
        mentores.add(mentor);
    }

    public String participarEnFeria() {
        return nombre + " participa en una feria local.";
    }

    public void evolucionar() {
        productos.add("Nuevo producto diversificado");
        contacto += " / Nueva sede abierta";
    }

    @Override
    public String toString() {
        return "Emprendimiento: " + nombre +
                "\nTipo: " + tipo +
                "\nMisión: " + mision +
                "\nContacto: " + contacto +
                "\nProductos: " + productos +
                "\nMentores: " + mentores;
    }
}


class EmprendimientoTec extends Emprendimiento {
    public EmprendimientoTec(String nombre, String mision, String contacto) {
        super(nombre, "Tecnologico", mision, contacto);
    }

    @Override
    public String participarEnFeria() {
        return nombre + " participa con innovación en feria tecnologica.";
    }
}

class EmprendimientoGast extends Emprendimiento {
    public EmprendimientoGast(String nombre, String mision, String contacto) {
        super(nombre, "Gastronomico", mision, contacto);
    }

    @Override
    public String participarEnFeria() {
        return nombre + " ofrece degustaciones en feria culinaria.";
    }
}

class EmprendimientoAgri extends Emprendimiento {
    public EmprendimientoAgri(String nombre, String mision, String contacto) {
        super(nombre, "Agrícola", mision, contacto);
    }

    @Override
    public String participarEnFeria() {
        return nombre + " presenta productos orgánicos en feria agropecuaria.";
    }
}

