
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 notas" id="base_modulo_${id_menu}">
  <h3>Notas</h3>
    <div class="row col-12 m-0 p-2 pl-4 pt-3" id="contenido_cards">
        <div class="card m-2 mb-5 mr-5" style="width: 18rem;">
            <div class="card-body" style="background: white; color: black;">
                <p class="mb-5" style="text-align: center; font: bold 15px Arial;">Agregar Nueva Nota</p>
                <i class="far fa-calendar-plus" style="font-size: 10rem; display: flex; margin: auto; cursor: pointer;" onclick="add_note()"></i>
            </div>
        </div>
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

<spring:url value="${pathRecursos}/empresas360/notas/notas.css" var="modulo_notasCSS" />
<spring:url value="${pathRecursos}/empresas360/notas/notas.js" var="modulo_notasJS" />
<link href="${modulo_notasCSS}" rel="stylesheet"/>
<script src="${modulo_notasJS}" ></script>
<script>
    init_notas("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>