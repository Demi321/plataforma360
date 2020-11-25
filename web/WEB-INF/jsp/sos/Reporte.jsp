<%-- 
    Document   : Reporte
    Created on : 1/03/2019, 11:55:15 AM
    Author     : web
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>    
    
</head>

<body id="pdf" >
    <input type="hidden" value="${reporte}" id="jsonReporte">
    <input type="hidden" id="config" value="${config}">
    <%@include file="../plantilla/callhead.jsp" %>
    
    <script src="${pathRecursos}/js/appReporte.js"></script>
    
</body>



