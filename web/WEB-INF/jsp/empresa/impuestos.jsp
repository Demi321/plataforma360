
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 impuestos" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/impuestos/impuestos.css" var="modulo_impuestosCSS" />
<spring:url value="${pathRecursos}/empresas360/impuestos/impuestos.js" var="modulo_impuestosJS" />
<link href="${modulo_impuestosCSS}" rel="stylesheet"/>
<script src="${modulo_impuestosJS}" ></script>
<script>
//    init_impuestos("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_impuestos(${json});
</script>