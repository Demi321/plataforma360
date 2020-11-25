<%-- 
    Document   : spring_LH
    Created on : 15 ago 2019, 11:33:00
    Author     : moises
--%>


<%-- FRAMEWORKS --%>
<%--******** JSPDF *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/jsPDF-1.3.2/jspdf.min.js" var="pdfJS" />
<%--******** POPPER *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/Popper/popper.min.js" var="popperJS" />
<%--******** BOOSTRAP *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/bootstrap-4.3.1/css/bootstrap.css" var="bootstrapCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/bootstrap-4.3.1/css/bootstrap-grid.css" var="bootstrapGrindCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/bootstrap-4.3.1/js/bootstrap.js" var="bootstrapJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/bootstrap-4.3.1/css/bootstrap-slider.css" var="bootstrapSliderCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/bootstrap-4.3.1/js/bootstrap-slider.js" var="bootstrapSliderJS" />
<%--******** JQUERY *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/jQuery/jquery-3.4.1.js" var="jQueryJS" />
<%--******** PICKADATE *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/themes/rtl.css" var="rtlCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/themes/classic.css" var="classicCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/themes/classic.date.css" var="classicdateCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/themes/classic.time.css" var="classictimeCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/legacy.js" var="legacyJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/picker.js" var="pickerJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/picker.date.js" var="pickerdateJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/picker.time.js" var="pickertimeJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/pickadate/translations/es_ES.js" var="pickerTranslationEsJS" />
<%--******** SWEETALERT *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/SweetAlert/sweetalert2.css" var="sweetalertCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/SweetAlert/sweetalert2.all.js" var="sweetalertJS" />
<%--******** VUE MULTISELECT *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/vue_multiselect/vue-multiselect.min.css" var="VueMultiselectCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/vue_multiselect/vue-multiselect.min.js" var="VueMultiselectJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/vue_multiselect/vue-multiselect.min0.js" var="VueMultiselect0JS" />
<%--******** ICOMOON *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/icomoon/style.css" var="icomoonCSS" />
<%--******** FONT AWESOME *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/fontawesome-5.15.1/css/all.css" var="fontawesomeCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/fontawesome-5.15.1/js/all.js" var="fontawesomeJS" />
<%--******** OPENTOK *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/Opentok/opentok.js" var="opentokJS" />
<%--******** MOMENT *******--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/frameworks/moment/moment.js" var="momentJS" />
<script src="${momentJS}" ></script>

<link href="${icomoonCSS}" rel="stylesheet" />
<link href="${bootstrapCSS}" rel="stylesheet" />
<link href="${bootstrapSliderCSS}" rel="stylesheet" />
<link href="${bootstrapGrindCSS}" rel="stylesheet" />
<script src="${popperJS}" ></script>
<script src="${jQueryJS}"></script>
<script src="${bootstrapJS}" ></script>
<script src="${bootstrapSliderJS}" ></script>
<link href="${rtlCSS}" rel="stylesheet" />
<link href="${classicCSS}" rel="stylesheet" />
<link href="${classicdateCSS}" rel="stylesheet" />
<link href="${classictimeCSS}" rel="stylesheet" />
<script src="${pdfJS}"></script>
<%--script src="${legacyJS}"></script--%>
<script src="${pickerJS}"></script>
<script src="${pickerdateJS}"></script>
<script src="${pickertimeJS}"></script>
<script src="${pickerTranslationEsJS}" ></script>
<link href="${sweetalertCSS}" rel="stylesheet" />
<script src="${sweetalertJS}"></script>

<link href="${fontawesomeCSS}" rel="stylesheet" />
<script src="${fontawesomeJS}" ></script>
<link href="${VueMultiselectCSS}" rel="stylesheet" />
<script src="${VueMultiselectJS}" ></script>
<script src="${VueMultiselect0JS}" ></script>
<script src="${opentokJS}" ></script>


<%-- Estilos Base >
<spring:url value="https://plataforma911.ml/RecursosMoises/css/vendor/normalizev8.0.0.css" var="normalizeCss" />
<spring:url value="https://plataforma911.ml/RecursosMoises/css/vendor/jquery-ui.1.12.1.css" var="jqueryUiCss" />
<spring:url value="https://plataforma911.ml/RecursosMoises/css/vendor/bootstrap-grid.css" var="bootstrapGridCss" />
<spring:url value="https://plataforma911.ml/RecursosMoises/css/vendor/bootstrap.css" var="bootstrap4" /--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/css/layoutoperador.css" var="layoutMasterCSS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/css/app.css" var="appCSS" />


