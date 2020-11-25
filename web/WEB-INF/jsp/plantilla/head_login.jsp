<%-- FRAMEWORKS --%>

<%--******** JQUERY *******--%>
<spring:url value="/resources/frameworks/jQuery/jquery-3.4.1.js" var="jQueryJS" />
<script src="${jQueryJS}"></script>
<%--******** BOOSTRAP *******--%>
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

<spring:url value="${FAVICON}" var="favicon" />
<link rel="icon" type="image/png" href="${favicon}" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- Javascript Base --%>
<spring:url value="${pathRecursos}/plantilla/toggleeffect.js" var="toggleeffectJS" />
<spring:url value="${pathRecursos}/plantilla/plantilla.css" var="plantillaCSS" />
<spring:url value="${FAVICON}" var="favicon" />
<link href="${plantillaCSS}" rel="stylesheet" />
<link href="${pathRecursos}/css/form-index.css" rel="stylesheet" />
    
<title>${title}</title>


