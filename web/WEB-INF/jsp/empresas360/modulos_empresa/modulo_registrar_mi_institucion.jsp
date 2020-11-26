<%-- 
    Document   : modulo_registrar_mi_institucion
    Created on : 24/11/2020, 10:38:39 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistrarmiInstitución">
    <h3>Registrar mi Institucón</h3>
    <div class="col-12 p-0">
        <form id="form_registro_institucion">
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Nombre Institución:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_institucion" placeholder="Institución" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Director:</label>
                <div class="col-sm-4"><input type="text" class="form-control-plaintext input" id="nombre_director" placeholder="Nombre" /></div>
                <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_paterno_director" placeholder="Apellido Paterno" /></div>
                <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_materno_director" placeholder="Apellido Materno" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Teléfono:</label>
                <div class="col-sm-10"><input type="number" class="form-control-plaintext input" id="telefono_institucion" placeholder="Teléfono" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Correo Electrónico:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="correo_institucion" placeholder="Correo Electrónico" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Turno:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="turno" placeholder="Turno" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Zona:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="zona" placeholder="Zona" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Clave:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="clave" placeholder="Clave" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">CCT:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="cct" placeholder="Clave de Centro de Trabajo" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label">Dirección:</label>
                <div class="col-sm-10 col-md-8 col-lg-9"><input type="text" class="form-control-plaintext input" id="direccion_institucion" placeholder="Dirección" /></div>
                <input type="button" class="col-sm-12 col-md-2 col-lg-1 btn btn-danger" value="Buscar" id="buscar_direccion">
            </div>
            <div class="form-group row m-0 p-2"></div>
            <div class="col-sm-12 col-md-12 col-lg-6" style="height: 250px;">
                <div class="col-12 w-100 h-100 rounded" id="map21"></div>
            </div>
            <input type="hidden" id="lat" />
            <input type="hidden" id="lng" />
        </form>
    </div>
</div>