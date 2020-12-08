<%-- 
    Document   : modulo_monitoreo_personal
    Created on : 24/11/2020, 10:24:53 AM
    Author     : global
--%>

<!-- 
========================================
MóDULO DE REPORTING 
========================================
-->
<div class="row col-12 m-0 p-0 h-100" id="base_modulo_MonitoreodeEmpleados">
    <div id="frame_monitoreo_empleados">
        <div id="sidepanel_grupos">
            <div class="row col-12 m-0 p-0" id="old_toggle">
            <div id="titulomenu"> Nuevo grupo </div>
        </div>
        <div id="old_sidebar" class="position-relative">
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
                        :hide-selected="false"
                        @close="onClosed"
                        @tag="onTag"
                        @remove="onRemove"
                        @input="onInput"
                        @open="onTouch"
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
            <div class="p-0"  id="grupos" style="overflow-y: scroll;">
                <span id="span" style="color: lightblue; display: block; font-size: 12px; display:none;"  >${Alerta}</span>
                <%-- Aqui se iran insertando los grupos --%>
                <div class="accordion" id="accordion">
                </div>
            </div>
        </div>
        </div>

        <div class="h-100" id="content_map">
            <div class="content_map">
                <div class="col-12 m-0 p-0 h-100" id="map" style="position: relative;"></div>
            </div>
        </div>
    </div>

</div>
<!-- 
========================================
FINAL MóDULO DE REPORTING
========================================
-->

<spring:url value="${pathRecursos}/css/Empresa/modulo_monitoreo_personal.css" var="modulo_monitoreo_personalCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_monitoreo_personal.js" var="modulo_monitoreo_personalJS" />
<link href="${modulo_monitoreo_personalCSS}" rel="stylesheet"/>
<script src="${modulo_monitoreo_personalJS}" ></script>