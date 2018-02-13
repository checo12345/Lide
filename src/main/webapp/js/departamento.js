/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/tablaDepartamentos
* Sub modulo: departamentos.js
* Fecha de modificación: 16/08/2017
* Descripción:  Jquery para el manejo del catalogo de departamentos
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
			$('.footable').footable();
			
			
        });

 /*
  * Descripcion: AJAX para obtener la informacion de un departamento y mostrarla en modal
  * Fecha: 19 de Septiembre de 2017
  * @author: Sergio Rojas
  * */
 function editarDepartamento(pkDepartamento)
 {
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerDepartamento",
			type : "POST",
			data : {"departamento.pk_departamento":pkDepartamento},
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#modalDinamico").empty() ;
					$("#modalDinamico").html(respuestaHtml) ;
					$("#btn-admon-departamento").attr('onclick', 'actualizarDepartamento()');
					$("#tituloForm").hide();
					$("#mdl-departamento").modal();
					toastr.success("Se obtuvo exitosamente",'Departamento');

			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
 }

 /*
  * Descripcion: AJAX para actualizar la informacion del departamento
  * Fecha: 19 de Septiembre de 2017
  * @author: Sergio Rojas
  * */
 function actualizarDepartamento()
 {
	 
	 var postData = $("#registrarDepartamentoForm").serializeArray();
	 var formURL = $("#registrarDepartamentoForm").attr("action");
	 $.ajax({
			url : "actualizarDepartamento",
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				 debugger
					toastr.success(respuestaJson.mensaje,'Departamento');
					$("#mdl-departamento").modal('hide');
					setTimeout(function() {cargarTablaDepartamentos();}, 1000);
					
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error(respuestaJson.mensaje,'Departamento');
			}
		});
	 

 }

 


/*
 * Descripcion: Ajax para eliminar un departamento de su catalogo
 * Fecha: 19 de Septiembre de 2017
 * @author: Sergio Rojas
 * */
function eliminarDepartamento(pkDepartamento) {

    swal({
        title: "Eliminar Departamento",
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
        	eliminarAjaxDepartamento(pkDepartamento) ;
        }
    );
}

/*
 * Descripcion: AJAX para eliminar un departamento de su catalogo
 * Fecha: 19 de Septiembre de 2017
 * @author: Sergio Rojas
 * */
function eliminarAjaxDepartamento(pkDepartamento)
{

	 $.ajax({
			url : "eliminarDepartamento",
			type : "POST",
			data : {"departamento.pk_departamento":pkDepartamento},
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				toastr.success(respuestaJson.mensaje,'Departamento');
				eliminarVisualDepartamento(pkDepartamento) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});

}


function eliminarVisualDepartamento(pkDepartamento) {

	footable = $("#tablaDepartamentos").data('footable');
	filaTabla = $("#departamento" + pkDepartamento);

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

