/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Servlet_UsuarioValidar extends HttpServlet {

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
            out.println("<title>Servlet Servlet_UsuarioValidar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_UsuarioValidar at " + request.getContextPath() + "</h1>");
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
        String cedula_usuario = request.getParameter("cedulaUsuario");
        
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
        
        Coneccion cone = null;
        cone = new Coneccion();
        Usuario u = new Usuario(cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento,  clave, correo, fecha_registro, correo);
        Boolean existe;
        PrintWriter out = response.getWriter();
        //out.println("<script> alert(); </script>");
        existe=cone.existeUsuarios(cedula_usuario);
        if(existe){
            out.println("<html><head></head><body onload=\"alert('El numero de cedula ya existe en la base de datos'); window.location='Admin_user_agg_c.jsp' \"></body></html>");
        }
        else{
            out.println("<html><head></head><body onload=\"alert('Numero de cedula validado correctamente'); window.location='Admin_user_agg.jsp?cedula="+cedula_usuario+"'\"></body></html>");
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
