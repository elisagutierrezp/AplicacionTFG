package main.java.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import static javax.servlet.RequestDispatcher.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.util.Log;

/**
 * Servlet implementation class ErrorHandler
 */
public class ErrorHandler extends HttpServlet {

	/**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws IOException {
    	
    	Log.log.error("Se ha producido un error");    
        resp.setContentType("text/html; charset=utf-8");
        
        try (PrintWriter writer = resp.getWriter()) {
        	
            writer.write("<html><head><title>Error description</title></head><body>");
            writer.write("<h2>Error description</h2>");
            writer.write("<ul>");
            
            Arrays.asList(ERROR_STATUS_CODE, ERROR_EXCEPTION_TYPE,ERROR_MESSAGE)
              .forEach(e ->writer.write("<li>" + e + ":" + req.getAttribute(e) + " </li>"));
            
            writer.write("</ul>");
            writer.write("</html></body>");
            
        }
    }
}