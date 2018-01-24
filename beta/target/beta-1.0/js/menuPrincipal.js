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

/*
* Descripci�n: Carga el formulario para agregar un nuevo contratista
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function cargarFormularioContratista() {
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarFormularioContratista",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Agregar Contratista");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}

/*
* Descripci�n: Carga el formulario para agregar una nueva empresa
* Fecha: 09 de septiembre de 2017
* @author: Sergio Rojas
* */
function cargarFormularioEmpresa() {
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarFormularioEmpresa",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Agregar Empresa");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}

/*
 * Descripcion: AJAX para cargar el formulario de un departamento y mostrarla en modal
 * Fecha: 19 de Septiembre de 2017
 * @author: Sergio Rojas
 * */
function cargarFormularioDepartamento()
{
	debugger
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "cargarFormularioDepartamento",
			type : "POST",
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					$("#btn-admon-departamento").attr('onclick', 'agregarDepartamento()');
					
					validarForm() ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
}

function validarForm() {
	    validateForm = $("#registrarDepartamentoForm").validate({
	    	debug: false,
	    	rules: {
	    		"departamento.departamento_nombre": {
	    			required: true
	    		},
	    		"departamento.departamento_descripcion": {
	    			required: true
	    		}
	    	},
	    	messages: {
	    		"departamento.departamento_nombre": {
	    			required: "Nombre es obligatorio"
	    		},
	    		"departamento.departamento_descripcion": {
	    			required: "Descripci�n es obligatoria"
	    		}
	    	},
	    	errorPlacement: function (error, element) {
	    		error.insertAfter($(element).parent());
	    	},
	    	submitHandler: function(form){
	    		return false;
	    	}
	    });
}
/*
 * Descripcion: AJAX para registrar la informacion de un departamento
 * Fecha: 19 de Septiembre de 2017
 * @author: Sergio Rojas
 * */
function agregarDepartamento()
{
	 if ($("#registrarDepartamentoForm").valid()){
	 $('.ibox-content').toggleClass('sk-loading');
	 var postData = $("#registrarDepartamentoForm").serializeArray();
	 var formURL = $("#registrarDepartamentoForm").attr("action");
	 $.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				if (respuestaJson.exito){
					toastr.success(respuestaJson.mensaje,'Departamento') ;
					limpiarForm("registrarDepartamentoForm") ;
				}
				else
					toastr.warning(respuestaJson.mensaje,'Departamento');
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Departamento');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
	 }
}

/*
* Descripci�n: Carga la tabla con los departamentos que existen
* Fecha: 09 de septiembre de 2017
* @author: Sergio Rojas
* */
function cargarTablaDepartamentos(){
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarTablaDepartamentos",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Catalogo Departamentos");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}



/*
* Descripci�n: Carga la tabla con los puestos que existen
* Fecha: 10 de Octubre de 2017
* @author: Sergio Rojas
* */
function cargarTablaPuestos(){
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarTablaPuestos",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Catalogo Departamentos");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}




/*
 * Descripcion: AJAX para cargar el formulario de un puesto
 * Fecha: 24 de Noviembre de 2017
 * @author: Sergio Rojas
 * */
function cargarFormularioPuesto()
{
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "cargarFormularioPuesto",
			type : "POST",
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					$("#btn-admon-puesto").attr('onclick', 'agregarPuesto()');
					
					validarFormPuesto() ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Puesto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
}

function validarFormPuesto() {
	    validateForm = $("#registrarPuestoForm").validate({
	    	debug: false,
	    	rules: {
	    		"puesto.puesto_nombre": {
	    			required: true
	    		},
	    		"puesto.puesto_descripcion": {
	    			required: true
	    		}
	    	},
	    	messages: {
	    		"puesto.puesto_nombre": {
	    			required: "Nombre es obligatorio"
	    		},
	    		"puesto.puesto_descripcion": {
	    			required: "Descripci�n es obligatoria"
	    		}
	    	},
	    	errorPlacement: function (error, element) {
	    		error.insertAfter($(element).parent());
	    	},
	    	submitHandler: function(form){
	    		return false;
	    	}
	    });
}

