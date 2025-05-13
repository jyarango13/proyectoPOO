package Modelo;

/**
 */
public class Poliza {
    private String codigoPoliza;
    private String empresa;
    private String tipoSeguro;
    private String moneda;
    private String concepto;
    private int edadMaximaIng;
    private int edadMinimaPer;
    private int edadMaximaPer;
    private double cobertura;
    private double factor;

    public Poliza(String codigoPoliza, String empresa, String tipoSeguro, String moneda, String concepto, int edadMaximaIng, int edadMinimaPer, int edadMaximaPer, double cobertura, double factor) {
        this.codigoPoliza = codigoPoliza;
        this.empresa = empresa;
        this.tipoSeguro = tipoSeguro;
        this.moneda = moneda;
        this.concepto = concepto;
        this.edadMaximaIng = edadMaximaIng;
        this.edadMinimaPer = edadMinimaPer;
        this.edadMaximaPer = edadMaximaPer;
        this.cobertura = cobertura;
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getCodigoPoliza() {
        return codigoPoliza;
    }

    public void setCodigoPoliza(String codigoPoliza) {
        this.codigoPoliza = codigoPoliza;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getEdadMaximaIng() {
        return edadMaximaIng;
    }

    public void setEdadMaximaIng(int edadMaximaIng) {
        this.edadMaximaIng = edadMaximaIng;
    }

    public int getEdadMinimaPer() {
        return edadMinimaPer;
    }

    public void setEdadMinimaPer(int edadMinimaPer) {
        this.edadMinimaPer = edadMinimaPer;
    }

    public int getEdadMaximaPer() {
        return edadMaximaPer;
    }

    public void setEdadMaximaPer(int edadMaximaPer) {
        this.edadMaximaPer = edadMaximaPer;
    }

    public double getCobertura() {
        return cobertura;
    }

    public void setCobertura(double cobertura) {
        this.cobertura = cobertura;
    }
    

    

}
