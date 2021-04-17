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
            
            <form id="form_RegistrarMateria" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Materia</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="materia_nombre">Nombre Materia:</label>
                                        <input type="text" name="materia_nombre" class="form-control" id="Registrar_materia" placeholder="Nombre de Materia" required="" />
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Sucursal:</label>
                                        <select class="form-control" name="sector" id="Registrar_sucursal" placeholder="Seleccione sucursal" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">Sucursal 1</option>
                                            <option value="2">Sucursal 2</option>
                                        </select>
                                    </div>
                                    
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Profesor Encargado</label>
                                        <select class="form-control" name="sector" id="Registrar_profesor" placeholder="Seleccione profesor" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">Profesor 1</option>
                                            <option value="2">Profesor 2</option>
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
                <h3>Materias Creadas: ${materias.size()}</h3>
                
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                         <th scope="col">Materia</th>
                         <th scope="col">Profesor Encargado</th>
                         <th scope="col">Sucursal</th>
                         <th scope="col">Acciones</th> 
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach items="${materias}" var="materia">
                            <tr>
                                <td scope="row">${materia.nombre_materia}</td>
                                <td scope="row">${materia.id_usuario}</td>
                                <td scope="row">${materia.id_sucursal}</td>

                                <td scope="row">                                           
                                    <button class="btn btn-danger" title="eliminar grupo" onclick="eliminarMateria(${materia.id_materia})"><span>Eliminar</span></button>
                                </td>
                            </tr>
                        </c:forEach>
                       
                   </tbody>
               </table> 
            </div>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/registrarmateria/registrarmateria.js" var="modulo_registrarmateriaJS" />
<script src="${modulo_registrarmateriaJS}" ></script>
