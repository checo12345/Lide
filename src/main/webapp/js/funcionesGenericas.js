/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js.funcionesGenericas
* Sub modulo: funcionesGenericas.js
* Fecha de modificación: 09/09/2017
* Descripción:  Jquery que contiene funciones que se ocupan en todo el sistema y se evita repetirlas
* Autor: Sergio Rojas
=========================================================================
*/

$(document).ready(function() {
	
	//Opciones del mensaje de aviso al completar una operación
	toastr.options = {
	        "closeButton": true,
	        "debug": false,
	        "progressBar": true,
	        "preventDuplicates": false,
	        "newestOnTop": true,
	        "positionClass": "toast-top-right",
	        "onclick": null,
	        "showDuration": "400",
	        "hideDuration": "1000",
	        "timeOut": "2000",
	        "extendedTimeOut": "2000",
	        "showEasing": "swing",
	        "hideEasing": "linear",
	        "showMethod": "fadeIn",
	        "hideMethod": "fadeOut"
	    };
}) ;


/*
 * Descripcion: Limpia el formulario
 * Fecha: 19 de Septiembre de 2017
 * @author: Sergio Rojas
 * */
function limpiarForm(nombreForm) {
	$("#"+nombreForm).closest('form').find("input[type=text], textarea").val("");
}

