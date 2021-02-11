
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 recordatorios" id="base_modulo_${id_menu}">
 <h3>Lineamientos de Seguridad Sanitaria Estatales</h3>
    <div class="row col-12 m-0 p-2 pt-3">

    </div>
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/recordatorios/recordatorios.css" var="modulo_recordatoriosCSS" />
<spring:url value="${pathRecursos}/empresas360/recordatorios/recordatorios.js" var="modulo_recordatoriosJS" />
<link href="${modulo_recordatoriosCSS}" rel="stylesheet"/>
<script src="${modulo_recordatoriosJS}" ></script>
<script>
    init_recordatorios("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>