
package Dao;
//libreria
import Interfaces.Parametros;
import java.sql.*;
import Formatos.Mensajes;
public class PruebaConexion implements Parametros {
    public static void main(String[] args) {
        try{
           Class.forName(driver);
           Connection con =  DriverManager.getConnection(ruta,usuario,clave);
           Mensajes.m1("Conexion a DB exitosa");
        }catch(Exception e){
             Mensajes.m1("ERROR no se puede conectar la BD... "+e);
        }          
        
    }
    
}
