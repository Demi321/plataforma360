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
    <spring:url value="${pathRecursos}/css/operador.css" var="operadorCSS" />
    <link href="${operadorCSS}" rel="stylesheet" />
    <spring:url value="${pathRecursos}/plantilla/empresa.css" var="homeCSS" />

    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/plantilla/empresa.js" var="homeJS" />
    <script 
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&libraries=places&v=weekly">
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<!-- Include a header DIV with the same name as the draggable DIV, followed by "header" -->
<!--<div id="video_drag_header" style="padding: 10px; cursor: move; z-index: 10; background-color: #495057; color: #fff;"><h7 class="nombre_completo"></h7></div>-->
<!--<div id="conectado_jornada_laboral" style="min-height: 150px; min-width: 150px; width: 100%; height: 100%; overflow: hidden;" >-->
</div>
</div>
<body>

    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <div class="Paneblock d-block" id="blockpage"></div>
    <aside>
        <div class="" id="toggle">
            <div><i class="fas fa-ellipsis-v"></i></div><span>Plataforma 360</span></div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <%@include file="../plantilla/callhead.jsp" %>
    <%--<%@include file="../plantilla/callhead_sinlogin.jsp" %>--%>

    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
        </div>
        <link href="${homeCSS}" rel="stylesheet" />
        <script src="${homeJS}" ></script>
    </section>
    <script>
            
           var perfil_usuario = ${perfil_usuario}
            var empresa_usuario = ${empresa_usuario}
            var sucursales_usuario = ${sucursales_usuario}
            var directorio_usuario = ${directorio_usuario}
            var jornada_usuario = ${jornada_usuario}
            var reportes_usuario = ${reportes_usuario}
            var BucketName = "lineamientos";
            var bucketRegion = "us-east-1";
            var IdentityPoolId = "us-east-1:a8460f87-8d3f-4452-935a-b95a4fcc83ed";
            AWS.config.update({
                region: bucketRegion,
                credentials: new AWS.CognitoIdentityCredentials({
                    IdentityPoolId: IdentityPoolId
                })
            });

            var s3 = new AWS.S3({
                apiVersion: "2006-03-01",
                params: {Bucket: BucketName}
            });
    </script>
    ${modulos}
    <div class="d-none" id="guardando_logo">
        <div class="mensaje_guardando_logo">Guardando Logo ...</div>
    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <script>
         $(window).on("load", function () {
                $("#blockpage").removeClass("d-block");
                $("#blockpage").addClass("d-none");
                var sidebar = $("#sidebar");
                $("#sidebar").remove();
                $("aside").append(sidebar);

                //Seccion para activar el drag de las categorias
                //$('#sidebar .accordion').attr("draggable","true");
                var dragSrcEl = null;

                function handleDragStart(e) {
                    //Cerrar todos los acordeones
                    $(".collapse_sidebar_cntr").removeClass("show");
                    // Target (this) element is the source node.
                    dragSrcEl = this.parentNode;
                    e.dataTransfer.effectAllowed = 'move';
                    e.dataTransfer.setData('text/html', this.parentNode.outerHTML);

                    this.classList.add('dragElem');
                }
                function handleDragOver(e) {
                    if (e.preventDefault) {
                        e.preventDefault(); // Necessary. Allows us to drop.
                    }
                    this.parentNode.classList.add('over');

                    e.dataTransfer.dropEffect = 'move';  // See the section on the DataTransfer object.

                    return false;
                }

                function handleDragEnter(e) {
                    // this / e.target is the current hover target.
                }

                function handleDragLeave(e) {
                    this.parentNode.classList.remove('over');  // this / e.target is previous target element.
                }

                function handleDrop(e) {
                    // this/e.target is current target element.

                    if (e.stopPropagation) {
                        e.stopPropagation(); // Stops some browsers from redirecting.
                    }
                    // Don't do anything if dropping the same column we're dragging.
                    if (dragSrcEl != this.parentNode) {
                        // Set the source column's HTML to the HTML of the column we dropped on.
                        //alert(this.outerHTML);
                        //dragSrcEl.innerHTML = this.innerHTML;
                        //this.innerHTML = e.dataTransfer.getData('text/html');
                        this.parentNode.parentNode.removeChild(dragSrcEl);
                        var dropHTML = e.dataTransfer.getData('text/html');
                        this.parentNode.insertAdjacentHTML('beforebegin', dropHTML);
                        var dropElem = this.parentNode.previousSibling.firstChild;

                        addDnDHandlers(dropElem);

                    }
                    this.parentNode.classList.remove('over');
                    return false;
                }

                function handleDragEnd(e) {
                    // this/e.target is the source node.
                    this.parentNode.classList.remove('over');

                    /*[].forEach.call(cols, function (col) {
                     col.classList.remove('over');
                     });*/
                }

                function addDnDHandlers(elem) {
                    elem.addEventListener('dragstart', handleDragStart, false);
                    elem.addEventListener('dragenter', handleDragEnter, false)
                    elem.addEventListener('dragover', handleDragOver, false);
                    elem.addEventListener('dragleave', handleDragLeave, false);
                    elem.addEventListener('drop', handleDrop, false);
                    elem.addEventListener('dragend', handleDragEnd, false);

                }

                var cols = document.querySelectorAll('#sidebar div.collapse_sidebar');
                [].forEach.call(cols, addDnDHandlers);


                //inicializar mapas
                //initMaps();

                //grafica de home de empleado ***********
                if ($("#menu_section_HomeEmpleado").length) {
                    $("#menu_section_HomeEmpleado").click(() => {
                        console.log("bingo:onload");
                        chart_productividad();
                    });
                    chart_productividad();
                }
                $(".modulo_menu")[0].click();
            });
        
    </script>
</body>