<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_tareas">
          
    <div class="col-12 content text-dark" id="formulario_institucion">
        <h3>Evaluaciones Asignadas: ${materias.size()}</h3>

        <table style="width:100%" class="table table-hover">
           <thead>
               <tr>
                 <th scope="col">Grupo</th>
                 <th scope="col">Materia</th>
                 <th scope="col">Evaluacion</th>
                 <th scope="col">Tipo</th>
                 <th scope="col">Fecha de Entrega</th>
                 <th scope="col">Acciones</th>
               </tr>
           </thead>
           <tbody>
               <c:forEach items="${tareas}" var="tarea">
                    <tr>
                        <td scope="row">${tarea.nombre_grupo}</td>
                        <td scope="row">${tarea.nombre_materia}</td>
                        <td scope="row">${tarea.titulo_evaluacion}</td>
                        <td scope="row">${tarea.tipo}</td>
                        <td scope="row">${tarea.fecha_entrega}</td>

                        <td scope="row">                                           
                            <button class="btn btn-secondary" title="Ver evaluación" onclick="detalleTarea(${tarea.id_evaluacion})"><span>Ver</span></button>
                            <button class="btn btn-success" title="Contacto"><span>Contacto</span></button>
                        </td>
                    </tr>
                </c:forEach>

           </tbody>
       </table> 
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
<spring:url value="${pathRecursos}/escuelas/alumno/tareas.js" var="modulo_tareasJS" />
<script src="${modulo_tareasJS}" ></script>
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>

<script>
   
</script>
