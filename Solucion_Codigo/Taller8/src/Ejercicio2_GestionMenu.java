import java.util.ArrayList;
public class Ejercicio2_GestionMenu {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("Luis Mendez");

<<<<<<< HEAD
        
=======
>>>>>>> 0605e6831a3e34c769a2f911dfa72ab9bd0b68ff
        cuenta.agregarMenu(new MenuCarta("Lomo fino", 15.00, 3.00, 2.00, 10));
        cuenta.agregarMenu(new MenuDia("Sopa del dia", 5.50, 1.50, 1.00));
        cuenta.agregarMenu(new MenuNinos("Hamburguesa", 4.00, 0.50, 0.50));
        cuenta.agregarMenu(new MenuEconomico("Arroz con pollo", 6.00, 20));

        cuenta.calcularValores();

        System.out.println(cuenta);
    }
}


abstract class Menu {
    public String nombrePlato;
    public double valorMenu;

    public Menu(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public abstract void calcularValorMenu();

    @Override
    public String toString() {
        return "Plato: " + nombrePlato + "\nValor menu: " + valorMenu + "\n";
    }
}


class MenuCarta extends Menu {
    public double valorInicial;
    public double guarnicion;
    public double bebida;
    public double porcentajeServicio;

    public MenuCarta(String nombrePlato, double valorInicial, double guarnicion, double bebida, double porcentajeServicio) {
        super(nombrePlato);
        this.valorInicial = valorInicial;
        this.guarnicion = guarnicion;
        this.bebida = bebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    public void calcularValorMenu() {
        valorMenu = valorInicial + guarnicion + bebida + (porcentajeServicio / 100 * valorInicial);
    }

    @Override
    public String toString() {
        return "Menu a la Carta:\n" + super.toString() +
               "Valor inicial: " + valorInicial +
               "\nGuarnicion: " + guarnicion +
               "\nBebida: " + bebida +
               "\nServicio: " + porcentajeServicio + "\n";
    }
}


class MenuDia extends Menu {
    public double valorInicial;
    public double postre;
    public double bebida;

    public MenuDia(String nombrePlato, double valorInicial, double postre, double bebida) {
        super(nombrePlato);
        this.valorInicial = valorInicial;
        this.postre = postre;
        this.bebida = bebida;
    }

    public void calcularValorMenu() {
        valorMenu = valorInicial + postre + bebida;
    }

    @Override
    public String toString() {
        return "Menu del Dia:\n" + super.toString() +
               "Valor inicial: " + valorInicial +
               "\nPostre: " + postre +
               "\nBebida: " + bebida + "\n";
    }
}
class MenuNinos extends Menu {
    public double valorInicial;
    public double helado;
    public double pastel;

    public MenuNinos(String nombrePlato, double valorInicial, double helado, double pastel) {
        super(nombrePlato);
        this.valorInicial = valorInicial;
        this.helado = helado;
        this.pastel = pastel;
    }

    public void calcularValorMenu() {
        valorMenu = valorInicial + helado + pastel;
    }

    @Override
    public String toString() {
        return "Menu de Ninios:\n" + super.toString() +
               "Valor inicial: " + valorInicial +
               "\nHelado: " + helado +
               "\nPastel: " + pastel + "\n";
    }
}

class MenuEconomico extends Menu {
    public double valorInicial;
    public double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorInicial, double porcentajeDescuento) {
        super(nombrePlato);
        this.valorInicial = valorInicial;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void calcularValorMenu() {
        valorMenu = valorInicial - (porcentajeDescuento / 100 * valorInicial);
    }

    @Override
    public String toString() {
        return "Menu Economico:\n" + super.toString() +
               "Valor inicial: " + valorInicial +
               "\nDescuento: " + porcentajeDescuento + "\n";
    }
}



class Cuenta {
    public String nombreCliente;
    public ArrayList<Menu> listaMenu = new ArrayList<>();
    public double subtotal;
    public double iva = 12;
    public double totalPagar;

    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void agregarMenu(Menu menu) {
        listaMenu.add(menu);
    }

    public void calcularValores() {
        subtotal = 0;
        for (Menu m : listaMenu) {
            m.calcularValorMenu();
            subtotal += m.valorMenu;
        }
        totalPagar = subtotal + (iva / 100 * subtotal);
    }

    @Override
    public String toString() {
        String reporte = "CLIENTE: " + nombreCliente + "\n\n";
        for (Menu m : listaMenu) {
            reporte += m.toString() + "\n";
        }
        reporte += String.format("Subtotal: %.2f\nIVA (%.0f%%): %.2f\nTotal a pagar: %.2f\n",
                                 subtotal, iva, (iva / 100 * subtotal), totalPagar);
        return reporte;
    }
}
