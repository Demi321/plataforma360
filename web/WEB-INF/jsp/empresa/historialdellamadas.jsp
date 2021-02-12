
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 historialdellamadas historialdellamadas_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/historialdellamadas/historialdellamadas.css" var="modulo_historialdellamadasCSS" />
<spring:url value="${pathRecursos}/empresas360/historialdellamadas/historialdellamadas.js" var="modulo_historialdellamadasJS" />
<link href="${modulo_historialdellamadasCSS}" rel="stylesheet"/>
<script src="${modulo_historialdellamadasJS}" ></script>
<script>
    init_historialdellamadas("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
</script>