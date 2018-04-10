/*========================================================================
* Sistema: LIDE
* Modulo: Actualizar los productos
* Sub modulo: actualizarProductos.js
* Fecha de modificacion: 09/04/2018
* Descripcion:  Jquery para la actualizacion de productos
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
           
            $("#codigoProducto").delayPasteKeyUp(function(){
            	debugger
            	obtenerProductoPorCodigoActualizar() ;
             }, 300);
            
            
        });
 
 $.fn.delayPasteKeyUp = function(fn, ms)
 {
 	 var timer = 0;
 	 $(this).on("propertychange input", function()
 	 {
 		 clearTimeout(timer);
 		 timer = setTimeout(fn, ms);
 	 });
 };
 
 function obtenerProductoPorCodigoActualizar()
 {
 	 var postData = $("#buscarProductoForm").serializeArray();
 	 $('.ibox-content').toggleClass('sk-loading');
 	 $.ajax({
 			url : "obtenerProductoPorCodigoActualizar.action",
 			type : "POST",
 			dataType : "html",
 			data:	postData,
 			success : function(respuestaHtml, textStatus, jqXHR) {
 				
 					if(respuestaHtml.includes("error")== false){
 						$("#contenidoDinamico2").empty() ;
 						$("#contenidoDinamico2").html(respuestaHtml) ;
 					}
 					else{
 						$("#contenidoError").empty() ;
 						$("#contenidoError").html(respuestaHtml) ;
 					}
 						
 					
 			},
 			error : function(jqXHR,textStatus,errorThrown) {
 				toastr.error("No se encontro",'Departamento');
 			}
 		});
 	 $('.ibox-content').toggleClass('sk-loading');
 }
 
 
 function actualizarProducto()
 {
 	 var postData = $("#registrarProductoForm").serializeArray();
 	 $('.ibox-content').toggleClass('sk-loading');
 	 $.ajax({
 			url : "actualizarProducto.action",
 			type : "POST",
 			dataType : "json",
 			data:	postData,
 			success : function(respuestaJson, textStatus, jqXHR) {
 				
 					if(respuestaJson.exito){
 						$("#contenidoDinamico2").empty() ;
 						toastr.success(respuestaJson.mensaje,'LIDE');
 					}
 						
 					
 			},
 			error : function(jqXHR,textStatus,errorThrown) {
 				toastr.error("No se encontro",'Departamento');
 			}
 		});
 	 $('.ibox-content').toggleClass('sk-loading');
 }