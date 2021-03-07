/**
 * Con este fichero se permite modificar, insertar, eliminar y visualizar todas las empleados, 
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
import main.java.model.Empleado;

public class EmpleadoDao {

	private Connection connection;

	public EmpleadoDao() {
		connection = UtilDB.getConnection();
	}

	/**
     * Inserta un nuevo empleado en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param empleado 
     */
	public void addempleado(Empleado empleado) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Empleado(Trabajador_DNI) values (?)");

			preparedStatement.setString(1, empleado.getTrabajador_DNI());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Elimina un empleado en la base de datos
     * mediante una consulta con el DNI del empleado proporcionado.
     * Al eliminar un empleado se han de borrar todas sus dependecias por lo
     * que ademas se elimina Jornada, Fichaje, Peticion y Trabajador 
     * que corresponda con el DNI.
     * @param Trabajador_DNI
     */
	public void deleteempleado(String Trabajador_DNI) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("DELETE from jornada_proyecto where Empleado_trabajador_DNI=?");
			preparedStatement.setString(1, Trabajador_DNI);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE from Fichaje where Empleado_trabajador_DNI=?");
			preparedStatement1.setString(1, Trabajador_DNI);
            preparedStatement1.executeUpdate();
            
            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE from Peticion  where Empleado_trabajador_DNI=?");
			preparedStatement2.setString(1, Trabajador_DNI);
            preparedStatement2.executeUpdate();
			
            PreparedStatement preparedStatement3 = connection.prepareStatement("DELETE FROM Empleado where Trabajador_DNI=?");
			preparedStatement3.setString(1, Trabajador_DNI);
            preparedStatement3.executeUpdate();
            
			PreparedStatement preparedStatement4 = connection.prepareStatement("DELETE FROM Trabajador where DNI=?");
			preparedStatement4.setString(1, Trabajador_DNI);
            preparedStatement4.executeUpdate();	

		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Actualiza el empleado en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param empleado
     */
	public void updateempleado(Empleado empleado) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE Empleado set Trabajador_DNI=?" + "where Trabajador_DNI=?");
			preparedStatement.setString(1, empleado.getTrabajador_DNI());
			preparedStatement.setString(2, empleado.getTrabajador_DNI());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
	}

	/**
     * Obtiene una List de todos los Empleados de la base de datos
     * mediante una consulta.
     * @return lista
     */
	public List<Empleado> getAllempleados() {
		List<Empleado> empleadodb = new ArrayList<Empleado>();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from Empleado;");
				while (rs.next()) {

					Empleado empleado = new Empleado();
					empleado.setTrabajador_DNI(rs.getString("Trabajador_DNI"));
					empleadodb.add(empleado);
				}

			} catch (SQLException e) {
				Log.logdb.error("SQL Exception: " + e);
			}
			return empleadodb;
		} else {
			Log.logdb.error("No hay conexion con la bbdd");
			return null;
		}

	}

	/**
     * Obtiene un Empleado de la base de datos
     * mediante una consulta con el DNI del empleado buscado.
     * @param  Trabajador_DNI
     * @return Empleado
     */
	public Empleado getempleadoByDNI(String Trabajador_DNI) {
		Empleado empleado = new Empleado();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Empleado where Trabajador_DNI=?");
			preparedStatement.setString(1, Trabajador_DNI);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				empleado.setTrabajador_DNI(rs.getString("Trabajador_DNI"));

			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return empleado;
	}
}
