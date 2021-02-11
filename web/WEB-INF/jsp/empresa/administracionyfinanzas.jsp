
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 administracionyfinanzas administracionyfinanzas_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/administracionyfinanzas/administracionyfinanzas.css" var="modulo_administracionyfinanzasCSS" />
<spring:url value="${pathRecursos}/empresas360/administracionyfinanzas/administracionyfinanzas.js" var="modulo_administracionyfinanzasJS" />
<link href="${modulo_administracionyfinanzasCSS}" rel="stylesheet"/>
<script src="${modulo_administracionyfinanzasJS}" ></script>
<script>
    init_administracionyfinanzas("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>