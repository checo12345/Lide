/*========================================================================
* Sistema: LIDE
* Modulo: punto de venta
* Sub modulo: puntoVenta.js
* Fecha de modificacion: 27/03/2018
* Descripcion:  Jquery para el manejo del punto de venta
* Autor: Sergio Rojas
=========================================================================
*/
 $(document).ready(function() {
            
            $("#tablaProducto").on("input", "input", function() {
          	  var input = $(this);
          	  var columns = input.closest("tr").children();
          	  var price = columns.eq(3).text();
          	  var calculated = input.val() * price;
          	  columns.eq(5).text(calculated.toFixed(2));
          	  sumar_columnas();
          	  
          	});
           
            $("#codigoProducto").delayPasteKeyUp(function(){
            	debugger
            	obtenerProductoPorCodigo() ;
             }, 300);
            
            
        });
 

function obtenerProductoPorCodigo()
{
	 var postData = $("#buscarProductoForm").serializeArray();
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "obtenerProductoPorCodigo.action",
			type : "POST",
			dataType : "html",
			data:	postData,
			success : function(respuestaHtml, textStatus, jqXHR) {
				
					if(respuestaHtml.includes("error")== false){
						debugger
						var footable = $("#tablaProducto").data('footable');
						footable.appendRow(respuestaHtml);
						$("#codigoProducto").val("") ;
						sumar_columnas() ;
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

function sumar_columnas(){
	var sum=0;
	    //itera cada input de clase .subtotal y la suma
	    $('.precio').each(function() {     
	            sum += parseFloat($(this).text());                     
	    }); 
	    //cambia valor del total y lo redondea a la segunda decimal
	    $('#totalVenta').text('$'+sum.toFixed(2));
	}


function agregarVenta()
{
	var Orden = {};
 	var listaProductos = [];
    var producto = new Object();
    
    
    
	$("#tablaProducto tbody tr").each(function (index) {
		$(this).children("td").each(function (index2) {
			switch (index2) {
			case 0:
				producto.codigoBarras=$(this).text();
			break;
				
			case 4:
				producto.quantity=$(this).children("input").val()*-1;
				producto.storeId=5629499534213120;
				producto.coverageAreaId=5066549580791808;
				break;
			}
			
			
		});
		listaProductos.push(producto);
		producto = new Object();
	});
     
	Orden["productos"] = listaProductos;
	 	

	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "agregarVenta.action",
			type : "POST",
			dataType : "json",
			data:	JSON.stringify(Orden),
			contentType: "application/json; charset=utf-8",
			success : function(respuestaHtml, textStatus, jqXHR) {
						if(respuestaHtml.exito){
							toastr.success(respuestaHtml.mensaje, 'Punto de Venta');
							limpiarTabla();
						}
						else
							toastr.warning(respuestaHtml.mensaje, 'Punto de Venta');
						
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
}

function limpiarTabla()
{
	    $(".footable>tbody>tr").each(function(index, elem){
	        $(elem).remove();
	     });
	    
	    $('#totalVenta').text('$0.00');
	}

/*
 * Descripcion: Modal para eliminar un producto de la tabla
 * Fecha: 04 de Enero de 2018
 * @author: Sergio Rojas
 * */
function modalEliminarProducto(codigoBarras) {

    swal({
        title: "Eliminar Producto",
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
        	eliminarVisualProducto(codigoBarras) ;
        }
    );
}


function eliminarVisualProducto(codigoBarras) {

	footable = $("#tablaProducto").data('footable');
	filaTabla = $("#" + codigoBarras);

	filaTabla.css({
		"background-color" : '#DD6B55',
		"color" : "#000"
	})
	filaTabla.css({
		"background-color" : '#DD6B55'
	}).hide(1500, function() {
		footable.removeRow(filaTabla);
		sumar_columnas();
	});
	
	
}


$.fn.delayPasteKeyUp = function(fn, ms)
{
	 var timer = 0;
	 $(this).on("propertychange input", function()
	 {
		 clearTimeout(timer);
		 timer = setTimeout(fn, ms);
	 });
};


