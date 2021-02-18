
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-3 h-100 archivo" id="base_modulo_${id}">
    
    <div class="cabecera-archivos w-100">
        <h1 class="titulo w-100">Nube de archivos</h1>
    </div>
    
    <div class="content-archivos w-100">
        <div class="lista-izquierda-archivos w-100">
            <div class="box">
                <ul class="directory-list">
                    <li>assets
                        <ul>
                            <li>css
                                <ul>
                                    <li>typography.css</li>
                                    <li>layout.css</li>
                                    <li>modules.css</li>
                                    <li>states.css</li>
                                    <li>theme.css</li>
                                </ul>
                            </li>
                            <li>js
                                <ul>
                                    <li>custom.js</li>
                                    <li>jquery.js</li>
                                </ul>
                            </li>
                            <li>images
                                <ul>
                                    <li>logo.svg</li>
                                    <li>arrow-sprite.svg</li>
                                    <li>social-sprite.svg</li>
                                </ul>
                            </li>
                            <li>functions.php</li>
                        </ul>
                    </li>
                    <li>templates
                        <ul>
                            <li>pages
                                <ul>
                                    <li>about.tpl</li>
                                    <li>pricing.tpl</li>
                                    <li>contact.tpl</li>
                                    <li>home.tpl</li>
                                    <li>features.tpl</li>
                                </ul>
                            </li>
                            <li>header.tpl</li>
                            <li>menu.tpl</li>
                            <li>footer.tpl</li>
                        </ul>
                    </li>
                    <li>index.php</li>
                    <li>style.css</li>
                </ul>
            </div>
        </div>
        <div class="detalle-archivos">
            <p>Detalle</p>
        </div>
    <div>
    
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

<spring:url value="${pathRecursos}/empresas360/archivo/archivo.css" var="modulo_archivoCSS" />
<spring:url value="${pathRecursos}/empresas360/archivo/archivo.js" var="modulo_archivoJS" />
<link href="${modulo_archivoCSS}" rel="stylesheet"/>
<script src="${modulo_archivoJS}" ></script>
<script>
//    init_archivo("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_archivo(${json});
</script>