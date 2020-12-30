<%-- 
    Document   : modulo_lineamientos_estatales
    Created on : 22-nov-2020, 21:24:02
    Author     : Eduardo
--%>

<div class="row col-12 m-0 p-0" id="base_modulo_Notas">
    <h3>Notas</h3>
    <div class="row col-12 m-0 p-2 pt-3" id="contenido_cards">
        <div class="card m-2" style="width: 18rem;">
            <div class="card-body" style="background: white; color: black;">
                <p class="mb-5" style="text-align: center; font: bold 15px Arial;">Agregar Nueva Nota</p>
                <i class="far fa-calendar-plus" style="font-size: 10rem; display: flex; margin: auto; cursor: pointer;" onclick="add_note()"></i>
            </div>
        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_notas.css" var="modulo_notasCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_notas.js" var="modulo_notasJS" />
<link href="${modulo_notasCSS}" rel="stylesheet"/>
<script src="${modulo_notasJS}" ></script>

