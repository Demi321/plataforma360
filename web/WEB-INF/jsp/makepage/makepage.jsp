<%-- 
    Document   : makepage
    Created on : 5/01/2021, 10:17:12 AM
    Author     : moises
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String foo = "bar";
            pageContext.setAttribute("foo", foo);
            System.out.println(foo);
        %>
        <c:choose>
            <c:when test="${foo eq 'bar'}">
                <jsp:include page="one.jsp" flush="true"/>
            </c:when>
            <c:when test="${foo eq 'baz'}">
                <jsp:include page="two.jsp" flush="true"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="three.jsp" flush="true"/>
            </c:otherwise>
        </c:choose>
        <c:if test="${foo == 'bar' }">
            <jsp:include page="one.jsp" flush="true"/>
        </c:if>
        ${page_added}
    </body>
    <script>
        var v = 0;
        v=${v};
    </script>
</html>
