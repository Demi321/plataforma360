
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 invitacionempresarial invitacionempresarial_${id}" id="base_modulo_${id_menu}">
    
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/invitacionempresarial/invitacionempresarial.css" var="modulo_invitacionempresarialCSS" />
<spring:url value="${pathRecursos}/empresas360/invitacionempresarial/invitacionempresarial.js" var="modulo_invitacionempresarialJS" />
<link href="${modulo_invitacionempresarialCSS}" rel="stylesheet"/>
<script src="${modulo_invitacionempresarialJS}" ></script>
<script>
    init_invitacionempresarial("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
</script>