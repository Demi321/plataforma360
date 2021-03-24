<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 miperfil text-dark" id="base_modulo_${id}">

    <div class="row col-12 m-0 p-2 pt-3">
        <div class="col-12 col-sm-12 col-md-4 col-lg-3 p-0 d-flex align-items-center justify-content-center" style="
             min-height: 300px;
             ">
            <div class="img_perfil" id="img"></div>
        </div>
        <div class="col-12 col-sm-12 col-md-8 p-0">
            <h4>Mi Perfil</h4>
            <h5 class="nombre_completo">-</h5>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Instituto</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_empresa" placeholder="Empresa" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Sucursal:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_sucursal" placeholder="Sucursal" /></div>
            </div>
           
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Puesto:</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="puesto" placeholder="Puesto" /></div>
            </div>
            <div class="form-group row m-0 p-2">
                <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Número de estudiante</label>
                <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="num_empleado" placeholder="Número de estudiante" /></div>
            </div>

            <div class="form-group row m-0 p-2">
                <label class="col-12 col-md-12 col-lg-2 col-form-label d-flex justify-content-start align-items-center">Datos personales</label>
                <label class="col-12 col-md-4 col-lg-2 col-form-label d-flex justify-content-center align-items-center">Fecha de nacimiento:</label>
                <div class="col-12 col-md-8 col-lg-3"><input type="date" class="form-control-plaintext input" id="fecha_nacimiento" placeholder="Seleccione una fecha" /></div>
                <label class="col-12 col-md-4 col-lg-2 col-form-label d-flex justify-content-center align-items-center">Sexo</label>
                <div class="col-12 col-md-8 col-lg-3">
                    <select class="form-control" name="genero" id="genero" placeholder="Seleccione un valor" required="">
                        <option disabled="true" selected="true" value="">Seleccione un valor</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Femenino">Femenino</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="col-12 col-sm-12" style="
             min-height: 30px;
             ">
        </div>
    </div>

</div>
<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresa_empleado/perfil/perfil.css" var="modulo_perfilCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/perfil/perfil.js" var="modulo_perfilJS" />
<link href="${modulo_perfilCSS}" rel="stylesheet"/>
<script src="${modulo_perfilJS}" ></script>
<script>
//    init_perfil("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_perfil(${json});
</script>