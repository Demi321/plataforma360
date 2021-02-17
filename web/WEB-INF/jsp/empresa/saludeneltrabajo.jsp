
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 saludeneltrabajo saludeneltrabajo_${id}" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/saludeneltrabajo/saludeneltrabajo.css" var="modulo_saludeneltrabajoCSS" />
<spring:url value="${pathRecursos}/empresas360/saludeneltrabajo/saludeneltrabajo.js" var="modulo_saludeneltrabajoJS" />
<link href="${modulo_saludeneltrabajoCSS}" rel="stylesheet"/>
<script src="${modulo_saludeneltrabajoJS}" ></script>
<script>
//    init_saludeneltrabajo("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_saludeneltrabajo(${json});
</script>