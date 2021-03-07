package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.model.Jornada_Proyecto;

public class Jornada_Proyecto_Dao {

    private Connection connection;

    public Jornada_Proyecto_Dao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Inserta una nueva jornada en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Jornada_Proyecto
     */
    public void addJornada_Proyecto(Jornada_Proyecto Jornada_Proyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Jornada_Proyecto(Fecha, Horas, Empleado_trabajador_DNI, Proyecto_Clave) values (?, ?, ?, ?)");
     
            preparedStatement.setString(1, Jornada_Proyecto.getFecha());
            preparedStatement.setInt(2, Jornada_Proyecto.getHoras());            
            preparedStatement.setString(3, Jornada_Proyecto.getEmpleado_trabajador_DNI());
            preparedStatement.setInt(4, Jornada_Proyecto.getProyecto_Clave());                    
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Elimina una jornada en la base de datos
     * mediante una consulta con la clave del proyecto proporcionado.
     * @param Proyecto_Clave
     */
    public void deleteJornada_Proyecto(int Proyecto_Clave) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Jornada_Proyecto where Proyecto_Clave=?");
            preparedStatement.setInt(4, Proyecto_Clave);
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Actualiza una jornada en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Jornada_Proyecto
     */
    public void updateProyecto(Jornada_Proyecto Jornada_Proyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Jornada_Proyecto set Fecha=?, Horas=?, Empleado_trabajador_DNI=?, Proyecto_Clave=?" + "where Proyecto_Clave=?");
                    
            preparedStatement.setString(1, Jornada_Proyecto.getFecha());
            preparedStatement.setInt(2, Jornada_Proyecto.getHoras());            
            preparedStatement.setString(3, Jornada_Proyecto.getEmpleado_trabajador_DNI());
            preparedStatement.setInt(4, Jornada_Proyecto.getProyecto_Clave());
      
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }

    /**
     * Obtiene una List de todos las jornadas de la base de datos.
     * @return Jornada_Proyectodb
     */
    public List<Jornada_Proyecto> getAllJornada_Proyectos() {
        List<Jornada_Proyecto> Jornada_Proyectodb = new ArrayList<Jornada_Proyecto>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from Jornada_Proyecto;");
                while (rs.next()) {
                	Jornada_Proyecto Jornada_Proyecto = new Jornada_Proyecto();
                
                	Jornada_Proyecto.setFecha(rs.getString("Fecha"));
                	Jornada_Proyecto.setHoras(rs.getInt("Horas"));
                	Jornada_Proyecto.setEmpleado_trabajador_DNI(rs.getString("Empleado_trabajador_DNI"));
                    Jornada_Proyecto.setProyecto_Clave(rs.getInt("Proyecto_Clave"));
                	
                    Jornada_Proyectodb.add(Jornada_Proyecto);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return Jornada_Proyectodb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }

 
}
