
package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformeDao {

	private Connection connection;

	public InformeDao() {
		connection = UtilDB.getConnection();
	}

	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en un proyecto en una semana.
     * @param DNI
     * @param dia
     * @return lista
     */
	public ArrayList<String> getEmpleadoSemana(String DNI, String dia) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from Empleado inner join jornada_proyecto on "
							+ "trabajador_DNI = Empleado_trabajador_DNI where Fecha>= '" + dia + "' "
							+ "and Fecha<= DATE_ADD('" + dia + "', INTERVAL 7 DAY) and trabajador_DNI='" + DNI + "';");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Empleado DNI: " + DNI+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en un proyecto en un anio y mes concretos.
     * @param DNI
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpleadoMes(String DNI, String mes,String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto where "
							+ "Empleado_trabajador_DNI='"+ DNI+ "' and DATE_FORMAT(Fecha,'%Y/%m')='"+ year+ "/"+mes+"';");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Empleado DNI: " + DNI+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en un proyecto en un anio.
     * @param DNI
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpleadoYear(String DNI, String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto where Empleado_trabajador_DNI='"+DNI+ "' and year(fecha)='"+ year+ "';");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Empleado DNI: " + DNI+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de una semana.
     * Es decir, obtiene las vacaciones de la semana seleccionada.
     * @param DNI
     * @param dia
     * @return lista 
     */
	public ArrayList<String> getEmpleadoSemanaLibres(String DNI, String dia) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo from  Peticion where Fecha_Inicio>='"+dia+"' "
							+ "and Fecha_Fin<= DATE_ADD('"+dia+"', INTERVAL 7 DAY) and Empleado_trabajador_DNI='"+DNI+"' and Aceptada=1; ");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha_Inicio = "", fecha_Fin="", motivo="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");

				lista.add("Empleado DNI: "+DNI+ " ---- Desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un mes.
     * Es decir, obtiene las vacaciones del mes seleccionado.
     * @param DNI
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpleadoMesLibres(String DNI,String mes, String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo from  Peticion  where (DATE_FORMAT(Fecha_Inicio,'%Y/%m')='"+year+"/"+mes+"' or DATE_FORMAT(Fecha_Fin,'%Y/%m')='"+year+"/"+mes+"') and Empleado_trabajador_DNI='"+DNI+"' and Aceptada=1 group by Fecha_Inicio, Fecha_Fin; ");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha_Inicio = "", fecha_Fin="", motivo="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");

				lista.add("Empleado DNI: "+DNI+ " ---- Desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un anio.
     * Es decir, obtiene las vacaciones del a�o seleccionado.
     * @param DNI
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpleadoAnioLibres(String DNI, String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo from  Peticion  where (year(Fecha_Inicio)='"+year+"' or year(Fecha_Fin)='"+year+"') and Empleado_trabajador_DNI='"+DNI+"' and Aceptada=1 group by Fecha_Inicio, Fecha_Fin; ");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha_Inicio = "", fecha_Fin="", motivo="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");

				lista.add("Empleado DNI: "+DNI+ " ---- Desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}

	/**
     * Obtiene un ArrayList de las horas y fecha que han trabajado
     * en un proyecto en una semana.
     * @param clave
     * @param dia
     * @return lista
     */
	public ArrayList<String> getProyectoSemana(String clave, String dia){
        ArrayList<String> lista = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("select Fecha, sum(horas) from Empleado inner join jornada_proyecto on trabajador_DNI = Empleado_trabajador_DNI " + 
                    		"inner join Proyecto on Clave=Proyecto_Clave where Fecha>= '"+dia+"' and Fecha<= DATE_ADD('"+dia+"', INTERVAL 7 DAY) and clave='"+clave+"' group by Fecha;");

            ResultSet rs = preparedStatement.executeQuery();

            String fecha="";
            int horas=0;

            while (rs.next()) {
                fecha = rs.getString("Fecha");
                horas = Integer.parseInt(rs.getString("sum(horas)"));
                lista.add("Proyecto clave: " + clave+ "  ----  Fecha: " + fecha + "  ---- Horas: " + horas);
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return lista;

    }
	
	/**
     * Obtiene un ArrayList de las horas y fecha que han trabajado
     * en un proyecto en un mes.
     * @param clave
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getProyectoMes(String clave, String mes,String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto where "
							+ "Proyecto_Clave='"+ clave+ "' and DATE_FORMAT(Fecha,'%Y/%m')='"+ year+ "/"+mes+"' group by Fecha;");

			ResultSet rs = preparedStatement.executeQuery();
			
			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Proyecto clave: " + clave+ "  ----  Fecha: " + fecha + "  ---- Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	/**
     * Obtiene un ArrayList de las horas y fecha que han trabajado
     * en un proyecto en un ano.
     * @param clave
     * @param year
     * @return lista
     */
	public ArrayList<String> getProyectoYear(String clave, String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto where Proyecto_Clave='"+clave+ "' and year(fecha)='"+ year+ "' group by Fecha;");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Proyecto clave: " + clave+ "  ----  Fecha: " + fecha + "  ---- Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de una semana que afectan al proyecto.
     * Es decir, obtiene las vacaciones que afecten al proyecto de la semana seleccionada.
     * @param clave
     * @param dia
     * @return lista
     */
	public ArrayList<String> getProyectoSemanaLibres(String clave, String dia){
        ArrayList<String> lista = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre,Trabajador.Apellido from Empleado inner join Trabajador on DNI=trabajador_DNI inner join jornada_proyecto on trabajador_DNI = Empleado_trabajador_DNI inner join Proyecto on Clave=Proyecto_Clave inner join Peticion on Peticion.Empleado_trabajador_DNI=DNI where Fecha>='"+dia+"' and Fecha<= DATE_ADD('"+dia+"', INTERVAL 7 DAY) and clave='"+clave+"' and Aceptada=1 group by Fecha_Inicio, Fecha_Fin;");

            ResultSet rs = preparedStatement.executeQuery();

            String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";

            while (rs.next()) {
            	fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");
				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return lista;
    }
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un mes que afectan al proyecto.
     * Es decir, obtiene las vacaciones que afecten al proyecto del mes seleccionado.
     * @param clave
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getProyectoMesLibres(String clave, String mes,String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre,Trabajador.Apellido from Empleado inner join Trabajador on DNI=trabajador_DNI inner join jornada_proyecto on trabajador_DNI = Empleado_trabajador_DNI inner join Proyecto on Clave=Proyecto_Clave inner join Peticion on Peticion.Empleado_trabajador_DNI=DNI where DATE_FORMAT(Fecha,'%Y/%m')='"+year+"/"+mes+"' and clave='"+clave+"' and Aceptada=1 group by Fecha_Inicio, Fecha_Fin;");

			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("tras query");
			String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";

			while (rs.next()) {
            	fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");
				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);

            }
			
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un anio que afectan al proyecto.
     * Es decir, obtiene las vacaciones que afecten al proyecto del anio seleccionado.
     * @param clave
     * @param year
     * @return lista
     */
	public ArrayList<String> getProyectoAnioLibres(String clave,String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre,Trabajador.Apellido from Empleado inner join Trabajador on DNI=trabajador_DNI inner join jornada_proyecto on trabajador_DNI = Empleado_trabajador_DNI inner join Proyecto on Clave=Proyecto_Clave inner join Peticion on Peticion.Empleado_trabajador_DNI=DNI where year(Fecha)='"+year+"'  and clave='"+clave+"' and Aceptada=1 group by Fecha_Inicio, Fecha_Fin;");

			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("tras query");
			String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";

			while (rs.next()) {
            	fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");
				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
            }
			
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en una empresa en una semana.
     * @param id
     * @param dia
     * @return lista
     */
	public ArrayList<String> getEmpresaSemana(String id, String dia){
        ArrayList<String> lista = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("select Fecha, sum(horas) from Empleado inner join jornada_proyecto on trabajador_DNI = Empleado_trabajador_DNI "
                    		+ "inner join Trabajador on DNI=trabajador_DNI inner join Empresa on "
                    		+ "Id=Id_Empresa where Fecha>='"+ dia+"' and Fecha<= DATE_ADD('"+ dia+"', INTERVAL 7 DAY) and Id='"+id+"' group by Fecha;");

            ResultSet rs = preparedStatement.executeQuery();
           
            String fecha="";
            int horas=0;

            while (rs.next()) {
                fecha = rs.getString("Fecha");
                horas = Integer.parseInt(rs.getString("sum(horas)"));
                lista.add("Empresa ID: " + id+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
            }
        } catch (SQLException e) {
            Log.logdb.error("SQL Exception: " + e);
        }
        return lista;

    }
	
	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en una empresa en un mes.
     * @param id
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpresaMes(String id, String mes,String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto inner join Proyecto on Proyecto_Clave = Clave inner join Empresa on" + 
							" Id_Empresa = Id where Id='"+id +"' and DATE_FORMAT(Fecha,'%Y/%m')='"+ year+ "/"+mes+"' group by Fecha;");

			ResultSet rs = preparedStatement.executeQuery();
			
			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Empresa ID: " + id+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las horas y fecha que ha trabajado un empleado
     * en una empresa en un anio.
     * @param id
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpresaYear(String id, String year) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Fecha, Horas from jornada_proyecto inner join Proyecto on Proyecto_Clave = Clave inner join Empresa on "
							+ "Id_Empresa = Id where Id="+id+ " and year(fecha)='"+ year+ "' group by Fecha;");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha = "";
			int horas = 0;

			while (rs.next()) {

				fecha = rs.getString("Fecha");
				horas = Integer.parseInt(rs.getString("Horas"));

				lista.add("Empresa ID: " + id+ "  ----  Fecha: " + fecha + "  ----  Horas: " + horas);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}

	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de una semana que afectan a la empresa.
     * Es decir, obtiene las vacaciones que afecten a la empresa de la semana seleccionada.
     * @param id
     * @param dia
     * @return lista
     */
	public ArrayList<String> getEmpresaSemanaLibres(String id, String dia) {
		ArrayList<String> lista = new ArrayList<String>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre, Trabajador.Apellido from Empleado inner join Peticion on trabajador_DNI = Empleado_trabajador_DNI inner join Trabajador on DNI=trabajador_DNI inner join Empresa on Id=Id_Empresa where Fecha_Inicio>='"+dia+"' and Fecha_Fin<= DATE_ADD('"+dia+"', INTERVAL 7 DAY) and Id='"+id+"' and Aceptada=1;");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");

				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un mes que afectan a la empresa.
     * Es decir, obtiene las vacaciones que afecten a la empresa del mes seleccionado.
     * @param id
     * @param mes
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpresaMesLibres(String id, String mes, String year) {
		ArrayList<String> lista = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre, Trabajador.Apellido from Empleado inner join Peticion on trabajador_DNI = Empleado_trabajador_DNI inner join Trabajador on DNI=trabajador_DNI inner join Empresa on Id=Id_Empresa where (DATE_FORMAT(Fecha_Inicio,'%Y/%m')='"+year+"/"+mes+"' or DATE_FORMAT(Fecha_Fin,'%Y/%m')='"+year+"/"+mes+"') and Id='"+id+"' and Aceptada=1;");

			ResultSet rs = preparedStatement.executeQuery();

			String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");

				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	/**
     * Obtiene un ArrayList de las fecha inicio y final y motivo de las peticiones aceptadas
     * realizadas por un empleado en un intervalo de un anio que afectan a la empresa.
     * Es decir, obtiene las vacaciones que afecten a la empresa del anio seleccionado.
     * @param id
     * @param year
     * @return lista
     */
	public ArrayList<String> getEmpresaAnioLibres(String id, String year) {
		ArrayList<String> lista = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Fecha_Inicio, Fecha_Fin, Motivo, Trabajador.Nombre, Trabajador.Apellido from Empleado inner join Peticion on trabajador_DNI = Empleado_trabajador_DNI inner join Trabajador on DNI=trabajador_DNI inner join Empresa on Id=Id_Empresa where (year(Fecha_Inicio)='"+year+"' or year(Fecha_Fin)='"+year+"') and Id="+id+" and Aceptada=1;");

			ResultSet rs = preparedStatement.executeQuery();
			String fecha_Inicio = "", fecha_Fin="", motivo="", nombre="", apellido="";
			
			while (rs.next()) {

				fecha_Inicio = rs.getString("Fecha_Inicio");
				fecha_Fin = rs.getString("Fecha_Fin");
				motivo = rs.getString("Motivo");
				nombre= rs.getString("Trabajador.Nombre");
				apellido= rs.getString("Trabajador.Apellido");

				lista.add(nombre+ " " + apellido + " ---- Estuvo ausente desde " + fecha_Inicio +" ---- Hasta " + fecha_Fin+" ---- Con motivo: " + motivo);
			}
		} catch (SQLException e) {
			Log.logdb.error("SQL Exception: " + e);
		}
		return lista;

	}
	
	

}