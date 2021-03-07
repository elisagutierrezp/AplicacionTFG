<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.model.Empresa'%>
<%@page import='main.java.util.EmpresaDao'%>
<%@page import='main.java.util.EmpleadoDao'%>
<%@page import='main.java.model.Empleado'%>
<%@page import='main.java.util.ProyectoDao'%>
<%@page import='main.java.model.Proyecto'%>
<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>


<!DOCTYPE html>
<html lang="es-es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generar Informe</title>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="author" content="colorlib.com">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700"
	rel="stylesheet" />
<link href="css/main.css" rel="stylesheet" />
<link href="css/vacaciones1.css" rel="stylesheet" />

<style>
.navbar-nav {
	float: none;
	margin: 0 auto;
	text-align: right;
}

h1 {
	text-align: center;
}
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="true" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
     <div class="navbar-nav">
      <a class="nav-item nav-link active" href='MenuRRHH.jsp' >Página Principal <span class="sr-only"></span></a>
      <a class="nav-item nav-link active" href='EmpresaController?action=listaEmpresas'>Ver Empresas</a>
      <a class="nav-item nav-link active" href='EmpleadoController?action=listEmpleado'>Ver Empleados</a>
       <a class="nav-item nav-link active" href='ProyectoController?action=listProyectoA'>Ver Proyectos</a>
      <a class="nav-item nav-link active" href='PeticionController?action=listPeticiones'>Ver Peticiones</a>
    </div>
  </div>

</nav> 
</head>

