/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Usuario;
import com.itextpdf.text.pdf.codec.Base64;
import static com.sun.xml.ws.security.trust.sts.BaseSTSImpl.ENCRYPT_KEY;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Servlet_UsuarioAgg extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet_UsuarioAgg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_UsuarioAgg at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cedula_usuario = request.getParameter("cedula");

        String nombre_usuario = request.getParameter("nombreUsuario");
        String apellido_materno = request.getParameter("apellidoPaterno");
        String apellido_paterno = request.getParameter("apellidoMaterno");
        String provincia = request.getParameter("productCode");
        String canton = request.getParameter("product");
        String parroquia = request.getParameter("parroquia");
        String referencia = request.getParameter("referencia");
        String tipo_usuario = request.getParameter("tipo_user");
        String celular = request.getParameter("numerocelular");
        String telefono = request.getParameter("telefono");
        String fecha_nacimiento = request.getParameter("fechaNacimiento");
        
        String clave = request.getParameter("contrasenia");
        
        String correo = request.getParameter("correo");
        String fecha_registro = request.getParameter("fechaNacimiento");

        Coneccion cone =  new Coneccion();
        Usuario u = new Usuario(cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento,  clave, correo, fecha_registro, correo);
        Boolean exito;
        PrintWriter out = response.getWriter();
        //exito = cone.existeUsuarios(cedula_usuario);
        try {
            clave=encript(clave);
            u.setClave(clave);
            exito=cone.Insert_Usuarios(u);            
            if (exito) {
                
                out.println("<html><head></head><body onload=\"alert('Se registro correctamente'); window.location='Admin_home.jsp' \"></body></html>");
            } else {
                out.println("<html><head></head><body onload=\"alert('No se registro el usuario'); window.location='Admin_home.jsp'\"></body></html>");
            }

            //si todo sale bien se muestra la lista actulizada
        } catch (ParseException ex) {
            Logger.getLogger(Servlet_UsuarioAgg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Servlet_UsuarioAgg.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static String encript(String text) throws Exception {	
	Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.ENCRYPT_MODE, aesKey);

	byte[] encrypted = cipher.doFinal(text.getBytes());
		
	return Base64.encodeBytes(encrypted);
	}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {

        return "Short description";
    }// </editor-fold>

}
