package Controlador;

/**
 */
import Vista.FExpediente;
import Modelo.Expediente;
import Modelo.AbsDatos;
import Formatos.Mensajes;
import Controlador.ControladorMenu;
import Dao.*;
import Lista.*;
import Modelo.ExpedienteDetalle;
import Modelo.Cuota;
import Principal.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class ControladorExpediente extends AbsDatos implements ActionListener {

    ExpedienteDAO expediente = new ExpedienteDAO();
    FExpediente vista;
    int posicion;
    int contador;
    String codigoTemp;
    ExpedienteDetalle CExp;
    public double cuotaf;
    public double interes;
    public double deuda;
    public double montoResto;

    public String GenerarCodigoExpediente() {
        DecimalFormat df = new DecimalFormat("EXP0000");
        return (df.format(contador));
    }

    public ControladorExpediente(FExpediente FExp) {
        this.vista = FExp;
        //recopilando los eventos de los botones
        this.vista.jbtnGrabar.addActionListener(this);
        this.vista.jbtnEliminar.addActionListener(this);
        this.vista.jbtnConsultar.addActionListener(this);
        this.vista.jbtnActualizar.addActionListener(this);
        this.vista.jbtnAprobar.addActionListener(this);
        this.vista.jbtnCalcularCuota.addActionListener(this);
        this.vista.jbtnActualizar.setEnabled(false);
        this.vista.jbtnEliminar.setEnabled(false);
        this.vista.jbtnAprobar.setEnabled(false);
        this.vista.jtxtFechaRegistro.setEnabled(false);
        this.vista.jtxtCodigoExpediente.setEnabled(false);
        this.vista.jtxtCuota.setEnabled(false);
        this.vista.jtxtTea.setEnabled(false);
        this.vista.jtxtEstado.setEnabled(false);
        this.vista.jtxtAprobacion.setEnabled(false);
        this.vista.jtxtFechaRegistro.setText(fechaPC);
        this.vista.jtxtAprobacion.setText(null);
        this.vista.jtxtEstado.setText(estadoExp3);
        this.vista.jtxtTea.setText(String.valueOf(tea));
        this.vista.jcbxCiclica.removeAllItems();
        this.vista.jcbxMoneda.removeAllItems();
        this.vista.jcbxTipoSeg.removeAllItems();
        this.vista.jcbxCiclica.addItem(ciclica1);
        this.vista.jcbxCiclica.addItem(ciclica2);
        this.vista.jcbxMoneda.addItem(moneda1);
        this.vista.jcbxMoneda.addItem(moneda2);
        this.vista.jcbxTipoSeg.addItem(tipoSeguro1);
        this.vista.jcbxTipoSeg.addItem(tipoSeguro2);
        //actualizando el formulario
        ActualizarFormulario();
    }

    void LeerExpediente() {
        CExp = new ExpedienteDetalle();
        CExp.setCodigoExpediente(this.vista.jtxtCodigoExpediente.getText());
        CExp.setFechaRegistro(this.vista.jtxtFechaRegistro.getText());
        CExp.setCliente(this.vista.jtxtCliente.getText());
        CExp.setTipoSeg(this.vista.jcbxTipoSeg.getSelectedItem().toString());
        CExp.setDescripcion(this.vista.jtxtDescripcion.getText());
        CExp.setMoneda(this.vista.jcbxMoneda.getSelectedItem().toString());
        CExp.setfAprobado(null);
        CExp.setEstado(this.vista.jtxtEstado.getText());
        CExp.setCiclica(this.vista.jcbxCiclica.getSelectedItem().toString());
        CExp.setMonto(Integer.parseInt(this.vista.jtxtMonto.getText()));
        CExp.setPlazo(Integer.parseInt(this.vista.jtxtPlazo.getText()));
        CExp.setTea(Integer.parseInt(this.vista.jtxtTea.getText()));
        CExp.setCuota(cuotaf);

    }

    //implementando el metodo de la interface que define los eventos sobre el formulario
    public void calcularCuota() {
        double monto = (Integer.parseInt(this.vista.jtxtMonto.getText()));
        double plazo = (Integer.parseInt(this.vista.jtxtPlazo.getText()));
        double tea = (Integer.parseInt(this.vista.jtxtTea.getText()));
        double teac = (tea / 100) / 12;
        double calc = Math.pow((1 + teac), plazo);
        double cuota = (monto * (teac * calc)) / (calc - 1);
        cuotaf = (double) (Math.round(cuota * 100.0) / 100.0);
        this.vista.jtxtCuota.setText(String.valueOf(cuotaf));
        Mensajes.m1("La cuota ahora es: " + cuotaf + "el interes: " + interes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnCalcularCuota) {
            calcularCuota();
            //ActualizarFormulario();
        }
        if (e.getSource() == vista.jbtnGrabar) {
            try {
                ExpedienteDAO dao = new ExpedienteDAO();
                calcularCuota();
                LeerExpediente(); //leemos los datos  
                expediente.InsertarExpediente(CExp);
                LimpiarEntradas();
                Mensajes.m1("Expediente registrado correctamente!!!");
                ActualizarFormulario();
            } catch (NumberFormatException ne) {
                Mensajes.m1("Faltan datos");

            }

        }

        if (e.getSource() == vista.jbtnConsultar) {

            String codbuscado = Mensajes.m2("Ingrese el código del expediente a buscar : ");
            ExpedienteDAO dao = new ExpedienteDAO();
            CExp = dao.BuscarExpedientePorCodigo(codbuscado);
            if (CExp == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla Expediente");
                vista.jtxtCodigoExpediente.requestFocus();
            } else {
                this.vista.jbtnGrabar.setEnabled(false);
                this.vista.jbtnActualizar.setEnabled(true);
                this.vista.jbtnEliminar.setEnabled(true);
                this.vista.jbtnAprobar.setEnabled(true);
                //actualizamos el formulario con el objeto recuperado
                this.vista.jtxtCodigoExpediente.setText(CExp.getCodigoExpediente());
                this.vista.jtxtCliente.setText(CExp.getCliente());
                this.vista.jtxtCuota.setText(String.valueOf(CExp.getCuota()));
                this.vista.jtxtDescripcion.setText(CExp.getDescripcion());
                this.vista.jtxtFechaRegistro.setText(CExp.getFechaRegistro());
                this.vista.jtxtMonto.setText(String.valueOf(CExp.getMonto()));
                this.vista.jtxtPlazo.setText(String.valueOf(CExp.getPlazo()));
                //  actualizando los combos en base a los datos del objeto
                if (CExp.getTipoSeg().equals(tipoSeguro1)) {
                    vista.jcbxTipoSeg.setSelectedIndex(0);
                }
                if (CExp.getTipoSeg().equals(tipoSeguro2)) {
                    vista.jcbxTipoSeg.setSelectedIndex(1);
                }
                if (CExp.getMoneda().equals(moneda1)) {
                    vista.jcbxMoneda.setSelectedIndex(0);
                }
                if (CExp.getMoneda().equals(moneda2)) {
                    vista.jcbxMoneda.setSelectedIndex(1);
                }
                if (CExp.getCiclica().equals(ciclica1)) {
                    vista.jcbxCiclica.setSelectedIndex(0);
                }
                if (CExp.getCiclica().equals(ciclica2)) {
                    vista.jcbxCiclica.setSelectedIndex(1);
                }
            }
        }
        if (e.getSource() == vista.jbtnActualizar) {
            LeerExpediente();
            ExpedienteDAO dao = new ExpedienteDAO();
            dao.ActualizarExpediente(CExp);
            ActualizarFormulario();
            Mensajes.m1("Datos del expediente actualizados correctamente");
            this.vista.jbtnGrabar.setEnabled(true);
            this.vista.jbtnActualizar.setEnabled(false);
            this.vista.jbtnEliminar.setEnabled(false);
            this.vista.jbtnAprobar.setEnabled(false);
        }
        if (e.getSource() == vista.jbtnEliminar) {
            int respuesta = Mensajes.m3("Desea eliminar el registro?", "Confirmación");
            if (respuesta == 0) { // 0 = Yes
                ExpedienteDAO dao = new ExpedienteDAO();
                dao.EliminarExpediente(vista.jtxtCodigoExpediente.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro del expediente ha sido eliminado.....");
                this.vista.jbtnGrabar.setEnabled(true);
                this.vista.jbtnActualizar.setEnabled(false);
                this.vista.jbtnEliminar.setEnabled(false);
                this.vista.jbtnAprobar.setEnabled(false);

            }
        }

        if (e.getSource() == vista.jbtnAprobar) {
            int respuesta = Mensajes.m3("Desea aprobar el expediente?", "Confirmación");
            if (respuesta == 0) { // 0 = Yes
                LeerExpediente();
                CuotaDAO dao = new CuotaDAO();
                Cuota cu = new Cuota();
                crearCronograma(CExp.getCodigoExpediente());
                ActualizarFormulario();
                Mensajes.m1("Datos de la cuota generado correctamente");
                this.vista.jbtnGrabar.setEnabled(true);
                this.vista.jbtnActualizar.setEnabled(false);
                this.vista.jbtnEliminar.setEnabled(false);
                this.vista.jbtnAprobar.setEnabled(false);
            }
        }
    }

    public ArrayList<Cuota> crearCronograma(String codExp) {
        ArrayList<Cuota> ListaCuoF = new ArrayList();
        Cuota cuo = new Cuota();
        CuotaDAO dao = new CuotaDAO();

        String exp = CExp.getCodigoExpediente();
        double si = CExp.getMonto();
        double t = CExp.getTea();
        double tf = ((t / 100) / 12);
        double c = Double.valueOf(this.vista.jtxtCuota.getText());
        double p = CExp.getPlazo();
        String ci = CExp.getCiclica();
        Calendar fecha = new GregorianCalendar();
        int anio = (fecha.get(Calendar.YEAR));
        int mes = (fecha.get(Calendar.MONTH) + 1);
        String tipseg = CExp.getTipoSeg();
        double impseg = verificarSeguro(tipseg);
        si=si+ impseg;

        for (int i = 1; i <= p; i++) {
            if (mes == 12) {
                mes = 1;
                anio += 1;
            } else {
                mes++;
            }
            String fechad = anio + "-" + mes + "-" + ci;
            double interes = si * tf;
            double capital = c - interes;
            double montoResto = si - capital;
            cuo.setExpediente(exp);
            cuo.setCronograma("C" + exp);
            cuo.setCodCuota(String.valueOf(i));
            cuo.setFecVencimiento(fechad);
            cuo.setSaldoIni(montoResto);
            cuo.setTea(t);
            cuo.setCapital(capital);
            cuo.setInteresProgramado(interes);
            cuo.setSeguroProgramado(impseg);
            cuo.setCuota(c);
            ListaCuoF.add(cuo);
            si = montoResto;
            dao.InsertarCuota(cuo);

        }
        return ListaCuoF;

    }

    public int verificarSeguro(String tiposeg) {
        if (CExp.getTipoSeg().equalsIgnoreCase("OBLIGATORIO")) {
            double monto = CExp.getCuota();
            if (monto > 0 && monto <= 100000) {
                return 1000;
            }
            if (monto > 100000 && monto <= 200000) {
                return 2000;
            }
            if (monto > 200000) {
                return 3000;
            }
        }
        return 0;

    }

    void LimpiarEntradas() {
        //this.vista.jtxtCodigoExpediente.setText("");
        this.vista.jtxtCliente.setText("");
        this.vista.jtxtDescripcion.setText("");
        //this.vista.jtxtFechaRegistro.setText("");
        this.vista.jtxtMonto.setText("");
        this.vista.jtxtPlazo.setText("");
        this.vista.jtxtCuota.setText("");
        this.vista.jcbxCiclica.setSelectedIndex(0);
        this.vista.jcbxMoneda.setSelectedIndex(0);
        this.vista.jcbxTipoSeg.setSelectedIndex(0);
        this.vista.jtxtCodigoExpediente.requestFocus();
    }

    void ActualizarFormulario() {
        ControladorMenu.ListaExp.ListaExp = expediente.SincronizarListaExpediente();
        ControladorMenu.ListaExp.MostrarEnTablaExp(vista.jtblExpediente); // lo mostramos en la tabla
        LimpiarEntradas();
        contador = expediente.obtenerCantidadRegistros() + 1;
        codigoTemp = GenerarCodigoExpediente();
        this.vista.jtxtCodigoExpediente.setText(codigoTemp);
    }

}
