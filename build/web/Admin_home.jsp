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
        <title>Inicio</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/nicepage.js" defer=""></script>
        <link href="css/Inicio.css" rel="stylesheet" type="text/css"/>
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
    <body class="u-body" >
        <%
        if(session.getAttribute("ced_user") ==null) {
            response.sendRedirect("Login.jsp");
        }
        %>
        
        
        <jsp:include page="header/header.html"></jsp:include>              
        <section class="u-align-center u-clearfix u-section-1" id="sec-4155">                   
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <h1 class="u-text u-text-default u-text-1">Bienvenido  ${nombre_user} <br>
                    
                </h1>
                <h1 class="u-text u-text-default u-text-1">"Cuando la voz calla con la muerte, mi corazón te seguirá hablando"<br>
                    <br>- Rabindranath Tagore
                </h1>
                <div class="u-carousel u-expanded-width-xs u-gallery u-layout-thumbnails u-lightbox u-no-transition u-show-text-always u-gallery-1" id="carousel-e131" data-interval="5000" data-u-ride="carousel">                       
                    <div class="u-carousel-inner u-gallery-inner" role="listbox">
                        <div class="u-active u-carousel-item u-gallery-item u-carousel-item-1">
                            <div class="u-back-slide" data-image-width="286" data-image-height="176">
                                <img class="u-back-image u-expanded" src="images/descarga.jpg">
                            </div>
                            <div class="u-over-slide u-over-slide-1">
                                <h3 class="u-gallery-heading">Sample Title</h3>
                                <p class="u-gallery-text">Sample Text</p>                  
                            </div>                  
                        <div class="u-carousel-item u-gallery-item u-carousel-item-2">
                            <div class="u-back-slide" data-image-width="512" data-image-height="288">
                                <img class="u-back-image u-expanded" src="images/unnamed.jpg">                  
                            </div>
                            <div class="u-over-slide u-over-slide-2">
                                <h3 class="u-gallery-heading">Sample Title</h3>
                                <p class="u-gallery-text">Sample Text</p>                  
                            </div>                  
                        </div>                  
                        <div class="u-carousel-item u-gallery-item u-carousel-item-3" data-image-width="2836" data-image-height="1875">
                            <div class="u-back-slide" data-image-width="800" data-image-height="800">
                                <img class="u-back-image u-expanded" src="images/CX1TE_uG.jpg">                  
                            </div>
                            <div class="u-over-slide u-over-slide-3">
                                <h3 class="u-gallery-heading">Sample Title</h3>
                                <p class="u-gallery-text">Sample Text</p>                  
                            </div>                  
                        </div>
                    </div>                              
                    <a class="u-absolute-vcenter u-carousel-control u-carousel-control-prev u-grey-70 u-icon-circle u-opacity u-opacity-70 u-spacing-10 u-text-white u-carousel-control-1" href="#carousel-e131" role="button" data-u-slide="prev">                 
                        <span aria-hidden="true">
                                                    
                            <svg viewBox="0 0 451.847 451.847">
                            <path d="M97.141,225.92c0-8.095,3.091-16.192,9.259-22.366L300.689,9.27c12.359-12.359,32.397-12.359,44.751,0
                                                                                             c12.354,12.354,12.354,32.388,0,44.748L173.525,225.92l171.903,171.909c12.354,12.354,12.354,32.391,0,44.744
                                                                                             c-12.354,12.365-32.386,12.365-44.745,0l-194.29-194.281C100.226,242.115,97.141,234.018,97.141,225.92z"></path></svg>
                                                
                        </span>                 
                        <span class="sr-only">
                                                    
                            <svg viewBox="0 0 451.847 451.847">
                            <path d="M97.141,225.92c0-8.095,3.091-16.192,9.259-22.366L300.689,9.27c12.359-12.359,32.397-12.359,44.751,0
                                                                                             c12.354,12.354,12.354,32.388,0,44.748L173.525,225.92l171.903,171.909c12.354,12.354,12.354,32.391,0,44.744
                                                                                             c-12.354,12.365-32.386,12.365-44.745,0l-194.29-194.281C100.226,242.115,97.141,234.018,97.141,225.92z"></path></svg>
                                                
                        </span>                    
                    </a>
                    <a class="u-absolute-vcenter u-carousel-control u-carousel-control-next u-grey-70 u-icon-circle u-opacity u-opacity-70 u-spacing-10 u-text-white u-carousel-control-2" href="#carousel-e131" role="button" data-u-slide="next">
                        <span aria-hidden="true">                     
                            <svg viewBox="0 0 451.846 451.847">
                            <path d="M345.441,248.292L151.154,442.573c-12.359,12.365-32.397,12.365-44.75,0c-12.354-12.354-12.354-32.391,0-44.744
                                                                                             L278.318,225.92L106.409,54.017c-12.354-12.359-12.354-32.394,0-44.748c12.354-12.359,32.391-12.359,44.75,0l194.287,194.284
                                                                                             c6.177,6.18,9.262,14.271,9.262,22.366C354.708,234.018,351.617,242.115,345.441,248.292z"></path></svg>
                                                
                        </span>  
                        <span class="sr-only">                        
                            <svg viewBox="0 0 451.846 451.847">
                            <path d="M345.441,248.292L151.154,442.573c-12.359,12.365-32.397,12.365-44.75,0c-12.354-12.354-12.354-32.391,0-44.744
                                                                                             L278.318,225.92L106.409,54.017c-12.354-12.359-12.354-32.394,0-44.748c12.354-12.359,32.391-12.359,44.75,0l194.287,194.284
                                                                                             c6.177,6.18,9.262,14.271,9.262,22.366C354.708,234.018,351.617,242.115,345.441,248.292z">
                                                                                                 
                            </path>
                            </svg>                 
                        </span>                    
                    </a>
                    <ol class="u-carousel-thumbnails u-spacing-10 u-carousel-thumbnails-1">
                        <li class="u-active u-carousel-thumbnail u-carousel-thumbnail-1" data-u-target="#carousel-e131" data-u-slide-to="0">
                            <img class="u-carousel-thumbnail-image u-image" src="images/descarga.jpg">                   
                        </li>
                        <li class="u-carousel-thumbnail u-carousel-thumbnail-2" data-u-target="#carousel-e131" data-u-slide-to="1">
                            <img class="u-carousel-thumbnail-image u-image" src="images/unnamed.jpg">                   
                        </li>
                        <li class="u-carousel-thumbnail u-carousel-thumbnail-3" data-u-target="#carousel-e131" data-u-slide-to="2">
                            <img class="u-carousel-thumbnail-image u-image" src="images/CX1TE_uG.jpg">                   
                        </li>                   
                    </ol>                  
                </div>                  
            </div>              
        </section>
       
        <section class="u-clearfix u-palette-3-light-3 u-section-3" id="sec-8dc8">
            <div class="u-clearfix u-sheet u-sheet-1">
                <div class="u-clearfix u-expanded-width u-layout-wrap u-layout-wrap-1">
                        <div class="u-layout">
                            <div class="u-layout-row">
                                <div class="u-align-left u-container-style u-layout-cell u-left-cell u-size-30 u-layout-cell-1">
                                    <div class="u-container-layout u-container-layout-1">
                                        <p class="u-text u-text-1"><b>Servicios funerarios</b>
                                            <br>
                                            <br>Desde 2013 brindamos servicios funerarios en Quevedo, ofreciendo un espacio para el descanso eterno de tus seres queridos en armonía con la naturaleza.<br>Honramos la memoria de quienes han partido, brindándote atención inmediata y con calidez humana para tu tranquilidad.<br><b>Dirección Camposanto:</b>&nbsp;Km 7 ½ Vía Valencia<br><b>Horario de atención:</b>&nbsp;Lun – Vie 08H30 a 17H30, Domingos 08H30 a 16H00<br>
                                            <br>Accede a nuestros servicios integrales para el descanso eterno: Inhumación, cremación y&nbsp;velación. Llámanos al 099 6819223
                                        </p>
                                    </div>
                                </div>
                                <div class="u-container-style u-layout-cell u-right-cell u-size-30 u-layout-cell-2">
                                    <div class="u-container-layout u-container-layout-2">
                                        <div class="u-expanded u-grey-10 u-map">
                                            <div class="embed-responsive">
                                                <iframe class="embed-responsive-item" src="//maps.google.com/maps?output=embed&amp;q=Km%207%20V%C3%ADa%20a%20Valencia%20Quevedo%2C%20Ecuador&amp;t=m" data-map="JTdCJTIycG9zaXRpb25UeXBlJTIyJTNBJTIybWFwLWFkZHJlc3MlMjIlMkMlMjJhZGRyZXNzJTIyJTNBJTIyS20lMjA3JTIwViVDMyVBRGElMjBhJTIwVmFsZW5jaWElMjBRdWV2ZWRvJTJDJTIwRWN1YWRvciUyMiUyQyUyMnpvb20lMjIlM0ElMjIlMjIlMkMlMjJ0eXBlSWQlMjIlM0ElMjJyb2FkJTIyJTJDJTIybGFuZyUyMiUzQW51bGwlMkMlMjJhcGlLZXklMjIlM0FudWxsJTJDJTIybWFya2VycyUyMiUzQSU1QiU1RCU3RA=="></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>  
            </div>
        </section>
        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-c103">
            <div class="u-clearfix u-sheet u-sheet-1">
                <p class="u-small-text u-text u-text-variant u-text-1">Km. 7½ Vía Valencia<br>
                    <b>Atención al cliente y ventas</b>
                            <br>052754912 / 0969202533 / 0996819223<br> &nbsp    
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
