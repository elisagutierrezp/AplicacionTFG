<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Jornada_Proyecto'%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='main.java.util.ProyectoDao'%>
<%@page import='java.util.ArrayList'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Calendar'%>
<%@page import='java.util.Date'%>

<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Jornada</title>
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
	<link href="css/vacaciones.css" rel="stylesheet" />
</head>
<body>
	<h1>Añadir la jornada del proyecto</h1>
	<%
		HttpSession sesion = (HttpSession) request.getSession();
		String DNI = (String) sesion.getAttribute("DNI");
		if (DNI==null){
			response.sendRedirect("index.jsp");
		}
	%>
	<form method="Post" action=ProyectoController>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Fecha de la jornada anterior de trabajo:</label>
				 <br>
				<input class="datepicker" name="Fecha" id="Fecha" type="text" placeholder="Seleccione día" />
			</div>
            <div class="icon-wrap">
                <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z">
                </path>            
            </div>				
			<div class="form-group col-md-6">
				<label>Horas</label> <select class="form-control" name="Horas"
					id="Horas">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
				</select>
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>DNI del empleado</label> <input type="text" readonly
					name="Empleado_trabajador_DNI" class="form-control"
					id="Empleado_trabajador_DNI" placeholder="Empleado_trabajador_DNI"
					value="<%=DNI%>" pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra">

			</div>
			<div class="form-group col-md-6">
				<label>Clave</label> <select class="form-control" id="Clave"
					name="Clave" pattern=[0-9]{1,} title="Debe ser un número">

					<%
						ProyectoDao dao = new ProyectoDao();
						Proyecto proyecto = new Proyecto();
						List<Proyecto> proyectodb = new ArrayList<Proyecto>();
						proyectodb = dao.getTodosProyectos();

						for (int i = 0; i < proyectodb.size(); i++) {
					%>
					<option><%=proyectodb.get(i).getClave()%></option>
					<%
						}
					%>
				</select>
			</div>
		</div>


		<button type="submit" name="addJornada" class="btn btn-primary">Añadir
			jornada</button>
			<button type="button" class="btn btn-danger" onclick="location.href='ProyectosEmpleado.jsp';">Cancelar</button>

	</form>

	<%
		Jornada_Proyecto jornada_proyectos = new Jornada_Proyecto();

		if (request.getAttribute("fail") != null) {
			out.print("<script>alert('Datos erroneos!');</script>");
		}

		else if (request.getAttribute("jornada_proyectos") != null) {

			request.getAttribute("jornada_proyectos");

			String Fecha = jornada_proyectos.getFecha();
			Integer Horas = jornada_proyectos.getHoras();
			String Empleado_trabajador_DNI = jornada_proyectos.getEmpleado_trabajador_DNI();
			Integer Clave = jornada_proyectos.getProyecto_Clave();

			sesion.setAttribute("Fecha", Fecha);
			sesion.setAttribute("Horas", Horas);
			sesion.setAttribute("Empleado_trabajador_DNI", Empleado_trabajador_DNI);
			sesion.setAttribute("Clave", Clave);

			response.sendRedirect("ProyectosEmpleado.jsp");
		}
	%>
	
	<script src="js/extention/choices.js"></script>
    <script src="js/extention/flatpickr.js"></script>
    <script>
      flatpickr(".datepicker",
      {});

    </script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>
</body>
</html>