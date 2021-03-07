package main.java.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.model.Proyecto;
import main.java.util.ProyectoDao;
import main.java.model.Jornada_Proyecto;
import main.java.util.Jornada_Proyecto_Dao;
import main.java.util.Log;

/**
 * Servlet implementation class ProyectoController
 */
@WebServlet("/ProyectoController")
public class ProyectoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/AnadirProyecto.jsp";
	private static String INSERT_JORNADA = "/AnadirJornada_Proyecto.jsp";
	private static String EDIT = "/ModificarProyecto.jsp";
	private static String LIST_PROJECTA = "/ProyectosRRHH.jsp";
	private static String LIST_PROJECTB = "/ProyectosEmpleado.jsp";
	private ProyectoDao dao;
	private Log log;

	public ProyectoController() {
		super();
		dao = new ProyectoDao();
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
		HttpSession session = (HttpSession) request.getSession();
		String DNI = (String) session.getAttribute("DNI");
		Log.log.info("Entramos en el doGet");
		String action = request.getParameter("action");
		Log.log.info("Recogemos el parametro action con valor " + action);

		//Elimina un proyecto
		if (action.equalsIgnoreCase("delete")) {

			Log.log.info("Parametro valor DELETE");
			int proyectoClave = Integer.parseInt(request.getParameter("clave"));
			
			dao.deleteProyecto(proyectoClave);	
			
			request.setAttribute("proyectos", dao.getAllProyectosJornada(DNI));
			forward = LIST_PROJECTA;

		} 
		//Redirige a modificar un proyecto
		else if (action.equalsIgnoreCase("edit")) {

			Log.log.info("Parametro valor EDIT");
			forward = EDIT;
			int proyectoClave = Integer.parseInt(request.getParameter("clave"));
			Proyecto proyecto = dao.getProyectoByClave(proyectoClave);
			request.setAttribute("proyectos", proyecto);

		} 
		//Muestra todos los proyectos (RRHH)
		else if (action.equalsIgnoreCase("listProyectoA")) {

			Log.log.info("Parametro valor LIST");
			forward = LIST_PROJECTA;
			request.setAttribute("proyectos", dao.getTodosProyectos());

		} 
		//Muestra los proyectos del empleado de la sesion
		else if (action.equalsIgnoreCase("listProyectoB")) {

			Log.log.info("Parametro valor LIST");
			forward = LIST_PROJECTB;
			request.setAttribute("proyectos", dao.getProyectosDNI(DNI));

		} 
		//Redirige a insertar nueva jornada
		else if (action.equalsIgnoreCase("insertJornada")) {
			forward = INSERT_JORNADA;
			request.setAttribute("proyectos", dao.getProyectosDNI(DNI));
		} else {
			Log.log.info("Parametro valor vacio vamos a insertar");
			forward = INSERT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
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
		Proyecto proyecto = new Proyecto();
		Jornada_Proyecto jornada = new Jornada_Proyecto();
		
		//Inserta un nuevo proyecto
		if (request.getParameter("anadir") != null) {

			proyecto.setId_Empresa(Integer.parseInt(request.getParameter("Id_Empresa")));
			proyecto.setClave(Integer.parseInt(request.getParameter("clave")));
			proyecto.setDescripcion(request.getParameter("descripcion"));

			Log.log.info("Vamos a insertar el proyecto");
			
			dao.addProyecto(proyecto);

			request.setAttribute("proyectos", dao.getTodosProyectos());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PROJECTA);
			view.forward(request, response);
		}
		//Modificar proyecto
		else if (request.getParameter("btnUpdate") != null) {

			Log.log.info("Parametro valor UPDATE");

			proyecto.setClave(Integer.parseInt(request.getParameter("Clave")));
			proyecto.setDescripcion(request.getParameter("Descripcion"));
			proyecto.setId_Empresa(Integer.parseInt(request.getParameter("Id_Empresa")));
			
			dao.updateProyecto(proyecto);

			request.setAttribute("proyectos", dao.getTodosProyectos());
			
			RequestDispatcher view = request.getRequestDispatcher(LIST_PROJECTA);
			view.forward(request, response);
			
		}
		//Inserta una jornada de proyecto nueva
		else if (request.getParameter("addJornada") != null) {
			Jornada_Proyecto_Dao daojp = new Jornada_Proyecto_Dao();
			
			jornada.setEmpleado_trabajador_DNI(request.getParameter("Empleado_trabajador_DNI"));
			jornada.setFecha(request.getParameter("Fecha"));
			jornada.setHoras(Integer.parseInt(request.getParameter("Horas")));
			jornada.setProyecto_Clave(Integer.parseInt(request.getParameter("Clave")));
			
			Log.log.info("Vamos a insertar la jornada");
			daojp.addJornada_Proyecto(jornada);

			request.setAttribute("proyectos", dao.getTodosProyectos());
			RequestDispatcher view = request.getRequestDispatcher(LIST_PROJECTB);
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
