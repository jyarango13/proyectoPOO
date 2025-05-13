
package Interfaces;

import java.util.ArrayList;
import Modelo.*;

/**
 */
public interface AccionesExpediente {
   public void InsertarExpediente(ExpedienteDetalle Expediente);
    public ExpedienteDetalle BuscarExpedientePorCodigo(String codExpediente);
    public void ActualizarExpediente(ExpedienteDetalle nuevoExpediente);
    public void EliminarExpediente(String codExpediente);
    public ArrayList<ExpedienteDetalle> SincronizarListaExpediente();    
}
