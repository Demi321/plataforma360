<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row col-12 m-0 p-0 miperfil text-dark" >

    <div class="row col-12 m-0 p-2 pt-3">
        <div class="col-12 col-sm-12 col-md-8 p-0">
            <h4>Detalles</h4>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Titulo:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${tarea[0].titulo_evaluacion}</label>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Descripcion:</label>
                <label class="col-sm-10 col-form-label d-flex justify-content-start align-items-center">${tarea[0].descripcion}</label>
            </div>
           
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Fecha de Entrega:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${tarea[0].fecha_entrega}</label>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Materia:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${tarea[0].nombre_materia}</label>
            </div>
            
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Grupo:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${tarea[0].nombre_grupo}</label>
            </div>
            
             <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Entregar:</label>
                <div class="col-sm-10"><input type="file" class="form-control-plaintext input" id="inputFile"/></div>
            </div>
             <div class="form-group row m-0 p-2">
               <button class="btn btn-success" onclick="subirArchivosNormal(${tarea[0].id_evaluacion})">Entregar.${tarea[0].id_evaluacion}</button>
            </div>
            
        </div>
    </div>

</div>


<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
<script src="https://sdk.amazonaws.com/js/aws-sdk-2.685.0.min.js"></script>
<spring:url value="${pathRecursos}/escuelas/alumno/tareas.js" var="modulo_tareasJS" />
<script src="${modulo_tareasJS}" ></script>
<script>
  
</script>

