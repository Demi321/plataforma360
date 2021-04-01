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
            <h3>Horario de grupo ${grupo[0].nombre_grupo}</h3>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <span class="col-3">Numero de materias a registrar</span>
                <input class="col-3 form-group" type="number" min="1" id="numeroMateria">
                <button class="btn btn-success" id="numeroMaterias" onclick="nuevasMaterias()"><span>Continuar</span></button>
                <table style="width:100%" class="table table-hover" id="tablaHorario">
                   <thead>
                       <tr>
                            <th scope="col">Grupo</th>
                            <th scope="col">Materia</th>
                            <th scope="col">Profesor</th>
                            <th scope="col">Lunes</th>
                            <th scope="col">Martes</th>
                            <th scope="col">Miercoles</th>
                            <th scope="col">Jueves</th>
                            <th scope="col">Viernes</th>
                            <th scope="col">Acciones</th>
                       </tr>
                   </thead>
                   <tbody>
                       
                   </tbody>
               </table> 
            </div>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <span class="col-3">Materias Registradas</span>
                <table style="width:100%" class="table table-hover" id="tablaHorario">
                   <thead>
                       <tr>
                            <th scope="col">Grupo</th>
                            <th scope="col">Materia</th>
                            <th scope="col">Profesor</th>
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
                               <td>Profesor ${h.id_usuario}</td>
                               <td>${h.lunes}</td>
                               <td>${h.martes}</td>
                               <td>${h.miercoles}</td>
                               <td>${h.jueves}</td>
                               <td>${h.viernes}</td>
                               <td><button class="btn btn-danger" onclick="eliminarHorario(${h.id_grupo_horario},${h.id_grupo})"><span>Eliminar</span></button></td>
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
    function nuevasMaterias(){
        
        $('#numeroMaterias').prop('disabled',true)
        $('#numeroMateria').prop('disabled',true)

      for (var i = 1 ; i <= $('#numeroMateria').val(); i++) {
           var profesor = "<select class='form-control' name='sector' id='profesorhorario"+i+"' placeholder='Seleccione Profesor' required=''><option disabled='' selected='' value=''>Selecciona una opción</option><option value='1'>Profesor 1</option><option value='2'>Profesor 2</option></select>";
           var materia = "<select class='form-control' name='sector' id='materia_horario"+i+"' placeholder='Seleccione Materia' required=''><option disabled='' selected='' value=''>Selecciona una opción</option><c:forEach items='${materias}' var='materia'><option value='${materia.id_materia}'>${materia.nombre_materia}</option></c:forEach></select>";
       
           $("#tablaHorario > tbody:last").append("<tr id='seccionHorario"+i+"'><td>${grupo[0]['nombre_grupo']}</td><td>"+materia+"</td><td>"+profesor+"</td><td><input type='text' class='form-control' name='sector' id='lunes_horario"+i+"' placeholder='Escriba horario'></td><td><input type='text' class='form-control' name='sector' id='martes_horario"+i+"' placeholder='Escriba horario'></td><td><input type='text' class='form-control' name='sector' id='miercoles_horario"+i+"' placeholder='Escriba horario'></td><td><input type='text' class='form-control' name='sector' id='jueves_horario"+i+"' placeholder='Escriba horario'></td><td><input type='text' class='form-control' name='sector' id='viernes_horario"+i+"' placeholder='Escriba horario'></td><td><button class='btn btn-success' onclick='registrarHorario("+i+")'><span>Registrar</span></button></td></tr>");
      }  
        
       
    }
    
    function registrarHorario(i){
    
        let json = buildJSON_Section("seccionHorario"+i);
        json.bandera = i;
        json.id_grupo = ${grupo[0]['id_grupo']};
        
        console.log(json);
       RequestPOST("/API/registro/grupo_horario", json).then((response) => {
            console.log(response);
            swal.fire({
                text: response.mensaje
            }).then(() => {
                //recargar por access token 
                if (response.success) {
                    var id = response.id
                    $('#base_modulo_Registrarcurso').load('registro_grupo_horario?indice='+i)
                    /*let url = window.location.protocol + "//" + window.location.host + "/" + DEPENDENCIA + "/";
                    acceso_externo(url);*/
                }
            });
        });
    }
    
    function eliminarHorario(indice,i){
    
        let json = {};
        json.id_grupo = indice;
        
        console.log(json);
       RequestPOST("/API/elimina/grupo_horario", json).then((response) => {
            console.log(response);
            swal.fire({
                text: response.mensaje
            }).then(() => {
                //recargar por access token 
                if (response.success) {
                    var id = response.id
                    $('#base_modulo_Registrarcurso').load('registro_grupo_horario?indice='+i)
                    /*let url = window.location.protocol + "//" + window.location.host + "/" + DEPENDENCIA + "/";
                    acceso_externo(url);*/
                }
            });
        });
    }
</script>