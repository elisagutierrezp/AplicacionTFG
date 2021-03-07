package main.java.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.model.Peticion;
import main.java.util.PeticionDao;
import main.java.util.Log;

/**
 * Servlet implementation class PeticionController
 */
@WebServlet("/PeticionController")
public class PeticionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/VacacionesEmpleado.jsp";
	private static String HOLIDAYS = "/CalendarioEmpleado.jsp";
	private static String LIST_PETICIONES = "/ListaPeticiones.jsp";
	private static String LIST_BY_DNI = "/RevisarSolicitudesEmpleado.jsp";
	
	
	private PeticionDao dao;
	private Log log;

	public PeticionController() {
		super();
		dao = new PeticionDao();
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
		
		//Acepta la peticion y actualiza su estado
		if (action.equalsIgnoreCase("aceptar")) {
			
			Peticion peticion = new Peticion();
			request.setAttribute("id", request.getParameter("id"));
			
			peticion.setAceptada(1);
			peticion.setLeido(false);
			
			dao.updatePeticion(Integer.parseInt(request.getParameter("id")), 1);

			request.setAttribute("peticiones", dao.getAllpeticiones());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PETICIONES);
			view.forward(request, response);

		} 
		//Rechaza la peticion y actualiza su estado
		else if (action.equalsIgnoreCase("rechazar")) {
			
			Peticion peticion = new Peticion();
			request.setAttribute("id", request.getParameter("id"));
			
			peticion.setAceptada(0);
			peticion.setLeido(false);
			
			dao.updatePeticion(Integer.parseInt(request.getParameter("id")), 0);

			request.setAttribute("peticiones", dao.getAllpeticiones());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PETICIONES);
			view.forward(request, response);

		}
		//Muesta las peticiones al empleado de la sesion y actualiza su estado leido
		else if (action.equalsIgnoreCase("listpeticionesbydni")) {

			Log.log.info("Parametro valor LIST");
			String DNI = request.getParameter("DNI");
			request.setAttribute("DNI", DNI);
			
			dao.updatePeticionLeido(DNI);
			
			request.setAttribute("peticiones", dao.getAllpeticionesByDNI(DNI));	
			
			forward = LIST_BY_DNI;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
			
		}
	
		else if (action.equalsIgnoreCase("anadirVacaciones")){
			
			Log.log.info("Añadimos vacaciones");
			HttpSession sesion = request.getSession();
			String DNI = (String) sesion.getAttribute("DNI");
			request.setAttribute("DNIVacaciones", DNI);
			
			forward = HOLIDAYS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
			
		}
		//Muestra todas las peticiones a el usuario de RRHH
		else if (action.equalsIgnoreCase("listPeticiones")) {

			Log.log.info("Parametro valor LIST");
			
			forward = LIST_PETICIONES;
			request.setAttribute("peticiones", dao.getAllpeticiones());
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			forward = LIST_PETICIONES;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		return;
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
		
		Peticion peticion = new Peticion();

		//Acepta la peticion y actualiza su estado
		if (request.getParameter("btnAceptar") != null) {
			
			request.setAttribute("id", request.getParameter("id"));
			peticion.setAceptada(1);
			peticion.setLeido(false);
			
			dao.updatePeticion(Integer.parseInt(request.getParameter("id")), 1);
			
			request.setAttribute("peticiones", dao.getAllpeticiones());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PETICIONES);
			view.forward(request, response);

		} 
		//Rechaza la peticion y actualiza su estado
		else if (request.getParameter("btnRechazar") != null) {
			
			request.setAttribute("id", request.getParameter("id"));
			peticion.setAceptada(0);
			peticion.setLeido(false);
			
			dao.updatePeticion(Integer.parseInt(request.getParameter("id")), 0);
			
			request.setAttribute("peticiones", dao.getAllpeticiones());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PETICIONES);
			view.forward(request, response);
		}
		//Crea una peticion de vacaciones
		else if (request.getParameter("solicitar")!=null) {
			
			HttpSession sesion = request.getSession();
			String DNI = (String) sesion.getAttribute("DNI");
			Peticion peticion_ultima = new Peticion();
			
			peticion_ultima = dao.encuentraultimo(); //con el fin de sumar uno al id
		
			peticion.set_Fecha_Inicio(request.getParameter("inicio"));
			peticion.set_Fecha_Fin(request.getParameter("fin"));
			peticion.setMotivo(request.getParameter("motivo"));
			peticion.setEmpleado_trabajador_DNI(DNI);
			peticion.setAceptada(-1);
			peticion.setLeido(false);
			
			if (peticion_ultima != null){
				peticion.setId_Peticion(peticion_ultima.getId_Peticion()+1);
			}
			else {
				peticion.setId_Peticion(0);
			}
			
			dao.addPeticion(peticion);
			
			request.setAttribute("peticiones", dao.getAllpeticiones());
			request.setAttribute("motivo", request.getParameter("motivo"));
			RequestDispatcher view = request.getRequestDispatcher("MenuEmpleado.jsp");
			view.forward(request, response);
		}
		//Crea una peticion de horas libres
		else if (request.getParameter("solicitarhoras")!=null) {
						
			HttpSession sesion = request.getSession();
			String DNI = (String) sesion.getAttribute("DNI");
			Peticion peticion_ultima = new Peticion();
			
			peticion_ultima = dao.encuentraultimo(); //con el fin de sumar uno al id
		
			peticion.set_Fecha_Inicio(request.getParameter("fecha")+" "+request.getParameter("hinicio")+":00");
			peticion.set_Fecha_Fin(request.getParameter("fecha")+" "+ request.getParameter("hfin")+":00");
			peticion.setMotivo(request.getParameter("motivo"));
			peticion.setEmpleado_trabajador_DNI(DNI);
			peticion.setAceptada(-1);
			peticion.setLeido(false);
			
			if (peticion_ultima != null){
				peticion.setId_Peticion(peticion_ultima.getId_Peticion()+1);
			}
			else {
				peticion.setId_Peticion(0);
			}
			
			dao.addPeticion(peticion);
			
			request.setAttribute("peticionesh", dao.getAllpeticiones());
			request.setAttribute("motivo", request.getParameter("motivo"));
			RequestDispatcher view = request.getRequestDispatcher("MenuEmpleado.jsp");
			view.forward(request, response);
		}
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