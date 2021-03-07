
package main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import main.java.model.Fichaje;
import main.java.util.FichajeDao;
import main.java.util.Log;

/**
 * Servlet implementation class FichajeController
 */
@WebServlet("/FichajeController")
public class FichajeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT = "/MenuEmpleado.jsp";
	private FichajeDao dao;
	private Log log;

	public FichajeController() {
		super();
		dao = new FichajeDao();
	}


	/**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String forward = "";
		Log.log.info("Entramos en el doGet");
		String action = request.getParameter("action");
		Log.log.info("Recogemos el parametro action con valor " + action);
		
		request.setAttribute("finalizado", 0);
		Fichaje fichaje = new Fichaje();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = dateFormat.format(date);
			
		if (action.equalsIgnoreCase("anadirInicioJornada")) {
			
			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss");
			String hora = hourdateFormat.format(date);
			String DNI = request.getParameter("DNI");
			
			request.setAttribute("iniciado", 1);
			request.setAttribute("finalizado", 0);
			
			fichaje.setFecha(fecha);
			fichaje.setHora_Inicio(fecha+" "+hora);
			fichaje.setEmpleado_trabajador_DNI(DNI);
	
			dao.addFichaje(fichaje);

			forward = INSERT;

		} else if (action.equalsIgnoreCase("anadirFinJornada")) {
			
			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss");
			String horaSalida = hourdateFormat.format(date);
			String DNI = request.getParameter("DNI");
			
			request.setAttribute("iniciado", 0);
			
			dao.anadirFinFIchaje(DNI, fecha+" "+horaSalida);
			
			request.setAttribute("finalizado", 1);
			forward = INSERT;
		}else {
			Log.log.info("Parametro valor vacio vamos a insertar");
			forward = INSERT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
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