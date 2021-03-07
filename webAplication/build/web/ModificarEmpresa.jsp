<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Empresa'%>
<%@page import='main.java.util.EmpresaDao'%>

 <!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar empresa</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	</head>
	<body>
	<div>
	
	<%
		HttpSession sesion = request.getSession();
   		String DNI = (String) sesion.getAttribute("DNI");
    	if (DNI==null){
			response.sendRedirect("index.jsp");
		}
	
		Empresa empresa = new Empresa();
		empresa = (Empresa) request.getAttribute("empresa");
	%>
	
	<form method="Post" action=EmpresaController>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Nombre</label> <input type="text" class="form-control" name="Nombre" id="Nombre" value="<%= empresa.getNombre() %>" >
			</div>
			<div class="form-group col-md-6">
				<label >Direccion</label> <input type="text" class="form-control" name="Direccion" id="Direccion" value="<%= empresa.getDireccion() %>">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label >Código Postal</label> <input type="text" class="form-control" name="CP" id="CP" placeholder="Codigo Postal" value="<%= empresa.getCP() %>" pattern="[0-9]{5}" title="Deben ser 5 números (si son 4 añada un 0 al principio)">
			</div>
			<div class="form-group col-md-6">
				<label >Población</label> <input type="text" class="form-control" name="Poblacion" id="Poblacion" placeholder="Poblacion" value="<%= empresa.getPoblacion() %>">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label >Provincia</label> <input type="text" class="form-control" name="Provincia" id="Provincia" placeholder="Provincia" value="<%= empresa.getProvincia() %>">
			</div>

			<div class="form-group col-md-6">
				<label>Teléfono</label> <input type="text" class="form-control" name="Telefono" id="Telefono" placeholder="Telefono" value="<%= empresa.getTelefono() %>" pattern=[6-7-9]{1}[0-9]{8} title="Deben ser 9 números y que sea un teléfono válido">
			</div>
		</div>

		<div class="form-group col-md-6">
			<label>Id</label> <input type="text" class="form-control" name="Id" id="Id" placeholder="Id" value="<%= empresa.getId() %>" readonly>
		</div>
		<button type="submit" class="btn btn-primary" name="btnUpdate" onclick="location.href='EmpresaController?action=';">Actualizar Empresa</button>
		  <button type="button" class="btn btn-danger" onclick="location.href='listaEmpresas.jsp';">Cancelar</button>
	</form>
	</div>

	</body>
	</html>