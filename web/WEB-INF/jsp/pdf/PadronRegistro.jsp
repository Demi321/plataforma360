<%-- 
    Document   : PadronRegistro
    Created on : 13/12/2019, 04:55:15 PM
    Author     : moises
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>    
    
</head>

<body id="pdf" >
    <input type="hidden" value="${registros}" id="registros">
    <input type="hidden" id="config" value="${config}">
    <%@include file="../plantilla/callhead.jsp" %>
    
    <script src="${pathRecursos}/js/PDF/padronregistros.js"></script>
    
</body>