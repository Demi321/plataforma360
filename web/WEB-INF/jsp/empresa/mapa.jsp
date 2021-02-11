
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 mapa" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/mapa/mapa.css" var="modulo_mapaCSS" />
<spring:url value="${pathRecursos}/empresas360/mapa/mapa.js" var="modulo_mapaJS" />
<link href="${modulo_mapaCSS}" rel="stylesheet"/>
<script src="${modulo_mapaJS}" ></script>
<script>
    init_mapa("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>