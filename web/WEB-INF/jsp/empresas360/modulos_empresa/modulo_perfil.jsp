<%-- 
    Document   : modulo_perfil_empresa
    Created on : 24/11/2020, 10:17:57 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_MiPerfil">
    <h3>Mi Perfil</h3>

    <div class="row col-12 m-0 p-2 pt-3">
        <div class="col-12 col-sm-12 col-md-4 p-0" style="
             min-height: 300px;
             ">
            <div class="img_perfil" id="img"></div>
        </div>
        <div class="col-12 col-sm-12 col-md-8 p-0">
            <h4>Bienvenid@</h4>
            <h5 class="nombre_completo"></h5>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Empresa:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_empresa" placeholder="Empresa" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Sucursal:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_sucursal" placeholder="Sucursal" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Área:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_area" placeholder="Área" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Puesto:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="puesto" placeholder="Puesto" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Número de empleado:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="num_empleado" placeholder="Número de empleado" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Jornada:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Entrada:</label>
                <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="horario_entrada" placeholder="Entrada" /></div>
                <label class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Salida</label>
                <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="horario_salida" placeholder="Salida" /></div>
            </div>

        </div>
        <div class="col-12 col-sm-12" style="
             min-height: 30px;
             ">
        </div>
    </div>
</div>