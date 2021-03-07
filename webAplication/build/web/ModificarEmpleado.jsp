<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Trabajador'%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='main.java.model.Empresa'%>
<%@page import='main.java.util.EmpresaDao'%>
<%@page import='java.util.*'%>

 <!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar empleado</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	</head>
	<body>
	<div>
	
	<%
		Trabajador trabajador = new Trabajador();
		trabajador = (Trabajador) request.getAttribute("empleado");
	  	HttpSession sesion = request.getSession();
      	String DNI = (String) sesion.getAttribute("DNI");
      	if (DNI==null){
			response.sendRedirect("index.jsp");
		}
	%>

<form method="Post" action=EmpleadoController>
	
  <div class="form-row">
    <div class="form-group col-md-6">
      <label >Nombre</label> <input type="text" class="form-control" id="Nombre" name="Nombre" required placeholder="Nombre" value="<%= trabajador.getNombre() %>">
    </div>
    <div class="form-group col-md-6"> <label>Apellido</label> <input type="text" class="form-control" id="Apellido" name ="Apellido" required placeholder="Apellido" value="<%= trabajador.getApellido() %>">
    </div>
  </div>
  
  <div class="form-row">
  <div class="form-group col-md-6">
    <label >Teléfono</label> <input type="text" class="form-control" id="Telefono" name ="Telefono" required placeholder="Telefono" value="<%= trabajador.getTelefono() %>" pattern=[6-7-9]{1}[0-9]{8} title="Deben ser 9 números y que sea un teléfono válido">
  </div>
  <div class="form-group col-md-6">
    <label >Contraseña</label> <input type="text" class="form-control" id="Contrasena" name="Contrasena" required placeholder="Contrasena" value="<%= trabajador.getContrasena() %>" pattern=".{4,}" title="Mínimo 4 caracteres">
  </div>
  </div>
  

  <div class="form-row">
    <div class="form-group col-md-6">
      <label>DNI</label>  <input type="text" class="form-control" name="DNI" required readonly="readonly" id="DNI" placeholder="DNI" value="<%= trabajador.getDNI() %>" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra">
    </div>
   

    <div class="form-group col-md-6">
      <label for="inputCity">Id_Empresa</label>
      <select class="form-control" id="Id_Empresa" name="Id_Empresa" value="<%= trabajador.getId_Empresa() %>">
				<%
				EmpresaDao dao = new EmpresaDao();
				Empresa empresa = new Empresa();
				List<Empresa> empresadb = new ArrayList<Empresa>();
				empresadb=dao.getAllempresas();
				%>
				<option><%= trabajador.getId_Empresa()%></option>
				<%
				for (int i=0; i<empresadb.size();i++){
					if (empresadb.get(i).getId() != trabajador.getId_Empresa()){
					%>
					<option><%= empresadb.get(i).getId()%></option>
					<%
				}}
				%>
				</select>
      
      </div>
    </div>
    
  <button type="submit" class="btn btn-primary" name="btnUpdate" onclick="location.href='EmpleadoController?action=';">Modificar Empleado</button>
  <button type="button" class="btn btn-danger" onclick="location.href='listaEmpleados.jsp';">Cancelar</button>
</form>
</div>	
</body>
	</html>