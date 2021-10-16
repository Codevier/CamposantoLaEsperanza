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
        <title>Cuotas</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
        <link href="css/tabla_scroll.css" rel="stylesheet" type="text/css"/>
        <link href="css/Tablas.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
        <script src="js/ordenar.js" type="text/javascript"></script>
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/nicepage.js" defer=""></script>
        <script src="js/BuscadorTabla.js" type="text/javascript"></script>
        <script src="js/BuscadorUsuario.js" type="text/javascript"></script> 
        <script type=”text/javascript” src=”js/stacktable.js”></script>
        <meta name="generator" content="Nicepage 3.22.0, nicepage.com">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
        <script type="application/ld+json">{
            "@context": "http://schema.org",
            "@type": "Organization",
            "name": "",
            "logo": "images/Diseosinttulo6.png",
            "sameAs": []
            }</script>
        <script type=”text/javascript”>
            $(document).ready(function () {
                $(‘#tabla1′).stacktable();
            });
        </script>


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
        <section class="u-align-center u-clearfix u-section-1" id="sec-00f8">
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div class="u-expanded-width u-table u-table-responsive u-table-1">                                              
                    <form action="action">
                        <label>Buscar: </label>
                        <input id="searchTerm" type="text" onkeyup="doSearch()" />
                        <br>
                        <br>                                                                      
                        <div id="div1" style="max-height:  600px;">                                                    
                            <table id="datos" class="u-table-entity u-table-entity-1" >                                                        
                                <thead class="u-gradient u-table-header u-table-header-1">                                                          
                                    <tr>                                                                
                                        <td onclick="sortTable(0, 'str')"><strong>id</strong></td>
                                        <td onclick="sortTable(1, 'str')"><strong>contrato</strong></td> 
                                        <td onclick="sortTable(2, 'str')"><strong>numero de cuota</strong></td>
                                        <td onclick="sortTable(3, 'str')"><strong>registro</strong></td>
                                        <td onclick="sortTable(4, 'str')"><strong>estado</strong></td>
                                        <td onclick="sortTable(5, 'str')"><strong>fecha programada</strong></td>
                                        <td onclick="sortTable(6, 'str')"><strong>fecha realizada</strong></td>
                                        <td onclick="sortTable(7, 'str')"><strong>papeleta</strong></td>
                                        <td onclick="sortTable(8, 'str')"><strong>valor a pagar</strong></td>
                                        <td onclick="sortTable(9, 'str')"><strong>valor cancelado</strong></td>
                                        <td colspan=2><strong>ACCIONES</strong></td>                                                        
                                    </tr>                                                        
                                </thead>                                                        
                                <tbody class="u-table-alt-palette-3-light-3 u-table-body u-table-body-1">                                                            
                                    <c:forEach var="Objeto" items="${lista}">                                                                                                                                
                                        <tr>                                                                                      
                                            <td><c:out value="${Objeto.id_cuota}"/></td>
                                            <td><c:out value="${Objeto.id_contrato}"/></td> 
                                            <td><c:out value="${Objeto.numero_cuota}"/></td>
                                            <td><c:out value="${Objeto.fecha_registro}"/></td>                                                                                
                                            <td><c:out value="${Objeto.estado}"/></td> 
                                            <td><c:out value="${Objeto.fecha_programada}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.fecha_realizada}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.num_papeleta}"/></td> 
                                            <td>$<c:out value="${Objeto.valor_apagar}"/></td>  
                                            <td>$<c:out value="${Objeto.valor_cancelado}"/></td>
                                            <c:choose>
                                                <c:when test="${Objeto.estado=='deuda'}">
                                                    <td><a href="Servlet_CuotaSeleccionada?id_cuota=${Objeto.id_cuota}">Cobrar</a></td>   
                                                </c:when>    
                                                <c:otherwise>
                                                    <td><a href="#">Sin accion</a></td>   
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>                                                            
                                    </c:forEach>                                                        
                                </tbody>                 
                            </table>         
                        </div>       
                    </form>             
                </div>         
            </div>          
        </section>
        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-c103">
            <div class="u-clearfix u-sheet u-sheet-1">
                                        
                <p class="u-small-text u-text u-text-variant u-text-1">Km. 7½ Vía Valencia<br>
                    <b>Atención al cliente y ventas</b>
                                            
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
