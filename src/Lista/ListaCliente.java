
package Lista;

import Interfaces.ImpresionesTCli;
import Modelo.Cliente;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 */
public class ListaCliente implements ImpresionesTCli{
    public ArrayList<Cliente> ListaCli;
    //constructor
    public ListaCliente(){
        ListaCli = new ArrayList();
    }
    
        //Metodos que manipulan los objetos de la coleccion
    public void AgregarExpediente(Cliente cli){ ListaCli.add(cli); } //metodo que agrega un objeto a la coleccion (add)
    public Cliente RecuperarExpediente(int posicion){ return ListaCli.get(posicion); } //metodo que retorna un Expediente en una posicion (get)
    public void EliminarExpediente(int posicion){ ListaCli.remove(posicion); } //metodo que elimina un objeto de la coleccion (remove)
    //metodo que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion,Cliente cliActual){ ListaCli.set(posicion,cliActual); }
    //metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de Expediente
    public int BuscarExpediente(String codbuscado){
        for(int i=0; i<ListaCli.size();i++){ //size(): lleva la cantidad de objetos en la coleccion
            if(codbuscado.equals(ListaCli.get(i).getCodigo()))
                return i; //si existe retorna la posicion
        }
        return -1; // retornará -1 si no existe el codigo en la coleccion
    } 
    @Override
         //   Object[] fila = {numeracion, super.getCodigo(),super.getFechaNacimiento(),super.getNroDocumento(),cip,fIngreso,clsfun,sueldo};

    public void MostrarEnTablaCli(JTable tabla) {
      DefaultTableModel mt =  new DefaultTableModel(null,EncabezadoCliente);
      tabla.setModel(mt);
      for(int i=0;i<ListaCli.size();i++){
          mt.addRow(ListaCli.get(i).RegistroCliente(i+1)); // por que i+1, por que i inicia en 0
      }
    }    

}
