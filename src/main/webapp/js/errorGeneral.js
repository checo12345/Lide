/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/menuPrincipal
* Sub modulo: menuPrincipal.js
* Fecha de modificación: 16/08/2017
* Descripción:  Jquery para el manejo del menu principal
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