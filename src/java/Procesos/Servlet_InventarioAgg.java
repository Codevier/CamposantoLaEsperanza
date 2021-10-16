/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Propiedad;
import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Servlet_InventarioAgg extends HttpServlet {

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
            out.println("<title>Servlet Servlet_InventarioAgg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_InventarioAgg at " + request.getContextPath() + "</h1>");
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
        String cedula_user=(String) request.getSession().getAttribute("ced_user");

        String puerta = request.getParameter("puerta");
        String bloque = request.getParameter("bloque");
        String tipoInsert = request.getParameter("id_categoria");
        String cara = request.getParameter("cara");
        String numero_caras = request.getParameter("numero_caras");
        
        String tipo_propiedad = request.getParameter("tipo_propiedad");
        Coneccion cone = null;
        cone = new Coneccion();
        Propiedad p = new Propiedad("id_propiedad",cedula_user,numero_caras,tipo_propiedad, puerta, bloque,cara,"","disponible");
        //titulo_propiedad
        Boolean exito;
        PrintWriter out = response.getWriter();
        //exito = cone.existeUsuarios(cedula_usuario);
        try {
            exito=cone.Insert_Propiedad(p);
            if (exito) {
                out.println("<html><head></head><body onload=\"alert('Se registro correctamente'); window.location='Admin_home.jsp' \"></body></html>");
            } else {
                out.println("<html><head></head><body onload=\"alert('No se registro el usuario'); window.location='Admin_home.jsp'\"></body></html>");
            }

            //si todo sale bien se muestra la lista actulizada
        } catch (ParseException ex) {
            Logger.getLogger(Servlet_UsuarioAgg.class.getName()).log(Level.SEVERE, null, ex);
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
