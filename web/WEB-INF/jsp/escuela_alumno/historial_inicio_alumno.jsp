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
                                         
                                      
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                    </div>
                   <!-- <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_materia" />-->
                </div>
                
            </form>
            <div class="col-12 content text-dark" id="formulario_institucion">
               
                
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
                                      
                       <c:forEach items="${historial_alumno}" var="ha" >
                               <tr>
                                   <td>${ha.nombre_materia}</td>
                                   <td>${ha.nombre_periodo}</td>
                                   <td>${ha.recurse}</td>
                                   <td>${ha.calificacion}</td>
                                  
                                </tr>
                           </c:forEach>
                                  
                   </tbody>
               </table> 
                
               
                
                
                
            </div>
        </div>
    </div>
</div>

 

<script>
    

</script>