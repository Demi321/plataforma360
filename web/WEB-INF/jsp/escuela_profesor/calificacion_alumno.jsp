<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row col-12 m-0 p-0 miperfil text-dark" >

    <div class="row col-12 m-0 p-2 pt-3">
        <div class="col-12 col-sm-12 col-md-8 p-0">
            <h4>Detalles</h4>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Alumno:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Alumno ${id_usuario}</label>
            </div>
           
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Materia:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${materia[0].nombre_materia}</label>
            </div>
            
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Grupo:</label>
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">${grupo[0].nombre_grupo}</label>
            </div>
            
             <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Calificación:</label>
                <div class="col-sm-10"><input type="number" class="form-control-plaintext input" id="calificacion_alumno" value="${calificacion}"/></div>
            </div>
             <div class="form-group row m-0 p-2">
               <button class="btn btn-success" onclick="calificarAlumno(${id_usuario},${materia[0].nombre_materia},${grupo[0].nombre_grupo})">Calificar.</button>
            </div>
            
        </div>
    </div>

</div>


<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<spring:url value="${pathRecursos}/escuelas/profesor/calificacion.js" var="modulo_calificacionJS" />
<script src="${modulo_calificacionJS}" ></script>
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
<script src="https://sdk.amazonaws.com/js/aws-sdk-2.685.0.min.js"></script>
<script>
    
   
</script>

