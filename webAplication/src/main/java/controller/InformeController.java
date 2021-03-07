package main.java.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.util.InformeDao;
import main.java.model.GenerarPDF;
import main.java.util.Log;

/**
 * Servlet implementation class InformeController
 */
@WebServlet("/InformeController")
public class InformeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/GenerarInforme.jsp";

	private InformeDao dao;
	private Log log;
	private GenerarPDF pdf;

	public InformeController() {
		super();
		dao = new InformeDao();
		pdf = new GenerarPDF();
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Log.log.info("Entramos por el doPost");

		//Al pulsar generar obtiene los valores y genera informe en pdf
		if (request.getParameter("generar") != null) { 

			String mes = "";
			String opcion = (String) request.getParameter("input");
			String fecha = (String) request.getParameter("fechas");
			String seleccion = (String) request.getParameter("select");
			String dia = (String) request.getParameter("inicio");
			String anioSelec = (String) request.getParameter("select3");
			String ruta = "";
			int mesSeleccionado = 0;
			if (request.getParameter("select2") != null) {
				mesSeleccionado = Integer.parseInt(request.getParameter("select2"));
				if (mesSeleccionado < 10) {
					mes = "0" + mesSeleccionado;
				}
			}

			//Para empleado
			if (opcion.equals("Empleado")) {
				
				//En funcion de la fecha: Semana/Mes/A�o
				if (fecha.equals("Semana")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();

					//Obtenemos los datos de la bbdd
					lista = dao.getEmpleadoSemana(seleccion, dia);
					lista2 = dao.getEmpleadoSemanaLibres(seleccion, dia);

					//Pasamos los datos a la clase para generar el pdf
					ruta = pdf.generarPDF(lista, lista2, "Semana");

				} else if (fecha.equals("Mes")) {

					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getEmpleadoMes(seleccion, mes, anioSelec);
					lista2 = dao.getEmpleadoMesLibres(seleccion, mes, anioSelec);

					ruta = pdf.generarPDF(lista, lista2, "Mes");

				} else if (fecha.equals("Anual")) {

					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getEmpleadoYear(seleccion, anioSelec);
					lista2 = dao.getEmpleadoAnioLibres(seleccion, anioSelec);

					ruta = pdf.generarPDF(lista, lista2, "Anual");
											
				 
				}
			}
			//Para proyecto
			else if (opcion.equals("Proyecto")) {

				if (fecha.equals("Semana")) {

					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getProyectoSemana(seleccion, dia);
					lista2 = dao.getProyectoSemanaLibres(seleccion, dia);

					ruta = pdf.generarPDF(lista, lista2, "Semana");

				} else if (fecha.equals("Mes")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getProyectoMes(seleccion, mes, anioSelec);
					lista2 = dao.getProyectoMesLibres(seleccion, mes, anioSelec);

					ruta = pdf.generarPDF(lista, lista2, "Mes");

				} else if (fecha.equals("Anual")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getProyectoYear(seleccion, anioSelec);
					lista2 = dao.getProyectoAnioLibres(seleccion, anioSelec);

					ruta = pdf.generarPDF(lista, lista2, "Anual");
				}

			} 
			//Para Empresa
			else if (opcion.equals("Empresa")) {

				if (fecha.equals("Semana")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();

					lista = dao.getEmpresaSemana(seleccion, dia);
					lista2 = dao.getEmpresaSemanaLibres(seleccion, dia);

					ruta = pdf.generarPDF(lista, lista2, "Semana");

				} else if (fecha.equals("Mes")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getEmpresaMes(seleccion, mes, anioSelec);
					lista2 = dao.getEmpresaMesLibres(seleccion, mes, anioSelec);
					
					ruta = pdf.generarPDF(lista, lista2, "Mes");

				} else if (fecha.equals("Anual")) {
					ArrayList<String> lista = new ArrayList<String>();
					ArrayList<String> lista2 = new ArrayList<String>();
					lista = dao.getEmpresaYear(seleccion, anioSelec);
					lista2 = dao.getEmpresaAnioLibres(seleccion, anioSelec);

					ruta = pdf.generarPDF(lista, lista2, "Anual");

				}
			}

			Log.log.info("Vamos a insertar el proyecto");
			request.setAttribute("generado",true);
			String nuevoPath = ruta.replace('\\', '/');
			request.setAttribute("ruta", nuevoPath);		
		}
		
		RequestDispatcher view = request.getRequestDispatcher(INSERT);
		view.forward(request, response);
		
		return;
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
