/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Contrato;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Servlet_ContratoRenoSelect extends HttpServlet {

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
        Coneccion cone;
        try (PrintWriter out = response.getWriter()) {
            cone= new Coneccion();
            Contrato con;
            String id_contrato= request.getParameter("id_contrato");
            con=cone.ContratosARenovar(id_contrato);
            
            
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin_contratos_reno_agg.jsp");
            request.setAttribute("cedula", con.getCedula_cliente());
            request.setAttribute("tipo_contrato", con.getDescrip_tipo());
            request.setAttribute("valor", con.getValor_propiedad());
            request.setAttribute("entrada", con.getEntrada());
            request.setAttribute("cant_meses", con.getCantidad_meses());
            request.setAttribute("val_mes", con.getValor_por_mes());
            request.setAttribute("propiedad", con.getPropiedad());
            request.setAttribute("difunto_nombre", con.getNombres_difunto());
            request.setAttribute("difunto_apellido", con.getApellidos_difunto());
            request.setAttribute("difunto_cedula", con.getCedula_difunto());
            request.setAttribute("difunto_fecha", con.getFecha_defuncion());
            request.setAttribute("id_contrato", con.getId_contrato());
            request.setAttribute("difunto_dir", con.getDireccion_difunto());
            
            dispatcher.forward(request, response);
            
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
