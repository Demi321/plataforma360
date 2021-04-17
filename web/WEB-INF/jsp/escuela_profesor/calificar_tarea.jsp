<%-- 
    Document   : calificar_tarea
    Created on : 15/04/2021, 10:32:16 AM
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="card col-12">
            <div class="card-body pt-0">
                <span class="title">
                    Alumnos
                </span>
                <div class="row col-12 m-0 p-1 text-body">
                    <table style="width:100%" class="table table-hover" id="tablaHorario">
                       <thead>
                           <tr>
                                <th scope="col">Alumno</th>
                                <th scope="col">Documento</th>
                                <th scope="col">Acciones</th>
                           </tr>
                       </thead>
                       <tbody>
                           <c:forEach items="${alumnos}" var="a" >
                                <tr>
                                    <td>Alumno ${a.id_usuario}</td>
                                    <td>
                                        <button class="btn btn-primary" onclick="ver_documento( ${a.id_usuario},  ${id_evaluacion})" ><span>Ver Documento</span></button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success" onclick="ver_tarea_alumno( ${a.id_usuario},  ${id_evaluacion})">
                                            <span>Calificar</span>                                            
                                        </button>
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
    

<spring:url value="${pathRecursos}/escuelas/profesor/tareas.js" var="modulo_tareasJS" />
<script src="${modulo_tareasJS}" ></script>
