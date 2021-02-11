
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 monitoreodeempleados" id="base_modulo_${id_menu}">
 <div id="frame_monitoreo_empleados">
        <div id="sidepanel_grupos">
            <div class="row col-12 m-0 p-0" id="old_toggle">
            <div id="titulomenu"> Nuevo grupo </div>
        </div>
        <div id="old_sidebar" class="position-relative">
            <div id="buscarContactos_monitoreo">
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


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresas360/monitoreodeempleados/monitoreodeempleados.css" var="modulo_monitoreodeempleadosCSS" />
<spring:url value="${pathRecursos}/empresas360/monitoreodeempleados/monitoreodeempleados.js" var="modulo_monitoreodeempleadosJS" />
<link href="${modulo_monitoreodeempleadosCSS}" rel="stylesheet"/>
<script src="${modulo_monitoreodeempleadosJS}" ></script>
<script>
    init_monitoreodeempleados("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>