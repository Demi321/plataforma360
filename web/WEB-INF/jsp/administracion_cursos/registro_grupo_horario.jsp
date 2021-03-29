<%-- 
    Document   : registro_sucursal
    Created on : 22/03/2021, 10:19:18 AM
    Author     : XPS
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_Registrarcurso">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            
            <form id="form_RegistrarGrupoHorario" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <input type="hidden" id="grupo_escuela" value="${id_grupo}">
                    
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Horario Grupo ${id_grupo}</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Asignar materia y profesor</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-6 col-md-6 col-lg-6 col-xl-6 mt-2">
                                         <label class="" for="sector">Materia:</label>
                                        <select class="form-control" name="sector" id="materia_grupo" placeholder="Seleccione hora">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="3">Español</option>
                                            <option value="4">Matematicas</option>
                                            <option value="5">Geografia</option>
                                        </select>
                                    </div>
                               
                                    <div class="col-6 col-md-6 col-lg-6 col-xl-6 mt-2">
                                         <label class="" for="sector">Profesor:</label>
                                        <select class="form-control" name="sector" id="profesor_grupo" placeholder="Seleccione hora">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">Profesor 1</option>
                                            <option value="2">Profesor 2</option>
                                            <option value="3">Profesor 3</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra Horario</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Dias y hora</h7></div>
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <label class="" >Dias:</label>
                                       
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <label class="" >Horario</label>
                                    </div>
                                </div>
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <input type="checkbox" class="form-control" id="dia_lunes1" onclick="habilitarhora()"/>
                                        <label class="" for="lunes">Lunes</label>
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <select class="form-control" name="sector" id="hora_lunes" placeholder="Seleccione hora" disabled >
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">7:00-8:00</option>
                                            <option value="2">9:00-10:00</option>
                                            <option value="3">19:00-20:00</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <input type="checkbox" class="form-control" id="dia_martes1" onclick="habilitarhora()"/>
                                        <label class="" for="martes">Martes</label>
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <select class="form-control" name="sector" id="hora_martes" placeholder="Seleccione hora" disabled>
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">7:00-8:00</option>
                                            <option value="2">9:00-10:00</option>
                                            <option value="3">19:00-20:00</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <input type="checkbox" class="form-control" id="dia_miercoles1" onclick="habilitarhora()"/>
                                        <label class="" for="miercoles">Miercoles</label>
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <select class="form-control" name="sector" id="hora_miercoles" placeholder="Seleccione hora" disabled>
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">7:00-8:00</option>
                                            <option value="2">9:00-10:00</option>
                                            <option value="3">19:00-20:00</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <input type="checkbox" class="form-control" id="dia_jueves1" onclick="habilitarhora()" />
                                        <label class="" for="jueves">Jueves</label>
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <select class="form-control" name="sector" id="hora_jueves" placeholder="Seleccione hora" disabled>
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">7:00-8:00</option>
                                            <option value="2">9:00-10:00</option>
                                            <option value="3">19:00-20:00</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <input type="checkbox" class="form-control" id="dia_viernes1" onclick="habilitarhora()"/>
                                        <label class="" for="viernes">Viernes</label>
                                    </div>
                                   
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                        <select class="form-control" name="sector" id="hora_viernes" placeholder="Seleccione hora" disabled>
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="1">7:00-8:00</option>
                                            <option value="2">9:00-10:00</option>
                                            <option value="3">19:00-20:00</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar"  />
                </div>
                
            </form>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/escuelas/registrargrupo/registrargrupo.js" var="modulo_registrargrupoJS" />
<script src="${modulo_registrargrupoJS}" ></script> 