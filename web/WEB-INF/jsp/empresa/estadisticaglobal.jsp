
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 estadisticaglobal estadisticaglobal_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/estadisticaglobal/estadisticaglobal.css" var="modulo_estadisticaglobalCSS" />
<spring:url value="${pathRecursos}/empresas360/estadisticaglobal/estadisticaglobal.js" var="modulo_estadisticaglobalJS" />
<link href="${modulo_estadisticaglobalCSS}" rel="stylesheet"/>
<script src="${modulo_estadisticaglobalJS}" ></script>
<script>
    init_estadisticaglobal("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>