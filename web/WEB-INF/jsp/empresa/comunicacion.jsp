
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="contenedor-sonidos-notificaciones">
    
    <audio muted id="sonido1">
        <spring:url value="${pathRecursos}/Audio/sonido1.mp3" var="sonido1" />
        <source src="${sonido1}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido2">
        <spring:url value="${pathRecursos}/Audio/sonido2.mp3" var="sonido2" />
        <source src="${sonido2}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido3">
        <spring:url value="${pathRecursos}/Audio/sonido3.mp3" var="sonido3" />
        <source src="${sonido3}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido4">
        <spring:url value="${pathRecursos}/Audio/sonido4.mp3" var="sonido4" />
        <source src="${sonido4}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido5">
        <spring:url value="${pathRecursos}/Audio/sonido5.mp3" var="sonido5" />
        <source src="${sonido5}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido6">
        <spring:url value="${pathRecursos}/Audio/sonido6.mp3" var="sonido6" />
        <source src="${sonido6}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido7">
        <spring:url value="${pathRecursos}/Audio/sonido7.mp3" var="sonido7" />
        <source src="${sonido7}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido8">
        <spring:url value="${pathRecursos}/Audio/sonido8.mp3" var="sonido8" />
        <source src="${sonido8}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido9">
        <spring:url value="${pathRecursos}/Audio/sonido9.mp3" var="sonido9" />
        <source src="${sonido9}" type="audio/mpeg">
    </audio>
    
    <audio muted id="sonido10">
        <spring:url value="${pathRecursos}/Audio/sonido10.mp3" var="sonido10" />
        <source src="${sonido10}" type="audio/mpeg">
    </audio>
    
</div>

