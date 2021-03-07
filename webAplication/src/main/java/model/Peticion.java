package main.java.model;

public class Peticion {

    private String Fecha_Inicio;
    private String Fecha_Fin;
    private String Motivo;
    private String Empleado_trabajador_DNI;
    private String rrhh_trabajador_DNI;
    private int Id_Peticion;
    private int Aceptada;
    private boolean Leido;

    //GETTER Y SETTER
    /**
     * Obtener la fecha de inicio de la peticion
     * @return String Fecha_Inicio
     */
    public String getFecha_Inicio() {
        return Fecha_Inicio;
    }

    /**
     * Establece la fecha de inicio de la peticion
     * @param f_inicio
     */
    public void set_Fecha_Inicio(String f_inicio) {
        this.Fecha_Inicio = f_inicio;
    }

    /**
     * Obtener la fecha de fin de la peticion
     * @return Fecha_Fin
     */
    public String getFecha_Fin() {
        return Fecha_Fin;
    }

    /**
     * Establece la fecha de fin de la peticion
     * @param f_fin
     */
    public void set_Fecha_Fin(String f_fin) {
        this.Fecha_Fin = f_fin;
    }

    /**
     * Obtener el motivo de la peticion
     * @return String Motivo
     */
    public String getMotivo() {
        return Motivo;
    }

    /**
     * Establece el motivo de la peticion
     * @param motivo
     */
    public void setMotivo(String motivo) {
        this.Motivo = motivo;
    }

    /**
     * Obtiene el dni del empleado que ha 
     * realizado la peticion.
     * @return String Empleado_trabajador_DNI
     */
    public String getEmpleado_trabajador_DNI() {
        return Empleado_trabajador_DNI;
    }

    /**
     * Establece el dni del empleado que ha 
     * realizado la peticion.
     * @param empleado_trabajador_DNI
     */
    public void setEmpleado_trabajador_DNI(String empleado_trabajador_DNI) {
        this.Empleado_trabajador_DNI = empleado_trabajador_DNI;
    }

    /**
     * Obtiene el dni de RRHH que ha 
     * gestionado la peticion.
     * @return String rrhh_trabajador_DNI
     * 
     */
    public String getRrhh_trabajador_DNI() {
        return rrhh_trabajador_DNI;
    }

    /**
     * Establece el dni de RRHH que ha 
     * gestionado la peticion.
     * @param rrhh_trabajador_DNI
     */
    public void setRrhh_trabajador_DNI(String rrhh_trabajador_DNI) {
        this.rrhh_trabajador_DNI = rrhh_trabajador_DNI;
    }

    /**
     * Obtiene la ID de la peticion.
     * @return int Id_Peticion
     */
    public int getId_Peticion() {
        return Id_Peticion;
    }

    /**
     * Establece la ID de la peticion.
     * @param id_peticion
     */
    public void setId_Peticion(int id_peticion) {
        this.Id_Peticion = id_peticion;
    }

    /**
     * Obtiene el estado de la peticion.
     * @return int Aceptada
     */
    public int isAceptada() {
        return Aceptada;
    }

    /**
     * Establece el estado de la peticion.
     * @param aceptada
     */
    public void setAceptada(int aceptada) {
        this.Aceptada = aceptada;
    }

    /**
     * Obtiene el estado de leido de la peticion.
     * @return boolean Leido
     */
	public boolean isLeido() {
		return Leido;
	}

	/**
     * Establece el estado de leido de la peticion.
     * @param leido
     */
	public void setLeido(boolean leido) {
		Leido = leido;
	}

}
