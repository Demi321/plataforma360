
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 perfildesalud perfildesalud_${id}" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/perfildesalud/perfildesalud.css" var="modulo_perfildesaludCSS" />
<spring:url value="${pathRecursos}/empresas360/perfildesalud/perfildesalud.js" var="modulo_perfildesaludJS" />
<link href="${modulo_perfildesaludCSS}" rel="stylesheet"/>
<script src="${modulo_perfildesaludJS}" ></script>
<script>
//    init_perfildesalud("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
init_perfildesalud(${json});
</script>