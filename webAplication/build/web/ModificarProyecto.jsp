<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='main.java.util.ProyectoDao'%>


 <!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show All Users</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	</head>
	<body>
	<div>
	
	<%
		Proyecto proyecto = new Proyecto();
		proyecto= (Proyecto) request.getAttribute("proyectos");
	 	HttpSession sesion = request.getSession();
     	String DNI = (String) sesion.getAttribute("DNI");
     	if (DNI==null){
			response.sendRedirect("index.jsp");
		}	
	%>
	
	<form method="Post" action=ProyectoController>
	
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Descripción</label>
      <input type="text" class="form-control" id="Descripcion" name="Descripcion" value="<%= proyecto.getDescripcion() %>" >
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Clave</label>
      <input type="text" class="form-control" id="Clave" name="Clave" value="<%= proyecto.getClave() %>" readonly>
    </div>
  </div>
  
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="inputAddress">Id Empresa</label>
    <input type="text" class="form-control" id="Id_Empresa" name="Id_Empresa" value="<%= proyecto.getId_Empresa() %>" readonly>
  </div>
  </div>
    
  <button type="submit" class="btn btn-primary" name="btnUpdate" onclick="location.href='ProyectoController?action=';">Actualizar Proyecto</button>
    <button type="button" class="btn btn-danger" onclick="location.href='ProyectosRRHH.jsp';">Cancelar</button>
</form>
</div>

	</body>
	</html>