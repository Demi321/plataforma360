

<div class="row col-12 p-0 m-0" style="margin-bottom: 5px; padding-bottom: 5px;">


    <div class="col-3 divImgPerfil" style="background-image: url('${perfilIMG}')">
        <img src=""  class="img-fotografia" id="ImgPerfil" style="display:none;" alt="Imagen de perfil de usuario"/>
    </div>

    <div class="col-9 divContainerDatosPerfil"  style="">
        <div class="row col-12 divContainerDatosPerfil2" >
            <label id="label" class="labelPerfil" >ID:</label>
            <input type="text" id="id" value="${id}" class="inputPerfil">
        </div>
        <div class="row col-12 divContainerDatosPerfil2" >
            <label id="label" class="labelPerfil" >Nombre:</label>
            <input type="text" id="NombrePerfil" value="${NombrePerfil}" class="inputPerfil">
        </div>


    </div>

    <div class="row col-12 divContainerDatosPerfil3">
         <div class="col-12 p-0">
        <label class="labelPerfil"  id="label">Dirección:</label>
        <input class="inputPerfil" id="DireccionPerfil" value="${DireccionPerfil}">
         </div>

    </div>
    <div class="row col-12 divContainerDatosPerfil3">
         <div class="col-12 p-0">
        <label  class="labelPerfil"   id="label">Fecha de Nacimiento: </label>
        <input type="text"  class="inputPerfil" id="Fecha_nacimiento" value="${Fecha_nacimiento}">   
         </div>
    </div>
    <div class="row col-12 divContainerDatosPerfil3">
        <div class="col-6 p-0">
            <label class="labelPerfil" id="label">Teléfono:</label>
            <input type="text" class="inputPerfil" id="TelPerfil" value="${TelPerfil}">
        </div>
        <div class="col-6 p-0">
            <label class="labelPerfil" id="label">E-mail:</label>
            <input type="text" class="inputPerfil" id="CorreoPerfil" value="${CorreoPerfil}">
        </div>
    </div>
    <div class="row col-12 divContainerDatosPerfil3">
        <div class="col-6 p-0">
            <label class="labelPerfil" id="label">Género:</label>
            <input type="text" class="inputPerfil"  id="GenPerfil" value="${GenPerfil}">
        </div>
        <div class="col-6 p-0">
            <label class="labelPerfil" id="label">Rh:</label>
            <input type="text" class="inputPerfil" id="RhPerfil" value="${RhPerfil}">
        </div>
    </div>

    <div class="row col-12 divContainerDatosPerfil3" style=" height: auto;width: 100%; ">
        <div class="col-6 p-0">
             <label class="labelPerfil" id="label"> Condición Médica:</label>
        <input type="text" class="inputPerfil" id="CondicionMedica" value="${CondicionMedica}">
        </div>
        <div class="col-6 p-0">
            <label class="labelPerfil" id="label">Alergias:</label>
        <input type="text" class="inputPerfil" id="AlergiasPerfil" value="${AlergiasPerfil}">
        </div>

    </div>
    <div style=" height: auto;width: 100%; background-color: #283139">
        <a style="margin:5px;font-size: 12px;">CONTACTOS DE EMERGENCIA</a>
    </div>
    <div class="row justify-content-center" style=" height: auto;width: 100%;margin:   0px;">
        <input class="col-sm-5 col-md-5  col-lg-5 col-xl-5 "type="text"  style="padding: 0px;   background: transparent;  font: 12px Arial; border: none;   color: white;   height:25px;" id="ContactoNombre" value="${ContactoNombre}"> 
        <input class="col-sm-5 col-md-5  col-lg-5 col-xl-5 " type="text"  style="padding: 0px;   background: transparent;  font: 12px Arial; border: none;   color: white;    height:25px;" id="ContactoNumero" value="${ContactoNumero}">
    </div>


</div>     


