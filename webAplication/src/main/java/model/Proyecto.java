/**
 * En este fichero se crean los get y set de Proyecto.
 */

package main.java.model;

public class Proyecto {
	
    private String Descripcion;
    private int Id_Empresa;
    private int Clave;

    /**
     * Obtiene el ID de la empresa a la 
     * que pertenece el proyecto.
     * @return int Id_Empresa
     */
	public int getId_Empresa() {
		return Id_Empresa;
	}

	/**
     * Establece el ID de la empresa.
     * @param id_Empresa
     */
	public void setId_Empresa(int id_Empresa) {
		this.Id_Empresa = id_Empresa;
	}

	/**
     * Obtiene la clave de proyecto.
     * @return int Clave
     */
	public int getClave() {
		return Clave;
	}

	/**
     * Establece la clave del proyecto.
     * @param clave
     */
	public void setClave(int clave) {
		this.Clave = clave;
	}

	/**
     * Obtiene la descripcion del proyecto.
     * @return String Descripcion
     */
	public String getDescripcion() {
		return Descripcion;
	}

	/**
     * Establece la descripcion del proyecto
     * @param descripcion
     */
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	
}
