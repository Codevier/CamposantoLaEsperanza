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
        <title>Agregar contrato</title>
        <link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/Login.css" media="screen">
        <link href="css/Registrar-Usuario.css" rel="stylesheet" type="text/css"/>
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
        <script language="JavaScript">
        if(history.forward(1)){
        history.replace(history.forward(1));
        } //Esto para cuando le pulse al botón
        
        </script>
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
        <section class="u-clearfix u-section-1" id="sec-4739">
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div class="u-form u-form-1">
                    
                    
                    <form action="Servlet_ContratoAgg?ced_cliente=${cedula}&puerta=${puerta}&tipo_propiedad=${tipo}&bloque=${bloque}&cara=${cara}&propiedad=${propiedad}" method="POST"  name="form" style="padding: 10px;">
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Cédula del Cliente:</label>
                            <input type="text" placeholder="" id="text-ae35" name="ced_cliente" disabled value="${cedula}"   class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:right" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Nombre del Cliente:</label>
                            <input type="text" placeholder="" id="text-ae35" name="nombre_cliente" value="${nombre}" disabled class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Nombre del Vendedor:</label>
                            <input type="text" placeholder="" id="text-ae35" name="nombre_vendedor" disabled value="${nombre_user}" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        
                        </div>
                        <div style="width: 50%; float:right" class="u-form-group u-form-partition-factor-2 u-form-group-4">
                            <label for="text-3a01" class="u-label">Tipo de contrato</label>
                            <select required class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white" id="tipo_contrato" name="tipo_contrato" >
                            </select>
                        </div> 
                        <div style="width: 100%; float:left" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Numero de papeleta:</label>
                            <input  type="text" id="text-ae35" name="papeleta"   class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Valor de la propiedad</label>
                            <input   id="valor_propiedad" name="valor_propiedad" disabled  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:right" class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Entrada</label>
                            <input  id="entrada" name="entrada" disabled  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-9">
                            <label for="text-7fc3" class="u-label">Cantidad de meses:</label>
                            <input id="cantidad_meses" disabled  name="cantidad_meses" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-11">
                            <label for="text-49fa" class="u-label">Valor por mes:</label>
                            <input id="valor_por_mes" disabled  name="valor_por_mes" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Tipo de propiedad:</label>
                            <input id="tipo_propiedad" name="tipo_propiedad" disabled value="${tipo}" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 33%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-8">
                            <label for="text-f7c8" class="u-label">Bloque Nº:</label>
                            <input id="bloque" name="bloque" disabled value="${bloque}" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 33%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-9">
                            <label for="text-7fc3" class="u-label">Puerta Nº:</label>
                            <input type="text" placeholder="" id="puerta" disabled value="${puerta}" name="puerta" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 34%; float:left" class="u-form-group u-form-partition-factor-4 u-form-group-11">
                            <label for="text-49fa" class="u-label">Cara:</label>
                            <input type="text" placeholder="" id="cara" disabled value="${cara}" name="cara" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Nombres del Difunto:</label>
                            <input type="text" placeholder="" id="text-ae35" name="nombres_difunto"  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div style="width: 50%; float:left" class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Apellidos del Difunto:</label>
                            <input type="text" placeholder="" id="text-ae35" name="apellidos_difunto" class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div  class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Cedula del Difunto:</label>
                            <input maxlength="10" type="text" id="text-ae35" name="cedula_difunto"   class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        <div  class="u-form-group u-form-partition-factor-2 u-form-group-3">
                            <label for="text-ae35" class="u-label">Fecha de defuncion</label>
                            <input type="date" id="text-ae35" name="fecha_defuncion"  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        
                        <div class="u-form-group u-form-group-2">
                            <label for="text-c714" class="u-label">Direccion donde vivia del Difunto:</label>
                            <input type="text" placeholder="Direccion" id="text-c714"   name="direccion_difunto"  class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
                        </div>
                        
                        <div class="u-align-left u-form-group u-form-submit">
                            <center><input type="submit" id="btn-action"  class="u-btn u-btn-submit u-button-style" value="Guardar"/></center>
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
                                .attr("value", item.id_tipo_contrato) //.. con el valor
                                .text(item.description) //.. el texto a mostrar
                                .appendTo("#tipo_contrato"); //.. y lo agregamos al combo
                    });
                    var productCode = $("#tipo_contrato").val();
                    
                    $.getJSON("Servlet_Combobox_tipoContrato_s", //.. este es el servlet
                        {productCode: productCode}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            //document.getElementById("valueInput").innerHTML =data.;
                            //y construimos el combo
                            //$("#product").empty(); //limpiamos lo que hay
                            
                            $.each(data, function (index, item) {
                                //document.getElementById("valor_propiedad").innerHTML =item.valorBoveda;
                                //document.getElementById("saldo").innerHTML =item.saldo;
                                //document.getElementById("valor_por_mes").innerHTML =item.entrada;
                                $("#valor_propiedad").empty();
                                $("#valor_propiedad").val(item.valorBoveda);
                                
                                $("#entrada").empty();
                                $("#entrada").val(item.entrada);
                                
                                $("#valor_por_mes").empty();
                                $("#valor_por_mes").val(item.valor_mes);
                                
                                $("#cantidad_meses").empty();
                                $("#cantidad_meses").val(item.cantidad_meses);
                                 
                                //$("<option/>")
                                  //      .attr("value", item.description)
                                    //    .text(item.description)
                                      //  .appendTo("#product");

                            });
                            //$("#product").change(productCodeOnChange2);
                        });
                    
                
                    $("#tipo_contrato").change(productCodeOnChange);// asignamos el evento si cambia de valor
                    
                });
                
            };
            var productCodeOnChange = function () {
                var productCode = $(this).val(); //tomamos el valor seleccionado
                console.log("productCode:", productCode);// lo mostramos en el log
                //... y llamamos a nuestro servlet
                $.getJSON("Servlet_Combobox_tipoContrato_s", //.. este es el servlet
                        {productCode: productCode}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            //document.getElementById("valueInput").innerHTML =data.;
                            //y construimos el combo
                            //$("#product").empty(); //limpiamos lo que hay
                            
                            $.each(data, function (index, item) {
                                //document.getElementById("valor_propiedad").innerHTML =item.valorBoveda;
                                //document.getElementById("saldo").innerHTML =item.saldo;
                                //document.getElementById("valor_por_mes").innerHTML =item.entrada;
                                $("#valor_propiedad").empty();
                                $("#valor_propiedad").val(item.valorBoveda);
                                
                                $("#entrada").empty();
                                $("#entrada").val(item.entrada);
                                
                                $("#valor_por_mes").empty();
                                $("#valor_por_mes").val(item.valor_mes);
                                
                                $("#cantidad_meses").empty();
                                $("#cantidad_meses").val(item.cantidad_meses);
                                 
                                //$("<option/>")
                                  //      .attr("value", item.description)
                                    //    .text(item.description)
                                      //  .appendTo("#product");

                            });
                            //$("#product").change(productCodeOnChange2);
                        });
            };
           
            $(document).ready(init);
        </script>                            
    </body>                                
</html>
