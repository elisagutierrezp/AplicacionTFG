
package main.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import main.java.model.Empleado;
import main.java.util.EmpleadoDao;
import main.java.util.TrabajadorDao;
import main.java.model.Trabajador;
import main.java.util.Log;


/**
 * Servlet implementation class EmpleadoController
 */
public class EmpleadoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT = "/AnadirEmpleado.jsp";
	private static String LIST_USER = "/listaEmpleados.jsp";
	private static String EDIT = "/ModificarEmpleado.jsp";
	private EmpleadoDao dao;
	private TrabajadorDao daot;
	private Log log;

	public EmpleadoController() {
		super();
		dao = new EmpleadoDao();
		daot = new TrabajadorDao();
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
		Log.log.info("Parametro valor LIST");
		
		//Elimina el empleado
		if (action.equalsIgnoreCase("delete")) {

			Log.log.info("Parametro valor DELETE");
			String empleadoDNI = request.getParameter("DNI");
			dao.deleteempleado(empleadoDNI);
			
			forward = LIST_USER;
			request.setAttribute("empleados", dao.getAllempleados());

		} else if (action.equalsIgnoreCase("edit")) { //Redirige a la ventana para modificar el empleado

			Log.log.info("Parametro valor EDIT");
			forward = EDIT;
			String trabajadorDNI = request.getParameter("DNI");
			Trabajador trabajador = daot.gettrabajadorByDNI(trabajadorDNI);
			request.setAttribute("empleado", trabajador);

		} else if (action.equalsIgnoreCase("add")) { //Redirige a la ventana para insertar un empleado nuevo

			Log.log.info("Parametro valor ADD");
			forward = INSERT;
			String empleadoDNI = request.getParameter("empleadoDNI");
			Empleado empleado = dao.getempleadoByDNI(empleadoDNI);
			request.setAttribute("empleado", empleado);

		}
		//Redirige a la ventana para ver los empleados
		else if (action.equalsIgnoreCase("listEmpleado")) {

			Log.log.info("Parametro valor LIST");
			forward = LIST_USER;
			request.setAttribute("empleados", dao.getAllempleados());

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
		
		Empleado empleado = new Empleado();
		Trabajador trabajador = new Trabajador();
		
		
		//Inserta el empleado tras rellenar los campos y pulsar anadir
		if (request.getParameter("anadir") != null) {

			trabajador.setDNI(request.getParameter("DNI"));
			trabajador.setNombre(request.getParameter("Nombre"));
			trabajador.setApellido(request.getParameter("Apellido"));
			trabajador.setTelefono(Integer.parseInt(request.getParameter("Telefono")));
			trabajador.setContrasena(request.getParameter("Contrasena"));
			trabajador.setId_Empresa(Integer.parseInt(request.getParameter("Id_Empresa")));
			trabajador.setPuesto("Empleado");

			empleado.setTrabajador_DNI(request.getParameter("DNI"));

		
			Log.log.info("Vamos a insertar el usuario");
			daot.addtrabajador(trabajador);
			dao.addempleado(empleado);

		}
		
		//Actualiza el empleado tras rellenar los campos y pulsar btnUpdate
		else if (request.getParameter("btnUpdate") != null) {
			
			trabajador.setDNI(request.getParameter("DNI"));
			trabajador.setNombre(request.getParameter("Nombre"));
			trabajador.setApellido(request.getParameter("Apellido"));
			trabajador.setTelefono(Integer.parseInt(request.getParameter("Telefono")));
			trabajador.setContrasena(request.getParameter("Contrasena"));
			trabajador.setId_Empresa(Integer.parseInt(request.getParameter("Id_Empresa")));
			trabajador.setPuesto("Empleado");
			empleado.setTrabajador_DNI(request.getParameter("DNI"));
			
			Log.log.info("Se actualiza el empleado y el trabajador");
			dao.updateempleado(empleado);
			daot.updatetrabajador(trabajador);
			
		}
		
		request.setAttribute("empleados", dao.getAllempleados());
		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
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
