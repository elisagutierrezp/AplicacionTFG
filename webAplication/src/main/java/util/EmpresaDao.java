/**
 * Con este fichero se permite modificar, insertar, eliminar y visualizar todas las empresas, 
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
import main.java.model.Empresa;

public class EmpresaDao {

    private Connection connection;

    public EmpresaDao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Inserta una nueva Empresa en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param empresa 
     */
    public void addempresa(Empresa empresa) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Empresa(Id, Nombre, Telefono, CP, Poblacion, Provincia, Direccion) values (?, ?, ?, ?, ?, ?, ?)");
     
            preparedStatement.setInt(1, empresa.getId());
            preparedStatement.setString(2, empresa.getNombre());        
            preparedStatement.setInt(3, empresa.getTelefono());
            preparedStatement.setInt(4, empresa.getCP());
            preparedStatement.setString(5, empresa.getPoblacion());
            preparedStatement.setString(6, empresa.getProvincia());
            preparedStatement.setString(7, empresa.getDireccion());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Elimina una empresa en la base de datos
     * mediante una consulta con la ID de la empresa proporcionada.
     * Al eliminar una empresa se han de borrar todas sus dependecias por lo
     * que ademas se elimina Jornada, Fichaje, Peticion y Trabajador
     * que corresponda con la clave.
     * @param empresaId
     * @param trabajadores
     */
    public void deleteempresa(int empresaId, List<String> trabajadores) {
        try {
        	
        	TrabajadorDao daot = new TrabajadorDao();
        	for (int i = 0; i < trabajadores.size(); i++) {
        		daot.deletetrabajador(trabajadores.get(i));
				//daot.deletetrabajador(trabajadores.get(0));
			}
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Empresa where Id=?");
			preparedStatement.setInt(1, empresaId);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
    }

    /**
     * Actualiza una empresa en la base de datos
     * mediante una consulta con los datos proporcionados.
     * @param empresa
     */
    public void updateempresa(Empresa empresa) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Empresa set Id=?, Nombre=?, Telefono=?, CP=?, Poblacion=?, Provincia=?, Direccion=?" + "where Id=?");

            preparedStatement.setInt(1, empresa.getId());
            preparedStatement.setString(2, empresa.getNombre());
            preparedStatement.setInt(3, empresa.getTelefono());
            preparedStatement.setInt(4, empresa.getCP());
            preparedStatement.setString(5, empresa.getPoblacion());
            preparedStatement.setString(6, empresa.getProvincia());
            preparedStatement.setString(7, empresa.getDireccion());
            preparedStatement.setInt(8, empresa.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);            
        }
    }

    /**
     * Obtiene una List de todos las Empresas de la base de datos.
     * @return lista
     */
    public List<Empresa> getAllempresas() {
        List<Empresa> empresadb = new ArrayList<Empresa>();
        if (connection != null)
        {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from Empresa;");
                while (rs.next()) {
                	Empresa empresa = new Empresa();
                	
                	empresa.setId(rs.getInt("Id"));
                	empresa.setNombre(rs.getString("Nombre"));
                	empresa.setTelefono(rs.getInt("Telefono"));
                	empresa.setCP(rs.getInt("CP"));
                	empresa.setPoblacion(rs.getString("Poblacion"));
                	empresa.setProvincia(rs.getString("Provincia"));
                	empresa.setDireccion(rs.getString("Direccion"));

                    empresadb.add(empresa);
                }
                
            } catch (SQLException e) {
                Log.logdb.error("SQL Exception: " + e);            
            }
            return empresadb;
        }
        else
        {
            Log.logdb.error("No hay conexion con la bbdd");
            return null;
        }
       
    }

    /**
     * Obtiene una Empresa de la base de datos con la ID buscada.
     * @param  empresaId
     * @return Empresa
     */
    public Empresa getempresaById(int empresaId) {
        Empresa empresa = new Empresa();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Empresa where Id=?");
            preparedStatement.setInt(1, empresaId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                	empresa.setId(rs.getInt("Id"));
                	empresa.setNombre(rs.getString("Nombre"));
                	empresa.setTelefono(rs.getInt("Telefono"));
                	empresa.setCP(rs.getInt("CP"));
                	empresa.setPoblacion(rs.getString("Poblacion"));
                	empresa.setProvincia(rs.getString("Provincia"));
                	empresa.setDireccion(rs.getString("Direccion"));
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return empresa;
    }
}
