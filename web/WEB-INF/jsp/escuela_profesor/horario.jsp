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
                                            <button type="button" class="btn btn-info">Lista</button>
                                            <button type="button" class="btn btn-info">Iniciar</button>
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
    <!-- MODALS-->
    <!-- MODAL PARA MOSTRAR LISTA DE ALUMNOS -->
    <div class="modal fade" id="tareaModal" tabindex="-1" role="dialog" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
            <!-- Pantalla lg -->
        <div class="modal-dialog modal-lg" role="document">
            <!-- CONTENIDO DEL MODAL -->
            <div class="modal-content">
                    <!-- HEADER -->
                    <div class="modal-header">
                    <h5 class="modal-title" id="nuevoEventoModalLabel">Lista</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                            </button>
                    </div>
                    <!-- FORMULARIO CON ID "nuevoEvento" -->
                    <!-- BODY DEL MODAL CON LOS INPUTS DEL EVENTO -->
                    <div class="modal-body">
                        <!-- CONTAINER  -->
                        <div class="container">
                            <!-- ROW -->
                            <div class="row">
                                    <!-- SUPERVISOR_ID -->
                                    <!-- <div class="col-12 col-md-4 mt-5">
                                            <label for="supervisor_id">supervisor_id</label>
                                            <input class="form-control" type="number" id="supervisor_id">
                                    </div> -->

                                    <!-- TITULO -->
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
                            </div>
                        </div>
                    </div>
                        <!-- FOOTER PARA BOTONES -->
                    <div class="modal-footer">
                            <!-- BOTON CANSELAR/QUITAR MODAL -->
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
