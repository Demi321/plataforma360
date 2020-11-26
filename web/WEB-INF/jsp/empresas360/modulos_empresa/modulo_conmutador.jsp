<%-- 
    Document   : modulo_conmutador
    Created on : 24/11/2020, 10:23:34 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-0 h-100" id="base_modulo_Conmutador">

    <div class="row col-12 m-0 p-0 h-100">
        <div class="row m-0 col-12 col-sm-12 col-md-3 p-0 h-100">
            <div class="row m-0 col-12 col-sm-12 p-0 h-50">
                <h6>Participantes</h6>
                <div class="col-6 col-sm-6 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                    <input type="button" class="btn btn-info" value="Agregar participante" />
                </div>
                <div class="col-6 col-sm-6 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                    <input type="button" class="btn btn-info" value="Enviar invitacion por correo" />
                </div>
                <div class="col-12 col-sm-12 p-0" id="listado_participantes" style="height: calc(100% - 80px); border: solid 1px #d3d3d3; border-radius: 15px;"></div>
            </div>
            <div class="row m-0 col-12 col-sm-12 p-0 h-50">
                <div class="form-group row col-12 m-0 p-2" style="height: 70px;">
                    <label class="col-sm-12 col-form-label d-flex justify-content-start align-items-center" style="height: 30px;">Proyecto:</label>
                    <div class="col-sm-12 p-0" style="height: 30px;"><input type="text" class="form-control-plaintext input" id="asunto_proyecto" placeholder="Proyecto" /></div>
                </div>

                <div class="form-group row col-12 m-0 p-2" style="height: calc(100% - 70px);">
                    <label class="col-sm-12 col-form-label d-flex justify-content-start align-items-center" style="height: 30px;">Minuta:</label>

                    <div class="col-sm-12 p-0" style="height: calc(100% - 80px);"><textarea id="reporte" placeholder="Minuta de reunión" style="height: 100%;"></textarea></div>
                    <div class="col-sm-12 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                        <input type="button" class="btn btn-danger w-100" value="Guardar" />
                    </div>
                </div>
            </div>
        </div>
        <div class="row m-0 col-12 col-sm-12 col-md-9 p-0 pl-3">
            <div id="videos">
                <div class="p-0 row GRIDcontainer" id="GRID">
                    <input type="button" id="maximizarVideo" class="maximizarVideo" />
                </div>

                <div id="publisher"></div>
                <div class="row col-12 m-0 p-0" id="menu_botones" style="background: rgb(52, 58, 64); position: absolute; bottom: 0px; left: calc(50% - 100px); width: 300px; border-top-left-radius: 50px; border-top-right-radius: 50px;">

                </div>
            </div>
        </div>
    </div>
</div>