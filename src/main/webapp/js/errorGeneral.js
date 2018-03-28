/*========================================================================
* Sistema: Lide
* Modulo: js
* Sub modulo: errorGeneral.js
* Fecha de modificación: 27/03/2018
* Descripción:  Jquery para el manejo de errores generales
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
	 	
	 		setTimeout(function(){
	 			mostrarError($("#error").val())
	 			}, 1000);
        });
 
 
 function mostrarError(errorGeneral) {
	 toastr.options = {
             closeButton: true,
             progressBar: true,
             showMethod: 'slideDown',
             timeOut: 4000
         };
         toastr.error(errorGeneral, 'Error');
         if (errorGeneral == "No tienes sesion para este modulo")
        	 {
        	 	setTimeout(function() {window.location = "irIniciarSesion";}, 4000);
        	 }
	 
 }