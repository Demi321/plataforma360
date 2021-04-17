<%-- 
    Document   : registro_sucursal
    Created on : 22/03/2021, 10:19:18 AM
    Author     : XPS
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_Registrarcurso">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            
            <form id="form_RegistrarGrupo" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Grupo</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <label class="" for="materia_nombre">Nombre Grupo:</label>
                                        <input type="text" name="materia_nombre" class="form-control" id="Registrar_materia" placeholder="Nombre de Materia" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <label class="" for="materia_nombre">Nivel o Grado Grupo:</label>
                                        <input type="number" class="form-control" id="nivel_grupo" placeholder="Nivel o Grado" required="" />
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <label class="" for="sector">Sucursal:</label>
                                        <select class="form-control" name="sector" id="Registrar_sucursal" placeholder="Seleccione sucursal" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">Sucursal 1</option>
                                            <option value="2">Sucursal 2</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar"  />
                </div>
                
            </form>
              <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Grupos Creados: ${materias.size()}</h3>
                
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                            <th scope="col">Grupo</th>
                            <th scope="col">Nivel</th>
                            <th scope="col">Sucursal</th>
                            <th scope="col">Acciones</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach items="${grupos}" var="grupo" varStatus="loop">
                            <tr>
                                <td scope="row">${grupo.nombre_grupo} </td>
                                <td scope="row">${grupo.nivel}</td>
                                <td scope="row">${grupo.id_sucursal}</td>

                                <td scope="row">                                           
                                    <button class="btn btn-secondary" title="editar grupo" onclick="editarGrupo(${grupo.id_grupo})"><span>Editar</span></button>
                                    <button class="btn btn-danger" title="eliminar grupo" onclick="eliminarGrupo(${grupo.id_grupo})"><span>Eliminar</span></button>
                                </td>
                            </tr>
                        </c:forEach>
                       
                   </tbody>
               </table> 
            </div>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/registrargrupo/registrargrupo.js" var="modulo_registrargrupoJS" />
<script src="${modulo_registrargrupoJS}" ></script>
<script>
    
</script>