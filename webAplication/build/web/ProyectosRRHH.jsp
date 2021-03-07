
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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
		aria-expanded="true" aria-label="Toggle navigation">
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
				<th scope="col">Descripción</th>
				<th scope="col">Clave</th>
				<th scope="col">Empresa Id</th>


			</tr>
		</thead>
		<%
		HttpSession sesion = request.getSession();
		String DNI = (String) sesion.getAttribute("DNI");
        if (DNI==null){
			response.sendRedirect("index.jsp");
		}
        
        sesion.setAttribute("DNI", DNI);
			ProyectoDao proyectodao = new ProyectoDao();

			List<Proyecto> proyectos = proyectodao.getTodosProyectos();

			Iterator<Proyecto> iterador = proyectos.iterator();

			Proyecto proyecto = new Proyecto();

			while (iterador.hasNext()) {
				proyecto = iterador.next();
		%>

		<tbody>
			<tr>
				<td><%=proyecto.getDescripcion()%></td>
				<td><%=proyecto.getClave()%></td>
				<td><%=proyecto.getId_Empresa()%></td>
				<td><button type="submit" class="btn btn-success"
						name="btnModificar"
						onclick="location.href='ProyectoController?action=edit&clave=<%= proyecto.getClave() %>';">Modificar</button>
					<button type="submit" class="btn btn-danger"
						name="btnModificar"
						onclick="location.href='ProyectoController?action=delete&clave=<%= proyecto.getClave() %>';">Eliminar</button>

				</td>
			</tr>

			<%
				}
			%>

		</tbody>
	</table>
	<button type="submit" class="btn btn-primary" name="interfazAnadir"
		onClick="location.href='ProyectoController?action='">Añadir
		nuevo proyecto</button>

</body>
</html>