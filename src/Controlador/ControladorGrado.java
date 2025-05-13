package Controlador;

/**
 */
import Vista.*;
import Modelo.*;
import Formatos.*;
import Dao.*;
import Lista.*;
import Principal.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ControladorGrado implements ActionListener {

    GradoDAO grado = new GradoDAO();
    FGrado vista;
    int posicion;
    int contador;
    String codigoTemp;
    Grado CGra;

    public String GenerarCodigoGrado() {
        DecimalFormat df = new DecimalFormat("GRA0000");
        return (df.format(contador));
    }

    public ControladorGrado(FGrado FGra) {
        this.vista = FGra;
        //recopilando los eventos de los botones
        this.vista.jbtnGrabar.addActionListener(this);
        this.vista.jbtnEliminar.addActionListener(this);
        this.vista.jbtnConsultarGrado.addActionListener(this);
        this.vista.jbtnActualizar.addActionListener(this);
        this.vista.jbtnActualizar.setEnabled(false);
        this.vista.jbtnEliminar.setEnabled(false);
        this.vista.jtxtCodClase.setEnabled(false);
        //actualizando el formulario
        ActualizarFormulario();
    }

    void LeerGrado() {
        CGra = new Grado();
        CGra.setCodGrado(this.vista.jtxtCodClase.getText());
        CGra.setNombre(this.vista.jtxtNombreG.getText());
        CGra.setCorta(this.vista.jtxtCorta.getText());
        CGra.setDescripcion(this.vista.jtxtDesc.getText());
        CGra.setAlcance(Integer.parseInt(this.vista.jtxtAlcance.getText()));
        CGra.setEstado(this.vista.jtxtEstado.getText());
    }
    //implementando el metodo de la interface que define los eventos sobre el formulario

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnGrabar) {
            GradoDAO dao = new GradoDAO();
            LeerGrado(); //leemos los datos               
            grado.InsertarGrado(CGra);
            LimpiarEntradas();
            Mensajes.m1("Grado registrado correctamente!!!");
            ActualizarFormulario();
        }

        if (e.getSource() == vista.jbtnConsultarGrado) {

            String codbuscado = Mensajes.m2("Ingrese el código del grado a buscar : ");
            GradoDAO dao = new GradoDAO();
            CGra = dao.BuscarGradoPorCodigo(codbuscado);
            if (CGra == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla Grado");
                vista.jtxtCodClase.requestFocus();
            } else {
                this.vista.jbtnGrabar.setEnabled(false);
                this.vista.jbtnActualizar.setEnabled(true);
                this.vista.jbtnEliminar.setEnabled(true);
                //actualizamos el formulario con el objeto recuperado
                this.vista.jtxtCodClase.setText(CGra.getCodGrado());
                this.vista.jtxtNombreG.setText(CGra.getNombre());
                this.vista.jtxtCorta.setText(CGra.getCorta());
                this.vista.jtxtDesc.setText(CGra.getDescripcion());
                this.vista.jtxtAlcance.setText(String.valueOf(CGra.getAlcance()));
                this.vista.jtxtEstado.setText(String.valueOf(CGra.getEstado()));
            }

        }
        if (e.getSource() == vista.jbtnActualizar) {
            LeerGrado();
            GradoDAO dao = new GradoDAO();
            dao.ActualizarGrado(CGra);
            ActualizarFormulario();
            Mensajes.m1("Datos del grado actualizados correctamente");
            this.vista.jbtnGrabar.setEnabled(true);
            this.vista.jbtnActualizar.setEnabled(false);
            this.vista.jbtnEliminar.setEnabled(false);
        }
        if (e.getSource() == vista.jbtnEliminar) {
            int respuesta = Mensajes.m3("Desea eliminar el registro?", "Confirmación");
            if (respuesta == 0) { // 0 = Yes
                GradoDAO dao = new GradoDAO();
                dao.EliminarGrado(vista.jtxtCodClase.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro del grado ha sido eliminado.....");
                  this.vista.jbtnGrabar.setEnabled(true);
            this.vista.jbtnActualizar.setEnabled(false);
            this.vista.jbtnEliminar.setEnabled(false);
            }
        }
    }

    void LimpiarEntradas() {
        //this.vista.jtxtCodClase.setText("");
        this.vista.jtxtNombreG.setText("");
        this.vista.jtxtCorta.setText("");
        this.vista.jtxtDesc.setText("");
        this.vista.jtxtAlcance.setText("");
        this.vista.jtxtEstado.setText("");
    }

    void ActualizarFormulario() {
        Main.ListaGra.ListaGra = grado.SincronizarListaGrado();
        Main.ListaGra.MostrarEnTablaGra(vista.jtblGrados); // lo mostramos en la tabla
        LimpiarEntradas();
        contador = grado.obtenerCantidadRegistrosG() + 1;
        codigoTemp = GenerarCodigoGrado();
        this.vista.jtxtCodClase.setText(codigoTemp);
    }

}
