/**
 En este fichero se crea la conexion con la base de datos. 
 */

package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDB {

    private static Connection connection = null;

    /**
     * Crea la conexion con la base de datos.
     * @return Connection connection
     */
    public static Connection getConnection() {
    	
        String driver = "com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/grupo3_bbdd?serverTimezone=Europe/Madrid";
        String user="root";
        String password= "root"; 
        
        Log.log.info("Conectamos con bbdd");
        if (connection != null) {
        	
            Log.logdb.info("Ya hay una conexion");
            return connection;
            
        } else {
        	
            try {  
            	
                Log.logdb.info("Creamos una nueva conexion");            
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                Log.log.info("conectado");
                
            } catch (ClassNotFoundException e) {
            	Log.log.info("ERROR CONEXION");
                Log.logdb.error("Error de conexion: " + e);                
            } catch (SQLException e) {
            	Log.log.info("ERROR SQL");
                Log.logdb.error("Error de SQL: " + e );                
            }
            return connection;
        }
    }
}