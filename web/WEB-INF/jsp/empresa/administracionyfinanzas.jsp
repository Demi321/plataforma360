
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 administracionyfinanzas administracionyfinanzas_${id}" id="base_modulo_${id}">
    <div class="row col-12 m-0 p-2" id="base_modulo_PanelEstadístico">

        <!--Panel estadistico-->
        <div class="row col-12 m-0 p-2 px-4 panel_estadistico bg-white text-dark d-flex align-items-center ">
            Estadistica Global
            <hr class="w-100 m-0">
        </div>
        <!--final de Panel estadistico-->

        <!-- declaracion de Vue para busqueda de empresa o sucursal -->
        <div class="col-12 p-1 d-flex align-items-center justify-content-center bg-white text-dark listado_institucion" >
            <multiselect
                v-model="value"
                placeholder="Selecciona una institución"
                label="title"
                track-by="id"
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
                >
<!--                <template slot="singleLabel" slot-scope="props">
                    <img class="option__image" :src="props.option.img"/>
                    <span class="option__desc"><span class="option__title">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                </template>

                <template slot="option" slot-scope="props">
                    <img class="option__image" :src="props.option.img" />
                    <span class="option__desc"><span class="option__title ">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                </template>-->
            </multiselect>
        </div>
        <!-- declaracion de Vue para busqueda de empresa o sucursal -->

        <!--Body de graficos-->
        <div class="row col-12 m-0 p-2 bg-white">
            <!--Cuadro de datos 1-->
            <div class="row col-12 m-0 mb-2 p-2 d-flex d-lg-none cuadros_datos1">
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>
                <div class="col-2 p-1">
                    <div class="p-2 box">
                        <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark">
                            <div class="px-2 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                            <div class="col px-2 text-center">Nombre de Datos</div>
                        </div>
                    </div>
                </div>

            </div>
            <!--Final Cuadro de datos 1-->
            <div class="row col-12 m-0 p-2">
                <!--Cuadro de datos 2-->
                <div class="row col-6 col-md-6 col-lg-5 col-xl-4 m-0 mb-2 p-2 d-none d-lg-flex cuadros_datos2">
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 p-1">
                        <div class="p-2 box">
                            <div class="row col-12 m-0 px-2 align-items-center justify-content-center text-dark h-100">
                                <div class="px-1 h2 font-weight-bold" id="total_pacientes_atendidos">50</div>
                                <div class="col px-2 text-center">Nombre de Datos</div>
                            </div>
                        </div>
                    </div>


                </div>
                <!--Final Cuadro de datos 2-->

                <!--Graficos de pastel-->
                <div class="row col-12 col-md-12 col-lg-7 col-xl-8 m-0 mb-2 p-2 graficas1">
                    <div class="col-md-4 m-0 p-1">
                        <div id="piechart1" class="chart"></div>
                    </div>

                    <div class="col-md-4 m-0 p-1">
                        <div id="piechart2" class="chart"></div>
                    </div>
                    <div class="col-md-4 m-0 p-1">
                        <div id="piechart3" class="chart"></div>
                    </div>
                </div>
                <!--final de Graficos de pastel-->
                <!--Graficos de linea de sintomas-->
                <div class="row col-12 col-md-12 m-0 mb-2 p-2 graficas2">
                    <div class="col-12 m-0 p-1">
                        <div id="line_chart_sintomas" class="chart"></div>
                    </div>
                </div>
                <!--Final de Graficos de linea de sintomas-->

                <!--Graficos de Barras-->
                <div class="row col-md-8 col-lg-4 p-2 m-0 mb-0 graficas3">
                    <div class="col-12 m-0 py-3 px-2">
                        <div id="chart_barras" class="chart">
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <label class="p-1 m-0">DATO 1</label>
                                <div class="col p-1">
                                    <div class="progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <label class="p-1 m-0">DATO 2</label>
                                <div class="col p-1">
                                    <div class="progress">
                                        <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <label class="p-1 m-0">DATO 3</label>
                                <div class="col p-1">
                                    <div class="progress">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <label class="p-1 m-0">DATO 4</label>
                                <div class="col p-1">
                                    <div class="progress">
                                        <div class="progress-bar bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <label class="p-1 m-0">DATO 5</label>
                                <div class="col p-1">
                                    <div class="progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!--Final de Graficos de Barras-->

                <!--Tabla de resultados-->
                <div class="row col-md-4 col-lg-2 p-2 m-0 mb-2 resultados">
                    <div class="col-12 m-0 p-1">
                        <div id="chart_resultados" class="chart">
                            <div class="row p-2 m-0 col-12 align-items-center justify-content-center">
                                <div class="p-1 m-0 h3 text-center">Resultados</div>
                                <p class="m-0 py-2">
                                    Nombre de datos: <strong>50</strong>
                                </p>
                                <p class="m-0 py-2">
                                    Nombre de datos: <strong>50</strong>
                                </p>
                                <p class="m-0 py-2">
                                    Nombre de datos: <strong>50</strong>
                                </p>
                                <p class="m-0 py-2">
                                    Nombre de datos: <strong>50</strong>
                                </p>

                            </div>
                        </div>
                    </div>
                </div>
                <!--Final tabla de resultados-->

                <!--Graficos reporte de aislamiento-->
                <div class="row col-lg-6 p-2 m-0 mb-2 graficas4 d-flex align-items-center justify-content-center">
                    <div class="col-12 m-0 p-1 h3 d-flex align-items-center justify-content-center">
                        Reporte de Aislamiento
                    </div>
                    <div class="col-6 col-sm-4 col-md-2 m-0 p-1">
                        <div id="chart_aislamiento1" class="chart"></div>
                        <div id="labelOverlay">
                            <p>25<span>%</span></p>
                        </div>
                        <strong class="text-center text-truncate">Home office</strong>
                    </div>
                    <div class="col-6 col-sm-4 col-md-2 m-0 p-1">
                        <div id="chart_aislamiento2" class="chart"></div>
                        <div id="labelOverlay">
                            <p>30<span>%</span></p>
                        </div>
                        <strong class="text-center text-truncate">En la oficina</strong>
                    </div>
                    <div class="col-6 col-sm-4 col-md-2 m-0 p-1">
                        <div id="chart_aislamiento3" class="chart"></div>
                        <div id="labelOverlay">
                            <p>50<span>%</span></p> 
                        </div>
                        <strong class="text-center text-truncate">Aislamiento</strong>
                    </div>
                    <div class="col-6 col-sm-4 col-md-2 m-0 p-1">
                        <div id="chart_aislamiento4" class="chart"></div>
                        <div id="labelOverlay">
                            <p>20<span>%</span></p>
                        </div>
                        <strong class="text-center text-truncate">Hospitalizado</strong>
                    </div>
                    <div class="col-6 col-sm-4 col-md-2 m-0 p-1">
                        <div id="chart_aislamiento5" class="chart"></div>
                        <div id="labelOverlay">
                            <p>12<span>%</span></p>
                        </div>
                        <strong class="text-center text-truncate">Recuperacion</strong>
                    </div>

                </div>
                <!--Final de Graficos reporte de aislamiento-->
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

<spring:url value="${pathRecursos}/empresas360/administracionyfinanzas/administracionyfinanzas.css" var="modulo_administracionyfinanzasCSS" />
<spring:url value="${pathRecursos}/empresas360/administracionyfinanzas/administracionyfinanzas.js" var="modulo_administracionyfinanzasJS" />
<link href="${modulo_administracionyfinanzasCSS}" rel="stylesheet"/>
<script src="${modulo_administracionyfinanzasJS}" ></script>
<script>
//    init_administracionyfinanzas("${id}","${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_administracionyfinanzas(${json});
</script>