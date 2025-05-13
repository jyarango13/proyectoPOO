package Controlador;

import Lista.ListaExpediente;
import Lista.ListaGrado;
import Lista.ListaCliente;
import Lista.ListaCuota;
import java.awt.event.ActionEvent;
import Vista.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 */
public class ControladorMenu implements ActionListener {

    Menu vista;
    //Grado
    public static FGrado FGra;
    public static ListaGrado ListaGra;
    public static ControladorGrado ControlGra;
    //Expediente
    public static FExpediente FExp;
    public static ListaExpediente ListaExp;
    public static ControladorExpediente ControlExp;
    //Cronograma
    public static FCronograma FCro;
//    public static ListaCuota ListaCuo;
//    public static ControladorCronograma ControlCro;
    //Cliente
        public static FCliente FCli;
    public static ListaCliente ListaCli;
    public static ControladorCliente ControlCli;


    public ControladorMenu(Menu menu) {
        this.vista = menu;
        this.vista.jbtnExpediente.addActionListener(this);
        botonTransparente(vista.jbtnExpediente);
        this.vista.jbtnCliente.addActionListener(this);
        botonTransparente(vista.jbtnCliente);
        this.vista.jbtnCronograma.addActionListener(this);
        botonTransparente(vista.jbtnCronograma);
        this.vista.jbtnMantenimiento.addActionListener(this);
        botonTransparente(vista.jbtnMantenimiento);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.jbtnCliente) {
            ListaCli= new ListaCliente();
            FCli = new FCliente();
            ControlCli = new ControladorCliente(FCli);
               FCli.setBounds(280, 400,1000, 640);
            FCli.setResizable(false);
            FCli.setLocationRelativeTo(null);
            FCli.setVisible(true);
        }
        if (e.getSource() == vista.jbtnExpediente) {
            ListaExp = new ListaExpediente();
            FExp = new FExpediente();
            ControlExp = new ControladorExpediente(FExp);
            FExp.setBounds(280, 400,900, 640);
            FExp.setResizable(false);
            FExp.setLocationRelativeTo(null);
            FExp.setVisible(true);
        }
        if (e.getSource() == vista.jbtnCronograma) {
//            ListaCuo =new ListaCuota();
//            FCro=new FCronograma();
            FCro = new FCronograma();
            FCro.setBounds(280, 400, 960, 550);
            FCro.setResizable(false);
            FCro.setLocationRelativeTo(null);
            FCro.setVisible(true);
        }
        if (e.getSource() == vista.jbtnMantenimiento) {
            ListaGra = new ListaGrado();
            FGra = new FGrado();
            ControlGra = new ControladorGrado(FGra);
            FGra.setBounds(280, 400, 736, 550);
            FGra.setResizable(false);
            FGra.setLocationRelativeTo(null);
            FGra.setVisible(true);
        }
    }

    void botonTransparente(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(true);
        b.setFocusPainted(false);
    }

}
