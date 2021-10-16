/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Ubicacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Usuario
 */
public class Servlet_UbicacionSeleccionada extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Coneccion cone = null;
        cone= new Coneccion();
        String provCode = req.getParameter("productCode");
        String[][] ubicaciones= cone.Cantones(provCode);
        List<Ubicacion> listaUbicacion = new ArrayList<>();
        listaUbicacion=Ubicacion.listarUbicacion(ubicaciones);
        
        //de la misma manera, crearemos un arreglo json
        JsonArrayBuilder array = Json.createArrayBuilder();

        //esta vez recorremos la lista como lambda
        
        listaUbicacion.stream().map((Ubicacion) -> Json.createObjectBuilder()
                .add("productId", Ubicacion.CantonCodigo)
                .add("purchaseCost", Ubicacion.UbicacionCodigo)
                .add("description", Ubicacion.CantonNombre).build()).forEach((item) -> {
            array.add(item);
        });
                
                 
        resp.setContentType(MediaType.APPLICATION_JSON);//tipo application/json
        try (JsonWriter jsonWriter = Json.createWriter(resp.getOutputStream())) {
            jsonWriter.writeArray(array.build());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

}
