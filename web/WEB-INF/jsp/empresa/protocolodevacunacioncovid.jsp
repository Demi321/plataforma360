
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 protocolodevacunacioncovid protocolodevacunacioncovid_${id}" id="base_modulo_${id}">
    
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

<spring:url value="${pathRecursos}/empresas360/protocolodevacunacioncovid/protocolodevacunacioncovid.css" var="modulo_protocolodevacunacioncovidCSS" />
<spring:url value="${pathRecursos}/empresas360/protocolodevacunacioncovid/protocolodevacunacioncovid.js" var="modulo_protocolodevacunacioncovidJS" />
<link href="${modulo_protocolodevacunacioncovidCSS}" rel="stylesheet"/>
<script src="${modulo_protocolodevacunacioncovidJS}" ></script>
<script>
//    init_protocolodevacunacioncovid("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
init_protocolodevacunacioncovid(${json});
</script>