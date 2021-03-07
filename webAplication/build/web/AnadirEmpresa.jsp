<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Empresa'%>



<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Empresa</title>
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
<h1>Agregar Empresa</h1>
	<form method="Post" action=EmpresaController>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Nombre</label> <input type="text" class="form-control" name="Nombre" required id="Nombre" placeholder="Nombre">
			</div>
			<div class="form-group col-md-6">
				<label >Direccion</label> <input type="text" class="form-control" name="Direccion" required id="Direccion" placeholder="Direccion">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label >Código Postal</label> <input type="text" class="form-control" name="CP" required id="CP" placeholder="Codigo Postal" pattern="[0-9]{5}" title="Deben ser 5 números (si son 4 añada un 0 al principio)">
			</div>
			<div class="form-group col-md-6">
				<label >Población</label> <input type="text" class="form-control" name="Poblacion" required id="Poblacion" placeholder="Poblacion">
			</div>
		</div>


		<div class="form-row">
			<div class="form-group col-md-6">
				<label >Provincia</label> <input type="text" class="form-control" name="Provincia" required id="Provincia" placeholder="Provincia" >
			</div>


			<div class="form-group col-md-6">
				<label>Teléfono</label> <input type="text" class="form-control" name="Telefono" required id="Telefono" placeholder="Telefono" pattern=[6-7-9]{1}[0-9]{8} title="Deben ser 9 números y que sea un teléfono válido">
			</div>
		</div>

		<div class="form-group col-md-6">
			<label>Id</label> <input type="text" class="form-control" name="Id" required id="Id" placeholder="Id" pattern=[0-9]{1,} title="Debe ser un número">
		</div>
		

		<button type="submit" name="anadir" class="btn btn-primary">Añadir Empresa</button>
		<button type="button" class="btn btn-danger" onclick="location.href='listaEmpresas.jsp';">Cancelar</button>
			
	</form>
	
	<%
	 HttpSession sesion = request.getSession();
     String DNI_sesion = (String) sesion.getAttribute("DNI");
     if (DNI_sesion==null){
			response.sendRedirect("index.jsp");
		}
	Empresa empresa = new Empresa();

	if (request.getAttribute("fail") != null) {
		out.print("<script>alert('Datos erroneos!');</script>");
	}

	else if (request.getAttribute("empresas") != null) {

	
		request.getAttribute("empresas");

		
		String Nombre = empresa.getNombre();
		String Direccion = empresa.getDireccion();
		Integer Telefono = empresa.getTelefono();
		Integer CP = empresa.getCP();
		String Provincia = empresa.getProvincia();
		Integer Id = empresa.getId();
		String Poblacion = empresa.getPoblacion();

		sesion.setAttribute("NombreEmpresa", Nombre);
		sesion.setAttribute("Direccion", Direccion);
		sesion.setAttribute("CP", CP);
		sesion.setAttribute("Telefono", Telefono);
		sesion.setAttribute("Provincia", Provincia);
		sesion.setAttribute("Id", Id);
		sesion.setAttribute("Poblacion", Poblacion);

		response.sendRedirect("listaEmpresas.jsp");
	}
	%>

</body>
</html>