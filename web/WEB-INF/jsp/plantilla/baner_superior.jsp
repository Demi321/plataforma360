<style>
    .modal_superior{
        height: 100px;
        background: #187560;
        z-index: 100;
        box-shadow: -1px 2px 11px 0px black;
        overflow: hidden;
        transition: 0.7s;
    }
    .close_modal_superior{
        height: 30px;
        width: 30px;
        position: absolute;
        right: 5px;
        top: 5px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 25px;
        cursor: pointer;
        background: #9292922e;
        z-index: 10000;
        border-radius: 5px;
        color: white;
        border: solid 1px;
    }
    .modificador_modal_superior_header{
        transform: translate(0px, 100px);
    }
    .modificador_modal_superior_body{
        height: calc( 100% - 90px - 100px);
        transform: translate(0px, 100px);
    }
    .modificador_modal_superior_menu{
        top: calc(0px + 100px) !important;
    }
    .modificador_modal_superior_menu_modal{
        top: calc(60px + 100px) !important;
        height: calc( 100% - 90px - 100px)!important;
    }
    .logo_modal_superior{
        background-position: center;
        background-size: 55%;
        background-repeat: no-repeat;
    }


    /* Smartphones (landscape) ----------- */
    @media 
    only screen 
    and (min-width : 321px) {
        .logo_modal_superior{
            background-position: center;
            background-size: contain;
            background-repeat: no-repeat;
        }
    }

    @media 
    only screen 
    and (min-width : 300px) 
    and (orientation : portrait) {
        .logo_modal_superior{
            background-position: center;
            background-size: contain;
            background-repeat: no-repeat;
        }
    }


    @media 
    only screen 
    and (max-width : 768px) {
        .logo_modal_superior{
            background-position: center;
            background-size: contain;
            background-repeat: no-repeat;
        }
    }
    @media 
    only screen 
    and (min-width : 768px)  {
        .logo_modal_superior{
            background-position: center;
            background-size: 85%;
            background-repeat: no-repeat;
        }
    }
    @media 
    only screen 
    and (max-width : 768px) 
    and (orientation : landscape) {
        .logo_modal_superior{
            background-position: center;
            background-size: 75%;
            background-repeat: no-repeat;
        }

    }

    /* iPads (portrait and landscape) ----------- */
    @media 
    only screen 
    and (min-device-width : 768px) 
    and (max-device-width : 1024px) {
        .logo_modal_superior{
            background-position: center;
            background-size: 65%;
            background-repeat: no-repeat;
        }

    }

    /* Desktops and laptops ----------- */
    @media 
    only screen  
    and (min-width : 1224px) {
        .logo_modal_superior{
            background-position: center;
            background-size: 50%;
            background-repeat: no-repeat;
        }
    }

    /* ----------- iPad Pro ----------- */
    /* Portrait and Landscape */
    @media only screen 
    and (min-width: 1024px) 
    and (max-height: 1366px) 
    and (-webkit-min-device-pixel-ratio: 1.5) {
        .logo_modal_superior{
            background-position: center;
            background-size: 55%;
            background-repeat: no-repeat;
        }
    }
</style>
<div class="row col-12 m-0 p-0 modal_superior" id="modal_superior">
    <div class="close_modal_superior" id="close_modal_superior">
        <i class="fas fa-times"></i>    
    </div>
    <div class="col-3 logo_modal_superior" id="logo_superior_2"></div>
    <div class="col-3 logo_modal_superior d-none" id="logo_superior_1"></div>
    <div class="col-3 logo_modal_superior" id="logo_superior_3"></div>
    <div class="col-3 logo_modal_superior" id="logo_superior_3"></div>
    <div class="col-3 logo_modal_superior" id="logo_superior_4"></div>
</div>
<script>
    document.getElementById("logo_superior_1").style.backgroundImage = "url(https://recursos360.ml/911/Img/Logos/cdmx.jpeg)";
    document.getElementById("logo_superior_2").style.backgroundImage = "url(https://recursos360.ml/911/Img/Logos/fundacion.jpeg)";
    document.getElementById("logo_superior_4").style.backgroundImage = "url(https://recursos360.ml/911/Img/Logos/covid360.jpeg)";
    $(document).ready(function () {
        $("header").addClass("modificador_modal_superior_header");
        $(".menuModalIcon").addClass("modificador_modal_superior_menu");
        $("aside").addClass("modificador_modal_superior_body");
        $("section").addClass("modificador_modal_superior_body");
        $("#menu").addClass("modificador_modal_superior_menu_modal");
        $("#close_modal_superior").click(function () {
            console.log("close modal superior");
            $("header").removeClass("modificador_modal_superior_header");
            $(".menuModalIcon").removeClass("modificador_modal_superior_menu");
            $("#menu").removeClass("modificador_modal_superior_menu_modal");
            $("aside").removeClass("modificador_modal_superior_body");
            $("section").removeClass("modificador_modal_superior_body");
            document.getElementById("modal_superior").style = "height:0;opacity: 0.5;";
        });

    });
</script>

