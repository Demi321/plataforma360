<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Evaluación
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <form id="reporte_seguridad_form" class="p-1">

                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Materias:</strong>
                                    </div>
                                    <div class="col-12 col-md-8 col-lg-3">
                                        <select class="form-control" name="materia" id="materia" placeholder="Seleccione un valor" required="">
                                            <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                            <option value="1">Materia1</option>
                                            <option value="2">Materia2</option>
                                        </select>
                                    </div>
                                </div>
                               
                            </div>
                            
                             <div class="row col-12 m-0 p-1">
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Tareas:</strong>
                                    </div>
                                    <div class="col-12 col-md-8 col-lg-3">
                                        <select class="form-control" name="tarea" id="tarea" placeholder="Seleccione un valor" required="">
                                            <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                            <option value="1">Tarea1</option>
                                            <option value="2">Tarea2</option>
                                        </select>
                                    </div>
                                </div>
                               
                            </div>
                            
                            <div class="row col-12 m-0 p-1">
                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Alumnos</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <table style="width:50%">
                                        <tr>
                                          <th>Alumno</th>                                          
                                          <th>Calificación</th>
                                          <th>Acciones</th> 
                                        </tr>
                                        <tr>
                                          <td>Tarea n</td>
                                          <td><input class="form-control" type="text" id="calificacion" value="calif"></td>
                                          <td>                                           
                                            <button type="button" class="btn btn-info">contacto</button>
                                          </td>
                                        </tr>
                                    </table>                                    
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>            
        </div>
    </div>
</div>


<!--script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
    agregar_menu(${json});
</script -->

<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
