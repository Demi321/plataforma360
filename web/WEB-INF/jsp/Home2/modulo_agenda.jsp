<%-- 
    Document   : modulo_lineamientos_estatales
    Created on : 22-nov-2020, 21:24:02
    Author     : Eduardo
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 h-100" id="base_modulo_Agenda">
   <h3>Lineamientos de Seguridad Sanitaria Estatales</h3>
    <div class="row col-12 m-0 p-2 pt-3">

    </div>
</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_agenda.css" var="modulo_agendaCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_agenda.js" var="modulo_agendaJS" />
<link href="${modulo_agendaCSS}" rel="stylesheet"/>
<script src="${modulo_agendaJS}" ></script>

