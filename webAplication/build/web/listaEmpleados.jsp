<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Trabajador'%>
<%@page import='main.java.util.TrabajadorDao'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>


<!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
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
      <a class="nav-item nav-link active" href='PeticionController?action=listPeticiones'>Ver Peticiones</a>
       <a class="nav-item nav-link active" href='ProyectoController?action=listProyectoA'>Ver Proyectos</a>
      <a class="nav-item nav-link active" href='GenerarInforme.jsp'>Generar Informe</a>
    </div>
  </div>

</nav>

    </head> 
    <body> 
        
        <table class="table table-striped"> 
            <thead> 
                <tr> 
                    <th scope="col">DNI</th>
                    <th scope="col">Nombre</th>  
                    <th scope="col">Apellido</th> 
                    <th scope="col">Telefono</th> 
                    <th scope="col">Id_Empresa</th> 
                    <th scope="col">Puesto</th> 
                    
                </tr> 
            </thead> 
            <%
            
            HttpSession sesion = request.getSession();
            String DNI = (String) sesion.getAttribute("DNI");
            if (DNI==null){
				response.sendRedirect("index.jsp");
			}
			
            sesion.setAttribute("DNI", DNI);
            TrabajadorDao trabajadordao = new TrabajadorDao();
         	List<Trabajador>lista_trabajadores=trabajadordao.getAlltrabajadores();
          	Iterator <Trabajador> iterador = lista_trabajadores.iterator();
          	Trabajador trabajador = new Trabajador();
          	
          	while (iterador.hasNext()){
          		
          		trabajador= iterador.next();
          		if (trabajador.getPuesto().equals("Empleado")){
            %>
            
            <tbody> 
                    <tr> 
                        <td><%= trabajador.getDNI() %></td> 
                        <td><%= trabajador.getNombre() %></td> 
                        <td><%= trabajador.getApellido() %></td> 
                        <td><%= trabajador.getTelefono() %></td> 
                        <td><%= trabajador.getId_Empresa() %></td> 
                        <td><%= trabajador.getPuesto() %></td> 
                       
                   
                   <td><button type="submit" class="btn btn-success"
						name="btnModificar"
						onclick="location.href='EmpleadoController?action=edit&DNI=<%= trabajador.getDNI() %>';">Modificar</button>
						<button type="submit" class="btn btn-danger" name="btnEliminar"
						onclick="location.href='EmpleadoController?action=delete&DNI=<%= trabajador.getDNI() %>';">Eliminar</button>
					<% } %>
				</td>
                    </tr> 
                    
                    <% } %>
             
            </tbody> 
        </table> 
          <button type="submit" class="btn btn-primary" name="interfazAnadir" onClick="location.href='EmpleadoController?action='">
                Añadir nuevo empleado
         </button>
        
    </body>
</html>