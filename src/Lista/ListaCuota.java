package Lista;

import Modelo.Cuota;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 */
public class ListaCuota {

    public ArrayList<Cuota> ListaCuo;

    //constructor
    public ListaCuota() {
        ListaCuo = new ArrayList();
    }

    //Metodos que manipulan los objetos de la coleccion
    public void AgregarCuota(Cuota cuo) {
        ListaCuo.add(cuo);
    } //metodo que agrega un objeto a la coleccion (add)

    public String[] EncabezadoCuota = {"Num", "Codigo", "Expediente", "Fecha Venc", "Saldo Inicial", "Capital", "Interes","Seguro","Cuota"};

    public void MostrarEnTablaCuo(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, EncabezadoCuota);
        tabla.setModel(mt);
        for (int i = 0; i < ListaCuo.size(); i++) {
            mt.addRow(ListaCuo.get(i).RegistroCuota(i + 1)); // por que i+1, por que i inicia en 0
        }
    }
                

}
