
package Modelo;

/**
 */
public class Cuota {
    private String expediente;
    private String cronograma;
    private String codCuota;
    private String fecVencimiento;
    private String fecAprobacion;
    private String ciclica;
   private double saldoIni;
   private double tea;
   private double capital;
   private double plazo;
   private double interesProgramado;
   private double seguroProgramado;
   private String TipoSeg;
   private double cuota;
   
       public Object[] RegistroCuota(int numeracion) {
        Object[] fila = {numeracion, expediente,fecVencimiento,saldoIni,capital,interesProgramado,seguroProgramado,cuota};
        return fila;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getCronograma() {
        return cronograma;
    }

    public void setCronograma(String cronograma) {
        this.cronograma = cronograma;
    }

    public String getCodCuota() {
        return codCuota;
    }

    public void setCodCuota(String codCuota) {
        this.codCuota = codCuota;
    }

    public String getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public double getSaldoIni() {
        return saldoIni;
    }

    public void setSaldoIni(double saldoIni) {
        this.saldoIni = saldoIni;
    }

    public double getTea() {
        return tea;
    }

    public void setTea(double tea) {
        this.tea = tea;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInteresProgramado() {
        return interesProgramado;
    }

    public void setInteresProgramado(double interesProgramado) {
        this.interesProgramado = interesProgramado;
    }

    public double getSeguroProgramado() {
        return seguroProgramado;
    }

    public void setSeguroProgramado(double seguroProgramado) {
        this.seguroProgramado = seguroProgramado;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public double getPlazo() {
        return plazo;
    }

    public void setPlazo(double plazo) {
        this.plazo = plazo;
    }

    public String getFecAprobacion() {
        return fecAprobacion;
    }

    public void setFecAprobacion(String fecAprobacion) {
        this.fecAprobacion = fecAprobacion;
    }

    public String getCiclica() {
        return ciclica;
    }

    public void setCiclica(String ciclica) {
        this.ciclica = ciclica;
    }

    public String getTipoSeg() {
        return TipoSeg;
    }

    public void setTipoSeg(String TipoSeg) {
        this.TipoSeg = TipoSeg;
    }
   
}
