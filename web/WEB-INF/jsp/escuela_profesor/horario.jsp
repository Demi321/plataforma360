<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="card col-12">
            <div class="card-body pt-0">
                <span class="title">
                    Horario
                </span>
                <div class="row col-12 m-0 p-1 text-body">
                    <table style="width:100%" class="table table-hover" id="tablaHorario">
                       <thead>
                           <tr>
                                <th scope="col">Grupo</th>
                                <th scope="col">Materia</th>
                                <th scope="col">Lunes</th>
                                <th scope="col">Martes</th>
                                <th scope="col">Miercoles</th>
                                <th scope="col">Jueves</th>
                                <th scope="col">Viernes</th>
                                <th scope="col">Acciones</th>
                           </tr>
                       </thead>
                       <tbody>
                           <c:forEach items="${horario}" var="h" >
                               <tr>
                                   <td>${h.nombre_grupo}</td>
                                   <td>${h.nombre_materia}</td>
                                   <td>${h.lunes}</td>
                                   <td>${h.martes}</td>
                                   <td>${h.miercoles}</td>
                                   <td>${h.jueves}</td>
                                   <td>${h.viernes}</td>
                                   <td>
                                       <button class="btn btn-success"><span>Iniciar Sesión</span></button>
                                   </td>
                                </tr>
                           </c:forEach>


                       </tbody>
                   </table>                                     
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
<spring:url value="${pathRecursos}/escuelas/profesor/tareas.js" var="modulo_horarioJS" />
<script src="${modulo_horarioJS}" ></script>
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
