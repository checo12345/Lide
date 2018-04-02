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

var validateForm ,datosLocalizacion,datosNavegador, codigo;

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


});

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


var GoogleAuth;
var SCOPE = 'https://www.googleapis.com/auth/drive.metadata.readonly';
function handleClientLoad() {
  // Load the API's client and auth2 modules.
  // Call the initClient function after the modules load.
  gapi.load('client:auth2', initClient);
}

function initClient() {
  // Retrieve the discovery document for version 3 of Google Drive API.
  // In practice, your app can retrieve one or more discovery documents.
  var discoveryUrl = 'https://www.googleapis.com/discovery/v1/apis/drive/v3/rest';

  // Initialize the gapi.client object, which app uses to make API requests.
  // Get API key and client ID from API Console.
  // 'scope' field specifies space-delimited list of access scopes.
  gapi.client.init({
      'apiKey': 'AIzaSyBr30_EsTD_Nz6KHG7Biff0NCw-2r7v7PI',
      'discoveryDocs': [discoveryUrl],
      'clientId': '299646937934-39jf1363b3e0fs212vn8q285t6duo396.apps.googleusercontent.com',
      'scope': SCOPE
  }).then(function () {
    GoogleAuth = gapi.auth2.getAuthInstance();

    // Listen for sign-in state changes.
    GoogleAuth.isSignedIn.listen(updateSigninStatus);

    // Handle initial sign-in state. (Determine if user is already signed in.)
    debugger
    var user = GoogleAuth.currentUser.get();
    setSigninStatus();
    codigo= user.EL ;
    // Call handleAuthClick function when user clicks on
    //      "Sign In/Authorize" button.
    $('#sign-in-or-out-button').click(function() {
      handleAuthClick();
    }); 
    $('#revoke-access-button').click(function() {
      revokeAccess();
    }); 
  });
}

function handleAuthClick() {
  if (GoogleAuth.isSignedIn.get()) {
    // User is authorized and has clicked 'Sign out' button.
    GoogleAuth.signOut();
  } else {
    // User is not signed in. Start Google auth flow.
    GoogleAuth.signIn();
  }
}

function revokeAccess() {
  GoogleAuth.disconnect();
}

function setSigninStatus(isSignedIn) {
	debugger
  var user = GoogleAuth.currentUser.get();
  var isAuthorized = user.hasGrantedScopes(SCOPE);
  if (isAuthorized) {
	  GoogleAuth.grantOfflineAccess().then(signInCallback);
	  
  } else {
    $('#sign-in-or-out-button').html('Sign In/Authorize');
    $('#revoke-access-button').css('display', 'none');
    $('#auth-status').html('You have not authorized this app or you are ' +
        'signed out.');
  }
}

function signInCallback(authResult) {
	debugger
	$.ajax({
		url : "validarSesion.action",
		type : "POST",
		dataType : "json",
		data:	{codigo:authResult['code']},
		success : function(respuestaHtml, textStatus, jqXHR) {
			toastr.success("No se encontro",'Departamento');
		},
		error : function(jqXHR,textStatus,errorThrown) {
			toastr.error("No se encontro",'Departamento');
		}
	});
  
}

function updateSigninStatus(isSignedIn) {
  setSigninStatus();
}

function probarGoogle() {
	
	//Example 2: Use gapi.client.request(args) function
	var request = gapi.client.request({
	  'method': 'POST',
	  'path': 'http://localhost:8080/_ah/api/customerApi/v1/getCoverageAreas',
	  'params': {'fields': 'user'}
	});
	// Execute the API request.
	request.execute(function(response) {
	  console.log(response);
	});
	
}
	