package main.java.model;

public class RRHH extends Trabajador {

    private String Trabajador_DNI;

    public RRHH() {
        super();
    }

    //GETTER Y SETTER
    /**
     * Obtener el Dni de RRHH (trabajador).
     * @return String Trabajador_DNI
     */
    public String getTrabajador_DNI() {
        return Trabajador_DNI;
    }

    /**
     * Establece el nuevo Dni de RRHH (trabajador).
     * @param trabajador_DNI
     */
    public void setTrabajador_DNI(String trabajador_DNI) {
        this.Trabajador_DNI = trabajador_DNI;
    }

}
