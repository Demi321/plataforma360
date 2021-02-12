
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 perfillaboral perfillaboral_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/perfillaboral/perfillaboral.css" var="modulo_perfillaboralCSS" />
<spring:url value="${pathRecursos}/empresas360/perfillaboral/perfillaboral.js" var="modulo_perfillaboralJS" />
<link href="${modulo_perfillaboralCSS}" rel="stylesheet"/>
<script src="${modulo_perfillaboralJS}" ></script>
<script>
    init_perfillaboral("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
</script>