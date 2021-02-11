
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 plantillalaboral" id="base_modulo_${id_menu}">
 </div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/plantillalaboral/plantillalaboral.css" var="modulo_plantillalaboralCSS" />
<spring:url value="${pathRecursos}/empresas360/plantillalaboral/plantillalaboral.js" var="modulo_plantillalaboralJS" />
<link href="${modulo_plantillalaboralCSS}" rel="stylesheet"/>
<script src="${modulo_plantillalaboralJS}" ></script>
<script>
    init_plantillalaboral("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>