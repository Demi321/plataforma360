<%-- 
    Document   : callhead
    Created on : 29 jul 2019, 11:52:27
    Author     : moises
--%>
<spring:url value="${pathRecursos}/config.js" var="configJS" />
<script src="${configJS}"></script>
<!--<script src="${pathRecursos}/js/login/ValidarCookie.js" ></script>-->

<spring:url value="${pathRecursos}/plantilla/personalizacion.js" var="personalizacionJS" />

<script src="${personalizacionJS}"></script>
<script src="${toggleeffectJS}"></script>


<%--******** JSPDF *******--%>
<spring:url value="/resources/frameworks/jsPDF-1.3.2/jspdf.min.js" var="pdfJS" />
<script src="${pdfJS}"></script>
<%--******** PICKADATE *******--%>
<spring:url value="/resources/frameworks/pickadate/themes/rtl.css" var="rtlCSS" />
<spring:url value="/resources/frameworks/pickadate/themes/classic.css" var="classicCSS" />
<spring:url value="/resources/frameworks/pickadate/themes/classic.date.css" var="classicdateCSS" />
<spring:url value="/resources/frameworks/pickadate/themes/classic.time.css" var="classictimeCSS" />
<spring:url value="/resources/frameworks/pickadate/picker.js" var="pickerJS" />
<spring:url value="/resources/frameworks/pickadate/picker.date.js" var="pickerdateJS" />
<spring:url value="/resources/frameworks/pickadate/picker.time.js" var="pickertimeJS" />
<spring:url value="/resources/frameworks/pickadate/translations/es_ES.js" var="pickerTranslationEsJS" />
<link href="${rtlCSS}" rel="stylesheet" />
<link href="${classicCSS}" rel="stylesheet" />
<link href="${classicdateCSS}" rel="stylesheet" />
<link href="${classictimeCSS}" rel="stylesheet" />
<script src="${pickerJS}"></script>
<script src="${pickerdateJS}"></script>
<script src="${pickertimeJS}"></script>
<script src="${pickerTranslationEsJS}" ></script>
<%--******** SWEETALERT *******--%>
<spring:url value="/resources/frameworks/SweetAlert/sweetalert2.css" var="sweetalertCSS" />
<spring:url value="/resources/frameworks/SweetAlert/sweetalert2.all.js" var="sweetalertJS" />
<link href="${sweetalertCSS}" rel="stylesheet" />
<script src="${sweetalertJS}"></script>
<%--******** VUE MULTISELECT *******--%>
<spring:url value="/resources/frameworks/vue_multiselect/vue-multiselect.min.css" var="VueMultiselectCSS" />
<spring:url value="/resources/frameworks/vue_multiselect/vue-multiselect.min.js" var="VueMultiselectJS" />
<spring:url value="/resources/frameworks/vue_multiselect/vue-multiselect.min0.js" var="VueMultiselect0JS" />
<link href="${VueMultiselectCSS}" rel="stylesheet" />
<script src="${VueMultiselectJS}" ></script>
<script src="${VueMultiselect0JS}" ></script>
<%--******** FONT AWESOME *******--%>
<spring:url value="/resources/frameworks/fontawesome-5.15.1/css/all.css" var="fontawesomeCSS" />
<spring:url value="/resources/frameworks/fontawesome-5.15.1/js/all.js" var="fontawesomeJS" />
<link href="${fontawesomeCSS}" rel="stylesheet" />
<script src="${fontawesomeJS}" ></script>
<%--******** OPENTOK *******--%>
<spring:url value="/resources/frameworks/Opentok/opentok.js" var="opentokJS" />
<script src="${opentokJS}" ></script>
<%--******** MOMENT *******--%>
<spring:url value="/resources/frameworks/moment/moment.js" var="momentJS" />
<script src="${momentJS}" ></script>
<%--******** LOTTIE *******--%>
<spring:url value="/resources/frameworks/lottie/lottie_5.5.7.js" var="lottieJS" />
<script src="${lottieJS}" ></script>


<script src="${pathRecursos}/js/functions/functions.js" ></script>



<!--<script src="${pathRecursos}/js/SocketGeneral/SocketGeneral.js" ></script>-->
<link href="${plantillaCSS}" rel="stylesheet" />
<link href="${pathRecursos}/css/app.css" rel="stylesheet" />