/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
import main.java.model.Empresa;
import main.java.util.EmpresaDao;
import main.java.util.Log;
import main.java.util.TrabajadorDao;

/**
 * Servlet implementation class EmpresaController
 */
public class EmpresaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT = "/AnadirEmpresa.jsp";
	private static String EDIT = "/ModificarEmpresa.jsp";
	private static String LIST_ENTERPRISE = "/listaEmpresas.jsp";
	private EmpresaDao dao;
	private TrabajadorDao daot;
	private Log log;

	public EmpresaController() {
		super();
		dao = new EmpresaDao();
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

		if (action.equalsIgnoreCase("delete")) { //Elimina Empresa 

			Log.log.info("Parametro valor INSERT");
			
			int Id_Empresa = Integer.parseInt(request.getParameter("Id"));
			List<String> trabajadores = daot.getAlltrabajadoresEmpresa(Id_Empresa);
			
			dao.deleteempresa(Id_Empresa,trabajadores);
			
			request.setAttribute("empresas", dao.getAllempresas());
			forward = LIST_ENTERPRISE;

		} else if (action.equalsIgnoreCase("edit")) { 

			Log.log.info("Parametro valor EDIT");
			
			forward = EDIT;
			int Id_Empresa = Integer.parseInt(request.getParameter("Id"));
			Empresa empresa = dao.getempresaById(Id_Empresa);
			
			request.setAttribute("empresa", empresa);
		}

		else if (action.equalsIgnoreCase("update")) {

			if (request.getParameter("btnUpdate") != null) {
				
				Empresa empresa = new Empresa();
				Log.log.info("Parametro valor UPDATE");

				empresa.setId(Integer.parseInt(request.getParameter("Id")));
				empresa.setNombre(request.getParameter("Nombre"));
				empresa.setCP(Integer.parseInt(request.getParameter("CP")));
				empresa.setTelefono(Integer.parseInt(request.getParameter("Telefono")));
				empresa.setPoblacion(request.getParameter("Poblacion"));
				empresa.setProvincia(request.getParameter("Provincia"));
				empresa.setDireccion(request.getParameter("Direccion"));

				dao.updateempresa(empresa);

				request.setAttribute("empresas", dao.getAllempresas());
				RequestDispatcher view = request.getRequestDispatcher(LIST_ENTERPRISE);
				view.forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("listaEmpresas")) {

			Log.log.info("Parametro valor LIST");
			forward = LIST_ENTERPRISE;
			request.setAttribute("empresas", dao.getAllempresas());

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
		/* processRequest(request, response); */
		Empresa empresa = new Empresa(); // Creamos la nueva empresa

		if (request.getParameter("anadir") != null) {

			empresa.setId(Integer.parseInt(request.getParameter("Id")));
			empresa.setNombre(request.getParameter("Nombre"));
			empresa.setCP(Integer.parseInt(request.getParameter("CP")));
			empresa.setTelefono(Integer.parseInt(request.getParameter("Telefono")));
			empresa.setPoblacion(request.getParameter("Poblacion"));
			empresa.setProvincia(request.getParameter("Provincia"));
			empresa.setDireccion(request.getParameter("Direccion"));

			Log.log.info("Insertamos la empresa");
			dao.addempresa(empresa);

			request.setAttribute("empresas", dao.getAllempresas());
			RequestDispatcher view = request.getRequestDispatcher(LIST_ENTERPRISE);
			view.forward(request, response);
		}
		else if (request.getParameter("btnUpdate") != null) {
			
			Log.log.info("Parametro valor UPDATE");

			empresa.setId(Integer.parseInt(request.getParameter("Id")));
			empresa.setNombre(request.getParameter("Nombre"));
			empresa.setCP(Integer.parseInt(request.getParameter("CP")));
			empresa.setTelefono(Integer.parseInt(request.getParameter("Telefono")));
			empresa.setPoblacion(request.getParameter("Poblacion"));
			empresa.setProvincia(request.getParameter("Provincia"));
			empresa.setDireccion(request.getParameter("Direccion"));

			dao.updateempresa(empresa);

			request.setAttribute("empresas", dao.getAllempresas());
			RequestDispatcher view = request.getRequestDispatcher(LIST_ENTERPRISE);
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
