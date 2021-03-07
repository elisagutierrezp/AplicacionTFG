
package main.java.model;

public class Trabajador{
  private String DNI;
  private String Nombre;
  private String Apellido;
  private int Telefono;
  private String Contrasena;
  private int Id_Empresa;
  private String Puesto;
  
  /**
   * Obtener el Dni del trabajador
   * @return String DNI
   */
  public String getDNI() {
        return DNI;
    }

  /**
	* Establece el nuevo Dni del trabajador.
	* @param DNI 
	*/  
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * Obtener el nombre del trabajador
     * @return String Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Establece el nombre del trabajador
     * @param Nombre
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * Obtener el apellido del trabajador
     * @return String Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * Establece el apellido del trabajador
     * @param Apellido
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * Obtener el telefono del trabajador
     * @return int Telefono
     */
    public int getTelefono() {
        return Telefono;
    }

    /**
     * Establece el telefono del trabajador
     * @param Telefono
     */
    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * Obtener la contrase�a del trabajador
     * @return String Contrasena
     */
    public String getContrasena() {
        return Contrasena;
    }

    /**
     * Establece la contrase�a del trabajador
     * @param Contrasena
     */
    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    /**
     * Obtener la ID de la empresa que pertenece el trabajador
     * @return int Id_Empresa
     */
    public int getId_Empresa() {
        return Id_Empresa;
    }

    /**
     * Establece la ID de la empresa que pertenece el trabajador
     * @param Id_Empresa
     */
    public void setId_Empresa(int Id_Empresa) {
        this.Id_Empresa = Id_Empresa;
    }

    /**
     * Obtener el puesto del trabajador
     * @return String Puesto
     */
    public String getPuesto() {
        return Puesto;
    }

    /**
     * Establece el puesto del trabajador
     * @param Puesto
     */
    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }
  
  
}
