<%-- 
    Document   : historial
    Created on : 27/04/2021, 11:30:15 AM
    Author     : David jmz
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_RegistrarSucursal">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            
            <form id="form_HistorialAlumno" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Historial Alumno</h3></div>
                        <div class="col-12 teform_RegistrarAlumnoxt-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Alumno:</label>
                                        <select class="form-control" name="sector" id="id_usuario" placeholder="Seleccione sucursal" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                              <c:forEach items="${alumno}" var="a">
                                                 
                                                <option value="${a.id_usuario}" onclick="historial_alumno(${a.id_usuario})">${a.id_usuario}</option>
                                            </c:forEach>
                                          
                                        </select>
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                    </div>
                   <!-- <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_materia" />-->
                </div>
                
            </form>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Alumnos Inscritos: ${alumno.size()}</h3>
                
                <table style="width:100%" class="table table-hover" id="tablaHistorialAlumno">
                   <thead>
                       <tr>
                        
                         <th scope="col">Materia</th>
                         <th scope="col">Período</th> 
                         <th scope="col">Recurse</th> 
                         <th scope="col">Calificación</th> 
                       </tr>
                   </thead>
                   <tbody>
                     
                       
                   </tbody>
               </table> 
                
               
                
                
                
            </div>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/historial_alumno/HistorialAlumno.js" var="modulo_registraralumnoJS" />
<script src="${modulo_registraralumnoJS}" ></script>

<script>
    

</script>