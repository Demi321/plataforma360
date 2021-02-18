<%-- 
    Document   : callhead
    Created on : 29 jul 2019, 11:52:27
    Author     : moises
--%>
<spring:url value="${pathRecursos}/config.js" var="configJS" />
<script src="${configJS}"></script>
<script src="${pathRecursos}/js/login/ValidarCookie.js" ></script>

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

<spring:url value="/resources/frameworks/jQuery/jquery.scrollify.min.js" var="jQueryScrollifyJS" />
<script src="${jQueryScrollifyJS}"></script>

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

<%--******** XLSX *******--%>
<!--<spring:url value="/resources/frameworks/xlsx/xlsx.min.js" var="xlsxJS" />-->
<spring:url value="/resources/frameworks/xlsx/xlsx.full.min.js" var="xlsxJS" />
<script src="${xlsxJS}" ></script>

<!-- DataTable -->
<spring:url value="/resources/frameworks/datatables/js/jquery.dataTables.min.js" var="dataTable" />
<script src="${dataTable}" ></script>
<spring:url value="/resources/frameworks/datatables/js/dataTables.buttons.min.js" var="dataTableButton" />
<script src="${dataTableButton}" ></script>
<spring:url value="/resources/frameworks/datatables/js/buttons.flash.min.js" var="buttonsFlash" />
<script src="${buttonsFlash}" ></script>
<spring:url value="/resources/frameworks/datatables/js/jszip.min.js" var="jsZip" />
<script src="${jsZip}" ></script>
<spring:url value="/resources/frameworks/datatables/js/pdfmake.min.js" var="pdfMake" />
<script src="${pdfMake}" ></script>                
<spring:url value="/resources/frameworks/datatables/js/vfs_fonts.js" var="vfs_fonts" />
<script src="${vfs_fonts}" ></script> 
<spring:url value="/resources/frameworks/datatables/js/buttons.html5.min.js" var="buttonsHTML" />
<script src="${buttonsHTML}" ></script> 
<spring:url value="/resources/frameworks/datatables/js/buttons.print.min.js" var="buttonsPrint" />
<script src="${buttonsPrint}" ></script>

<!-- jQuery UI -->
<spring:url value="/resources/frameworks/jQuery/jquery-ui.min.js" var="jqueryuiJS" />
<script src="${jqueryuiJS}" ></script>
<spring:url value="/resources/frameworks/jQuery/jquery-ui.min.css" var="jqueryuiCSS" />
<!--<script src="${jqueryuiCSS}" ></script>-->

<spring:url value="/resources/frameworks/datatables/css/dataTables.bootstrap4.min.css" var="dataTableCSS" />
<link href="${dataTableCSS}" rel="stylesheet" />
<spring:url value="/resources/frameworks/datatables/css/buttons.dataTables.min.css" var="buttonsDatTableCSS" />
<link href="${buttonsDatTableCSS}" rel="stylesheet" />

<script src="${pathRecursos}/js/functions/functions.js" ></script>

<script src="${pathRecursos}/js/btns-toggle.js" ></script>



<script src="${pathRecursos}/js/SocketGeneral/SocketGeneral.js" ></script>
<link href="${plantillaCSS}" rel="stylesheet" />
<link href="${pathRecursos}/css/app.css" rel="stylesheet" />

<link href="${pathRecursos}/css/layoutBase.css" rel="stylesheet" />
<link href="${pathRecursos}/css/normalize.css" rel="stylesheet" />

<!-- Menu Click DereccontextMenuJSho -->
<spring:url value="/resources/frameworks/jQuery/context-menu.min.js" var="contextMenuJS" />
<script src="${contextMenuJS}" ></script>
<spring:url value="/resources/frameworks/jQuery/context-menu.min.css" var="contextMenuCSS" />
<link href="${contextMenuCSS}" rel="stylesheet" />

<!-- FileInput -->
<spring:url value="/resources/frameworks/fileinput/fileinput.css" var="fileinputCSS" />
<link href="${fileinputCSS}" rel="stylesheet" />
<spring:url value="/resources/frameworks/fileinput/fileinput.js" var="fileinputJS" />
<script src="${fileinputJS}" ></script>
<spring:url value="/resources/frameworks/fileinput/es.js" var="esFileinputJS" />
<script src="${esFileinputJS}" ></script> 