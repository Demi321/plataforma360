
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 reportejornadaslaborales" id="base_modulo_${id}">
    <div class="col-12 m-0 p-2 pt-3" id="reporteJornadasLaborales">
        <h3 style="display: block; width: 100%; text-align: left">Reporte de jornadas laborales</h3>
        
        <div class="text-left">
            <h6 class="text-left" style="font-size: 2rem; color: #343a40; text-align: left">Reporte diario jornada laboral</h6>
            <hr class="bg-dark">
        </div>
        <div class="row m-2">
            <div class="card col-12 col-lg-4">
                <div class="card-body text-dark border-0 rounded" style="background: #f5f5f5">                    
                    <p><h5 class="p-0" id="nombreEmpresa">Nombre de empresa</h5></p>
                    <strong id="fechaString">Martes, 05 de Enero de 2021</strong>
                    <p id="totalSucursales">Sucursales 5</p>
                    <p id="totalEmpleados">Total de empleados 150</p>
                </div>
            </div>
            <div class="card col-12 col-lg-4">
                <div class="card-body text-dark border-0 rounded" style="background: #f5f5f5">
                    <div style="position:relative;padding-bottom:60%;height: 0;overflow-y: auto;overflow-y: hidden">
                        <div style="position: absolute;top: 0;left: 0;width: 100%;height: 500px" id="piechartConexionEmpresa"></div>
                    </div>
                </div>
            </div>
            <div class="card col-12 col-lg-4">
                <div class="card-body text-dark border-0 rounded pt-5" style="background: #f5f5f5">
                    <div class="row pt-5">
                        <div class="col-sm-7 col-md-8" id="Faltas"> 
                            <div class="rectangleVacio2" id="faltaEmpresa_1"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_2"></div> 
                            <div class="rectangleVacio2" id="faltaEmpresa_3"></div> 
                            <div class="rectangleVacio2" id="faltaEmpresa_4"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_5"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_6"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_7"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_8"></div> 
                            <div class="rectangleVacio2" id="faltaEmpresa_9"></div>
                            <div class="rectangleVacio2" id="faltaEmpresa_10"></div>
                            <p>Faltas</p> 
                        </div>                            
                        <div class="col-sm-5 col-md-4">
                            <p><strong id="PorcentajaFaltas">5.9%</strong></p> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-7 col-md-8" id="Retardos"> 
                            <div class="rectangleVacio2" id="retardoEmpresa_1"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_2"></div> 
                            <div class="rectangleVacio2" id="retardoEmpresa_3"></div> 
                            <div class="rectangleVacio2" id="retardoEmpresa_4"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_5"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_6"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_7"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_8"></div> 
                            <div class="rectangleVacio2" id="retardoEmpresa_9"></div>
                            <div class="rectangleVacio2" id="retardoEmpresa_10"></div>
                            <p>Retardos</p>
                        </div>
                        <div class="col-sm-5 col-md-4">
                            <p><strong id="PorcentajaFaltas">29.4%</strong></p> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-7 col-md-8" id="Puntales"> 
                            <div class="rectangleVacio2" id="puntualEmpresa_1"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_2"></div> 
                            <div class="rectangleVacio2" id="puntualEmpresa_3"></div> 
                            <div class="rectangleVacio2" id="puntualEmpresa_4"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_5"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_6"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_7"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_8"></div> 
                            <div class="rectangleVacio2" id="puntualEmpresa_9"></div>
                            <div class="rectangleVacio2" id="puntualEmpresa_10"></div>
                            <p>Puntal</p>
                        </div>
                        <div class="col-sm-5 col-md-4">
                            <p><strong id="PorcentajePuntales">35.3%</strong></p> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="tabs_jornadas_laborales" class="col-12">
            <ul class="opciones_tabs_jornadas_laborales">
                <li id="tab_en_jornada" class="active"><a href="#tabs_1_jornadas_laborales">Empleados en jornada laboral</a></li>
                <li id="tab_reporte_jornada"><a href="#tabs_2_jornadas_laborales">Generar reporte de jornadas laborales</a></li>
            </ul>
            <div id="tabs_1_jornadas_laborales">
                <div id="inicio-reporte-jornadas-laborales" class="w-100">
                    <div class="text-center" style="position: relative">
                        <h6 style="font-size: 2rem; color: #343a40">Empleados en jornada laboral</h6>

                        <button id="btn-refrescar-jornadas" class="btn btn-outline-dark btn-lg" style="position: absolute; top: 0; right: 20px; ">
                            <i class="fas fa-sync-alt"></i>
                        </button>

                    </div>

                    <div class="row mt-4 mb-5 text-center">
                        <div class="col-md-6 col-sm-12">
                            <p style="font-size: 1.4rem">Entrada</p>
                            <button type="button" class="btn btn-success mr-2">
                                En tiempo <span class="badge badge-light" id="contadorEnTiempo"></span>
                            </button>
                            <button type="button" class="btn btn-warning mr-2">
                                Retardo <span class="badge badge-light" id="contadorRetardo"></span>
                            </button>
                            <button type="button" class="btn btn-danger">
                                Tarde <span class="badge badge-light" id="contadorTarde"></span>
                            </button>
                        </div>
                        <div class="col-md-6 col-sm-12">
                            <p style="font-size: 1.4rem">Salida</p>
                            <button type="button" class="btn btn-info mr-2">
                                Aún en jornada <span class="badge badge-light" id="contadorAunEnJornada"></span>
                            </button>
                            <button type="button" class="btn btn-success mr-2">
                                En tiempo <span class="badge badge-light" id="contadorEnTiempoSalida"></span>
                            </button>
                            <button type="button" class="btn btn-warning mr-2">
                                Antes <span class="badge badge-light" id="contadorRetardoSalida"></span>
                            </button>
                            <button type="button" class="btn btn-danger">
                                Muy antes <span class="badge badge-light" id="contadorTardeSalida"></span>
                            </button>
                        </div>
                    </div>

                    <div class="empleados-en-jornada-laboral">

                        <div id="sin-empleados-en-jornada" class="d-none">
                            <div class="alert alert-info" role="alert">Aún no se encuentra ningún empleado en jornada laboral!</div>
                        </div>

                        <div style="max-height: 550px; overflow-y: auto" id="con-empleados-en-jornada" class="d-none">

                            <table id="tabla-empleados-en-jornada" class="table table-hover">
                                <thead class="thead-light">
                                    <tr class="text-center">
                                        <th>Empleado</th>
                                        <th>Sucursal</th>
                                        <th>Área</th>
                                        <th>Hora de Entrada</th>
                                        <th>Hora Inicio Jornada</th>
                                        <th>Hora Última Desconexión</th>
                                        <th>Hora de Salida</th>
                                        <th>Hora Salida Jornada</th>
                                        <th>Cantidad Desconexiones</th>
                                        <th>Enviar Mensaje</th>
                                        <th>Iniciar Llamada</th>
                                    </tr>
                                </thead>
                                <tbody id="cuerpo-tabla-empleados-en-jornada">
                                </tbody>
                            </table>

                        </div>

                    </div>
                </div>
            </div>
            <div id="tabs_2_jornadas_laborales">
                <div class="col-12 text-center">

                    <h6 class="mb-5" style="font-size: 2rem; color: #343a40">Reporte de jornadas laborales</h6>

                    <form id="form_historia_jornadas2" class="row mb-4">
                        
                        <div class="col-md-2 col-sm-12 p-0">
                            <div class="form-group">
                                <label for="fecha_inicio_reporte2" class="mr-2">Fecha de inicio</label>
                                <input type="date" class="form-control" id="fecha_inicio_reporte2">
                            </div>
                        </div>

                        <div class="col-md-2 col-sm-12 p-0">
                            <div id="contenedor_fecha_final" class="form-group mx-sm-3">
                                <label for="fecha_fin_reporte2" class="mr-2">Fecha de fin</label>
                                <input type="date" class="form-control" id="fecha_fin_reporte2">
                            </div>
                        </div>

                        <div class="col-md-3 col-sm-12">
                            <div class="form-group">
                                <label class="mr-2" for="tipo_busqueda">Tipo de búsqueda</label>
                                <select class="form-control-plaintext input p-2 text-dark m-0 mb-1" name="tipo_busqueda" id="tipo_busqueda">
                                    <option selected disabled>Seleccionar...</option>
                                    <option value="SUCURSAL">Por sucursal</option>
                                    <option value="AREA">Por Área</option>
                                    <option value="EMPLEADO">Por empleado</option>
                                    <option value="TODOS">Todos</option>
                                </select>
                            </div>
                        </div>

                        <!-- SELECT DE SUCURSALES -->
                        <div id="contenedor-select-sucursales" class="col-md-3 col-sm-12 d-none">
                            <div class="form-group">
                                <label class="mr-2" for="sucursal_jornadas">Seleccionar sucursal</label>
                                <select name="sucursal_jornadas" id="sucursal_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                    <option selected disabled>Seleccionar...</option>
                                </select>
                            </div>
                        </div>
                        
                        <!-- SELECT DE AREAS -->
                        <div id="contenedor-select-areas" class="col-md-3 col-sm-12 d-none">
                            <div class="form-group">
                                <label class="mr-2" for="area_jornadas">Seleccionar área</label>
                                <select name="area_jornadas" id="area_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                    <option selected disabled>Seleccionar...</option>
                                </select>
                            </div>
                        </div>

                        <!-- SELECT DE EMPLEADOS -->
                        <div id="contenedor-select-empleados" class="col-md-3 col-sm-12 d-none">
                            <div class="form-group">
                                <label class="mr-2" for="empleado_jornadas">Seleccionar empleado</label>
                                <select name="empleado_jornadas" id="empleado_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                    <option selected disabled>Seleccionar...</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-1 p-3 d-flex">
                            <button class="btn btn-dark btn-block"><i class="fas fa-search"></i></button>
                        </div>

                        <div class="col-md-1 p-3 d-flex">
                            <button type="button" style="background-color: darkgreen; border-color: darkgreen" id="botonDescargaReporteJornada2" class="btn btn-dark btn-block d-none"><i class="fas fa-file-excel"></i></button>
                        </div>
                        
                    </form>                    
                </div>
                
                <div id="inicio_jornadas_laborales" class="jumbotron">
                    <h1 class="display-4">Generar reporte de jornadas laborales!</h1>
                    <p class="lead mt-3">Elige los parámetros de búsqueda para generar el reporte.</p>
                    <hr class="my-4">
                    <p>Pasos para generarlo.</p>

                    <ul>
                        <li>Elige un rango de búsqueda </li>
                        <li>Selecciona un tipo de búsqueda</li>
                        <li style="list-style: none;">
                            <ul>
                                <li>Por sucursal</li>
                                <li>Por Área</li>
                                <li>Por empleado</li>
                                <li>Todos (filtrará todos los empleados de la empresa)</li>
                            </ul>
                        </li>
                    </ul>
                    
                    <p style="font-style: italic;">El tiempo de generación dependerá del rango de búsqueda y de la cantidad de empleados que se seleccione</p>
                    
                </div>
                
                <div id="resultado-busqueda-jornadas2" class="d-none col-12">
                    
                    <div id="tablas_resultados" class="accordion">
                        
                    </div>
                    
                    <div class="d-none" id="resultados-exportar-excel">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 bg-white" id="reporteEmpleadoJornadasLaborales">
        <div class="row">
            <div class="col-12">
                <div class="row pt-4 pl-5">
                    <div class="col-6">
                        <h5 class="p-0" style="font-size: 180%">Reporte empleado</h5>
                    </div>
                    <div class="col-6">
                        <button type="button" class="btn btn-danger" id="botonRegresarJornadasLaborales">regresar</button>
                    </div>                                        
                </div>
                <hr class="bg-dark mt-1 ml-5">
                <div class="card p-3">
                    <div class="card-body text-dark border-0 p-4 shadow p-3 mb-4" style="border-radius: 25px;background: #f5f5f5">
                        <div class="row">
                            <!--<div class="col-12 col-lg-3">
                                <img class="card-img-left col-lg-11" src="https://picsum.photos/200" alt="Card image cap">
                            </div>-->
                            <div class="col-lg-3 pr-0">
                                <img class="card-img-left" width="180" height="180" id="avatarEmpleado" alt="Card image cap">    
                            </div>
                            <div class="col-lg-9 pl-0">
                                <div class="row">
                                    <div class="col-12 col-lg-8">
                                        <h5 class="card-title p-0" id="nombreEmpleado" style="font-size: 180%">Nombre: </h5>
                                        <p><strong class="p-0" style="color:#495057;font-size: 160%">Dirección: </strong><span class="card-text" id="direccionEmpleado"></span></p>
                                        <div class="row pt-2">
                                            <div class="col-6">
                                                <p><strong>Área: </strong><span class="card-text" id="areaEmpleado"></span></p>
                                            </div>
                                            <div class="col-6">
                                                <p><strong>Cargo: </strong><span class="card-text" id="cargoEmpleado"></span></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-lg-4">
                                        <p class="card-text mt-3 mb-4" style="font-size: 115%">Folio de Empleado: <span id="num_empleado"></span></p>
                                        <div class="row justify-content-center">
                                            <div class="col-3 p-0">
                                                <button type="button" id="inicia_llamada_empleado_en_jornada_reporte_empleado" class="btn rounded-circle rounded" style="background: #53AC30"><i class="text-white fas fa-phone-square-alt fa-lg"></i></button>
                                                <p class="card-text" style="font-size:80%;color: #9B9B9B">LLamada</p>
                                            </div>
                                            <div class="col-3 p-0">
                                                <button type="button" id="enviar_mensaje_empleado_en_jornada_reporte_empleado" class="btn rounded-circle rounded" style="background: #E13E1F"><i class="text-white far fa-comment-dots fa-lg"></i></button>
                                                <p class="card-text" style="font-size:80%;color: #9B9B9B">Chat</p>
                                            </div>
                                            <div class="col-4 p-0">
                                                <button type="button" id="inicia_llamada_empleado_en_jornada_reporte_empleado" class="btn rounded-circle rounded" style="background: #2C95A9"><i class="text-white fas fa-video fa-sm"></i></button>
                                                <p class="card-text" style="font-size:80%;color: #9B9B9B">Videollamada</p>
                                            </div>
                                        </div>                                                                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <p><strong>Reporte de Aislamiento: </strong><span class="card-text" id="reporteAislamientoEmpleado"></span></p>
                                        <strong>Actividades a desempeñar: </strong><span class="card-text" id="actividadesDesempeñarEmpleado">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam non elit vel dui pretium porta. Curabitur eget diam porta, tempus arcu vitae, convallis purus. Praesent quis semper nunc, non pretium quam. Phasellus placerat rhoncus porta. </span>
                                    </div>                                    
                                </div>                                    
                            </div>        
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row pb-1 pl-3 pr-0">
            <div class="card col-12 col-lg-7 ">
                <div class="card-body text-dark border-0 shadow mb-4" style="border-radius: 25px;background: #f5f5f5">
                    <strong style="font-size:120%;">Rendimiento semanal</strong>
                    <hr class="bg-dark mt-1">
                    <strong style="font-size:150%;color: #555555;"><strong style="font-size:100%;color: #555555;" id="diaLunes"></strong> al <strong style="font-size:100%;color: #555555;" id="diaViernes"></strong> de <strong style="font-size:100%;color: #555555;" id="stringMesRendimientoSemanal"></strong></strong>
                    <p>Este fue su rendimiento durante la semana</p>
                    <div class="row d-flex align-items-center pr-3">
                        <div class="col-lg-3 p-0">
                            <div style="position:relative;padding-bottom:100%;height: 0;overflow: hidden">                               
                                <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart"></div>
                                <div id="labelOverlay">
                                    <strong>Productividad</strong>
                                    <p><span id="jornadas_laborales_productividad_porcentaje">25</span><span>%</span></p>
                                </div>
                            </div>                                
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoDiasLaboralesEmpleado"></i>
                            <p>Dias laborales</p>
                            <h5 class="p-0 d-flex justify-content-center" id="diasLaboralesSemanaEmpleado"></h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoHorasLaboralesEmpleado"></i>
                            <p>Horas laborales</p>
                            <h5 class="p-0 d-flex justify-content-center" id="horasLaboralesSemanaEmpleado"></h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="far fa-smile fa-lg" style="color:#97BA38"></i>
                            <p>Proyectos atendidos</p>
                            <h5 class="p-0 d-flex justify-content-center"> - </h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="colorIconoRetardos"></i>
                            <p>Retardos</p>
                            <h5 class="p-0 d-flex justify-content-center mt-4" id="retardosSemanaEmpleado"></h5>
                        </div>
                        <div class="col-6 col-lg-1 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoFaltasLaboralesEmpleado"></i>
                            <p>Faltas</p>
                            <h5 class="p-0 d-flex justify-content-center mt-4" id="faltasSemanaEmpleado"></h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card col-12 col-lg-5 pr-5">
                <div class="card-body text-dark border-0 shadow p-3 mb-4" style="background: #f5f5f5;border-radius: 25px">
                    <strong style="font-size:120%;">Actividades laborales</strong>
                    <hr class="bg-dark mt-1">
                    <div style="position:relative;padding-bottom:35%;height: 0;overflow-y: auto;overflow-y: hidden">
                        <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="piechartActividadesLaborales"></div>
                    </div>
                    <script type="text/javascript">
                        google.charts.load("visualization", "1", {'packages': ['corechart']});
                        google.charts.setOnLoadCallback(drawChart);
                        function drawChart() {
                            var data = google.visualization.arrayToDataTable([
                                ['Actividades', 'Hours per Day'],
                                ['Proyectos', 50],
                                ['Reuniones de trabajo', 20],
                                ['Presentaciones', 20],
                                ['Sin conexion en plataforma', 2]
                            ]);
                            var options = {
                                title: 'Actividades laborales',
                                //width: '100%',
                                colors: ['#96C02A', '#F6BC32', '#278597', '#E74339'],
                                backgroundColor: '#f5f5f5',
                                chartArea: {
                                    left: "0%",
                                    //height: "50%",
                                    top: "0%",
                                    //width: "35%"
                                }
                            };
                            var chart = new google.visualization.PieChart(document.getElementById('piechartActividadesLaborales'));
                            chart.draw(data, options);
                        }
                        $(window).on("throttledresize", function (event) {
                            var options = {
                                width: '100%',
                                height: '100%'
                            };
                            var data = google.visualization.arrayToDataTable([]);
                            drawChart(data, options);
                        });
                    </script>
                </div>
            </div>            
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card m-4 shadow p-3 mb-4" style="background: #f5f5f5;border-radius: 25px">
                    <div class="card-body text-dark border-0" style="background: #f5f5f5;border-radius: 25px">
                        <strong style="font-size:120%;">Aprovechamiento laboral</strong>
                        <hr class="bg-dark mt-1">
                        <p>Buscar su aprovechamiento laboral por fecha.</p>
                        <form id="form_aprovechamiento_laboral" class="mb-4">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="fecha_inicio_aprovechamiento_laboral" class="mr-2">Fecha de inicio</label>
                                        <input type="date" class="form-control" id="fecha_inicio_aprovechamiento_laboral">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div id="contenedor_fecha_final" class="form-group mx-sm-3">
                                        <label for="fecha_fin_aprovechamiento_laboral" class="mr-2">Fecha de fin</label>
                                        <input type="date" class="form-control" id="fecha_fin_aprovechamiento_laboral">
                                    </div>
                                </div>
                                <div class="col-md-2 p-3 d-flex">
                                    <button class="btn btn-danger btn-block">Buscar <i class="fas fa-search"></i></button>
                                </div>
                                <!--<div class="col-md-1 p-3 d-flex">
                                    <button type="button" style="background-color: darkgreen; border-color: darkgreen" id="botonDescargaActividadesLaborales" class="btn btn-dark btn-block d-none"><i class="fas fa-file-excel"></i></button>
                                </div>-->
                            </div>
                        </form>
                        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                        <script type="text/javascript">
                        google.charts.load('current', {packages: ['corechart', 'line']});
                        google.charts.setOnLoadCallback(drawBasic);
                        function drawBasic() {
                            var data = new google.visualization.DataTable();
                            data.addColumn('string', 'fecha');
                            data.addColumn('number', 'puntialidad');
                            data.addColumn('number', 'retardos');
                            data.addColumn('number', 'proyectos');
                            data.addColumn('number', 'sin conexion');
                            data.addRows([
                                ["ene", 0, 0, 6, 0], ["feb", 4, 5, 2, 0], ["2", 4, 15, 6, 0], ["3", 12, 9, 3, 0], ["4", 4, 10, 8, 0], ["5", 12, 5, 2, 0],
                                ["6", 12, 3, 8, 3], ["7", 8, 9, 2, 0], ["8", 4, 2, 5, 0]
                            ]);
                            var options = {
                                width: 900,
                                height: 'auto',
                                legend: 'none',
                                hAxis: {
                                    title: 'Fecha'
                                },
                                vAxis: {
                                    title: 'Horas'
                                },
                                backgroundColor: '#f5f5f5',
                                chartArea: {
                                    //left: "0%",
                                    //height: "auto",
                                    //top: "0%",
                                    width: '100%'
                                },
                                pointSize: 8,
                                dent: 0.05,
                                colors: ['#F4BC32', '#97BD2A', '#257F8E', '#CC3C43']
                            };
                            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                            chart.draw(data, options);
                        }
                        /*$(window).resize(function () {
                         drawCurveTypes();
                         });*/
                        </script>
                        <div class="row">
                            <div class="col-12">                                            
                                <!-- <div id="linechart_material"></div> -->
                                <div style="position:relative;padding-bottom:50%;height: 0;overflow-y: auto;overflow-x: auto">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="chart_div">
                                        <!--<div id="linechart_material"></div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row p-3">
            <div class="col-12">
                <div class="card">
                    <div class="card-body text-dark border-0 shadow p-3 mb-4" style="background: #f5f5f5;border-radius: 25px">
                        <strong style="font-size:120%;">Rendimiento mensual</strong>
                        <hr class="bg-dark mt-1">
                        <h5 class="p-0 pb-2" style="font-size:170%;" id="stringMesRendimientoMensual"></h5>
                        <p>Este fue su rendimiento durante el mes.</p>
                        <div class="row">
                            <!--<div class="col-12 col-lg-3">
                                <div class="datepicker" id="calendarioRendimientoMensual"></div>
                            </div>-->
                            <div class="datepicker col-12 col-lg-4 pr-5" id="calendarioRendimientoMensual"></div>
                            <div class="col-12 col-lg-1 p-0 pt-5">
                                <div class="row">
                                    <div class="col-3 col-lg-12">
                                        <div class="rectangleColor3 p-2" style="width: 16px;height: 16px"></div>
                                        <p style="font-size: 90%">Puntuales</p>
                                    </div>
                                    <div class="col-3 col-lg-12">
                                        <div class="rectangleColor2 p-2" style="width: 16px;height: 16px"></div>
                                        <p style="font-size: 90%">Retardos</p>
                                    </div>
                                    <div class="col-3 col-lg-12">
                                        <div class="rectangleColor1 p-2" style="width: 16px;height: 16px"></div>
                                        <p style="font-size: 90%">Faltas</p>
                                    </div>
                                    <div class="col-3 col-lg-12">
                                        <div class="rectangleVacio2 p-1" style="width: 16px;height: 16px"></div>
                                        <p style="font-size: 90%">Vacaciones</p>
                                    </div>
                                </div>                              
                            </div>
                            <div class="col-12 col-lg-3 pr-0 pl-0">
                                <div style="position:relative;padding-bottom:77%;height: 0;overflow:hidden">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart2"></div>
                                    <div id="labelOverlay">
                                        <strong>Productividad</strong>
                                        <p><span id="jornadas_laborales_productividad_mensual_porcentaje">25</span><span>%</span></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-2 pl-0">
                                <div style="position:relative;padding-bottom:60%;height: 0;overflow: hidden">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart3"></div>
                                    <div id="labelOverlay">
                                        <strong style="font-size: 8px">Puntualidad</strong>
                                        <p><span style="font-size: 15pt" id="jornadas_laborales_puntualidad_porcentaje">25</span><span style="font-size: 15pt">%</span></p>
                                    </div>
                                </div>                                
                                <div style="position:relative;padding-bottom:60%;height: 0;overflow: hidden">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart4"></div>
                                    <div id="labelOverlay">
                                        <strong style="font-size: 8px">Cumplimiento</strong>
                                        <p><span style="font-size: 15pt" id="jornadas_laborales_cumplimiento_porcentaje">93</span><span style="font-size: 15pt">%</span></p>
                                    </div>
                                </div>                                
                            </div>
                            <div class="col-12 col-lg-2">
                                <p class="pb-3"><strong>Resumen General</strong></p>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Días laborales </p>
                                    </div>
                                    <div class="col-5">
                                        <p><strong id="diasLaboralesMesEmpleado"></strong><strong>/</strong><strong id="diasTotalesLaboralesMesEmpleado"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Horas laborales </p>
                                    </div>
                                    <div class="col-5">
                                        <p><strong id="horasLaboralesMesEmpleado"></strong><strong>/</strong><strong id="horasTotalesLaboralesMesEmpleado"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Retardos </p>
                                    </div>
                                    <div class="col-5">
                                        <p><strong id="retardosLaboralesMesEmpleado"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Faltas </p>
                                    </div>
                                    <div class="col-5">                                       
                                        <p><strong id="faltasLaboralesMesEmpleado"></strong></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Dias de vacaciones </p>
                                    </div>
                                    <div class="col-5">                                        
                                        <p><strong> - </strong></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
        </div>
        <div class="row p-3">
            <div class="col-12">
                <div class="card">
                    <div class="card-body text-dark border-0 shadow p-3 mb-4" style="background: #f5f5f5;border-radius: 25px">
                        <strong style="font-size:120%;">Colaboradores Principales</strong>
                        <hr class="bg-dark mt-1">
                        <p>Estos son los colaboradores con los que más interactuo en la plataforma360.</p>
                        <div class="row justify-content-center text-center">
                            <div class="col-lg-1"></div>
                            <div class="card col-12 col-lg-2" style="width: 18rem;">                                
                                <div class="card-body bg-white text-dark border-0 shadow mb-4 pb-4" style="border-radius: 25px">
                                    <div class="col-12 text-right pt-3"></div>  
                                    <img class="card-img-top rounded-circle col-4 col-lg-12" src="https://picsum.photos/200" alt="Card image cap">
                                    <h5 class="card-title p-0 mb-0 pt-4 justify-content-center" style="font-size: 130%">Renata Solis</h5>
                                    <p class="card-text mb-0">Tiempo total</p>
                                    <strong style="color: #6F6F6D;font-size: 120%">50 hrs 53 min</strong>
                                </div>
                            </div> 
                            <div class="col-lg-1"></div>
                            <div class="card col-12 col-lg-2" style="width: 18rem;">                                
                                <div class="card-body bg-white text-dark border-0 shadow mb-4 pb-4" style="border-radius: 25px">
                                    <div class="col-12 text-right p-0">
                                        <i class="fas fa-star fa-lg" style="color: #FDCF36"></i>
                                    </div>                                    
                                    <img class="card-img-top rounded-circle col-4 col-lg-9" src="https://picsum.photos/200" alt="Card image cap">
                                    <h5 class="card-title p-0 mb-0 pt-4 justify-content-center" style="font-size: 130%">Renata Solis</h5>
                                    <p class="card-text">Jefe de Ventas</p>
                                    <p class="card-text mb-0">Tiempo total</p>
                                    <strong style="color: #6F6F6D;font-size: 120%">50 hrs 53 min</strong>
                                </div>
                            </div> 
                            <div class="card col-12 col-lg-2" style="width: 18rem;">
                                <div class="card-body bg-white text-dark border-0 shadow mb-4 pb-4" style="border-radius: 25px">
                                    <div class="col-12 text-right p-0">
                                        <i class="fas fa-star fa-lg" style="color: #FDCF36"></i>
                                    </div>   
                                    <img class="card-img-top rounded-circle col-4 col-lg-9" src="https://picsum.photos/200" alt="Card image cap">
                                    <h5 class="card-title p-0 mb-0 pt-4 justify-content-center" style="font-size: 130%">Renata Solis</h5>
                                    <p class="card-text">Ventas</p>
                                    <p class="card-text mb-0">Tiempo total</p>
                                    <strong style="color: #6F6F6D;font-size: 120%">50 hrs 53 min</strong>
                                </div>
                            </div> 
                            <div class="card col-12 col-lg-2" style="width: 18rem;">
                                <div class="card-body bg-white text-dark border-0 shadow mb-4 pb-4" style="border-radius: 25px">
                                    <div class="col-12 text-right p-0">
                                        <i class="fas fa-star fa-lg" style="color: #FDCF36"></i>
                                    </div>   
                                    <img class="card-img-top rounded-circle col-4 col-lg-9" src="https://picsum.photos/200" alt="Card image cap">
                                    <h5 class="card-title p-0 mb-0 pt-4 justify-content-center" style="font-size: 130%">Renata Solis</h5>
                                    <p class="card-text">Administración</p>
                                    <p class="card-text mb-0">Tiempo total</p>
                                    <strong style="color: #6F6F6D;font-size: 120%">50 hrs 53 min</strong>
                                </div>
                            </div> 
                            <div class="card col-12 col-lg-2" style="width: 18rem;">
                                <div class="card-body bg-white text-dark border-0 shadow mb-4 pb-4" style="border-radius: 25px">
                                    <div class="col-12 text-right p-0">
                                        <i class="fas fa-star fa-lg" style="color: #FDCF36"></i>
                                    </div>   
                                    <img class="card-img-top rounded-circle col-4 col-lg-9" src="https://picsum.photos/200" alt="Card image cap">
                                    <h5 class="card-title p-0 mb-0 pt-4 justify-content-center" style="font-size: 130%">Renata Solis</h5>
                                    <p class="card-text">Marketing</p>
                                    <p class="card-text mb-0">Tiempo total</p>
                                    <strong style="color: #6F6F6D;font-size: 120%">50 hrs 53 min</strong>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>                
            </div>
        </div>
        <div class="row p-3">
            <div class="col-12">
                <div class="card">
                    <div class="card-body text-dark border-0 shadow p-3 mb-4 w-100" style="background: #f5f5f5;border-radius: 25px">
                        <strong style="font-size:120%;">Historial laboral</strong>
                        <hr class="bg-dark mt-1">
                        <p>Seleccione las fechas para realizar la búsqueda de su historial de jornadas.</p>
                        <form id="form_historial_laboral2" class="mb-4">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="fecha_inicio_historial_laboral2" class="mr-2">Fecha de inicio</label>
                                        <input type="date" class="form-control" id="fecha_inicio_historial_laboral2">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div id="contenedor_fecha_final" class="form-group mx-sm-3">
                                        <label for="fecha_fin_historial_laboral2" class="mr-2">Fecha de fin</label>
                                        <input type="date" class="form-control" id="fecha_fin_historial_laboral2">
                                    </div>
                                </div>
                                <div class="col-md-2 p-3 d-flex">
                                    <button type="button" class="btn btn-danger btn-block" id="buscar_reportes_personales">Buscar <i class="fas fa-search"></i></button>
                                </div>
                                <!--<div class="col-md-1 p-3 d-flex">
                                    <button type="button" style="background-color: darkgreen; border-color: darkgreen" id="botonDescargaHistorialLaboral" class="btn btn-dark btn-block d-none"><i class="fas fa-file-excel"></i></button>
                                </div>-->
                            </div>
                        </form>                                    
                        <div id="empleadoSinHistorialLaboral" class="d-none">
                            <div class="alert alert-info" role="alert">no se encontro ninguna jornada laboral!</div>
                        </div>
                        <div id="empleadoConHistorialLaboral" class="d-none">
                            <div class="row p-3">
                                <div class="card col-6 col-lg-2">
                                    <div class="card-body bg-white text-dark border-0 rounded pb-0">
                                        <div class="row">
                                            <div class="col-4">
                                                <i class="text-success fas fa-star fa-2x"></i>
                                            </div>
                                            <div class="col-8">
                                                <strong>Puntual</strong>
                                                <p><span id="puntualHistorialLaboral"></span><span> dias</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card col-6 col-lg-2">
                                    <div class="card-body bg-white text-dark border-0 rounded pb-0">
                                        <div class="row">
                                            <div class="col-4">
                                                <i class="text-danger fas fa-clock fa-2x"></i>
                                            </div>
                                            <div class="col-8">
                                                <strong>Retardo</strong>
                                                <p><span id="retardoHistorialLaboral"></span><span> dias</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-2">
                                    <button type="button"><i class="fas fa-print fa-3x"></i></button>
                                </div>
                            </div>
                            <div style="max-height: 550px; overflow-y: auto">
                                <table id="tablaHistorialLaboralEmpleado" class="table table-hover">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                            <th scope="col">Día</th>
                                            <th scope="col">Fecha</th>
                                            <th scope="col">Hora de entrada</th>
                                            <th scope="col">Hora de salida</th>
                                            <th scope="col">Horas laborales</th>
                                            <th scope="col">Cantidad Desconexiones</th>
                                            <th scope="col">Observaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody class="text-center" id="cuerpoTablaHistorialLaboralEmpleado">        
                                    </tbody>                    
                                </table>
                            </div>        
                            <div class="row">
                                <div class="col-12 d-flex flex-row-reverse">
                                    <h5 style="font-size: 120%" id="horasLaboralesTotales"></h5>
                                </div>                                
                            </div>                            
                        </div>                                                                        
                    </div>           
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

<spring:url value="${pathRecursos}/empresas360/reportejornadaslaborales/reportejornadaslaborales.css" var="modulo_reportejornadaslaboralesCSS" />
<spring:url value="${pathRecursos}/empresas360/reportejornadaslaborales/reportejornadaslaborales.js" var="modulo_reportejornadaslaboralesJS" />
<link href="${modulo_reportejornadaslaboralesCSS}" rel="stylesheet"/>
<script src="${modulo_reportejornadaslaboralesJS}" ></script>
<script>
//    init_reportejornadaslaborales("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
    init_reportejornadaslaborales(${json});
</script>