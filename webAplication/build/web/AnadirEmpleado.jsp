<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Empleado'%>
<%@page import='main.java.model.Trabajador'%>
<%@page import='main.java.util.*'%>
<%@page import='main.java.model.Empresa'%>
<%@page import='java.util.ArrayList'%>
<%@page import='java.util.List'%>

<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Empleado</title>
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
	<h1>Añadir Empleado</h1>

	<form method="Post" action=EmpleadoController>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Nombre</label> <input type="text" name="Nombre" required class="form-control" id="Nombre" placeholder="Nombre">
			</div>
			<div class="form-group col-md-6">
				<label>Apellido</label> <input type="text" name="Apellido" required class="form-control" id="Apellido" placeholder="Apellido">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Teléfono</label> <input type="text" name="Telefono" required class="form-control" id="Telefono" placeholder="Teléfono" pattern=[6-7-9]{1}[0-9]{8} title="Deben ser 9 números y que sea un teléfono válido">
			</div>
			<div class="form-group col-md-6">
				<label>Contraseña</label> <input type="text" name="Contrasena" required class="form-control" id="Contrasena" placeholder="Contraseña" pattern=".{4,}" title="Mínimo 4 caracteres">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>DNI</label> <input class="form-control"  id="DNI" name="DNI" required class="DNI" placeholder="DNI" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra">
			</div>


			<div class="form-group col-md-6">
				<label>Id_Empresa</label> 
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

		<div class="form-group col-md-6">
			<label>Puesto</label> <input type="text" class="form-control" value="Empleado" readonly="readonly" id="Puesto" placeholder="">
		</div>

		<button type="submit" name="anadir" class="btn btn-primary">Añadir</button>
		<button type="button" class="btn btn-danger" onclick="location.href='listaEmpleados.jsp';">Cancelar</button>

	</form>

	<%

	 HttpSession sesion = request.getSession();
     String DNI_sesion = (String) sesion.getAttribute("DNI");
     if (DNI_sesion==null){
			response.sendRedirect("index.jsp");
		}
	Empleado empleado = new Empleado();
	Trabajador trabajador = new Trabajador();

	if (request.getAttribute("fail") != null) {
		out.print("<script>alert('Datos erroneos!');</script>");
	}

	else if (request.getAttribute("empleados") != null) {

		request.getAttribute("empleados");

		String DNI = trabajador.getDNI();
		String Nombre = trabajador.getNombre();
		String Apellido = trabajador.getApellido();
		Integer Telefono = trabajador.getTelefono();
		String Contrasena = trabajador.getContrasena();
		Integer Id_Empresa = trabajador.getId_Empresa();

		sesion.setAttribute("DNI", DNI);
		sesion.setAttribute("Nombre", Nombre);
		sesion.setAttribute("Apellido", Apellido);
		sesion.setAttribute("Telefono", Telefono);
		sesion.setAttribute("Contrasena", Contrasena);
		sesion.setAttribute("Id_Empresa", Id_Empresa);
		sesion.setAttribute("Puesto", "Empleado");

		response.sendRedirect("ListaEmpleados.jsp");
	}
	%>
</body>
</html>