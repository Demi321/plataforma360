<%-- 
    Document   : modulo_reporte
    Created on : 24/11/2020, 10:31:17 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_NuevoReporte">
    <h3>Reporte de Evento</h3>

    <div class="row col-12 m-0 p-2 pt-3">

        <div class="col-12 col-sm-12 col-md-8 p-0">
            <h5 class="nombre_completo"></h5>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Fecha:</label>
                <div class="col-sm-3"><input type="date" class="form-control-plaintext input" id="fecha_reporte" placeholder="Fecha" disabled="true"/></div>
                <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Hora:</label>
                <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="hora_reporte" placeholder="Hora" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Asunto</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="asunto_reporte" placeholder="Asunto" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Categoria:</label>
                <div class="col-sm-10">
                    <select class="form-control"  id="categoria" placeholder="Seleccione uno" required="">
                        <option disabled="" selected="" value="">Selecciona una opción</option>
                    </select>
                </div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Reporte</label>
                <div class="col-sm-10"><textarea  id="reporte_reporte_evento" placeholder="Redacta tu reporte"></textarea></div>
            </div>

            <div class="form-group row m-0 p-2 ">

                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Dirección:</label>
                <div class="col-sm-10" >
                    <input type="text" class="form-control-plaintext input" id="d_autocompletar" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true"/>
                </div>

            </div>
        </div>
        <div class="row m-0 col-12 col-sm-12 col-md-4 p-0" style="
             min-height: 500px;
             ">
            <div class="row col-12 m-0 p-0 p-1 bg-secondary color-white rounded mb-3" style="cursor:pointer;" id="evidencia_evento">
                <h5 class="text-white" style="
                    font-size: 1.5rem;
                    width: 100%;
                    align-items: center;
                    justify-content: center;
                    ">Agregar Evidencia Fotografica 
                </h5>
                <div class="col-12 m-0 h-75 d-flex justify-content-center align-items-center" id="cont_img_evento">
                    <i style="font-size: 8rem;color: white;" class="fas fa-image"></i>
                </div>
                <div class="col-12 m-0 h-25 d-none">
                    <input type="file" id="img_reporte_evento">
                </div>
            </div>
            <div class="col-12 p-1" id="map"></div>
        </div>
        <div class="col-12 col-sm-12" style="
             min-height: 30px;
             ">
            <input id="guardar_reporte_evento" type="button" class="btn btn-danger" value="Guardar Reporte"/>
        </div>
    </div>
</div>