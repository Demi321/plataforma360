<%-- 
    Document   : menu_profesor
    Created on : 23/03/2021, 01:39:29 PM
    Author     : XPS
--%>

<input type="button" value="Perfil" class="btn btn-secondary col-12" onclick="perfil()">
<input type="button" value="Horario" class="btn btn-secondary col-12" onclick="horario()">
<input type="button" value="Tareas" class="btn btn-secondary col-12" onclick="tareas()">
<input type="button" value="Evaluacion" class="btn btn-secondary col-12" onclick="evaluacion()">
<input type="button" value="Calificación" class="btn btn-secondary col-12" onclick="calificacion()">

<script>
    function perfil(){
        $('#contenidoSection').load('perfil_profesor')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function horario(){
        $('#contenidoSection').load('horario_profesor')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function tareas(){
        $('#contenidoSection').load('tareas_profesor')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function evaluacion(){
        $('#contenidoSection').load('evaluacion_profesor')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function calificacion(){
        $('#contenidoSection').load('calificacion_profesor')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
</script>