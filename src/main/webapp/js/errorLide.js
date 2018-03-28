/*========================================================================
* Sistema: Lide
* Modulo: js
* Sub modulo: errorLide.js
* Fecha de modificación: 27/03/2018
* Descripción:  Jquery para el manejo de errores del negocio
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
             timeOut: 5000
         };
         toastr.warning(errorGeneral, 'Error Lide');
 }