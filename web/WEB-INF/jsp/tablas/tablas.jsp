<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : Moises Juárez
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
    <%--******** XLSX *******--%>
    <!--<spring:url value="/resources/frameworks/xlsx/xlsx.min.js" var="xlsxJS" />-->
    <spring:url value="/resources/frameworks/xlsx/xlsx.full.min.js" var="xlsxJS" />
    <script src="${xlsxJS}" ></script>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div>
                <input id="file_excel" type="file" placeholder="Selecciona un archivo">
            </div>

            <!-- 1 -->

            <div>
                <h1>table -> book</h1>
                <div class="preview">
                    <div>
                        <table id="table1">
                            <tr>
                                <td>1</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>4</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <button type="button" onclick="exportTable1()">export table</button>
            </div>
            <!-- 2 -->
            <div>
                <h1>aoa -> table -> book</h1>
                <div class="preview">
                    <div class="table-a"></div>
                </div>
                <button type="button" onclick="exportTable2()">export table</button>
            </div>
            <!-- 3 -->
            <div>
                <h1>aoa -> sheet1 + sheet2 -> book</h1>
                <div class="preview">
                    <div class="table-b"></div>
                    <div class="table-c"></div>
                </div>
                <button type="button" onclick="exportTable3()">export table</button>
            </div>
            <!-- 3 -->
            <div class="col-12 m-0">
                <h1>documento cargado</h1>
                <div class="preview">
                    <div class="table-d"></div>
                </div>
                <!--<button type="button" onclick="exportTable3()">export table</button>-->
            </div>
            <!-- 3 -->
            <div class="col-12 m-0">
                <h1>Resultado</h1>
                <div class="preview">
                    <div class="table-e"></div>
                </div>
                <button type="button" onclick="exportTable_resultado()">exportar tabla</button>
            </div>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead_sinlogin.jsp" %>
    <style>

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }
        td {
            border: 1px #000 solid;
            padding: 5px 10px;
        }
        section {
            padding: 20px 20px 0;
            font-family: consolas;
        }
        button {
            font-family: inherit;
            padding: 5px 10px;
        }
        .preview {
            padding: 10px 0;
            display: flex;
        }
        .preview>div {
            padding-right: 10px;
        }
    </style>
    <script>
        var info = new Array();
        let header = new Array();
        let datos = new Array();
        let matriz = new Array();
        const aoa1 = [
            ['a', 'b', 'c', 'd'],
            [1, 2, 3, 4],
            [5, 6, 7, 8]
        ];
        const aoa2 = [
            ['x', 'y', 'z'],
            [1, 2, 3],
            [4, 5, 6]
        ];

        const worksheet1 = XLSX.utils.aoa_to_sheet(aoa1);
        const worksheet2 = XLSX.utils.aoa_to_sheet(aoa2);
        var worksheet4;
        console.log(worksheet1);

        document.querySelector('.table-a').innerHTML = XLSX.utils.sheet_to_html(worksheet1, {id: 'table2', header: 'sheet1'});
        document.querySelector('.table-b').innerHTML = XLSX.utils.sheet_to_html(worksheet1, {id: 'table2', header: 'sheet1'});
        document.querySelector('.table-c').innerHTML = XLSX.utils.sheet_to_html(worksheet2, {id: 'table2', header: 'sheet2'});

        function exportTable1() {
            const table = document.querySelector('#table1');
            const workbook = XLSX.utils.table_to_book(table, {sheet: 'test'});
            return XLSX.writeFile(workbook, 'table_to_book.xlsx');
        }

        function exportTable2() {
            const table = document.querySelector('#table2');
            const workbook = XLSX.utils.table_to_book(table, {sheet: 'test'});
            return XLSX.writeFile(workbook, 'aoa_to_table_to_book.xlsx');
        }

        function exportTable3() {
            const workbook = {
                SheetNames: ['test1', 'test2'],
                Sheets: {
                    test1: worksheet1,
                    test2: worksheet2
                }
            };
            return XLSX.writeFile(workbook, 'aoa_to_sheet1_and_sheet2_to_book.xlsx');
        }
        function exportTable_resultado() {
            const workbook = {
                SheetNames: ['Resultado'],
                Sheets: {
                    Resultado: worksheet4
                }
            };
            return XLSX.writeFile(workbook, 'datos.xlsx');
        }
        function fileReader(oEvent) {
            console.log("En la funcion fileReader");
            var oFile = oEvent.target.files[0];
            var sFilename = oFile.name;

            var reader = new FileReader();
            var result = {};
            if (sFilename.toString().includes(".csv") || sFilename.toString().includes(".xlsx")) {
                reader.onload = function (e) {
                    var data = e.target.result;
                    console.log(data);
                    data = new Uint8Array(data);
                    var workbook = XLSX.read(data, {type: 'array', cellDates: true});
//            console.log(workbook);
                    var result = {};
                    workbook.SheetNames.forEach(function (sheetName) {
                        var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {raw: true});
                        if (roa.length)
                            result[sheetName] = roa;
                    });
                    // see the result, caution: it works after reader event is done.

                    const worksheet3 = workbook.Sheets;

                    console.log(worksheet3);
                    let keys = Object.keys(worksheet3);
                    console.log(keys);
                    for (var i = 0; i < keys.length; i++) {
                        document.querySelector('.table-d').innerHTML += XLSX.utils.sheet_to_html(worksheet3[keys[i]], {id: 'table1', header: keys[i]+" - "+sFilename.toString().split(".")[0]});
                    }

                    console.log(sFilename);
                    //console.log(result.length);
                    header = new Array();
                    datos = new Array();
                    header.push('');
                    datos.push(sFilename.toString().split(".")[0])
                    let keys_result = Object.keys(result);

                    for (var i = 0; i < keys_result.length; i++) {
                        let key = keys_result[i]
                        console.log(key);
                        console.log(result[key].length);
                        for (var j = 0; j < result[key].length; j++) {

                            header.push(result[key][j].concepto);
                            datos.push(result[key][j].cantidad);
                        }
                    }
                    console.log(header);
                    console.log(datos);

                    if (matriz.length === 0) {
                        matriz.push(header);
                    }
                    matriz.push(datos);

                    worksheet4 = XLSX.utils.aoa_to_sheet(matriz);
                    console.log(worksheet4);

                    document.querySelector('.table-e').innerHTML = XLSX.utils.sheet_to_html(worksheet4, {id: 'table2', header: 'Resultado'});



                    console.log(result);
                    if (validar_info(result)) {
                        let keys_archivo = Object.keys(result);
                        let info_completa = new Array();
                        $.each(keys_archivo, function (i) {
                            let info_hoja = result[keys_archivo[i]];
                            let info_completa_hoja = new Array();
                            $.each(info_hoja, function (j) {
                                let alias = Object.keys(info_hoja[j]);
                                let keys_hoja = transforma_arreglo(Object.keys(info_hoja[j]));
                                let json = {};
                                $.each(alias, function (k) {
                                    if (info_hoja[j][alias[k]].toString().includes("(hora ")) {
                                        let fecha = formato_fecha(info_hoja[j][alias[k]].toString());
                                        json[keys_hoja[k]] = fecha;
                                    } else {
                                        json[keys_hoja[k]] = info_hoja[j][alias[k]];
                                    }
                                });
                                json.alias = alias;
                                json.tipo_usuario = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_usuario;
                                json.tipo_servicio = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio;
                                json.id360 = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).id_usuario;
                                info_completa_hoja.push(json);
                            });
                            info_completa.push(info_completa_hoja);
                        });
                        RequestPOST("/API/escuela360/registro_personal_array", info_completa).then(function (response) {
                            console.log(response);
                        });



                    } else {
                        Swal.fire({
                            title: 'Archivo incompleto',
                            text: "EL archivo debe contener la informacion mínima: Nombre, Apellido paterno, Apellido materno y Correo."
                        });
                    }
                };
                reader.readAsArrayBuffer(oFile);
            } else {
                Swal.fire({
                    title: 'Extención Inválida',
                    text: "El archivo debe de ser un csv ó xlsx."
                });
            }
        }

        $("#file_excel").change(function (e) {
            fileReader(e);
        });
    </script>

</body>