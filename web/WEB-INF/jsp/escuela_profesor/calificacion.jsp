<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_${id}">
    <div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_RegistrarSucursal">
        <div class="registro_institucion row m-0 p-2">
            <div class="col-12 content text-dark" id="formulario_institucion">
                <div class="caja row m-0 p-0 col-12">
                    <div class="col-12"><h3 class="text-dark p-3 m-0">Tareas</h3></div>
                    <div class="col-12 row m-0 p-2">
                        <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                            <div class="row m-0 col-12">
                                <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                    <label class="" for="registro_patronal">Grupo:</label>
                                    <select class="form-control" name="sector" id="grupo_calificacion" placeholder="Seleccione uno" required="">
                                        <option disabled="" selected="" value="">Selecciona una opción</option>
                                        <c:forEach items="${grupo}" var="g" >
                                            <option value="${g.id_grupo}" onclick="materia_grupo(${g.id_grupo})">${g.nombre_grupo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                    <label class="" for="sector">Materia:</label>
                                        <select class="form-control" name="sector" id="materia_calificacion" placeholder="Seleccione sucursal" required="">
                                            
                                        </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Evaluacion</h3>
                <table style="width:100%" class="table table-hover" id="tablaAlumnos">
                   <thead>
                       <tr>
                        <th scope="col">Grupo</th>
                        <th scope="col">Materia</th>                         
                        <th scope="col">Alumno</th>
                        <th scope="col">Acciones</th> 
                       </tr>
                   </thead>
                   <tbody>
                       
                   </tbody>
               </table>   
            </div>
            <hr>
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
<spring:url value="${pathRecursos}/escuelas/profesor/calificacion.js" var="modulo_calificacionJS" />
<script src="${modulo_calificacionJS}" ></script>
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
<script>
   
</script>
