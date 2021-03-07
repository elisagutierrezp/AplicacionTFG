/**
 * Con este fichero se permite modificar, insertar, eliminar y visualizar todas las fichas,  
 */

package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.model.Fichaje;

public class FichajeDao {

    private Connection connection;

    public FichajeDao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Inserta un nuevo fichaje en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Fichaje
     */
    public void addFichaje(Fichaje Fichaje) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Fichaje(Hora_Inicio, Fecha ,Empleado_trabajador_DNI) values (?, ?, ?)");
            
            preparedStatement.setString(1, Fichaje.getHora_Inicio().toString());         
            preparedStatement.setString(2, Fichaje.getFecha().toString());
            preparedStatement.setString(3, Fichaje.getEmpleado_trabajador_DNI());
         
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Elimina un fichaje en la base de datos
     * mediante una consulta con el DNI del trabajador proporcionado.
     * @param Empleado_trabajador_DNI
     */
    public void deleteFichaje(String Empleado_trabajador_DNI) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Fichaje where Empleado_trabajador_DNI=?");
            preparedStatement.setString(1, Empleado_trabajador_DNI);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Actualiza un fichaje en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Fichaje
     */
    public void updateFichaje(Fichaje Fichaje) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Fichaje set Hora_Inicio=?, Hora_Fin=?, Fecha=?, Empleado_trabajador_DNI=?" + "where Empleado_trabajador_DNI=?");
                    
            preparedStatement.setString(1, Fichaje.getHora_Inicio().toString());
            preparedStatement.setString(2, Fichaje.getHora_Fin().toString());            
            preparedStatement.setString(3, Fichaje.getFecha().toString());
            preparedStatement.setString(4, Fichaje.getEmpleado_trabajador_DNI());
      
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }
    
    /**
     * Actualiza la hora de fin de un fichaje con en la base de datos
     * mediante una consulta con el DNI y la nueva hora.
     * @param dni
     * @param horaf
     */
    public void anadirFinFIchaje(String dni, String horaf) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Fichaje set Hora_Fin= '" + horaf +"' where Empleado_trabajador_DNI=?");

            preparedStatement.setString(1, dni);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }
    
    /**
     * Comprueba si un trabajador ha fichado.
     * @param DNI
     * @return boolean
     */
    public boolean haFichado(String DNI) {
        try {
        	Statement statement = connection.createStatement();
        	 ResultSet rs = statement.executeQuery("SELECT Fecha FROM Fichaje where Fecha = CURDATE() and empleado_trabajador_DNI='" + DNI+"';");
        	 if (rs.next()) {
	        	 if (rs.getString("Fecha")!=null) {
	        		 return true;
	        	 }
        	 }

        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
		return false;
    }
    
    /**
     * Obtiene una List de todos los Fichajes de la base de datos.
     * @return Fichajedb
     */
    public List<Fichaje> getAllFichajes() {
        List<Fichaje> Fichajedb = new ArrayList<Fichaje>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from Fichaje;");
                while (rs.next()) {
                	Fichaje Fichaje = new Fichaje();
                
                	Fichaje.setHora_Inicio(rs.getString("Hora_Inicio"));
                	Fichaje.setHora_Fin(rs.getString("Hora_Fin"));
                	Fichaje.setFecha(rs.getString("Fecha"));
                	Fichaje.setEmpleado_trabajador_DNI(rs.getString("Empleado_trabajador_DNI"));
                	

                    Fichajedb.add(Fichaje);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return Fichajedb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }

    /**
     * Obtiene los fichajes de un trabajador de la base de datos
     * mediante una consulta con el DNI del trabajador buscado.
     * @param  FichajeDNI
     * @return Fichaje
     */
    public Fichaje getFichajeByDNI(String FichajeDNI) {
        Fichaje Fichaje = new Fichaje();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Fichaje where Empleado_trabajador_DNI=?");
            preparedStatement.setString(1, FichajeDNI);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	Fichaje.setHora_Inicio(rs.getString("Hora_Inicio"));
            	Fichaje.setHora_Fin(rs.getString("Hora_Fin"));
            	Fichaje.setFecha(rs.getString("Fecha"));
            	Fichaje.setEmpleado_trabajador_DNI(rs.getString("Empleado_trabajador_DNI"));
            	
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return Fichaje;
    }
}
