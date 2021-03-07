<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import='main.java.model.Peticion'%>
<%@page import='main.java.util.PeticionDao'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Peticiones</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        
           <style>
        
       .navbar-nav {
    float:none;
    margin:0 auto;
    text-align: right;
	}

		
        </style>
        
       <nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="true" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
     <div class="navbar-nav">
      <a class="nav-item nav-link active" href='MenuRRHH.jsp' >Página Principal <span class="sr-only"></span></a>
      <a class="nav-item nav-link active" href='EmpresaController?action=listaEmpresas'>Ver Empresas</a>
      <a class="nav-item nav-link active" href='EmpleadoController?action=listEmpleado'>Ver Empleados</a>
       <a class="nav-item nav-link active" href='ProyectoController?action=listProyectoA'>Ver Proyectos</a>
      <a class="nav-item nav-link active" href='GenerarInforme.jsp'>Generar Informe</a>
    </div>
  </div>

</nav> 
    </head> 
    <body> 
        <table class="table table-striped" > 
            <thead> 
                <tr> 
                    <th scope="col">Fecha Inicio</th>
                    <th scope="col">Fecha Fin</th>  
                    <th scope="col">Motivo</th> 
                    <th scope="col">Empleado DNI</th> 
                    <th scope="col">Id Peticion</th> 
                    <th scope="col">Estado</th> 
                
                </tr> 
            </thead> 
            <%
            
            HttpSession sesion = request.getSession();
            String DNI = (String) sesion.getAttribute("DNI");
            if (DNI==null){
				response.sendRedirect("index.jsp");
			}
			
            PeticionDao peticiondao = new PeticionDao();
         	List<Peticion>lista_peticions=peticiondao.getAllpeticiones();
          	Iterator <Peticion> iterador = lista_peticions.iterator();
          	Peticion peticion = new Peticion();
          	
          	while (iterador.hasNext()){
          		
          		peticion= iterador.next();
          	
            %>
            
            <tbody > 
                    <tr> 
                        <td><%= peticion.getFecha_Inicio()%></td> 
                        <td><%= peticion.getFecha_Fin()%></td> 
                        <td><%= peticion.getMotivo()%></td>
                        <td><%= peticion.getEmpleado_trabajador_DNI()%></td> 
                        <td><%= peticion.getId_Peticion()%></td>
                        
                        <% 
                        
                        if ( peticion.isAceptada()==-1){
                        	%>
                        
                        <td><button type="submit" class="btn btn-success" name="btnAceptar" onclick="location.href='PeticionController?action=aceptar&id=<%= peticion.getId_Peticion() %>';">Aceptar</button>
 						<button type="submit" class="btn btn-danger" name="btnRechazar" onclick="location.href='PeticionController?action=rechazar&id=<%= peticion.getId_Peticion() %>';">Rechazar</button>
 						</td> 
 						
 						<%} 
                        else{
                        	if (peticion.isAceptada()==1) {
                        		%>
                        		<td>Aceptada</td> 
                        		<%
                        	}
                        	else{
                        		%>
                        		<td>Rechazada</td> 
                        		<%
                        	}
 						%>                            	
                    </tr> 
                    
                    <% } }%>
             
            </tbody> 
        </table>
     
    </body>
</html>
