
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 areasdetrabajo" id="base_modulo_${id}">
    <h3>Administra tus areas de trabajo</h3>
    <form id="registrar_area" class="row m-0 p-0 col-12">
        <input type="hidden" id="AreasdeTrabajo_tipo_usuario">
        <div style="font-size: 1rem;align-items: center;display: flex;padding: 10px 5px;" class="col-sm-12 col-md-7 p-2">
            <select class="form-control-plaintext input p-2 text-dark m-0 mb-1" id="AreasdeTrabajo_listado_sucursales" placeholder="Seleccione uno" required="" style="font: bold 1.4rem Arial; border: none; background: none; border-bottom: solid 2px #495057;">
                <option disabled="" selected="" value="">Selecciona una sucursal</option>
            </select>
        </div>
        <div class="row m-0 col-sm-12 col-md-5 p-2">
            <div class="col-sm-8">
                <input list="listado_Areas" name="AreasdeTrabajo_listado_Areas" id="AreasdeTrabajo_listado_Areas" placeholder="Establece una Área" class=" p-2 text-dark m-0 mb-1" style="
                       font: bold 1.4rem Arial;
                       border: none;
                       background: none;
                       border-bottom: solid 2px #495057;
                       " required="true" type="text">
                <datalist id="listado_Areas"></datalist>
            </div>
            <div class="col-sm-4 p-1"><input type="submit" class="btn btn-danger w-100" value="Agregar"></div>
        </div>
    </form>

    <div class="row m-0 p-0 col-12 content" id="areas_registradas">

    </div>
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

<spring:url value="${pathRecursos}/empresas360/areasdetrabajo/areasdetrabajo.css" var="modulo_areasdetrabajoCSS" />
<spring:url value="${pathRecursos}/empresas360/areasdetrabajo/areasdetrabajo.js" var="modulo_areasdetrabajoJS" />
<link href="${modulo_areasdetrabajoCSS}" rel="stylesheet"/>
<script src="${modulo_areasdetrabajoJS}" ></script>
<script>
//    init_areasdetrabajo("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_areasdetrabajo(${json});
</script>