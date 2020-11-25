<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    

</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Agrega un perfil a tu cuenta</div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">





        </div>
        <div class="row col-12 m-0 p-2 pt-3 d-none" id="base_modulo_RegistrarmiInstitución">
            <h3>Registrar mi Institución</h3>
            <div class="row col-12 m-0 p-0 ">
                <div class="col-sm-12 col-md-12 col-lg-6 d-flex align-items-center">
                    <form id="form_registro_institucion">
                        <div class="datos_institucion">
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">Nombre Institución:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_nombre" placeholder="Institución" required="true"/></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">Director:</label>
                                <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_nombre_director" placeholder="Nombre"  disabled="true"/></div>
                                <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_apellido_paterno_director" placeholder="Apellido Paterno"   disabled="true" /></div>
                                <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_apellido_materno_director" placeholder="Apellido Materno"   disabled="true" /></div>
                                <div class="col-sm-12 col-md-12 col-lg-12 d-none "><input type="hidden" class="form-control-plaintext input bg-light rounded" id="registro_institucion_id_usuario" placeholder=""  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">Teléfono:</label>
                                <div class="col-sm-12 col-md-9 "><input type="number" class="form-control-plaintext input bg-light rounded" id="registro_institucion_telefono" placeholder="Teléfono"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">Correo Electrónico:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_correo" placeholder="Correo Electrónico"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3  col-form-label">Turno:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_turno" placeholder="Turno"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3  col-form-label">Zona:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_zona" placeholder="Zona"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3  col-form-label">Clave:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_clave" placeholder="Clave"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">CCT:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_cct" placeholder="Clave de Centro de Trabajo"  /></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <label class="col-sm-12 col-md-3 col-form-label">Dirección:</label>
                                <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input bg-light rounded" id="registro_institucion_direccion" placeholder="Dirección"  required="true"/></div>
                            </div>
                            <div class="form-group row m-0 p-2">
                                <div class="col-sm-12 ">
                                    <input type="submit" class="btn btn-danger" value="Registrar" />
                                </div>
                            </div>
                            <input type="hidden" id="registro_institucion_lat" />
                            <input type="hidden" id="registro_institucion_lng" />
                        </div>
                    </form>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6" style="min-height: 250px;">
                    <div class="col-12 w-100 h-100 rounded" id="map2">

                    </div>
                </div>
            </div>
        </div>

        <div class="row col-12 m-0 p-2 pt-3  d-none" id="base_modulo_VincularconunaInstitución">
            <h3>Ingresa el codigo de vinculacion</h3>
            <div class="row col-12 m-0 p-0 ">
                <div class="col-sm-12 col-md-12 col-lg-6 d-flex align-items-center">
                    <div class="datos_institucion">
                        <form id="vinculacion_institucion_academica" class="form-group row m-0 p-2">
                            <!--<div class="form-group row m-0 p-2">-->
                            <label class="col-sm-12 col-md-3 col-form-label">Código:</label>
                            <div class="col-sm-12 col-md-6"><input type="text" class="form-control-plaintext input bg-light rounded" id="codigo_institucion_academica" placeholder="Ingresa el codigo de la Institución Académica" /></div>
                            <div class="col-sm-12 col-md-3 "><input type="submit" class="btn btn-danger w-100" placeholder="Institución" value="Buscar"/></div>
                            <!--</div>-->
                        </form>
                        <hr>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Nombre Institución:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_nombre" placeholder="Institución" disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Director:</label>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_nombre_director" placeholder="Nombre"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_apellido_paterno_director" placeholder="Apellido Paterno"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_apellido_materno_director" placeholder="Apellido Materno"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 d-none "><input type="hidden" class="form-control-plaintext input bg-light rounded" id="registro_institucion_id_usuario" placeholder=""  required="true"/></div>

                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Teléfono:</label>
                            <div class="col-sm-12 col-md-9 "><input type="number" class="form-control-plaintext input" id="vinculacion_institucion_telefono" placeholder="Teléfono"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Correo Electrónico:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_correo" placeholder="Correo Electrónico"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Turno:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_turno" placeholder="Turno"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Zona:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_zona" placeholder="Zona"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Clave:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_clave" placeholder="Clave"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">CCT:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_cct" placeholder="Clave de Centro de Trabajo"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Dirección:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_institucion_direccion" placeholder="Dirección"  disabled="true"/></div>

                        </div>
                        <input type="hidden" id="vinculacion_institucion_lat" />
                        <input type="hidden" id="vinculacion_institucion_lng" />
                        <div class="form-group row m-0 p-2">
                            <div class="col-sm-12 "><input type="button" class="btn btn-danger" id="vincular_institucion" value="Vincular"  disabled="true"/></div>

                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6" style="min-height: 250px;">
                    <div class="col-12 w-100 h-100 rounded" id="map">

                    </div>
                </div>
            </div>
        </div>

        <div class="row col-12 m-0 p-2 pt-3  d-none" id="base_modulo_VincularmeaunGrupo">
            <h3>Ingresa el codigo de vinculacion</h3>

            <div class="row col-12 m-0 p-0 ">
                <div class="col-sm-12 col-md-12 col-lg-6 d-flex align-items-center">
                    <div class="datos_institucion pb-0">
                        <form id="vinculacion_grupo_academico" class="form-group row m-0 p-2">
                            <!--<div class="form-group row m-0 p-2">-->
                            <label class="col-sm-12 col-md-3 col-form-label">Código:</label>
                            <div class="col-sm-12 col-md-6"><input type="text" class="form-control-plaintext input bg-light rounded" id="codigo_grupo_academico" placeholder="Ingresa el codigo de la Institución Académica" /></div>
                            <div class="col-sm-12 col-md-3 "><input type="submit" class="btn btn-danger w-100" value="Buscar"/></div>
                            <!--</div>-->
                        </form>
                        <hr>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Nombre Institución:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_nombre" placeholder="Institución" disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Director:</label>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_nombre_director" placeholder="Nombre"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_apellido_paterno_director" placeholder="Apellido Paterno"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_apellido_materno_director" placeholder="Apellido Materno"  disabled="true"/></div>
                            <div class="col-sm-12 col-md-12 col-lg-12 d-none "><input type="hidden" class="form-control-plaintext input bg-light rounded" id="registro_institucion_id_usuario" placeholder=""  required="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Teléfono:</label>
                            <div class="col-sm-12 col-md-9 "><input type="number" class="form-control-plaintext input" id="vinculacion_grupo_telefono" placeholder="Teléfono"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Correo Electrónico:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_correo" placeholder="Correo Electrónico"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Turno:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_turno" placeholder="Turno"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Zona:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_zona" placeholder="Zona"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3  col-form-label">Clave:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_clave" placeholder="Clave"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">CCT:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_cct" placeholder="Clave de Centro de Trabajo"  disabled="true"/></div>
                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Dirección:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_direccion_institucion" placeholder="Dirección"  disabled="true"/></div>

                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Grupo:</label>
                            <div class="col-sm-12 col-md-9 "><input type="text" class="form-control-plaintext input" id="vinculacion_grupo_grupo" placeholder="Grupo"  disabled="true"/></div>

                        </div>
                        <div class="form-group row m-0 p-2">
                            <label class="col-sm-12 col-md-3 col-form-label">Profesores:</label>
                            <div class="col-sm-12" id="profesores_grupo">
                                <ul class="pl-3">
                                    <li>Profesor_nombre1 apellido_paterno1 apellido_materno1</li>
                                    <li>Profesor_nombre2 apellido_paterno2 apellido_materno2</li>
                                </ul>
                            </div>
                        </div>
                        <input type="hidden" id="vinculacion_grupo_lat" />
                        <input type="hidden" id="vinculacion_grupo_lng" />
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6" style="min-height: 250px;">
                    <div class="col-12 w-100 h-100 rounded" id="map3">

                    </div>
                </div>
            </div>
        </div>




    </section>

    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMaps">
    </script>
    <style>

        .menu_sidebar{
            width: 100%;
            height: 50px;
            border: solid 1px #495057;
            border-radius: 3px;
            margin: 5px 0;
            cursor: pointer;
            text-align: center;
            background: #343a40;
            align-items: center;
            padding: 0 20px;
            font: bold 1.12rem Arial;
        }
        .menu_sidebar:hover, .menu_selected{
            background: #0097a9;
        }
        .modulo_section{
            width: 100%;
            height: 100%;
            color: #343a40;
        }
        section{
            background: #f5f5f5;
        }
        #toggle{
            background: #f5f5f5;
        }
        footer{
            background-color: #40474f;
        }
        .datos_institucion{
            padding-bottom: 25%;
            width: 100%;
        }
        h1 {
            font: bold 2.1rem Arial;
            margin: 5px 0;
        }
        h2{
            font-size: 2rem;
            color: #0097a9;
            font: bold 2rem Ariel;
        }
        h5{
            font: normal 01rem Arial;
            margin: 0;
        }
        h4 {
            font: normal 1.4rem Arial;
            padding: 2px 10px;
        }
        .img_perfil{
            width: 150px; 
            height: 150px; 
            background: #6c757d; 
            border-radius: 50%;
        }
        .col-form-label {
            padding-top: calc(0.375rem + 1px);
            padding-bottom: calc(0.375rem + 1px);
            margin-bottom: 0;
            font-size: inherit;
            line-height: 1.5;
            font: bold 1.1rem Arial;
        }

        .input {
            overflow: visible;
            font: 1.1rem Arial;
            border: solid 1px #dee2e6;
            margin: 5px 0;
            padding: 5px 15px;
            border-width: 1px;
        }
        .multiselect {
            border-radius: 0px;
            border-bottom: 2px solid #d3d3d3;
            width: 96%;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 10px;
        }
        .multiselect__single {
            background: none;
            color: #303030;
            font: bold 12px arial;
        }
        .card-header {
            background: #0097a9;
            font: bold 1.1rem Arial;
            color: white;
            text-align: center;
        }
    </style>
    <script>
        agregar_menu("Registrar mi Institución");
        agregar_menu("Vincular con una Institución");
        agregar_menu("Vincularme a un Grupo");



