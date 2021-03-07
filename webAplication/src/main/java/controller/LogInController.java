package main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.model.Empleado;
import main.java.model.RRHH;
import main.java.util.*;
import main.java.util.Log;

/**
 * Servlet implementation class LogInController
 */
public class LogInController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/empleado.jsp";
    private static String LIST_USER = "/listEmpleado.jsp";
    private LogInDao dao;
    private Log log;
    public LogInController() {
        super();
        dao = new LogInDao();
        
        
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String action = request.getParameter("action");    
        HttpSession sesion = request.getSession();
     
        if(action.equalsIgnoreCase("out")){
        	sesion.removeAttribute("DNI");
        	sesion.invalidate();
            response.sendRedirect("index.jsp");
        }
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
        
        HttpSession session= request.getSession(true);
        String DNI="";
        String contra="";
        Empleado empleado = new Empleado();
        RRHH rrhh = new RRHH();

        //Al pulsar btnLogIn obtiene los valores del formulario
        if(request.getParameter("btnLogIn")!=null){
        	
            DNI =  request.getParameter("DNI");
            contra = request.getParameter("Contrasena");  
            empleado= dao.getempleadoByDNIContra(DNI, contra);

            session.setAttribute("DNI",DNI);
            //Comprueba su rol y si coincide con la BBDD
            if(empleado.getTrabajador_DNI()==null){
            	Log.log.info("fail No hay acceso empleado");
            	
            	rrhh= dao.getrrhhByDNIContra(DNI, contra);
            	
            	if (rrhh.getTrabajador_DNI()!=null) {
            		Log.log.info("hay acceso rrhh!");
                	request.setAttribute("rrhh", rrhh);                  
                    request.getRequestDispatcher("index.jsp").forward(request, response); 
                    session.setAttribute("DNI_RRHH",DNI);
            	}
            	else {
            		request.setAttribute("fail","No hay acceso!");
            		request.getRequestDispatcher("index.jsp").forward(request, response); 
            	}
            }
            else if (empleado.getTrabajador_DNI()!=null){
            	Log.log.info("hay acceso empleado!");
            	request.setAttribute("empleado", empleado);                  
                request.getRequestDispatcher("index.jsp").forward(request, response); 
                session.setAttribute("DNI_Empleado",DNI);
            } 
        }
        
    }
           
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}