
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 balanza" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/balanza/balanza.css" var="modulo_balanzaCSS" />
<spring:url value="${pathRecursos}/empresas360/balanza/balanza.js" var="modulo_balanzaJS" />
<link href="${modulo_balanzaCSS}" rel="stylesheet"/>
<script src="${modulo_balanzaJS}" ></script>
<script>
//    init_balanza("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_balanza(${json});
</script>