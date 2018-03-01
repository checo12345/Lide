


<div id="mdl-generico" class="modal small inmodal fade modaltext" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b" id="tituloDepartamentoForm">TOMAR FOTOGRAFIA</h1>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" id="modalDinamico"> 
            	<video id="v" width="720" autoplay></video>      
            </div>
            <div class="modal-footer">
 				<button type="button" class="btn btn-primary block m-b pull-right" id="tomarFoto" onclick="tomarFoto()" style="margin-left: 10px;">Capturar</button>
				<button type="button" class="btn block m-b pull-right" id="cancelarFoto" onclick="cancelarFoto()">Cancelar</button>
            </div>
        </div>
    </div>
</div>