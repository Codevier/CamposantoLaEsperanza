<%-- 
    Document   : Login
    Created on : 16-ago-2021, 17:08:02
    Author     : Usuario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="page_type" content="np-template-header-footer-from-plugin">
        <title>Login</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
        <link href="css/Contratos.css" rel="stylesheet" type="text/css"/>
        <link href="css/Tablas.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/nicepage.js" defer=""></script>
        <meta name="generator" content="Nicepage 3.22.0, nicepage.com">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        

        <script type="application/ld+json">{
            "@context": "http://schema.org",
            "@type": "Organization",
            "name": "",
            "logo": "images/Diseosinttulo6.png",
            "sameAs": []
            }</script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="Login">
        <meta property="og:type" content="website">

    </head>
    <body class="u-body">
        <jsp:include page="header/header.html"></jsp:include>                             
        <section class="u-clearfix u-section-1" id="sec-88c7">
           
                <h3 class="u-custom-font u-font-playfair-display u-text u-text-default u-text-1">Datos del contrato</h3>
                <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div class="u-form u-form-1">
                    <form action="#" method="POST" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" source="custom" name="form" style="padding: 10px;">
                        <div class="u-form-group u-form-group-1">
                            <label for="text-3809" class="u-label">Cédula del Cliente:</label>
                            <input type="text" placeholder="Número de identificación" id="text-3809" disabled value="${cedula}" name="text" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" maxlength="10">
                        </div>
                        <div class="u-form-group u-form-group-2">
                            <label for="text-c714" class="u-label">Nombre del Cliente:</label>
                            <input type="text" placeholder="Nombres Completos" id="text-c714" disabled value="${nombre}" name="text-1" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Nombre del Vendedor:</label>
                            <input type="text" placeholder="" id="text-ae35" name="text-10" disabled value="Xavier" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label for="text-3a01" class="u-label">Tipo de contrato</label>
                            <select required class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" id="tipo_contrato" name="tipo_contrato" >
                            </select>
                        </div>  
                        
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Valor de la propiedad</label>
                            <input type="text" placeholder="" id="valor_propiedad" name="valor_propiedad" disabled  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Saldo:</label>
                            <input type="text" placeholder="" id="saldo" name="saldo" disabled  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-9">
                            <label for="text-7fc3" class="u-label">Cantidad de meses:</label>
                            <input type="text" placeholder="" id="cantidad_meses" disabled  name="cantidad_meses" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-11">
                            <label for="text-49fa" class="u-label">Valor por mes:</label>
                            <input type="text" placeholder="" id="valor_por_mes" disabled  name="valor_por_mes" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Tipo de contrato:</label>
                            <input type="text" placeholder="" id="tipo_contrat" name="tipo_contrat" disabled value="${tipo}" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Bloque Nº:</label>
                            <input type="text" placeholder="" id="bloque" name="text-7" disabled value="${bloque}" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-9">
                            <label for="text-7fc3" class="u-label">Puerta Nº:</label>
                            <input type="text" placeholder="" id="puerta" disabled value="${puerta}" name="puerta" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-11">
                            <label for="text-49fa" class="u-label">Cara:</label>
                            <input type="text" placeholder="" id="cara" disabled value="${cara}" name="cara" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div> 
                        <div class="u-align-left u-form-group u-form-submit">
                            <a href="#" class="u-btn u-btn-submit u-button-style">Enviar</a>
                            <input type="submit" value="submit" class="u-form-control-hidden">
                        </div>
                        <div class="u-form-send-message u-form-send-success"> Gracias! Tu mensaje ha sido enviado. </div>
                        <div class="u-form-send-error u-form-send-message"> No se puede enviar su mensaje. Por favor, corrija los errores y vuelva a intentarlo. </div>
                        <input type="hidden" value="" name="recaptchaResponse">
                    </form>
                        
                </div>
            </div>
                                
        </section>



        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-c103"><div class="u-clearfix u-sheet u-sheet-1">
                <p class="u-small-text u-text u-text-variant u-text-1">Km. 7½ Vía Valencia<br><b>Atención al cliente y ventas</b>
                    <br>052754912 / 0969202533 / 0996819223<br> &nbsp;
                </p>
            </div></footer>
        <section class="u-backlink u-clearfix u-grey-80">
            <a class="u-link" href="https://nicepage.com/css-templates" target="_blank">
                <span>CSS Templates</span>
            </a>
            <p class="u-text">
                <span>created with</span>
            </p>
            <a class="u-link" href="https://nicepage.com/wysiwyg-html-editor" target="_blank">
                <span>Visual HTML Editor</span>
            </a>. 
        </section>
                                
        <script>
            /**
             * Inicializador de la página
             * @returns {undefined}
             */
            var init = function () {
                //Obtenemos la lista json de ProductCode
                $.getJSON("Servlet_Combobox_tipoContrato", {}, function (data, textStatus, jqXHR) {
                    //preparamos el combo con la lista obtenida den data
                    $("#tipo_contrato").empty(); //limpiamos el contenido
                    $.each(data, function (index, item) {//recorremos la lista
                        $("<option/>") //creamos un objeto option...
                                .attr("value", item.prodCode) //.. con el valor
                                .text(item.description) //.. el texto a mostrar
                                .appendTo("#tipo_contrato"); //.. y lo agregamos al combo
                    });
                    //$("#tipo_contrato").change(productCodeOnChange);// asignamos el evento si cambia de valor
                });
            };
            var productCodeOnChange = function () {
                var productCode = $(this).val(); //tomamos el valor seleccionado
                console.log("productCode:", productCode);// lo mostramos en el log
                //... y llamamos a nuestro servlet
                $.getJSON("Servlet_UbicacionSeleccionada", //.. este es el servlet
                        {productCode: productCode}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            document.getElementById("valueInput").innerHTML =data.;
                            //y construimos el combo
                            $("#product").empty(); //limpiamos lo que hay
                            $.each(data, function (index, item) {
                                document.getElementById("valor_propiedad").innerHTML =item.valorBoveda;
                                //$("<option/>")
                                  //      .attr("value", item.description)
                                    //    .text(item.description)
                                      //  .appendTo("#product");

                            });
                            //$("#product").change(productCodeOnChange2);
                        });
            };
            var productCodeOnChange2 = function () {
                var canton = $(this).val(); //tomamos el valor seleccionado
                var provincia = document.getElementById("productCode").value
                console.log("canton", canton);
                console.log("provincia", provincia);// lo mostramos en el log
                //... y llamamos a nuestro servlet
                $.getJSON("Servlet_UbicacionCanton", //.. este es el servlet
                        {canton: canton, provincia: provincia}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            //y construimos el combo
                            $("#parroquia").empty(); //limpiamos lo que hay
                            $.each(data, function (index, item) {
                                $("<option/>")
                                        .attr("value", item.description)
                                        .text(item.description)
                                        .appendTo("#parroquia");

                            });
                        });
            };
            $(document).ready(init);
            
        </script> 
                                
    </body>
                                
</html>
