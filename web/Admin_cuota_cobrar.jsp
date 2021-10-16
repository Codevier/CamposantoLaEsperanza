<%-- 
    Document   : Login
    Created on : 16-ago-2021, 17:08:02
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="page_type" content="np-template-header-footer-from-plugin">
        <title>Cuota</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
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
                    <form action="Servlet_CuotaPago?id=${object.id_cuota}" method="POST"  name="form" style="padding: 10px;">
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-9e0b" class="u-label">Fecha progromada</label>
                            <input disabled  id="puerta" value="${object.fecha_programada}" name="puerta" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="required" onkeypress="return validaNumericos(event)">
                        </div>
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label for="text-dca0" class="u-label">Valor a pagar</label>
                            <input disabled type="number" id="text-dca0" value="${object.valor_apagar}" id="bloque" name="bloque" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="required" onkeypress="return validaNumericos(event)">
                        </div>
                        <div>
                            <label for="text-dca0" class="u-label">Valor a cancelar</label>
                            <input type="number" step="any" id="text-dca0" placeholder="Introduzca el valor de la papeleta" id="valor" name="valor" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="required" onkeypress="return validaNumericos(event)">
                        </div>
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label for="text-dca0" class="u-label">Numero de papeleta</label>
                            <input type="number" id="text-dca0" placeholder="Introduzca el numero de la papeleta" id="papeleta" name="papeleta" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" required="required" onkeypress="return validaNumericos(event)">
                        </div>
                        <div class="u-align-left u-form-group u-form-submit">
                            <center><input type="submit" id="btn-action" class="u-btn u-btn-submit u-button-style" value="Cobrar"/></center>
                        </div>
                    </form>
                </div>
            </div>
        </section>



        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-c103"><div class="u-clearfix u-sheet u-sheet-1">
                <p class="u-small-text u-text u-text-variant u-text-1">Km. 7½ Vía Valencia<br><b>Atención al cliente y ventas</b>
                    <br>052754912 / 0969202533 / 0996819223<br> &nbsp;
                </p>
            </div>
        </footer>
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
