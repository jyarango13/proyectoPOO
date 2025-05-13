//package Controlador;
//
//import Dao.CuotaDAO;
//import Formatos.Mensajes;
//import Lista.ListaCuota;
//import Lista.ListaExpediente;
//import Lista.ListaGrado;
//import java.awt.event.ActionEvent;
//import Vista.*;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import Modelo.Cuota;
//
///**
// */
//public class ControladorCronograma implements ActionListener {
//
//    FCronograma vista;
//   Cuota cuo;
//               CuotaDAO cuota=new CuotaDAO();
//
//    public static ListaCuota ListaCuo;
//
//    public ControladorCronograma(FCronograma FCro) {
//        this.vista = FCro;
//        this.vista.jbtnConsultarExpCronograma.addActionListener(this);
//       }
//    void ActualizarFormulario(){
//               ControladorMenu.ListaCuo.ListaCuo = cuota.SincronizarListaCuota();
//        ControladorMenu.ListaCuo.MostrarEnTablaCuo(vista.jtblCronograma); // lo mostramos en la tabla
//    }
//      
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == vista.jbtnConsultarExpCronograma) {
//                         // String codbuscado = Mensajes.m2("Ingrese el código del expediente a buscar : ");
//                         String codbusc=this.vista.jtxtBuscarExpCronograma.getText();
//            CuotaDAO dao=new CuotaDAO();
//            cuo=dao.BuscarCuotaPorCodigo(codbusc);
//            if (cuo == null) {
//                Mensajes.m1("El codigo " + codbusc + " no existe.");
//                vista.jtxtBuscarExpCronograma.requestFocus();
//            } else {
//      
//        }
//    }
//
//}
//}
//
