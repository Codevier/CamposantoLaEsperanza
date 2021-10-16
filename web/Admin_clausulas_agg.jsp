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
        <title>Agregar clausulas</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
        <link href="css/Normas.css" rel="stylesheet" type="text/css"/>
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
        <script language="JavaScript">
        if(history.forward(1)){
        history.replace(history.forward(1));
        } //Esto para cuando le pulse al botón
        
        </script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="Login">
        <meta property="og:type" content="website">

    </head>
    <body class="u-body">
        <%
        if(session.getAttribute("ced_user") ==null) {
            response.sendRedirect("Login.jsp");
        }
        %>
        <jsp:include page="header/header.html"></jsp:include>   
                                
        <section class="u-clearfix u-section-1" id="sec-3919">
                                    
            <div class="u-clearfix u-sheet u-sheet-1">
                <div class="u-container-style u-group u-palette-3-light-3 u-group-1">
                    <div class="u-container-layout u-container-layout-1">
                        <h3 class="u-custom-font u-font-playfair-display u-text u-text-default u-text-1">Agregar Clausulas</h3>
                        <div class="u-align-center u-form u-form-1">
                            <form action="#" method="POST" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" source="custom" name="form" style="padding: 10px;">
                                <div class="u-form-date u-form-group u-form-group-1">
                                    <label for="date-c84b" class="u-form-control-hidden u-label"></label>
                                    <input type="date" placeholder="MM/DD/YYYY" id="date-c84b" name="date" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="">
                                </div>
                                <div class="u-form-group u-form-group-2">
                                    <label for="text-01b1" class="u-label">Nº de Clausula:</label>
                                    <input type="text" id="text-01b1" name="text" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                                </div>
                                <div class="u-form-group u-form-message">
                                    <label for="message-ad8e" class="u-form-control-hidden u-label"></label>
                                    <textarea placeholder="Agregar Clausula al contrato" rows="4" cols="50" id="message-ad8e" name="message" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required=""></textarea>
                                </div>
                                <div class="u-align-left u-form-group u-form-submit">
                                    <a href="#" class="u-border-2 u-border-grey-75 u-btn u-btn-submit u-button-style u-palette-3-base u-text-grey-75 u-btn-1">Enviar</a>
                                    <input type="submit" value="submit" class="u-form-control-hidden">
                                </div>
                                <div class="u-form-send-message u-form-send-success">La clausula se agrego al contrato correctamente</div>
                                <div class="u-form-send-error u-form-send-message">No se puede enviar su mensaje. Por favor, corrija los errores y vuelva a intentarlo.</div>
                                <input type="hidden" value="" name="recaptchaResponse">
                            </form>
                        </div>
                    </div>
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
                                
    </body>
                                
</html>
