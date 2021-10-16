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
        <link href="css/Registrar-Usuario.css" rel="stylesheet" type="text/css"/>
        <link href="css/Tablas.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/nicepage.js" defer=""></script>
        <meta name="generator" content="Nicepage 3.22.0, nicepage.com">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">




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
        <%
        if(session.getAttribute("ced_user") ==null) {
            response.sendRedirect("Login.jsp");
        }
        %>
        <section class="u-clearfix u-section-1" id="sec-4739">
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div class="u-form u-form-1">
                    <form action="Servlet_ClienteValidar" method="POST"   name="form" style="padding: 10px;">
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-9e0b" class="u-label">Cédula del cliente </label>
                            <input type="text" id="text-9e0b" placeholder="Introduzca su número de identificación" name="cedulaCliente" id="cedulaCliente" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" >
                        </div>
                        <div class="u-align-left u-form-group u-form-submit">
                            <center><input type="submit" id="btn-action"  class="u-btn u-btn-submit u-button-style" value="Validar cédula"/></center>
                        </div>
                        
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
                $.getJSON("Servlet_UbicacionCodigo", {}, function (data, textStatus, jqXHR) {
                    //preparamos el combo con la lista obtenida den data
                    $("#productCode").empty(); //limpiamos el contenido
                    $.each(data, function (index, item) {//recorremos la lista
                        $("<option/>") //creamos un objeto option...
                                .attr("value", item.description) //.. con el valor
                                .text(item.description) //.. el texto a mostrar
                                .appendTo("#productCode"); //.. y lo agregamos al combo
                    });
                    $("#productCode").change(productCodeOnChange);// asignamos el evento si cambia de valor
                });
            };
            var productCodeOnChange = function () {
                var productCode = $(this).val(); //tomamos el valor seleccionado
                console.log("productCode:", productCode);// lo mostramos en el log
                //... y llamamos a nuestro servlet
                $.getJSON("Servlet_UbicacionSeleccionada", //.. este es el servlet
                        {productCode: productCode}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            //y construimos el combo
                            $("#product").empty(); //limpiamos lo que hay
                            $.each(data, function (index, item) {
                                $("<option/>")
                                        .attr("value", item.description)
                                        .text(item.description)
                                        .appendTo("#product");

                            });
                            $("#product").change(productCodeOnChange2);
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
