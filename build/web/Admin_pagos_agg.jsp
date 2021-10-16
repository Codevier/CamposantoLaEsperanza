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
        <title>Login</title>
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
        <%
        if(session.getAttribute("ced_user") ==null) {
            response.sendRedirect("Login.jsp");
        }
        %>
        <jsp:include page="header/header.html"></jsp:include>                             
        <section class="u-align-center u-clearfix u-section-1" id="sec-00f8">
                                    <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                                        <div class="u-expanded-width u-table u-table-responsive u-table-1">
                                            <form action = "SearchName" method = "post"> <! - El nombre del servlet es SearchName ->
                                                Buscar por : 
                                                <select name="OS">
                                                    <option value="1">Numero</option> 
                                                    <option value="2">Entrada</option> 
                                                    <option value="3">Bloque</option>
                                                    <option value="10">Cara</option> 
                                                    <option value="11">Lote</option> 
                                                    <option value="12">Fecha de registro</option> 
                                                </select> <input type = "text" name = "name">
                                                <input type = "submit" value = "Filtrar" />
                                            </form>
                                            <table class="u-table-entity u-table-entity-1">
                                                <colgroup>
                                                    <col width="10%">
                                                    <col width="10%">
                                                    <col width="10%">
                                                    <col width="10%">
                                                    <col width="10%">
                                                    <col width="25%">
                                                    <col width="20%">
                                                </colgroup>
                                                <thead class="u-gradient u-table-header u-table-header-1">
                                                    <tr style="height: 65px;">
                                                        <th class="u-table-cell">Numero</th>
                                                        <th class="u-table-cell">Entrada</th>
                                                        <th class="u-table-cell">Bloque</th>
                                                        <th class="u-table-cell">Cara</th>
                                                        <th class="u-table-cell">Lote</th>
                                                        <th class="u-table-cell">Fecha de registro</th>
                                                        <th colspan=2>ACCIONES</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="u-table-alt-palette-3-light-3 u-table-body u-table-body-1">
                                                    <tr style="height: 65px;">
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">15-08-2021</td>
                                                        <td><a href="adminArticulo?action=showedit&id=<c:out value=${articulo.id}" />Editar</a></td>
                                                        <td><a href="adminArticulo?action=eliminar&id=<c:out value=${articulo.id}"/>Eliminar</a> </td>
                                                    </tr>
                                                    <tr style="height: 65px;">
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">15-08-2021</td>
                                                        <td><a href="adminArticulo?action=showedit&id=<c:out value=${articulo.id}" />Editar</a></td>
                                                        <td><a href="adminArticulo?action=eliminar&id=<c:out value=${articulo.id}"/>Eliminar</a> </td>
                                                    </tr>
                                                    <tr style="height: 65px;">
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">15-08-2021</td>
                                                        <td><a href="adminArticulo?action=showedit&id=<c:out value=${articulo.id}" />Editar</a></td>
                                                        <td><a href="adminArticulo?action=eliminar&id=<c:out value=${articulo.id}"/>Eliminar</a> </td>
                                                    </tr>
                                                    <tr style="height: 45px;">
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">01</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">02</td>
                                                        <td class="u-table-cell">15-08-2021</td>
                                                        <td><a href="adminArticulo?action=showedit&id=<c:out value=${articulo.id}" />Editar</a></td>
                                                        <td><a href="adminArticulo?action=eliminar&id=<c:out value=${articulo.id}"/>Eliminar</a> </td>
                                                    </tr>
                                                </tbody>
                                            </table>
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
