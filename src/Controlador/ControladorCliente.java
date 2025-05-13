package Controlador;

import Dao.*;
import Principal.Main;
import Vista.*;
import Modelo.*;
//librerias de las clases que manipulan eventos del formulario
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;//ventanas de dialogo
import javax.swing.JOptionPane;
import Formatos.*;
import java.text.DecimalFormat;

public class ControladorCliente extends AbsDatos implements ActionListener {

    ClienteDAO cliente = new ClienteDAO();
    FCliente vista;
    int posicion;
    int contador;
    Cliente CCli;
    String codigoTemp;

    public String GenerarCodigoCliente() {
        DecimalFormat df = new DecimalFormat("CLI0000");
        return (df.format(contador));
    }

    public ControladorCliente(FCliente FCli) {
        this.vista = FCli;
        this.vista.jbtnGrabar.addActionListener(this);
        this.vista.jbtnActualizar.addActionListener(this);
        this.vista.jbtnEliminar.addActionListener(this);
        this.vista.jbtnActualizar.setEnabled(false);
        this.vista.jbtnEliminar.setEnabled(false);
                this.vista.jtxtCodCliente.setEnabled(false);

        this.vista.jbtnConsultar.addActionListener(this);
        //CBX
        this.vista.jcbxEstadoCli.removeAllItems();
        this.vista.jcbxEstadoCli.addItem(estado1);
        this.vista.jcbxEstadoCli.addItem(estado2);
        this.vista.jcbxEstadoCivil.removeAllItems();
        this.vista.jcbxEstadoCivil.addItem(estcivil1);
        this.vista.jcbxEstadoCivil.addItem(estcivil2);
        this.vista.jcbxEstadoCivil.addItem(estcivil3);
        this.vista.jcbxEstadoCivil.addItem(estcivil4);
//        this.vista.jcbxCFuncionario.removeAllItems();
//        this.vista.jcbxCFuncionario.addItem("Prueba");
        this.vista.jcbxSexo.removeAllItems();
        this.vista.jcbxSexo.addItem(sexo1);
        this.vista.jcbxSexo.addItem(sexo2);
        ActualizarFormulario();
    }

    void LimpiarEntradas() {
        this.vista.jtxtCodCliente.setText("");
        this.vista.jtxtCliente.setText("");
        this.vista.jtxtfNacimiento.setText("");
        this.vista.jtxtDNI.setText("");
        this.vista.jtxtDireccion.setText("");
        this.vista.jtxtEmail.setText("");
        this.vista.jtxtTelefono.setText("");
        this.vista.jtxtCIDN.setText("");
        this.vista.jtxtCIP.setText("");
        this.vista.jtxtfIniAporte.setText("");
        this.vista.jtxtSueldo.setText("");
        this.vista.jcbxEstadoCli.setSelectedIndex(0);
        this.vista.jcbxEstadoCivil.setSelectedIndex(0);
        this.vista.jtxtClsFun.setText("");
        this.vista.jcbxSexo.setSelectedIndex(0);
        this.vista.jtxtCliente.requestFocus();
    }

    void LeerCliente() {
        CCli = new Cliente();
        CCli.setCodigo(this.vista.jtxtCodCliente.getText());
        CCli.setNombres(this.vista.jtxtCliente.getText());
        CCli.setFechaNacimiento(this.vista.jtxtfNacimiento.getText());
        CCli.setNroDocumento(this.vista.jtxtDNI.getText());
        CCli.setDireccion(this.vista.jtxtDireccion.getText());
        CCli.setEmail(this.vista.jtxtEmail.getText());
        CCli.setTelefono(this.vista.jtxtTelefono.getText());
        CCli.setEstadoCivil(this.vista.jcbxEstadoCivil.getSelectedItem().toString());
        CCli.setSexo(this.vista.jcbxSexo.getSelectedItem().toString());
        CCli.setNroCid(this.vista.jtxtCIDN.getText());
        CCli.setCip(this.vista.jtxtCIP.getText());
        CCli.setfIngreso(this.vista.jtxtfIniAporte.getText());
        CCli.setClsfun(this.vista.jtxtClsFun.getText());
        CCli.setSituacion(this.vista.jcbxEstadoCli.getSelectedItem().toString());
        CCli.setSueldo(Integer.parseInt(this.vista.jtxtSueldo.getText()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnGrabar) {
            LeerCliente();//leemos los datos
            ClienteDAO dao = new ClienteDAO();
            cliente.InsertarCliente(CCli);
            LimpiarEntradas();
            Mensajes.m1("Cliente registrado correctamente!!!");
            ActualizarFormulario();
        }
        if (e.getSource() == vista.jbtnConsultar) {
            String codbuscado = Mensajes.m2("Ingrese el código del cliente a buscar : ");
            ClienteDAO dao = new ClienteDAO();
            CCli = dao.BuscarClientePorCodigo(codbuscado);
            if (CCli == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla Cliente");
                vista.jtxtCodCliente.requestFocus();
            } else {
                this.vista.jbtnGrabar.setEnabled(false);
                this.vista.jbtnActualizar.setEnabled(true);
                this.vista.jbtnEliminar.setEnabled(true);
                //actualizamos el formulario con el objeto recuperado
                this.vista.jtxtCodCliente.setText(CCli.getCodigo());
                this.vista.jtxtCliente.setText(CCli.getNombres());
                this.vista.jtxtfNacimiento.setText(CCli.getFechaNacimiento());
                this.vista.jtxtDNI.setText(CCli.getNroDocumento());
                this.vista.jtxtDireccion.setText(CCli.getDireccion());
                this.vista.jtxtEmail.setText(CCli.getEmail());
                this.vista.jtxtTelefono.setText(CCli.getTelefono());
                //2da parte
                this.vista.jtxtCIDN.setText(CCli.getNroCid());
                this.vista.jtxtCIP.setText(CCli.getCip());
                this.vista.jtxtfIniAporte.setText(CCli.getfIngreso());
                this.vista.jtxtClsFun.setText(CCli.getClsfun());
                this.vista.jtxtSueldo.setText(String.valueOf(CCli.getSueldo()));
                //Combo box

            }

        }
        if (e.getSource() == vista.jbtnActualizar) {
            LeerCliente();
            ClienteDAO dao = new ClienteDAO();
            dao.ActualizarCliente(CCli);
            ActualizarFormulario();
            Mensajes.m1("Datos del Cliente actualizados correctamente");
        }
        if (e.getSource() == vista.jbtnEliminar) {
            int respuesta = Mensajes.m3("Desea eliminar el registro?", "Confirmación");
            if (respuesta == 0) {//0=YES
                ClienteDAO dao = new ClienteDAO();
                dao.EliminarCliente(vista.jtxtCodCliente.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro del cliente ha sido eliminado.....");
                            this.vista.jbtnGrabar.setEnabled(true);
                this.vista.jbtnActualizar.setEnabled(false);
                this.vista.jbtnEliminar.setEnabled(false);
            }
        }
    }

    void ActualizarFormulario() {
        LimpiarEntradas();
        ControladorMenu.ListaCli.ListaCli = cliente.SincronizarListaCliente();
        ControladorMenu.ListaCli.MostrarEnTablaCli(vista.jtblCliente);//lo mostramos en la tabla
        contador = cliente.obtenerCantidadRegistros() + 1;
        codigoTemp = GenerarCodigoCliente();
        this.vista.jtxtCodCliente.setText(codigoTemp);
    }

}
