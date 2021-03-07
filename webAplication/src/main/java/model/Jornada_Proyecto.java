package main.java.model;

public class Jornada_Proyecto {

    private String Fecha;
    private int Horas;
    private String Empleado_trabajador_DNI;
    private int proyecto_Clave;

    //GETTER Y SETTER
    /**
     * Obtener la fecha de la jornada.
     * @return String Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * Establece la fecha de la jornada.
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    /**
     * Obtiene las horas de la jornada.
     * @return int Horas
     */
    public int getHoras() {
        return Horas;
    }

    /**
     * Establece las horas de la jornada
     * @param horas
     */
    public void setHoras(int horas) {
        this.Horas = horas;
    }

    /**
     * Obtener el Dni de la jornada del empleado
     * @return String Empleado_trabajador_DNI
     */
    public String getEmpleado_trabajador_DNI() {
        return Empleado_trabajador_DNI;
    }

    /**
     * Establece el nuevo Dni de la jornada del empleado
     * @param empleado_trabajador_DNI
     */
    public void setEmpleado_trabajador_DNI(String empleado_trabajador_DNI) {
        this.Empleado_trabajador_DNI = empleado_trabajador_DNI;
    }

    /**
     * Obtener la clave del proyecto
     * @return int proyecto_Clave
     */
    public int getProyecto_Clave() {
        return proyecto_Clave;
    }

    /**
     * Establece la clave del proyecto
     * @param proyecto_clave
     */
    public void setProyecto_Clave(int proyecto_clave) {
        this.proyecto_Clave = proyecto_clave;
    }

}
