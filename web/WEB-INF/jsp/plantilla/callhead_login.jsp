<%-- 
    Document   : callhead
    Created on : 29 jul 2019, 11:52:27
    Author     : moises
--%>
<spring:url value="${pathRecursos}/config.js" var="configJS" />
<script src="${configJS}"></script>
<script src="${pathRecursos}/js/login/ValidarSesion.js" ></script>

<spring:url value="${pathRecursos}/plantilla/personalizacion.js" var="personalizacionJS" />
<script src="${personalizacionJS}"></script>

<%-- FRAMEWORKS --%>


<%--******** SWEETALERT *******--%>
<spring:url value="/resources/frameworks/SweetAlert/sweetalert2.css" var="sweetalertCSS" />
<spring:url value="/resources/frameworks/SweetAlert/sweetalert2.all.js" var="sweetalertJS" />
<link href="${sweetalertCSS}" rel="stylesheet" />
<script src="${sweetalertJS}"></script>
<%--******** FONT AWESOME *******--%>
<spring:url value="/resources/frameworks/fontawesome-5.15.1/css/all.css" var="fontawesomeCSS" />
<spring:url value="/resources/frameworks/fontawesome-5.15.1/js/all.js" var="fontawesomeJS" />
<link href="${fontawesomeCSS}" rel="stylesheet" />
<script src="${fontawesomeJS}" ></script>
<%--******** XLSX *******--%>
<!--<spring:url value="/resources/frameworks/xlsx/xlsx.min.js" var="xlsxJS" />-->
<spring:url value="/resources/frameworks/xlsx/xlsx.full.min.js" var="xlsxJS" />
<script src="${xlsxJS}" ></script>

<%--*****************************************************************************--%>

<script src="${pathRecursos}/js/functions/functions.js" ></script>
<script src="${togglefectJS}"></script>

<script src="${modernizrJs}"></script>
<script src="${controlmodalJS}"></script>