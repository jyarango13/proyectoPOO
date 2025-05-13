package Modelo;
/**
 */
public abstract class Expediente {

    private String codigoExpediente;
    private String fechaRegistro;
    private String cliente;
    private String usuario;
    private String descripcion;
    private String tipoSeg;
    private int plazo;
    private String ciclica;
    private String moneda;
    private int monto;
    
//Sin ingresar
    private int contador;
    
        public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    //generar codigo

        ExpedienteDetalle expd;

    //Constructores
    public Expediente() {
    }

    //getters and setters

    public String getCodigoExpediente() {
        return codigoExpediente;
    }

    public void setCodigoExpediente(String codigoExpediente) {
        this.codigoExpediente = codigoExpediente;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoSeg() {
        return tipoSeg;
    }

    public void setTipoSeg(String tipoSeg) {
        this.tipoSeg = tipoSeg;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getCiclica() {
        return ciclica;
    }

    public void setCiclica(String ciclica) {
        this.ciclica = ciclica;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

}
