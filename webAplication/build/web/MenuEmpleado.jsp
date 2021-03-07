<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Trabajador'%>
<%@page import='main.java.util.TrabajadorDao'%>
<%@page import='main.java.util.FichajeDao'%>
<%@page import='main.java.util.PeticionDao'%>

<!DOCTYPE html>

<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Empleado</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<style>
.trans {
	background-color: white;
	display: flex;
	justify-content: center;
}

table {
	text-align: center;
	
}

tr {
	weigh: "100px";
}

h2 {
	text-align: center;
}

h3 {
	font-size: "1%";
}
</style>
</head>
<body>

	<div class="jumbotron">
		<%
			HttpSession sesion = request.getSession();
			String DNI = (String) sesion.getAttribute("DNI_Empleado");
			System.out.println(sesion.getId());
			
			if (DNI==null){
				response.sendRedirect("index.jsp");
			}
			
			

			TrabajadorDao dao = new TrabajadorDao();
			FichajeDao daof = new FichajeDao();
			PeticionDao daop = new PeticionDao();
			Trabajador trabajador = new Trabajador();
			trabajador = dao.gettrabajadorByDNI(DNI);
			int leidos = daop.getLeidos(DNI);
			sesion.setAttribute("haFichado", daof.haFichado(DNI));
			sesion.setAttribute("DNI", DNI);
			
		%>

		<h1 class="display-4">
			Bienvenid@,
			<%=trabajador.getNombre()%>
		</h1>
		<p class="lead">Bienvenido a su página principal de trabajo. Ha accedido a la vista como empleado.
			Debajo se muestran las distintas opciones que puede seleccionar.</p>
		<hr class="my-4">
		<p>No dude en preguntar a la dirección en caso de tener algún
			problema.</p>
			<p align="right"> <button type="button" class="btn btn-info" onclick="location.href='LogInController?action=out';">Cerrar Sesión</button> </p>


	</div>


	<table class=trans cellpadding="10px">

		<tr>
			<h2>Menú</h2>
		</tr>
		<tr>
			<td>
				<h4>Mis Proyectos</h4>
				<p>Clique aquí para ver sus proyectos asignados.</p>
				<button type="button" class="btn btn-primary"
					onclick="location.href='ProyectoController?action=listProyectoB';">Mis
					proyectos</button>
			</td>

			<td>
				<h4>Solicitar Días Libres</h4>
				<p>Si desea solicitar días libres o un único día libre, clique
					aquí.</p>
				<button type="button" class="btn btn-primary"
					onclick="location.href='VacacionesEmpleado.jsp'">Vacaciones</button>
			</td>

			<td>
				<h4>Solicitar Horas Libres</h4>
				<p>Si por algún motivo no acudirá al trabajo por un periodo de
					horas, clique aquí.</p>
				<button type="button" class="btn btn-primary"
					onclick="location.href='HorasLibresEmpleado.jsp'">Horas libres</button>
			</td>

			<td>
				<h4>Ver Calendario</h4>
				<p>Para visualizar su calendario de vacaciones, clique aquí.</p>
				<button type="button" class="btn btn-primary"
					onclick="location.href='PeticionController?action=anadirVacaciones&DNI=<%=DNI%>';">Calendario</button>
			</td>

			<td>
				<h4>Mis Solicitudes</h4>
				<p>Puede visualizar sus solicitudes generadas clicando aquí.</p>
				<button type="button" class="btn btn-primary"
					onclick="location.href='PeticionController?action=listpeticionesbydni&DNI=<%=DNI%>';">Solicitudes</button>
			</td>



		</tr>


		<tr>
			<td></td>
			<td></td>
			<td>
				<h4>Fichaje</h4>
				<p>Deberá fichar una vez al inicio de la jornada y otra vez al
					finalizar la jornada.</p> <%
			 	Boolean alerta1 = false;
			 	Boolean alerta2 = false;
	if 	((Boolean)sesion.getAttribute("haFichado")==true && request.getAttribute("iniciado") == null )	{	
		 %>
		<button type="button" class="btn btn-success" disabled
				name="anadirInicioJornada"
				onclick="location.href='FichajeController?action=anadirInicioJornada&DNI=<%=DNI%>';">Fichar
				Entrada</button>

			<button type="button" class="btn btn-danger" disabled
				name="anadirFinJornada"
				onclick="location.href='FichajeController?action=anadirFinJornada&DNI=<%=DNI%>';">Fichar
				Salida</button><% 
	} 
	else if (request.getAttribute("iniciado") == null ) { //Viene desde el index
 %>

				<button type="button" class="btn btn-success"
					name="anadirInicioJornada"
					onclick="location.href='FichajeController?action=anadirInicioJornada&DNI=<%=DNI%>';">Fichar
					Entrada</button>

				<button type="button" class="btn btn-danger" disabled
					name="anadirFinJornada"
					onclick="location.href='FichajeController?action=anadirFinJornada&DNI=<%=DNI%>';">Fichar
					Salida</button> <%
 	} else if ((Integer) request.getAttribute("iniciado") != 0  ) { //Ha fichado entrada o salida
 		
 		if ((Integer) request.getAttribute("finalizado") == 0) { //Ha fichado entrada pero no salida
 			
 %>
				<div class="alert alert-success alert-dismissible">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Bien!</strong> Se ha guardado su fichaje de entrada!
					Acuérdate de fichar al salir.
				</div> <%
 	}
 		 %>

				<button type="button" class="btn btn-success" disabled
					name="anadirInicioJornada"
					onclick="location.href='FichajeController?action=anadirInicioJornada&DNI=<%=DNI%>';">Fichar
					Entrada</button>

				<button type="button" class="btn btn-danger" name="anadirFinJornada"
					onclick="location.href='FichajeController?action=anadirFinJornada&DNI=<%=DNI%>';">Fichar
					Salida</button> <%
					
								
 	} 
 	
 	else if ( (Integer) request.getAttribute("finalizado") != 0 ) { //Ha fichado salida y entrada
 		
 		%>
				<div class="alert alert-success alert-dismissible">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Bien!</strong> Se ha guardado su fichaje de salida!
				</div>
				<button type="button" class="btn btn-success" disabled
					name="anadirInicioJornada"
					onclick="location.href='FichajeController?action=anadirInicioJornada&DNI=<%=DNI%>';">Fichar
					Entrada</button>

				<button type="button" class="btn btn-danger" name="anadirFinJornada" disabled
					onclick="location.href='FichajeController?action=anadirFinJornada&DNI=<%=DNI%>';">Fichar
					Salida</button><%
 	}
 	%>
			</td>

		</tr>
	</table>

	<%
		
		if (request.getAttribute("peticiones") != null) {
			out.print("<script>alert('Peticion enviada para: " + DNI + "');</script>");
		}
		if (request.getAttribute("peticionesh") != null) {
			out.print("<script>alert('Peticion enviada para: " + DNI + "' );</script>");
		}
		if (leidos!=0){
			out.print("<script>alert('Tienes " + leidos + " solicitudes sin leer.' );</script>");
		}
	%>

</body>
</html>
