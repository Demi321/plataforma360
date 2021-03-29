<%-- 
    Document   : registro_sucursal
    Created on : 22/03/2021, 10:19:18 AM
    Author     : XPS
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/registrarmateria/registrarmateria.js" var="modulo_registrarmateriaJS" />
<script src="${modulo_registrarmateriaJS}" ></script>