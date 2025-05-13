package Dao;

import Interfaces.AccionesGrado;
import Modelo.*;
import java.util.*;
import Formatos.*;
import Interfaces.*;

/**
 */
public class GradoDAO extends ConectarBD implements AccionesGrado {

    @Override
    public ArrayList<Grado> SincronizarListaGrado() {
        ArrayList<Grado> ListaGra = new ArrayList();
        try {
            //ejecutamos la consulta
            rs = st.executeQuery("SELECT C_C_CLSFUN,C_T_NOMBRE,C_T_CORTA,C_T_DESCRIPCION,N_N_ALCANCE,N_I_ESTADO FROM S06CLSFUN;");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                Grado gra = new Grado(); //creamos el objeto vacio
                gra.setCodGrado(rs.getString(1)); //recuperamos el codigo(columna 1) de la consulta y lo enviamos al objeto docente
                gra.setNombre(rs.getString(2));
                gra.setCorta(rs.getString(3));
                gra.setDescripcion(rs.getString(4));
                gra.setAlcance(rs.getInt(5));
                gra.setEstado(rs.getString(6));
                ListaGra.add(gra);
            }
            rs.close(); //cerramos la conexion para liberar espacio
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return ListaGra;
    }

    @Override
    public void InsertarGrado(Grado grado) {
        try {
            //definimos la sql con interrogantes(parametros) para que sean actualizado desde el metodo
            ps = conexion.prepareStatement("INSERT INTO S06CLSFUN VALUES(?,?,?,?,?,?);");
            //actualizar los parametros de la consulta
            ps.setString(1, grado.getCodGrado());
            ps.setString(2, grado.getNombre());
            ps.setString(3, grado.getCorta());
            ps.setString(4, grado.getDescripcion());
            ps.setInt(5, grado.getAlcance());
            ps.setString(6, grado.getEstado());
            ps.executeUpdate(); // actualiza y ejecuta la consulta SQL
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR: no se puede insertar el registro ..." + e);
        }
    }

    @Override
    public Grado BuscarGradoPorCodigo(String codGrado) {
        Grado gra = null; //creamos el objeto inicializamos en vacio
        try {
            rs = st.executeQuery(
                    "SELECT * FROM S06CLSFUN WHERE C_C_CLSFUN='" + codGrado + "';");
            if (rs.next()) {
                gra = new Grado();
                gra.setCodGrado(rs.getString(1)); //11
                gra.setNombre(rs.getString(2));
                gra.setCorta(rs.getString(3));
                gra.setDescripcion(rs.getString(4));
                gra.setAlcance(rs.getInt(5));
                gra.setEstado(rs.getString(6));

            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede buscar el codigo .." + e);
        }
        return gra;
    }
  

    @Override
    public void ActualizarGrado(Grado nuevoGrado) {
        try {
            //
            ps = conexion.prepareStatement("UPDATE S06CLSFUN SET C_T_NOMBRE= ?, C_T_CORTA= ?,C_T_DESCRIPCION= ?,N_N_ALCANCE= ?,N_I_ESTADO= ? WHERE C_C_CLSFUN = ?;");
            ps.setString(1, nuevoGrado.getNombre());
            ps.setString(2, nuevoGrado.getCorta());
            ps.setString(3, nuevoGrado.getDescripcion());
            ps.setInt(4, nuevoGrado.getAlcance());
            ps.setString(5, nuevoGrado.getEstado());
            ps.setString(6, nuevoGrado.getCodGrado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede actualizar el registro..." + e);
        }
    }

    @Override
    public void EliminarGrado(String codGrado) {
           try {
            ps = conexion.prepareStatement("DELETE FROM S06CLSFUN WHERE C_C_CLSFUN=?;");
            ps.setString(1, codGrado);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el registro... " + e);
        }
    }

    @Override
    public int obtenerCantidadRegistrosG() {
        int contador = 0;
        try {

            rs = st.executeQuery("SELECT count(*) as total FROM S06CLSFUN;");
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
