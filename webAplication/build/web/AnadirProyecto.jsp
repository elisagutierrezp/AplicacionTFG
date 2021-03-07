<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='main.java.model.Empresa'%>
<%@page import='main.java.util.EmpresaDao'%>
<%@page import='java.util.*'%>

<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Proyecto</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
</head>
<body>
	<form method="Post" action=ProyectoController>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Descripcion</label> <input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="Descripcion">
			</div>
			<div class="form-group col-md-6">
				<label> Clave</label> <input type="text" class="form-control" name="clave" id="clave" placeholder="Clave" pattern=[0-9]{1,} title="Debe ser un número">
			</div>

		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Id Empresa</label>
				<select class="form-control" id="Id_Empresa" name="Id_Empresa">
				<%
				
				EmpresaDao dao = new EmpresaDao();
				Empresa empresa = new Empresa();
				List<Empresa> empresadb = new ArrayList<Empresa>();
				empresadb=dao.getAllempresas();
				
				for (int i=0; i<empresadb.size();i++){
					%>
					<option><%= empresadb.get(i).getId()%></option>
					<%
				}
				%>
				</select>
				
				 
			</div>
		</div>

		<button type="submit" name="anadir" class="btn btn-primary">Añadir
			Proyecto</button>
			<button type="button" class="btn btn-danger" onclick="location.href='ProyectosRRHH.jsp';">Cancelar</button>
	</form>

	<%
		HttpSession sesion = request.getSession();
     	String DNI_sesion = (String) sesion.getAttribute("DNI");
     	if (DNI_sesion==null){
			response.sendRedirect("index.jsp");
		}
		Proyecto proyecto = new Proyecto();
	
		if (request.getAttribute("fail") != null) {
			out.print("<script>alert('Datos erroneos!');</script>");
		}
		else if (request.getAttribute("proyectos") != null) {
	
			request.getAttribute("proyectos");
	
			String Descripcion = proyecto.getDescripcion();
			Integer Clave = proyecto.getClave();
			Integer Id_empresa = proyecto.getId_Empresa();
	
			sesion.setAttribute("Descripcion", Descripcion);
			sesion.setAttribute("Clave", Clave);
			sesion.setAttribute("Id_Empresa", Id_empresa);
	
			response.sendRedirect("Proyectos.jsp");
	}
	%>


</body>
</html>