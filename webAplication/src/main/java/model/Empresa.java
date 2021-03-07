/**
 * En este fichero se crean los get y set de Empresa.
 */
package main.java.model;

public class Empresa {

    private int Id;
    private String Nombre;
    private int Telefono;
    private int CP;
    private String Poblacion;
    private String Provincia;
    private String Direccion;

    //GETTER y SETTER
    
    /**
     * Obtener el Id de la Empresa.
     * @return int Id
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece la ID de la Empresa.
     * @param id
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Obtener el codigo postal de la Empresa.
     * @return int CP
     */
    public int getCP() {
        return CP;
    }

    /**
     * Establece el codigo postal de la Empresa.
     * @param cP
     */
    public void setCP(int cP) {
        this.CP = cP;
    }

    /**
     * Obtener el telefono de la Empresa.
     * @return int Telefono
     */
    public int getTelefono() {
        return Telefono;
    }

    /**
     * Establece el telefono de la Empresa.
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.Telefono = telefono;
    }

    /**
     * Obtener el Nombre de la Empresa.
     * @return String Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Establece el nombre de la Empresa.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    /**
     * Obtener la poblacion de la Empresa.
     * @return String Poblacion
     */
    public String getPoblacion() {
        return Poblacion;
    }

    /**
     * Establece la poblacion de la Empresa.
     * @param poblacion
     */
    public void setPoblacion(String poblacion) {
        this.Poblacion = poblacion;
    }

    /**
     * Obtener la direccion de la Empresa.
     * @return String Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * Establece la direccion de la Empresa.
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    /**
     * Obtener la provincia de la Empresa.
     * @return String Provincia
     */
    public String getProvincia() {
        return Provincia;
    }

    /**
     * Establece la provincia de la Empresa.
     * @param provincia
     */
    public void setProvincia(String provincia) {
        this.Provincia = provincia;
    }
}
