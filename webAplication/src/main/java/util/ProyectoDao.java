/**
 * COn este fichero se permite modificar, añadir, eliminar y visualizar todas las Proyectos, 
 * además de poder buscar una de ellas a través de su ID. 
 */

package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.Jornada_Proyecto;
import main.java.model.Proyecto;

public class ProyectoDao {

    private Connection connection;

    public ProyectoDao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Inserta un nuevo proyecto en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param proyecto
     */
    public void addProyecto(Proyecto proyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Proyecto(Descripcion, Clave, Id_Empresa) values (?, ?, ?)");
     
            preparedStatement.setString(1, proyecto.getDescripcion());
            preparedStatement.setInt(2, proyecto.getClave());            
            preparedStatement.setInt(3, proyecto.getId_Empresa());
         
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Elimina un proyecto en la base de datos
     * mediante una consulta con la clave del proyecto proporcionado.
     * Al eliminar un proyecto se han de borrar todas sus dependecias por lo
     * que ademas se elimina Jornada,que corresponda con la clave del proyecto.
     * @param ProyectoClave
     */
    public void deleteProyecto(int ProyectoClave) {
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement("DELETE from jornada_proyecto where Proyecto_Clave=?");
            preparedStatement.setInt(1, ProyectoClave);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from Proyecto where Clave=?");
            preparedStatement1.setInt(1, ProyectoClave);
            preparedStatement1.executeUpdate();
           
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Actualiza un proyecto en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param Proyecto
     */
    public void updateProyecto(Proyecto Proyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Proyecto set Descripcion=? where Clave=?");
                    
            preparedStatement.setString(1, Proyecto.getDescripcion());        
            preparedStatement.setInt(2, Proyecto.getClave()); 
      
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }

    /**
     * Obtiene una List con un arraylist de los proyectos y las jornadas de los mismos
     * de la base de datos que se encuentren con el DNI de la jornada buscado.
     * @param  DNI
     * @return lista
     */
    public List<ArrayList> getAllProyectosJornada(String DNI) {
        
        @SuppressWarnings("rawtypes")
		List<ArrayList> lista = new ArrayList<ArrayList> ();
        ArrayList<Proyecto> Proyectodb = new ArrayList<Proyecto>();
        ArrayList<Jornada_Proyecto> Jornadadb = new ArrayList<Jornada_Proyecto>();       
        
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("Select Descripcion,Clave, Id_Empresa,sum(Horas), Fecha, Trabajador_DNI from Proyecto "
                		+ "inner join jornada_proyecto on Proyecto.Clave = jornada_proyecto.Proyecto_Clave inner join Empleado "
                		+ "on Empleado.Trabajador_DNI= jornada_proyecto.Empleado_trabajador_DNI WHERE Trabajador_DNI='"+DNI+ "' group by Proyecto.clave;");
                
                while (rs.next()) {
                	Proyecto Proyecto = new Proyecto();
                	Jornada_Proyecto jornada = new Jornada_Proyecto(); 
                	
                	Proyecto.setDescripcion(rs.getString("Descripcion"));
                	Proyecto.setClave(rs.getInt("Clave"));
                	Proyecto.setId_Empresa(rs.getInt("Id_Empresa"));
                	jornada.setHoras(rs.getInt("sum(Horas)"));
                	jornada.setProyecto_Clave(rs.getInt("Clave"));
                	jornada.setEmpleado_trabajador_DNI(rs.getString("Trabajador_DNI"));
                	
                    Proyectodb.add(Proyecto);
                    Jornadadb.add(jornada);           
                    
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            lista.add(Proyectodb);
            lista.add(Jornadadb);
            return lista;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }
    
    /**
     * Obtiene una List de Proyectos de la base de datos
     * que tengan el DNI buscado entre sus empleados.
     * @param  DNI
     * @return Proyectodb
     */
    public List<Proyecto> getProyectosDNI(String DNI) {
        List<Proyecto> Proyectodb = new ArrayList<Proyecto>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("Select Descripcion,Clave, Id_Empresa,Horas, Fecha, Trabajador_DNI from Proyecto "
                		+ "inner join jornada_proyecto on Proyecto.Clave = jornada_proyecto.Proyecto_Clave inner join Empleado "
                		+ "on Empleado.Trabajador_DNI= jornada_proyecto.Empleado_trabajador_DNI WHERE Trabajador_DNI='"+DNI+ "';");
                
                
                
                while (rs.next()) {
                	Proyecto Proyecto = new Proyecto();
                
                	Proyecto.setDescripcion(rs.getString("Descripcion"));
                	Proyecto.setClave(rs.getInt("Clave"));
                	Proyecto.setId_Empresa(rs.getInt("Id_Empresa"));
                	
                    Proyectodb.add(Proyecto);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return Proyectodb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }

    /**
     * Obtiene una List de todos los proyectos de la base de datos.
     * @return Proyectodb
     */
    public List<Proyecto> getTodosProyectos() {
        List<Proyecto> Proyectodb = new ArrayList<Proyecto>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("Select * from Proyecto");
                       
                while (rs.next()) {
                	Proyecto Proyecto = new Proyecto();
                
                	Proyecto.setDescripcion(rs.getString("Descripcion"));
                	Proyecto.setClave(rs.getInt("Clave"));
                	Proyecto.setId_Empresa(rs.getInt("Id_Empresa"));
                	

                    Proyectodb.add(Proyecto);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return Proyectodb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }
    
    /**
     * Obtiene un Proyecto de la base de datos
     * mediante una consulta con la clave del proyecto buscado.
     * @param  ProyectoClave
     * @return Proyecto Proyecto
     */
    public Proyecto getProyectoByClave(int ProyectoClave) {
        Proyecto Proyecto = new Proyecto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Proyecto where Clave=?");
            preparedStatement.setInt(1, ProyectoClave);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	Proyecto.setDescripcion(rs.getString("Descripcion"));
            	Proyecto.setClave(rs.getInt("Clave"));
            	Proyecto.setId_Empresa(rs.getInt("Id_Empresa"));
            	
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return Proyecto;
    }
}
