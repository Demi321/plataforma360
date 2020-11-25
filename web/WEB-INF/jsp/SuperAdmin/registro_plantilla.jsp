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
        <div class="row col-12 m-0 p-0" id="toggle">Registro Plantilla Laboral</div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">



        </div>


        <div class="row col-12 m-0 p-0 h-100" id="base_modulo_AjustesdePrivacidad">
            <div class="row col-12 m-0 p-2 pt-3 h-100 d-flex justify-content-center align-items-center">
                <h3>Configuracion de privacidad y seguridad del centro de trabajo</h3>
                <div class="row col-12 m-0 p-3 h-75">
                    <div class="col-12 col-sm-6 col-md-3 col-lg-3 p-3 text-center">
                        <div class="row col-12 m-0 p-2">
                            <div class="col-12 px-5" class="titulo_ajuste">Bloquear la vinculacion a este centro de trabajo</div>

                            <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                                <div class="cuadrado_icon">
                                    <div class="cuadrado_contenido" id="bloqueo_total">
                                        <i class="fas fa-lock-open"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_bloqueo_total">Desbloqueado</strong></div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-3 col-lg-3 p-3 text-center">
                        <div class="row col-12 m-0 p-2">
                            <div class="col-12 px-5" class="titulo_ajuste">Habilitar vista en la App 360</div>

                            <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                                <div class="cuadrado_icon">
                                    <div class="cuadrado_contenido" id="visibilidad_app">
                                        <i class="fas fa-eye"></i> 
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_visibilidad_app">Visible</strong></div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-3 col-lg-3 p-3 text-center">
                        <div class="row col-12 m-0 p-2">
                            <div class="col-12 px-5" class="titulo_ajuste">Agregar Token de seguridad para la vinculación</div>

                            <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                                <div class="cuadrado_icon">
                                    <div class="cuadrado_contenido" id="token_seguridad">
                                        <i class="fas fa-door-open"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_token_seguritat">Sin Token</strong></div>
                            <div class="d-none row col-12 m-0 p-0" id="info_token">
                                <div class="col-sm-12 col-md-12 px-4"><input type="text" class="form-control-plaintext input" id="" placeholder="Token" style="font: bold 1.1rem Roboto; text-align: center;" /></div>
                                <div class="col-sm-12 col-md-6 pl-4 pr-1"><input type="button" class="btn btn-danger w-100" id="" value="Generar Aleatorio" /></div>
                                <div class="col-sm-12 col-md-6 pl-1 pr-4"><input type="button" class="btn btn-danger w-100" id="" value="Establecer" /></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-3 col-lg-3 p-3 text-center">
                        <div class="row col-12 m-0 p-2">
                            <div class="col-12 px-5" class="titulo_ajuste">Restringir vinculación con lista blanca</div>

                            <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                                <div class="cuadrado_icon">
                                    <div class="cuadrado_contenido"id="lista_blanca">
                                        <i class="fas fa-user-friends"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_lista_blanca">Desactivada</strong></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12" id="registros_file"></div>
        </div>


    </section>

    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <style>


        .cuadrado_contenido:hover {
            box-shadow: 1px 1px 10px -1px #7c7878;
            border: solid 3px white;
        }

        .titulo_ajuste{
            font: bold 1.2rem Arial;
        }
        .cuadrado_icon{
            width: 40%; 
            padding: 20%; 
            position: relative;
            cursor: pointer;
        }
        .cuadrado_contenido{
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 5rem;
            background: #212529;
            border-radius: 15px;
            border: solid 2px #dee2e6;
        }
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
            color: #f5f5f5;
        }
        section{
            background: #343a40;
        }
        #toggle{
            background: #f5f5f5;
        }
        footer{
            background-color: black;
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
        h6 {
            color: #0097a9;
            font: bold 1rem Ariel;
        }
        h4 {
            font: normal 1.4rem Arial;
            padding: 2px 10px;
        }
        h4 > strong{

            /* width: 25%; */

            font: bold 1.1rem Roboto;

            display: inline-block;
        }
        h4 > span{

            font: normal 1.3rem Roboto;

            padding-left: 10px;
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
            color:#f5f5f5;
        }
        .input::placeholder{
            color: #f5f5f5;
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
        .multiselect__input {
            background: none;
            color: #343a40;
            font: bold 12px arial;
        }
    </style>
    <script>


        $("#bloqueo_total").click(function () {
            document.getElementById("bloqueo_total").innerHTML = '<i class="fas fa-lock"></i>';
            document.getElementById("estatus_bloqueo_total").innerHTML = 'Bloqueada';
        });
        $("#visibilidad_app").click(function () {
            document.getElementById("visibilidad_app").innerHTML = '<i class="fas fa-eye-slash"></i>';
            document.getElementById("estatus_visibilidad_app").innerHTML = 'No Visible';
        });
        $("#token_seguridad").click(function () {
            document.getElementById("token_seguridad").innerHTML = '<i class="fas fa-key"></i>';
            document.getElementById("estatus_token_seguridad").innerHTML = 'Con Token';
            $("#info_token").removeClass("d-none");
        });
        $("#lista_blanca").click(function () {
            document.getElementById("lista_blanca").innerHTML = '<i class="fas fa-clipboard-list"></i>';
            document.getElementById("estatus_lista_blanca").innerHTML = 'Activada';
        });
        console.log("Bingoooooo");
        agregar_menu("Plantilla Laboral");
        registro_plantilla_laboral("Plantilla Laboral");


        function agregar_menu(nombre) {
            let div = document.createElement("div");
            div.className = "menu_sidebar d-flex";
            div.innerHTML = nombre;
            div.id = "menu_section_" + nombre.replace(/\s/g, "");
            $("#sidebar").append(div);

            let div2 = document.createElement("div");
            div2.className = "modulo_section d-none";
            div2.id = "modulo_section_" + nombre.replace(/\s/g, "");//quitale los espacios si llegara a tener 
            //            div2.innerHTML = nombre;

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
                //                div2.appendChild($("#base_modulo_"+ nombre.replace(/\s/g, "")));
                div2.appendChild(document.getElementById("base_modulo_" + nombre.replace(/\s/g, "")));
            }
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

            form_registro.appendChild(form_info("Nombre", "docente_nombre", "text"));

            let div = document.createElement("div");
            div.className = 'form-group row m-0 p-2';
            let label = document.createElement("label");
            label.for = "docente_apellidopaterno";
            label.className = 'col-sm-2 col-form-label';
            label.innerHTML = "Apellidos:";
            div.appendChild(label);
            let div2 = document.createElement("div");
            div2.className = 'col-sm-5';
            let input = document.createElement("input");
            input.type = "text";
            input.className = 'form-control-plaintext input';
            input.id = "docente_apellidopaterno";
            input.placeholder = "Apellido Paterno";
            div2.appendChild(input);

            let div3 = document.createElement("div");
            div3.className = 'col-sm-5';
            let input1 = document.createElement("input");
            input1.type = "text";
            input1.className = 'form-control-plaintext  input';
            input1.id = "docente_apellidomaterno";
            input1.placeholder = "Apellido Materno";
            div3.appendChild(input1);

            div.appendChild(div2);
            div.appendChild(div3);

            form_registro.appendChild(div);

            form_registro.appendChild(form_info("Correo Electrónico", "docente_correo", "text"));

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
            let div_empty = document.createElement("div");
            div_empty.className = 'col-sm-12';
            div_empty.id = "registros_file";
            let div_btn2_doc = document.createElement("div");
            div_btn2_doc.className = 'col-sm-12';
            div_btn2_doc.innerHTML = '<p>El documento debe ser un archivo con extensión csv ó xlsx y debe contener como minimo las columnas <strong>Nombre, Apellido Paterno, Apellido Materno y Correo</strong> puedes descargar una plantilla <a target="_blank" href="https://lineamientos.s3.amazonaws.com/plantilla.xlsx">aquí.</a></p>';
            let btn_doc = document.createElement('button');
            btn_doc.type = 'submit';
            btn_doc.className = ' d-none btn btn-danger mb-2';
            btn_doc.innerHTML = 'Subir Archivo';
            div_btn2_doc.appendChild(btn_doc);
//            div_btn_doc.appendChild(div_empty);
            div_btn_doc.appendChild(div_btn2_doc);

            form_doc.appendChild(div_btn_doc);

            div_doc.appendChild(form_doc);

            div_contendor.appendChild(div_doc);

            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contendor);
            $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_empty);

            $("#form_registro_personal").submit(function (e) {
                e.preventDefault();
                //        let inputs = $("[id^=docente_]");
                let json = buildJSON_Section("form_registro_personal");
                console.log(json);
                let keys = Object.keys(json);
                for (var i = 0; i < keys.length; i++) {
                    let key = keys[i].split("docente_");
                    key = key[1];
                    json[key] = json[keys[i]];
                    delete json[keys[i]];
                }
                json.tipo_usuario = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA)).tipo_usuario;
                json.tipo_servicio = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA)).tipo_servicio;
                let jsonObj = [[json]];
                RequestPOST("/API/registro_invitacion", jsonObj).then(function (response) {
                    swal.fire({
                        text: response.mensaje
                    }).then(function () {
                        if (response.success) {
                            document.location.reload();
//                            $("#registros_file").empty();
//                            $("#registros_file").removeAttr("style");
                        }
                    });

                });
            });

            $("#menu_section_" + nombre.replace(/\s/g, "")).click();

        }
        $("#file_plantilla_laboral").change(function (e) {
            fileReader(e);
        });
