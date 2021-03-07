
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import='main.java.util.PeticionDao'%>
<%@page import='main.java.model.Peticion'%>
<%@page import='java.util.ArrayList'%>
<!DOCTYPE html>
<html>
<head>
    <title>CALENDARIO DE DÍAS LIBRES</title>

	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


<link href='assets/css/fullcalendar.css' rel='stylesheet' />
<link href='assets/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Show All Users</title>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<script src='assets/js/jquery-1.10.2.js' type="text/javascript"></script>
<script src='assets/js/jquery-ui.custom.min.js' type="text/javascript"></script>
<script src='assets/js/fullcalendar.js' type="text/javascript"></script>


	<% 
		String fecha_inicio2 = (String)request.getAttribute("fecha_inicio");
	
		HttpSession sesion = request.getSession();
		String DNI_sesion = (String) sesion.getAttribute("DNI");
		if (DNI_sesion==null){
			response.sendRedirect("index.jsp");
		}
	%>

<script type="text/javascript">
    var Fecha_Inicio = '${fecha_inicio}';
</script>
<script>

	$(document).ready(function() {
	    var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		/*  className colors

		className: default(transparent), important(red), chill(pink), success(green), info(blue)

		*/


		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events div.external-event').each(function() {

			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};

			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});

		});


		/* initialize the calendar
		-----------------------------------------------------------------*/
		var calendar =  $('#calendar').fullCalendar({
			header: {
				left: 'title',
				center: 'month',
				right: 'prev,next today'
			},
			editable: true,
			firstDay: 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
			selectable: false,
			defaultView: 'month',
			eventColor: '#378006',

			axisFormat: 'h:mm',
			columnFormat: {
                month: 'ddd',    // Mon
                week: 'ddd d', // Mon 7
                day: 'dddd M/d',  // Monday 9/7
                agendaDay: 'dddd d'
            },
            titleFormat: {
                month: 'MMMM yyyy', // September 2009
                week: "MMMM yyyy", // September 2009
                day: 'MMMM yyyy'                  // Tuesday, Sep 8, 2009
            },
			allDaySlot: false,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Motivo de día/s libre/s:');
				if (title){
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
			  
			         
			         calendar.fullCalendar('unselect');
			},
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');

				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);

				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;

				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
			},	
			
		
			
			events: [
				<% 
				
				
				String titulo = "Vacaciones";
				String fecha_inicio = null;
				String fecha_fin = null;
				String motivo = null;
				
				
				ArrayList<Peticion> lista = new ArrayList<>();
				String DNI = (String)request.getAttribute("DNIVacaciones");
				
				PeticionDao daop = new PeticionDao();
				 lista = daop.ObtenerVacaciones(DNI);
				 
				 for (int i =0; i<lista.size();i++){

					 Peticion peticion = new Peticion();
					 peticion = lista.get(i);
						
					 //fecha inicio
						fecha_inicio = peticion.getFecha_Inicio();
						String[] fecha_inicio_separada = fecha_inicio.split(" ");
						String fecha = fecha_inicio_separada[0];
						String hora = fecha_inicio_separada[1];
						
						String[] fecha_separada = fecha.split("-");
						
						int año = Integer.parseInt(fecha_separada[0]);
						int mes = Integer.parseInt(fecha_separada[1]);
						int dia = Integer.parseInt(fecha_separada[2]);
						
				
						String[] hora_separada = hora.split(":");
						int horas = Integer.parseInt(hora_separada[0]);
						
						int minutos = Integer.parseInt(hora_separada[1]);
						
						
				
				
						fecha_fin = peticion.getFecha_Fin();
				
						
						String[] fecha_fin_separada = fecha_fin.split(" ");
						
						String fechaf = fecha_fin_separada[0];
						String horaf = fecha_fin_separada[1];
						
						String[] fecha_separadaf = fechaf.split("-");
						
						int año_f = Integer.parseInt(fecha_separadaf[0]);
						int mes_f= Integer.parseInt(fecha_separadaf[1]);
						int dia_f = Integer.parseInt(fecha_separadaf[2]);
				
						String[] hora_separadaf = horaf.split(":");
						int horas_f = Integer.parseInt(hora_separadaf[0]);
				
						int minutos_f= Integer.parseInt(hora_separadaf[1]);
						
					
						if ((año_f==año) && (mes_f==mes) && (dia_f==dia)){
							titulo ="Días libres";
						}
						
				
			//motivo
			
			
						motivo = peticion.getMotivo();
						
				 
					
                 
				%>

				
				{
					 title:"<%=titulo%>",
			          start: new Date("<%=año%>","<%=mes-1%>", "<%=dia%>", "<%=horas%>", "<%=minutos%>"),
			            end: new Date("<%=año_f%>","<%=mes_f-1%>", "<%=dia_f%>", "<%=horas_f%>", "<%=minutos_f%>"),
			            allDay: false,
			            className: 'evento'
	          },
	          <%
					}
   				 
              %>
	          
	          
	        ],
	        
	        eventRender: function(event, element) {
	            if (event.className == 'evento') {
	                element.css({
	                    'background-color': '#d2ebc3',
	                    'border-color': '#333333'
	                });
	            }
	        }
		});
	});

</script>
<style>

	body {
		margin-top: 20px;
		text-align: center;
		font-size: 14px;
		font-family: "Helvetica Nueue",Arial,Verdana,sans-serif;
		background-color: #171577;
		}

	#wrap {
		width: 1100px;
		margin: 0 auto;
		}

	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		text-align: left;
		}

	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
		}

	.external-event { /* try to mimick the look of a real event */
		margin: 10px 0;
		padding: 2px 4px;
		background: #3366CC;
		color: #fff;
		font-size: .85em;
		cursor: pointer;
		}

	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
		}

	#external-events p input {
		margin: 0;
		vertical-align: middle;
		}

	#calendar {
/* 		float: right; */
        margin: 0 auto;
		width: 800px;
		background-color: #FFFFFF;
		  border-radius: 6px;
        box-shadow: 0 1px 2px #C3C3C3;
		}

</style>

</head>
<body>
<div id='wrap'>

<div id='calendar'></div>

<div style='clear:both'></div>
</div>

<div>
<button type="button" class="btn btn-primary" onclick="location.href='MenuEmpleado.jsp';">Salir</button>
</div>


</body>
</html>
