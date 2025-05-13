package Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 */
public abstract class AbsDatos {

    Calendar fecha = new GregorianCalendar();
    String anio = Integer.toString(fecha.get(Calendar.YEAR));
    String mes = Integer.toString(fecha.get(Calendar.MONTH)+1);
    String dia = Integer.toString(fecha.get(Calendar.DATE));
    String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
    String minuto = Integer.toString(fecha.get(Calendar.MINUTE));

    public String fechaPC = anio + "/" + mes + "/" + dia;
    public String horaPC = hora + ":" + minuto;
    public String sexo1 = "MASCULINO";
    public String sexo2 = "FEMENINO";
    public String estado1 = "ACTIVO";
    public String estado2 = "INACTIVO";
    public String estcivil1 = "SOLTERO";
    public String estcivil2 = "CASADO";
    public String estcivil3 = "VIUDO";
    public String estcivil4 = "DIVORCIADO";

    public String area1 = "CONTABILIDAD";
    public String area2 = "ADMINISTRACIÓN";
    public String area3 = "SISTEMAS";
    public String area4 = "ATENCIÓN AL CLIENTE";
    public String tipoDocumento1 = "DNI";
    public String tipoDocumento2 = "CARNET DE EXTRANJERIA";
    public String moneda1 = "SOLES";
    public String moneda2 = "DOLARES";
    public String situacion1 = "EN VERIFICACIÓN";
    public String situacion2 = "APROBADO";
    public String situacion3 = "RECHAZADO";
    public String tipoSeguro1 = "OBLIGATORIO";
    public String tipoSeguro2 = "EXONERADO";
    public String ciclica1 = "5";
    public String ciclica2 = "30";
    
    public int tea=4;
    
    public String estadoExp1="APROBADO";
    public String estadoExp2="RECHAZADO";
    public String estadoExp3="PENDIENTE";

}
