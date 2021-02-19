
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 estadisticaglobal estadisticaglobal_${id}" id="base_modulo_${id}">
    <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_ReporteDashboardEmpresa">
    <section id="contenido">
        <div class="Contenedor">                   
            <div class="EmpleadosRCT">
                <p class="NombreCorporativo" id="nombre_corporativo" style="font-size: 10px">Nombre de Corporativo</p>
                <hr style="background-color: white; height: 1px; clear:both;">
                <div id="divTotalSucursales">
                    <p class="TotalSucursal" style="font-size: 10px;">                        
                        Total de sucursales <strong id="Total_sucursales"> 235</strong><br>
                    </p>                    
                </div>
                <div id="divTotalEmpleados">
                    <p class="TotalEmpleados" style="font-size: 10px;">                        
                        Total de empleados <strong id="Total_empleados"> 235</strong><br>
                    </p>
                    
                </div>
            </div>
            <div class="GraficaPastel" >
                <div class="row">
                    <div class="col-12 mr-0" id="PorcentageA">
                        <div class="row">                            
                            <div>
                                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                                <div id="piechart" style="width: 400px; height: 300px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="Sexo">                
                <hr style="background-color: white; height: 1px; clear:both;">
                <div class="row">
                    <div class="col-ms-7 col-md-12">
                        <div class="row">
                            <div class="col-sm-7 col-md-7" id="Faltas">
                                <div class="rectangleVacio2" id="recFalt1_1"></div>
                                <div class="rectangleVacio2" id="recFalt1_2"></div>
                                <div class="rectangleVacio2" id="recFalt1_3"></div>
                                <div class="rectangleVacio2" id="recFalt1_4"></div>
                                <div class="rectangleVacio2" id="recFalt1_5"></div>
                                <div class="rectangleVacio2" id="recFalt1_6"></div>
                                <div class="rectangleVacio2" id="recFalt1_7"></div>
                                <div class="rectangleVacio2" id="recFalt1_8"></div>
                                <div class="rectangleVacio2" id="recFalt1_9"></div>
                                <div class="rectangleVacio2" id="recFalt1_10"></div></div>
                            <div class="col-sm-5 col-md-5">
                                <p>Faltas <strong id="PorcentajaFaltas">5.9%</strong></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7 col-md-7" id="Retardos">
                                <div class="rectangleVacio3" id="recReta2_1"></div>
                                <div class="rectangleVacio3" id="recReta2_2"></div>
                                <div class="rectangleVacio3" id="recReta2_3"></div>
                                <div class="rectangleVacio3" id="recReta2_4"></div>
                                <div class="rectangleVacio3" id="recReta2_5"></div>
                                <div class="rectangleVacio3" id="recReta2_6"></div>
                                <div class="rectangleVacio3" id="recReta2_7"></div>
                                <div class="rectangleVacio3" id="recReta2_8"></div>
                                <div class="rectangleVacio3" id="recReta2_9"></div>
                                <div class="rectangleVacio3" id="recReta2_10"></div>
                            </div>
                            <div class="col-sm-5 col-md-5">
                                <p>Retardos <strong id="PorcentajeRetardos"> 29.4%</strong></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7 col-md-7" id="Puntales">
                                <div class="rectangleVacio3" id="recPunt3_1"></div>
                                <div class="rectangleVacio3" id="recPunt3_2"></div>
                                <div class="rectangleVacio3" id="recPunt3_3"></div>
                                <div class="rectangleVacio3" id="recPunt3_4"></div>
                                <div class="rectangleVacio3" id="recPunt3_5"></div>
                                <div class="rectangleVacio3" id="recPunt3_6"></div>
                                <div class="rectangleVacio3" id="recPunt3_7"></div>
                                <div class="rectangleVacio3" id="recPunt3_8"></div>
                                <div class="rectangleVacio3" id="recPunt3_9"></div>
                                <div class="rectangleVacio3" id="recPunt3_10"></div>
                            </div>
                            <div class="col-sm-5 col-md-5">
                                <p>Puntales <strong id="PorcentajePuntales">35.3%</strong></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>             
            <div class="DistribucionE">
                <p>Lista de edades</p>
                <hr style="background-color: white; height: 1px; clear:both;">
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <div class="wrapper">
                    <div>
                         <div id="chart_div_edades" class="chart"></div> 
                    </div>
                    <div class="DatosGraficaEdades">
                        <h2 style="text-align: center">Lsita de Edades</h2>
                        <table class="table1">
                        <tbody>
                            <tr>                             
                                <td style="padding: 0.75rem;">Rangos de Edades</td>                              
                                <td>No. Empleados</td>
                            </tr>
                            <tr>                                                            
                                <td>1. De 18 a 25 años</td>
                                <td class="td2">30</td>
                            </tr>
                            <tr>
                              <td>2. De 26 a 35</td>
                              <td class="td2">60</td>                              
                            </tr>
                            <tr>
                              <td>3. De 36 a 45</td>
                              <td class="td2">40</td>                              
                            </tr>
                            <tr>
                              <td>4. De 46 a 55</td>
                              <td class="td2">10</td>                              
                            </tr>
                            <tr>
                              <td>5. Mayores de 60</td>
                              <td class="td2">10</td>                              
                            </tr>
                            <tr>
                              <td>6. Sin fecha de nacimiento</td>
                              <td class="td2">10</td>                              
                            </tr>
                          </tbody>
                        </table>
                    </div> 
                </div>                                                                     
                <!--div id = "containerLine" class="chart"-->
            </div> 
            <div class="DistribucionSexo">
                <p>Sexo de Empleados</p>
                <hr style="background-color: white; height: 1px; clear:both;">
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <div class="wrapper">
                    <div>
                        <div id="chart_div_sexo" class="chart"></div>
                    </div>   
                    <div class="DatosGraficaEdades">
                        <h2 style="text-align: center">Número de Empleados acorde a sexo</h2>
                        <table class="table1">
                        <tbody>
                            <tr>                             
                                <td style="padding: 0.75rem;">Sexo</td>                              
                                <td>No. Empleados</td>
                                <td>Procentaje</td>
                            </tr>
                            <tr>                                                            
                                <td>Hombres</td>
                                <td class="td2">100</td>
                                <td class="td2">64%</td>
                            </tr>
                            <tr>
                              <td>Mujeres</td>
                              <td class="td2">50</td>
                              <td class="td2">33%</td>
                            </tr>                            
                          </tbody>
                        </table>
                    </div>
                </div>
                <!--div id = "containerLine" class="chart"-->
            </div> 
            <div class="DistribucionSucursales">
                <p>Empleados Registrados</p>
                <hr style="background-color: white; height: 1px; clear:both;">
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <div class="wrapper">
                    <div>
                        <div id="chart_div_sucursales" class="chart"></div>
                    </div>
                    <div class="DatosGraficaEdades">
                        <h2 style="text-align: center">Lsita de sucursales</h2>
                        <table class="table1">
                        <tbody>
                            <tr>                             
                                <td style="padding: 0.75rem;">Sucursal</td>                              
                                <td>No. Empleados</td>
                            </tr>
                            <tr>
                                <td>1. De 18 a 25 años</td>
                                <td class="td2">30</td>
                            </tr>
                            <tr>
                              <td>2. De 26 a 35</td>
                              <td class="td2">60</td>                              
                            </tr>
                            <tr>
                              <td>3. De 36 a 45</td>
                              <td class="td2">40</td>                              
                            </tr>
                            <tr>
                              <td>4. De 46 a 55</td>
                              <td class="td2">10</td>                              
                            </tr>
                            <tr>
                              <td>5. Mayores de 60</td>
                              <td class="td2">10</td>                              
                            </tr>
                            <tr>
                              <td>6. Sin fecha de nacimiento</td>
                              <td class="td2">10</td>                              
                            </tr>
                          </tbody>
                        </table>
                    </div>                    
                </div>                
                <!--div id = "containerLine" class="chart"-->
            </div> 
            <div class="ActividadesLaborales">
                <p>Actividades laborales</p>
                <hr style="background-color: white; height: 1px; clear:both;">                     
                <div class="row">
                    <div class="col-12 mr-0" id="PorcentageB">
                        <div class="row">                                                                                               
                            <div>
                                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                                <div class="wrapper">
                                    <div>                                        
                                        <div id="chart_div_act_laborales" style="width: 400px; height: 300px;"></div>
                                    </div>
                                    <div class="DatosGraficaEdades">
                                        <h2 style="text-align: center">Actividades laborales</h2>
                                        <table class="table1">
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td style="padding: 0.75rem;">Actividad laboral</td>                              
                                                <td>Porcentaje</td>
                                            </tr>
                                            <tr>                                                            
                                                <td class="rectangleActLab1" ></td>
                                                <td>Proyectos</td>
                                                <td class="td2">50%</td>
                                            </tr>
                                            <tr>
                                                <td class="rectangleActLab2" ></td>
                                                <td>Reuniones de trabajo</td>
                                                <td class="td2">20%</td>                              
                                            </tr>
                                            <tr>
                                                <td class="rectangleActLab3" ></td>
                                                <td>Presentaciones</td>
                                                <td class="td2">20%</td>                              
                                            </tr>
                                            <tr>
                                                <td class="rectangleActLab4" ></td>
                                                <td>Sin conexion en plataforma</td>
                                                <td class="td2">10%</td>
                                            </tr>                                                                                      
                                          </tbody>
                                        </table>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ProductividadSucursal">
                <div class="col-12">
                    <div class="row">
                        <div class="col-12">
                            <p>Productividad por sucursal</p>
                            <hr style="background-color: white; height: 1px;">
                        </div>
                        <div class="col-12 text-center">
                            <form id="form_productividad_sucursal" class="form-inline mt-4 justify-content-center">

                                <div class="form-group mb-2">
                                    <label for="fecha_inicio_reporte" class="mr-3">Sucursales</label>
                                    <select class="form-control"  id="sucursales" placeholder="Seleccione uno" required="">
                                        <option disabled="" selected="" value="">Selecciona una opción</option>
                                    </select>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="fecha_inicio_reporte" class="mr-3">Fecha de inicio</label>
                                    <input type="date" class="form-control" id="fecha_inicio_productividad_sucursal">
                                </div>
                                <div id="contenedor_fecha_final" class="form-group mx-sm-3 mb-2">
                                    <label for="fecha_fin_reporte" class="mr-3">Fecha de fin</label>
                                    <input type="date" class="form-control" id="fecha_fin_productividad_sucursal">
                                </div>

                                <div class="form-group mb-2">
                                    <button class="btn btn-primary ml-3"> Buscar <i class="fas fa-search"></i></button>
                                    <button id="botonDescargaReporteJornada" class="btn btn-dark ml-3 d-none"><i class="fas fa-file-excel"></i></button>
                                </div>
                            </form>
                        </div>
                        <div class="col-12">
                            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                            <div id="chart_div4" style="width: 100%; height: 100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="InversionTiempo">
                <div class="col-12">
                    <div class="row">
                        <div class="col-12">
                            <p>Inversión en tiempo</p>
                            <hr style="background-color: white; height: 1px;">
                        </div>
                        <div class="col-12 text-center">
                            <form id="form_inversion_tiempo" class="form-inline mt-4 justify-content-center">

                                <div class="form-group mb-2">
                                    <label for="fecha_inicio_reporte" class="mr-3">Sucursales</label>
                                    <select class="form-control"  id="sucursales" placeholder="Seleccione uno" required="">
                                        <option disabled="" selected="" value="">Selecciona una opción</option>
                                    </select>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="fecha_inicio_reporte" class="mr-3">Fecha de inicio</label>
                                    <input type="date" class="form-control" id="fecha_inicio_inversion_tiempo">
                                </div>
                                <div id="contenedor_fecha_final" class="form-group mx-sm-3 mb-2">
                                    <label for="fecha_fin_reporte" class="mr-3">Fecha de fin</label>
                                    <input type="date" class="form-control" id="fecha_fin_inversion_tiempo">
                                </div>

                                <div class="form-group mb-2">
                                    <button class="btn btn-primary ml-3"> Buscar <i class="fas fa-search"></i></button>
                                    <button id="botonDescargaReporteJornada" class="btn btn-dark ml-3 d-none"><i class="fas fa-file-excel"></i></button>
                                </div>

                            </form>
                        </div>
                        <div class="col-12">
                            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                            <div id="chart_div_line" style="width: 100%; height: 100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="HistoralLaboral">                
                <div class="col-12">
                    <div class="row">
                        <div class="col-12">
                            <p>Historail Laboral</p>
                            <hr style="background-color: white; height: 1px;">
                        </div>  
                    </div>                                       
                </div>
                <div class="col-12 text-center">
                    <form id="form_historial_laboral" class="form-inline mt-4 justify-content-center">

                        <div class="form-group mb-2">
                            <label for="fecha_inicio_reporte" class="mr-3">Sucursales</label>
                            <select class="form-control"  id="sucursales" placeholder="Seleccione uno" required="">
                                <option disabled="" selected="" value="">Selecciona una opción</option>
                            </select>
                        </div>
                        <div class="form-group mb-2">
                            <label for="fecha_inicio_reporte" class="mr-3">Fecha de inicio</label>
                            <input type="date" class="form-control" id="fecha_inicio_historial_laboral">
                        </div>
                        <div id="contenedor_fecha_final" class="form-group mx-sm-3 mb-2">
                            <label for="fecha_fin_reporte" class="mr-3">Fecha de fin</label>
                            <input type="date" class="form-control" id="fecha_fin_laboral">
                        </div>

                        <div class="form-group mb-2">
                            <button class="btn btn-primary ml-3"> Buscar <i class="fas fa-search"></i></button>
                            <button id="botonDescargaReporteJornada" class="btn btn-dark ml-3 d-none"><i class="fas fa-file-excel"></i></button>
                        </div>

                    </form>
                </div>
                <div id="resultado-busqueda-historial-laborañl" class="d-none mt-4">
                    <div id="con-resultados-historial-laboral" class="table-responsive d-none">
                        <table class="table table-hover table-striped>"
                               <thead class="thead-dark"">
                            <tr>
                                <th>Día</th>
                                <th>Fecha</th>
                                <th>Hora entrada</th>
                                <th>Hora salida</th>
                            </tr>
                            </thead>
                            <tbody id="cuerpo-tabla-jornadas"></tbody>
                        </table>
                    </div>
                    <div id="sin-resultados-jornadas" class="d-none">
                        <div style="font-size: 1.5rem;" class="alert alert-dark text-center" role="alert">No se encontraron resultados en este periodo</div>
                    </div>                    
                    <table class="d-none" id="resultados-exportar-excel">
                    </table>
                </div>
            </div>            
        </div>      
    </section>
</div>
</div>

<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//    agregar_menu("${id_menu}", "${icono_categoria}", "${nombre}", "${alias}", '${icono}', '${categoria}', "");
agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresas360/estadisticaglobal/estadisticaglobal.css" var="modulo_estadisticaglobalCSS" />
<spring:url value="${pathRecursos}/empresas360/estadisticaglobal/estadisticaglobal.js" var="modulo_estadisticaglobalJS" />
<link href="${modulo_estadisticaglobalCSS}" rel="stylesheet"/>
<script src="${modulo_estadisticaglobalJS}" ></script>
<script>
//    init_estadisticaglobal("${id}", "${id_usuario}", "${tipo_usuario}", '${tipo_servicio}', '${tipo_area}');
init_estadisticaglobal(${json});
</script>