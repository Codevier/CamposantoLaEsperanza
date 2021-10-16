<%@page import="Modelos.Coneccion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css1/bootstrap.min.css" rel="stylesheet" type="text/css"/>     
        <title>JSP Page</title>
        <style>
            table tr:not(:first-child){
                cursor: pointer;transition: all .25s ease-in-out;
            }
            table tr:not(:first-child):hover{background-color: #ddd;}
        </style>
    </head>
    <body style="margin-top: 30px">      
        <%
            //CONECTANOD A LA BASE DE DATOS:
            PreparedStatement pst;
            Coneccion cn = new Coneccion();
            ResultSet rs;
            PreparedStatement ps;
            String sql;
            //Emnpezamos Listando los Datos de la Tabla Usuario
            Statement smt;
            smt = cn.getConecction().createStatement();
            rs = smt.executeQuery("select co.numero_contrato as NumeroContrato, co.cedula_cliente as Cedula,cl.apellido_paterno ||' '||cl.apellido_materno ||' '||cl.nombre_cliente as Cliente ,co.saldo as Valor_restante , cl.correo as email from cartera_vencida cv inner join contratos co on co.id_contrato=cv.id_contrato inner join clientes cl on cl.cedula_cliente= co.cedula_cliente");
            //Creamo la Tabla:     
        %>
        <br>
        <div class="container"> 
            <form class="form1" action="javascript:sendmail()" >
                <input type="text" name="Cedula1" id="Cedula1" style="visibility:hidden;"><br><br>
                <input type="text" name="NumeroContrato1" id="NumeroContrato1" style="visibility:hidden;"><br><br>
                <input type="text" name="Cliente1" id="Cliente1" style="visibility:hidden;"><br><br>
                <input type="text" name="Valor_restante1" id="Valor_restante1"style="visibility:hidden;"><br><br>
                <input type="text" name="email1" id="email1" style="visibility:hidden;"><br><br>
                <!--<a  class="btn btn-success" href="Agregar.jsp">Nuevo Registro</a> Esto es Cuando se Crea un nuevo Archivo Agregar.jsp -->         
                <table class="table table-bordered"  id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center">numero_contrato</th>
                            <th class="text-center">cedula_cliente</th>
                            <th class="text-center">Nombres</th>
                            <th class="text-center">Valor restante</th>
                            <th class="text-center">correo</th>
                            <th class="text-center">Accion</th>
                        </tr>
                    </thead>
                    <tbody id="tbodys">
                        <%
                            while (rs.next()) {
                        %>
                        <tr>
                            <td class="text-center"><%= rs.getString("NumeroContrato")%></td>
                            <td class="text-center"><%= rs.getString("Cedula")%></td>
                            <td class="text-center"><%= rs.getString("Cliente")%></td>
                            <td class="text-center"><%= rs.getString("Valor_restante")%></td>
                            <td class="text-center"><%= rs.getString("email")%></td>
                            <td class="text-center">
                                <input type="submit" value="selecccionar para enviar notificacion" class="btn btn-primary"/>
                            </td>

                        </tr>

                        <%}%>
                </table>

            </form>
        </div>

        <script>

            var table = document.getElementById('tablaDatos');

            for (var i = 1; i < table.rows.length; i++)
            {
                table.rows[i].onclick = function ()
                {
                    //rIndex = this.rowIndex;
                    document.getElementById("Cedula1").value = this.cells[0].innerHTML;
                    document.getElementById("NumeroContrato1").value = this.cells[1].innerHTML;
                    document.getElementById("Cliente1").value = this.cells[2].innerHTML;
                    document.getElementById("Valor_restante1").value = this.cells[3].innerHTML;
                    document.getElementById("email1").value = this.cells[4].innerHTML;
                };
            }

        </script>

        <script src="https://smtpjs.com/v3/smtp.js"></script>
        <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
        <script>
            function sendmail()
            {
                var email = $('#email1').val();
                var cedula = $('#Cedula1').val();
                var contrato = $('#NumeroContrato1').val();
                var nombre = $('#Cliente1').val();
                var cancelar = $('#Valor_restante1').val();

                var Body = '<br>Nombre del cliente :  ' + nombre + '<br> con la cedula : ' + cedula + '<br> con el contrato:' + contrato + '<br> debe el valor de:' + cancelar;
                //console.log(name, phone, email, message);
                Email.send({
                    SecureToken: "88e5bcc2-528f-4355-bfcb-808ba2e58224",
                    To: email,
                    From: "gadlaesperanza.cementerio@gmail.com",
                    Subject: "New message de GadEsperanza Vinculacion ",
                    Body: Body
                }
                ).then(
                        message => {
                            //console.log (message);
                            if (message == 'OK') {
                                alert('Notificacion enviado al correo del cliente.');
                            } else {
                                console.error(message);
                                alert('Error. No se ha enviado. ')

                            }

                        }
                );

            }
        </script>
        <script src="js/jquery1.js" type="text/javascript"></script>             
        <script src="js/bootstrap1.min.js" type="text/javascript"></script>        
    </body>
</html>
