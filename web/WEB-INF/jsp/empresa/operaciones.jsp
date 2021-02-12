
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 operaciones operaciones_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/operaciones/operaciones.css" var="modulo_operacionesCSS" />
<spring:url value="${pathRecursos}/empresas360/operaciones/operaciones.js" var="modulo_operacionesJS" />
<link href="${modulo_operacionesCSS}" rel="stylesheet"/>
<script src="${modulo_operacionesJS}" ></script>
<script>
    init_operaciones("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
</script>