package Dao;

import Modelo.*;
import java.util.*;
import Formatos.*;
import Interfaces.*;

public class ClienteDAO extends ConectarBD implements AccionesCliente {

    @Override
    public ArrayList<Cliente> SincronizarListaCliente() {
        ArrayList<Cliente> ListaCli = new ArrayList();

        try {
            rs = st.executeQuery("SELECT C_C_CLIENTE,D_NACIMIENTO,C_T_DNI,C_T_CIP,D_SOCIO,C_C_CLSFUN,N_I_SUELDO FROM S10CLI");

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getString(1));
                cli.setFechaNacimiento(rs.getString(2));
                cli.setNroDocumento(rs.getString(3));
                cli.setCip(rs.getString(4));
                cli.setfIngreso(rs.getString(5));
                cli.setClsfun(rs.getString(6));
                cli.setSueldo(rs.getInt(7));
                ListaCli.add(cli);
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos......" + e);
        }
        return ListaCli;
    }

    @Override
    public void InsertarCliente(Cliente Cliente) {
        try {
            ps = conexion.prepareStatement("INSERT INTO S10CLI VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, Cliente.getCodigo());
            ps.setString(2, Cliente.getNombres());
            ps.setString(3, Cliente.getFechaNacimiento());
            ps.setString(4, Cliente.getNroDocumento());
            ps.setString(5, Cliente.getDireccion());
            ps.setString(6, Cliente.getEmail());
            ps.setString(7, Cliente.getTelefono());
            ps.setString(8, Cliente.getEstadoCivil());
            ps.setString(9, Cliente.getSexo());
            ps.setString(10, Cliente.getNroCid());
            ps.setString(11, Cliente.getCip());
            ps.setString(12, Cliente.getfIngreso());
            ps.setString(13, Cliente.getClsfun());
            ps.setString(14, Cliente.getSituacion());
            ps.setInt(15, Cliente.getSueldo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR: no se puede insertar el registro...." + e);
        }
    }

    @Override
    public Cliente BuscarClientePorCodigo(String codCliente) {
        Cliente cli = null; //creamos el objeto inicializamos en vacio
        try {
            rs = st.executeQuery("SELECT * FROM S10CLI WHERE C_C_CLIENTE='" + codCliente + "'");
            if (rs.next()) {
                cli = new Cliente();
                cli.setCodigo(rs.getString(1));
                cli.setNombres(rs.getString(2));
                cli.setFechaNacimiento(rs.getString(3));
                cli.setNroDocumento(rs.getString(4));
                cli.setDireccion(rs.getString(5));
                cli.setEmail(rs.getString(6));
                cli.setTelefono(rs.getString(7));
                cli.setEstadoCivil(rs.getString(8));
                cli.setSexo(rs.getString(9));
                cli.setNroCid(rs.getString(10));
                cli.setCip(rs.getString(11));
                cli.setfIngreso(rs.getString(12));
                cli.setClsfun(rs.getString(13));
                cli.setSituacion(rs.getString(14));
                cli.setSueldo(rs.getInt(15));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede buscar el codigo .." + e);
        }
        return cli;
    }

    @Override
    public void ActualizarCliente(Cliente nuevoCliente) {
        try {
            ps = conexion.prepareStatement("UPDATE S10CLI SET C_T_CLIENTE= ?,D_NACIMIENTO = ?, "
                    + "C_T_DNI= ?,C_T_DIRECCION = ?, C_T_EMAIL= ?, C_T_TELEFONOC= ?, N_I_ESTCIV= ?, FLG_SEXO= ?"
                    + ", C_T_NUMDOCID= ?, C_T_CIP= ?, D_SOCIO= ?, C_C_CLSFUN= ?, N_I_ESTADO= ?, N_I_SUELDO= ? WHERE C_C_CLIENTE = ?;");
                   ps.setString(1, nuevoCliente.getNombres());
            ps.setString(2, nuevoCliente.getFechaNacimiento());
            ps.setString(3, nuevoCliente.getNroDocumento());
            ps.setString(4, nuevoCliente.getDireccion());
            ps.setString(5, nuevoCliente.getEmail());
            ps.setString(6, nuevoCliente.getTelefono());
            ps.setString(7, nuevoCliente.getEstadoCivil());
            ps.setString(8, nuevoCliente.getSexo());
            ps.setString(9, nuevoCliente.getNroCid());
            ps.setString(10, nuevoCliente.getCip());
            ps.setString(11, nuevoCliente.getfIngreso());
            ps.setString(12, nuevoCliente.getClsfun());
            ps.setString(13, nuevoCliente.getSituacion());
            ps.setInt(14, nuevoCliente.getSueldo());
            ps.setString(15, nuevoCliente.getCodigo());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede actualizar el registro..." + e);
        }
    }

    @Override
    public void EliminarCliente(String codCliente) {
        try {
            ps = conexion.prepareStatement("DELETE FROM S10CLI WHERE C_C_CLIENTE=?;");
            ps.setString(1, codCliente);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el registro... " + e);
        }
    }

    public int obtenerCantidadRegistros() {
        int contador = 0;
        try {

            rs = st.executeQuery("SELECT count(*) as total FROM S10CLI;");
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
