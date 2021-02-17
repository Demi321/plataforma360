<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 home_empleado" id="base_modulo_${id}">
    <div class="col-12 col-sm-12 col-md-7 ">
        <div class="card">
            <div class="card-header pb-0">
                <span class="title home_empleado_nombre_empleado">
                    -
                </span>
                <span id="home_empleado_estatus_descanso" class="position-absolute d-none" style="right: 10px;">
                    Descanso <span style="color:#a4a2a2"><i class="fas fa-circle"></i></span>
                </span>
                <span id="home_empleado_estatus_activo" class="position-absolute d-none" style="right: 10px;">
                    Activo <span style="color:#28a745"><i class="fas fa-circle"></i></span>
                </span>
            </div>
            <div class="card-body pt-0">
                <div class="row m-0 p-1 col-12">
                    <hr>
                    <div class="col-5 p-3 d-flex align-items-center justify-content-center">
                        <div class="p-3 marco">
                            <div class="h-100 w-100" id="home_empleado_img"></div>
                        </div>
                    </div>
                    <div class="col-7 p-1">
                        <div class="h3 font-weight-bold">Puesto de trabajo</div>
                        <div class="clima d-flex align-items-center pl-3"></div>
                        <div class="py-2"><i class="fas fa-map-marker-alt"></i><strong id="home_empleado_municipio"> - </strong><span id="home_empleado_estado">, - </span></div>
                        <div class="py-1">Semáforo: <strong>Rojo <span style="color: red"><i class="fas fa-circle"></i></span></strong></div>
                        <div id="clock"></div>
                        <button type="button" class="btn btn-primary iniciar_jornada" id="iniciar_jornada"> <i class="fas fa-play"></i> Iniciar jornada</button>
                        <button type="button" class="btn btn-warning pausar_jornada" id="pausar_jornada"> <i class="fas fa-pause"></i> Pausar Jornada (Descanso)</button>
                        <button type="button" class="btn btn-warning reanudar_jornada" id="reanudar_jornada"> <i class="fas fa-play"></i> Reanudar Jornada (Descanso)</button>
                        <button type="button" class="btn btn-danger finalizar_jornada"  id="finalizar_jornada"> <div class="finalizar_jornada_icon"></div> Finalizar Jornada</button>

                        <div class="row col-12 m-0 p-0 mt-3">
                            <div class="col-4 p-0">
                                <div class="progress">
                                    <span class="state1"><i class="fas fa-circle"></i></span>
                                    <div class="d-none state_walking_1"><div class="walking_man"></div></div>
                                    <div class="progress-bar bg-success progress-bar1" role="progressbar"></div>
                                </div>
                            </div>
                            <div class="col-4 p-0">
                                <div class="progress">
                                    <span class="state2"><i class="fas fa-circle"></i></span>
                                    <div class="d-none state_walking_2"><div class="walking_man"></div></div>
                                    <div class="progress-bar bg-success progress-bar2" role="progressbar"></div>
                                </div>
                            </div>
                            <div class="col-4 p-0">
                                <div class="progress">
                                    <span class="state3"><i class="fas fa-circle"></i></span>
                                    <div class="d-none state_walking_3"><div class="walking_man"></div></div>
                                    <div class="progress-bar bg-success progress-bar3" role="progressbar"></div>
                                    <span class="state4"><i class="fas fa-circle"></i></span>
                                    <div class="d-none state_walking_4"><div class="walking_man"></div></div>
                                </div>
                            </div>


                        </div>
                    </div>

                </div>
                <div class="noticias row col-12 p-3 m-0"></div>
            </div>
        </div>
    </div>
    <div class="col-12 col-sm-12 col-md-5 ">
        <div class="card card_contactos">
            <div class="card-header pb-0">
                <span class="title">
                    Contactos
                </span>
            </div>
            <div class="card-body pt-0">
                <div class="row m-0 p-1 col-12">
                    <hr>
                    <div class="col-12 p-1 d-flex align-items-center justify-content-center" id="directorio_contactos">

                        <multiselect
                            v-model="value"
                            placeholder="Buscar contacto"
                            label="title"
                            track-by="id360"
                            select-label=""
                            :options="options"
                            :close-on-select="true"
                            :custom-label="customLabel"
                            :show-labels="true"
                            :hide-selected="false"
                            @close="onClosed"
                            @tag="onTag"
                            @remove="onRemove"
                            @input="onInput"
                            @open="onOpen"
                            placeholder="Buscar contacto"
                            >
                            <template slot="singleLabel" slot-scope="props">
                                <!--<img class="option__image" :src="props.option.img"/>-->
                                <span class="option__desc"><span class="option__title">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                            </template>

                            <template slot="option" slot-scope="props">
                                <img class="option__image" :src="props.option.img" />
                                <span class="option__desc"><span class="option__title ">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                            </template>
                        </multiselect>


                    </div>
                    <div class="col-12 p-1 d-flex align-items-center justify-content-center" id="filtro_areas">
                    </div>
                    <div class="col-12 p-1 align-items-center justify-content-center" id="cards_contactos">
                    </div>


                </div>
                <div class="noticias row col-12 p-3 m-0"></div>
            </div>
        </div>
    </div>
    <div class="col-12 col-sm-12 col-md-7 ">
        <div class="card">
            <div class="card-header pb-0">
                <span class="title">
                    Seguimiento empleado
                </span>
                <span class="position-absolute" style="right: 10px;">
                    Riesgo Basal:  <span style="color:orange"> Medio <i class="fas fa-circle"></i></span>
                </span>
            </div>
            <div class="card-body pt-0">
                <div class="row m-0 p-1 col-12">
                    <hr>
                    <div class="col-12 p-1 m-0 mb-2 border-bottom">
                        <strong>Jornada Laboral</strong>
                    </div>
                    <div class="col-12 p-1 m-0">
                        <div class="d-flex align-items-center">
                            <div class="clock">
                                <div>
                                </div>
                                <div class="dot"></div>
                                <div>
                                    <div class="hour-hand"></div>
                                    <div class="minute-hand"></div>
                                    <div class="second-hand"></div>
                                </div>
                                <div>
                                </div>
                                <div class="diallines"></div>
                            </div>
                            <div class="d-inline-block col">
                                <div class="p-1"><strong id="today"></strong></div>
                                <div class="row col-12 m-0 p-0 jornada_laboral align-items-center justify-content-start d-flex d-sm-grid">
                                    <div class="col d-flex p-1"><strong>Entrada: </strong><span id="hora_entrada">-:-:-</span></div>
                                    <div class="col d-flex p-1"><strong>Descanso: </strong><span id="inicio_descanso">-:-:-</span><strong> a </strong><span id="final_descanso">-:-:-</span></div>
                                    <div class="col d-flex p-1"><strong>Salida: </strong><span id="salida">-:-:-</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 p-1 m-0 mb-2 mt-3 border-bottom">
                        <strong>Rendimiento Semanal</strong>
                    </div>
                    <div class="col-12 p-1 m-0" id="intervalo_semanal">
                    </div>
                    <div class="col-12 p-1 m-0 overflow-hidden">
                        <span class="text_small">Este es tu rendimiento semanal, recuerda iniciar tu jornada a tiempo.</span>
                        <div class="row col-12 m-0 p-0">
                            <div class="col-12 col-sm-12 col-md-3 m-0 p-1 d-flex align-items-center justify-content-center">
                                <div id="chart_productividad" class="chart"></div>
                                <div id="labelOverlay">
                                    <strong>Productividad</strong>
                                    <p><span id="home_empleado_productividad_porcentaje">25</span><span>%</span></p>
                                </div>
                            </div>
                            <div class="col-12 col-sm-12 col-md-9 m-0 p-1 d-flex align-items-center justify-content-center">
                                <div class="row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                    <div class="col d-flex align-items-center justify-content-center">
                                        <div class="d-block text-center">
                                            <div style="color: #8bc34a; font:2rem Arial" class="p-1" id="dias_laborables_ok"><i class="far fa-smile"></i></div>
                                            <div style="color: #ffc107; font:2rem Arial" class="p-1 d-none" id="dias_laborables_no_ok"><i class="far fa-frown-open"></i></div>
                                            <div style="font: bold 0.8rem Arial;color: #6c757d;" class="p-1">Dias laborales</div>
                                            <div style="font: bold 3rem Arial;color: #6c757d;" class="p-1" id="dias_laborables">-</div>
                                        </div>
                                    </div>
                                    <div class="col d-flex align-items-center justify-content-center">
                                        <div class="d-block text-center">
                                            <div style="color: #8bc34a; font:2rem Arial" class="p-1" id="horas_laborables_ok"><i class="far fa-smile"></i></div>
                                            <div style="color: #ffc107; font:2rem Arial" class="p-1 d-none" id="horas_laborables_no_ok"><i class="far fa-frown-open"></i></div>
                                            <div style="font: bold 0.8rem Arial;color: #6c757d;" class="p-1">Horas laborales</div>
                                            <div style="font: bold 3rem Arial;color: #6c757d;" class="p-1" id="horas_laborables">-</div>
                                        </div>
                                    </div>
                                    <div class="col d-flex align-items-center justify-content-center">
                                        <div class="d-block text-center">
                                            <div style="color: #8bc34a; font:2rem Arial" class="p-1" id="proyectos_atendidos_ok"><i class="far fa-smile"></i></div>
                                            <div style="color: #ffc107; font:2rem Arial" class="p-1 d-none" id="proyectos_atendidos_no_ok"><i class="far fa-frown-open"></i></div>
                                            <div style="font: bold 0.8rem Arial;color: #6c757d;" class="p-1">Proyectos atendidos</div>
                                            <div style="font: bold 3rem Arial;color: #6c757d;" class="p-1" id="proyectos_atendidos">-</div>
                                        </div>
                                    </div>
                                    <div class="col d-flex align-items-center justify-content-center">
                                        <div class="d-block text-center">
                                            <div style="color: #8bc34a; font:2rem Arial" class="p-1" id="retardos_ok"><i class="far fa-smile"></i></div>
                                            <div style="color: #ffc107; font:2rem Arial" class="p-1 d-none" id="retardos_no_ok"><i class="far fa-frown-open"></i></div>
                                            <div style="font: bold 0.8rem Arial;color: #6c757d;" class="p-1">Retardos</div>
                                            <div style="font: bold 3rem Arial;color: #6c757d;" class="p-1" id="retardos">-</div>
                                        </div>
                                    </div>
                                    <div class="col d-flex align-items-center justify-content-center">
                                        <div class="d-block text-center">
                                            <div style="color: #8bc34a; font:2rem Arial" class="p-1" id="faltas_ok"><i class="far fa-smile"></i></div>
                                            <div style="color: #ffc107; font:2rem Arial" class="p-1 d-none" id="faltas_no_ok"><i class="far fa-frown-open"></i></div>
                                            <div style="font: bold 0.8rem Arial;color: #6c757d;" class="p-1">Faltas</div>
                                            <div style="font: bold 3rem Arial;color: #6c757d;" class="p-1" id="faltas">-</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 p-1 m-0 mb-2 border-bottom">
                        <strong>Reportes</strong>
                    </div>
                    <div class="row col-12 p-1 m-0">
                        <div class="col p-2">
                            <div class="" id="reporte1"></div>
                        </div>
                        <div class="col p-2">
                            <div class="" id="reporte2"></div>
                        </div>
                        <div class="col p-2">
                            <div class="" id="reporte3"></div>
                        </div>
                        <div class="col p-2">
                            <div class="" id="reporte4"></div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="col-12 col-sm-12 col-md-5 ">
        <div class="card card_llamadas">
            <div class="card-header pb-0">
                <span class="title">
                    Panel de llamadas
                </span>
            </div>
            <div class="card-body pt-0">
                <div class="row m-0 p-1 col-12">
                    <hr>


                </div>
                <div class="row m-0 p-0 col-12 d-flex align-items-center justify-content-center icono_panel_llamadas">
                    <i class="fas fa-mobile-alt"></i>
                </div>
                <div class="row m-0 p-1 col-12 contenido_panel_llamadas">

                </div>
                <div class="row m-0 p-1 col-12 botones_panel_llamadas">
                    <hr>
                    <div class="col" id="panel_llamadas_colgar"><i class="fas fa-phone-alt"></i></div>
                    <div class="col" id="panel_llamadas_contestar"><i class="fas fa-phone-alt"></i></div>
                </div>
            </div>
        </div>
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

<spring:url value="${pathRecursos}/empresa_empleado/home_empleado/home_empleado.css" var="modulo_home_empleadoCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/home_empleado/home_empleado.js" var="modulo_home_empleadoJS" />
<link href="${modulo_home_empleadoCSS}" rel="stylesheet"/>
<script src="${modulo_home_empleadoJS}" ></script>
<script>
//    init_home_empleado("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_home_empleado(${json});
</script>