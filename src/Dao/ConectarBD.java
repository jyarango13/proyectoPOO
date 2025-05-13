package Dao;
//libreria
import Interfaces.Parametros;
import java.sql.*;
import Formatos.*;
public class ConectarBD implements Parametros{
    public Connection conexion;
    public Statement st;
    public ResultSet rs;
    public PreparedStatement ps;
    public ConectarBD(){
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(ruta,usuario,clave);
            
            st = conexion.createStatement();
        }catch(Exception e){
            Mensajes.m1("ERROR no se puede conectar a la BD....."+e);
        }
    }    
}//fin de la clase
