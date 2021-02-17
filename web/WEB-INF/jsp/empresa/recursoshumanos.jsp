
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 recursoshumanos recursoshumanos_${id}" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/recursoshumanos/recursoshumanos.css" var="modulo_recursoshumanosCSS" />
<spring:url value="${pathRecursos}/empresas360/recursoshumanos/recursoshumanos.js" var="modulo_recursoshumanosJS" />
<link href="${modulo_recursoshumanosCSS}" rel="stylesheet"/>
<script src="${modulo_recursoshumanosJS}" ></script>
<script>
//    init_recursoshumanos("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_recursoshumanos(${json});
</script>