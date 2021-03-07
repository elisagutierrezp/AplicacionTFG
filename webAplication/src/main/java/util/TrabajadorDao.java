package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.model.Trabajador;

public class TrabajadorDao {

    private Connection connection;

    public TrabajadorDao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Inserta un nuevo trabajador en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param trabajador
     */
    public void addtrabajador(Trabajador trabajador) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Trabajador(DNI, Nombre, Apellido, Telefono, Contrasena, Id_Empresa, Puesto) values (?, ?, ?, ?, ?, ?, ?)");
     
            preparedStatement.setString(1, trabajador.getDNI());
            preparedStatement.setString(2, trabajador.getNombre());          
            preparedStatement.setString(3, trabajador.getApellido());
            preparedStatement.setInt(4, trabajador.getTelefono());
            preparedStatement.setString(5, trabajador.getContrasena());
            preparedStatement.setInt(6, trabajador.getId_Empresa());
            preparedStatement.setString(7, trabajador.getPuesto());
                                           
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Elimina un trabajador en la base de datos
     * mediante una consulta con el DNI del trabajador proporcionado.
     * Al eliminar un trabajador se han de borrar todas sus dependecias por lo
     * que ademas se elimina Jornada, Fichaje, Peticion y Empleado 
     * que corresponda con el DNI.
     * @param DNI
     */
    public void deletetrabajador(String DNI) {
        try {

			PreparedStatement preparedStatement = connection.prepareStatement("DELETE from jornada_proyecto where Empleado_trabajador_DNI=?");
			preparedStatement.setString(1, DNI);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE from Fichaje where Empleado_trabajador_DNI=?");
			preparedStatement1.setString(1, DNI);
            preparedStatement1.executeUpdate();
            
            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE from Peticion  where Empleado_trabajador_DNI=?");
			preparedStatement2.setString(1, DNI);
            preparedStatement2.executeUpdate();
			
            PreparedStatement preparedStatement3 = connection.prepareStatement("DELETE FROM Empleado where Trabajador_DNI=?");
			preparedStatement3.setString(1, DNI);
            preparedStatement3.executeUpdate();
            
			PreparedStatement preparedStatement4 = connection.prepareStatement("DELETE FROM Trabajador where DNI=?");
			preparedStatement4.setString(1, DNI);
            preparedStatement4.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Actualiza un trabajador en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param trabajador
     */
    public void updatetrabajador(Trabajador trabajador) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Trabajador set DNI=?, Nombre=?, Apellido=?, Telefono=?, Contrasena=?, Id_Empresa=?, Puesto=?" + "where DNI=?");

            preparedStatement.setString(1, trabajador.getDNI());
            preparedStatement.setString(2, trabajador.getNombre());          
            preparedStatement.setString(3, trabajador.getApellido());
            preparedStatement.setInt(4, trabajador.getTelefono());
            preparedStatement.setString(5, trabajador.getContrasena());
            preparedStatement.setInt(6, trabajador.getId_Empresa());
            preparedStatement.setString(7, trabajador.getPuesto());
            preparedStatement.setString(8, trabajador.getDNI());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }

    /**
     * Obtiene una List de todos los Trabajadores de la base de datos.
     * @return trabajadordb
     */
    public List<Trabajador> getAlltrabajadores() {
        List<Trabajador> trabajadordb = new ArrayList<Trabajador>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from Trabajador;");
                
                while (rs.next()) {                	
                	
                	Trabajador trabajador = new Trabajador();
                  
                  trabajador.setDNI(rs.getString("DNI"));
                  trabajador.setNombre(rs.getString("Nombre"));
                  trabajador.setApellido(rs.getString("Apellido"));
                  trabajador.setTelefono(rs.getInt("Telefono"));
                  trabajador.setContrasena(rs.getString("Contrasena"));
                  trabajador.setId_Empresa(rs.getInt("Id_Empresa"));
                  trabajador.setPuesto(rs.getString("Puesto"));
   	
                    trabajadordb.add(trabajador);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return trabajadordb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }

    /**
     * Obtiene una List de trabajadores de la base de datos
     * que se encuentren en la empresa buscada.
     * @param  id
     * @return DNIs
     */
    public List<String> getAlltrabajadoresEmpresa(int id) {
        List<String> DNIs = new ArrayList<String>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select DNI from Trabajador where Puesto!='RRHH' and Id_Empresa="+ id+";");
                while (rs.next()) {                	
                    DNIs.add(rs.getString("DNI"));
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return DNIs;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }
    
    /**
     * Obtiene un Trabajador de la base de datos
     * mediante una consulta con el DNI del trabajador buscado.
     * @param  DNI
     * @return Trabajador trabajador
     */
    public Trabajador gettrabajadorByDNI(String DNI) {
        Trabajador trabajador = new Trabajador();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Trabajador where DNI=?");
            preparedStatement.setString(1, DNI);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                  trabajador.setDNI(rs.getString("DNI"));
                  trabajador.setNombre(rs.getString("Nombre"));
                  trabajador.setApellido(rs.getString("Apellido"));
                  trabajador.setTelefono(rs.getInt("Telefono"));
                  trabajador.setContrasena(rs.getString("Contrasena"));
                  trabajador.setId_Empresa(rs.getInt("Id_Empresa"));
                  trabajador.setPuesto(rs.getString("Puesto"));
                
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return trabajador;
    }
}