<div class="row col-12 m-0 p-0 h-100 comunicacion" id="base_modulo_${id_menu}">

    <div class="col-8 d-none" >
        <div id="app">
            <div style="width: 600px;">
                <label class="typo__label">Simple select / dropdown</label>
                <multiselect
                    v-model="value"
                    placeholder="Selecciona los participantes"
                    label="title"
                    track-by="id360"
                    :multiple="true"
                    :options="options"
                    :close-on-select="false"
                    :custom-label="customLabel"
                    :show-labels="true"
                    :hide-selected="true"
                    @close="onClosed"
                    @tag="onTag"
                    @remove="onRemove"
                    @input="onInput"
                    >
                    <template slot="singleLabel" slot-scope="props">
                        <img class="option__image" :src="props.option.img"/><span class="option__desc"><span class="option__title">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                    </template>

                    <template slot="option" slot-scope="props">
                        <img class="option__image" :src="props.option.img" />
                        <span class="option__desc"><span class="option__title ">{{ props.option.nombre }} {{ props.option.apellido_paterno }}  {{ props.option.apellido_materno }}</span></span>
                    </template>
                </multiselect>
                <input type="hidden" id="data_vue" :value="value">

            </div>
        </div>
    </div>
    <div class="col-4 d-none" >
        <input type="button" class="btn btn-danger" id="iniciar_llamada" value="iniciar llamada" />
    </div>
    <div id="frame">
        <div id="sidepanel">
            <div id="profile" class="">
                <div class="wrap">
                    <div class="cont-img online">
                    <div id="profile-img" class=" img" alt=""> </div>
                    </div>
                    <p id="profile-nombre"></p>
                    <i class="fa fa-chevron-down expand-button d-none" aria-hidden="true"></i>
                    <div id="status-options" class="d-none">
                        <ul  class="p-0 " >
                            <li id="status-online" class=""><span class="status-circle"></span> <p>Online</p></li>
                            <li id="status-away" class=""><span class="status-circle"></span> <p>Away</p></li>
                            <li id="status-busy" class=""><span class="status-circle"></span> <p>Busy</p></li>
                            <li id="status-offline" class="active"><span class="status-circle"></span> <p>Offline</p></li>
                        </ul>
                    </div>
                    <div id="expanded">
                        <label for="twitter"><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></label>
                        <input name="twitter" type="text" value="mikeross">
                        <label for="twitter"><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></label>
                        <input name="twitter" type="text" value="ross81">
                        <label for="twitter"><i class="fa fa-instagram fa-fw" aria-hidden="true"></i></label>
                        <input name="twitter" type="text" value="mike.ross">
                    </div>
                </div>
            </div>
            <div id="search">
                <label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
                <!--                <input type="text" placeholder="Search contacts...">-->
                <div id="buscarContactos">
                    <div>
                        <multiselect
                            v-model="value"
                            placeholder="Buscar ..."
                            label="title"
                            track-by="id360"
                            select-label=""
                            :options="options"
                            :close-on-select="true"
                            :custom-label="customLabel"
                            :show-labels="true"
                            :hide-selected="true"
                            @close="onClosed"
                            @tag="onTag"
                            @remove="onRemove"
                            @input="onInput"
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
                        <input type="hidden" id="data_vue" :value="value">

                    </div>
                </div>
            </div>
            <div id="contacts" class="">
                <ul  class="p-0" id="message_contacts">


                </ul>
            </div>
            <div id="bottom-bar">
                <button id="addGroup"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span> Nuevo Grupo</span></button>
                <button id="settings"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span> Configuración</span></button>
            </div>
        </div>
        
        <div class="h-100" style="display: none;" id="content_call">
            <div class="container-fluid h-100">
                
                <section>
                    <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
                          <div class="row col-12 m-0 p-0 "  style="height: 100%; top: 0;">
                              
                                <div class="col" id="side2">
                                      <div id="videos">
                                            <div class=" p-0 row GRIDcontainer"  id="GRID">


                                            </div>
                                            <div id="publisher"></div>
                                      </div>

                                </div>


                                <div class="col  p-0" id="side1">
                                      <div class="col-12 p-0 side1Map">
                                            <div style="height: 35px;" class="card-headertitle text-center" >
                                                  PARTICIPANTES
                                                  <input type="button" id="directorio" class="AddNewGroup der">
                                            </div> 
                                            <%-- Ubicacion Mapa --%>
                                            <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" style="    height: calc(100% - 3.3vh); color: black;">


                                                  <div id="participantes" class="containerCardsParticipantes">

                                                  </div>
                                            </div>
                                      </div> 
                                            
                                            
                                      <div class="col-12 p-0 side1Chat">

                                            <form id="chat" class="h-100">
                                                  <div class="card" id="textchat" >
                                                        <div class="card-headertitle text-center" >
                                                              CHAT
                                                        </div>
                                                        <div class="card-body" style="background-color: #f4f4f4;">

                                                              <div class="row" id="history" style="width: 100%; margin: 0;">

                                                              </div>


                                                              <div class="inputTextChat" style=" background: none;border: none;">
                                                                    <input class="form-control " type="text" placeholder="Comienza un chat aqui" id="msgTxt" disabled="true" autocomplete="off">
                                                              </div>

                                                        </div>

                                                  </div>
                                            </form>

                                      </div>
                                </div>


                          </div>
                    </div>
                    <%-- Información del Perfil - seccion izquierda--%>
                    <div class="row col-sm-12 col-md-12 col-lg-12 col-xl-12" style="display: none;">
                          <!-- Información General -->


                          <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6  " style="padding: 5px; padding-top: 0;    height: -webkit-fill-available;">


                                <br>





                          </div>

                          <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6 " style="padding: 5px; padding-top: 0; ">
                                <!--div class="card-headertitle text-center">
                                    <h3>Llamada</h3>
                                </div-->
                                <div class="card-body">
                                      <div class="row col-12 mx-auto mt-1" style="padding: 0; height: 100%; max-height: 800px;">
                                            <div class="col-9">
                                                  <div class="col-sm-12 col-md-9 col-lg-9 col-xl-9 embed-responsive embed-responsive-1by1">
                                                        <div class="embed-responsive-item" id="videos">

                                                              <div id="subscriber" class="big" style="background:#929799; border-right: solid 1px white;">
                                                                    <!-- Trigger/Open The Modal -->

                                                              </div>
                                                              <div id="publisher" name="publisher">

                                                              </div> 
                                                              <div id="botonera">

                                                              </div>


                                                        </div>  

                                                  </div>
                                            </div>
                                            <div class="col-3" id="waitingbar" style="border-left: solid 1px; padding: 0; max-height: 100%; overflow-y: scroll;">


                                            </div>
                                      </div>
                                </div>
                          </div>


                    </div>



                    <%-- Información  - seccion derecha --%>
                    <div class="col-sm-12 col-md-12 col-lg-9 col-xl-9">

                          <div class="row">


                          </div>
                          <%-- Chat --%>
                          <%-- Chat --%>

                    </div>
                    <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>
              </section>
                
            
            </div>
        </div>

        <div class="h-100" id="content_messaging">
            
        </div>
    </div>
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/comunicacion/comunicacion.css" var="modulo_comunicacionCSS" />
<spring:url value="${pathRecursos}/empresas360/comunicacion/comunicacion.js" var="modulo_comunicacionJS" />
<link href="${modulo_comunicacionCSS}" rel="stylesheet"/>
<script src="${modulo_comunicacionJS}" ></script>
<script>
    init_comunicacion("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>