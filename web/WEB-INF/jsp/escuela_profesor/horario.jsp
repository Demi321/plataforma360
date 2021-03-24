<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="card col-12">
            <div class="card-body pt-0">
                <span class="title">
                    Horario
                </span>
                <div class="row col-12 m-0 p-1">

                    <table style="width:100%" class="table table-hover">
                        <thead>
                            <tr>
                              <th scope="col">Materia</th>
                              <th scope="col">Grupo</th> 
                              <th scope="col">Lunes</th> 
                              <th scope="col">Martes</th> 
                              <th scope="col">Miercoles</th> 
                              <th scope="col">Jueves</th> 
                              <th scope="col">Viernes</th> 
                              <th scope="col">Acciones</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                              <td scope="row">Materia n</td>
                              <td scope="row">Gruopo n</td>
                              <td scope="row">Hora n</td>
                              <td scope="row">Hora n</td>
                              <td scope="row">Hora n</td>
                              <td scope="row">Hora n</td>
                              <td scope="row">Hora n</td>
                              <td scope="row">                                           
                                <button type="button" class="btn btn-info">Crear Sesión</button>
                              </td>
                            </tr>
                        </tbody>
                    </table>                                    
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
