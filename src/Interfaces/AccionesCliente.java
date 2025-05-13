
package Interfaces;


import Modelo.Cliente;
import java.util.ArrayList;

/**
 */
public interface AccionesCliente {
    public void InsertarCliente(Cliente Cliente);
    public Cliente BuscarClientePorCodigo(String codCliente);
    public void ActualizarCliente(Cliente nuevoCliente);
    public void EliminarCliente(String codCliente);
    public ArrayList<Cliente> SincronizarListaCliente();    
}
