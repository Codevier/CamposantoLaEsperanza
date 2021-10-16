/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import com.itextpdf.text.pdf.codec.Base64;
import static com.sun.xml.ws.security.trust.sts.BaseSTSImpl.ENCRYPT_KEY;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class Servlet_Login extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        Coneccion cone;
        
        try (PrintWriter out = response.getWriter()) {
            String cedula = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            clave=encript(clave);
            cone= new Coneccion();
            String resp= cone.login(cedula, clave);
            switch(resp){
                case "error":
                    out.println("<html><head></head><body onload=\"alert('Error'); window.location='Login.jsp' \"></body></html>");
            
                    break;
                case "credenciales incorrectas":
                    out.println("<html><head></head><body onload=\"alert('Credenciales incorrectas'); window.location='Login.jsp' \"></body></html>");
            
                    break;
                case "sin respuesta":
                    out.println("<html><head></head><body onload=\"alert('Sin respuesta'); window.location='Login.jsp' \"></body></html>");
            
                    break;
                case "Encargado":
                    out.println("<html><head></head><body onload=\"alert('Aun no esta el perfil del encargado'); window.location='Login.jsp' \"></body></html>");          
                    break;
                case "Administrador":
                    
                    request.getSession().setAttribute("ced_user", cedula);
                    String nombre=cone.nombreUser(cedula);
                    request.getSession().setAttribute("nom_user", nombre);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin_home.jsp");
                    request.setAttribute("nombre_user", resp);
                    dispatcher.forward(request, response);
                    break;
                default:
                    
                    out.println("<html><head></head><body onload=\"alert('No se pudo ingresar'); window.location='Login.jsp' \"></body></html>");
                    break;
        }
            
            /* TODO output your page here. You may use following sample code. */
            
        }
    }
    
    private static String encript(String text) throws Exception {	
	Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.ENCRYPT_MODE, aesKey);

	byte[] encrypted = cipher.doFinal(text.getBytes());
		
	return Base64.encodeBytes(encrypted);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