<body>
	<div class="s008">
		<form name="myform" id="myform" action=InformeController method="Post">
			<div class="inner-form">
				<div class="basic-search">
					<div class="input-field">
						<div class="icon-wrap"></div>
					</div>
				</div>
				<div class="advance-search">
					<span class="desc">Seleccione las propiedades que desea para
						generar el informe de horas</span>
					<div class="row">
						<div class="input-field">
							<div class="input-select">

								<%
									HttpSession sesion = request.getSession();
									String DNI_sesion = (String) sesion.getAttribute("DNI");
									if (DNI_sesion==null){
										response.sendRedirect("index.jsp");
									}
									EmpresaDao empresadao = new EmpresaDao();
									List<Empresa> lista_empresas = empresadao.getAllempresas();

									EmpleadoDao empleadodao = new EmpleadoDao();
									List<Empleado> lista_empleados = empleadodao.getAllempleados();

									ProyectoDao proyectodao = new ProyectoDao();
									List<Proyecto> lista_proyectos = proyectodao.getTodosProyectos();
								%>

								<input type="hidden" id="lista_empresas" required
									value="<%=lista_empresas%>" /></input> <input type="hidden"
									id="lista_empleados" value="<%=lista_empleados%>" /></input> <input
									type="hidden" id="lista_proyectos" value="<%=lista_proyectos%>" /></input>


								<select id="input" name="input" onchange="sacarEmpresas()">
									<option class="option" id="seleccion">Seleccione</option>
									<option class="option">Empresa</option>
									<option class="option">Proyecto</option>
									<option class="option">Empleado</option>

								</select>
							</div>
						</div>

						<div class="input-field">
							<div class="input-select">
								<select name="fechas" id="fechas" onchange="sacarFecha()">
									<option class="option">Seleccione opcion</option>
									<option>Semana</option>
									<option>Mes</option>
									<option>Anual</option>
								</select>
							</div>
						</div>

						<div class="row third">
							<div class="input-field">
								<div class="result-count">

									<div class="group-btn">
										<button class="btn-delete" id="delete">Eliminar</button>
										<button class="btn-search" name="generar" id="generar" disabled>Generar</button>
										
										<% 
											if ((Boolean) request.getAttribute("generado") != null){
												if((Boolean) request.getAttribute("generado") == true){
										%>
											<button class="btn-delete" name="abrir" id="abrir" onClick = "copiarAlPortapapeles()">Copiar ruta</button>
										<% }}%>
										
									</div>
								</div>
							</div>

						</div>
					</div>

					<div id=listas class="input-select"></div>

					<div id=date class="input-select"></div>


					<div class="s002" style="display: none" id="calendario">
						<div class="input-field second-wrap">
							<div class="icon-wrap">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24">
                <path
										d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
              </svg>
							</div>
							<input class="datepicker" name="inicio" id="inicio" type="text"
								placeholder="Seleccione día" required/>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/extention/choices.js"></script>
	<script>
		const customSelects = document.querySelectorAll("select");
		const deleteBtn = document.getElementById('delete')
		const choices = new Choices('select', {
			searchEnabled : false,
			removeItemButton : true,
			itemSelectText : '',
		});
		for (let i = 0; i < customSelects.length; i++) {
			customSelects[i].addEventListener('addItem', function(event) {
				if (event.detail.value) {
					let parent = this.parentNode.parentNode
					parent.classList.add('valid')
					parent.classList.remove('invalid')
				} else {
					let parent = this.parentNode.parentNode
					parent.classList.add('invalid')
					parent.classList.remove('valid')
				}
			}, false);
		}
		deleteBtn.addEventListener("click", function(e) {
			e.preventDefault()
			const deleteAll = document.querySelectorAll('.choices__button')
			for (let i = 0; i < deleteAll.length; i++) {
				deleteAll[i].click();
			}
		});
	</script>

	<script>

	function copiarAlPortapapeles() {
		  var path = " <%= request.getAttribute("ruta") %> "
		  var aux = document.createElement("input");
		  aux.setAttribute("value", path);
		  document.body.appendChild(aux);
		  aux.select();
		  document.execCommand("copy");
		  document.body.removeChild(aux);
		  alert("Ruta copiada en el portapapeles")
		}
    function sacarEmpresas() { //Genera los select dinamicos

        var valor = document.getElementById('input');
    	var valor2 = document.getElementById('fechas');
    
    	//COMPRUEBA CAMPOS VACIOS
    	if ((valor.value != "Seleccione" && valor2.value != "Seleccione opcion") && (valor.value != '' && valor2.value !='')){
    		document.getElementById("generar").disabled = false;
    	}
    	else {  
             document.getElementById("generar").disabled = true;
        }
          
    	
    	if (valor.value === "Empresa"){
        			
        	borrar('listas');
        	var bloque=document.getElementById('listas');
    
        	elemento = document.createElement('select');
        	elemento.setAttribute("class", "input-select");
            elemento.id = 'select';
            elemento.name = 'select';
     		bloque.appendChild(elemento);

     		var lista = [<%for (int i = 0; i < lista_empresas.size(); i++) {%>"<%=lista_empresas.get(i).getId()%>"<%=i + 1 < lista_empresas.size() ? "," : ""%><%}%>];

	        for (var i = 0; i< lista.length; i++) 
	        {
                newOption = document.createElement('option');
                newOption.value=lista[i];
                newOption.text=lista[i];
                elemento.appendChild(newOption);
            } 
        }
        
        else if (valor.value === "Proyecto"){
        	
            borrar('listas');
            var bloque=document.getElementById('listas');
        
            elemento = document.createElement('select');
      		elemento.setAttribute("class", "input-select");
            elemento.id = 'select';
            elemento.name ='select';
            bloque.appendChild(elemento);
                 
            var lista = [<%for (int i = 0; i < lista_proyectos.size(); i++) {%>"<%=lista_proyectos.get(i).getClave()%>"<%=i + 1 < lista_proyectos.size() ? "," : ""%><%}%>];
       
            for (var i = 0; i< lista.length; i++) 
            {
               	newOption = document.createElement('option');
                newOption.value=lista[i];
                newOption.text=lista[i];
                elemento.appendChild(newOption);
            } 
        }
        
        else if (valor.value === "Empleado"){
        	
        	borrar('listas');
            var bloque=document.getElementById('listas');
        
            elemento = document.createElement('select');
      		elemento.setAttribute("class", "input-select");
            elemento.id = 'select';
            elemento.name ='select';
            bloque.appendChild(elemento);

            var lista = [<%for (int i = 0; i < lista_empleados.size(); i++) {%>"<%=lista_empleados.get(i).getTrabajador_DNI()%>"<%=i + 1 < lista_empleados.size() ? "," : ""%><%}%>];
       
            for (var i = 0; i< lista.length; i++) 
            {
                newOption = document.createElement('option');
                newOption.value=lista[i];
                newOption.text=lista[i];
                elemento.appendChild(newOption);
            } 
       }

    }
    
     function sacarFecha(){ //Select dinamicos para las fechas
    
    	var listas = document.getElementById("fechas");
    	var valor = document.getElementById('input');
    	//COMPRUEBA CAMPOS VACIOS
    	if ((valor.value != "Seleccione" && listas.value != "Seleccione opcion") && (valor.value != '' && listas.value !='')){
    		document.getElementById("generar").disabled = false;
    	}
    	
    	else { 
             document.getElementById("generar").disabled = true;
        }
          
    	
    	if(listas.value =="Semana"){
    		borrar("date");
    		document.getElementById('calendario').style.display= 'block';

    	}
    	else if (listas.value =="Mes"){
    		borrar("date");
    		document.getElementById('calendario').style.display= 'none';

            var meses =["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
            var bloque=document.getElementById('date');
                
            elemento2 = document.createElement('select');
      		elemento2.setAttribute("class", "input-select");
            elemento2.id = 'select2';
            bloque.appendChild(elemento2);
			elemento2.name='select2';
                 
			for (var i = 0; i< meses.length; i++) 
			{
                newOption = document.createElement('option');
                newOption.value=i+1;
                newOption.text=meses[i];
                elemento2.appendChild(newOption);
            } 
			elemento3 = document.createElement('select');
            elemento3.setAttribute("class", "input-select");
            elemento3.id = 'select3';
            elemento3.name = 'select3';
            bloque.appendChild(elemento3);
              
            for (var j=2000; j<=2021; j++ )
            {
             	newOption = document.createElement('option');
                newOption.value=j;
                newOption.text=j;
                elemento3.appendChild(newOption); 
            }
                 
    	}else if (listas.value =="Anual"){
    		
    		borrar("date");
    		document.getElementById('calendario').style.display= 'none';
    		
    		var bloqueaño=document.getElementById('date');
    		
			elemento3 = document.createElement('select');   
            elemento3.setAttribute("class", "input-select");
            elemento3.id = 'select3';
            elemento3.name = 'select3';
            bloqueaño.appendChild(elemento3);
                 
            for (var j=2000; j<=2021; j++ )
            {
                newOption = document.createElement('option');
                newOption.value=j;
                newOption.text=j;
                elemento3.appendChild(newOption);
            }
    	}   		
    } 

     function borrar(borrar) {
     	const bloque = document.getElementById(borrar);
     	while (bloque.lastElementChild) 
     	{
     		bloque.removeChild(bloque.lastElementChild);
     	}
    }
     
     
</script>
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
