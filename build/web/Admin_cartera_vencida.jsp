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
        <title>Cartera vencida</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
        <link href="css/tabla_scroll.css" rel="stylesheet" type="text/css"/>
        <link href="css/Tablas.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
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
        <%
        if(session.getAttribute("ced_user") ==null) {
            response.sendRedirect("Login.jsp");
        }
        %>
        <header class="u-align-center-sm u-align-center-xs u-clearfix u-gradient u-header u-header" id="sec-16cc"><div class="u-clearfix u-sheet u-sheet-1">

                <a href="https://nicepage.com" class="u-image u-logo u-image-1" data-image-width="1080" data-image-height="1080">

                    
                </a>               
                
                <nav class="u-align-right u-menu u-menu-dropdown u-offcanvas u-menu-1">
                    

                    <div class="u-custom-menu u-nav-container">
                        <ul class="u-nav u-spacing-30 u-unstyled u-nav-1">
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="Admin_home.jsp" style="padding: 10px 26px;">Inicio</a>
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-2">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="#">Noticias</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="#">Quienes Somos</a>
                                        </li></ul>
                                </div>
                            </li>
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="" style="padding: 10px 26px;"></a>
                            </li>
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="#" style="padding: 10px 26px;">Inventario</a>
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Servlet_Inventario">Ver inventario</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_inventario_agg.jsp">Agregar al inventario</a>                                                 
                                        </li>
                                    </ul>
                                </div>
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="#" style="padding: 10px 26px;">Ventas</a>
                            
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Servlet_ClienteVenta">Nueva venta</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="#">Ver ventas</a>                                                 
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="u-nav-item"><a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href= "Admin_pagos_all.jsp" style="padding: 10px 26px;">Pagos</a>
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_pagos_all.jsp">Ver pagos</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_pagos_agg.jsp">Agregar al pago</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_pagos_all.jsp">Por cobrar</a>
                                        </li>
                                    </ul>
                                </div>
                            
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="#" style="padding: 10px 26px;">Clientes</a>

                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white"    href="Servlet_Clientes">Ver clientes</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_cliente_agg.jsp">Registrar clientes</a>                                    
                                        </li>
                                    </ul>                     
                                </div>
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="#" style="padding: 10px 26px;">Certificados</a>
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white"    href="Servlet_Clientes">Contratos</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Servlet_PDF">Informe diario</a>                                    
                                        </li>
                                    </ul>                     
                                </div>
                            </li>
                            <li class="u-nav-item">
                                <a class="u-border-2 u-border-active-palette-1-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-grey-90" href="Login.html" style="padding: 10px 26px;">Usuario</a>
                                <div class="u-nav-popup">
                                    <ul class="u-h-spacing-20 u-nav u-unstyled u-v-spacing-10 u-nav-3">
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="#">Mis datos</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Servlet_Usuarios">Ver usuarios</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Admin_user_agg.jsp">Registrar usuario</a>
                                        </li>
                                        <li class="u-nav-item">
                                            <a class="u-button-style u-nav-link u-white" href="Login.jsp">Cerrar sesion</a>
                                        </li>
                                    </ul>
                                </div>                                
                                <div class="u-black u-menu-overlay u-opacity u-opacity-70">                                
                                </div>

                                
                    </div>                                
                                
                </nav>                             
                                
            </div>
                                
        </header>                          
        <section class="u-align-center u-clearfix u-section-1" id="sec-00f8">
                                    
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                                        
                <div class="u-expanded-width u-table u-table-responsive u-table-1">
                                                                    
                    <form action="action" action="javascript:sendmail()" >
                        
                        <label>Buscar: </label>
                        <input id="searchTerm" type="text" onkeyup="doSearch()" />
                        <br>
                        <br>                                                                      
                        <div id="div1" >                                                    
                            <table id="datos" class="u-table-entity u-table-entity-1" >                                                        
                                <thead class="u-gradient u-table-header u-table-header-1">                                                          
                                    <tr>  
                                        <td><strong>Id</strong></td> 
                                        <td> <strong>cedula_usuario</strong></td>
                                        <td><strong>cedula_usuario</strong></td>                               		 
                                        <td><strong>correo</strong></td>
                                        <td><strong>adquisicion</strong></td> 
                                        <td><strong>pago</strong></td>
                                        <td><strong>transcurrida</strong></td>
                                        <td><strong>adquisicion</strong></td>
                                        <td><strong>cancelar</strong></td>
                                        <td><strong>adeudado</strong></td>                                     
                                        <td><strong>vencido</strong></td>
                                        <td colspan=1><strong>ACCIONES</strong></td>                                                        
                                    </tr>                                                        
                                </thead>                                                        
                                <tbody class="u-table-alt-palette-3-light-3 u-table-body u-table-body-1">                                                            
                                    <c:forEach var="Objeto" items="${lista}">                                                                                                                                
                                        <tr>  
                                            <td><c:out value="${Objeto.item}"/></td> 
                                            <td><c:out value="${Objeto.cedula_cliente}"/></td>
                                            <td><c:out value="${Objeto.nombre_completo}"/></td>  
                                                                                                                                                                                                               
                                            <td><c:out value="${Objeto.correo}"/></td> 
                                            <td><c:out value="${Objeto.fecha_adquisicion}"/></td>                                                                                                                                              
                                            <td><c:out value="${Objeto.fecha_ultimo_pago}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.fecha_transcurrida}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.valor_adquisicion}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.valor_cancelado}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.valor_que_adeuda}"/></td>                                                                                                                                             
                                            <td><c:out value="${Objeto.valor_vencido}"/></td>
                                            <td><input type="submit" value="selecccionar para enviar notificacion" class="btn btn-primary"/></td>                                                             
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
