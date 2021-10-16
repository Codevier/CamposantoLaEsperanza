/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
public class Servlet_ContratoAgg extends HttpServlet {

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
            out.println("<title>Servlet Servlet_ContratoAgg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_ContratoAgg at " + request.getContextPath() + "</h1>");
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
        
        String cedula_cliente = request.getParameter("ced_cliente");
        
        String cedula_user=(String) request.getSession().getAttribute("ced_user");
        String tipo_contrato = request.getParameter("tipo_contrato");
        PrintWriter out = response.getWriter();
        String tipo_propiedad = request.getParameter("tipo_propiedad");
        String bloque = request.getParameter("bloque");
        String puerta = request.getParameter("puerta");
        String cara = request.getParameter("cara");
        String nombres_difunto = request.getParameter("nombres_difunto");
        String apellidos_difunto = request.getParameter("apellidos_difunto");
        String cedula_difunto = request.getParameter("cedula_difunto");
        String fecha_defuncion = request.getParameter("fecha_defuncion");
        String papeleta = request.getParameter("papeleta");
        
        String direccion_difunto = request.getParameter("direccion_difunto");
        String propiedad = request.getParameter("propiedad");
        Coneccion cone = null;
        cone = new Coneccion();
        String[][] tipos= cone.ContratosTipos(tipo_contrato);
        String valor_propiedad = tipos[0][1];
        String descrip_tipo = tipos[0][4];
        String entrada = tipos[0][2];
        String cantidad_meses = tipos[0][5];
        String valor_por_mes = tipos[0][6];
        try {
            Boolean exito=cone.Insert_Contrato(papeleta,propiedad,cedula_cliente, cedula_user, descrip_tipo, valor_propiedad, entrada, cantidad_meses, valor_por_mes, tipo_propiedad, bloque, puerta, cara, nombres_difunto, apellidos_difunto, cedula_difunto, fecha_defuncion, direccion_difunto);
            if (exito) {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin_home.jsp");
                //dispatcher.include(request, response);
                out.println("<html><head></head><body onload=\"alert('Se registro correctamente'); window.location='Admin_home.jsp' \"></body></html>");
            } else {
                out.println("<html><head></head><body onload=\"alert('No se registro correctamente'); window.location='Admin_home.jsp' \"></body></html>");
            
                //out.println("<html><head></head><body onload=\"alert('No se registro el usuario'); window.location='Admin_home.jsp'\"></body></html>");
            }
        } catch (ParseException ex) {
            Logger.getLogger(Servlet_ContratoAgg.class.getName()).log(Level.SEVERE, null, ex);
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
