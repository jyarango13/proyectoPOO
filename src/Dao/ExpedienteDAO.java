package Dao;

import Formatos.Mensajes;
import Modelo.ExpedienteDetalle;
import Interfaces.AccionesExpediente;
import java.util.ArrayList;

/**
 */
public class ExpedienteDAO extends ConectarBD implements AccionesExpediente {

    @Override
    public ArrayList<ExpedienteDetalle> SincronizarListaExpediente() {
        ArrayList<ExpedienteDetalle> ListaExp = new ArrayList();
        try {
            //ejecutamos la consulta
            //  public String[] EncabezadoExpediente= {"Num","Codigo","Fecha Reg","Cliente","Moneda","Monto", "Inicial"};
            rs = st.executeQuery("SELECT C_C_EXPEDIENTE,D_REGISTRO,C_C_CLIENTE,N_I_MONEDA,N_N_MONTO,N_N_CUOTA,D_APROBADO,N_I_ESTADO  FROM S10EXP;");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                ExpedienteDetalle exp = new ExpedienteDetalle(); //creamos el objeto vacio
                exp.setCodigoExpediente(rs.getString(1)); //11
                exp.setFechaRegistro(rs.getString(2));
                exp.setCliente(rs.getString(3));
                exp.setMoneda(rs.getString(4));
                exp.setMonto(rs.getInt(5));
                exp.setCuota(rs.getInt(6));
                exp.setfAprobado(rs.getString(7));
                exp.setEstado(rs.getString(8));
                ListaExp.add(exp);

//                exp.setUsuario(rs.getString(3));
//                exp.setTipoSeg(rs.getString(5));
//                exp.setDescripcion(rs.getString(6));
//                exp.setPlazo(rs.getInt(10));
//                exp.setCiclica(rs.getString(11));
            }
            rs.close(); //cerramos la conexion para liberar espacio

        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return ListaExp;
    }

    @Override
    public void InsertarExpediente(ExpedienteDetalle exp) {
        try {
            //definimos la sql con interrogantes(parametros) para que sean actualizado desde el metodo
            ps = conexion.prepareStatement("INSERT INTO S10EXP VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
            //actualizar los parametros de la consulta
            ps.setString(1, exp.getCodigoExpediente());
            ps.setString(2, exp.getFechaRegistro());
            ps.setString(3, exp.getCliente());
            ps.setString(4, exp.getTipoSeg());
            ps.setString(5, exp.getDescripcion());
            ps.setString(6, exp.getMoneda());
            ps.setInt(7, exp.getMonto());
            ps.setInt(8, exp.getPlazo());
            ps.setString(9, exp.getCiclica());
            ps.setInt(10, exp.getTea());
            ps.setDouble(11, exp.getCuota());
            ps.setString(12, null);
            ps.setString(13, exp.getEstado());
            ps.executeUpdate(); // actualiza y ejecuta la consulta SQL
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR: no se puede insertar el registro ..." + e);
        }
    }

    @Override
    public ExpedienteDetalle BuscarExpedientePorCodigo(String codExpediente) {
        ExpedienteDetalle exp = null; //creamos el objeto inicializamos en vacio
        try {
            rs = st.executeQuery(
                    "SELECT * FROM S10EXP WHERE C_C_EXPEDIENTE='" + codExpediente + "';");
            if (rs.next()) {
                exp = new ExpedienteDetalle();
                exp.setCodigoExpediente(rs.getString(1)); //11
                exp.setFechaRegistro(rs.getString(2));
                exp.setCliente(rs.getString(3));
                exp.setMoneda(rs.getString(6));
                exp.setMonto(rs.getInt(7));
                exp.setTipoSeg(rs.getString(4));
                //Completando
              
                exp.setDescripcion(rs.getString(5));
                exp.setPlazo(rs.getInt(8));
                exp.setTea(rs.getInt(10));
                exp.setCuota(rs.getInt(11));
                exp.setCiclica(rs.getString(9));
                exp.setfAprobado(rs.getString(12));
                exp.setEstado(rs.getString(13));
                
                  exp.setUsuario(rs.getString(3));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede buscar el codigo .." + e);
        }
        return exp;
    }

    @Override
    public void ActualizarExpediente(ExpedienteDetalle nuevoExpediente) {
        try {
            //
            ps = conexion.prepareStatement("UPDATE S10EXP SET D_REGISTRO= ?,"
                    + " C_C_USUARIO= ?,C_C_CLIENTE= ?,C_C_TIPSEG= ?,"
                    + "C_T_DESCRIPCION= ?,N_I_MONEDA= ?,N_N_MONTO= ?,"
                    + "N_I_PLAZO= ?,N_I_CICLICA= ? WHERE C_C_EXPEDIENTE = ?;");
            ps.setString(1, nuevoExpediente.getFechaRegistro());
            ps.setString(2, nuevoExpediente.getUsuario());
            ps.setString(3, nuevoExpediente.getCliente());
            ps.setString(4, nuevoExpediente.getTipoSeg());
            ps.setString(5, nuevoExpediente.getDescripcion());
            ps.setString(6, nuevoExpediente.getMoneda());
            ps.setInt(7, nuevoExpediente.getMonto());
            ps.setInt(8, nuevoExpediente.getPlazo());
            ps.setString(9, nuevoExpediente.getCiclica());
            ps.setString(10, nuevoExpediente.getCodigoExpediente());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede actualizar el registro..." + e);
        }
    }

    @Override
    public void EliminarExpediente(String codExpediente) {
        try {
            ps = conexion.prepareStatement("DELETE FROM S10EXP WHERE C_C_EXPEDIENTE=?;");
            ps.setString(1, codExpediente);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el registro... " + e);
        }
    }

    public int obtenerCantidadRegistros() {
        int contador = 0;
        try {
            rs = st.executeQuery("SELECT count(*) as total FROM S10EXP;");
            if (rs.next()) {
                contador = Integer.parseInt(rs.getString("total"));
            } else {
                contador = 0;
            }
            rs.close(); //cerramos la conexion para liberar espacio

        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar la cantidad de registros." + e);
        }
        return contador;
    }

}