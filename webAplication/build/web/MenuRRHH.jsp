<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Trabajador'%>
<%@page import='main.java.util.TrabajadorDao'%>
<%@page session= "true" %>


 <!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu RRHH</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    
    
    <style>
        .trans{
      background-color: white;
      display: flex;
      justify-content: center;
    }
    
    
    tr{
    weigh:"100px";
    }
    h2{
     text-align: center;

 

    }
    
    h3{
    font-size: "1%";
    }
    
    </style>
    </head> 
    <body>
    <div class="jumbotron">
    
    <%
		
			String DNI = (String) session.getAttribute("DNI_RRHH");
			if (DNI==null){
				response.sendRedirect("index.jsp");
			}
			
			TrabajadorDao dao = new TrabajadorDao();
			Trabajador trabajador = new Trabajador();
			trabajador = dao.gettrabajadorByDNI(DNI);
			session.setAttribute("DNI", DNI);
			
		%>

		<h1 class="display-4">
			Bienvenid@, 
			<%= 
			trabajador.getNombre()
		%> 
 </h1>
  <p class="lead">Bienvenido a su página principal de trabajo. Ha accedido a  la vista del personal de Recursos Humanos. Debajo se muestran las distintas opciones que puede seleccionar.</p>
  <hr class="my-4">
  <p>No dude en preguntar a la dirección en caso de tener algún problema.</p>

 <p align="right"> <button type="button" class="btn btn-info" onclick="location.href='LogInController?action=out';">Cerrar Sesión</button> </p>

 

</div>
        <table class=trans cellpadding="25px"> 
    
                <tr > 
                    <h2>Menú</h2> 
                </tr> 
                <tr>
                    <td> 
                    <h4>
                    Revisar Solicitudes
                    </h4>
                    <p>
                    Para revisar las peticiones de vacaciones, día libre u horas libres que tiene pendientes de aceptar o denegar, pincha en el siguiente botón.
                    </p>
                    <button type="button" class="btn btn-primary" onclick="location.href='PeticionController?action=listPeticiones';">Revisar Solicitudes</button></td>
                    
                     
                     
                     <td>
                     <h4>
                     Ver todas las empresas
                    </h4> 
                    <p>
                    Para visualizar todas las empresas que mantiene, pincha en el siguiente botón.
                    </p>
                     <button type="button" class="btn btn-primary" onclick="location.href='EmpresaController?action=listaEmpresas';"> Todas las empresas</button></td>
   
 
                      <td>
                      <h4>
                      Generar Informe
                       </h4>
                       <p>
                    Puede generar un informe clicando en el siguiente botón.
                    </p>
                      <button type="button" class="btn btn-primary" onclick="location.href='GenerarInforme.jsp';">Generar informes</button></td>

                       
                  </tr> 
                  <tr>
                  <td>
                  <h4>
                      Ver todos los proyectos
                       </h4>
                       <p>
                    Para visualizar todos los proyectos, pincha en el siguiente botón.
                    </p>
                      <button type="button" class="btn btn-primary" onclick="location.href='ProyectoController?action=listProyectoA';">Todos los proyectos</button></td>
				<td>
                  <h4>
                      Ver todos los empleados
                       </h4>
                       <p>
                    Para visualizar todos los empleados, pincha en el siguiente botón.
                    </p>
                      <button type="button" class="btn btn-primary" onclick="location.href='EmpleadoController?action=listEmpleado';">Todos los empleados</button></td>
						
 
 
           
                  <table> 
                  </tr>   
            </tbody> 
        </table> 
    </body>
</html>
 