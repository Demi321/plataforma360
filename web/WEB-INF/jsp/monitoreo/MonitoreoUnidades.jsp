<%-- 
    Document   : Monitoreo
    Created on : 02 Ene 2020, 06:07:15 PM
    Author     : Moisés Juárez 
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside class="toggleactive">
        <div class="row col-12 m-0 p-0" id="toggle">
            <div id="titulomenu"> Nuevo grupo </div>
        </div>
        <div id="sidebar" class="position-relative">
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
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-0" id="contenidoSection">
            <div class="col-12 m-0 p-0 h-100" id="map" style="position: relative;"></div>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/Empresa/appGrupos.js" ></script>
    <script src="${pathRecursos}/js/Empresa/directorio.js" ></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
    </script>

    <spring:url value="${pathRecursos}/css/Monitoreo/monitoreo.css" var="monitoreoCSS" />
    
    <link href="${monitoreoCSS}" rel="stylesheet"/>
</body>


