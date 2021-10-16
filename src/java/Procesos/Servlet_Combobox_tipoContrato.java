/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Contratos_tipos;
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
public class Servlet_Combobox_tipoContrato extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param resp
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        //processRequest(request, response);
        Coneccion cone = null;
        cone= new Coneccion();
        String[][] tipos= cone.ContratosTipos();
        List<Contratos_tipos> listaContratos_tipos = new ArrayList<>();
        
        listaContratos_tipos=Contratos_tipos.listarTiposContratos(tipos) ;
        JsonArrayBuilder array = Json.createArrayBuilder();
        for (Contratos_tipos contratos_tipos : listaContratos_tipos) {
            JsonObject item = Json.createObjectBuilder()
                    .add("valorBoveda", contratos_tipos.valor_boveda)
                    .add("valor_mes", contratos_tipos.valor_mes)
                    .add("description", contratos_tipos.tipo_de_contrato)
                    .add("id_tipo_contrato", contratos_tipos.id_tipo_contrato)
                    .add("saldo", contratos_tipos.saldo)
                    .add("cantidad_meses", contratos_tipos.cantidad_meses)
                    .add("entrada", contratos_tipos.valor_entrada).build();
            array.add(item);
        }
        resp.setContentType(MediaType.APPLICATION_JSON); //ahora preparamos la salida json al cliente...
        try (JsonWriter jsonWriter = Json.createWriter(resp.getOutputStream())) { //.. para imprimir... 
            jsonWriter.writeArray(array.build());
        }
    }


}
