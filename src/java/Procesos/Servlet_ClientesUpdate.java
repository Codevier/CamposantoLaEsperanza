/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Clientes;
import Modelos.Coneccion;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Servlet_ClientesUpdate extends HttpServlet {

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
            out.println("<title>Servlet Servlet_ClientesUpdate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_ClientesUpdate at " + request.getContextPath() + "</h1>");
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
        String cedula_cliente= request.getParameter("cedula");
        String cedula_usuario=  (String) request.getSession().getAttribute("ced_user");
        String nombre_cliente= request.getParameter("nombreCliente");
        String apellido_paterno= request.getParameter("apellidoPaterno");
        String apellido_materno= request.getParameter("apellidoMaterno");
        String referencia=request.getParameter("referencia");
        String provincia=request.getParameter("productCode");
        String canton=request.getParameter("product");
        String parroquia=request.getParameter("parroquia");
        
        String correo=request.getParameter("correo");
        String celular= request.getParameter("celular");
        String telefono= request.getParameter("telefono");
        String fecha_nacimiento= request.getParameter("fechaNacimiento");
       
        String fecha_registro=request.getParameter("fechaRegistro");
	
        Coneccion cone = null;
        Clientes u = new Clientes(  cedula_cliente,cedula_usuario, nombre_cliente,
                        apellido_paterno, apellido_materno, referencia,provincia,parroquia,canton,correo,
                        celular, telefono, fecha_nacimiento, fecha_registro);
       
        cone = new Coneccion();
         
        Boolean exito;
        PrintWriter out = response.getWriter();
        //exito = cone.existeUsuarios(cedula_usuario);
        try {
            exito=cone.Update_Clientes(u);            
            String[][] clientes= cone.Clientes();
            List<Clientes> listaClientes = new ArrayList<>();
            listaClientes=Clientes.listarClientes(clientes);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin_cliente_all.jsp");
            request.setAttribute("lista", listaClientes);
            dispatcher.forward(request, response);

            //si todo sale bien se muestra la lista actulizada
        

            //si todo sale bien se muestra la lista actulizada
        } catch (Exception ex) {
            Logger.getLogger(Servlet_ClientesAgg.class.getName()).log(Level.SEVERE, null, ex);
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
