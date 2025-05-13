
package Lista;
import Modelo.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ImpresionesTExp;

/**
 */
public class ListaExpediente implements ImpresionesTExp{
    public ArrayList<ExpedienteDetalle> ListaExp;
    //constructor
    public ListaExpediente(){
        ListaExp = new ArrayList();
    }
    
        //Metodos que manipulan los objetos de la coleccion
    public void AgregarExpediente(ExpedienteDetalle exp){ ListaExp.add(exp); } //metodo que agrega un objeto a la coleccion (add)
    public Expediente RecuperarExpediente(int posicion){ return ListaExp.get(posicion); } //metodo que retorna un Expediente en una posicion (get)
    public void EliminarExpediente(int posicion){ ListaExp.remove(posicion); } //metodo que elimina un objeto de la coleccion (remove)
    //metodo que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion,ExpedienteDetalle expActual){ ListaExp.set(posicion,expActual); }
    //metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de Expediente
    public int BuscarExpediente(String codbuscado){
        for(int i=0; i<ListaExp.size();i++){ //size(): lleva la cantidad de objetos en la coleccion
            if(codbuscado.equals(ListaExp.get(i).getCodigoExpediente()))
                return i; //si existe retorna la posicion
        }
        return -1; // retornará -1 si no existe el codigo en la coleccion
    } 
    @Override
    public void MostrarEnTablaExp(JTable tabla) {
      DefaultTableModel mt =  new DefaultTableModel(null,EncabezadoExpediente);
      tabla.setModel(mt);
      for(int i=0;i<ListaExp.size();i++){
          mt.addRow(ListaExp.get(i).RegistroExpediente(i+1)); // por que i+1, por que i inicia en 0
      }
    }    


    
}