<%-- Javascript Base --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/js/plantilla/togglefect.js" var="togglefectJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/plantilla/controlmodal.js" var="controlmodalJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/vendor/modernizr-3.5.0.min.js" var="modernizrJs" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/functions/functions.js" var="functionsJS" />
<%--spring:url value="https://plataforma911.ml/RecursosMoises/js/vendor/bootstrap.js" var="bootstrap4Js" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/vendor/bootstrap.bundle.js" var="bootstrapBundleJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/vendor/jquery-ui.1.12.1.js" var="jqueryUiJs" /--%>
<spring:url value="https://plataforma911.ml/RecursosMoises/js/login/ValidarCookie.js" var="sesionJS" />


<!--OPERADOR-->
<%-- Estilos Base -->
<spring:url value="https://plataforma911.ml/RecursosMoises/css/flexdatalist/flexdatalist.css" var="flexdatalistCSS"/--%>
<%-- Estilos Personalizados --%>       
<spring:url value="https://plataforma911.ml/RecursosMoises/css/operador.css" var="operadorCSS" />
<%-- Javascript Base -->
<spring:url value="https://plataforma911.ml/RecursosMoises/js/flexdatalist/flexdatalist.js" var="flexdatalistJS" /--%>
<%-- Javascript Personalizados --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/Operador/app.js" var="appJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/Operador/map.js" var="mapJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/OperadorSocketConnection.js" var="OSocketJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SocketFolios.js" var="SocketFoliosJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/FireBase.js" var="FirebaseJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/vue/vue.js" var="vueJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SocketGeneral/SocketGeneral.js" var="SocketGeneralJS" />
<%-- Recursos --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/Img/perfil.png" var="perfilIMG" />
<spring:url value="https://plataforma911.ml/RecursosMoises/json/incidentes.json" var="IncidentesJSON" />


<!--REPORTE ELEMENTO-->

<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%>  
<spring:url value="https://plataforma911.ml/RecursosMoises/js/appReporteElemento.js" var="appRepEJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SocketConnectionReporteElemento.js" var="SocketREJS" /> 
<spring:url value="https://plataforma911.ml/RecursosMoises/js/mapReporteE.js" var="mapREJS" />

<!--REGISTRO-->
<%-- Estilos Personalizado --%>    
<spring:url value="https://plataforma911.ml/RecursosMoises/css/formulario-registro.css" var="formCSS" />  
<spring:url value="https://plataforma911.ml/RecursosMoises/css/form-index.css" var="formIndexCSS" />

<%-- Javascript Personalizado --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SuperAdmin/formulario-registro.js" var="formJs" /> 
<!--PROYECTO-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%>    
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SuperAdmin/proyecto.js" var="proyectoJS" />
<!--PRELOADING-->
<%-- Estilos Personalizados --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/css/preloading.css" var="appPLCSS" />
<%-- Javascript Personalizado --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/js/preloading/preloading.js" var="appPLJS" /> 
<!--LOGIN-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%>  
<spring:url value="https://plataforma911.ml/RecursosMoises/js/login/ValidarSesion.js" var="VsesionJS" />
<!--HISTORICO RUTA-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%>   
<spring:url value="https://plataforma911.ml/RecursosMoises/js/appRuta.js" var="appHRJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/map2.js" var="mapHRJS" />
<%-- Recursos --%>
<spring:url value="https://plataforma911.ml/RecursosMoises/Img/calendar.png" var="calendarPNG" />
<!--DESPACHO-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%> 
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/Despacho/appAd.js" var="appADJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/Despacho/SocketConnectionReporteElementoNot.js" var="SocketRENOTJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SocketConnection.js" var="SocketJS" />
<!--LLAMADA GUPAL-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%> 
<spring:url value="https://plataforma911.ml/RecursosMoises/js/Empresa/appEmpresa.js" var="appEJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/Empresa/map.js" var="mapEJS" />
<!--MONITOREO DE UNIDADES-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%>  
<spring:url value="https://plataforma911.ml/RecursosMoises/js/Empresa/appGrupos.js" var="appGJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/Empresa/directorio.js" var="dirJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/SocketConnectionEmpresa.js" var="SocketEJS" />
<!--BUSQUEDA DE REPORTES-->
<%-- Estilos Personalizados --%>
<%-- Javascript Personalizados --%> 
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/BusquedaReporte/map.js" var="mapBRJS" />
<spring:url value="https://plataforma911.ml/RecursosMoises/js/sos/BusquedaReporte/BusquedaReporte.js" var="appBRJS" />
<!--REPORTE-->
<spring:url value="https://plataforma911.ml/RecursosMoises/js/appReporte.js" var="appReporteJS" />