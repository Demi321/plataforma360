
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 paneldatosrrhh paneldatosrrhh_${id}" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/paneldatosrrhh/paneldatosrrhh.css" var="modulo_paneldatosrrhhCSS" />
<spring:url value="${pathRecursos}/empresas360/paneldatosrrhh/paneldatosrrhh.js" var="modulo_paneldatosrrhhJS" />
<link href="${modulo_paneldatosrrhhCSS}" rel="stylesheet"/>
<script src="${modulo_paneldatosrrhhJS}" ></script>
<script>
//    init_paneldatosrrhh("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
init_paneldatosrrhh(${json});
</script>