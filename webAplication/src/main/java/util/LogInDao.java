package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.model.RRHH;
import main.java.model.Empleado;

public class LogInDao {

    private Connection connection;

    public LogInDao() {
        connection = UtilDB.getConnection();
    }

    /**
     * Obtiene a RRHH de la base de datos buscando si existe
     * mediante el DNI y su contrase�a para iniciar sesion en el sistema.
     * @param  DNI
     * @param Contrasena
     */
    public RRHH getrrhhByDNIContra (String DNI, String Contrasena) {
        RRHH rrhh = new RRHH();
  
        try {
        	  PreparedStatement preparedStatement = connection.prepareStatement("select DNI,Contrasena from Trabajador "
              		+ "inner join RRHH on Trabajador.DNI=RRHH.Trabajador_DNI where DNI=" + "'" + DNI + "' and Contrasena=" + "'" + Contrasena + "'");
        
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {

                	rrhh.setTrabajador_DNI(rs.getString("DNI"));
                	rrhh.setContrasena(rs.getString("Contrasena"));

            }   
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }       
        return rrhh;
    }
    
    /**
     * Obtiene al Empleado de la base de datos buscando si existe
     * mediante el DNI y su contrase�a para iniciar sesion en el sistema.
     * @param  DNI
     * @param Contrasena
     */
    public Empleado getempleadoByDNIContra(String DNI, String Contrasena) {
        Empleado empleado = new Empleado();
        
        try {
        	
            PreparedStatement preparedStatement = connection.prepareStatement("select DNI,Contrasena from Trabajador inner join Empleado on Trabajador.DNI=Empleado.Trabajador_DNI where DNI=" + "'" + DNI + "' and Contrasena=" + "'" + Contrasena + "'");
           
            ResultSet rs = preparedStatement.executeQuery();
           
         
            if (rs.next()) {

                	empleado.setTrabajador_DNI(rs.getString("DNI"));
                	empleado.setContrasena(rs.getString("Contrasena"));
                	
                	Log.log.info(DNI +" "+ Contrasena);
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return empleado;
    }
    
}