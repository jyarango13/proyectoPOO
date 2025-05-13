package Modelo;

/**
 */
public class ExpedienteDetalle extends Expediente {

    private double cuota;
    private String estado;
    private int tea;
    private String fAprobado;

    public ExpedienteDetalle() {

    }


        //  public String[] EncabezadoExpediente= {"Num","Codigo","Fecha Reg","Cliente","Moneda","Monto", "Inicial"};
   
        public Object[] RegistroExpediente(int numeracion) {
        Object[] fila = {numeracion, super.getCodigoExpediente(), super.getFechaRegistro(),super.getCliente(),super.getMoneda(),super.getMonto(),this.getCuota(),this.fAprobado,this.getEstado()};
        return fila;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTea() {
        return tea;
    }

    public void setTea(int tea) {
        this.tea = tea;
    }

    public String getfAprobado() {
        return fAprobado;
    }

    public void setfAprobado(String fAprobado) {
        this.fAprobado = fAprobado;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

}
