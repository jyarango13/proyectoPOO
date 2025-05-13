
package Lista;
import Modelo.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ImpresionesTGra;

/**
 */
public class ListaGrado implements ImpresionesTGra{
    public ArrayList<Grado> ListaGra;
    //constructor
    public ListaGrado(){
        ListaGra = new ArrayList();
    }
    
        //Metodos que manipulan los objetos de la coleccion
    public void AgregarExpediente(Grado gra){ ListaGra.add(gra); } //metodo que agrega un objeto a la coleccion (add)
    public Grado RecuperarExpediente(int posicion){ return ListaGra.get(posicion); } //metodo que retorna un Expediente en una posicion (get)
    public void EliminarExpediente(int posicion){ ListaGra.remove(posicion); } //metodo que elimina un objeto de la coleccion (remove)
    //metodo que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion,Grado graActual){ ListaGra.set(posicion,graActual); }
    //metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de Expediente
    public int BuscarExpediente(String codbuscado){
        for(int i=0; i<ListaGra.size();i++){ //size(): lleva la cantidad de objetos en la coleccion
            if(codbuscado.equals(ListaGra.get(i).getCodGrado()))
                return i; //si existe retorna la posicion
        }
        return -1; // retornará -1 si no existe el codigo en la coleccion
    } 
    @Override
    public void MostrarEnTablaGra(JTable tabla) {
      DefaultTableModel mt =  new DefaultTableModel(null,EncabezadoGrado);
      tabla.setModel(mt);
      for(int i=0;i<ListaGra.size();i++){
          mt.addRow(ListaGra.get(i).RegistroGrado(i+1)); // por que i+1, por que i inicia en 0
      }
    }    
    
}
