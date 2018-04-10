/*========================================================================
* Sistema: LIDE	
* Modulo: js/menuPrincipal
* Sub modulo: menuPrincipal.js
* Fecha de modificacion: 16/08/2017
* Descripcion:  Jquery para el manejo del punto de venta
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 4000
                };
                toastr.success('Bienvenido', 'Sistema LIDE');
            }, 1300);
           
            
        });
 
function cerrarSesion() {
	window.location = "cerrarSesion.action";
} 

function obtenerPuntoDeVenta() {
	$('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerPuntoDeVenta.action",
			type : "POST",
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
				$("#contenidoDinamico").empty() ;
				$("#contenidoDinamico").html(respuestaHtml) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'LIDE');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
	
}


function obtenerActualizacionProducto() {
	$('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerActualizacionProducto.action",
			type : "POST",
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
				$("#contenidoDinamico").empty() ;
				$("#contenidoDinamico").html(respuestaHtml) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'LIDE');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
	
}

