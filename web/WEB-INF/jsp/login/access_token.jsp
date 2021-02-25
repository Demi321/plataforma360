<%-- 
    Document   : Login
    Created on : 2/10/2018, 05:02:03 PM
    Author     : Vostro Placas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<head>
    <%@include file="../plantilla/head_login.jsp" %>  
    <title>Inicio de Sesión</title>
</head>
<body>
    <%@include file="../plantilla/header_vacio.jsp" %>
    <input type="hidden" id="cuenta360" value="${cuenta360}">
    <div class="formulario" style="border: none;">
        <!--h2>Sistema de Monitoreo de Emergencias</h2-->
        <h2></h2>
        <form class="form-group col-12 m-0" method="POST" id="Log-in">

            <label for="usr"  class="" style="display: table; margin-left: auto; margin-right: auto; font: 15px Arial;">Usuario:</label>
            <input type="text" name="usr" class="form-control" id="usuario" style="color: #495057; background-color: #fff;">
            <label for="pwd" class="" style="display: table; margin-left: auto; margin-right: auto; font: 15px Arial;">Contraseña:</label>
            <input type="password" name="pwd" class="form-control" id="contra" style="color: #495057; background-color: #fff;">
            <button type="submit" class="btn btn-default" name="enviar" style="border-radius: 20px; width: 150px; margin-left: auto;  margin-right: auto;">Entrar</button>
            <p class="text-center"><a href="/lineamientos">Registrarse</a></p>
            <span   style="font-size: 13px; margin-left: auto; margin-right: auto; display: table;">Las cookies y notificaciones deben de estar activadas en el navegador.</span>
        </form>
        <div style="    display: block;
             margin-top: -25px;
             position: absolute;
             z-index: 10000;
             height: 30px;
             width: 100%;">
            <span id="span" style="color: white;
                  background-color: #ff8200;
                  height: 30px;
                  justify-content: center;
                  font: 12px Arial;
                  padding-top: 8px;
                  border-bottom-right-radius: 10px;
                  border-bottom-left-radius: 10px;
                  display: none;"  >${Alerta}</span>
        </div>

    </div> 

    <div class="d-none vista_completa" id="seleccionar_institucion" >
        <div class="listado_instituciones row ">
            <div class="row col-12 m-0 p-0 h-100">
                <div class="row col-12 m-0 p-4 body">
                    <div class="col-12 title">Selecciona con que cuenta quieres continuar</div>
                    <div class="col-12 content"id="catalogo_instituciones"></div>
                </div>
                <div class="col-12 m-0 p-0 butons">
                    <input type="button" class="btn btn-danger boton m-0" style="border: none;" value="continuar" id="boton_seleccionar_institucion">
                </div>
            </div>
        </div>
    </div>
    <div class="d-none vista_completa" id="registrar_modulo" >
        <div class="listado_instituciones row ">
            <div class="row col-12 m-0 p-0 h-100">
                <div class="row col-12 m-0 p-4 body">
                    <div class="col-12 title">Parece que no cuentas con un perfil para continuar, Registralo.</div>
                    <div class="col-12 content"id="catalogo_instituciones">
                        <form class="row col mx-auto p-2 " style="
                              width: 50%;
                              min-width: 400px;
                              margin-top: 10%;
                              " id="registrar_nuevo_modulo">
                            <div class="form-group row m-0 mb-3 mt-3 col-12">
                                <label class="col-4 d-flex align-items-center m-0  text-white" for="estado">Estado:</label>
                                <select class="form-control col-8" id="estados">
                                    <option>Default select</option>
                                </select>
                            </div>
                            <div class="form-group row m-0 mb-3 mt-3 col-12">
                                <label class="col-4 d-flex align-items-center m-0  text-white" for="tipo_usuario">Dependencia:</label>
                                <select class="form-control col-8" id="tipo_usuario">
                                    <option>Default select</option>
                                </select>
                            </div>
                            <div class="form-group row m-0 mb-3 mt-3 col-12">
                                <label class="col-4 d-flex align-items-center m-0  text-white" for="tipo_servicio">Institución:</label>
                                <select class="form-control col-8" id="tipo_servicio">
                                    <option>Default select</option>
                                </select>
                            </div>
                            <div class="form-group col-12">
                                <input type="submit" value="Registrar" class="w-50 btn btn-danger" style="
                                       margin: auto;
                                       margin-top: 30px;
                                       ">
                            </div>
                        </form>



                    </div>
                </div>
                <div class="col-12 m-0 p-0 butons">
                    <input type="button" class="btn btn-danger boton m-0" style="border: none;" value="Regresar" id="boton_regresar">
                </div>
            </div>
        </div>
    </div>
    <div class="d-none vista_completa" id="listar_modulos_externos" >
        <div class="listado_instituciones row ">
            <div class="row col-12 m-0 p-0 h-100">
                <div class="row col-12 m-0 p-4 body">
                    <div class="col-12 title">Estos son los módulos con los que cuenta. Seleccione uno ó Registre uno nuevo</div>
                    <div class="col-12 content"id="lista_modulos_externos">

                    </div>
                </div>
                <div class="col-12 m-0 p-0 butons">
                    <input type="button" class="btn btn-danger boton m-0 mr-5" style="border: none;" value="Continuar" id="continuar_modulo">
                    <input type="button" class="btn btn-danger boton m-0" style="border: none;" value="Registrar" id="registrar_modulo_nuevo">
                </div>
            </div>
        </div>
    </div>

    <%@include file="../plantilla/footer.jsp" %>

    <%@include file="../plantilla/callhead_login_accesstoken.jsp" %>
    <link href="${pathRecursos}/css/form-index.css" rel="stylesheet" />
    <script>
        if ($("#cuenta360").length) {
            if ($("#cuenta360").val() !== "") {
                sesion_cookie = JSON.parse($("#cuenta360").val());
                if (sesion_cookie.plataforma360.length > 0) {
                    document.getElementById("span").style.display = "none";
                    let plataforma360 = false;
                    let count_plataforma360 = 0;
                    let frst_id = null;
                    for (var i = 0; i < sesion_cookie.plataforma360.length; i++) {
                        let institucion = sesion_cookie.plataforma360[i];
                        console.log(institucion);
                        /*Solo para dev*/
                        institucion.url = "https://empresas.claro360.com/plataforma360_dev_moises/";
                        /***************/
                        if (institucion.url === window.location.protocol + "//" + window.location.host + '/' + DEPENDENCIA + '/') {
                            plataforma360 = true;
                            count_plataforma360++;
                            if (/*count_plataforma360 === 1*/ i === 0) {
                                frst_id = institucion.id;
                            }
                            listar_institucion(institucion);
                            $("#institucions_listado" + frst_id).click();
                        }
                    }
                    if (plataforma360) {
                        $("#seleccionar_institucion").removeClass("d-none");
                        if (count_plataforma360 === 1) {
                            $("#institucions_listado" + frst_id).click();
                        }

                        document.getElementById("boton_seleccionar_institucion").addEventListener("click", continuar_institucion_seleccionada);
                        console.log(count_plataforma360);
                        if (count_plataforma360 === 1) {
                            document.getElementById("boton_seleccionar_institucion").click();
                        }
                    } else {
                        if (window.location.host.toString().includes("escuela360")) {
                            //cargar modulo de empresa.
                            acceso_escuela360(sesion_cookie)
                        } else {
                            $("#listar_modulos_externos").removeClass("d-none");
                            listar_modulos_externos();
//                        registrar_modulo();
//                            Swal.fire({
//                                title: 'Cuenta sin acceso.',
//                                text: 'Actualmente no tienes acceso a esta plataforma.'
//                            });
                        }
                    }


//                $("#boton_seleccionar_institucion").click(continuar_institucion_seleccionada());

                } else {
//                    Swal.fire({
//                        title: 'Cuenta sin acceso.',
//                        text: 'Actualmente no tienes acceso a esta plataforma.'
//                    });
                    registrar_modulo();

                }


                //console.log(config);
            }
        }

        let modulo_seleccionado = null;

        function listar_modulos_externos() {
            let modulos_p360 = sesion_cookie.plataforma360;
            let modulos = new Array();
            let token = sesion_cookie.token;
            let id360 = sesion_cookie.idUsuario_Sys;
            $.each(modulos_p360, function (i) {
                if (modulos_p360[i].url.toString().includes("sos911")) {
                    if (!modulos.includes(modulos_p360[i].url)) {
                        modulos.push(modulos_p360[i].url);
                        let div = document.createElement("div");
                        div.className = "row col-12 institucion_listado";
                        div.id = "listado_modulo_externo_" + modulos_p360[i].id;
                        let logo = document.createElement("div");
                        logo.className = "col";
                        logo.style = "border-radius:5px;padding: 40px;max-width: 40px;background-image:url(" + "https://sos911.ml/p360/Img/Logos/Claro%20360.png" + ");background-size: contain;background-repeat: no-repeat; background-position: center;";
                        let info = document.createElement("div");
                        info.className = "col";
                        info.style = "color: white; font: bold 1rem Arial; display: flex;  align-items: center; padding-left: 3rem;";
                        let content_data = document.createElement("div");
                        content_data.className = "row m-0 p-0 col-12";
                        let alias = document.createElement("div");
                        alias.className = "col-12";
                        alias.innerHTML = modulos_p360[i].alias;

//                    let nombre = document.createElement("div");
//                    nombre.className = "col-12";
//                    nombre.innerHTML = modulos_p360[i].nombre + " " + modulos_p360[i].apellido_m + " " + modulos_p360[i].apellido_p;
//                    nombre.style = "color: white; font: normal 0.8rem Arial; display: flex;  align-items: center;";

                        content_data.appendChild(alias);
//                    content_data.appendChild(nombre);

                        info.appendChild(content_data);
                        div.appendChild(logo);
                        div.appendChild(info);
                        $("#lista_modulos_externos").append(div);
                        $("#lista_modulos_externos").append('<hr class="division mb-2 mt-2">');
                        listener_lista_modulos(modulos_p360[i]);
                    }
                }
            });
            console.log(modulos);
            $("#continuar_modulo").click(function () {
                console.log("bingo");
                if (modulo_seleccionado !== null) {
                    //Hacemos el request por acces token a l modulo seleccionado
                    RequestPOST("/API/cuenta360/access_token", {
                        "token": token,
                        "id360": id360
                    }).then(function (response) {
                        if (response.success) {
                            let path = modulo_seleccionado.url + "API/cuenta360/access_token/" + id360 + "/" + response.access_token;
                            window.location.replace(path);
                        }

                    });
                }
            });
            $("#registrar_modulo_nuevo").click(function () {
                console.log("bingo");
                $("#listar_modulos_externos").addClass("d-none");
                registrar_modulo();
            });
        }

        function listener_lista_modulos(json) {
            $("#listado_modulo_externo_" + json.id).click(function () {
                console.log("bingo");
                $('[id^=listado_modulo_externo_]').removeClass("institucion_seleccionada");
                $("#listado_modulo_externo_" + json.id).addClass("row col-12 institucion_listado institucion_seleccionada");
                modulo_seleccionado = json;
            });
        }
        $("#boton_regresar").click(function () {
            $("#registrar_modulo").addClass("d-none");
            $("#listar_modulos_externos").removeClass("d-none");
        });

        function registrar_modulo() {
            document.getElementById("span").style.display = "none";

            if (window.location.host.toString().includes("sos")) {
                console.log("sos");
                $("#registrar_modulo").removeClass("d-none");
                modulo_sos();
            }
            if (window.location.host.toString().includes("empresas")) {
                //cargar modulo de empresa.
                RequestPOST("/API/cuenta360/access_token", {
                    "token": sesion_cookie.token,
                    "id360": sesion_cookie.idUsuario_Sys
                }).then(function (response) {
                    if (response.success) {
                        //access_token
//                                    let hostdir = window.location.protocol + "//" + window.location.host+ "/" + DEPENDENCIA + "/";
                        let path = "https://seguridadsanitaria.claro360.com/lineamientos/API/cuenta360/access_token/" + sesion_cookie.idUsuario_Sys + "/" + response.access_token;
//            window.location.replace(path);
                        window.location.replace(path);
                    }

                });

            }
            if (window.location.host.toString().includes("escuela360")) {
                //cargar modulo de empresa.
                acceso_escuela360(sesion_cookie)
            }
        }
        function modulo_sos() {
            //cargar modulos de sos.
            //listado de servicios disponibles 
            RequestGET("/API/JSON/RegistroPlataforma360").then(function (estados) {
                //llenar los estados//
                console.log(estados);
                plataformas_activas(estados);


            });
        }
        function acceso_escuela360(sesion_cookie) {
            let keys = Object.keys(sesion_cookie);
            let modulos_externos = new Array();
            let url_modulos = new Array();
            for (let i = 0; i < keys.length; i++) {
                let a = typeof (sesion_cookie[keys[i]]);
                if (a === "object" && (sesion_cookie[keys[i]] !== null && sesion_cookie[keys[i]] !== "null" && sesion_cookie[keys[i]] !== undefined)) {
                    if (sesion_cookie[keys[i]].length) {
                        for (var j = 0; j < sesion_cookie[keys[i]].length; j++) {
                            if (sesion_cookie[keys[i]][j].plataforma === "1") {
                                if (!url_modulos.includes(sesion_cookie[keys[i]][j].url)) {
                                    url_modulos.push(sesion_cookie[keys[i]][j].url);
                                    if (sesion_cookie[keys[i]][j].alias) {
                                        let nuevo_modulo = [sesion_cookie[keys[i]][j].alias, sesion_cookie[keys[i]][j].url];
                                        modulos_externos.push(nuevo_modulo);
                                    } else {
                                        let nuevo_modulo = [keys[i], sesion_cookie[keys[i]][j].url];
                                        modulos_externos.push(nuevo_modulo);
                                    }

                                }
                            }
                        }
                    }
                    delete sesion_cookie[keys[i]];

                }
            }
            sesion_cookie.modulos_externos = modulos_externos;
            console.log(sesion_cookie);
            sesion_cookie.modulo_principal = "agregar_perfil";
            sesion_cookie.modulos = "";
            setCookie("username_v3.2_" + DEPENDENCIA, JSON.stringify(sesion_cookie), 1000);
        }
        function plataformas_activas(estados) {//XD
            $("#estados").empty();

            let keys = Object.keys(estados);

            let option = document.createElement("option");
            option.innerHTML = "Seleccionar una opción";
            $("#estados").append(option);
            for (var i = 0; i < keys.length; i++) {
                option = document.createElement("option");
                option.value = estados[keys[i]].url + "/";
                option.innerHTML = estados[keys[i]].alias;
                $("#estados").append(option);
                //<option value="">Opcion 1</option>
            }
            $("#estados").on('change', function () {
                let v = $(this).children("option:selected").val();
                $("#tipo_usuario").empty();
                $("#tipo_servicio").empty();
                if (v !== "") {
                    console.log(v);
                    //traer tipos de usuario 
                    tipos_usuarios(v);
                }
            });
        }
        var servicioUsuario = null;
        function tipos_usuarios(url) {
            ///dale 
            RequestPOST("/API/trae_servicios/esterno", {"url": url}).then(function (servicios) {
                //dale
                servicioUsuario = servicios;
                $("#tipo_usuario").empty();
                $("#tipo_servicio").empty();
                let tipos_usuario = servicios.tipos_usuarios;
                let tipos_servicio = servicios.servicios_usuarios;
                let option_tu = document.createElement("option");
                option_tu.innerHTML = "Seleccionar una opción";
                $("#tipo_usuario").append(option_tu);
                $.each(tipos_usuario, function (i) {
                    option_tu = document.createElement("option");
                    option_tu.value = tipos_usuario[i].id;
                    option_tu.innerHTML = tipos_usuario[i].tipo_usuario;
                    $("#tipo_usuario").append(option_tu);
                });
                $("#tipo_usuario").on("change", function () {
                    $("#tipo_servicio").empty();
                    let option_ts = document.createElement("option");
                    option_ts.innerHTML = "Seleccionar una opción";
                    $("#tipo_servicio").append(option_ts);
                    $.each(tipos_servicio, function (i) {
                        if ($("#tipo_usuario").val() === tipos_servicio[i].idTipoUsuario) {
                            option_ts = document.createElement("option");
                            option_ts.value = tipos_servicio[i].id;
                            option_ts.innerHTML = tipos_servicio[i].nombre;
                            $("#tipo_servicio").append(option_ts);
                        }
                    });
                });
            });
        }

        $("#registrar_nuevo_modulo").submit(function (e) {
            e.preventDefault();
            let id360 = sesion_cookie.idUsuario_Sys;
            let modulos = null;
            let modulo_principal = null;
            for (var i = 0; i < servicioUsuario.servicios_usuarios.length; i++) {
                if (servicioUsuario.servicios_usuarios[i].id === $("#tipo_servicio option:selected").val()) {
                    modulos = servicioUsuario.servicios_usuarios[i].modulos;
                    modulo_principal = servicioUsuario.servicios_usuarios[i].modulo_principal;
                    break;
                }
            }
            if (modulos !== null && modulo_principal !== null) {
                let json = {
                    url: $("#estados option:selected").val(),
                    tipo_usuario: $("#tipo_usuario option:selected").val(),
                    tipo_servicio: $("#tipo_servicio option:selected").val(),
                    id360: id360,
                    modulos: modulos,
                    modulo_principal: modulo_principal
                };
                console.log(json);
                RequestPOST("/API/registro/nuevo_modulo", json).then(function (response) {
                    Swal.fire({
                        title: 'Aviso.',
                        text: response.mensaje
                    }).then(function () {
                        if (response.success) {
                            //realizar el login
                            RequestPOST("/API/cuenta360/access_token", {
                                "token": sesion_cookie.token,
                                "id360": sesion_cookie.idUsuario_Sys
                            }).then(function (response) {
                                if (response.success) {
                                    //access_token
//                                    let hostdir = window.location.protocol + "//" + window.location.host+ "/" + DEPENDENCIA + "/";
                                    let path = $("#estados option:selected").val() + "API/cuenta360/access_token/" + sesion_cookie.idUsuario_Sys + "/" + response.access_token;
//            window.location.replace(path);
                                    window.location.replace(path);
                                }

                            });
                        }
                    });
                });
            }


            //mientras subelo y vamos revisando que jale XD
        });


    </script>
    <script src="${sdk_awsJS}" ></script>
    <script src="${files_awsJS}" ></script>
</body>
