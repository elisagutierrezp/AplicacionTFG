package main.java.model;

public class Empleado extends Trabajador {

    private String Trabajador_DNI;

    public Empleado() {
        super();
    }

    //GETTER Y SETTER
    /**
     * Obtener el Dni del empleado (trabajador).
     * @return String DNI
     */
    public String getTrabajador_DNI() {
        return Trabajador_DNI;
    }

    /**
     * Establece el nuevo Dni del empleado (trabajador).
     * @param trabajador_DNI 
     */
    public void setTrabajador_DNI(String trabajador_DNI) {
        this.Trabajador_DNI = trabajador_DNI;
    }

}