//        listado_profesores();
        function listado_profesores() {
            let vue = new Vue({
                components: {
                    Multiselect: window.VueMultiselect.default
                },
                data: {
                    value: [],
                    options: [{
                            "nombre": "Moises",
                            "id": 1
                        },
                        {
                            "nombre": "Moises2",
                            "id": 2
                        }]
                },
                methods: {
                    customLabel(option) {
                        return  option.id.toString().padStart(4, "0") + " " + option.nombre;
                    },
                    onChange(value) {
                        console.log("change");
                        console.log(value);
                    },
                    onSelect(op) {

                        console.log(op);

                    },
                    onTouch() {
                        console.log("Open");

                    }
                }
            }).$mount('#listado_profesores');
        }

        function agregar_menu(nombre) {
            let div = document.createElement("div");
            div.className = "menu_sidebar d-flex";
            div.innerHTML = nombre;
            div.id = "menu_section_" + nombre.replace(/\s/g, "");
            $("#sidebar").append(div);

            let div2 = document.createElement("div");
            div2.className = "modulo_section d-none";
            div2.id = "modulo_section_" + nombre.replace(/\s/g, "");
            $("#contenidoSection").append(div2);

            div.addEventListener("click", function () {
                let modulos = $(".modulo_section");
                modulos.addClass("d-none");
                let menus = $(".menu_sidebar");
                menus.removeClass("menu_selected");
                $("#modulo_section_" + nombre.replace(/\s/g, "")).removeClass("d-none");
                $("#menu_section_" + nombre.replace(/\s/g, "")).addClass("menu_selected");
            });

            if ($("#base_modulo_" + nombre.replace(/\s/g, "")).length) {
                $("#base_modulo_" + nombre.replace(/\s/g, "")).removeClass("d-none");
                div2.appendChild(document.getElementById("base_modulo_" + nombre.replace(/\s/g, "")));
            }
        }
        // RELLENAR VALORES
        var cookie = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA));
        $("#registro_institucion_nombre_director").val(cookie.nombre);
        $("#registro_institucion_apellido_paterno_director").val(cookie.apellido_p);
        $("#registro_institucion_apellido_materno_director").val(cookie.apellido_m);
        $("#registro_institucion_id_usuario").val(cookie.id_usuario);


        $("#vinculacion_institucion_academica").submit(function (e) {
            e.preventDefault();
            /*Limpiar formulario*/
            if (marker !== null) {
                marker.setMap(null);
            }
            let inputs = $("[id^=vinculacion_institucion_]");
            for (var i = 0; i < inputs.length; i++) {
                inputs[i].value = "";
            }
            map.setZoom(10);
            //desabilitar boton
            $("#vincular_institucion").attr("disabled", true);
            institucion_vinculacion = null;
            /******/
            let json = buildJSON_Section("vinculacion_institucion_academica");
            console.log(json);
            let id = json.codigo_institucion_academica.split("-")[0];
            let token = json.codigo_institucion_academica.split("-")[1];
            json.id = id;
            json.token = token;
            //codigo_institucion_academica

            RequestPOST("/API/escuela/info_escuela", json).then(function (response) {
                console.log(response);

                if (response.success) {
                    //Llenar la informacion
                    let keys = Object.keys(response);
                    for (var i = 0; i < keys.length; i++) {
                        if ($("#vinculacion_institucion_" + keys[i]).length) {
                            $("#vinculacion_institucion_" + keys[i]).val(response[keys[i]]);
                        }
                    }
                    //validar coordenadas map
                    let lat = parseFloat(response.lat);
                    let lng = parseFloat(response.lng);
                    if (!isNaN(lat) && !isNaN(lng)) {
                        if (marker !== null) {
                            marker.setMap(null);

                        }
                        var latlng = {lat: lat, lng: lng};
                        marker = new google.maps.Marker({
                            position: latlng,
                            map: map,
                            draggable: false,
                            animation: google.maps.Animation.DROP
                        });
                        console.log(latlng);
                        map.setCenter(latlng);
                        map.setZoom(18);
                    }

                    //Habilitar boton de vinculacion
                    $("#vincular_institucion").attr("disabled", false);
                    delete response.date_created;
                    delete response.time_created;
                    delete response.date_updated;
                    delete response.time_updated;
                    institucion_vinculacion = response;

                } else {
                    Swal.fire({
                        title: 'Aviso',
                        text: response.mensaje
                    })
                }

            });
        });

        var institucion_vinculacion = null;
        $("#vincular_institucion").click(function () {
            institucion_vinculacion.id_usuario = cookie.id_usuario;
            console.log(institucion_vinculacion);

            RequestPOST("/API/escuela360/vinculacion_institucion", institucion_vinculacion).then(function (response) {
                console.log(response);
                Swal.fire({
                    title: 'Aviso',
                    text: response.mensaje
                }).then(function () {
                    if (response.success) {
                        let sesion = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA));
                        sesion.modulo_principal = "planeacion_docente";
                        sesion.modulos = "403,401";
                        sesion.nombre_institucion = response.nombre_institucion;
                        sesion.tipo_usuario = response.tipo_usuario;
                        sesion.tipo_servicio = response.tipo_servicio;
                        setCookie("username_v3.1_" + DEPENDENCIA, JSON.stringify(sesion), 1000);
                        let hostdir = window.location.protocol + "//" + window.location.host;
                        let path = hostdir + "/" + DEPENDENCIA + "/planeacion_docente";
                        window.location.replace(path);
                    }
                });
            });
        });

        $("#form_registro_institucion").submit(function (e) {
            e.preventDefault();
            console.log("bingo");
//            let inputs = $("[id^=registro_institucion_]");
//            let procede = true;
//            $.each(inputs, function (i) {
//                let input = $("#" + inputs[i].id);
//                if (input.val() === "") {
//                    input.css("box-shadow", "0px 0px 7px 1px #d02e2e");
//                    procede = false;
//                } else {
//                    input.removeAttr("style");
//                }
////            });
//            if (procede) {
            let json = buildJSON_Section("form_registro_institucion");
            console.log(json);
            let keys = Object.keys(json);
            for (var i = 0; i < keys.length; i++) {
                let key = keys[i].split("registro_institucion_");
                key = key[1];
                json[key] = json[keys[i]];
                delete json[keys[i]];
            }
            console.log(json);
            RequestPOST("/API/escuela360/registro_institucion", json).then(function (response) {
                console.log(response);
                Swal.fire({
                    title: 'Aviso',
                    text: response.mensaje
                }).then(function () {
                    if (response.success) {
                        let sesion = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA));
                        sesion.modulo_principal = "administracion_directiva";
                        sesion.modulos = "403,400";
                        sesion.nombre_institucion = response.nombre_institucion;
                        sesion.tipo_usuario = response.tipo_usuario;
                        sesion.tipo_servicio = response.tipo_servicio;
                        setCookie("username_v3.1_" + DEPENDENCIA, JSON.stringify(sesion), 1000);
                        let hostdir = window.location.protocol + "//" + window.location.host;
                        let path = hostdir + "/" + DEPENDENCIA + "/administracion_directiva";
                        window.location.replace(path);
                    }
                });

            });

