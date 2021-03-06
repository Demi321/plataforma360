<%-- 
    Document   : plantilla
    Created on : 02 Ene 2020, 16:25:53
    Author     : Moises Juárez
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />
</head>

<body>
    <input type="hidden" id="cuenta360" value="${cuenta360}">
    <script>
        var institucion_seleccionada = null;
        if ($("#cuenta360").length) {
            if ($("#cuenta360").val() !== "") {
                
                //eliminar sesion 
                
                sesion_cookie = JSON.parse($("#cuenta360").val());
                console.log(sesion_cookie);
                //alert(sesion_cookie);
                if (sesion_cookie.plataforma360.length > 0) {
                    //document.getElementById("span").style.display = "none";
                    let plataforma360 = false;
                    let count_plataforma360 = 0;
                    let frst_id = null;
                    for (var i = 0; i < sesion_cookie.plataforma360.length; i++) {
                        let institucion = sesion_cookie.plataforma360[i];
                        console.log(institucion);
                        institucion_seleccionada=institucion;
                        break;
                    }
                    continuar_institucion_seleccionada();
                } else {
                    swal.fire({
                        text: "No cuentas con la funcionalidad de llamada en tu cuenta."
                    }).then(() => {
                        window.close();
                    });
                }
            }
        }




function continuar_institucion_seleccionada() {
    console.log("continuar");
    sesion_cookie.nombre_institucion = institucion_seleccionada.nombre_institucion;
    sesion_cookie.modulos = institucion_seleccionada.modulos;
    sesion_cookie.telefono_institucion = institucion_seleccionada.telefono_institucion;
    sesion_cookie.modulo_principal = institucion_seleccionada.modulo_principal;
    sesion_cookie.tipo_usuario = institucion_seleccionada.tipo_usuario;
    sesion_cookie.tipo_servicio = institucion_seleccionada.tipo_servicio;
    sesion_cookie.tipo_area = institucion_seleccionada.tipo_area;
    sesion_cookie.segmento = institucion_seleccionada.tipo;

    sesion_cookie.idUsuario_Sys = institucion_seleccionada.id_usuario;
    sesion_cookie.nombre = sesion_cookie.claro360.nombre;
    sesion_cookie.apellidos = sesion_cookie.claro360.apellido_paterno + " " + sesion_cookie.claro360.apellido_materno;
    sesion_cookie.modulos = institucion_seleccionada.modulos;
    sesion_cookie.url = institucion_seleccionada.url;
    sesion_cookie.correo = sesion_cookie.claro360.correo;
    sesion_cookie.usuario = sesion_cookie.claro360.usuario;
    sesion_cookie.token = sesion_cookie.claro360.token;


    let keys = Object.keys(sesion_cookie);
    let modulos_externos = new Array();
    let url_modulos = new Array();
    for (let i = 0; i < keys.length; i++) {
//            console.log(keys[i]);
//            console.log(typeof(sesion_cookie[keys[i]]).toString());
        let a = typeof (sesion_cookie[keys[i]]);
        if (a === "object" && (sesion_cookie[keys[i]] !== null && sesion_cookie[keys[i]] !== "null" && sesion_cookie[keys[i]] !== undefined)) {
            if (sesion_cookie[keys[i]].length) {
                for (var j = 0; j < sesion_cookie[keys[i]].length; j++) {
                    //console.log(sesion_cookie[keys[i]][j]+" ->"+keys[i]);
//                    console.log(keys[i]);
//                    console.log(sesion_cookie[keys[i]][j].url);
//                    if (keys[i].includes("plataforma360")) {
//                        let nuevo_modulo = [sesion_cookie[keys[i]][j].alias, sesion_cookie[keys[i]][j].url];
//                        modulos_externos.push(nuevo_modulo);
//                    } else {
//                        let nuevo_modulo = [keys[i], sesion_cookie[keys[i]][j].url];
//                        modulos_externos.push(nuevo_modulo);
//                    }
                    if (sesion_cookie[keys[i]][j].plataforma === "1") {
                        if (!url_modulos.includes(sesion_cookie[keys[i]][j].url)) {
                            url_modulos.push(sesion_cookie[keys[i]][j].url);
                            if (sesion_cookie[keys[i]][j].alias) {
                                let icono = sesion_cookie[keys[i]][j].icono;
                                if (icono === null) {
                                    //poner icono por default
                                    icono = "defaul.png";
                                } else {
                                    icono = icono.split("/");
                                    icono = icono[icono.length - 1];
                                }
                                let nuevo_modulo = [sesion_cookie[keys[i]][j].alias, sesion_cookie[keys[i]][j].url, icono, sesion_cookie[keys[i]][j].tipo];

                                modulos_externos.push(nuevo_modulo);
                            } else {
                                let icono = sesion_cookie[keys[i]][j].icono;
                                if (icono === null) {
                                    //poner icono por default
                                    icono = "defaul.png";
                                } else {
                                    icono = icono.split("/");
                                    icono = icono[icono.length - 1];
                                }
                                let nuevo_modulo = [keys[i], sesion_cookie[keys[i]][j].url, icono, sesion_cookie[keys[i]][j].tipo];
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
    delete sesion_cookie.app360;
    delete sesion_cookie.claro360;
    delete sesion_cookie.condicion_medica;
    delete sesion_cookie.empleados;
    delete sesion_cookie.facturacion;
    delete sesion_cookie.incidentes;
    delete sesion_cookie.lineamientos;
    delete sesion_cookie.mapagis;
    delete sesion_cookie.plan_interno;
    delete sesion_cookie.plataforma360;
    delete sesion_cookie.telemedicina_medico;
    delete sesion_cookie.telemedicina_paciente;
    delete sesion_cookie.videovigilancia;
    console.log("colocando cookie");
    setCookie("username_v3.2_" + "plataforma360", JSON.stringify(sesion_cookie), 1000);

}


function setCookie(cname, cvalue, exdays) {
    cvalue = window.btoa(cvalue);
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

    var user = JSON.parse(getCookie("username_v3.2_" + "plataforma360"));
    console.log(user);
    //alert(user);
    if (user !== "") {
//        var hostdir = window.location.protocol + "//" + window.location.host;
//        var path = hostdir + "/" + "plataforma360" + "/" + user.modulo_principal;
//        window.location.replace(path);
    } else {
        window.location.reload();
    }
    //window.location.reload();
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return window.atob(c.substring(name.length, c.length));
        }
    }
    return "";
}
    </script>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">
            <div><i class="fas fa-ellipsis-v"></i></div>
            <span id="titulomenu"> Participantes </span>
            <input type="button" id="directorio" class="AddNewGroup der">
        </div>
        <div id="sidebar">
            <div class="row m-0 p-0 h-50 overflow-auto" >
                <div class="col-12 p-0 m-0" style="position: relative;display: block;">
                    <div id="participantes" class="containerCardsParticipantes">

                    </div>
                </div>
            </div>
            <div class="row m-0 p-0 h-50" style="position: relative;">
                <div class="card-headerseparador" >
                    Reporte
                </div>
                <div style="height: calc(100% - 30px); position: absolute; width: 100%; overflow: scroll; top:30px;">
                    <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                        <div class="col-8">
                            <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Folio:</label>
                        </div>
                        <div class="col-12">
                            <input type="text" placeholder="Ingresa un folio para la llamada"  class="ClearInput" id="folio" name="folio" 
                                   style="background: white; border-radius: 3px;text-align: left; padding-left: 10px;     color: #40474e; font: 12px arial;" >
                        </div>
                    </div> 
                    <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                        <div class="col-8">
                            <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Motivo:</label>
                        </div>
                        <div class="col-12">
                            <textarea type="text" placeholder="Escribe el motivo de la llamada" style="font: 12px Arial; width: 100%; border-radius: 3px; " class="form-control" id="motivo" name="motivo" rows="3" cols="80" required="required"></textarea>
                        </div>
                    </div> 
                    <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                        <div class="col-8">
                            <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Reporte:</label>
                        </div>
                        <div class="col-12">
                            <textarea type="text" placeholder="Redacta tu reporte" 
                                      style="font: 12px Arial; width: 100%; border-radius: 3px; " 
                                      class="form-control" 
                                      id="reporte" 
                                      name="reporte" 
                                      rows="8" 
                                      cols="80" ></textarea>
                        </div>
                    </div> 
                    <div class="col-12 text-center">
                        <button  class=" btn btn-danger" type="submit"  id="btn-reporte" value="Guardar" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;" >Guardar</button>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center" style="width: auto;display:none;">
                <div class=" col-sm-12 col-md-12 col-lg-12 col-xl-12 form-group">
                    <div class=" col-sm-4 col-md-8 col-lg-9 col-xl-9 justify-content-center" style="padding-left:  0px;">
                        <div style=" height: auto;width: auto; ">
                        </div>
                    </div>
                    <div class="card-headermenus" >
                        <a style="margin:5px;font-size: 11px"> </a>
                    </div> 
                    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 p-0" id="accordion">
                        <div class="card" >
                            <div class="titulomenu" id="headingZero">
                                <a id="aheadingZero" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseZero" aria-expanded="false" aria-controls="collapseZero">
                                    Contactos
                                </a>
                            </div>
                            <div id="collapseZero" class="collapse show" aria-labelledby="headingZero" data-parent="#accordion" style="    max-height: 500px;   overflow-y: scroll;">
                            </div>
                        </div>
                        <div class="card">
                            <div class="titulomenu" id="headingThree">

                                <a id="aheadingThree" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Detallar Reporte
                                </a>
                            </div>
                            <div id="collapseThree" class="collapse show" aria-labelledby="headingThree" data-parent="#accordion">
                                <div style="height: auto;width: 100%; ">
                                    <form id="reporte">
                                        <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                            <div class="col-8">
                                                <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Historial de Párticipantes:</label>
                                            </div>
                                            <div class="col-12">
                                                <textarea placeholder="Historial de párticipantes" style="font: 12px Arial; width: 100%; border-radius: 3px; " class="form-control" id="hitorialparticipantes" name="hitorialparticipantes" rows="3" cols="80" required="required"></textarea>
                                            </div>
                                        </div> 
                                        <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                            <div class="col-8">
                                                <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " id="label">Reporte:</label>
                                            </div>
                                            <div class="col-12">
                                                <textarea class="form-control" id="AreaReporte" style="font: 12px Arial; margin-top: 5px; border-radius: 3px;" name="reporte" rows="5" cols="80" placeholder="Escribe tu reporte" required="required"></textarea>
                                            </div>
                                        </div> 
                                        <div class="row col-12 mx-auto mt-2" style="padding: 0;">
                                            <%--div class="col-1">
                                                <input class="checkbox" type="checkbox"  id="registrarLugar" checked="true">

                                                    </div>
                                                    <div class="col-7">
                                                        <label class="label-default" style="height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; ">Añadir mejora de sitio</label>                                                            
                                                    </div--%>
                                            <div class="col-12 text-center">
                                                <button  class=" btn btn-danger" type="submit"  id="btn-reporte" value="Guardar" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;" >Guardar</button>
                                            </div>
                                        </div> 
                                    </form>
                                    <button id="myBtn" style="display:none;" title="Reporte PDF"></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!------------------------------------------------------------fin del codigo implementado para vistas-->
            </div>
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="row col-12 m-0 p-0 "  style="height: 100%; top: 0;">
                <div class="col  p-0" id="side1">
                    <div class="col-12 p-0 pl-1 side1Map d-none">
                        <div class="card-headertitle text-center" >
                            MAPA
                        </div> 
                        <%-- Ubicacion Mapa --%>
                        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" style="    height: calc(100% - 3.3vh); color: black;">
                            <div class="embed-responsive embed-responsive-4by3">
                                <div class="embed-responsive-item" id="map" >
                                </div>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-12 p-0 pl-1 pt-0 side1Chat h-100">
                        <form id="chat" class="h-100">
                            <div class="card" id="textchat" >
                                <div class="card-headertitle text-center" >
                                    CHAT
                                </div>
                                <div class="card-body" style="background-color: #f4f4f4;">
                                    <div class="row" id="history" style="width: 100%; margin: 0;">
                                    </div>
                                    <div class="inputTextChat" style=" background: none;border: none;">
                                        <input class="form-control " type="text" placeholder="Comienza un chat aqui" id="msgTxt" disabled="true" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col" id="side2">
                    <div id="videos">
                        <div class=" p-0 row GRIDcontainer"  id="GRID">
                        </div>
                        <div id="publisher"></div>
                    </div>
                </div>
            </div>
        </div>
        <%-- Información del Perfil - seccion izquierda--%>
        <div class="row col-sm-12 col-md-12 col-lg-12 col-xl-12" style="display: none;">
            <!-- Información General -->
            <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6  " style="padding: 5px; padding-top: 0;    height: -webkit-fill-available;">
                <br>
            </div>
            <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6 " style="padding: 5px; padding-top: 0; ">
                <!--div class="card-headertitle text-center">
                    <h3>Llamada</h3>
                </div-->
                <div class="card-body">
                    <div class="row col-12 mx-auto mt-1" style="padding: 0; height: 100%; max-height: 800px;">
                        <div class="col-9">
                            <div class="col-sm-12 col-md-9 col-lg-9 col-xl-9 embed-responsive embed-responsive-1by1">
                                <div class="embed-responsive-item" id="videos">
                                    <div id="subscriber" class="big" style="background:#929799; border-right: solid 1px white;">
                                        <!-- Trigger/Open The Modal -->
                                    </div>
                                    <div id="publisher" name="publisher">
                                    </div> 
                                    <div id="botonera">
                                    </div>
                                </div>  
                            </div>
                        </div>
                        <div class="col-3" id="waitingbar" style="border-left: solid 1px; padding: 0; max-height: 100%; overflow-y: scroll;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%-- Información  - seccion derecha --%>
        <div class="col-sm-12 col-md-12 col-lg-9 col-xl-9">
            <div class="row">
            </div>
            <%-- Chat --%>
            <%-- Chat --%>
        </div>
        <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>
    </section>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
        body {font-family: Arial, Helvetica, sans-serif;}

        /* The Modal (background) */
        .modal {
            display:none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 100; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            /*width: 45%;*/width: 80%;
            height: 80%;
            /**/
            /*top: 20%;*/
        }

        /*Frame*/
        .frame{
            margin: auto;
            padding: 5px;
            border: 1px solid #888;
            width: -webkit-calc(100% - 40px);
            width:    -moz-calc(100% - 40px);
            width:         calc(100% - 40px);
            height: -webkit-calc(100% - 80px);
            height:    -moz-calc(100% - 80px);
            height:         calc(100% - 80px);
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
    </style>

    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content" id="modal-content">
            <span class="close" id="CloseFrame" style="width: 30px;">&times;</span>

            <iframe id="FrameReporte" style="height: 100%; display:block;" src=""></iframe>
            <div id="demoCarrusel" class="carousel slide" data-ride="carousel" style="
                 max-width: 100%;
                 width: 100%;
                 display:none;
                 ">
                <ul class="carousel-indicators" id="carousel-indicators">
                </ul>
                <div class="carousel-inner" id="carousel-inner">

                </div>
                <a class="carousel-control-prev" href="#demoCarrusel" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demoCarrusel" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>

        </div>

    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <%--<%@include file="../plantilla/callhead.jsp" %>--%>
    <%@include file="../plantilla/callhead_sinlogin.jsp" %>
    <script src="${pathRecursos}/js/Empresa/llamada_entrante.js" ></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
    </script>

</body>




