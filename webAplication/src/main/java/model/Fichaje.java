package main.java.model;

public class Fichaje {

    private String Hora_Inicio;
    private String Hora_Fin;
    private String Fecha;
    private String Empleado_trabajador_DNI;

    //GETTER Y SETTER
    /**
     * Obtener la hora de inicio del fichaje.
     * @return String Hora_Inicio
     */
    public String getHora_Inicio() {
        return Hora_Inicio;
    }

    /**
     * Establece la hora inicio de fichar.
     * @param hora_inicio
     */
    public void setHora_Inicio(String hora_inicio) {
        this.Hora_Inicio = hora_inicio;
    }

    /**
     * Obtener la hora final del fichaje.
     * @return String Hora_Fin
     */
    public String getHora_Fin() {
        return Hora_Fin;
    }

    /**
     * Establece la hora fin de fichar.
     * @param hora_fin
     */
    public void setHora_Fin(String hora_fin) {
        this.Hora_Fin = hora_fin;
    }

    /**
     * Obtener la fecha del fichaje.
     * @return String Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * Establece la fecha de fichar.
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    /**
     * Obtener el Dni del empleado del fichaje.
     * @return String Empleado_trabajador_DNI
     */ 
    public String getEmpleado_trabajador_DNI() {
        return Empleado_trabajador_DNI;
    }

    /**
     * Establece el dni del empleado a fichar.
     * @param empleado_trabajador_DNI
     */
    public void setEmpleado_trabajador_DNI(String empleado_trabajador_DNI) {
        this.Empleado_trabajador_DNI = empleado_trabajador_DNI;
    }

}
