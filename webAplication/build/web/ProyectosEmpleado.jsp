
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='main.java.util.ProyectoDao'%>
<%@page import='main.java.util.Jornada_Proyecto_Dao'%>
<%@page import='main.java.model.Jornada_Proyecto'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>
<%@page import='java.util.ArrayList'%>

<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROYECTOS</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<style>
.navbar-nav {
	float: none;
	margin: 0 auto;
	text-align: right;
}
</style>

	<% 
		HttpSession sesion = (HttpSession) request.getSession();
		String DNI = (String) sesion.getAttribute("DNI");
		if (DNI==null){
			response.sendRedirect("index.jsp");
		}
	%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
		aria-expanded="true" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		<div class="navbar-nav">
			<a class="nav-item nav-link active" href='MenuEmpleado.jsp'>Página
				Principal <span class="sr-only"></span>
			</a> <a class="nav-item nav-link active"
				href='ProyectoController?action=listProyectoB'>Mis Proyectos</a> <a
				class="nav-item nav-link active" href="VacacionesEmpleado.jsp">Solicitar vacaciones</a>
			<a class="nav-item nav-link active" href="HorasLibresEmpleado.jsp">Solicitar horas
				libres</a> <a class="nav-item nav-link active" href="#">Calendario</a> 
				<a class="nav-item nav-link active" href="PeticionController?action=anadirVacaciones&DNI<%=DNI%>">Ver Calendario</a> 
				
		</div>
	</div>
</nav>
</head>
<body>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Descripción</th>
				<th scope="col">Clave</th>
				<th scope="col">Empresa Id</th>
				<th scope="col">Horas</th>
				<th scope="col">DNI</th>

			</tr>
		</thead>
		<%
			
		ProyectoDao proyectodao = new ProyectoDao();

		List<ArrayList> lista_proyectos = proyectodao.getAllProyectosJornada(DNI);

		ArrayList<Proyecto> proyectos = lista_proyectos.get(0);
		ArrayList<Jornada_Proyecto> jornadas = lista_proyectos.get(1);

		Iterator<Proyecto> iterador = proyectos.iterator();
		Iterator<Jornada_Proyecto> iterador2 = jornadas.iterator();

		Proyecto proyecto = new Proyecto();
		Jornada_Proyecto jornada = new Jornada_Proyecto();

		while (iterador.hasNext()) {

			proyecto = iterador.next();
			jornada = iterador2.next();
		%>

		<tbody>
			<tr>
				<td><%=proyecto.getDescripcion()%></td>
				<td><%=proyecto.getClave()%></td>
				<td><%=proyecto.getId_Empresa()%></td>
				<td><%=jornada.getHoras()%></td>
				<td><%=jornada.getEmpleado_trabajador_DNI()%></td>

			</tr>

			<%
				}
			%>

		</tbody>
	</table>
	<button type="submit" class="btn btn-primary" name="interfazAnadir"
		onClick="location.href='ProyectoController?action=insertJornada'">
		Añadir nueva jornada</button>

</body>
</html>