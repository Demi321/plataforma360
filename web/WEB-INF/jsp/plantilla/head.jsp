<%-- FRAMEWORKS --%>
<%--******** JQUERY *******--%>
<spring:url value="/resources/frameworks/jQuery/jquery-3.4.1.js" var="jQueryJS" />
<script src="${jQueryJS}"></script>

<%--******** POPPER *******--%>
<spring:url value="/resources/frameworks/Popper/popper.min.js" var="popperJS" />
<script src="${popperJS}" ></script>
<%--******** BOOSTRAP *******--%>
<!--<spring:url value="/resources/frameworks/bootstrap-5.0.0/css/bootstrap.css" var="bootstrapCSS" />
<spring:url value="/resources/frameworks/bootstrap-5.0.0/css/bootstrap-grid.css" var="bootstrapGrindCSS" />
<spring:url value="/resources/frameworks/bootstrap-5.0.0/js/bootstrap.js" var="bootstrapJS" />-->
<spring:url value="/resources/frameworks/bootstrap-4.3.1/css/bootstrap.css" var="bootstrapCSS" />
<spring:url value="/resources/frameworks/bootstrap-4.3.1/css/bootstrap-grid.css" var="bootstrapGrindCSS" />
<spring:url value="/resources/frameworks/bootstrap-4.3.1/js/bootstrap.js" var="bootstrapJS" />
<spring:url value="/resources/frameworks/bootstrap-4.3.1/css/bootstrap-slider.css" var="bootstrapSliderCSS" />
<spring:url value="/resources/frameworks/bootstrap-4.3.1/js/bootstrap-slider.js" var="bootstrapSliderJS" />
<link href="${bootstrapCSS}" rel="stylesheet" />
<link href="${bootstrapSliderCSS}" rel="stylesheet" />
<link href="${bootstrapGrindCSS}" rel="stylesheet" />
<script src="${bootstrapJS}" ></script>
<script src="${bootstrapSliderJS}" ></script>



<link href="${pathRecursos}/css/app.css" rel="stylesheet" />
<link href="${pathRecursos}/css/layoutoperador.css" rel="stylesheet" />


<%-- Javascript Base --%>
<spring:url value="${pathRecursos}/js/aws/aws-sdk-2.685.0.min.js" var="sdk_awsJS" />

<script src="${sdk_awsJS}" ></script>

<spring:url value="${pathRecursos}/plantilla/toggleeffect.js" var="toggleeffectJS" />
<spring:url value="${pathRecursos}/plantilla/plantilla.css" var="plantillaCSS" />
<spring:url value="${FAVICON}" var="favicon" />
<link rel="icon" type="image/png" href="${favicon}" />
<link href="${plantillaCSS}" rel="stylesheet" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>${title}</title>





