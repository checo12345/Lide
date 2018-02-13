
/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/tablaContacto
* Sub modulo: contacto.js
* Fecha de modificación: 28/11/2017
* Descripción:  Jquery para el manejo del catalogo de contactos
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
			$('.footable').footable();
			
			
        });

 /*
  * Descripcion: AJAX para obtener la informacion de un contacto y mostrarla en modal
  * Fecha: 28 de Noviembre de 2017
  * @author: Sergio Rojas
  * */
 function obtenerContacto(pkContacto)
 {
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerContacto",
			type : "POST",
			data : {"contacto.pk_contacto":pkContacto},
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#modalDinamico").empty() ;
					$("#modalDinamico").html(respuestaHtml) ;
					$("#btn-admon-contacto").attr('onclick', 'actualizarContacto()');
					$("#tituloForm").hide();
					$("#mdl-contacto").modal();
					toastr.success("Se obtuvo exitosamente",'Contacto');

			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Contacto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
 }

 /*
  * Descripcion: AJAX para actualizar la informacion del contacto
  * Fecha: 30 de Diciembre de 2017
  * @author: Sergio Rojas
  * */
 function actualizarContacto()
 {
	 var postData = $("#registrarContactoForm").serializeArray();
	 $.ajax({
			url : "actualizarContacto",
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
					toastr.success(respuestaJson.mensaje,'Contacto');
					$("#mdl-contacto").modal('hide');
					setTimeout(function() {cargarTablaContactos();}, 1000);
					
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error(respuestaJson.mensaje,'Contacto');
			}
		});
 }

 


/*
 * Descripcion: Modal para eliminar un contacto de su catalogo
 * Fecha: 04 de Enero de 2018
 * @author: Sergio Rojas
 * */
function modalEliminarContacto(pkContacto) {

    swal({
        title: "Eliminar Contacto",
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
        	eliminarContacto(pkContacto) ;
        }
    );
}

/*
 * Descripcion: AJAX para eliminar un contacto de su catalogo
 * Fecha: 04 de Enero de 2018
 * @author: Sergio Rojas
 * */
function eliminarContacto(pkContacto)
{

	 $.ajax({
			url : "eliminarContacto",
			type : "POST",
			data : {"contacto.pk_contacto":pkContacto},
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				toastr.success(respuestaJson.mensaje,'Contacto');
				eliminarVisualContacto(pkContacto) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Contacto');
			}
		});

}


function eliminarVisualContacto(pkContacto) {

	footable = $("#tablaContacto").data('footable');
	filaTabla = $("#contacto" + pkContacto);

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

