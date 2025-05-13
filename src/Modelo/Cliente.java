package Modelo;


/**
 */
//extends Persona
public class Cliente extends Persona {

    private String nroCid;
    private String cip;
    private String fIngreso;
    private String clsfun;
    private String situacion;
    private int sueldo;

    public Cliente() {
    }
        public Object[] RegistroCliente(int numeracion) {
        Object[] fila = {numeracion, super.getCodigo(),super.getFechaNacimiento(),super.getNroDocumento(),cip,fIngreso,clsfun,sueldo};
        return fila;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getfIngreso() {
        return fIngreso;
    }

    public void setfIngreso(String fIngreso) {
        this.fIngreso = fIngreso;
    }

    public String getClsfun() {
        return clsfun;
    }

    public void setClsfun(String clsfun) {
        this.clsfun = clsfun;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getNroCid() {
        return nroCid;
    }

    public void setNroCid(String nroCid) {
        this.nroCid = nroCid;
    }


}
