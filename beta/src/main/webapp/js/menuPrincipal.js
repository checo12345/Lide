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
				
			case 3:
				producto.quantity=1;//$(this).text();
				producto.storeId=5910974510923776;
				producto.coverageAreaId=4785074604081152;
				break;
			}
			
		});
		listaProductos.push(producto); 
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
				$("#contenidoDinamico2").empty() ;
				$("#contenidoDinamico2").html(respuestaHtml) ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
}


