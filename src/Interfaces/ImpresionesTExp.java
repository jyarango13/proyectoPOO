
package Interfaces;
//libreria
import javax.swing.JTable;

public interface ImpresionesTExp {
    public String[] EncabezadoExpediente= {"Num","Codigo","Fecha Reg","Cliente","Moneda","Monto", "Cuota","Fecha Aprobación","Estado"};
    public void MostrarEnTablaExp(JTable tabla);
}
