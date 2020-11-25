
<%-- 
    Document   : callhead
    Created on : 29 jul 2019, 11:52:27
    Author     : moises
--%>
<spring:url value="${pathRecursos}/config.js" var="configJS" />
<script src="${configJS}"></script>
<script>
    function deleteCookie(cname) {
        var d = new Date();
        d.setTime(d.getTime() - (1 * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + "value" + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return JSON.parse(window.atob(c.substring(name.length, c.length)));
            }
        }
        return "";
    }
    console.log("/***************Sesion esn script access token ****************/");
    var user = getCookie("username_v3.1_" + DEPENDENCIA);
    console.log(user);
    if (user !== "") {
        deleteCookie("username_v3.1_" + DEPENDENCIA);
    }
</script>

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

<%--*****************************************************************************--%>

<script src="${pathRecursos}/js/functions/functions.js" ></script>
<script src="${togglefectJS}"></script>

<script src="${modernizrJs}"></script>
<script src="${controlmodalJS}"></script>