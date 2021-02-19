
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 misreportes" id="base_modulo_${id}">
    <div class="col-12 bg-white" id="misReportes">
        <div class="row">
            <div class="col-12">
                <div class="row pt-4 pl-5">
                    <div class="col-12">
                        <h5 class="p-0" style="font-size: 180%">Reporte empleado</h5>
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
                                <img class="card-img-left" width="180" height="180" id="avatarEmpleadoMisReportes" alt="Card image cap">    
                            </div>
                            <div class="col-lg-9 pl-0">
                                <div class="row">
                                    <div class="col-12 col-lg-8">
                                        <h5 class="card-title p-0" id="nombreEmpleadoMisReportes" style="font-size: 180%">Nombre: </h5>
                                        <p><strong class="p-0" style="color:#495057;font-size: 160%">Dirección: </strong><span class="card-text" id="direccionEmpleadoMisReportes"></span></p>
                                        <div class="row pt-2">
                                            <div class="col-6">
                                                <p><strong>Área: </strong><span class="card-text" id="areaEmpleadoMisReportes"></span></p>
                                            </div>
                                            <div class="col-6">
                                                <p><strong>Cargo: </strong><span class="card-text" id="cargoEmpleadoMisReportes"></span></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-lg-4">
                                        <p class="card-text mt-3 mb-4" style="font-size: 115%">Folio de Empleado: <span id="num_empleadoMisReportes"></span></p>                                                                                      
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <p><strong>Reporte de Aislamiento: </strong><span class="card-text" id="reporteAislamientoEmpleadoMisReportes"></span></p>
                                        <strong>Actividades a desempeñar: </strong><span class="card-text" id="actividadesDesempeñarEmpleadoMisReportes">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam non elit vel dui pretium porta. Curabitur eget diam porta, tempus arcu vitae, convallis purus. Praesent quis semper nunc, non pretium quam. Phasellus placerat rhoncus porta. </span>
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
                    <strong style="font-size:150%;color: #555555;"><strong style="font-size:100%;color: #555555;" id="diaLunesMisReportes"></strong> al <strong style="font-size:100%;color: #555555;" id="diaViernesMisReportes"></strong> de <strong style="font-size:100%;color: #555555;" id="stringMesRendimientoSemanalMisReportes"></strong></strong>
                    <p>Este fue su rendimiento durante la semana</p>
                    <div class="row d-flex align-items-center pr-3">
                        <div class="col-lg-3 p-0">
                            <div style="position:relative;padding-bottom:100%;height: 0;overflow: hidden">                               
                                <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchartMisReportes"></div>
                                <div id="labelOverlay">
                                    <strong>Productividad</strong>
                                    <p><span id="jornadas_laborales_productividad_porcentajeMisReportes">25</span><span>%</span></p>
                                </div>
                            </div>                                
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoDiasLaboralesEmpleadoMisReportes"></i>
                            <p>Dias laborales</p>
                            <h5 class="p-0 d-flex justify-content-center" id="diasLaboralesSemanaEmpleadoMisReportes"></h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoHorasLaboralesEmpleadoMisReportes"></i>
                            <p>Horas laborales</p>
                            <h5 class="p-0 d-flex justify-content-center" id="horasLaboralesSemanaEmpleadoMisReportes"></h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="far fa-smile fa-lg" style="color:#97BA38"></i>
                            <p>Proyectos atendidos</p>
                            <h5 class="p-0 d-flex justify-content-center">3</h5>
                        </div>
                        <div class="col-6 col-lg-2 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="colorIconoRetardosMisReportes"></i>
                            <p>Retardos</p>
                            <h5 class="p-0 d-flex justify-content-center mt-4" id="retardosSemanaEmpleadoMisReportes"></h5>
                        </div>
                        <div class="col-6 col-lg-1 p-0 text-center">
                            <i class="text-success far fa-smile fa-lg" id="iconoFaltasLaboralesEmpleadoMisReportes"></i>
                            <p>Faltas</p>
                            <h5 class="p-0 d-flex justify-content-center mt-4" id="faltasSemanaEmpleadoMisReportes"></h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card col-12 col-lg-5 pr-5">
                <div class="card-body text-dark border-0 shadow p-3 mb-4" style="background: #f5f5f5;border-radius: 25px">
                    <strong style="font-size:120%;">Actividades laborales</strong>
                    <hr class="bg-dark mt-1">
                    <div style="position:relative;padding-bottom:35%;height: 0;overflow-y: auto;overflow-y: hidden">
                        <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="piechartActividadesLaboralesMisReportes"></div>
                    </div>
                    <script type="text/javascript">
                        google.charts.load("visualization", "1", {'packages': ['corechart']});
                        google.charts.setOnLoadCallback(drawChartMisReportes);
                        function drawChartMisReportes() {
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
                            var chart = new google.visualization.PieChart(document.getElementById('piechartActividadesLaboralesMisReportes'));
                            chart.draw(data, options);
                        }
                        $(window).on("throttledresize", function (event) {
                            var options = {
                                width: '100%',
                                height: '100%'
                            };
                            var data = google.visualization.arrayToDataTable([]);
                            drawChartMisReportes(data, options);
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
                        <form id="form_aprovechamiento_laboralMisReportes" class="mb-4">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="fecha_inicio_aprovechamiento_laboralMisReportes" class="mr-2">Fecha de inicio</label>
                                        <input type="date" class="form-control" id="fecha_inicio_aprovechamiento_laboralMisReportes">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div id="contenedor_fecha_finalMisReportes" class="form-group mx-sm-3">
                                        <label for="fecha_fin_aprovechamiento_laboralMisReportes" class="mr-2">Fecha de fin</label>
                                        <input type="date" class="form-control" id="fecha_fin_aprovechamiento_laboralMisReportes">
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
                        google.charts.setOnLoadCallback(drawBasicMisReportes);
                        function drawBasicMisReportes() {
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
                            var chart = new google.visualization.LineChart(document.getElementById('chart_divMisReportes'));
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
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="chart_divMisReportes">
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
                        <h5 class="p-0 pb-2" style="font-size:170%;" id="stringMesRendimientoMensualMisReportes"></h5>
                        <p>Este fue su rendimiento durante el mes.</p>
                        <div class="row">
                            <!--<div class="col-12 col-lg-3">
                                <div class="datepicker" id="calendarioRendimientoMensualMisReportes"></div>
                            </div>-->
                            <div class="datepicker col-12 col-lg-4 pr-5" id="calendarioRendimientoMensualMisReportes"></div>
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
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart2MisReportes"></div>
                                    <div id="labelOverlay">
                                        <strong>Productividad</strong>
                                        <p><span id="jornadas_laborales_productividad_mensual_porcentajeMisReportes">25</span><span>%</span></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-2 pl-0">
                                <div style="position:relative;padding-bottom:60%;height: 0;overflow: hidden">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart3MisReportes"></div>
                                    <div id="labelOverlay">
                                        <strong style="font-size: 8px">Puntualidad</strong>
                                        <p><span style="font-size: 15pt" id="jornadas_laborales_puntualidad_porcentajeMisReportes">25</span><span style="font-size: 15pt">%</span></p>
                                    </div>
                                </div>                                
                                <div style="position:relative;padding-bottom:60%;height: 0;overflow: hidden">
                                    <div style="position: absolute;top: 0;left: 0;width: 100%;height: 100%" id="donutchart4MisReportes"></div>
                                    <div id="labelOverlay">
                                        <strong style="font-size: 8px">Cumplimiento</strong>
                                        <p><span style="font-size: 15pt" id="jornadas_laborales_cumplimiento_porcentajeMisReportes">93</span><span style="font-size: 15pt">%</span></p>
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
                                        <p><strong id="diasLaboralesMesEmpleadoMisReportes"></strong><strong>/</strong><strong id="diasTotalesLaboralesMesEmpleadoMisReportes"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Horas laborales </p>
                                    </div>
                                    <div class="col-5">
                                        <p><strong id="horasLaboralesMesEmpleadoMisReportes"></strong><strong>/</strong><strong id="horasTotalesLaboralesMesEmpleadoMisReportes"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Retardos </p>
                                    </div>
                                    <div class="col-5">
                                        <p><strong id="retardosLaboralesMesEmpleadoMisReportes"></strong></p>                                        
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Faltas </p>
                                    </div>
                                    <div class="col-5">                                       
                                        <p><strong id="faltasLaboralesMesEmpleadoMisReportes"></strong></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-7 pr-0">
                                        <p>Dias de vacaciones </p>
                                    </div>
                                    <div class="col-5">                                        
                                        <p><strong>4</strong></p>
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
                        <form id="form_historial_laboral2MisReportes" class="mb-4">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="fecha_inicio_historial_laboral2MisReportes" class="mr-2">Fecha de inicio</label>
                                        <input type="date" class="form-control" id="fecha_inicio_historial_laboral2MisReportes">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div id="contenedor_fecha_finalMisReportes2" class="form-group mx-sm-3">
                                        <label for="fecha_fin_historial_laboral2MisReportes" class="mr-2">Fecha de fin</label>
                                        <input type="date" class="form-control" id="fecha_fin_historial_laboral2MisReportes">
                                    </div>
                                </div>
                                <div class="col-md-2 p-3 d-flex">
                                    <button type="button" class="btn btn-danger btn-block" id="buscar_reportes_personalesMisReportes">Buscar <i class="fas fa-search"></i></button>
                                </div>
                                <!--<div class="col-md-1 p-3 d-flex">
                                    <button type="button" style="background-color: darkgreen; border-color: darkgreen" id="botonDescargaHistorialLaboral" class="btn btn-dark btn-block d-none"><i class="fas fa-file-excel"></i></button>
                                </div>-->
                            </div>
                        </form>                                    
                        <div id="empleadoSinHistorialLaboralMisReportes" class="d-none">
                            <div class="alert alert-info" role="alert">no se encontro ninguna jornada laboral!</div>
                        </div>
                        <div id="empleadoConHistorialLaboralMisReportes" class="d-none">
                            <div class="row p-3">
                                <div class="card col-6 col-lg-2">
                                    <div class="card-body bg-white text-dark border-0 rounded pb-0">
                                        <div class="row">
                                            <div class="col-4">
                                                <i class="text-success fas fa-star fa-2x"></i>
                                            </div>
                                            <div class="col-8">
                                                <strong>Puntual</strong>
                                                <p><span id="puntualHistorialLaboralMisReportes"></span><span> dias</span></p>
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
                                                <p><span id="retardoHistorialLaboralMisReportes"></span><span> dias</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-2">
                                    <button type="button"><i class="fas fa-print fa-3x"></i></button>
                                </div>
                            </div>
                            <div style="max-height: 550px; overflow-y: auto">
                                <table id="tablaHistorialLaboralEmpleadoMisReportes" class="table table-hover">
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
                                    <tbody class="text-center" id="cuerpoTablaHistorialLaboralEmpleadoMisReportes">        
                                    </tbody>                    
                                </table>
                            </div>        
                            <div class="row">
                                <div class="col-12 d-flex flex-row-reverse">
                                    <h5 style="font-size: 120%" id="horasLaboralesTotalesMisReportes"></h5>
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

<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
<script>
//    init_misreportes("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
    init_misreportes(${json});
</script>