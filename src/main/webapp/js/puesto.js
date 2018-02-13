
/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/tablaPuesto
* Sub modulo: puesto.js
* Fecha de modificación: 10/10/2017
* Descripción:  Jquery para el manejo del catalogo de puestos
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
			$('.footable').footable();
			
			
        });

 /*
  * Descripcion: AJAX para obtener la informacion de un puesto y mostrarla en modal
  * Fecha: 31 de Octubre de 2017
  * @author: Sergio Rojas
  * */
 function obtenerPuesto(pkPuesto)
 {
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerPuesto",
			type : "POST",
			data : {"puesto.pk_departamento":pkPuesto},
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#modalDinamico").empty() ;
					$("#modalDinamico").html(respuestaHtml) ;
					$("#btn-admon-puesto").attr('onclick', 'actualizarPuesto()');
					$("#tituloForm").hide();
					$("#mdl-puesto").modal();
					toastr.success("Se obtuvo exitosamente",'Puesto');

			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Puesto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
 }

 /*
  * Descripcion: AJAX para actualizar la informacion del puesto
  * Fecha: 31 de Octubre de 2017
  * @author: Sergio Rojas
  * */
 function actualizarPuesto()
 {
	 var postData = $("#registrarPuestoForm").serializeArray();
	 $.ajax({
			url : "actualizarPuesto",
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
					toastr.success(respuestaJson.mensaje,'Puesto');
					$("#mdl-puesto").modal('hide');
					setTimeout(function() {cargarTablaPuestos();}, 1000);
					
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error(respuestaJson.mensaje,'Puesto');
			}
		});
	 

 }

 


/*
 * Descripcion: Modal para eliminar un puesto de su catalogo
 * Fecha: 31 de Octubre de 2017
 * @author: Sergio Rojas
 * */
function modalEliminarPuesto(pkPuesto) {

    swal({
        title: "Eliminar Puesto",
        text: '¿Desea realizar esta acción?',
        type: 'warning',
        showCancelButton: true,
        closeOnConfirm: true,
        showLoaderOnConfirm: true,
        confirmButtonColor: '#DD6B55',
        allowOutsideClick: true,
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar'
        },
        function(){
        	eliminarPuesto(pkPuesto) ;
        }
    );
}

/*
 * Descripcion: AJAX para eliminar un puesto de su catalogo
 * Fecha: 31 de Octubre de 2017
 * @author: Sergio Rojas
 * */
function eliminarPuesto(pkPuesto)
{

	 $.ajax({
			url : "eliminarPuesto",
			type : "POST",
			data : {"puesto.pk_puesto":pkPuesto},
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				toastr.success(respuestaJson.mensaje,'Puesto');
				eliminarVisualPuesto(pkPuesto) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Puesto');
			}
		});

}


function eliminarVisualPuesto(pkPuesto) {

	footable = $("#tablaPuestos").data('footable');
	filaTabla = $("#puesto" + pkPuesto);

	filaTabla.css({
		"background-color" : '#DD6B55',
		"color" : "#000"
	})
	filaTabla.css({
		"background-color" : '#DD6B55'
	}).hide(1500, function() {
		footable.removeRow(filaTabla);
	});
}

