

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import='main.java.model.Empresa'%>
<%@page import='main.java.util.EmpresaDao'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>

<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Empresas</title>
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
      <a class="nav-item nav-link active" href='EmpleadoController?action=listEmpleado'>Ver Empresas</a>
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
				<th scope="col">Id Empresa</th>
				<th scope="col">Nombre Empresa</th>
				<th scope="col">Teléfono</th>
				<th scope="col">Código Postal</th>
				<th scope="col">Población</th>
				<th scope="col">Provincia</th>
				<th scope="col">Dirección</th>
			</tr>
		</thead>
		<%
			HttpSession sesion = request.getSession();
			String DNI = (String) sesion.getAttribute("DNI");
        	if (DNI==null){
				response.sendRedirect("index.jsp");
			}
		
			EmpresaDao empresadao = new EmpresaDao();
			List<Empresa> lista_empresas = empresadao.getAllempresas();
			Iterator<Empresa> iterador = lista_empresas.iterator();
			Empresa empresa = new Empresa();
			sesion.setAttribute("DNI", DNI);

			while (iterador.hasNext()) {

				empresa = iterador.next();
		%>

		<tbody>
			<tr>
				<td><%=empresa.getId()%></td>
				<td><%=empresa.getNombre()%></td>
				<td><%=empresa.getTelefono()%></td>
				<td><%=empresa.getCP()%></td>
				<td><%=empresa.getPoblacion()%></td>
				<td><%=empresa.getProvincia()%></td>
				<td><%=empresa.getDireccion()%></td>
				<td><button type="submit" class="btn btn-success"
						name="btnModificar"
						onclick="location.href='EmpresaController?action=edit&Id=<%= empresa.getId() %>';">Modificar</button>
					<button type="submit" class="btn btn-danger"
						name="btnEliminar"
						onclick="location.href='EmpresaController?action=delete&Id=<%= empresa.getId() %>';">Eliminar</button>
				</td>
			</tr>

			<%
				}
			%>

		</tbody>
	</table>
	<button type="submit" class="btn btn-primary" name="interfazAnadir" onClick="location.href='EmpresaController?action='">Añadir
		nueva empresa</button>

</body>
</html>
