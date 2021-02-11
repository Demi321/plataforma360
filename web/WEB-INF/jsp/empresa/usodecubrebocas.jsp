
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 usodecubrebocas" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/usodecubrebocas/usodecubrebocas.css" var="modulo_usodecubrebocasCSS" />
<spring:url value="${pathRecursos}/empresas360/usodecubrebocas/usodecubrebocas.js" var="modulo_usodecubrebocasJS" />
<link href="${modulo_usodecubrebocasCSS}" rel="stylesheet"/>
<script src="${modulo_usodecubrebocasJS}" ></script>
<script>
    init_usodecubrebocas("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>