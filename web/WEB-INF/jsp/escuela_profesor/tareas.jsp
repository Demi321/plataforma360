<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_tareas">
   <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            
            <form id="form_RegistrarTarea" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Evaluación</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="titulo_tarea">Titulo:</label>
                                        <input type="text" name="titulo_tarea" class="form-control" id="titulo_tarea" placeholder="Nombre de Materia" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="nombre_tarea">Descripción:</label>
                                        <input type="text" name="nombre_tarea" class="form-control" id="nombre_tarea" placeholder="Nombre de Materia" required="" />
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Grupo:</label>
                                        <select class="form-control" name="sector" id="grupo_tarea" placeholder="Seleccione sucursal" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <c:forEach items="${grupo}" var="g" >
                                                <option value="${g.id_grupo}" onclick="materia_grupo(${g.id_grupo})">${g.nombre_grupo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Materia:</label>
                                        <select class="form-control" name="sector" id="materia_tarea" placeholder="Seleccione sucursal" required="">
                                            
                                        </select>
                                    </div>
                                    
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Tipo Evaluación:</label>
                                        <select class="form-control" name="sector" id="tipo_tarea" placeholder="Seleccione profesor" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <c:forEach items="${tipo_evaluacion}" var="tipo" >
                                                <option value="${tipo.id_tipo_evaluacion}">${tipo.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="fecha_tarea">Fecha de Entrega:</label>
                                        <input type="date" name="fecha_tarea" class="form-control" id="fecha_tarea" required="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_materia" />
                </div>
                
            </form>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Evaluaciones Creadas: ${materias.size()}</h3>
                
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                         <th scope="col">Titulo</th>
                         <th scope="col">Descripción</th>
                         <th scope="col">Tipo Evaluacion</th>
                         <th scope="col">Grupo</th>
                         <th scope="col">Materia</th>
                         <th scope="col">Fecha de Entrega</th>
                         <th scope="col">Acciones</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach items="${tareas}" var="tarea">
                            <tr>
                                <td scope="row">${tarea.titulo_evaluacion}</td>
                                <td scope="row">${tarea.descripcion}</td>
                                <td scope="row">${tarea.tipo}</td>
                                <td scope="row">${tarea.nombre_grupo}</td>
                                <td scope="row">${tarea.nombre_materia}</td>
                                <td scope="row">${tarea.fecha_entrega}</td>

                                <td scope="row">                                           
                                    <button class="btn btn-success" title="Calificar evaluación" onclick="calificar_tarea(${tarea.id_evaluacion},${tarea.id_grupo})"><span>Calificar</span></button>
                                </td>
                            </tr>
                        </c:forEach>
                       
                   </tbody>
               </table> 
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
<spring:url value="${pathRecursos}/escuelas/profesor/tareas.js" var="modulo_tareasJS" />
<script src="${modulo_tareasJS}" ></script>
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
<script>
    
</script>
