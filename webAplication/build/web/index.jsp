
<%@ page language='java' contentType='text/html;charset=iso-8859-1'%>
<%@page import='main.java.model.Empleado'%>
<%@page import='main.java.model.RRHH'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<meta name="description" content="Formulario de login">
<meta name="author" content="Parzibyte">

<title>Iniciar sesión</title>


<link href="./css/bootstrap.min.css" rel="stylesheet">

<style>	
		 .titulo {
			 border: medium double black;
			 background-color: lightgrey;
			 text-align: center;
			 font-family: "HelveticaNeue Condensed", monospace;
			 font-size: xx-large;
			 justify-content: center;
		 }

		 
	main{
  text-align: center;
  
}
body{
background-image: url("images/fondoinicio.jpg");
margin-top: 30px;
text-align: center;
justify-content: center;
}
.abs-center {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}


.form {
  width: 600px;
}
	 </style>
</head>
<body >
    <main role="main" class="container my-auto">
    <div class="row">
        <div id="login" class="col-lg-4 offset-lg-4 col-md-6 offset-md-3col-12">

	
			<p class="titulo" >Iniciar Sesión<p>
            <br>
           
            <form name="InicioSesion" method="Post" class="form" action=LogInController>
                <div class="align-self-center" class="form-group">

                    <label for="DNI"></label>


                    <input id="DNI" name="DNI" type="text" class="form-control" required  placeholder="Introduzca su DNI"
                    pattern="[0-9]{8}[A-Za-z]{1}" title="Debe poner 8 números y una letra"> 

                </div>

                <div class="align-self-center" class="form-group">

                    <label for="Contrasena"></label>
                    <input id="Contrasena" name="Contrasena" class="form-control" required type="password" placeholder="Contraseña">


                </div>
                <br>
                <br>
                <button type="submit" class="btn btn-primary"  name="btnLogIn" onclick="validarFormulario()">
                Entrar
                </button>
                <br>

           </form>
           
            <%
            	HttpSession sesion1 = request.getSession(true);
               String idsesion1 =sesion1.getId();
                HttpSession sesion2 = request.getSession(true);
				
            	Empleado empleado =  new Empleado();
             	RRHH rrhh = new RRHH();

                if(request.getAttribute("fail")!=null){
                    out.print("<script>alert('Usuario o contrasena erroneos!');</script>");
                }

                if(request.getAttribute("empleado")!=null){
                    request.getAttribute("empleado");

                    String DNI="";
                    String contrasena="";

                    DNI = empleado.getDNI();
                    contrasena = empleado.getContrasena();

                    sesion1.setAttribute("Empleado_DNI", DNI);
                    sesion1.setAttribute("Contrasena",contrasena);

                    response.sendRedirect("MenuEmpleado.jsp"); 
                }

                if(request.getAttribute("rrhh")!=null){
                    request.getAttribute("rrhh");
                    String DNI="";
                    String contrasena="";

                    DNI = rrhh.getDNI();
                    contrasena = rrhh.getContrasena();
                    sesion2.setAttribute("Trabajador_DNI", DNI);
                    sesion2.setAttribute("Contrasena",contrasena);

                    response.sendRedirect("MenuRRHH.jsp"); 
                } 
        %>

        </div>

    </div>
    
    </main>
</body>
</html>