//        var json_file={};
        function fileReader(oEvent) {
            console.log("En la funcion fileReader");
            json_file = {};

            var oFile = oEvent.target.files[0];
            var sFilename = oFile.name;

            var reader = new FileReader();
            var result = {};
            if (sFilename.toString().includes(".csv") || sFilename.toString().includes(".xlsx")) {
                let h1 = document.createElement("h1");
                h1.innerHTML = "Procesando Archivo";
                let dots = 0;
                let interval = setInterval(function () {
                    if (dots === 10) {
                        dots = 0;
                        h1.innerHTML = "Procesando Archivo";
                    }
                    h1.innerHTML += ".";
                    dots++;

                }, 500);

                $("#registros_file").append(h1);

                reader.onload = function (e) {
                    var data = e.target.result;
                    console.log(data);
                    data = new Uint8Array(data);
                    var workbook = XLSX.read(data, {type: 'array', cellDates: true});
                    //            console.log(workbook);
                    var result = {};
                    workbook.SheetNames.forEach(function (sheetName) {
                        var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {raw: true});
                        if (roa.length)
                            result[sheetName] = roa;
                    });
                    // see the result, caution: it works after reader event is done.
                    console.log(result);
                    if (validar_info(result)) {
                        let keys_archivo = Object.keys(result);
                        let info_completa = new Array();
                        $.each(keys_archivo, function (i) {
                            let info_hoja = result[keys_archivo[i]];
                            let info_completa_hoja = new Array();
                            $.each(info_hoja, function (j) {
                                let alias = Object.keys(info_hoja[j]);
                                let keys_hoja = transforma_arreglo(Object.keys(info_hoja[j]));
                                let json = {};
                                $.each(alias, function (k) {
                                    if (info_hoja[j][alias[k]].toString().includes("(hora ")) {
                                        var hoy = new Date(info_hoja[j][alias[k]].toString());
                                        var dd = hoy.getDate();
                                        var mm = hoy.getMonth() + 1; //January is 0!
                                        var yyyy = hoy.getFullYear();
                                        if (dd < 10) {
                                            dd = '0' + dd;
                                        }
                                        if (mm < 10) {
                                            mm = '0' + mm;
                                        }
                                        var fecha = yyyy + '-' + mm + '-' + dd;
                                        json[keys_hoja[k]] = fecha;
                                    } else {
                                        json[keys_hoja[k]] = info_hoja[j][alias[k]];
                                    }
                                });
                                json.alias = alias;
                                json.tipo_usuario = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA)).tipo_usuario;
                                json.tipo_servicio = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA)).tipo_servicio;
                                json.id360 = JSON.parse(getCookie("username_v3.1_" + DEPENDENCIA)).id_usuario;
                                info_completa_hoja.push(json);
                            });
                            info_completa.push(info_completa_hoja);
                        });
                        console.log(info_completa);
                        clearInterval(interval);
                        mostrar_resultados(info_completa);
