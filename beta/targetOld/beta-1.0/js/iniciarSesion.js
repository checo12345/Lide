/*========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: com.mx.contratistas.contenedores
* Sub modulo: iniciarSesion.js
* Fecha de modificaci�n: 14/08/2017
* Descripci�n:  Jquery para que el usuario inicie sesi�n
* Autor: Sergio Rojas
=========================================================================
*/

var validateForm ,datosLocalizacion,datosNavegador;

$(document).ready(function() {
	datosNavegador=navigator ;
	$.getJSON('//freegeoip.net/json/?callback=?', function(data) {
		datosLocalizacion=data ;
		});
	// Busca los elementos que sean de la clase .ladda-button
    Ladda.bind('.ladda-button',{ timeout: 2000 });

    // Simula progreso en el bot�n
    Ladda.bind('.progress-demo .ladda-button',{
        callback: function( instance ){
            var progress = 0;
            var interval = setInterval( function(){
                progress = Math.min( progress + Math.random() * 0.1, 1 );
                instance.setProgress( progress );

                if( progress === 1 ){
                    instance.stop();
                    clearInterval( interval );
                }
            }, 200 );
        }
    });

    var l = $( '.ladda-button' ).ladda();

    l.click(function(){
        l.ladda( 'start' );
        setTimeout(function(){
            l.ladda('stop');
        },3000)


    });


	
    //Valida el formulario de iniciar sesi�n
    validateForm = $("#iniciarSesionForm").validate({
    	debug: false,
    	rules: {
    		"usuario.usuario_usuario": {
    			required: true
    		},
    		"usuario.usuario_contrasena": {
    			required: true
    		}
    	},
    	messages: {
    		"usuario.usuario_usuario": {
    			required: "Usuario es obligatorio"
    		},
    		"usuario.usuario_contrasena": {
    			required: "Contraseña es obligatorio"
    		}
    	},
    	errorPlacement: function (error, element) {
    		error.insertAfter($(element).parent());
    	},
    	submitHandler: function(form){
    		return false;
    	}
    });
    
  //Valida el formulario de registro nuevo
    validateForm = $("#registrarEmpresaForm").validate({
    	debug: false,
    	rules: {
    		"empresa.empresa_nombre": {
    			required: true
    		},
    		"empresa.empresa_telefono": {
    			required: true
    		},
    		"usuario.usuario_usuario": {
    			required: true
    		},
    		"usuario.usuario_contrasena": {
    			required: true
    		},
    		"usuario.usuario_nombre": {
    			required: true
    		},
    		"usuario.usuario_apellidoPaterno": {
    			required: true
    		},
    		"usuario.usuario_apellidoMaterno": {
    			required: true
    		},
    		"usuario.usuario_correo": {
    			required: true,
    			email: true
    		},
    		"usuario.usuario_telefono": {
    			required: true,
    			number:true,
    			minlength: 10,
    			maxlength: 10
    		}
    	},
    	messages: {
    		"empresa.empresa_nombre": {
    			required: "Nombre es obligatorio"
    		},
    		"empresa.empresa_telefono": {
    			required: "Telefono es obligatorio",
    			number: "Introduce un numero valido",
    			maxlength: "Debe contener 10 dígitos",
    			minlength: "Debe contener 10 dígitos"
    		},
    		"usuario.usuario_usuario": {
    			required: "Usuario es obligatorio"
    		},
    		"usuario.usuario_contrasena": {
    			required: "Contraseña es obligatorio"
    		},
    		"usuario.usuario_nombre": {
    			required: "Nombre es obligatorio"
    		},
    		"usuario.usuario_apellidoPaterno": {
    			required: "Apellido Paterno es obligatorio"
    		},
    		"usuario.usuario_apellidoMaterno": {
    			required: "Apellido Materno es obligatorio"
    		},
    		"usuario.usuario_correo": {
    			required: "Correo es obligatorio",
    			email: "Ingresa un correo valido"
    		},
    		"usuario.usuario_telefono": {
    			required: "Telefono es obligatorio",
    			number: "Introduce un numero valido",
    			maxlength: "Debe contener 10 dígitos",
    			minlength: "Debe contener 10 dígitos"
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
* Descripci�n: Obtiene los campos de usuario y contrasenia para validar usuario
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function iniciarSesion() {
	
	if ($("#iniciarSesionForm").valid()){
		obtenerDatosCliente() ;
		var postData = $("#iniciarSesionForm").serializeArray();
		var formURL = $("#iniciarSesionForm").attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				if (respuestaJson.exito)
					irMenuPrincipal() ;
				else
					toastr.error(respuestaJson.mensaje,"Iniciar Sesion");

			},
			error : function(jqXHR,textStatus,errorThrown) {
				toastr.error(respuestaJson.mensaje,"Iniciar Sesion");
			}
		});
	}
	
}

/*
* Descripci�n: Funcion para llevar un registro de los clientes que se conectan a la aplicacion
* Fecha: 13 de Octubre de 2017
* @author: Sergio Rojas
* */

function obtenerDatosCliente()
{
	$("#ip").val(datosLocalizacion.ip) ;
	$("#navegador").val(datosNavegador.userAgent) ;
	$("#idioma").val(datosNavegador.language) ;
	$("#codigoPais").val(datosLocalizacion.country_code) ;
	$("#nombrePais").val(datosLocalizacion.country_name) ;
	$("#nombreRegion").val(datosLocalizacion.region_name) ;
	$("#nombreCiudad").val(datosLocalizacion.city) ;
	$("#latitude").val(datosLocalizacion.latitude) ;
	$("#longuitud").val(datosLocalizacion.longitude) ;
}
/*
* Descripci�n: Ventana modal para recuperar la contraseña
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function modalRecuperar(){

    $(".enviar").show();
    $(".form-control").removeClass('error');
    $("label.error").remove();
    $("#mdl-recuperar-pwd").modal();
}

/*
* Descripci�n: Obtiene los campos de usuario para recuperar la contraseña
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function recuperarCuenta(){
	var postData = $("#recuperarCuentaForm").serializeArray();
	var formURL = $("#recuperarCuentaForm").attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		dataType : "json",
		success : function(jsonResponse, textStatus, jqXHR) {
			if (jsonResponse.exito==true){
				$("#mdl-recuperar-pwd").modal('hide');
				toastr.success(jsonResponse.mensaje,'Recuperar Cuenta');
			}else{
				$("#mdl-recuperar-pwd").modal('hide');
				toastr.error(jsonResponse.mensaje,'Recuperar Cuenta');
			}				
		},
		error : function(jqXHR,textStatus,errorThrown) {
			$("#mdl-recuperar-pwd").modal('hide');
			toastr.error(jsonResponse.mensaje,'Recuperar Cuenta');
		}
	});
}

/*
* Descripci�n: Ventaa modal para mostrar formulario de nuevo registro
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function modalRegistrarEmpresa(){
    $("#mdl-registrarEmpresa").modal();
}

/*
* Descripci�n: Obtiene los campos de usuario y empresa para realizar el nuevo registro
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function registrarEmpresa(){
	
	if ($("#registrarEmpresaForm").valid()){
		var postData = $("#registrarEmpresaForm").serializeArray();
		var formURL = $("#registrarEmpresaForm").attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : "json",
			success : function(respuestaJson, textStatus, jqXHR) {
				if (respuestaJson.exito==true){
					$("#mdl-registrarEmpresa").modal('hide');
					toastr.success(respuestaJson.mensaje,'Registrar Cuenta');
				}else{
					toastr.error(respuestaJson.mensaje,'Registrar Cuenta');
				}				
			},
			error : function(jqXHR,textStatus,errorThrown) {
				$("#mdl-registrarEmpresa").modal('hide');
				toastr.error(respuestaJson.mensaje);
			}
		});
	}
}

/*
* Descripci�n: Redirecciona al menu principal
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function irMenuPrincipal() {
	window.location = "menuPrincipal.action";
} 

/*
* Descripci�n: Cierra la sesi�n actual del usuario
* Fecha: 31 de agosto de 2017
* @author: Sergio Rojas
* */
function cerrarSesion() {
	window.location = "cerrarSesion.action";
} 


/*
* Descripci�n: Funcion para abrir la camara de la computadora y tomar la foto
* Fecha: 20 de Seltiembre de 2017
* @author: Sergio Rojas
* */
function iniciarCamara(){
		
	var v ;
	
	(function(){
	        function userMedia(){
	            return navigator.getUserMedia = navigator.getUserMedia ||
	            navigator.webkitGetUserMedia ||
	            navigator.mozGetUserMedia ||
	            navigator.msGetUserMedia || null;

	        }

	        // Now we can use it
	        if( userMedia() ){

	            var constraints = {
	                video: true,
	                audio:false
	            };

	            var media = navigator.getUserMedia(constraints, function(stream){
	                v = document.getElementById('v');
	                // URL Object is different in WebKit
	                var url = window.URL || window.webkitURL;
	                canvas = document.getElementById("canvas") ;
	        		context = canvas.getContext("2d") ;
	                // create the url and set the source of the video element
	                v.src = url ? url.createObjectURL(stream) : stream;

	                // Start the video
	                v.play();
	            }, function(error){
	                console.log("ERROR");
	                console.log(error);
	            });
	        } else {
	            console.log("KO");
	        }
	    })();

	$("#mdl-generico").modal() ;
}

function tomarFoto() {
	context.drawImage(v, 0, 0, 330, 360, 0, 0, 120, 130);
	dataUrl = canvas.toDataURL("image/jpeg");
	$('#fotoTomada').attr('src', dataUrl);
	var info = dataUrl.split(",", 2);
	$("#fotoBase64").val(info[1]);
	$('#fotoBase64').attr("name", "usuario.usuario_fotoBase64");
	$("#mdl-generico").modal('hide');
}

	