
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 contabilidad" id="base_modulo_${id}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresas360/contabilidad/contabilidad.css" var="modulo_contabilidadCSS" />
<spring:url value="${pathRecursos}/empresas360/contabilidad/contabilidad.js" var="modulo_contabilidadJS" />
<link href="${modulo_contabilidadCSS}" rel="stylesheet"/>
<script src="${modulo_contabilidadJS}" ></script>
<script>
//    init_contabilidad("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_contabilidad(${json});
</script>