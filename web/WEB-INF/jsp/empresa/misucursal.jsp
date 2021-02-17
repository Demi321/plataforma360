
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 misucursal" id="base_modulo_${id}">
 
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

<spring:url value="${pathRecursos}/empresas360/misucursal/misucursal.css" var="modulo_misucursalCSS" />
<spring:url value="${pathRecursos}/empresas360/misucursal/misucursal.js" var="modulo_misucursalJS" />
<link href="${modulo_misucursalCSS}" rel="stylesheet"/>
<script src="${modulo_misucursalJS}" ></script>
<script>
//    init_misucursal("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_misucursal(${json});
</script>