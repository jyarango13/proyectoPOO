
package Interfaces;

import java.util.ArrayList;
import Modelo.Grado;

/**
 */
public interface AccionesGrado {
   public void InsertarGrado(Grado grado);
    public Grado BuscarGradoPorCodigo(String codGrado);
    public void ActualizarGrado(Grado nuevoGrado);
    public void EliminarGrado(String codGrado);
    public ArrayList<Grado> SincronizarListaGrado();    
    public int obtenerCantidadRegistrosG();
}
