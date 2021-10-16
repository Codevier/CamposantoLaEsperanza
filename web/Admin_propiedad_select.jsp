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
        <link href="css/Tablas.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script src="js/BuscadorTabla.js" type="text/javascript"></script>
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
                <div class="u-expanded-width u-table u-table-responsive u-table-1" >
                    <div class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label for="text-dca0" class="u-label">Cliente seleccionado</label><br> 
                            <label style="width: 70px; display: inline-block;" for="text-dca0" class="u-label" >Cedula</label>
                            <input type="text" id="cedula_cliente" name="cedula_cliente" value="${cedula}" disabled >
                        </div>
                        <div class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label style="width: 70px; display: inline-block;" for="text-dca0" class="u-label">Nombre</label>
                            <input type="text" id="nombre_cliente" name="nombre_cliente"  value="${nombre}" disabled >
                        </div>
                    <form action="action">
                       
                        <br>  
                        <label>Buscar: </label>
                        <input id="searchTerm" type="text" onkeyup="doSearch()" />
                        <br>
                        <br> 
                            <table id="datos" class="u-table-entity u-table-entity-1" >
                            <thead class="u-gradient u-table-header u-table-header-1">
                            <tr>
                                <td> <strong>id_propiedad</strong></td>
                                <td><strong>cedula_usuario</strong></td>                               		 
                                <td><strong>titulo_de_propiedad</strong></td> 
                                <td><strong>tipo</strong></td> 
                                <td><strong>puerta</strong></td>
                                <td><strong>bloque</strong></td>
                                <td><strong>cara</strong></td>
                                <td><strong>fecha_registro</strong></td>
                                <td colspan=1><strong>ACCIONES</strong></td>	
                            </tr>
                            </thead>
                            <tbody class="u-table-alt-palette-3-light-3 u-table-body u-table-body-1">
                            <c:forEach var="Objeto" items="${lista}">
                                <tr>
                                 <td><c:out value="${Objeto.id_propiedad}"/></td>
                                <td><c:out value="${Objeto.cedula_usuario}"/></td>                                    
                                <td><c:out value="${Objeto.titulo_de_propiedad}"/></td>                                                                                                                                              
                                <td><c:out value="${Objeto.tipo}"/></td>                                                                                                                                              
                                <td><c:out value="${Objeto.puerta}"/></td>                                                                                                                                             
                                <td><c:out value="${Objeto.bloque}"/></td>                                                                                                                                             
                                <td><c:out value="${Objeto.cara}"/></td>                                                                                                                                             
                                <td><c:out value="${Objeto.fecha_registro}"/></td>                                                                                                                                             
                                <td><a href="Servlet_ContratoNuevo?cedula=${cedula}&nombre=${nombre}&propiedad=${Objeto.id_propiedad}&tipo=${Objeto.tipo}&puerta=${Objeto.puerta}&bloque=${Objeto.bloque}&cara=${Objeto.cara}">Seleccionar</a></td>
                                </tr>
                            </c:forEach>
                                
                            </tbody>
                        </table>
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
                                
    </body>
                                
</html>
