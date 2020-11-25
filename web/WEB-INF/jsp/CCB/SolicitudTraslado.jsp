<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : web
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
    <aside class="d-none">
        <div class="row col-12 m-0 p-0" id="toggle">Solicitud de Traslado</div>
        <div id="sidebar">

        </div>
    </aside>
    <section>
        <!--Modal-->
        <div id="modal_m" style="
             position: absolute;
             width: 100%;
             height: 100%;
             z-index: 1000;
             background: #40474fd6;
             display: none;
             "><div id="modal_mensaje" style="
               width: 50%;
               height: 50%;
               margin: auto;
               top: 20%;
               position: absolute;
               left: 25%;
               font: bold 1.2rem Arial;
               align-items: center;
               display: flex;
               justify-content: center;
               background: white;
               border-radius: 15px;
               color: #40474f;
               padding: 20px;
               "></div></div>

        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

            <!--Contenido-->
            <div class="col-12 titulo-instalaciones">
                <h1>Referencia COVID 19 </h1>
                <hr>
                <label>Sistema Abreviado de Referencia a Unidad Temporal COVID-19</label>
            </div>
            <div class="col-12 contenedor-formulario" id="Formulario1">
                <div class="">
                    <form  id="form1">
                        <div class="col-12 formulario">
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="fechayhora">Fecha y Hora</label>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label for="fecha">Fecha </label>
                                    <input type="text" class="form-control" id="fecha" name="fecha"  disabled>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label for="hora">Hora</label>
                                    <input type="text" class="form-control" id="hora" name="hora"  disabled>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label for="turno2">Turno</label>
                                    <input type="text" class="form-control" id="turno2" name="turno2"  required>
                                </div>
                            </div>
                            <hr>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <h4>Datos del paciente</h4>
                                </div>
                            </div>                            
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <label for="paciente">Nombre del paciente</label>  
                                    <input type="text" class="form-control" id="nombre_paciente" name="nombre_paciente" required>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <label for="paciente">Apellido Paterno del paciente</label>  
                                    <input type="text" class="form-control" id="apellidop_paciente" name="apellidop_paciente" required>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <label for="paciente">Apellido Materno del paciente</label>  
                                    <input type="text" class="form-control" id="apellidom_paciente" name="apellidom_paciente" required>
                                </div> 

                                <div class="col-12 col-md-3 col-lg-3 col-xl-4" style="display: none">
                                    <br>
                                    <label for="curp">CURP</label>  
                                    <input type="text" class="form-control" id="curp" name="curp" value="">
                                </div>

                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <br>
                                    <label for="nacimiento">Fecha de nacimiento</label>  
                                    <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" required>
                                </div>

                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <br>
                                    <label for="genero">Sexo</label>  
                                    <select class="form-control inputForm" name="genero" id="genero" required>
                                        <option value="">Seleccione su sexo</option>
                                        <option value="mujer">Femenino</option>
                                        <option value="hombre">Masculino</option>
                                    </select>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <br>
                                    <label>Nacionalidad</label>
                                    <input type="text" class="form-control" id="nacionalidad" name="nacionalidad"  value="Mexicana">
                                </div>      
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <br>
                                    <label for="pais">País de nacimiento</label>
                                    <select id="pais" class="form-control">
                                        <option value="00">No Especificado</option>
                                        <option value="ABW">Aruba</option>
                                        <option value="AFG">Afganistán</option>
                                        <option value="AGO">Angola</option>
                                        <option value="AIA">Anguila</option>
                                        <option value="ALB">Albania</option>
                                        <option value="AND">Andorra</option>
                                        <option value="ANT">Antillas Neerlandesas</option>
                                        <option value="ARE">Emiratos Árabes Unidos</option>
                                        <option value="ARG">Argentina</option>
                                        <option value="ARM">Armenia</option>
                                        <option value="ASM">Samoa Estadounidense</option>
                                        <option value="ATA">Antártida</option>
                                        <option value="ATF">Territorios Franceses del Sur</option>
                                        <option value="ATG">Antigua y Barbuda</option>
                                        <option value="AUS">Australia</option>
                                        <option value="AUT">Áustria</option>
                                        <option value="AZE">Azerbaiyán</option>
                                        <option value="BDI">Burundi</option>
                                        <option value="BEL">Bélgica</option>
                                        <option value="BEN">Benin</option>
                                        <option value="BFA">Burkina Faso</option>
                                        <option value="BGD">Bangladesh</option>
                                        <option value="BGR">Bulgaria</option>
                                        <option value="BHR">Bahrein</option>
                                        <option value="BHS">Bahamas</option>
                                        <option value="BIH">Bosnia y Herzegovina</option>
                                        <option value="BLR">Belarus</option>
                                        <option value="BLZ">Belice</option>
                                        <option value="BMU">Bermudas</option>
                                        <option value="BOL">Bolivia</option>
                                        <option value="BRA">Brasil</option>
                                        <option value="BRB">Barbados</option>
                                        <option value="BRN">Brunei Darussalam</option>
                                        <option value="BTN">Bhutan</option>
                                        <option value="BUR">Birmania</option>
                                        <option value="BVT">Isla Bouvet</option>
                                        <option value="BWA">Botswana</option>
                                        <option value="CAF">República Centroafricana</option>
                                        <option value="CAN">Canadá</option>
                                        <option value="CCK">Islas Cocos (Keeling)</option>
                                        <option value="CHE">Suiza</option>
                                        <option value="CHL">Chile</option>
                                        <option value="CHN">China</option>
                                        <option value="CIV">Costa de Marfil</option>
                                        <option value="CMR">Camerún</option>
                                        <option value="COG">Congo</option>
                                        <option value="COK">Islas Cook</option>
                                        <option value="COL">Colombia</option>
                                        <option value="COM">Comoras</option>
                                        <option value="CPV">Cabo Verde</option>
                                        <option value="CRI">Costa Rica</option>
                                        <option value="CSK">Checoslovaquia</option>
                                        <option value="CUB">Cuba</option>
                                        <option value="CXR">Isla Christmas</option>
                                        <option value="CYM">Islas Caimanes</option>
                                        <option value="CYP">Chipre</option>
                                        <option value="CZE">Checa, República</option>
                                        <option value="DEU">Alemania</option>
                                        <option value="DJI">Djibouti</option>
                                        <option value="DMA">Dominica</option>
                                        <option value="DNK">Dinamarca</option>
                                        <option value="DOM">República Dominicana</option>
                                        <option value="DZA">Argelia</option>
                                        <option value="ECU">Ecuador</option>
                                        <option value="EGY">Egipto</option>
                                        <option value="ESH">Saharaui</option>
                                        <option value="ESP">España</option>
                                        <option value="EST">Estonia</option>
                                        <option value="ETH">Etiopía</option>
                                        <option value="FIN">Finlandia</option>
                                        <option value="FJI">Fiji</option>
                                        <option value="FLK">Islas Malvinas (Falkland)</option>
                                        <option value="FRA">Francia</option>
                                        <option value="FRO">Islas Feroe</option>
                                        <option value="FSM">Micronesia, Estados Federados De</option>
                                        <option value="GAB">Gabón</option>
                                        <option value="GBD">Reino Unido(C. de Depen. Territ. Brit.)</option>
                                        <option value="GBN">Reino Unido(C. de Nal. Brit. del Ext.)</option>
                                        <option value="GBO">Reino Unido(C. Brit. del Extranjero)</option>
                                        <option value="GBP">Reino Unido(Persona Bajo Protec. Brit.)</option>
                                        <option value="GBR">Reino Unido (Ciudadano Británico)</option>
                                        <option value="GBS">Reino Unido (Subdito Británico)</option>
                                        <option value="GEO">Georgia</option>
                                        <option value="GHA">Ghana</option>
                                        <option value="GIB">Gibraltar</option>
                                        <option value="GIN">Guinea</option>
                                        <option value="GLP">Guadalupe</option>
                                        <option value="GMB">Gambia</option>
                                        <option value="GNB">Guinea-Bissau</option>
                                        <option value="GNQ">Guinea Ecuatorial</option>
                                        <option value="GRC">Grecia</option>
                                        <option value="GRD">Granada</option>
                                        <option value="GRL">Groenlandia</option>
                                        <option value="GTM">Guatemala</option>
                                        <option value="GUF">Guayana Francesa</option>
                                        <option value="GUM">Guam</option>
                                        <option value="GUY">Guyana</option>
                                        <option value="HKG">Hong Kong</option>
                                        <option value="HMD">Islas Heard y Mcdonald</option>
                                        <option value="HND">Honduras</option>
                                        <option value="HRV">Croacia</option>
                                        <option value="HTI">Haití</option>
                                        <option value="HUN">Hungría</option>
                                        <option value="IDN">Indonesia</option>
                                        <option value="IND">India</option>
                                        <option value="IOT">Territorio Británico del Océano Índico</option>
                                        <option value="IRL">Irlanda</option>
                                        <option value="IRN">Irán, República Islámica Del</option>
                                        <option value="IRQ">Iráq</option>
                                        <option value="ISL">Islandia</option>
                                        <option value="ISR">Israél</option>
                                        <option value="ITA">Italia</option>
                                        <option value="JAM">Jamaica</option>
                                        <option value="JOR">Jordania</option>
                                        <option value="JPN">Japón</option>
                                        <option value="KAZ">Kazajstan</option>
                                        <option value="KEN">Kenya</option>
                                        <option value="KGZ">Kirguistan</option>
                                        <option value="KHM">Camboya</option>
                                        <option value="KIR">Kiribati</option>
                                        <option value="KNA">Saint Kitts y Nevis</option>
                                        <option value="KOR">República de Corea</option>
                                        <option value="KWT">Kuwáit</option>
                                        <option value="LAO">República Democrática Popular Lao</option>
                                        <option value="LBN">Líbano</option>
                                        <option value="LBR">Liberia</option>
                                        <option value="LBY">Jamahiriya Árabe Libia</option>
                                        <option value="LCA">Santa Lucía</option>
                                        <option value="LIE">Liechtenstein</option>
                                        <option value="LKA">Srí Lanka</option>
                                        <option value="LSO">Lesotho</option>
                                        <option value="LTU">Lituánia</option>
                                        <option value="LUX">Luxemburgo</option>
                                        <option value="LVA">Letonia</option>
                                        <option value="MAC">Macao</option>
                                        <option value="MAR">Marruecos</option>
                                        <option value="MCO">Mónaco</option>
                                        <option value="MDA">República de Moldova</option>
                                        <option value="MDG">Madagascar</option>
                                        <option value="MDV">Maldivas</option>
                                        <option value="MEX" selected="true">México</option>
                                        <option value="MHL">Islas Marshall</option>
                                        <option value="MKD">Macedonia, República Yugoslava de</option>
                                        <option value="MLI">Malí</option>
                                        <option value="MLT">Malta</option>
                                        <option value="MMR">Myanmar</option>
                                        <option value="MNG">Mongolia</option>
                                        <option value="MNP">Islas Maríanas Septentrionales</option>
                                        <option value="MOZ">Mozambique</option>
                                        <option value="MRT">Mauritania</option>
                                        <option value="MSR">Montserrat</option>
                                        <option value="MTQ">Martinica</option>
                                        <option value="MUS">Mauricio</option>
                                        <option value="MWI">Malawi</option>
                                        <option value="MYS">Malasia</option>
                                        <option value="NAM">Namibia</option>
                                        <option value="NCL">Nueva Caledonia</option>
                                        <option value="NER">Níger</option>
                                        <option value="NFK">Isla Norfolk</option>
                                        <option value="NGA">Nigeria</option>
                                        <option value="NIC">Nicarágua</option>
                                        <option value="NIU">Niué</option>
                                        <option value="NLD">Holanda (Países Bajos, Reino Unido de los)</option>
                                        <option value="NOR">Noruega</option>
                                        <option value="NPL">Nepál</option>
                                        <option value="NRU">Naurú</option>
                                        <option value="NTZ">Zona Neutral</option>
                                        <option value="NZL">Nueva Zelandia</option>
                                        <option value="OMN">Omán</option>
                                        <option value="PAK">Pakistán</option>
                                        <option value="PAN">Panamá</option>
                                        <option value="PCN">Pitcairn</option>
                                        <option value="PER">Perú</option>
                                        <option value="PHL">Filipinas</option>
                                        <option value="PLW">Palaú</option>
                                        <option value="PNG">Papúa Nueva Guinéa</option>
                                        <option value="POL">Polónia</option>
                                        <option value="PRI">Puerto Rico</option>
                                        <option value="PRK">República Popular Democrática de Corea</option>
                                        <option value="PRT">Portugal</option>
                                        <option value="PRY">Paraguay</option>
                                        <option value="PYF">Polinesia Francesa</option>
                                        <option value="QAT">Qatar</option>
                                        <option value="REU">Reunion</option>
                                        <option value="ROM">Rumania</option>
                                        <option value="RUS">Federación de Rusia</option>
                                        <option value="RWA">Rwanda</option>
                                        <option value="SAU">Arabia Saudita</option>
                                        <option value="SDN">Sudán</option>
                                        <option value="SEN">Senegal</option>
                                        <option value="SGP">Singapur</option>
                                        <option value="SHN">Santa Elena</option>
                                        <option value="SJM">Islas Svalbard y Jan Mayen</option>
                                        <option value="SLB">Islas Salomón</option>
                                        <option value="SLE">Sierra Leóna</option>
                                        <option value="SLV">El Salvador</option>
                                        <option value="SMR">San Marino</option>
                                        <option value="SOM">Somalia</option>
                                        <option value="SPM">Saínt Pierre y Miquelón</option>
                                        <option value="STP">Santo Tome y Príncipe</option>
                                        <option value="SUR">Suriname</option>
                                        <option value="SVK">Eslovaquia, República</option>
                                        <option value="SVN">Eslovenia</option>
                                        <option value="SWE">Suecia</option>
                                        <option value="SWZ">Swazilandia</option>
                                        <option value="SYC">Seychelles</option>
                                        <option value="SYR">República Árabe Siria</option>
                                        <option value="TCA">Islas Turcas y Caicos</option>
                                        <option value="TCD">Chad</option>
                                        <option value="TGO">Togo</option>
                                        <option value="THA">Tailandia</option>
                                        <option value="TJK">Tayikistán</option>
                                        <option value="TKL">Tokeláu</option>
                                        <option value="TKM">Turkmenistan</option>
                                        <option value="TMP">Timor Oriental</option>
                                        <option value="TON">Tonga</option>
                                        <option value="TTO">Trinidad y Tobago</option>
                                        <option value="TUN">Túnez</option>
                                        <option value="TUR">Turquía</option>
                                        <option value="TUV">Tuvalú</option>
                                        <option value="TWN">Taiwán, Provincia de China</option>
                                        <option value="TZA">República Unida de Tanzania</option>
                                        <option value="UGA">Uganda</option>
                                        <option value="UKR">Ucrania</option>
                                        <option value="UMI">Islas Remotas Menores de los Estados Unidos</option>
                                        <option value="UNK">DESCONOCIDO</option>
                                        <option value="URY">Uruguay</option>
                                        <option value="USA">Estados Unidos</option>
                                        <option value="UZB">Uzbekistan</option>
                                        <option value="VAT">Estado de La Ciudad del Vaticano</option>
                                        <option value="VCT">San Vicente y las Granadinas</option>
                                        <option value="VEN">Venezuela</option>
                                        <option value="VGB">Islas Vírgenes Británicas</option>
                                        <option value="VIR">Islas Vírgenes (Estados Unidos)</option>
                                        <option value="VNM">Viet Nam</option>
                                        <option value="VUT">Vanuatu</option>
                                        <option value="WLF">Islas Wallis y Futuna</option>
                                        <option value="WSM">Samoa</option>
                                        <option value="XES">Eslovaquia</option>
                                        <option value="YEM">Yemen, República del</option>
                                        <option value="YUG">Yugoslavia</option>
                                        <option value="ZAF">Sudáfrica</option>
                                        <option value="ZAR">Zaire</option>
                                        <option value="ZMB">Zambia</option>
                                        <option value="ZWE">Zimbabwe</option>
                                    </select>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <br>
                                    <label for="estado">Estado de nacimiento</label>  
                                    <select id="estado_nacimiento" name="estado_nacimiento" class="form-control" required="true">
                                        <option value="No Especificado">	No Especificado	</option>
                                        <option value="Aguascalientes">	Aguascalientes	</option>
                                        <option value="Baja California">	Baja California	</option>
                                        <option value="Baja California Sur">	Baja California Sur	</option>
                                        <option value="Campeche">	Campeche	</option>
                                        <option value="Coahuila de Zaragoza">	Coahuila de Zaragoza	</option>
                                        <option value="Colima">	Colima	</option>
                                        <option value="Chiapas">	Chiapas	</option>
                                        <option value="Chihuahua">	Chihuahua </option>
                                        <option value="Ciudad de México" selected="true">	Ciudad de México	</option>
                                        <option value="Durango">	Durango	</option>
                                        <option value="Guanajuato">	Guanajuato	</option>
                                        <option value="Guerrero">	Guerrero	</option>
                                        <option value="Hidalgo">	Hidalgo	</option>
                                        <option value="Jalisco">	Jalisco	</option>
                                        <option value="México">	México	</option>
                                        <option value="Michoacán de Ocampo">	Michoacán de Ocampo	</option>
                                        <option value="Morelos">	Morelos	</option>
                                        <option value="Nayarit">	Nayarit	</option>
                                        <option value="Nuevo León">	Nuevo León	</option>
                                        <option value="Oaxaca">	Oaxaca	</option>
                                        <option value="Puebla">	Puebla	</option>
                                        <option value="Querétaro">	Querétaro	</option>
                                        <option value="Quintana Roo">	Quintana Roo	</option>
                                        <option value="San Luis Potosí">	San Luis Potosí	</option>
                                        <option value="Sinaloa">	Sinaloa	</option>
                                        <option value="Sonora">	Sonora	</option>
                                        <option value="Tabasco">	Tabasco	</option>
                                        <option value="Tamaulipas">	Tamaulipas	</option>
                                        <option value="Tlaxcala">	Tlaxcala	</option>
                                        <option value="Veracruz de Ignacio de la Llave">	Veracruz de Ignacio de la Llave	</option>
                                        <option value="Yucatán">	Yucatán	</option>
                                        <option value="Zacatecas">	Zacatecas	</option>
                                        <option value="ESTADOS UNIDOS DE NORTEAMERICA">	ESTADOS UNIDOS DE NORTEAMERICA	</option>
                                        <option value="OTROS PAISES DE LATINOAMERICA">	OTROS PAISES DE LATINOAMERICA	</option>
                                        <option value="OTROS PAISES">	OTROS PAISES	</option>
                                        <option value="Desconocido">	Desconocido	</option>
                                        <option value="Nacido en el Extranjero">	Nacido en el Extranjero	</option>
                                    </select>
                                </div>
                                <br>
                            </div>
                            <hr>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <h4>Datos del registro de hospitalización</h4>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-4">
                                    <label for="hospital">Hospital que refiere</label>

                                    <input type="text" class="form-control" id="institucion_refiere" name="institucion_refiere" value="" disabled="true">
                                </div>  
                                <div class="col-4">
                                    <label for="medico">Médico que refiere</label>  
                                    <input type="text" class="form-control" id="medico_refiere" name="medico_refiere" required>
                                </div>
                                <div class="col-4">
                                    <label for="telefono">Teléfono de contacto 24 horas</label>  
                                    <input type="number" class="form-control" id="telefono_contacto_refiere" name="telefono_contacto" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="correo">Correo electrónico de contacto</label>  
                                    <input type="email" class="form-control" id="correo_contacto_refiere" name="correo_contacto" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="sintomas">Fecha de inicio de síntomas</label> 
                                    <input type="date" class="form-control" id="fecha_sintomas" name="fecha_sintomas" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="ingreso">Fecha de ingreso hospitalario</label>  
                                    <input type="date" class="form-control" id="fecha_ingreso" name="fecha_ingreso" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="hora_ingreso2">Hora de ingreso hospitalario</label>  
                                    <input type="time" class="form-control" id="hora_ingreso2" name="hora_ingreso2" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="prioridad2">Prioridad</label>  
                                    <select class="form-control inputForm" name="prioridad" id="prioridad2" required>
                                        <option value="Rojo">Rojo</option>
                                        <option value="Amarillo">Amarillo</option>
                                        <option value="Verde">Verde</option>
                                        <option value="Azul">Azul</option>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="nombre_cargo_solicita2">Nombre y cargo de quien solicita</label>  
                                    <input type="text" class="form-control" id="nombre_cargo_solicita2" name="nombre_cargo_solicita2" required>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="prueba_covid">Prueba COVID por PCR</label>  
                                    <select class="form-control inputForm" name="prueba_covid" id="prueb_covid" required>

                                        <option value="negativa">Negativa</option>
                                        <option value="positiva">Positiva</option>
                                        <option value="No se realizo">No se realizo</option>
                                    </select>
                                </div>                                
                            </div>


                            <hr>
                            <!--div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <label for="ingreso">Fecha de ingreso hospitalario</label>  
                                    <input type="date" class="form-control" id="fecha_ingreso" name="fecha_ingreso" required>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                   <label for="sintomas">Fecha de inicio de síntomas</label> 
                                   <input type="date" class="form-control" id="fecha_sintomas" name="fecha_sintomas" required>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                    <label for="prueba_covid">Prueba COVID por PCR</label>  
                                        <select class="form-control inputForm" name="prueba_covid" id="prueba_covid" required>
                                            <option value="0">Seleccione..</option>
                                            <option value="negativa">Negativa</option>
                                            <option value="positiva">Positiva</option>
                                        </select>
                                </div>
                           </div-->
                            <br>

                            <div class="col-12">
                                <div class="form-row">
                                    <div class="col-3">
                                        <label for="cormobilidades">Comorbilidades asociadas (Seleccione una o varias)</label> 
                                        <br>
                                    </div>
                                    <div class="col-1">
                                        <input type="checkbox" id="diabetes_melitus" name="Diabetes Melitus"/> <label>Diabetes Mellitus</label>
                                        <br>
                                    </div>
                                    <div class="col-2">
                                        <input type="checkbox" id="hipertension" name="Hipertensión arterial sistémica"/> <label>Hipertensión arterial sistémica</label>
                                        <br>
                                    </div>
                                    <div class="col-2">
                                        <input type="checkbox" id="enfermedad_pulmonar" name="Enfermedad pulmonar"/> <label> Enfermedad pulmonar</label>
                                        <br>
                                    </div>
                                    <div class="col-2">
                                        <input type="checkbox" id="enfermedad_renal" name="Enfermedad renal crónica"/> <label>Enfermedad renal crónica</label>
                                        <br>
                                    </div>
                                    <div class="col-2">
                                        <input type="checkbox" id="imc_mayor" name="IMC >30 y menor de 40"/> <label>IMC >30 y menor de 40</label>
                                    </div>
                                    <input type="hidden" name="cormobilidades" id="cormobilidades">
                                </div>
                                <div class="form-row" style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">IMC mayor que 40  </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label for="imc" class="checkbox-inline"><input type="radio" name="13" value="Si" onchange="document.getElementById('imc').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label for="imc" class="checkbox-inline"><input type="radio" name="13" value="No" onchange="document.getElementById('imc').value = 'No'"  >No</label>
                                    </div>
                                    <input type="hidden" value="No" id="imc"  >
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="peso" class="checkbox-inline">Peso mayor a 150 kilos  </label> 
                                    </div>

                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label for="peso" class="checkbox-inline">
                                            <input type="radio" name="28" value="Si" onchange="document.getElementById('peso').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label for="peso" class="checkbox-inline">
                                            <input type="radio" name="28" value="No" onchange="document.getElementById('peso').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" value="" id="peso"  >
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="traqueostomia" class="checkbox-inline">Traqueostomía </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="14" value="Si" onchange="document.getElementById('traqueostomia').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="14" value="No" onchange="document.getElementById('traqueostomia').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="traqueostomia" id="traqueostomia" value="">                                
                                </div>


                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="ventilacion_mecanica" class="checkbox-inline">Ventilación mecánica </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="29" value="Si" onchange="document.getElementById('ventilacion_mecanica').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="29" value="No" onchange="document.getElementById('ventilacion_mecanica').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" name="ventilacion_mecanica" id="ventilacion_mecanica" value="">  
                                </div>


                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Terapia renal sustitutiva</label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="15" value="Si" onchange="document.getElementById('terapia_renal').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="15" value="No" onchange="document.getElementById('terapia_renal').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="terapia_renal" id="terapia_renal" value="" >
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Incapacidad para alimentarse Vía Oral</label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="16" value="Si" onchange="document.getElementById('incapacidad_alimentacion').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="16" value="No" onchange="document.getElementById('incapacidad_alimentacion').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="incapacidad_alimentacion" id="incapacidad_alimentacion" value="">
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Cirrosis hepática Child C </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="17" value="Si" onchange="document.getElementById('cirrosis_hepatica').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="17" value="No" onchange="document.getElementById('cirrosis_hepatica').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="cirrosis_hepatica" id="cirrosis_hepatica" value="" >
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Mujer embarazada </label> 
                                    </div>
                                    <div class="col-3 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="18" value="Si" onchange="document.getElementById('embarazo').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-3 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="18" value="No" onchange="document.getElementById('embarazo').value = 'No'">No</label>
                                    </div>
                                    <div class="col-2 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="18" value="No aplica" onchange="document.getElementById('embarazo').value = 'No aplica'"  >No aplica</label>
                                    </div>
                                    <input type="hidden" name="embarazo" id="embarazo" value="">
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Postoperado en los últimos 5 días </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="19" value="Si" onchange="document.getElementById('postoperado').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="19" value="No" onchange="document.getElementById('postoperado').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="postoperado" id="postoperado" value="" >
                                </div>
                                <div class="form-row" style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Enfermedades exantemáticas o infección nosocomial </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="20" value="Si" onchange="document.getElementById('exantematicas_nosocomial').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="20" value="No" onchange="document.getElementById('exantematicas_nosocomial').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="exantematicas_nosocomial" id="exantematicas_nosocomial" value="No" >
                                </div>

                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Amerita procedimiento quirúrgico de urgencias </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="41" value="Si" onchange="document.getElementById('procedimiento_quirurgico_urgencias').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="41" value="No" onchange="document.getElementById('procedimiento_quirurgico_urgencias').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="procedimiento_quirurgico_urgencias" id="procedimiento_quirurgico_urgencias" value="" >
                                </div>

                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Sangrado activo o en requerimientos transfusionales</label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="21" value="Si" onchange="document.getElementById('requerimiento_transfusion').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="21" value="No" onchange="document.getElementById('requerimiento_transfusion').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="requerimiento_transfusion" id="requerimiento_transfusion" value="">
                                </div>
                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Infecciones bacterianas o fúngicas no controladas </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="22" value="Si" onchange="document.getElementById('infecciones').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="22" value="No" onchange="document.getElementById('infecciones').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="infecciones" id="infecciones" value="" >
                                </div>


                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Infecciones asociadas a brotes</label> 
                                        <!--button type="button" class="btn btn-secondary" data-toggle="tooltip" data-html="true" title="<em>Tooltip</em> <u>with</u> <b>HTML</b>">
                                            Tooltip with HTML
                                        </button-->                                        
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="42" value="Si" onchange="document.getElementById('infecciones_brotes').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="42" value="No" onchange="document.getElementById('infecciones_brotes').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" name="infecciones_brotes" id="infecciones_brotes" value="" >
                                </div>

                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Infecciones por microorganismos multidrogo-resistentes</label> 
                                        <!--button type="button" class="btn btn-secondary" data-toggle="tooltip" data-html="true" title="<em>Tooltip</em> <u>with</u> <b>HTML</b>">
                                            Tooltip with HTML
                                        </button-->                                        
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="43" value="Si" onchange="document.getElementById('infecciones_microorganismos').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="43" value="No" onchange="document.getElementById('infecciones_microorganismos').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" name="infecciones_microorganismos" id="infecciones_microorganismos" value="" >
                                </div>

                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Otras infecciones no controladas</label> 
                                        <!--button type="button" class="btn btn-secondary" data-toggle="tooltip" data-html="true" title="<em>Tooltip</em> <u>with</u> <b>HTML</b>">
                                            Tooltip with HTML
                                        </button-->                                        
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="44" value="Si" onchange="document.getElementById('otras_infecciones').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="44" value="No" onchange="document.getElementById('otras_infecciones').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" name="otras_infecciones" id="otras_infecciones" value="" >
                                </div>

                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Requiere uso de vasopresores</label> 
                                        <!--button type="button" class="btn btn-secondary" data-toggle="tooltip" data-html="true" title="<em>Tooltip</em> <u>with</u> <b>HTML</b>">
                                            Tooltip with HTML
                                        </button-->                                        
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="45" value="Si" onchange="document.getElementById('paciente_vasopresores').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="45" value="No" onchange="document.getElementById('paciente_vasopresores').value = 'No'">No</label>
                                    </div>
                                    <input type="hidden" name="paciente_vasopresores" id="paciente_vasopresores" value="" >
                                </div>



                                <div class="form-row">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline"> Estado posterior a paro cardiorrespiratorio</label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="23" value="Si" onchange="document.getElementById('paro_cardiovascular').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="23" value="No" onchange="document.getElementById('paro_cardiovascular').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="paro_cardiovascular" id="paro_cardiovascular" value="">
                                </div>
                                <br>
                                <div class="form-row">
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <label for="imc" class="checkbox-inline"> 
                                            <a href="/plataforma360/resources/pdf/criterios.pdf" target="_blank" style="color: white;text-decoration: underline;font: bold 1rem Arial;">Criterios de Ingreso</a>
                                        </label> 
                                    </div>

                                </div>

                                <div class="form-row" style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Enfermedad psiquiátrica de base no controlada </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="24" value="Si" onchange="document.getElementById('enfermedad_psiquiatrica').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="24" value="No" onchange="document.getElementById('enfermedad_psiquiatrica').value = 'No'" >No</label>
                                    </div>
                                    <input type="hidden" name="enfermedad_psiquiatrica" id="enfermedad_psiquiatrica" value="No">
                                </div>
                                <div class="form-row" style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Dimero D mayor a 1,000 al ingreso </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="25" value="Si" onchange="document.getElementById('dimero').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="25" value="No" onchange="document.getElementById('dimero').value = 'No'" >No</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="25" value="No" onchange="document.getElementById('dimero').value = 'No se cuenta con laboratorio'">No se cuenta con el laboratorio</label>
                                    </div>
                                    <input type="hidden" name="dimero" id="dimero" value="No" >
                                </div>
                                <div class="form-row"  style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Ferritina mayor a 300 al ingreso </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="26" value="Si" onchange="document.getElementById('ferritina').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="26" value="No" onchange="document.getElementById('ferritina').value = 'No'" >No</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                        <label class="checkbox-inline"><input type="radio" name="26" value="No" onchange="document.getElementById('ferritina').value = 'No se cuenta con laboratorio'">No se cuenta con el laboratorio</label>
                                    </div>
                                    <input type="hidden" name="ferritina" id="ferritina" value="No">
                                </div>
                                <div class="form-row"  style="display: none;">
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-5">
                                        <label for="imc" class="checkbox-inline">Troponinas elevadas </label> 
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="27" value="Si" onchange="document.getElementById('troponinas').value = 'Si'">Si</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="27" value="No" onchange="document.getElementById('troponinas').value = 'No'" >No</label>
                                    </div>
                                    <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                        <label class="checkbox-inline"><input type="radio" name="27" value="No" onchange="document.getElementById('troponinas').value = 'No se cuenta con laboratorio'">No se cuenta con el laboratorio</label>
                                    </div>
                                    <input type="hidden" name="troponinas" id="troponinas" value="No" >
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4"></div>
                            <div class="col-4 botones">
                                <input type="submit" class="btn btn-red" id="enviar" value="Siguiente">
                            </div>
                        </div>

                </div>
                </form>
                <!--                    <input type="button" class="btn btn-primary" value="Llenado" id="siguienteDEMO">-->
            </div>




            <div class="col-12 contenedor-formulario" id="Formulario2" style="display:none;">
                <div class="col-12 titulo-instalaciones">
                    <h1>Referencia de Paciente a Unidad Temporal COVID-19 </h1>
                    <hr>
                    <label>Complete los requisitos para la referencia y recepción de su paciente.</label>
                </div>

                <form id="form2">

                    <br>

                    <h1>Resumen Clínico Completo</h1>
                    <div class="col-12 formulario">
                        <div class="form-row">
                            <div class="col-12 encabezados">
                                <h1>Ficha de identificación paciente</h1>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre">Nombre(s)</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre">Apellido Paterno</label>
                                <input type="text" class="form-control" id="apellidop_paciente2" name="apellidop_paciente" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre">Apellido Materno</label>
                                <input type="text" class="form-control" id="apellidom_paciente2" name="apellidom_paciente" disabled>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">   
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4" style="display: none;">
                                <label for="curp">CURP</label>
                                <input type="text" class="form-control" id="curp2" name="curp2" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="genero">Sexo</label>
                                <input type="text" class="form-control" id="genero2" name="genero" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nacimiento">Fecha de nacimiento</label>  
                                <input type="date" class="form-control" id="fecha_nacimiento2" name="fecha_nacimiento" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nacionalidad">Nacionalidad</label>
                                <input type="text" class="form-control" id="nacionalidad2" name="nacionalidad" disabled>
                            </div>  
                        </div>
                        <br>
                        <div class="form-row">     

                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="pais">País de nacimiento</label>
                                <input type="text" class="form-control" id="pais2" name="pais" disabled>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="estado">Estado de nacimiento</label>  
                                <input type="text" class="form-control" id="estado_nacimiento2" name="estado_nacimiento" disabled >
                            </div>

                        </div>
                        <br>
                        <div class="form-row">     
                            <div class="col-12">
                                <label for="comorbilidades">Comorbilidades </label>
                                <input type="text" class="form-control" id="comorbilidades2" name="comorbilidades" disabled>
                            </div>
                            <div class="col-12">
                                <label for="medicamentos">Medicamentos de uso regular</label>
                                <input type="text" class="form-control" id="medicamentos" name="medicamentos">
                            </div>

                        </div>
                        <br>
                        <hr>
                        <div class="form-row">
                            <div class="col-12 encabezados">
                                <h1>Datos de responsable(familiar)</h1>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre">Nombre(s)</label>
                                <input type="text" class="form-control" id="nombre_responsable" name="nombre_responsable" required>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="apellidop_responsable">Apellido Paterno</label>
                                <input type="text" class="form-control" id="apellidop_responsable" name="apellidop_responsable" required>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="apellidom_responsable">Apellido Materno</label>
                                <input type="text" class="form-control" id="apellidom_responsable" name="apellidom_responsable" required>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">                         
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="genero">Sexo</label>
                                <select class="form-control inputForm" name="genero" id="genero_responsable" required>
                                    <option value="">Seleccione su sexo</option>
                                    <option value="Femenino">Femenino</option>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Desconocido">Desconocido</option>
                                </select>

                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="vinculo">Vínculo</label>  
                                <select class="form-control inputForm" id="vinculo" name="vinculo" required>
                                    <option value="">Seleccione una opción</option>
                                    <option value="AMA">	Abuela Materna	</option>
                                    <option value="AMA">	Abuela Materna	</option>
                                    <option value="ANC">	Tío/a	</option>
                                    <option value="APA">	Abuela Paterna	</option>
                                    <option value="CHD">	Hijo / a	</option>
                                    <option value="Ciu">	Cuidador / a	</option>
                                    <option value="Con">	Concubino / a	</option>
                                    <option value="CSN">	Primo/a	</option>
                                    <option value="CUÑ">	Cuñado / a	</option>
                                    <option value="DPTE">	Dependiente	</option>
                                    <option value="EXF">	Familiar	</option>
                                    <option value="FND">	Amigo / a	</option>
                                    <option value="FTH">	Padre	</option>
                                    <option value="GFTHM">	Abuelo materno	</option>
                                    <option value="GFTHP">	Abuelo paterno	</option>
                                    <option value="HSBN">	Esposo/a	</option>
                                    <option value="LRT">	Responsable legal o tutor	</option>
                                    <option value="MTH">	Madre	</option>
                                    <option value="NFW">	Sobrino/a	</option>
                                    <option value="NON">	Ninguno	</option>
                                    <option value="NRA">	Nuera	</option>
                                    <option value="OTH">	Otros	</option>
                                    <option value="SIB">	Hermano / a	</option>
                                    <option value="SUE">	Suegro / a	</option>
                                    <option value="UDH">	Usuario/Derechohabiente	</option>
                                    <option value="YNO">	Yerno	</option>                                                                
                                </select>      
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="telefono">Teléfono de contacto</label>
                                <input type="number" class="form-control" id="telefono_responsable" name="telefono_responsable" required>
                            </div>  
                        </div>
                        <br>
                        <div class="form-row">                         
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="correo">Correo electrónico de contacto</label>
                                <input type="email" class="form-control" id="correo_contacto_responsable" name="correo_contacto" required>
                            </div>
                        </div>
                        <br>
                        <hr>    
                        <div class="form-row">
                            <div class="col-12 encabezados">
                                <h1>Padecimiento actual y evolución</h1>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="sintomas">Fecha de inicio de síntomas</label> 
                                <input type="date" class="form-control" id="fecha_sintomas2" name="fecha_sintomas" disabled="true">
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="ingreso">Fecha de ingreso hospitalario</label>  
                                <input type="date" class="form-control" id="fecha_ingreso2" name="fecha_ingreso" disabled="true">
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="hora_ingreso">Hora de ingreso hospitalario</label>  
                                <input type="text" class="form-control" id="hora_ingreso" name="hora_ingreso" disabled="true">
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="area_cama">Área y cama de hospitalización</label> 
                                <input type="text" class="form-control" id="area_cama" name="area_cama">
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="turno">Turno</label>
                                <input type="text" class="form-control" id="turno" name="turno"  disabled="true">
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre_cargo_solicita">Nombre y cargo de quien solicita</label>  
                                <input type="text" class="form-control" id="nombre_cargo_solicita" name="nombre_cargo_solicita" disabled="true">
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="prueba_covid">Prueba COVID por PCR</label>  
                                <input type="text" class="form-control" id="prueba_covid" name="prueba_covid" disabled="true">
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="prioridad">Prioridad</label> 
                                <input type="text" class="form-control" id="prioridad" name="prioridad" disabled="true">
                            </div>
                            
                        </div>
                        <br>
                        <div class="form-row">                                                          
                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                <label for="prueba_covid">Tratamiento que ha recibido hasta el momento</label>  
                                <textarea class="form-control" id="tratamiento" name="tratamiento" rows="4" required></textarea>
                            </div>
                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                <label for="prueba_covid">Evolución y estado actual</label>  
                                <textarea class="form-control" id="estado_actual" name="estado_actual" rows="4" required></textarea>
                            </div>
                            <div class="col-6 col-md-3 col-lg-3 col-xl-4">
                                <label for="prueba_covid">Motivo de envío</label>  
                                <select class="form-control inputForm" name="motivo_envio" id="motivo_envio" required>
                                    <option value="Convalecencia">Convalecencia</option>
                                    <option value="Control de síntomas">Control de síntomas</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>

                    <h1>Últimos Laboratorios</h1>
                    <div class="col-12 formulario">
                        <div class="form-row">
                            <div class="col-6">
                                <h4>Biometría hemática con diferencial</h4>
                                <br>
                            </div>
                            <div class="col-6">
                                <h4>Cantidad</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="eritrocitos">Eritrocitos </label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="eritrocitos" name="eritrocitos" min="2.0" max="10.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>millones/uL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="hemoglobina">Hemoglobina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="hemoglobina" name="hemoglobina" min="5.0" max="30.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>g/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="hematocrito">Hematocrito</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="hematocrito"  min="20.0" max="65" step="0.01" name="hemglobina"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="hemoglobina_corp" class="checkbox-inline">Hemoglobina Corp Media</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="hemoglobina_corp" name="hemoglobina_corp"  min="10.0" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>pg</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="concentracion_hemoglobina">Concentración Media Hemoglobina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="concentracion_hemoglobina" name="concentracion_hemoglobina"  min="10.0" max="70.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>g/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="distribucion_erotrocitos">Ancho distribución de Eritrocitos </label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="distribucion_erotrocitos" name="distribucion_erotrocitos"  min="7.0" max="20.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="plaquetas">Plaquetas </label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="plaquetas" name="plaquetas"  min="10.0" max="999.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>miles/uL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="leucocitos" >Leucocitos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="leucocitos" name="leucocitos"  min="2.0" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>miles/uL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="neutrofilos" >Neutrófilos</label>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="neutrofilos" name="neutrofilos"  min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="linfocitos" >Linfocitos</label>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="linfocitos" name="linfocitos"  min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="monocitos"> Monocitos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="monocitos" name="monocitos" min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="eosinofilos">Eosinófilos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id= "eosinofilos" name="eosinofilos" min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="basofilos">Basófilos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id= "basofilos" name="basofilos" min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="neutrofilos" >Neutrófilos</label>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="neutrofilos_ul" name="neutrofilos"  min="0.1" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>x103/μL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="linfocitos" >Linfocitos</label>
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="linfocitos_ul" name="linfocitos"  min="0.1" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>x103/μL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="monocitos"> Monocitos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="monocitos_ul" name="monocitos" min="0.1" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>x103/μL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="eosinofilos">Eosinófilos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id= "eosinofilos_ul" name="eosinofilos" min="0.1" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>x103/μL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="basofilos">Basófilos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id= "basofilos_ul" name="basofilos" min="0.1" max="50.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>x103/μL</label>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="col-6">
                                <h4>Química sanguínea</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="glucosa">Glucosa </label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="glucosa" name="glucosa" min="45" max="500" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="urea">Urea </label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="urea" name="urea" min="10" max="300" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="glucosa">Creatinina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="creatinina" name="creatinina" min="0.3" max="20.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="acido_urico">Ácido Úrico</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="acido_urico" name="acido_urico" min="2.0" max="10.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="colesterol">Colesterol</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="colesterol" name="colesterol" min="20" max="500" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="trigliceridos">Triglicéridos</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="trigliceridos" name="trigliceridos" min="20.0" max="3000" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="ferritina">Ferritina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="ferritina_num" name="ferritina" min="10" max="3000" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mcg/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="dimero_d">Dímero D</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="dimero_d" name="dimero_d"  min="10" max="3000" step="0.01" >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>ng/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="troponina_i">Troponina I</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="troponina_i" name="troponina_i"  min="0" max="20" step="0.01" >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>ng/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="ck_mb">CK MB</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="ck_mb" name="ck_mb" min="10" max="500" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="ck">CK</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="ck" name="ck" min="10" max="500" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="dhl">DHL</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="dhl" name="dhl" min="10" max="3000" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="ck_mb">BNP</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="bnp" name="bnp" min="10" max="3000" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>pg/mL</label>
                            </div>
                        </div>
                        <br>
                        <hr>
                        <div class="form-row">
                            <div class="col-6">
                                <h4>Electrolitos séricos</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="sodio">Sodio</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="sodio" name="sodio" min="110" max="170" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="potasio">Potasio</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="potasio" name="potasio" min="2.5" max="7" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="cloro">Cloro</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="cloro" name="cloro" min="70" max="130" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="calcio">Calcio total</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="calcio" name="calcio" min="6.5" max="14" step="0.01" >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="magnesio">Magnesio</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="magnesio" name="magnesio" min="1.0" max="4.9" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="fosforo">Fósforo</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="fosforo" name="fosforo" min="1.1" max="5"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <hr>
                        <div class="form-row">
                            <div class="col-6">
                                <h4>Pruebas de función hepática</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="bilirrubina_total">Bilirrubina total</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="bilirrubina_total" name="bilirrubina_total" min="0.0" max="15.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="bilirrubina_directa">Bilirrubina directa</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="bilirrubina_directa" name="bilirrubina_directa" min="0.0" max="15.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="bilirrubina_indirecta">Bilirrubina indirecta</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="bilirrubina_indirecta" name="bilirrubina_indirecta"  min="0.0" max="15.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mg/dL</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tgo">TGO</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tgo" name="tgo"  min="10.0" max="5000.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tgp">TGP</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tgp" name="tgp" min="10.0" max="5000.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="fosfatasa_alcalina">Fosfatasa Alcalina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="fosfatasa_alcalina" name="fosfatasa_alcalina" min="10.0" max="5000.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>UI/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="albumina">Albúmina</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="albumina" name="albumina" min="1.0" max="10.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>gd/L</label>
                            </div>
                        </div>
                        <br>

                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="globulinas">Globulinas</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="globulinas" name="globulinas" min="1.0" max="30.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>gd/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="relacion_ag">Relación A/G</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="relacion_ag" name="relacion_ag" min="0.0" max="5.0" step="0.01"  >
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="proteinas_totales">Proteínas Totales</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="proteinas_totales" name="proteinas_totales" min="1.0" max="30.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>gd/L</label>
                            </div>
                        </div>
                        <br>
                        <hr>
                        <div class="form-row">
                            <div class="col-6">
                                <h4>Tiempos de coagulación</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tp">TP</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tp" name="tp" min="0" max="30.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>seg</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tp">TPT</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tpt" name="tpt" min="0" max="100.0" step="0.01"   >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>seg</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tt">TT</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tt" name="tt" min="0" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>seg</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="inr">INR</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="inr" name="inr" min="0" max="5.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>sin unidades</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="tiempo_sangrado">Tiempo sangrado IVI</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="tiempo_sangrado" name="tiempo_sangrado" min="0" max="30.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>min</label>
                            </div>
                        </div>
                        <br>
                        <hr>
                        <div class="form-row">
                            <div class="col-6">
                                <h4> Gasometría arterial</h4>
                                <br>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="ph">pH</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="ph" name="ph" min="6.9" max="7.7" step="0.01"  >
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="pao2">PaO2</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="pao2" name="pao2" min="40" max="100" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mmHg</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="pco2">pCO2</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="pco2" name="pco2" min="20" max="75" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mmHg</label>
                            </div>
                        </div>
                        <br>   
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="sato2">SatO2</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="sato2" name="sato2" min="40" max="100" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="eb">EB</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="eb" name="eb"  >
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="hco3">HCO3</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="hco3" name="hco3" min="10" max="40.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mEq/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="lactato">Lactato</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="lactato" min="0.0" max="10.0" step="0.01" name="lactato"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>mmol/L</label>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                <label for="fio2">FiO2</label> 
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <input type="number" class="form-control" id="fio2" name="fio2" min="21" max="100.0" step="0.01"  >
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                <label>%</label>
                            </div>
                        </div>
                        <hr>
                        <div class="form-row">
                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                <label>Descripción u observaciones:</label> 
                            </div>
                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                <textarea type="text" class="form-control" id="observaciones" name="observaciones" rows="3" ></textarea>
                            </div>
                        </div>
                        <hr>
                        <div class="form">
                            <div class="col-12">
                                <h4>Estudios de imagen y descripción</h4> 
                            </div>
                            <div class="col-12 p-0">
                                <textarea class="form-control" id="estudios_imagen" ></textarea>
                            </div>

                        </div>
                    </div>

                    <br>

                    <h1>Datos de contacto</h1>
                    <div class="col-12 formulario">
                        <div class="form-row">
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="nombre">Nombre</label>
                                <input type="text" class="form-control" id="nombre_institucion" name="nombre" disabled="true">
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label for="telefono">Teléfono</label>
                                <input type="number" class="form-control" id="telefono_institucion" name="telefono" disabled="true">
                            </div>
                            <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                <label>Correo</label>
                                <input type="email" class="form-control" id="correo_institucion" name="correo" disabled="true">
                            </div>
                        </div>
                        <br>
                    </div>
                    <br>


                    <div class="form-row contenedor-formulario" style="display: none">
                        <div class="col-12">
                            <br>
                            <h4> En un plazo no mayor a 24 horas recibirá una respuesta a su petición por parte del equipo de la Unidad Temporal COVID-19.
                                <br>Agradecemos de antemano su tiempo y cooperación para agilizar este proceso.</h4>
                        </div>
                    </div>

                    <div class="form-row contenedor-formulario">
                        <div class="col-4"></div>
                        <div class="col-4 botones">
                            <input type="submit" class="btn btn-red" value="Enviar">
                        </div>
                    </div>
                </form>
                <!--                <input type="button" class="btn btn-secondary" value="Enviar" id="enviarDEMO">-->
            </div>
        </div>




    </div>
</section>

<%@include file="../plantilla/footer.jsp" %>
<%@include file="../plantilla/callhead.jsp" %>

<link href="${pathRecursos}/css/solicitudtraslado.css" rel="stylesheet" />
<script src="${pathRecursos}/js/ccb/solicitudtraslado.js"></script>





</body>