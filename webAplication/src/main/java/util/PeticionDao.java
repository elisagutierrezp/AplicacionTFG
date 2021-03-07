/* Con este fichero se permite modificar, insertar, eliminar y visualizar todas las Peticions, 
 * ademas de poder buscar una de ellas a traves de su ID. 
 */

package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.model.Peticion;

public class PeticionDao {

	private Connection connection;

	public PeticionDao() {
		connection = UtilDB.getConnection();
	}

	/**
     * Inserta una nueva peticion en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Peticion 
     */
	public void addPeticion(Peticion Peticion) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into Peticion(Fecha_Inicio, Fecha_Fin, Motivo, Empleado_trabajador_DNI, rrhh_trabajador_DNI, Id_Peticion, Aceptada, Leido) values (?, ?, ?,?,?,?,?,?)");
			preparedStatement.setString(1, Peticion.getFecha_Inicio());
			preparedStatement.setString(2, Peticion.getFecha_Fin());
			preparedStatement.setString(3, Peticion.getMotivo());
			preparedStatement.setString(4, Peticion.getEmpleado_trabajador_DNI());
			preparedStatement.setString(5, Peticion.getRrhh_trabajador_DNI());
			preparedStatement.setInt(6, Peticion.getId_Peticion());
			preparedStatement.setInt(7, Peticion.isAceptada());
			preparedStatement.setBoolean(8, false);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Elimina una peticion en la base de datos
     * mediante una consulta con la ID de peticion proporcionado.
     * @param Id_Peticion
     */
	public void deletePeticion(int Id_Peticion) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Peticion where Id_Peticion=?");
			preparedStatement.setInt(1, Id_Peticion);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Obtiene el ultimo valor del ID de la peticion
     * @return Peticion peticion
     */
	public Peticion encuentraultimo() {
		Peticion peticion = new Peticion();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM Peticion ORDER by Id_Peticion DESC LIMIT 1;");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				peticion.setId_Peticion(rs.getInt("Id_Peticion"));
			}

		}

		catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return peticion;
	}

	/**
     * Obtiene las peticiones no leidas por el trabajadador.
     * @param DNI
     * @return int leidos
     */
	public int getLeidos(String DNI) {
		int leidos = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select count(Leido) from Peticion WHERE Leido=false and Aceptada!=-1 and Empleado_trabajador_DNI='" + DNI + "';");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				leidos = rs.getInt("count(Leido)");	
			}
		}catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return leidos;
	}

	/**
     * Actualiza una peticion en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Id_Peticion
     * @param Aceptada
     */
	public void updatePeticion(int Id_Peticion, int Aceptada) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update Peticion set Aceptada=" + "'"
					+ Aceptada + "'" + "where Id_Peticion=" + "'" + Id_Peticion + "';");

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Actualiza una peticion de un trabajador concreto en la base de datos
     * mediante una consulta con los datos proporcionados cambiando el valor del atributo leido.
     * @param DNI
     */
	public void updatePeticionLeido(String DNI) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Peticion set Leido=true where Empleado_trabajador_DNI='" + DNI + "';");

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}
	
	/**
     * Obtiene una List de todas las peticiones aceptadas 
     * de un trabajador de la base de datos.
     * @param DNI
     * @return lista
     */
	public  ArrayList<Peticion> ObtenerVacaciones (String DNI) {
		ArrayList<Peticion> lista = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Peticion where Empleado_trabajador_DNI =? and Aceptada= 1;");
			preparedStatement.setString(1, DNI);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Peticion peticion = new Peticion();
				peticion.set_Fecha_Inicio(rs.getString("Fecha_Inicio"));
				peticion.set_Fecha_Fin(rs.getString("Fecha_Fin"));
				peticion.setMotivo(rs.getString("Motivo"));
				lista.add(peticion);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;
		
	}
	
	/**
     * Obtiene una List de todas las peticiones de la base de datos.
     * @return lista
     */
	public List<Peticion> getAllpeticiones() {
		List<Peticion> peticiondb = new ArrayList<Peticion>();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from Peticion;");
				while (rs.next()) {

					Peticion peticion = new Peticion();
					peticion.set_Fecha_Inicio(rs.getString("Fecha_Inicio"));
					peticion.set_Fecha_Fin(rs.getString("Fecha_Fin"));
					peticion.setMotivo(rs.getString("Motivo"));
					peticion.setEmpleado_trabajador_DNI(rs.getString("Empleado_trabajador_DNI"));
					peticion.setAceptada(rs.getInt("Aceptada"));
					peticion.setId_Peticion(rs.getInt("Id_Peticion"));
					peticion.setLeido(rs.getBoolean("Leido"));
					peticiondb.add(peticion);
				}
			} catch (SQLException e) {
				Log.logdb.error("SQL Exception: " + e);
			}
			return peticiondb;
		} else {
			Log.logdb.error("No hay conexion con la bbdd");
			return null;
		}

	}

	/**
     * Obtiene una List de todas las peticiones de un trabajador de la base de datos.
     * @param DNI
     * @return lista
     */
	public List<Peticion> getAllpeticionesByDNI(String DNI) {
		List<Peticion> peticiondb = new ArrayList<Peticion>();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from Peticion where Empleado_trabajador_DNI='" + DNI + "';");
				while (rs.next()) {

					Peticion peticion = new Peticion();
					peticion.set_Fecha_Inicio(rs.getString("Fecha_Inicio"));
					peticion.set_Fecha_Fin(rs.getString("Fecha_Fin"));
					peticion.setMotivo(rs.getString("Motivo"));
					peticion.setEmpleado_trabajador_DNI(rs.getString("Empleado_trabajador_DNI"));
					peticion.setAceptada(rs.getInt("Aceptada"));
					peticion.setId_Peticion(rs.getInt("Id_Peticion"));
					peticion.setLeido(rs.getBoolean("Leido"));
					peticiondb.add(peticion);
				}
			} catch (SQLException e) {
				Log.logdb.error("SQL Exception: " + e);
			}
			return peticiondb;
		} else {
			Log.logdb.error("No hay conexion con la bbdd");
			return null;
		}

	}

	/**
     * Obtiene una Peticion mediante su id de la base de datos .
     * @param Id_Peticion
     * @return Peticion peticion
     */
	public Peticion getpeticionById(int Id_Peticion) {
		Peticion peticion = new Peticion();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Peticion where Id_Peticion=?");
			preparedStatement.setInt(1, Id_Peticion);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				peticion.setId_Peticion(rs.getInt("Id_Peticion"));
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return peticion;
	}
}
