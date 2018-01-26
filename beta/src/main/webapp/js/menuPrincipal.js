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
			contentType: "application/json; charset=utf-8",
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
	 var test= ["1","2","3","4","5"] ;
	 	var params = {};
	 	var arr = [];
	    var p1 = { codigoBarras : "123", name : "Doe"};
	    var p2 = { codigoBarras : "456", name: "Smith"};
	    arr.push(p1); 
	    arr.push(p2);
	    params["productos"] = arr;
	    var datos= JSON.stringify(params) ;
	    //params="{'productos':[{'codigoBarras':'123','name':'Doe'},{'codigoBarras':'456','name':'Smith'}]}" ;
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "realizarVenta.action",
			type : "POST",
			dataType : "html",
			data:	JSON.stringify(params),
			contentType: "application/json; charset=utf-8", //traditional:true,
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


