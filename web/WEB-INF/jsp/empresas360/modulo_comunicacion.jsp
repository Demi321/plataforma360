<%-- 
    Document   : modulo_comunicacion
    Created on : 6/11/2020, 06:02:00 PM
    Author     : moises
--%>

<div class="row col-12 m-0 p-0 h-100" id="base_modulo_Comunicación">

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
            <div id="bottom-bar" class="d-none">
                <button id="addcontact"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add contact</span></button>
                <button id="settings"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>Settings</span></button>
            </div>
        </div>

        <div class="h-100" id="content_messaging">
            
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_comunicacion.css" var="modulo_comunicacionCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_comunicacion.js" var="modulo_comunicacionJS" />
<link href="${modulo_comunicacionCSS}" rel="stylesheet"/>
<script src="${modulo_comunicacionJS}" ></script>
