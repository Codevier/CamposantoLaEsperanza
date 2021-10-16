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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Servlet_UsuarioEliminar extends HttpServlet {

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
            String cedula_user = request.getParameter("cedula");
            Coneccion cone = new Coneccion();
            Boolean exito;
            try {
                String respuesta=null;
                exito = cone.Delete_Usuarios(cedula_user);
                PrintWriter pw = response.getWriter();
                if (exito) {
                    respuesta="Se elimino al usuario con numero de cedula "+cedula_user;
                   
                } else {
                    respuesta="No se puede eliminar, es posible que el usuario este relacionado en otra tabla";
                               
                }
                String[][] Usuarios = cone.Usuarios();
                List<Usuario> listaPropiedades = new ArrayList<>();
                listaPropiedades = Usuario.listarUsuarios(Usuarios);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin_usuario_all.jsp");
                request.setAttribute("lista", listaPropiedades);
                request.setAttribute("message", respuesta);
                dispatcher.include(request, response);

                //si todo sale bien se muestra la lista actulizada
            } catch (ParseException ex) {
                Logger.getLogger(Servlet_UsuarioAgg.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        processRequest(request, response);
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
