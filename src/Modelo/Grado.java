
package Modelo;

/**
 */
public class Grado {
    
    private String codGrado;
    private String nombre;
    private String corta;
    private String descripcion;
    private int alcance;
    private String  estado;

    public Grado(){}
    
         //  public String[] EncabezadoExpediente= {"Num","Codigo","Fecha Reg","Cliente","Moneda","Monto", "Inicial"};
        public Object[] RegistroGrado(int numeracion) {
        Object[] fila = {numeracion, codGrado, nombre,corta,descripcion,alcance,estado};
        return fila;
    }
    
    public String getCodGrado() {
        return codGrado;
    }

    public void setCodGrado(String codGrado) {
        this.codGrado = codGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorta() {
        return corta;
    }

    public void setCorta(String corta) {
        this.corta = corta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  
    

}
