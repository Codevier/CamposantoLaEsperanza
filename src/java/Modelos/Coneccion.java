/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.itextpdf.text.pdf.codec.Base64;
import static com.sun.xml.ws.security.trust.sts.BaseSTSImpl.ENCRYPT_KEY;
import java.rmi.RemoteException;
import java.security.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Usuario
 */
public class Coneccion {

    Statement st = null;
    String BD = "jdbc:postgresql://localhost:5433/camposanto_la_esperanza";
    String postgres = "postgres";
    String usuario = "postgres";
    String contra = "1234567";

    public String login(String usuario_b, String clave_b) throws RemoteException {
        String respuesta = "sin respuesta";
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, postgres, contra);
            st = conectar.createStatement();

            ResultSet rs1 = st.executeQuery("select  nombre_usuario from usuarios where cedula_usuario='" + usuario_b + "'");
            respuesta = "credenciales incorrectas";
            while (rs1.next()) {
                rs1.getString("nombre_usuario");
                rs1 = st.executeQuery("select  tipo_usuario from usuarios where cedula_usuario='" + usuario_b + "'");
                while (rs1.next()) {
                    respuesta = rs1.getString("tipo_usuario");
                    if (respuesta.equals("Administrador")) {
                        st = conectar.createStatement();
                        ResultSet rs2 = st.executeQuery("select  nombre_usuario from usuarios where cedula_usuario='" + usuario_b + "' and clave='" + clave_b + "'");
                        while (rs2.next()) {
                            rs2.getString("nombre_usuario");
                        }
                    } else {
                        respuesta = "sin acceso";
                    }
                }

            }
        } catch (SQLException ex) {
            respuesta = "error";

        }
        return respuesta;
    }

    public ResultSet ValoresTotalesInforme() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        ResultSet rs = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            rs = st.executeQuery(" SELECT     sum(ct.costo)as costo ,sum(valor_cancelado) as vdeposito,sum(valor_apagar) as saldo \n"
                    + "FROM public.cuotas cu inner join contratos ct on cu.id_contrato=ct.id_contrato inner join clientes cl on cl.cedula_cliente=ct.cedula_cliente");
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }
    
    public String[] Clausulas() {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select count(id_clausula) as dimension from Clausulas;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension];

            st = conectar.createStatement();
            rs = st.executeQuery("select id_clausula,clausula from Clausulas");
            dimension = 0;
            while (rs.next()) {
                respuesta[dimension] = rs.getString("clausula");

                dimension += 1;
            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[] Datos_Contrato(String numerocontrato) {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT  numero_cuenta as ncuenta FROM public.cuenta_pagos;");
            String numero_cuenta = "";
            while (rs.next()) {
                numero_cuenta = rs.getString("ncuenta");
            }
            rs = st.executeQuery("SELECT cl.cedula_cliente, cl.nombre_cliente ||' '|| cl.apellido_paterno ||' '|| cl.apellido_materno as nombres,  ct.\"Valor_total\", ct.valor_de_entrada, ct.saldo, p.tipo, p.puerta, p.bloque, p.cara, ct.fecha_del_contrato\n"
                    + "	FROM public.contratos ct inner join public.propiedad p ON p.id_propiedad = ct.id_propiedad inner join \n"
                    + "	public.clientes cl ON cl.cedula_cliente = ct.cedula_cliente\n"
                    + "	where ct.numero_contrato='" + numerocontrato + "'");
            respuesta = new String[11];

            while (rs.next()) {
                respuesta[0] = rs.getString(1);
                respuesta[1] = rs.getString(2);
                respuesta[2] = rs.getString(3);
                respuesta[3] = rs.getString(4);
                respuesta[4] = rs.getString(5);
                respuesta[5] = rs.getString(6);
                respuesta[6] = rs.getString(7);
                respuesta[7] = rs.getString(8);
                respuesta[8] = rs.getString(9);
                respuesta[9] = numero_cuenta;
                respuesta[10] = rs.getString(10);
            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[] Datos_Inhumacion(String numerocontrato) {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select c.\"Cedula_difunto\", c.\"Nombres_difunto\" || ' ' || c.\"Apellidos_difunto\" as NombresApellidos,c.\"Fecha_defuncion\", p.puerta, p.bloque, p.cara \n"
                    + "from contratos c inner join propiedad p on c.id_propiedad=p.id_propiedad\n"
                    + "where c.numero_contrato='" + numerocontrato + "'");
            respuesta = new String[6];
            while (rs.next()) {
                respuesta[0] = rs.getString(1);
                respuesta[1] = rs.getString(2);
                respuesta[2] = rs.getString(3);
                respuesta[3] = rs.getString(4);
                respuesta[4] = rs.getString(5);
                respuesta[5] = rs.getString(6);
            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[] Datos_CesionDerechos(String numerocontrato) {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select p.tipo, p.puerta, p.bloque,p.cara , cl.cedula_cliente, cl.nombre_cliente || ' ' || cl.apellido_paterno || ' ' || cl.apellido_materno\n"
                    + "as nombresaepellidos,cl.parroquia,cl.referencia, r.cedula_cliente , c.\"Valor_total\"\n"
                    + "from propiedad p inner join contratos c on p.id_propiedad=c.id_propiedad\n"
                    + "inner join clientes cl on c.cedula_cliente=cl.cedula_cliente\n"
                    + "inner join renovacion r on r.id_contrato=c.id_contrato\n"
                    + "where c.numero_contrato='" + numerocontrato + "'");

            respuesta = new String[10];

            while (rs.next()) {
                respuesta[0] = rs.getString(1);
                respuesta[1] = rs.getString(2);
                respuesta[2] = rs.getString(3);
                respuesta[3] = rs.getString(4);
                respuesta[4] = rs.getString(5);
                respuesta[5] = rs.getString(6);
                respuesta[6] = rs.getString(7);
                respuesta[7] = rs.getString(8);
                respuesta[8] = rs.getString(9);
                respuesta[9] = rs.getString(10);

            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[] Datos_Cliente(String cedulacliente) {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select r.cedula_cliente, cl.nombre_cliente || ' ' || cl.apellido_paterno || ' ' || cl.apellido_materno\n"
                    + "as nombresaepellidos,cl.parroquia,cl.referencia\n"
                    + "from renovacion r inner join clientes cl on cl.cedula_cliente=r.cedula_cliente\n"
                    + "where cl.cedula_cliente='" + cedulacliente + "'");

            respuesta = new String[4];

            while (rs.next()) 
            {
                respuesta[0] = rs.getString(1);
                respuesta[1] = rs.getString(2);
                respuesta[2] = rs.getString(3);
                respuesta[3] = rs.getString(4);
            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[] Datos_Ren_contrato(String numerocontrato) {
        String[] respuesta = new String[0];
        Statement st = null;

        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select cl.cedula_cliente, cl.nombre_cliente || ' ' || cl.apellido_paterno || ' ' || cl.apellido_materno\n"
                    + "as nombresaepellidos,r.\"Valor_total\" , r.\"Cantidad_meses\", c.\"Valor_total\",c.\"Cantidad_meses\"\n"
                    + "from public.renovacion r inner join clientes cl on r.cedula_cliente=cl.cedula_cliente inner join contratos c on c.id_contrato=r.id_contrato\n"
                    + "where c.numero_contrato='" + numerocontrato + "'");
            respuesta = new String[6];
            while (rs.next()) {
                respuesta[0] = rs.getString(1);
                respuesta[1] = rs.getString(2);
                respuesta[2] = rs.getString(3);
                respuesta[3] = rs.getString(4);
                respuesta[4] = rs.getString(5);
                respuesta[5] = rs.getString(6);
            }
            System.out.println("Consultando...");

        } catch (SQLException ex) {
            System.out.println("Error..."+ex.toString());
            
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public String nombreUser(String usuario_b) throws RemoteException {
        String respuesta = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, postgres, contra);
            st = conectar.createStatement();

            ResultSet rs1 = st.executeQuery("select  nombre_usuario from usuarios where cedula_usuario='" + usuario_b + "'");
            while (rs1.next()) {
                respuesta = rs1.getString("nombre_usuario");

            }

        } catch (SQLException ex) {
            respuesta = "error";

        }
        return respuesta;
    }

    public boolean existeCliente(String filtro) {
        boolean respuesta = false;
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(C.cedula_cliente) as dimension FROM public.clientes "
                    + "C WHERE C.cedula_cliente='" + filtro + "'");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            if (dimension > 0) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Boolean Insert_Clientes(Clientes c) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("INSERT INTO public.clientes(\n"
                    + "	cedula_cliente, cedula_usuario, nombre_cliente, apellido_materno, apellido_paterno, referencia, "
                    + "provincia, parroquia, canton, correo, celular, telefono, fecha_nacimiento,   fecha_registro)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?);");
            consulta.setString(1, c.getCedula_cliente());
            consulta.setString(2, c.getCedula_usuario());
            consulta.setString(3, c.getNombre_cliente());
            consulta.setString(4, c.getApellido_materno());
            consulta.setString(5, c.getApellido_paterno());
            consulta.setString(6, c.getReferencia());
            consulta.setString(7, c.getProvincia());
            consulta.setString(8, c.getParroquia());
            consulta.setString(9, c.getCanton());
            consulta.setString(10, c.getCorreo());
            consulta.setString(11, c.getCelular());
            consulta.setString(12, c.getTelefono());

            Date fechaNacimiento = Date.valueOf(c.getFecha_nacimiento());

            consulta.setDate(13, fechaNacimiento);

            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);

            consulta.setDate(14, sqlDate);
            lineas_modi = consulta.executeUpdate();
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public ResultSet InformeDiario() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        ResultSet rs = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
                rs = st.executeQuery(" SELECT ROW_NUMBER() OVER (ORDER BY T.fecha_registro) As N, T.* from ((SELECT cu.fecha_registro,  cl.apellido_paterno || ' ' || cl.apellido_materno || ' ' || cl.nombre_cliente  AS NOMBRES , \n" +
"																  cl.cedula_cliente as cedula , cu.num_papeleta as npapeleta, fecha_realizada as fdeposito , ct.costo ,\n" +
"                    	valor_cancelado as vdeposito, valor_apagar as saldo, 917 as cmingreso, 1111111111111111 as nfactura\n" +
"                    	FROM public.cuotas cu inner join contratos ct on cu.id_contrato=ct.id_contrato inner join clientes cl on cl.cedula_cliente=ct.cedula_cliente\n" +
"						where cu.fecha_realizada= current_date and cu.estado='cancelado'\n" +
"						)\n" +
"						UNION						\n" +
"						(SELECT Fecha_del_contrato as fecha_registro, cl.apellido_paterno || ' ' || cl.apellido_materno || ' ' || cl.nombre_cliente  AS NOMBRES,\n" +
"						cl.cedula_cliente as cedula ,num_papeleta as npapeleta, fecha_del_contrato as fdeposito,   costo, valor_de_entrada as vdeposito, saldo,\n" +
"						917 as cmingreso, 1111111111111111 as nfactura FROM public.contratos INNER join clientes as cl on cl.cedula_cliente=contratos.cedula_cliente\n" +
"						where contratos.fecha_registro= current_date )) as T\n" +
"\n" +
"                        ");

            System.out.println("Consultando...");

        } catch (SQLException ex) {
            System.out.println("Error..."+ex.toString());
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String[][] Inventario() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(id_propiedad) as dimension FROM public.propiedad;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][9];
            rs = st.executeQuery("SELECT id_propiedad, cedula_usuario, titulo_de_propiedad, tipo, puerta, bloque, "
                    + "cara, fecha_registro, estado\n"
                    + "	FROM public.propiedad");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("id_propiedad");
                respuesta[dimension][1] = rs.getString("cedula_usuario");
                respuesta[dimension][2] = rs.getString("titulo_de_propiedad");
                respuesta[dimension][3] = rs.getString("tipo");
                respuesta[dimension][4] = rs.getString("puerta");
                respuesta[dimension][5] = rs.getString("bloque");
                respuesta[dimension][6] = rs.getString("cara");
                respuesta[dimension][7] = rs.getString("fecha_registro");

                respuesta[dimension][8] = rs.getString("estado");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] InventarioDisponible() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(id_propiedad) as dimension FROM public.propiedad where estado='disponible';");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][9];
            rs = st.executeQuery("SELECT id_propiedad, cedula_usuario, titulo_de_propiedad, tipo, puerta, bloque, "
                    + "cara, fecha_registro, estado\n"
                    + "	FROM public.propiedad where estado='disponible'");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("id_propiedad");
                respuesta[dimension][1] = rs.getString("cedula_usuario");
                respuesta[dimension][2] = rs.getString("titulo_de_propiedad");
                respuesta[dimension][3] = rs.getString("tipo");
                respuesta[dimension][4] = rs.getString("puerta");
                respuesta[dimension][5] = rs.getString("bloque");
                respuesta[dimension][6] = rs.getString("cara");
                respuesta[dimension][7] = rs.getString("fecha_registro");

                respuesta[dimension][8] = rs.getString("estado");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] CarteraVencida() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(item) as dimension FROM cartera_vencida as cv "
                    + "INNER join contratos as co on cv.id_contrato= co.id_contrato INNER join clientes "
                    + "as cl on cl.cedula_cliente= co.cedula_cliente;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][11];
            rs = st.executeQuery("SELECT cl.cedula_cliente, cl.correo , (cl.nombre_cliente || ' ' || cl.apellido_materno|| "
                    + "' ' || cl.apellido_paterno) as nombre_completo , item,  fecha_adquisicion, fecha_ultimo_pago, "
                    + "fecha_transcurrida, valor_adquisicion, valor_cancelado, valor_que_adeuda, valor_vencido "
                    + "FROM cartera_vencida as cv INNER join contratos as co on cv.id_contrato= co.id_contrato INNER "
                    + "join clientes as cl on cl.cedula_cliente= co.cedula_cliente;");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("cedula_cliente");
                respuesta[dimension][1] = rs.getString("nombre_completo");
                respuesta[dimension][2] = rs.getString("item");
                respuesta[dimension][3] = rs.getString("fecha_adquisicion");
                respuesta[dimension][4] = rs.getString("fecha_ultimo_pago");
                respuesta[dimension][5] = rs.getString("fecha_transcurrida");
                respuesta[dimension][6] = rs.getString("valor_adquisicion");
                respuesta[dimension][7] = rs.getString("valor_cancelado");
                respuesta[dimension][8] = rs.getString("valor_que_adeuda");
                respuesta[dimension][9] = rs.getString("valor_vencido");

                respuesta[dimension][10] = rs.getString("correo");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] Usuarios() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(cedula_usuario) as dimension FROM public.usuarios;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][15];
            rs = st.executeQuery("SELECT cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, "
                    + "provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento, "
                    + " clave, correo, fecha_registro\n"
                    + "	FROM public.usuarios;");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("cedula_usuario");
                respuesta[dimension][1] = rs.getString("nombre_usuario");
                respuesta[dimension][2] = rs.getString("apellido_materno");
                respuesta[dimension][3] = rs.getString("apellido_paterno");
                respuesta[dimension][4] = rs.getString("provincia");
                respuesta[dimension][5] = rs.getString("canton");
                respuesta[dimension][6] = rs.getString("parroquia");
                respuesta[dimension][7] = rs.getString("referencia");
                respuesta[dimension][8] = rs.getString("tipo_usuario");
                respuesta[dimension][9] = rs.getString("celular");
                respuesta[dimension][10] = rs.getString("telefono");
                respuesta[dimension][11] = rs.getString("fecha_nacimiento");
                respuesta[dimension][12] = rs.getString("clave");
                respuesta[dimension][12] = decrypt(respuesta[dimension][12]);
                respuesta[dimension][13] = rs.getString("correo");
                respuesta[dimension][14] = rs.getString("fecha_registro");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] ContratosTipos() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(id_tipo_contrato)as dimension FROM public.tipo_contratos;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][7];
            rs = st.executeQuery("SELECT id_tipo_contrato, valor_boveda, valor_entrada, saldo, "
                    + "tipo_de_contrato, cantidad_meses, valor_mes\n"
                    + "	FROM public.tipo_contratos;");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("id_tipo_contrato");
                respuesta[dimension][1] = rs.getString("valor_boveda");
                respuesta[dimension][2] = rs.getString("valor_entrada");
                respuesta[dimension][3] = rs.getString("saldo");
                respuesta[dimension][4] = rs.getString("tipo_de_contrato");
                respuesta[dimension][5] = rs.getString("cantidad_meses");
                respuesta[dimension][6] = rs.getString("valor_mes");

                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] ContratosTipos(String filtro) {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs;
            respuesta = new String[1][7];
            rs = st.executeQuery("SELECT id_tipo_contrato, valor_boveda, valor_entrada, saldo, "
                    + "tipo_de_contrato, cantidad_meses, valor_mes\n"
                    + "	FROM public.tipo_contratos where id_tipo_contrato='" + filtro + "';");
            int dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("id_tipo_contrato");
                respuesta[dimension][1] = rs.getString("valor_boveda");
                respuesta[dimension][2] = rs.getString("valor_entrada");
                respuesta[dimension][3] = rs.getString("saldo");
                respuesta[dimension][4] = rs.getString("tipo_de_contrato");
                respuesta[dimension][5] = rs.getString("cantidad_meses");
                respuesta[dimension][6] = rs.getString("valor_mes");

                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public boolean existeUsuarios(String filtro) {
        boolean respuesta = false;
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(U.cedula_usuario) as dimension FROM public.usuarios as "
                    + "U WHERE U.cedula_usuario='" + filtro + "'");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            if (dimension > 0) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] Usuarios(String filtro) {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(cedula_usuario) as dimension\n"
                    + "FROM public.usuarios\n"
                    + "where cedula_usuario like '%" + filtro + "%';");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][12];
            rs = st.executeQuery("SELECT cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, "
                    + "direccion_pais, direccion_provincia, celular, telefono, fecha_nacimiento, fecha_registro, "
                    + "nombre_us, clave, tipo_usuario\n"
                    + "FROM public.usuarios\n"
                    + "where cedula_usuario like '%" + filtro + "%';");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("cedula_usuario");
                respuesta[dimension][1] = rs.getString("nombre_usuario");
                respuesta[dimension][2] = rs.getString("apellido_materno");
                respuesta[dimension][3] = rs.getString("apellido_paterno");
                respuesta[dimension][4] = rs.getString("direccion_pais");
                respuesta[dimension][5] = rs.getString("direccion_provincia");
                respuesta[dimension][6] = rs.getString("celular");
                respuesta[dimension][7] = rs.getString("telefono");
                respuesta[dimension][8] = rs.getString("fecha_nacimiento");
                respuesta[dimension][9] = rs.getString("fecha_registro");
                respuesta[dimension][9] = rs.getString("nombre_us");
                respuesta[dimension][9] = rs.getString("tipo_usuario");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Usuario UsuarioDetalles(String filtro) throws Exception {
        //String[][] respuesta = new String[0][0];
        Usuario u = new Usuario();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs;
            //Integer dimension = 1;

            //respuesta = new String[dimension][12];
            rs = st.executeQuery("SELECT cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, "
                    + "provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento, "
                    + " clave, correo, fecha_registro\n"
                    + "	FROM public.usuarios "
                    + "where cedula_usuario = '" + filtro + "';");
            //dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {

                String cedula_usuario = rs.getString("cedula_usuario");
                String nombre_usuario = rs.getString("nombre_usuario");
                String apellido_materno = rs.getString("apellido_materno");
                String apellido_paterno = rs.getString("apellido_paterno");
                String provincia = rs.getString("provincia");
                String canton = rs.getString("canton");
                String parroquia = rs.getString("parroquia");
                String referencia = rs.getString("referencia");
                String tipo_usuario = rs.getString("tipo_usuario");
                String celular = rs.getString("celular");
                String telefono = rs.getString("telefono");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");

                String clave = rs.getString("clave");
                clave = decrypt(clave);
                String correo = rs.getString("correo");
                String fecha_registro = rs.getString("fecha_registro");

                u = new Usuario(cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento, clave, correo, fecha_registro, correo);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;

    }

    public Clientes ClienteDetalles(String filtro) {
        //String[][] respuesta = new String[0][0];
        Clientes c = new Clientes();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs;
            //Integer dimension = 1;

            //respuesta = new String[dimension][12];
            rs = st.executeQuery("SELECT cedula_cliente, cedula_usuario, nombre_cliente, "
                    + "apellido_materno, apellido_paterno, referencia, provincia, parroquia, canton, "
                    + "correo, celular, telefono, fecha_nacimiento, fecha_registro\n"
                    + "	FROM public.clientes "
                    + "where cedula_cliente = '" + filtro + "';");
            //dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {

                c.setCedula_usuario(rs.getString("cedula_usuario"));
                c.setNombre_cliente(rs.getString("nombre_cliente"));
                c.setApellido_materno(rs.getString("apellido_materno"));
                c.setApellido_paterno(rs.getString("apellido_paterno"));
                c.setProvincia(rs.getString("provincia"));
                c.setCanton(rs.getString("canton"));
                c.setParroquia(rs.getString("parroquia"));
                c.setReferencia(rs.getString("referencia"));
                // String tipo_usuario = rs.getString("tipo_usuario");
                c.setCelular(rs.getString("celular"));
                c.setTelefono(rs.getString("telefono"));
                c.setFecha_nacimiento(rs.getString("fecha_nacimiento"));

                c.setCorreo(rs.getString("correo"));
                c.setFecha_registro(rs.getString("fecha_registro"));

            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    public String[][] Clientes() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select count(*) as dimension from Clientes;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][16];
            rs = st.executeQuery("SELECT cedula_cliente, cedula_usuario, nombre_cliente, apellido_materno, "
                    + "apellido_paterno, referencia, provincia, parroquia, canton, correo, celular, telefono, "
                    + "fecha_nacimiento,  fecha_registro\n"
                    + "	FROM public.clientes;");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("cedula_cliente");
                respuesta[dimension][1] = rs.getString("cedula_usuario");
                respuesta[dimension][2] = rs.getString("nombre_cliente");
                respuesta[dimension][3] = rs.getString("apellido_materno");
                respuesta[dimension][4] = rs.getString("apellido_paterno");
                respuesta[dimension][5] = rs.getString("referencia");
                respuesta[dimension][6] = rs.getString("provincia");
                respuesta[dimension][7] = rs.getString("parroquia");
                respuesta[dimension][8] = rs.getString("canton");
                respuesta[dimension][9] = rs.getString("correo");
                respuesta[dimension][10] = rs.getString("celular");
                respuesta[dimension][11] = rs.getString("telefono");
                respuesta[dimension][12] = rs.getString("fecha_nacimiento");

                respuesta[dimension][13] = rs.getString("fecha_registro");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    
    public List<Clientes> otherClientes(String cedula) {
        List<Clientes> r=new ArrayList<>();;
        Statement st = null;
        Connection conectar;
         
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs ;
            rs = st.executeQuery("SELECT cedula_cliente, cedula_usuario, nombre_cliente, apellido_materno, "
                    + "apellido_paterno, referencia, provincia, parroquia, canton, correo, celular, telefono, "
                    + "fecha_nacimiento,  fecha_registro\n"
                    + "	FROM public.clientes where cedula_cliente!='"+cedula+"';");
            
            System.out.println("Consultando...");
            while (rs.next()) {
                String cedula_cliente = rs.getString("cedula_cliente");
                String cedula_usuario = rs.getString("cedula_usuario");
                String nombre_cliente = rs.getString("nombre_cliente");
                String apellido_paterno = rs.getString("apellido_materno");
                String apellido_materno = rs.getString("apellido_paterno");
                String referencia = rs.getString("referencia");
                String provincia = rs.getString("provincia");
                String parroquia = rs.getString("parroquia");
                String canton = rs.getString("canton");
                String correo = rs.getString("correo");
                String celular = rs.getString("celular");
                String telefono = rs.getString("telefono");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                String fecha_registro = rs.getString("fecha_registro");

                Clientes c = new Clientes(cedula_cliente, cedula_usuario, nombre_cliente, apellido_paterno, apellido_materno, referencia, provincia, parroquia, canton, correo, celular, telefono, fecha_nacimiento, fecha_registro);
                r.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Error..."+ex.toString());
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;

    }

    public String[][] Ubicaciones() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(ubicacioncodigo) as dimension FROM public.ubicacion;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][7];
            rs = st.executeQuery("SELECT ubicacioncodigo, provinciacodigo, cantoncodigo, parroquiacodigo, provincianombre, "
                    + "cantonnombre, parroquianombre\n"
                    + "	FROM public.ubicacion;");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("ubicacioncodigo");
                respuesta[dimension][1] = rs.getString("provinciacodigo");
                respuesta[dimension][2] = rs.getString("cantoncodigo");
                respuesta[dimension][3] = rs.getString("parroquiacodigo");
                respuesta[dimension][4] = rs.getString("provincianombre");
                respuesta[dimension][5] = rs.getString("cantonnombre");
                respuesta[dimension][6] = rs.getString("parroquianombre");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] Cantones(String filtro) {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs = st.executeQuery("select count(P.cantoncodigo) as dimension  from\n"
                    + "(SELECT cantoncodigo,  cantonnombre \n"
                    + "	FROM public.ubicacion where provincianombre='" + filtro + "'\n"
                    + "	group by cantoncodigo,  cantonnombre\n"
                    + "	order by cantoncodigo ) as P ; ");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][7];
            rs = st.executeQuery("SELECT cantoncodigo,  cantonnombre \n"
                    + "	FROM public.ubicacion where provincianombre='" + filtro + "'\n"
                    + "	group by cantoncodigo,  cantonnombre\n"
                    + "	order by cantoncodigo");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = "";
                respuesta[dimension][1] = "";
                respuesta[dimension][2] = rs.getString("cantoncodigo");
                respuesta[dimension][3] = "";
                respuesta[dimension][4] = "";
                respuesta[dimension][5] = rs.getString("cantonnombre");
                respuesta[dimension][6] = "";
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] Parroquias(String filtro1, String filtro2) {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs = st.executeQuery("SELECT count(ubicacioncodigo) as dimension\n"
                    + "	FROM public.ubicacion where  cantonnombre='" + filtro1 + "' and ubicacion.provincianombre='" + filtro2 + "'");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][7];
            rs = st.executeQuery("SELECT ubicacioncodigo, parroquiacodigo, parroquianombre\n"
                    + "	FROM public.ubicacion where cantonnombre='" + filtro1 + "' and ubicacion.provincianombre='" + filtro2 + "'");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = rs.getString("ubicacioncodigo");
                respuesta[dimension][1] = "";
                respuesta[dimension][2] = "";
                respuesta[dimension][3] = rs.getString("parroquiacodigo");
                respuesta[dimension][4] = "";
                respuesta[dimension][5] = "";
                respuesta[dimension][6] = rs.getString("parroquianombre");
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public List<Contrato> Contratos() {
        List<Contrato> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("Select contratos.id_contrato, numero_contrato, id_propiedad, cedula_cliente, cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo, fecha_registro, \"Descripcion_contrato\", \"Valor_total\", \"Cantidad_meses\", \"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", \"Apellidos_difunto\", \"Fecha_defuncion\", \"Direccion_difunto\", num_papeleta, 'sin cambios' as estado from contratos where contratos.id_contrato not in (select renovacion.id_contrato from renovacion)\n"
                    + "	UNION Select contratos.id_contrato, contratos.numero_contrato, contratos.id_propiedad, renovacion.cedula_cliente, renovacion.cedula_usuario, renovacion.fecha_del_contrato, renovacion.valor_de_entrada, renovacion.\"Valor_total\", renovacion.saldo, renovacion.fecha_del_contrato, renovacion.\"Descripcion_contrato\", renovacion.\"Valor_total\", renovacion.\"Cantidad_meses\", renovacion.\"Valor_por_mes\", renovacion.\"Cedula_difunto\", renovacion.\"Nombres_difunto\", renovacion.\"Apellidos_difunto\", renovacion.\"Fecha_defuncion\", renovacion.\"Direccion_difunto\", renovacion.num_papeleta , 'renovado' as estado from renovacion inner join contratos on renovacion.id_contrato =contratos.id_contrato\n"
                    + "	");
            Contrato c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_contrato = rs.getString("id_contrato");
                String numero_contrato = rs.getString("numero_contrato");
                String papeleta = rs.getString("num_papeleta");
                String propiedad = rs.getString("id_propiedad");
                String cedula_cliente = rs.getString("cedula_cliente");
                String cedula_user = rs.getString("cedula_usuario");
                String fecha_del_contrato = rs.getString("fecha_del_contrato");
                String valor_de_entrada = rs.getString("valor_de_entrada");
                String descrip_tipo = rs.getString("Descripcion_contrato");
                String valor_propiedad = rs.getString("Valor_total");
                String entrada = rs.getString("valor_de_entrada");
                String saldo = rs.getString("id_contrato");
                String cantidad_meses = rs.getString("Cantidad_meses");
                String valor_por_mes = rs.getString("Valor_por_mes");

                String nombres_difunto = rs.getString("Nombres_difunto");
                String apellidos_difunto = rs.getString("Apellidos_difunto");
                String cedula_difunto = rs.getString("Apellidos_difunto");
                String fecha_defuncion = rs.getString("Fecha_defuncion");
                String direccion_difunto = rs.getString("Direccion_difunto");
                c = new Contrato(id_contrato, numero_contrato, papeleta, propiedad, cedula_cliente, cedula_user, fecha_del_contrato, valor_de_entrada, descrip_tipo, valor_propiedad, entrada, saldo, cantidad_meses, valor_por_mes, nombres_difunto, apellidos_difunto, cedula_difunto, fecha_defuncion, direccion_difunto);
                c.setEstado(rs.getString("estado"));
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    
    
    public List<Contrato> ContratosAtrasados() {
        List<Contrato> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT  Contr.id_contrato, Contr.numero_contrato, Contr.id_propiedad, \n" +
"Contr.cedula_cliente, Contr.cedula_usuario, Contr.fecha_del_contrato, \n" +
"Contr.valor_de_entrada, Contr.\"Valor_total\", Contr.saldo, Contr.fecha_del_contrato, \n" +
"Contr.\"Descripcion_contrato\", Contr.\"Valor_total\", Contr.\"Cantidad_meses\", Contr.\"Valor_por_mes\", \n" +
"Contr.\"Cedula_difunto\", Contr.\"Nombres_difunto\", Contr.\"Apellidos_difunto\", Contr.\"Fecha_defuncion\", \n" +
"Contr.\"Direccion_difunto\", Contr.num_papeleta , Contr.estado\n" +
"FROM public.cuotas as Cuo INNER join (Select contratos.id_contrato, numero_contrato, id_propiedad, cedula_cliente, cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo, fecha_registro, \"Descripcion_contrato\", \"Valor_total\", \"Cantidad_meses\", \"Valor_por_mes\", \"Cedula_difunto\", \n" +
"\"Nombres_difunto\", \"Apellidos_difunto\", \"Fecha_defuncion\", \"Direccion_difunto\", \n" +
"num_papeleta, 'sin cambios' as estado from contratos where contratos.id_contrato not in (select renovacion.id_contrato from renovacion)\n" +
"UNION Select contratos.id_contrato, contratos.numero_contrato, contratos.id_propiedad, renovacion.cedula_cliente, renovacion.cedula_usuario, renovacion.fecha_del_contrato, renovacion.valor_de_entrada, renovacion.\"Valor_total\", renovacion.saldo, renovacion.fecha_del_contrato, renovacion.\"Descripcion_contrato\", renovacion.\"Valor_total\", renovacion.\"Cantidad_meses\", renovacion.\"Valor_por_mes\", renovacion.\"Cedula_difunto\", renovacion.\"Nombres_difunto\", renovacion.\"Apellidos_difunto\", renovacion.\"Fecha_defuncion\", renovacion.\"Direccion_difunto\", renovacion.num_papeleta , 'renovado' as estado from renovacion inner join contratos on renovacion.id_contrato =contratos.id_contrato)\n" +
"as Contr On Cuo.id_contrato=Contr.id_contrato\n" +
"where Cuo.estado='deuda' and Cuo.fecha_programada<now ()\n" +
"group by Contr.id_contrato, Contr.numero_contrato, Contr.id_propiedad, \n" +
"Contr.cedula_cliente, Contr.cedula_usuario, Contr.fecha_del_contrato, \n" +
"Contr.valor_de_entrada, Contr.\"Valor_total\", Contr.saldo, Contr.fecha_del_contrato, \n" +
"Contr.\"Descripcion_contrato\", Contr.\"Valor_total\", Contr.\"Cantidad_meses\", Contr.\"Valor_por_mes\", \n" +
"Contr.\"Cedula_difunto\", Contr.\"Nombres_difunto\", Contr.\"Apellidos_difunto\", Contr.\"Fecha_defuncion\", \n" +
"Contr.\"Direccion_difunto\", Contr.num_papeleta , Contr.estado;\n" +
"\n" +
"\n" +
"\n" +
"                   ");
            Contrato c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_contrato = rs.getString("id_contrato");
                String numero_contrato = rs.getString("numero_contrato");
                String papeleta = rs.getString("num_papeleta");
                String propiedad = rs.getString("id_propiedad");
                String cedula_cliente = rs.getString("cedula_cliente");
                String cedula_user = rs.getString("cedula_usuario");
                String fecha_del_contrato = rs.getString("fecha_del_contrato");
                String valor_de_entrada = rs.getString("valor_de_entrada");
                String descrip_tipo = rs.getString("Descripcion_contrato");
                String valor_propiedad = rs.getString("Valor_total");
                String entrada = rs.getString("valor_de_entrada");
                String saldo = rs.getString("id_contrato");
                String cantidad_meses = rs.getString("Cantidad_meses");
                String valor_por_mes = rs.getString("Valor_por_mes");

                String nombres_difunto = rs.getString("Nombres_difunto");
                String apellidos_difunto = rs.getString("Apellidos_difunto");
                String cedula_difunto = rs.getString("Apellidos_difunto");
                String fecha_defuncion = rs.getString("Fecha_defuncion");
                String direccion_difunto = rs.getString("Direccion_difunto");
                c = new Contrato(id_contrato, numero_contrato, papeleta, propiedad, cedula_cliente, cedula_user, fecha_del_contrato, valor_de_entrada, descrip_tipo, valor_propiedad, entrada, saldo, cantidad_meses, valor_por_mes, nombres_difunto, apellidos_difunto, cedula_difunto, fecha_defuncion, direccion_difunto);
                c.setEstado(rs.getString("estado"));
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    
    
    public List<Cesion_derechos> CesioneDeDerechos() {
        List<Cesion_derechos> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT id_contrato, numero_contrato, id_propiedad,CL.cedula_cliente, "
                    + "( CL.nombre_cliente|| ' ' || Cl.apellido_paterno || ' ' || Cl.apellido_materno) AS Nombre_Completo, "
                    + "Co.cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo,"
                    + " Co.fecha_registro, \"Descripcion_contrato\", \"Valor_total\", \"Cantidad_meses\", \"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", \"Apellidos_difunto\" \n" +
"	FROM public.contratos as Co INNER join public.clientes as Cl on Co.cedula_cliente=Cl.cedula_cliente;");
            Cesion_derechos c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_contrato = rs.getString("id_contrato");
                String numero_contrato = rs.getString("numero_contrato");
                String propiedad = rs.getString("id_propiedad");
                String cedula_cliente = rs.getString("cedula_cliente");
                String Nombre_Completo = rs.getString("Nombre_Completo");
                String cedula_user = rs.getString("cedula_usuario");
                String fecha_del_contrato = rs.getString("fecha_del_contrato");
                String valor_de_entrada = rs.getString("valor_de_entrada");
                String descrip_tipo = rs.getString("Descripcion_contrato");
                String valor_propiedad = rs.getString("Valor_total");
                String entrada = rs.getString("valor_de_entrada");
                String saldo = rs.getString("saldo");
                String cantidad_meses = rs.getString("Cantidad_meses");
                String valor_por_mes = rs.getString("Valor_por_mes");
                String nombres_difunto = rs.getString("Nombres_difunto");
                String apellidos_difunto = rs.getString("Apellidos_difunto");
                String cedula_difunto = rs.getString("Cedula_difunto");
                c = new Cesion_derechos(id_contrato, numero_contrato, propiedad, cedula_cliente, Nombre_Completo, cedula_user, fecha_del_contrato, valor_de_entrada, descrip_tipo, valor_propiedad, entrada, saldo, cantidad_meses, valor_por_mes, nombres_difunto, apellidos_difunto, cedula_difunto);
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Erro..."+ex.toString());
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public List<Contrato> ContratosARenovar() {
        List<Contrato> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;
            Calendar aux;
            aux = Calendar.getInstance();
            aux.add(Calendar.MONTH, -1);
            PreparedStatement consulta;

            consulta = conectar.prepareStatement("Select contratos.id_contrato, numero_contrato, id_propiedad, "
                    + "cedula_cliente, cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo, "
                    + "fecha_registro, \"Descripcion_contrato\", \"Valor_total\", \"Cantidad_meses\", "
                    + "\"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", \"Apellidos_difunto\", "
                    + "\"Fecha_defuncion\", \"Direccion_difunto\", num_papeleta from contratos "
                    + "where contratos.id_contrato not in (select renovacion.id_contrato from renovacion) and contratos.fecha_del_contrato >= ? ");
            consulta.setDate(1, new java.sql.Date(aux.getTimeInMillis()));
            rs = consulta.executeQuery();
            Contrato c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_contrato = rs.getString("id_contrato");
                String numero_contrato = rs.getString("numero_contrato");
                String papeleta = rs.getString("num_papeleta");
                String propiedad = rs.getString("id_propiedad");
                String cedula_cliente = rs.getString("cedula_cliente");
                String cedula_user = rs.getString("cedula_usuario");
                String fecha_del_contrato = rs.getString("fecha_del_contrato");
                String valor_de_entrada = rs.getString("valor_de_entrada");
                String descrip_tipo = rs.getString("Descripcion_contrato");
                String valor_propiedad = rs.getString("Valor_total");
                String entrada = rs.getString("valor_de_entrada");
                String saldo = rs.getString("id_contrato");
                String cantidad_meses = rs.getString("Cantidad_meses");
                String valor_por_mes = rs.getString("Valor_por_mes");

                String nombres_difunto = rs.getString("Nombres_difunto");
                String apellidos_difunto = rs.getString("id_contrato");
                String cedula_difunto = rs.getString("Apellidos_difunto");
                String fecha_defuncion = rs.getString("Fecha_defuncion");
                String direccion_difunto = rs.getString("Direccion_difunto");
                c = new Contrato(id_contrato, numero_contrato, papeleta, propiedad, cedula_cliente, cedula_user, fecha_del_contrato, valor_de_entrada, descrip_tipo, valor_propiedad, entrada, saldo, cantidad_meses, valor_por_mes, nombres_difunto, apellidos_difunto, cedula_difunto, fecha_defuncion, direccion_difunto);
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            respuesta = null;

            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Contrato ContratosARenovar(String filtro) {
        Contrato respuesta = null;
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            PreparedStatement consulta;
            consulta = conectar.prepareStatement("SELECT id_contrato, numero_contrato, id_propiedad, cedula_cliente, \n"
                    + "cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo, \n"
                    + "fecha_registro, \"Descripcion_contrato\", \"Valor_total\", \"Cantidad_meses\", \"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", \"Apellidos_difunto\", \"Fecha_defuncion\", \"Direccion_difunto\", num_papeleta\n"
                    + "	FROM public.contratos where id_contrato = " + filtro + ";");
            rs = consulta.executeQuery();
            Contrato c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_contrato = rs.getString("id_contrato");
                String numero_contrato = rs.getString("numero_contrato");
                String papeleta = rs.getString("num_papeleta");
                String propiedad = rs.getString("id_propiedad");
                String cedula_cliente = rs.getString("cedula_cliente");
                String cedula_user = rs.getString("cedula_usuario");
                String fecha_del_contrato = rs.getString("fecha_del_contrato");
                String valor_de_entrada = rs.getString("valor_de_entrada");
                String descrip_tipo = rs.getString("Descripcion_contrato");
                String valor_propiedad = rs.getString("Valor_total");
                String entrada = rs.getString("valor_de_entrada");
                String saldo = rs.getString("id_contrato");
                String cantidad_meses = rs.getString("Cantidad_meses");
                String valor_por_mes = rs.getString("Valor_por_mes");

                String nombres_difunto = rs.getString("Nombres_difunto");
                String apellidos_difunto = rs.getString("Apellidos_difunto");
                String cedula_difunto = rs.getString("Apellidos_difunto");
                String fecha_defuncion = rs.getString("Fecha_defuncion");
                String direccion_difunto = rs.getString("Direccion_difunto");
                respuesta = new Contrato(id_contrato, numero_contrato, papeleta, propiedad, cedula_cliente, cedula_user, fecha_del_contrato, valor_de_entrada, descrip_tipo, valor_propiedad, entrada, saldo, cantidad_meses, valor_por_mes, nombres_difunto, apellidos_difunto, cedula_difunto, fecha_defuncion, direccion_difunto);

            }

        } catch (SQLException ex) {
            respuesta = null;

            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public List<Cuotas> Cuotas() {
        List<Cuotas> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT id_cuota, id_contrato, fecha_registro, estado, fecha_programada, "
                    + "fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota\n"
                    + "	FROM public.cuotas where estado!='renovado' ;");
            Cuotas c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_cuota = rs.getString("id_cuota");;
                String id_contrato = rs.getString("id_contrato");;
                String fecha_registro = rs.getString("fecha_registro");;
                String estado = rs.getString("estado");;
                String fecha_programada = rs.getString("fecha_programada");;
                String fecha_realizada = rs.getString("fecha_realizada");;
                String num_papeleta = rs.getString("num_papeleta");;
                String valor_apagar = rs.getString("valor_apagar");;
                String valor_cancelado = rs.getString("valor_cancelado");;
                String numero_cuota = rs.getString("numero_cuota");;

                c = new Cuotas(id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota);
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    
    public List<Cuotas> CuotasAtrasadas() {
        List<Cuotas> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota\n" +
"	FROM public.cuotas where estado='deuda' and fecha_programada<now ();");
            Cuotas c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_cuota = rs.getString("id_cuota");;
                String id_contrato = rs.getString("id_contrato");;
                String fecha_registro = rs.getString("fecha_registro");;
                String estado = rs.getString("estado");;
                String fecha_programada = rs.getString("fecha_programada");;
                String fecha_realizada = rs.getString("fecha_realizada");;
                String num_papeleta = rs.getString("num_papeleta");;
                String valor_apagar = rs.getString("valor_apagar");;
                String valor_cancelado = rs.getString("valor_cancelado");;
                String numero_cuota = rs.getString("numero_cuota");;

                c = new Cuotas(id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota);
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public List<Cuotas> Cuotas(String id) {
        List<Cuotas> respuesta = new ArrayList<>();
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT id_cuota, id_contrato, fecha_registro, estado, fecha_programada, "
                    + "fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota\n"
                    + "	FROM public.cuotas where id_contrato=" + id + " and  estado!='renovado';");
            Cuotas c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_cuota = rs.getString("id_cuota");;
                String id_contrato = rs.getString("id_contrato");;
                String fecha_registro = rs.getString("fecha_registro");;
                String estado = rs.getString("estado");;
                String fecha_programada = rs.getString("fecha_programada");;
                String fecha_realizada = rs.getString("fecha_realizada");;
                String num_papeleta = rs.getString("num_papeleta");;
                String valor_apagar = rs.getString("valor_apagar");;
                String valor_cancelado = rs.getString("valor_cancelado");;
                String numero_cuota = rs.getString("numero_cuota");;

                c = new Cuotas(id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota);
                respuesta.add(c);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Cuotas DetalleCuota(String id) {
        Cuotas respuesta = null;
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;

            rs = st.executeQuery("SELECT id_cuota, id_contrato, fecha_registro, estado, fecha_programada, "
                    + "fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota\n"
                    + "	FROM public.cuotas where id_cuota=" + id + ";");
            Cuotas c;
            System.out.println("Consultando...");
            while (rs.next()) {

                String id_cuota = rs.getString("id_cuota");;
                String id_contrato = rs.getString("id_contrato");;
                String fecha_registro = rs.getString("fecha_registro");;
                String estado = rs.getString("estado");;
                String fecha_programada = rs.getString("fecha_programada");;
                String fecha_realizada = rs.getString("fecha_realizada");;
                String num_papeleta = rs.getString("num_papeleta");;
                String valor_apagar = rs.getString("valor_apagar");;
                String valor_cancelado = rs.getString("valor_cancelado");;
                String numero_cuota = rs.getString("numero_cuota");;

                respuesta = new Cuotas(id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota);

            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Boolean CobrarCuota(String id, String papeleta, String valor) {
        Boolean respuesta = false;
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();

            ResultSet rs;
            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("UPDATE public.cuotas SET  estado=?, fecha_realizada=?, "
                    + "num_papeleta=?, valor_cancelado=? WHERE id_cuota=?;");
            consulta.setString(1, "cancelado");
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            consulta.setDate(2, sqlDate);
            consulta.setString(3, papeleta);
            consulta.setDouble(4, Double.valueOf(valor));
            consulta.setInt(5, Integer.valueOf(id));
            consulta.execute();

            respuesta = true;

        } catch (SQLException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public String[][] Provincias() {
        String[][] respuesta = new String[0][0];
        Statement st = null;
        Connection conectar;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("select count(P.provinciacodigo) as dimension  from\n"
                    + "(SELECT provinciacodigo,  provincianombre \n"
                    + "	FROM public.ubicacion\n"
                    + "	group by provinciacodigo,  provincianombre\n"
                    + "	order by provinciacodigo ) as P ;");
            Integer dimension = 0;
            while (rs.next()) {
                dimension = Integer.parseInt(rs.getString("dimension"));
            }
            respuesta = new String[dimension][7];
            rs = st.executeQuery("SELECT provinciacodigo,  provincianombre \n"
                    + "	FROM public.ubicacion\n"
                    + "	group by provinciacodigo,  provincianombre\n"
                    + "	order by provinciacodigo");
            dimension = 0;
            System.out.println("Consultando...");
            while (rs.next()) {
                respuesta[dimension][0] = "";
                respuesta[dimension][1] = rs.getString("provinciacodigo");
                respuesta[dimension][2] = "";
                respuesta[dimension][3] = "";
                respuesta[dimension][4] = rs.getString("provincianombre");
                respuesta[dimension][5] = "";
                respuesta[dimension][6] = "";
                dimension += 1;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    public Boolean Insert_Usuarios(Usuario u) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("INSERT INTO public.usuarios(\n"
                    + "cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, provincia, canton, parroquia, "
                    + "referencia, tipo_usuario, celular, telefono, fecha_nacimiento,  clave, correo, fecha_registro)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            consulta.setString(1, u.getCedula_usuario());
            consulta.setString(2, u.getNombre_usuario());
            consulta.setString(3, u.getApellido_materno());
            consulta.setString(4, u.getApellido_paterno());
            consulta.setString(5, u.getProvincia());
            consulta.setString(6, u.getCanton());
            consulta.setString(7, u.getParroquia());
            consulta.setString(8, u.getReferencia());
            consulta.setString(9, u.getTipo_usuario());
            consulta.setString(10, u.getCelular());
            consulta.setString(11, u.getTelefono());

            Date fechaNacimiento = Date.valueOf(u.getFecha_nacimiento());

            consulta.setDate(12, fechaNacimiento);
            consulta.setString(13, u.getClave());
            consulta.setString(14, u.getCorreo());

            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);

            consulta.setDate(15, sqlDate);
            lineas_modi = consulta.executeUpdate();
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Update_Usuarios(Usuario u) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("UPDATE public.usuarios\n"
                    + "	SET  nombre_usuario=?, apellido_materno=?, apellido_paterno=?, "
                    + "provincia=?, canton=?, parroquia=?, referencia=?, tipo_usuario=?, celular=?, telefono=?, "
                    + "fecha_nacimiento=?, correo=? ,clave=? \n"
                    + "	WHERE cedula_usuario=?;");

            consulta.setString(1, u.getNombre_usuario());
            consulta.setString(2, u.getApellido_materno());
            consulta.setString(3, u.getApellido_paterno());
            consulta.setString(4, u.getProvincia());
            consulta.setString(5, u.getCanton());
            consulta.setString(6, u.getParroquia());
            consulta.setString(7, u.getReferencia());
            consulta.setString(8, u.getTipo_usuario());
            consulta.setString(9, u.getCelular());
            consulta.setString(10, u.getTelefono());

            Date fechaNacimiento = Date.valueOf(u.getFecha_nacimiento());

            consulta.setDate(11, fechaNacimiento);
            consulta.setString(12, u.getCorreo());
            consulta.setString(13, u.getClave());
            consulta.setString(14, u.getCedula_usuario());
            lineas_modi = consulta.executeUpdate();
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Update_Clientes(Clientes u) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("UPDATE public.clientes\n"
                    + "	SET   nombre_cliente=?, apellido_materno=?, "
                    + "apellido_paterno=?, referencia=?, provincia=?, parroquia=?, canton=?, correo=?, celular=?, "
                    + "telefono=?, fecha_nacimiento=? "
                    + "	WHERE cedula_cliente=?;");

            consulta.setString(1, u.getNombre_cliente());
            consulta.setString(2, u.getApellido_materno());
            consulta.setString(3, u.getApellido_paterno());
            consulta.setString(4, u.getReferencia());
            consulta.setString(5, u.getProvincia());
            consulta.setString(6, u.getCanton());
            consulta.setString(7, u.getParroquia());
            consulta.setString(8, u.getCorreo());
            consulta.setString(9, u.getCelular());
            consulta.setString(10, u.getTelefono());
            Date fechaNacimiento = Date.valueOf(u.getFecha_nacimiento());

            consulta.setDate(11, fechaNacimiento);
            consulta.setString(12, u.getCedula_cliente());
            lineas_modi = consulta.executeUpdate();
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Delete_Usuarios(String cedula) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            consulta = conectar.prepareStatement("DELETE FROM public.usuarios WHERE cedula_usuario=?;");
            consulta.setString(1, cedula);
            lineas_modi = consulta.executeUpdate();
            if (lineas_modi > 0) {

                System.out.println("Los datos se guardaron correctamente");
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Delete_Clientes(String cedula) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            consulta = conectar.prepareStatement("DELETE FROM public.clientes WHERE cedula_cliente=?;");
            consulta.setString(1, cedula);
            lineas_modi = consulta.executeUpdate();
            if (lineas_modi > 0) {

                System.out.println("Los datos se guardaron correctamente");
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Delete_Inventario(String id_propiedad) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            consulta = conectar.prepareStatement("DELETE FROM public.propiedad WHERE id_propiedad=?;");
            int id = Integer.parseInt(id_propiedad);
            consulta.setInt(1, id);
            lineas_modi = consulta.executeUpdate();
            if (lineas_modi > 0) {

                System.out.println("Los datos se guardaron correctamente");
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Delete_TipoContrato(String id_propiedad) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            consulta = conectar.prepareStatement("DELETE FROM public.tipo_contratos WHERE id_tipo_contrato=?;");
            int id = Integer.parseInt(id_propiedad);
            consulta.setInt(1, id);
            lineas_modi = consulta.executeUpdate();
            if (lineas_modi > 0) {

                System.out.println("Los datos se guardaron correctamente");
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Insert_TipoContrato(Contratos_tipos c) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            consulta = conectar.prepareStatement("INSERT INTO public.tipo_contratos(valor_boveda, valor_entrada, "
                    + "saldo, tipo_de_contrato, cantidad_meses, valor_mes) VALUES ( ?, ?, ?, ?, ?, ?);");
            Double valor_boveda = Double.parseDouble(c.getValor_boveda().replace(",", "."));
            consulta.setDouble(1, valor_boveda);
            Double valor_entrada = Double.parseDouble(c.getValor_entrada().replace(",", "."));
            consulta.setDouble(2, valor_entrada);
            Double saldo = Double.parseDouble(c.getSaldo().replace(",", "."));
            consulta.setDouble(3, saldo);
            consulta.setString(4, c.getTipo_de_contrato());
            int catidad_meses = Integer.parseInt(c.getCantidad_meses());
            consulta.setInt(5, catidad_meses);
            Double valor_mes = Double.parseDouble(c.getValor_mes().replace(",", "."));
            consulta.setDouble(6, valor_mes);

            lineas_modi = consulta.executeUpdate();
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Insert_Contrato(String papeleta, String propiedad, String cedula_cliente, String cedula_user, String tipo_contrato, String valor_propiedad, String entrada,
            String cantidad_meses, String valor_por_mes, String tipo_propiedad, String bloque, String puerta, String cara, String nombres_difunto,
            String apellidos_difunto, String cedula_difunto, String fecha_defuncion, String direccion_difunto) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);

            PreparedStatement consulta;
            st = conectar.createStatement();

            ResultSet rs = st.executeQuery("SELECT max(id_contrato) as idContrato	FROM public.contratos;");
            Integer idContrato = 1;
            while (rs.next()) {
                try {
                    String aux = rs.getString("idContrato");
                    if (aux != null) {
                        idContrato = Integer.parseInt(aux) + 1;
                    }
                } catch (SQLException exception) {

                    // idPropiedad = 1;
                }
            }
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            
            //hay que borar esto 
            Calendar aux2 = Calendar.getInstance();
            aux2.add(Calendar.MONTH, -3);
            //
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            consulta = conectar.prepareStatement("INSERT INTO public.contratos(\n"
                    + "	id_contrato, numero_contrato, id_propiedad, cedula_cliente, cedula_usuario, "
                    + "fecha_del_contrato, "
                    + "valor_de_entrada, costo, saldo, \"Descripcion_contrato\", "
                    + "\"Valor_total\", "
                    + "\"Cantidad_meses\", \"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", "
                    + "\"Apellidos_difunto\", "
                    + "\"Fecha_defuncion\", \"Direccion_difunto\" ,num_papeleta, fecha_registro)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            consulta.setInt(1, idContrato);
            String tituloContrato = idContrato + "-CGLE-GADPRLE-C-" + año;
            consulta.setString(2, tituloContrato);
            consulta.setInt(3, Integer.valueOf(propiedad));
            consulta.setString(4, cedula_cliente);
            consulta.setString(5, cedula_user);
            //
            consulta.setDate(6, new java.sql.Date(aux2.getTimeInMillis()));
            //consulta.setDate(6, sqlDate);
            //
            Double valor_entrada = Double.parseDouble(entrada.replace(",", "."));
            consulta.setDouble(7, valor_entrada);
            Double valor_total = Double.parseDouble(valor_propiedad.replace(",", "."));
            consulta.setDouble(8, valor_total);
            Double valor_saldo = valor_total - valor_entrada;
            consulta.setDouble(9, valor_saldo);
            //
            //consulta.setDate(9, sqlDate);
            consulta.setString(10, tipo_contrato);
            consulta.setDouble(11, valor_total);
            consulta.setInt(12, Integer.valueOf(cantidad_meses));
            Double valor_mes = Double.parseDouble(valor_por_mes.replace(",", "."));
            consulta.setDouble(13, valor_mes);
            consulta.setString(14, cedula_difunto);
            consulta.setString(15, nombres_difunto);
            consulta.setString(16, apellidos_difunto);
            consulta.setDate(17, Date.valueOf(fecha_defuncion));
            consulta.setString(18, direccion_difunto);
            consulta.setString(19, papeleta);
            consulta.setDate(20, sqlDate);
            lineas_modi = consulta.executeUpdate();

            //actualizar estado de la propiedad 
            st.execute("UPDATE public.propiedad SET  estado='vendida' WHERE id_propiedad='" + propiedad + "';");

            rs = st.executeQuery("SELECT max(id_cuota) as idCuota FROM public.cuotas;");
            Integer idCouta = 1;
            while (rs.next()) {
                idCouta += 1;
                try {
                    String aux = rs.getString("idCuota");
                    if (aux != null) {
                        idCouta = Integer.parseInt(aux) + 1;
                    }
                } catch (SQLException exception) {

                    // idPropiedad = 1;
                }
            }
            consulta = conectar.prepareStatement("INSERT INTO public.cuotas(\n"
                    + "	id_cuota, id_contrato, fecha_registro, \n"
                    + "	estado, fecha_programada, valor_apagar, \n"
                    + "	numero_cuota)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?);");
            consulta.setInt(2, idContrato);
            consulta.setDate(3, sqlDate);
            consulta.setString(4, "deuda");
            consulta.setDouble(6, valor_mes);
            //calendar nos ayuda a calcular las fechas de las coutas
            Calendar aux;

            for (int i = 1; i <= Integer.valueOf(cantidad_meses); i++) {
                consulta.setInt(1, idCouta);
                aux = Calendar.getInstance();
                aux.add(Calendar.MONTH, i-3);
                consulta.setDate(5, new java.sql.Date(aux.getTimeInMillis()));
                consulta.setInt(7, i);
                idCouta += 1;
                lineas_modi = consulta.executeUpdate();
            }
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean Insert_ContratoRenovacion(Contrato c, String cedula_user, String valor_propiedad, String descrip_tipo, String entrada, String cantidad_meses, String valor_por_mes) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(id_renovacion) as idContrato FROM public.renovacion;");
            Integer idContrato = 1;
            while (rs.next()) {
                try {
                    String aux = rs.getString("idContrato");
                    if (aux != null) {
                        idContrato = Integer.parseInt(aux) + 1;
                    }
                } catch (SQLException exception) {

                    // idPropiedad = 1;
                }
            }
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            consulta = conectar.prepareStatement("INSERT INTO public.renovacion(\n"
                    + "	id_renovacion, id_contrato, cedula_usuario, cedula_cliente, fecha_del_contrato, "
                    + "\"Descripcion_contrato\", \"Valor_total\", saldo, valor_de_entrada, \"Cantidad_meses\", "
                    + "\"Valor_por_mes\", \"Cedula_difunto\", \"Nombres_difunto\", \"Apellidos_difunto\", "
                    + "\"Fecha_defuncion\", \"Direccion_difunto\", num_papeleta, estado)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            consulta.setInt(1, idContrato);
            consulta.setInt(2, Integer.valueOf(c.getId_contrato()));
            consulta.setString(3, cedula_user);
            consulta.setString(4, c.getCedula_cliente());
            consulta.setDate(5, sqlDate);
            consulta.setString(6, descrip_tipo);
            Double valor_total = Double.parseDouble(valor_propiedad.replace(",", "."));
            consulta.setDouble(7, valor_total);
            Double valor_entrada = Double.parseDouble(entrada.replace(",", "."));
            Double valor_saldo = valor_total - valor_entrada;
            consulta.setDouble(8, valor_saldo);
            consulta.setDouble(9, valor_entrada);
            consulta.setInt(10, Integer.valueOf(cantidad_meses));
            Double valor_mes = Double.parseDouble(valor_por_mes.replace(",", "."));
            consulta.setDouble(11, valor_mes);
            consulta.setString(12, c.getCedula_difunto());
            consulta.setString(13, c.getNombres_difunto());
            consulta.setString(14, c.getApellidos_difunto());
            consulta.setDate(15, Date.valueOf(c.getFecha_defuncion()));
            consulta.setString(16, c.getDireccion_difunto());
            consulta.setString(17, c.getPapeleta());
            consulta.setString(18, "Cambio de tipo de contrato");
            lineas_modi = consulta.executeUpdate();

            //actualizar estado de la propiedad 
            //st.execute("UPDATE public.propiedad SET  estado='vendida' WHERE id_propiedad='"+propiedad+"';");
            st.execute("UPDATE public.cuotas SET estado='renovado' WHERE id_contrato=" + c.getId_contrato() + ";");

            rs = st.executeQuery("SELECT max(id_cuota) as idCuota FROM public.cuotas;");
            Integer idCouta = 1;
            while (rs.next()) {
                idCouta += 1;
                try {
                    String aux = rs.getString("idCuota");
                    if (aux != null) {
                        idCouta = Integer.parseInt(aux) + 1;
                    }
                } catch (SQLException exception) {

                    // idPropiedad = 1;
                }
            }
            consulta = conectar.prepareStatement("INSERT INTO public.cuotas(\n"
                    + "	id_cuota, id_contrato, fecha_registro, \n"
                    + "	estado, fecha_programada, valor_apagar, \n"
                    + "	numero_cuota)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?);");
            consulta.setInt(2, Integer.valueOf(c.getId_contrato()));
            consulta.setDate(3, sqlDate);
            consulta.setString(4, "deuda");
            consulta.setDouble(6, valor_mes);
            //calendar nos ayuda a calcular las fechas de las coutas
            Calendar aux;

            for (int i = 1; i <= Integer.valueOf(cantidad_meses); i++) {
                consulta.setInt(1, idCouta);
                aux = Calendar.getInstance();
                aux.add(Calendar.MONTH, i);
                consulta.setDate(5, new java.sql.Date(aux.getTimeInMillis()));
                consulta.setInt(7, i);
                idCouta += 1;
                lineas_modi = consulta.executeUpdate();
            }
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    
    public Boolean Insert_CesionDeDerecho(String id_contrato, String clienteCedente,String clienteNuevo,String cedUser) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            PreparedStatement consulta;
            st = conectar.createStatement();
            ResultSet rs;
            Calendar fecha = new GregorianCalendar();
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            consulta = conectar.prepareStatement("INSERT INTO public.cesion_de_derechos(\n" +
            " id_contrato, cedula_usuario, cedula_cliente, fecha_de_cambio, estado)\n" +
            " VALUES ( ?, ?, ?, ?, ?);");
            consulta.setInt(1,Integer.valueOf(id_contrato));
            consulta.setString(2, cedUser);
            consulta.setString(3, clienteCedente);
            consulta.setDate(4, sqlDate);
            consulta.setString(5, "cliente cedio derecho");
            lineas_modi = consulta.executeUpdate();
            
            st.execute("UPDATE public.contratos SET cedula_cliente='"+clienteNuevo+"' WHERE id_contrato=" + id_contrato+ ";");
            System.out.println("Los datos se guardaron correctamente");
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    
    public Boolean Insert_Propiedad(Propiedad p) throws ParseException {
        Boolean resp = false;
        Statement st = null;
        Connection conectar;
        int lineas_modi = 0;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            conectar = DriverManager.getConnection(BD, usuario, contra);
            st = conectar.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(id_propiedad)+1 as IdPropiedad FROM public.propiedad;");
            Integer idPropiedad = 1;
            while (rs.next()) {
                try {
                    String aux = rs.getString("IdPropiedad");
                    if (aux != null) {
                        idPropiedad = Integer.parseInt(aux);
                    }
                } catch (SQLException exception) {

                    // idPropiedad = 1;
                }
            }

            PreparedStatement consulta;
            int cantCaras = Integer.parseInt(p.getTitulo_de_propiedad());

            for (int i = 0; i < cantCaras; i++) {
                String caraGenerada = p.getCara();
                consulta = conectar.prepareStatement("INSERT INTO public.propiedad(\n"
                        + "   cedula_usuario, titulo_de_propiedad, tipo, puerta, bloque, cara, fecha_registro , "
                        + "id_propiedad, estado)\n"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
                long now = System.currentTimeMillis();
                Date sqlDate = new Date(now);
                consulta.setString(1, p.getCedula_usuario());
                //generar titulo
                String tipoP = "L";
                if ("Boveda".equals(p.getTipo())) {
                    tipoP = "B";

                }

                Calendar fecha = new GregorianCalendar();
                int año = fecha.get(Calendar.YEAR);
                int puerta = Integer.parseInt(p.getPuerta());
                String tituloProiedad = idPropiedad + "-PGLE-GADPRLE-" + tipoP + "-" + año;
                consulta.setString(2, tituloProiedad);
                consulta.setString(3, p.getTipo());
                consulta.setInt(4, puerta);
                int bloque = Integer.parseInt(p.getPuerta());
                consulta.setInt(5, bloque);
                consulta.setString(6, caraGenerada);

                consulta.setDate(7, sqlDate);
                consulta.setInt(8, idPropiedad);
                consulta.setString(9, "disponible");
                idPropiedad += 1;
                lineas_modi = consulta.executeUpdate();

            }
            resp = true;
            System.out.println("Los datos se guardaron correctamente");

        } catch (SQLException ex) {
            System.out.println("Problema al consultar la base de datos ");
            //Logger.getLogger(MethodRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    private static String decrypt(String encrypted) throws Exception {
        byte[] encryptedBytes = Base64.decode(encrypted.replace("\n", ""));

        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }

}
