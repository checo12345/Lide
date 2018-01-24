
/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/tablaTipoContratista
* Sub modulo: tipoContratista.js
* Fecha de modificación: 18/01/2018
* Descripción:  Jquery para el manejo del catalogo de los tipos de contratista
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
			$('.footable').footable();
			
			
        });

 /*
  * Descripcion: AJAX para obtener la informacion de un tipo de contratista y mostrarla en modal
  * Fecha: 18 de Enero de 2018
  * @author: Sergio Rojas
  * */
 function obtenerTipoContratista(pk_tipoContratista)
 {
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerTipoContratista",
			type : "POST",
			data : {"tipoContratista.pk_tipoContratista":pk_tipoContratista},
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#modalDinamico").empty() ;
					$("#modalDinamico").html(respuestaHtml) ;
					$("#btn-admon-tipo-contratista").attr('onclick', 'actualizarTipoContratista()');
					$("#tituloForm").hide();
					$("#mdl-tipo-contratista").modal();
					toastr.success("Se obtuvo exitosamente",'Tipo de Contratista');

			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Tipo de Contratista');
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

