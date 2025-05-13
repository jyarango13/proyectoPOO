package Dao;

import Modelo.Cuota;
import java.util.ArrayList;
import Formatos.Mensajes;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 */
public class CuotaDAO extends ConectarBD {

    Cuota cuo = new Cuota();

    public ArrayList<Cuota> SincronizarListaCuota(String codExp) {
        ArrayList<Cuota> ListaCuo = new ArrayList();
        try {
            rs = st.executeQuery("SELECT C_C_EXPEDIENTE,N_I_TIPSEG,N_N_MONTO,N_I_CICLICA,N_N_TEA,N_N_CUOTA,N_I_PLAZO,D_APROBADO  FROM S10EXP WHERE C_C_EXPEDIENTE='" + codExp + "';");
                //    + "SELECT N_I_CRONOGRAMA FROM S10CRD WHERE C_C_EXPEDIENTE='" + codExp + "';");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                //ojooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOO PUEDE FALLAR POR INT
                cuo.setExpediente(rs.getString(1)); //11
                cuo.setTipoSeg(rs.getString(2));
                cuo.setSaldoIni(rs.getInt(3));
                cuo.setCiclica(rs.getString(4));
                cuo.setTea(rs.getInt(5));
                cuo.setCuota(rs.getInt(6));
                cuo.setPlazo(rs.getInt(7));
                cuo.setFecAprobacion(rs.getString(8));
               // cuo.setCronograma(rs.getString(9));
                ListaCuo.add(cuo);
            }
            rs.close(); //cerramos la conexion para liberar espacio

        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return ListaCuo;
    }

    public ArrayList<Cuota> crearCronograma(String codExp) {
        ArrayList<Cuota> ListaCuoF = new ArrayList();
        SincronizarListaCuota(codExp);
        String exp = cuo.getExpediente();
        double si = cuo.getSaldoIni();
        double t = cuo.getTea();
        double tf = ((t / 100) / 12);
        double c = cuo.getCuota();
        double p = cuo.getPlazo();
        String da = cuo.getFecAprobacion();
        String ci = cuo.getCiclica();
        Calendar fecha = new GregorianCalendar();
        int anio = (fecha.get(Calendar.YEAR));
        int mes = (fecha.get(Calendar.MONTH) + 1);
        cuo.setSeguroProgramado(verificarSeguro());

        for (int i = 1; i <= p; i++) {
            if (mes == 12) {
                mes = 1;
                anio += 1;
            } else {
                mes++;
            }
            double seguro = cuo.getSeguroProgramado();
            //seguro falta asignar
            double interes = si * tf;
            double capital = c - interes;
            double montoResto = si - capital;
            cuo.setCodCuota(String.valueOf(i));
            cuo.setExpediente(exp);
            cuo.setCuota(c);
            cuo.setFecAprobacion(anio + "/" + mes + "/" + ci);
            cuo.setSaldoIni(montoResto);
            cuo.setTea(t);
            cuo.setCapital(capital);
            cuo.setInteresProgramado(interes);
            cuo.setSeguroProgramado(seguro/p);
            //cadena += exp + " vence: " + anio + "/" + (mes) + "/" + ci + " cuota: " + c + " interes: " + interes + " capital/deuda: " + capital + " monto resto : " + montoResto + "\n";
            ListaCuoF.add(cuo);
            si = montoResto;
        }

        return ListaCuoF;

    }
    
    public void InsertarCuota(Cuota Cuota) {
        try {
            ps = conexion.prepareStatement("INSERT INTO S10CUO VALUES(?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, Cuota.getExpediente());
            ps.setString(2, Cuota.getCronograma());
            ps.setString(3, Cuota.getCodCuota());
            ps.setString(4, Cuota.getFecVencimiento());
            ps.setDouble(5, Cuota.getSaldoIni());
            ps.setDouble(6, Cuota.getTea());
            ps.setDouble(7, Cuota.getCapital());
            ps.setDouble(8, Cuota.getInteresProgramado());
            ps.setDouble(9, Cuota.getSeguroProgramado());
            ps.setDouble(10, Cuota.getCuota());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR: no se puede insertar el registro...." + e);
        }
    }

     public Cuota BuscarCuotaPorCodigo(String codExp) {
        Cuota cuo = null; //creamos el objeto inicializamos en vacio
        try {
            rs = st.executeQuery("SELECT N_I_CUOTA,D_VENCIMIENTO,N_N_SALDOINI,N_N_TEA,N_N_CAPITAL,N_N_INTPRG,C_I_CUOTACALC FROM S10CUO WHERE C_C_EXPEDIENTE='" + codExp + "'");
            if (rs.next()) {
                cuo = new Cuota();
                cuo.setCodCuota(rs.getString(1));
                cuo.setFecVencimiento(rs.getString(2));
                cuo.setSaldoIni(rs.getInt(3));
                cuo.setTea(rs.getInt(4));
                cuo.setCapital(rs.getInt(5));
                cuo.setInteresProgramado(rs.getInt(6));
                cuo.setCuota(rs.getInt(7));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede buscar el codigo .." + e);
        }
        return cuo;
    }

    public int verificarSeguro() {
        if (cuo.getTipoSeg().equalsIgnoreCase("OBLIGATORIO")) {
            double monto = cuo.getCuota();
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

}