//                        RequestPOST("/API/registro_invitacion",info_completa).then(function(response){
//                            console.log(response);
//                        });

                    } else {
                        Swal.fire({
                            title: 'Archivo incompleto',
                            text: "EL archivo debe contener la informacion mínima: Nombre, Apellido paterno, Apellido materno y Correo."
                        });
                    }
                };
                reader.readAsArrayBuffer(oFile);
            } else {
                Swal.fire({
                    title: 'Extención Inválida',
                    text: "El archivo debe de ser un csv ó xlsx."
                });
            }
        }
        function mostrar_resultados(json) {

            $("#registros_file").empty();
            $("#registros_file").css({
                "position": "absolute",
                "top": "0",
                "left": "0",
                "background": "#343a40",
                "height": "100%",
                "width": "100%",
                "z-index": "100"
            });
            console.log(json);
            let div = document.createElement("div");
            div.className = "row col-12 m-0 p-2";
            div.style = "max-height: 100%; overflow: scroll;";

            let head = document.createElement("div");
            head.className = "row m-0 p-0 col-12 mb-4 mt-3";
            div.style = "font-size: 1.5rem;";

            let espacio1 = document.createElement("div");
            espacio1.className = "col-1";
            espacio1.style = "font-size: 1.5rem;";

            espacio1.innerHTML = '<i style="cursor:pointer;" class="fas fa-arrow-left"></i>';

            let espacio2 = document.createElement("div");
            espacio2.className = "col";

            let espacio3 = document.createElement("div");
            espacio3.className = "col-4";

            let registrar = document.createElement("input");
            registrar.type = "button";
            registrar.value = "Registrar";
            registrar.className = "btn btn-danger";

            let num = document.createElement("div");
            num.className = "col-1";
            num.innerHTML = '<strong>#</strong>';

            let nombre = document.createElement("div");
            nombre.className = "col-3";
            nombre.innerHTML = '<strong>Nombre</strong>';

            let apellido_paterno = document.createElement("div");
            apellido_paterno.className = "col-2";
            apellido_paterno.innerHTML = '<strong>Apellido Paterno</strong>';

            let apellido_materno = document.createElement("div");
            apellido_materno.className = "col-2";
            apellido_materno.innerHTML = '<strong>Apellido Materno</strong>';

            let correo = document.createElement("div");
            correo.className = "col-4";
            correo.innerHTML = '<strong>Correo</strong>';

            let hr = document.createElement("hr");
            hr.className = "col-12 border";

            espacio3.appendChild(registrar);
            head.appendChild(espacio1);
            head.appendChild(espacio2);
            head.appendChild(espacio3);

            div.appendChild(head);
            div.appendChild(num);
            div.appendChild(nombre);
            div.appendChild(apellido_paterno);
            div.appendChild(apellido_materno);
            div.appendChild(correo);
            div.appendChild(hr);

            $("#registros_file").append(div);

            espacio1.addEventListener("click", function () {
                $("#registros_file").empty();
                $("#registros_file").removeAttr("style");
            });
            registrar.addEventListener("click", function () {
                RequestPOST("/API/registro_invitacion", json).then(function (response) {
                    swal.fire({
                        text: response.mensaje
                    }).then(function () {
                        if (response.success) {
                            document.location.reload();
//                            $("#registros_file").empty();
//                            $("#registros_file").removeAttr("style");
                        }
                    });
                });

            });

//            let correos = new Array();
            let cont = 0;
            for (var k = 0; k < json.length; k++) {
                let arr = json[k];

                for (var i = 0; i < arr.length; i++) {
                    let reg = arr[i];
//                    if (!correos.includes(reg.correo)) {
//                        correos.push(reg.correo);
                    cont++;
                    let reg_num = document.createElement("div");
                    reg_num.className = "col-1";
                    reg_num.innerHTML = cont;

                    let reg_nombre = document.createElement("div");
                    reg_nombre.className = "col-3";
                    reg_nombre.innerHTML = reg.nombre;

                    let reg_apellido_paterno = document.createElement("div");
                    reg_apellido_paterno.className = "col-2";
                    reg_apellido_paterno.innerHTML = reg.apellidopaterno;

                    let reg_apellido_materno = document.createElement("div");
                    reg_apellido_materno.className = "col-2";
                    reg_apellido_materno.innerHTML = reg.apellidomaterno;

                    let reg_correo = document.createElement("div");
                    reg_correo.className = "col-4";
                    reg_correo.innerHTML = reg.correo;

                    let reg_hr = document.createElement("hr");
                    reg_hr.className = "col-12 border-top";

                    div.appendChild(reg_num);
                    div.appendChild(reg_nombre);
                    div.appendChild(reg_apellido_paterno);
                    div.appendChild(reg_apellido_materno);
                    div.appendChild(reg_correo);
                    div.appendChild(reg_hr);
//                    }
                }
            }

        }
        function validar_info(info_archivo) {
            let procede = true;
            let keys_archivo = Object.keys(info_archivo);
            $.each(keys_archivo, function (i) {
                let info_hoja = info_archivo[keys_archivo[i]];
                console.log(info_hoja);
                $.each(info_hoja, function (j) {
                    //                let alias = Object.keys(info_hoja[j]);
                    let keys_hoja = transforma_arreglo(Object.keys(info_hoja[j]));
                    if (!keys_hoja.includes("nombre")
                            || !keys_hoja.includes("apellidopaterno")
                            || !keys_hoja.includes("apellidomaterno")
                            || !keys_hoja.includes("correo")) {
                        procede = false;
                        return false;
                    }
                });
                if (!procede) {
                    return false;
                }
            });

            return procede;
        }
        function transforma_arreglo(arreglo) {
            let arreglo_mod = new Array();
            $.each(arreglo, function (i) {
                let val = arreglo[i];

                //Comvertimos el valor a minuscula
                val = val.toString().toLowerCase();
                if (val.toString().includes("correo") || val.toString().includes("mail")) {
                    val = "correo";
                }
                //cambiamos las letras con acento por letras sin acento
                val = val.normalize('NFD')
                        .replace(/([aeio])\u0301|(u)[\u0301\u0308]/gi, "$1$2")
                        .normalize();
                //Quitamos los caracteres epeciales
                val = val.replace(/[^\w\s]/gi, '');
                //Quitamos los espcacios
                val = val.replace(/ /gi, '');
                arreglo_mod.push(val);
            });
            return arreglo_mod;
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
    </script>

</body>