//            } else {
//                
//            }
        });


        function datos_institucion(nombre) {
            let div = document.createElement("div");
            div.className = "row col-12 m-0 p-0 h-100";
            let div2 = document.createElement("div");
            div2.className = "col-sm-12 col-md-12 col-lg-6 d-flex align-items-center";
            let div3 = document.createElement("div");
            div3.className = "datos_institucion";
            let escuela = document.createElement("h1");
            escuela.innerHTML = "Escuela Primaria Benito Juárez" + "";
            let direccion = document.createElement("h5");
            direccion.innerHTML = "Calle Benito Juarez, Col. Roma CDMX";
            let director = document.createElement("h4");
            director.innerHTML = "Director: Enrique Juárez Hernández";
            let telefono = document.createElement("h4");
            telefono.innerHTML = "Télefono: 5512345678";
            let correo = document.createElement("h4");
            correo.innerHTML = "Correo electrónico: e.juarez@plantel.benitojuarez.com";
            let turno = document.createElement("h4");
            turno.innerHTML = "Turno: Matutino";

            let div4 = document.createElement("div");
            div4.className = "col-sm-12 col-md-12 col-lg-6";
            let div5 = document.createElement("div");
            div5.className = "col-12 w-100 h-100 rounded";
            div5.id = "map";


            div3.appendChild(escuela);
            div3.appendChild(direccion);
            div3.appendChild(document.createElement("hr"));
            div3.appendChild(director);
            div3.appendChild(telefono);
            div3.appendChild(correo);
            div3.appendChild(turno);
            div2.appendChild(div3);
            div4.appendChild(div5);
            div.appendChild(div2);
            div.appendChild(div4);
            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div);
        }

        function perfil_docente(nombre) {
            let div_contenedor = document.createElement("div");
            div_contenedor.className = "row col-12 m-0 p-0";
            let div1 = document.createElement("div");
            div1.className = 'col-12 p-0';
            let div2 = document.createElement('div');
            div2.className = 'row col-12 m-0 p-0';
            let div_img_perfil = document.createElement("div");
            div_img_perfil.className = 'col-sm-12 col-md-12 col-lg-3 p-0 d-flex align-items-center justify-content-center';
            let div_img_perfil2 = document.createElement('div');
            div_img_perfil2.className = 'img_perfil';
            let div_info_perfil = document.createElement('div');
            div_info_perfil.className = 'col-sm-12 col-md-10 col-lg-7 p-0 pt-3';
            let h2_Bienvenida = document.createElement("h2");
            h2_Bienvenida.innerHTML = 'Bienvenida';
            let h2_nombre = document.createElement('h2');
            h2_nombre.innerHTML = 'Profesora Karina Gutierrez Mendez';
            let br = document.createElement("br");
            let h4_escuela = document.createElement("h4");
            h4_escuela.className = 'p-0';
            let strong_escuela = document.createElement("strong");
            strong_escuela.innerHTML = "Escuela: ";
            h4_escuela.appendChild(strong_escuela);
            h4_escuela.innerHTML += 'Primaria Benito Juárez';
            let h4_tel = document.createElement("h4");
            h4_tel.className = 'p-0';
            let strong_tel = document.createElement("strong");
            strong_tel.innerHTML = "Télefono: ";
            h4_tel.appendChild(strong_tel);
            h4_tel.innerHTML += '5512345678';
            let h4_mail = document.createElement('h4');
            h4_mail.className = 'p-0';
            let strong_mail = document.createElement("strong");
            strong_mail.innerHTML = 'Correo electrónico: ';
            h4_mail.appendChild(strong_mail);
            h4_mail.innerHTML += 'e.juarez@plantel.benitojuarez.com';
            let div_editar = document.createElement("div");
            div_editar.className = 'col-sm-12 col-md-2 p-0';
            let boton_edit = document.createElement("button");
            boton_edit.type = 'button';
            boton_edit.className = 'btn btn-secondary';
            let i_edit = document.createElement("i");
            i_edit.className = 'fas fa-pencil-alt';
            i_edit.style = 'margin: 0 10px';
            boton_edit.appendChild(i_edit);
            boton_edit.innerHTML = "Editar";

            div_img_perfil.appendChild(div_img_perfil2);
            div_info_perfil.appendChild(h2_Bienvenida);
            div_info_perfil.appendChild(h2_nombre);
            div_info_perfil.appendChild(br);
            div_info_perfil.appendChild(br);
            div_info_perfil.appendChild(h4_escuela);
            div_info_perfil.appendChild(h4_tel);
            div_info_perfil.appendChild(h4_mail);
            div_editar.appendChild(boton_edit);

            div2.appendChild(div_img_perfil);
            div2.appendChild(div_info_perfil);
            div2.appendChild(div_editar);

            div1.appendChild(div2);

            div_contenedor.appendChild(div1);

            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contenedor);

        }

        function listado_docente(nombre) {
            //obtener servicio de listado 
            let div_contenedor = document.createElement("div");
            div_contenedor.className = 'col-12 p-0';
            let h3_titulo = document.createElement("h3");
            h3_titulo.className = 'm-3';
            h3_titulo.innerHTML = 'Plantilla Docente';
            let div1 = document.createElement("div");
            div1.className = 'row col-12 m-0 p-0';
            for (var i = 0; i < 10; i++) {
                div1.appendChild(agregar_personal_perfil({}));
            }
            div_contenedor.appendChild(h3_titulo);
            div_contenedor.appendChild(div1);

            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contenedor);

        }

        function agregar_personal_perfil(json) {

            let div2 = document.createElement("div");
            div2.className = "row col-12 m-0 p-0 mt-3 mb-3";
            let div_img = document.createElement("div");
            div_img.className = 'col-sm-12 col-md-4 col-lg-3 p-0 d-flex align-items-center justify-content-center';
            let div_img2 = document.createElement("div");
            div_img2.className = "img_perfil";
            div_img2.style = "width: 80px; height: 80px;";
            div_img.appendChild(div_img2);
            div2.appendChild(div_img);
            /*Iteracion*/
            let div_info_perfil = document.createElement("div");
            div_info_perfil.className = 'col-sm-12 col-md-8 col-lg-9 p-0 pt-3 mb-2';

            let h5_nombre = document.createElement("h5");
            h5_nombre.className = 'p-0';
            let strong_nombre = document.createElement("strong");
            strong_nombre.innerHTML = 'Profesor: ';
            h5_nombre.appendChild(strong_nombre);
            h5_nombre.innerHTML += 'Maria Fernanda Lopez Arteaga';

            let h5_telefono = document.createElement("h5");
            h5_telefono.className = 'p-0';
            let strong_telefono = document.createElement("strong");
            strong_telefono.innerHTML = 'Télefono: ';
            h5_telefono.appendChild(strong_telefono);
            h5_telefono.innerHTML += '5512345678';

            let h5_mail = document.createElement("h5");
            h5_mail.className = 'p-0';
            let strong_mail = document.createElement("strong");
            strong_mail.innerHTML = 'Correo electrónico: ';
            h5_mail.appendChild(strong_mail);
            h5_mail.innerHTML += 'e.juarez@plantel.benitojuarez.com';

            let h5_grupo = document.createElement("h5");
            h5_grupo.className = 'p-0';
            let strong_grupo = document.createElement("strong");
            strong_grupo.innerHTML = 'Grupos: ';
            h5_grupo.appendChild(strong_grupo);
            h5_grupo.innerHTML += '1A, 1B';

            div_info_perfil.appendChild(h5_nombre);
            div_info_perfil.appendChild(h5_telefono);
            div_info_perfil.appendChild(h5_mail);
            div_info_perfil.appendChild(h5_grupo);
            div2.appendChild(div_info_perfil);
            /**/
            return div2;
        }

        function registro_plantilla_laboral(nombre) {
            let div_contendor = document.createElement("div");
            div_contendor.className = 'row col-12 m-0 p-2 pt-3';
            let h3_title = document.createElement("h3");
            h3_title.innerHTML = 'Registrar nuevo personal';
            div_contendor.appendChild(h3_title);

            let div_form = document.createElement("div");
            div_form.className = 'col-12 p-0';
            let form_registro = document.createElement("form");
            form_registro.id = 'form_registro_personal';

            form_registro.appendChild(form_info("Nombre", "nombre", "text"));

            let div = document.createElement("div");
            div.className = 'form-group row m-0 p-2';
            let label = document.createElement("label");
            label.for = "apellido_paterno";
            label.className = 'col-sm-2 col-form-label';
            label.innerHTML = "Apellidos:";
            div.appendChild(label);
            let div2 = document.createElement("div");
            div2.className = 'col-sm-5';
            let input = document.createElement("input");
            input.type = "text";
            input.className = 'form-control-plaintext input';
            input.id = "apellido_paterno";
            input.placeholder = "Apellido Paterno";
            div2.appendChild(input);

            let div3 = document.createElement("div");
            div3.className = 'col-sm-5';
            let input1 = document.createElement("input");
            input1.type = "text";
            input1.className = 'form-control-plaintext  input';
            input1.id = "apellido_materno";
            input1.placeholder = "Apellido Materno";
            div3.appendChild(input1);

            div.appendChild(div2);
            div.appendChild(div3);

            form_registro.appendChild(div);

            form_registro.appendChild(form_info("Fecha Nacimiento", "fecha_nacimiento", "date"));
            form_registro.appendChild(form_info("Teléfono", "telefono", "number"));
            form_registro.appendChild(form_info("Correo Electrónico", "correo", "text"));
            form_registro.appendChild(form_info("Cedula Profesional", "cedula", "text"));
            form_registro.appendChild(form_info("CURP", "curp", "text"));
            form_registro.appendChild(form_info("RFC", "rfc", "text"));

            let div_btn = document.createElement("div");
            div_btn.className = 'form-group row m-0 p-2';
            let div_btn2 = document.createElement("div");
            div_btn2.className = 'col-sm-12';
            let btn = document.createElement('button');
            btn.type = 'submit';
            btn.className = 'btn btn-danger mb-2';
            btn.innerHTML = 'Registrar';
            div_btn2.appendChild(btn);
            div_btn.appendChild(div_btn2);

            form_registro.appendChild(div_btn);
            div_form.appendChild(form_registro);
            let hr = document.createElement("hr");
            div_form.appendChild(hr);

            div_contendor.appendChild(div_form);

            let div_doc = document.createElement("div");
            div_doc.className = 'col-12 p-0';
            let h3_doc = document.createElement("h3");
            h3_doc.innerHTML = 'Subir documento (Excel) de plantilla laboral';
            div_doc.appendChild(h3_doc);
            let form_doc = document.createElement("form");
            form_doc.id = 'form_registro_personal_file';
            form_doc.appendChild(form_info("Seleccionar Archivo", "file_plantilla_laboral", "file"));

            let div_btn_doc = document.createElement("div");
            div_btn_doc.className = 'form-group row m-0 p-2';
            let div_btn2_doc = document.createElement("div");
            div_btn2_doc.className = 'col-sm-12';
            let btn_doc = document.createElement('button');
            btn_doc.type = 'submit';
            btn_doc.className = 'btn btn-danger mb-2';
            btn_doc.innerHTML = 'Subir Archivo';
            div_btn2_doc.appendChild(btn_doc);
            div_btn_doc.appendChild(div_btn2);

            form_doc.appendChild(div_btn_doc);

            div_doc.appendChild(form_doc);

            div_contendor.appendChild(div_doc);

            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contendor);

        }
        function form_info(valor, id, tipo) {
            let div = document.createElement("div");
            div.className = 'form-group row m-0 p-2';
            let label = document.createElement("label");
            label.for = id;
            label.className = 'col-sm-2 col-form-label';
            label.innerHTML = valor + ":";
            div.appendChild(label);
            let div2 = document.createElement("div");
            div2.className = 'col-sm-10';
            let input = document.createElement("input");
            input.type = tipo;
            input.className = 'form-control-plaintext input';
            input.id = id;
            input.placeholder = valor;
            div2.appendChild(input);
            div.appendChild(div2);
            return div;
        }

        if ($("#registro_institucion_direccion").length) {
            //initMap2();

            $("#registro_institucion_direccion").focusout(function () {
                var geocoder = new google.maps.Geocoder();
                console.log($("#registro_institucion_direccion").val());
                var address = $("#registro_institucion_direccion").val();
                if (address !== "") {
                    geocoder.geocode({'address': address}, geocodeResult);
                }
            });
        }
        var mapa;
        var mapa3;
        function initMap2() {
            mapa = new google.maps.Map(document.getElementById('map2'), {zoom: 5, center: {lat: 19.503329, lng: -99.185714}/*,mapTypeId:'satellite'*/, styles: [{featureType: 'administrative', elementType: 'geometry', stylers: [{visibility: "off"}, {"weight": 1}]}, {featureType: 'administrative', elementType: 'geometry.fill', stylers: [{visibility: "on"}]}, {featureType: 'administrative', elementType: 'geometry.stroke', stylers: [{visibility: "off"}]}, {featureType: 'administrative', elementType: 'labels', stylers: [{color: '#000000'}, {visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'geometry', stylers: [{color: '#a6a6a6'}, {visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.country', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.land_parcel', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.locality', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.neighborhood', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels', stylers: [{color: '#696969'}, {visibility: "simplified"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'geometry', stylers: [{visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.province', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: "landscape", stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'geometry', stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.man_made', elementType: 'geometry', stylers: [{color: '#526081'}, {visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'road', elementType: 'geometry', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'water', elementType: 'geometry', stylers: [{color: '#F2F4F4'}, {visibility: "on"}]}, {featureType: 'water', elementType: 'labels', stylers: [{visibility: "off"}]}]});
            mapa.setTilt(45);
        }
        function initMap3() {
            mapa3 = new google.maps.Map(document.getElementById('map3'), {zoom: 5, center: {lat: 19.503329, lng: -99.185714}/*,mapTypeId:'satellite'*/, styles: [{featureType: 'administrative', elementType: 'geometry', stylers: [{visibility: "off"}, {"weight": 1}]}, {featureType: 'administrative', elementType: 'geometry.fill', stylers: [{visibility: "on"}]}, {featureType: 'administrative', elementType: 'geometry.stroke', stylers: [{visibility: "off"}]}, {featureType: 'administrative', elementType: 'labels', stylers: [{color: '#000000'}, {visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'geometry', stylers: [{color: '#a6a6a6'}, {visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.country', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.land_parcel', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.locality', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.neighborhood', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels', stylers: [{color: '#696969'}, {visibility: "simplified"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'geometry', stylers: [{visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.province', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: "landscape", stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'geometry', stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.man_made', elementType: 'geometry', stylers: [{color: '#526081'}, {visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'road', elementType: 'geometry', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'water', elementType: 'geometry', stylers: [{color: '#F2F4F4'}, {visibility: "on"}]}, {featureType: 'water', elementType: 'labels', stylers: [{visibility: "off"}]}]});
            mapa3.setTilt(45);
        }
        var marker = null;
        function geocodeResult(results, status) {
            if (status == 'OK') {
                $("#registro_institucion_lat").val(results[0].geometry.location.lat());
                $("#registro_institucion_lng").val(results[0].geometry.location.lng());
                if (marker !== null) {
                    marker.setMap(null);
                }
                marker = new google.maps.Marker({
                    position: {lat: parseFloat($("#registro_institucion_lat").val()), lng: parseFloat($("#registro_institucion_lng").val())},
                    map: mapa,
                    draggable: true,
                    animation: google.maps.Animation.DROP
                });
                var latlng = {lat: parseFloat($("#registro_institucion_lat").val()), lng: parseFloat($("#registro_institucion_lng").val())};
                console.log(latlng);
                mapa.setCenter(latlng);
                mapa.setZoom(18);
                marker.addListener('dragend', function () {
                    var lat = marker.getPosition().lat();
                    var lng = marker.getPosition().lng();
                    $("#registro_institucion_lat").val(lat);
                    $("#registro_institucion_lng").val(lng);
                });
            } else {
                alert("Geocoding no tuvo éxito debido a: " + status);
            }
        }
        function initMaps() {
            initMap();
            initMap2();
            initMap3();
        }


    </script>

</body>