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
public class Servlet_UbicacionCodigo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        String[][] ubicaciones= cone.Provincias();
        List<Ubicacion> listaUbicacion = new ArrayList<>();
        listaUbicacion=Ubicacion.listarUbicacion(ubicaciones);
        
        //obtenemos la lista de productos
        //debemos preparar la lista a devolver. 
        //Por los actuales estándares, es mejor usar JSON
        JsonArrayBuilder array = Json.createArrayBuilder();//creamos el arreglo json
        //recorremos la lista
        for (Ubicacion Ubicacion : listaUbicacion) {
            JsonObject item = Json.createObjectBuilder()
                    .add("prodCode", Ubicacion.ProvinciaCodigo)
                    .add("discountCode", Ubicacion.ProvinciaCodigo)
                    .add("description", Ubicacion.ProvinciaNombre).build();
            array.add(item);
        }
                /*Lo mismo, pero usando Lambda
                productCodeList.stream().map((productCode) -> Json.createObjectBuilder()
                .add("prodCode", productCode.getProdCode())
                .add("discountCode", productCode.getDiscountCode())
                .add("description", productCode.getDescription()).build()).forEach((item) -> {
            //creamos un objeto json con los campos que necesitamos
            array.add(item); //.. y lo agregamos al arreglo json
        });
         */

        resp.setContentType(MediaType.APPLICATION_JSON); //ahora preparamos la salida json al cliente...
        try (JsonWriter jsonWriter = Json.createWriter(resp.getOutputStream())) { //.. para imprimir... 
            jsonWriter.writeArray(array.build());
        }
    }

}
