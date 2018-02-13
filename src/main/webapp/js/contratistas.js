/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: js/Contratistas
* Sub modulo: contratistas.js
* Fecha de modificación: 02/09/2017
* Descripción:  Jquery para el manejo de las acciones del formulario para contratistas
* Autor: Sergio Rojas
=========================================================================
*/

 $(document).ready(function() {
	$("#genero").select2({
	                placeholder: "Selecciona el genero"
	            });
	
	$("#tipoContratista").select2({
        placeholder: "Selecciona el tipo"
    });
	
	$("#tipoEstancia").select2({
        placeholder: "Selecciona el tipo"
    });
	
	$("#status").select2({
        placeholder: "Selecciona el tipo"
    });
	
	$("#exEmpleado").select2({
        placeholder: "Selecciona el tipo"
    });
	
	$('.input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
	
	  //Valida el formulario de registro nuevo
    validateForm = $("#registrarContratistaForm").validate({
    	debug: false,
    	rules: {
    		"contratista.contratista_nombre": {
    			required: true
    		},
    		"contratista.contratista_paterno": {
    			required: true
    		},
    		"contratista.contratista_materno": {
    			required: true
    		},
    		"contratista.contratista_curp": {
    			required: true
    		},
    		"contratista.contratista_nss": {
    			required: true
    		},
    		"contratista.contratista_nacimiento": {
    			required: true
    		},
    		"contratista.contratista_genero": {
    			required: true
    		},
    		"contratista.contratista_correo": {
    			required: true,
    			email: true
    		},
    		"contratista.contratista_edad": {
    			required: true
    		},
    		"contratista.contratista_fk_empresa": {
    			required: true
    		},
    		"contratista.contratista_tipoEstancia": {
    			required: true
    		},
    		"contratista.contratista_departamento": {
    			required: true
    		},
    		"contratista.contratista_emergencia": {
    			required: true
    		},
    		"contratista.contratista_telefonoEmergencia": {
    			required: true
    		},
    		"contratista.contratista_tipoOperacion": {
    			required: true
    		},
    		"contratista.contratista_puesto": {
    			required: true
    		},
    		"contratista.contratista_tipoContratista": {
    			required: true
    		},
    		"contratista.contratista_exempleado": {
    			required: true
    		},
    		"contratista.contratista_fechaFin": {
    			required: true
    		},
    		"contratista.contratista_status": {
    			required: true
    		}
    		
    	},
    	messages: {
    		"contratista.contratista_nombre": {
    			required: "Nombre es obligatorio"
    		},
    		"contratista.contratista_paterno": {
    			required: "Apellido Paterno es obligatorio"
    		},
    		"contratista.contratista_materno": {
    			required: "Apellido Materno es obligatorio"
    		},
    		"contratista.contratista_curp": {
    			required: "CURP es obligatorio"
    		},
    		"contratista.contratista_nss": {
    			required: "NSS es obligatorio"
    		},
    		"contratista.contratista_nacimiento": {
    			required: "Fecha de Nacimiento es obligatorio"
    		},
    		"contratista.contratista_genero": {
    			required: "Genero es obligatorio"
    		},
    		"contratista.contratista_correo": {
    			required: "Correo es obligatorio",
    			email: "Ingresa un correo valido"
    		},
    		"contratista.contratista_edad": {
    			required: "Edad es obligatoria"
    		},
    		"contratista.contratista_fk_empresa": {
    			required: "Empresa es obligatorio"
    		},
    		"contratista.contratista_tipoEstancia": {
    			required: "Estancia es obligatorio"
    		},
    		"contratista.contratista_departamento": {
    			required: "Departamento es obligatorio"
    		},
    		"contratista.contratista_emergencia": {
    			required: "Contacto E es obligatorio"
    		},
    		"contratista.contratista_telefonoEmergencia": {
    			required: "Teléfono es obligatorio"
    		},
    		"contratista.contratista_tipoOperacion": {
    			required: "Tipo Operación es obligatorio"
    		},
    		"contratista.contratista_puesto": {
    			required: "Puesto es obligatorio"
    		},
    		"contratista.contratista_tipoContratista": {
    			required: "Tipo Contratista es obligatorio"
    		},
    		"contratista.contratista_exempleado": {
    			required: "Exempleado es obligatorio"
    		},
    		"contratista.contratista_fechaFin": {
    			required: "Fecha fin es obligatoria"
    		},
    		"contratista.contratista_status": {
    			required: "Status es obligatorio"
    		}
    		
    	},
    	errorPlacement: function (error, element) {
    		error.insertAfter($(element).parent());
    	},
    	submitHandler: function(form){
    		return false;
    	}
    });
 });
 
 /*
  * Descripcion: Limpia el formulario
  * Fecha: 19 de Septiembre de 2017
  * @author: Sergio Rojas
  * */
 function limpiarForm() {
 	$("#registrarContratistaForm").closest('form').find("input[type=text], textarea").val("");
 }
 
 /*
 * Descripción: Serializa los campos de usuario y empresa para realizar el nuevo registro
 * Fecha: 31 de agosto de 2017
 * @author: Sergio Rojas
 * */
 function agregarContratista(){
 	
 	if ($("#registrarContratistaForm").valid()){
 		var postData = $("#registrarContratistaForm").serializeArray();
 		var formURL = $("#registrarContratistaForm").attr("action");
 		$.ajax({
 			url : formURL,
 			type : "POST",
 			data : postData,
 			dataType : "json",
 			success : function(respuestaJson, textStatus, jqXHR) {
 				if (respuestaJson.exito==true){
 					toastr.success(respuestaJson.mensaje,'Registrar Contratista');
 				}else{
 					toastr.error(respuestaJson.mensaje,'Registrar Contratista');
 				}				
 			},
 			error : function(jqXHR,textStatus,errorThrown) {
 				toastr.error(respuestaJson.mensaje);
 			}
 		});
 	}
 }