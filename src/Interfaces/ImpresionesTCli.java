
package Interfaces;
//libreria
import javax.swing.JTable;

public interface ImpresionesTCli {
         //   Object[] fila = {numeracion, super.getCodigo(),super.getFechaNacimiento(),super.getNroDocumento(),cip,fIngreso,clsfun,sueldo};

    public String[] EncabezadoCliente= {"Num","Codigo","Fecha Nac","DNI","CIP","Fecha Socio", "Grado","Sueldo"};
    public void MostrarEnTablaCli(JTable tabla);

}
