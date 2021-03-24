<%-- 
    Document   : menu_cursos
    Created on : 19/03/2021, 11:56:24 AM
    Author     : XPS
--%>

<input type="button" value="Registrar Intitucion" class="btn btn-secondary col-12" onclick="insitucion()">
<input type="button" value="Registrar Sucursal" class="btn btn-secondary col-12" onclick="sucursal()">

<script>
    function insitucion(){
        $('#contenidoSection').load('registro_institucion')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    function sucursal(){
        $('#contenidoSection').load('registro_sucursal')
        //$('#contenidoSection').append('<h1>HOLA MUNDO</h1>')
    }
    
</script>