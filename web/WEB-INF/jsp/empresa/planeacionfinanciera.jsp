
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 planeacionfinanciera" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/planeacionfinanciera/planeacionfinanciera.css" var="modulo_planeacionfinancieraCSS" />
<spring:url value="${pathRecursos}/empresas360/planeacionfinanciera/planeacionfinanciera.js" var="modulo_planeacionfinancieraJS" />
<link href="${modulo_planeacionfinancieraCSS}" rel="stylesheet"/>
<script src="${modulo_planeacionfinancieraJS}" ></script>
<script>
    init_planeacionfinanciera("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>