<%-- 
    Document   : modulo_dashboard_principal
    Created on : 12/01/2021, 06:17:39 PM
    Author     : moises
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_DashboardPrincipal">
    
</div>
<spring:url value="${pathRecursos}/css/Empresa/modulo_dashboard_principal.css" var="modulo_dashboard_principalCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_dashboard_principal.js" var="modulo_dashboard_principalJS" />
<link href="${modulo_dashboard_principalCSS}" rel="stylesheet"/>
<script src="${modulo_dashboard_principalJS}" ></script>