/*
 * Descripcion: AJAX para registrar la informacion de un puesto
 * Fecha: 24 de Noviembre de 2017
 * @author: Sergio Rojas
 * */
function agregarPuesto()
{
	 if ($("#registrarPuestoForm").valid()){
	 $('.ibox-content').toggleClass('sk-loading');
	 var postData = $("#registrarPuestoForm").serializeArray();
	 var formURL = $("#registrarPuestoForm").attr("action");
	 $.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				if (respuestaJson.exito){
					toastr.success(respuestaJson.mensaje,'Puesto') ;
					limpiarForm("registrarPuestoForm") ;
				}
				else
					toastr.warning(respuestaJson.mensaje,'Puesto');
					
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se registro",'Puesto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
	 }
}


/*
* Descripci�n: Carga la tabla con los contactos que existen
* Fecha: 27 de Noviembre de 2017
* @author: Sergio Rojas
* */
function cargarTablaContactos(){
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarTablaContactos",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Catalogo Departamentos");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}



/*
 * Descripcion: AJAX para cargar el formulario de un contacto
 * Fecha: 4 de Enero de 2018
 * @author: Sergio Rojas
 * */
function cargarFormularioContacto()
{
	 $('.ibox-content').toggleClass('sk-loading');
	 $.ajax({
			url : "cargarFormularioContacto",
			type : "POST",
			dataType : "html",
			success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					$("#btn-admon-contacto").attr('onclick', 'agregarContacto()');
					
					validarFormContacto() ;
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se encontro",'Contacto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
}

function validarFormContacto() {
	    validateForm = $("#registrarContactoForm").validate({
	    	debug: false,
	    	rules: {
	    		"contacto.contacto_nombreCompleto": {
	    			required: true
	    		},
	    		"contacto.contacto_apellidoPaterno": {
	    			required: true
	    		},
	    		"contacto.contacto_apellidoMaterno": {
	    			required: true
	    		}
	    	},
	    	messages: {
	    		"contacto.contacto_nombreCompleto": {
	    			required: "Nombre es obligatorio"
	    		},
	    		"contacto.contacto_apellidoPaterno": {
	    			required: "Apellido P es obligatorio"
	    		},
	    		"contacto.contacto_apellidoMaterno": {
	    			required: "Apellido M es obligatorio"
	    		}
	    	},
	    	errorPlacement: function (error, element) {
	    		error.insertAfter($(element).parent());
	    	},
	    	submitHandler: function(form){
	    		return false;
	    	}
	    });
}

/*
 * Descripcion: AJAX para registrar la informacion de un contacto
 * Fecha: 4 de Enero de 2018
 * @author: Sergio Rojas
 * */
function agregarContacto()
{
	 if ($("#registrarContactoForm").valid()){
	 $('.ibox-content').toggleClass('sk-loading');
	 var postData = $("#registrarContactoForm").serializeArray();
	 var formURL = $("#registrarContactoForm").attr("action");
	 $.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
					if (respuestaJson.exito){
						toastr.success(respuestaJson.mensaje,'Contacto') ;
						limpiarForm("registrarContactoForm") ;
					}
					else
						toastr.warning(respuestaJson.mensaje,'Contacto');
						
					
			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error("No se registro",'Contacto');
			}
		});
	 $('.ibox-content').toggleClass('sk-loading');
	 }
}


/*
* Descripci�n: Carga la tabla con los tipos de contratista que existen
* Fecha: 17 de Enero de 2018
* @author: Sergio Rojas
* */
function cargarTablaTipoContratista(){
	$('.ibox-content').toggleClass('sk-loading');
	
			$.ajax({
				url : "cargarTablaTipoContratista",
				type : "POST",
				dataType : "html",
				success : function(respuestaHtml, textStatus, jqXHR) {
					$("#contenidoDinamico").empty() ;
					$("#contenidoDinamico").html(respuestaHtml) ;
					
				},
				error : function(jqXHR,textStatus,errorThrown) {
					toastr.error("Ocurrio un error","Catalogo Tipos de Contratista");
				}
			});
	$('.ibox-content').toggleClass('sk-loading');
}


