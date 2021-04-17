<%-- 
    Document   : registro_sucursal
    Created on : 22/03/2021, 10:19:18 AM
    Author     : XPS
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_RegistrarSucursal">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            
            <form id="form_RegistrarAlumno" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Alumno</h3></div>
                        <div class="col-12 teform_RegistrarAlumnoxt-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Alumno:</label>
                                        <select class="form-control" name="sector" id="registra_alumno" placeholder="Seleccione sucursal" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="12">Alumno 12</option>
                                            <option value="17">Alumno 17</option>
                                        </select>
                                    </div>
                                    
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Grupo:</label>
                                        <select class="form-control" name="sector" id="registra_grupo" placeholder="Seleccione profesor" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <c:forEach items="${grupo}" var="g">
                                                <option value="${g.id_grupo}">${g.nombre_grupo}</option>
                                            </c:forEach>
                                            
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_materia" />
                </div>
                
            </form>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Alumnos Inscritos: ${alumno.size()}</h3>
                
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                         <th scope="col">Alumno</th>
                         <th scope="col">Grupo</th>
                         <th scope="col">Acciones</th> 
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach items="${alumno}" var="a">
                            <tr>
                                <td scope="row">Alumno ${a.id_usuario}</td>
                                <td scope="row">${a.nombre_grupo}</td>

                                <td scope="row">                                           
                                    <button class="btn btn-danger" title="eliminar grupo" onclick="eliminarAlumno(${a.id_usuario})"><span>Eliminar</span></button>
                                </td>
                            </tr>
                        </c:forEach>
                       
                   </tbody>
               </table> 
            </div>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/registraralumno/registraralumno.js" var="modulo_registraralumnoJS" />
<script src="${modulo_registraralumnoJS}" ></script>
<script>
    
</script>