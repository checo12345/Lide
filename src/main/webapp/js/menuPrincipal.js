/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/menuPrincipal
* Sub modulo: menuPrincipal.js
* Fecha de modificaci�n: 16/08/2017
* Descripci�n:  Jquery para el manejo del menu principal
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
                toastr.success('Bienvenido', 'Sistema de Punto de Venta Web');

            }, 1300);
           
            
            $("#tablaContacto").on("input", "input", function() {
          	  var input = $(this);
          	  var columns = input.closest("tr").children();
          	  var price = columns.eq(3).text();
          	  var calculated = input.val() * price;
          	  columns.eq(5).text(calculated.toFixed(2));
          	  sumar_columnas();
          	  
          	});
           
            $("#codigoProducto").delayPasteKeyUp(function(){
            	agregarProducto() ;
             }, 300);
            
            
        });
 
function cerrarSesion() {
	window.location = "cerrarSesion.action";
} 

function agregarProducto()
{
	 var postData = $("#registrarContactoForm").serializeArray();
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "agregarProducto.action",
			type : "POST",
			dataType : "html",
			data:	postData,
			success : function(respuestaHtml, textStatus, jqXHR) {
					footable = $("#tablaContacto").data('footable');
					footable.appendRow(respuestaHtml);
					$("#codigoProducto").val("") ;
					sumar_columnas() ;
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


function realizarVenta()
{
	var Orden = {};
 	var listaProductos = [];
    var producto = new Object();
    
    
    
	$("#tablaContacto tbody tr").each(function (index) {
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
			url : "realizarVenta.action",
			type : "POST",
			dataType : "html",
			data:	JSON.stringify(Orden),
			contentType: "application/json; charset=utf-8",
			success : function(respuestaHtml, textStatus, jqXHR) {
				limpiarTabla();
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

	footable = $("#tablaContacto").data('footable');
	filaTabla = $("#" + codigoBarras);

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

function teclas(event) {
    tecla=(document.all) ? event.keyCode : event.which;
    if (tecla==13 && event.altKey) {
        alert('holaaa');
    }
 
    return false;
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




