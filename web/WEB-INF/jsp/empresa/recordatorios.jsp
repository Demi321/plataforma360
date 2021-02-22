
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

<div class="row col-12 m-0 p-0 h-100 recordatorios" id="base_modulo_${id}">
    <div class="container">

        <label for="new-task">Nuevo recordatorio</label>
        <form class="form-group m-0" id="agrega_recordatorio">
            <div class="input-group mb-3">
                <span class="input-group-text span_recordatorio" id="basic-addon1"><i class="far fa-sticky-note text-light"></i></span>
                <input type="text" class="form-control" id="nombre_recordatorio" placeholder="Título" aria-label="Username" aria-describedby="basic-addon1" required="true">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text span_recordatorio" id="basic-addon1"><i class="fas fa-quote-left text-light"></i></span>
                <input type="text" class="form-control" id="descripcion_recordatorio" placeholder="Descripción" aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="agregar_recordatorio">
                <label class="form-check-label px-3" for="agregar_recordatorio">Agregar alarma</label>
            </div>
            <div class="row col-12 m-0 p-0 detail">
                <div class="col-12 col-sm-12 col-md-6 p-0 input-group mb-3">
                    <span class="input-group-text span_recordatorio" id="basic-addon1"><i class="fas fa-calendar-check text-light" ></i></span>
                    <input type="date" class="form-control" id="fecha_recordatorio" placeholder="Selecciona una fecha" aria-label="Username" aria-describedby="basic-addon1">
                </div>

                <div class="col-12 col-sm-12 col-md-6 p-0 input-group mb-3">
                    <span class="input-group-text span_recordatorio" id="basic-addon1"><i class="far fa-clock text-light"></i></span>
                    <input type="time" class="form-control" id="hora_recordatorio" placeholder="Agrega una hora" aria-label="Username" aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="input-group mb-3 d-flex justify-content-end">
                <button type="submit" class="btn text-light" style="background:linear-gradient(135deg, #d0021b 0%,#d0021b 100%);"> Agregar recordatorio <i class="fas fa-plus-circle"></i></button>
            </div>
        </form>

        <!--     <p>
                <input id="new-task" type="text" />
                <button id="add_task">Agregar</button>
                </p>-->

        <h3>Todo</h3>
        <ul id="incomplete-tasks">
            <li class="row col-12 m-0">
                <div class="col-2 col-sm-2 col-md-1 p-0 d-flex align-items-center justify-content-center">
                    <input type="checkbox" checked="false"/>
                </div>
                <div class="row m-0 col-10 col-sm-10 col-md-9 p-0">
                    <label class="col-12 p-0">Enviar correo dedocumentacion</label>
                    <input type="text" class="col-12 tittle"/>
                    <p class="col-12 p-0 descripcion">Descripcion demo sobre la documentacion que supuestamente tengo que enviar por correo</p>
                    <input type="text" class="col-12 description"/>
                    <span class="fecha">2021/02/20</span><span class="hora">20:30:00</span>
                    
                </div>

                <div class="col-12 col-sm-12 col-md-2 p-0">
                    <button class="edit">Editar</button>
                    <button class="delete">Eliminar</button>
                </div>
            </li>
            
        </ul>

        <h3>Completados</h3>
        <ul id="completed-tasks">
            <li class="row col-12 m-0">
                <div class="col-2 col-sm-2 col-md-1 p-0 d-flex align-items-center justify-content-center">
                    <input type="checkbox" checked="true"/>
                </div>
                <div class="row m-0 col-10 col-sm-10 col-md-9 p-0">
                    <label class="col-12 p-0">Realizar ajustes al modulo de recordatorios</label>
                    <input type="text" class="col-12 tittle"/>
                    <p class="col-12 p-0 descripcion">El modulo esta se esta desarrollando para agregar notas tipo "to do list" la cual permitira agregar recordatorios con alertas visuales y sonoras configurables</p>
                    <input type="text" class="col-12 description"/>
                    <span class="fecha">2021/02/20</span><span class="hora">20:30:00</span>
                    
                </div>

                <div class="col-12 col-sm-12 col-md-2 p-0">
                    <button class="edit">Editar</button>
                    <button class="delete">Eliminar</button>
                </div>
            </li>
        </ul>
    </div>


</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
    agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresas360/recordatorios/recordatorios.css" var="modulo_recordatoriosCSS" />
<spring:url value="${pathRecursos}/empresas360/recordatorios/recordatorios.js" var="modulo_recordatoriosJS" />
<link href="${modulo_recordatoriosCSS}" rel="stylesheet"/>
<script src="${modulo_recordatoriosJS}" ></script>
<script>
//    init_recordatorios("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
    init_recordatorios(${json});
</script>