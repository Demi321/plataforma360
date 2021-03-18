<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Horario
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                            <div class="row col-12 m-0 p-1">
                                
                                <div class="row col-12 m-0 p-1">
                                    <table style="width:50%">
                                        <tr>
                                          <th>Materia</th>
                                          <th>Grupo</th> 
                                          <th>Lunes</th> 
                                          <th>Martes</th> 
                                          <th>Miercoles</th> 
                                          <th>Jueves</th> 
                                          <th>Viernes</th> 
                                          <th>Acciones</th> 
                                        </tr>
                                        <tr>
                                          <td>Materia n</td>
                                          <td>Gruopo n</td>
                                          <td>Hora n</td>
                                          <td>Hora n</td>
                                          <td>Hora n</td>
                                          <td>Hora n</td>
                                          <td>Hora n</td>
                                          <td>                                           
                                            <button type="button" class="btn btn-info">Unirse</button>
                                          </td>
                                        </tr>
                                    </table>                                    
                                </div>
                            </div>
                       </div>
                </div>
            </div>            
        </div>
    </div>
</div>


<!--script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
    agregar_menu(${json});
</script -->

<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>
