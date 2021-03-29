<%-- 
    Document   : menu_cursos
    Created on : 19/03/2021, 11:56:24 AM
    Author     : XPS
--%>

<input type="button" value="Registrar Intitucion" class="btn btn-secondary col-12" onclick="insitucion()">
<input type="button" value="Registrar Sucursal" class="btn btn-secondary col-12" onclick="sucursal()">
<input type="button" value="Registrar Materia" class="btn btn-secondary col-12" onclick="materia()">
<input type="button" value="Registrar Grupo" class="btn btn-secondary col-12" onclick="grupo()">
<input type="button" value="Inscribir Alumno" class="btn btn-secondary col-12" onclick="alumno()">




<script>
    function insitucion(){
        $('#contenidoSection').load('registro_institucion')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function sucursal(){
        $('#contenidoSection').load('registro_sucursal')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function grupo(){
        $('#contenidoSection').load('registro_grupo')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function materia(){
        $('#contenidoSection').load('registro_materia')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function alumno(){
        $('#contenidoSection').load('registro_alumno')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    
</script>