package Principal;

/**
 */
import Controlador.*;
import Modelo.*;
import Vista.*;
import Lista.*;

public class Main {

    public static Menu menu;
    public static ControladorMenu ControlMenu;

 //public static FExpediente FExp;
//    public static ListaExpediente ListaExp;
//    public static ControladorExpediente ControlExp;

    public static FGrado FGra;
    public static ListaGrado ListaGra;
    public static ControladorGrado ControlGra;

    public static void main(String[] args) {
        ListaGra = new ListaGrado();
        FGra = new FGrado();
        ControlGra = new ControladorGrado(FGra);
//        ListaExp = new ListaExpediente();
//        FExp = new FExpediente();
//        ControlExp = new ControladorExpediente(FExp);
        menu = new Menu();
        menu.setVisible(true);
      
        menu.setTitle("MENU");
        menu.setLocationRelativeTo(null);
        ControlMenu = new ControladorMenu(menu);

    }

}
