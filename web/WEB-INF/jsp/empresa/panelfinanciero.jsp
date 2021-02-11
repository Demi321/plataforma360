
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 panelfinanciero" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/panelfinanciero/panelfinanciero.css" var="modulo_panelfinancieroCSS" />
<spring:url value="${pathRecursos}/empresas360/panelfinanciero/panelfinanciero.js" var="modulo_panelfinancieroJS" />
<link href="${modulo_panelfinancieroCSS}" rel="stylesheet"/>
<script src="${modulo_panelfinancieroJS}" ></script>
<script>
    init_panelfinanciero("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>