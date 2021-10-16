/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Angel
 */
@WebServlet(name = "Servlet_Reestructuracion_Contrato", urlPatterns = {"/Servlet_Reestructuracion_Contrato"})
public class Servlet_Reestructuracion_Contrato extends HttpServlet {

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
         
       response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        String numero_contrato = request.getParameter("numero_contrato");

        String certificadoin = "R_C" + numero_contrato + ".pdf";
        response.setHeader("Content-Disposition", "filename=\"" + certificadoin + "\"");
        Coneccion cone;
        String[] datos_contrato = new String[0];
        ResultSet rs;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Calendar fecha = Calendar.getInstance();
        Date currentDate = new Date();

        simpleDateFormat = new SimpleDateFormat("MMMM");
        String mes = simpleDateFormat.format(currentDate);

        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        try {
            cone = new Coneccion();
            if (cone != null) {

                try {

                    datos_contrato = cone.Datos_Ren_contrato(numero_contrato);
                    if (datos_contrato != null) {
                        /*
                        datos_contrato[0] cedula
                        datos_contrato[1] nombres
                        datos_contrato[2] costo
                        datos_contrato[3] meses
                        datos_contrato[4] costoanterior
                        datos_contrato[5] mesesanterior
                                */

 /*
                        ---------------------------------
                        parámetros que recibe la reestructuración 
                        los meses y el valor de la cuota
                        con eso se actualiza las cuotas
                        ....
                        toca pedir esos datos para generar el documento
                        ahorita está estático
                         */
                        String mesescambio = "6";
                        String valorcuota = "65.83";

                        /* 
                        ----------------------------------------
                         */
                        Document documento = new Document();
                        documento.addTitle("Reestrucuración Contrato");

                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, BaseColor.BLACK);
                        Font fontnormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                        Font fontnegrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
                        Font fontpiepagina = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);

                        PdfWriter writer = PdfWriter.getInstance(documento, out);

                        documento.setMargins(60, 60, 0, 50);
                        documento.open();
                        // Let's create de first Chapter (Creemos el primer capítulo)
                        Chapter chapter = new Chapter(1);
                        chapter.setNumberDepth(0);
                        documento.add(chapter);
                        Paragraph par1 = new Paragraph();

                        String path = request.getContextPath();
                        String split_path[] = path.split("/");
                        path = request.getRealPath(split_path[0]);
                        String imagePath = "\\images\\encabezado.jpg";

                        Image image = Image.getInstance(path + imagePath);
                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        image.setAlignment(Element.ALIGN_CENTER);

                        documento.add(image);

                        par1.add(new Phrase("La Esperanza, " + dia + " de " + mes + " del " + año, fontnormal));
                        par1.setAlignment(Element.ALIGN_RIGHT);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.add(new Phrase("Lic. Evelina Sevillano Basantes", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("PRESIDENTA DEL GAD PARROQUIAL LA ESPERANZA", fontnegrita));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Presente.-", fontnormal));
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("Por medio de la presente les hago llegar el más cordial saludo, deseándole éxito en las importantes funciones que realiza diariamente.", fontnormal));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("A la vez de solicitar la reestructuración del CONTRATO Nro. " + numero_contrato + " de una propiedad de bóveda vista adquirida en el Camposanto General La Esperanza, que actualmente se encuentra a nombre de "
                                + datos_contrato[1] + " con cédula de identidad " + datos_contrato[0] + ", en donde deseo motivos cambiar el contrato de " + datos_contrato[3] + " a " + datos_contrato[5]
                                + " meses, por un costo total a pagar de $" + datos_contrato[2] + " y cuotas mensuales de $" + datos_contrato[4], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Sin nada más que mencionar, y esperando que mi petición tenga una respuesta favorable, me despido dándole las gracias más cumplidas.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Atentamente, ", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.setLeading(15);
                        par1.add(new Phrase(datos_contrato[1], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("C.I.: " + datos_contrato[0], fontnegrita));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";

                         
                        Image piepagina = Image.getInstance(path + imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 1 | 1", fontpiepagina), 500, 50, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Calle Ascencio Cabrera y Av. Rita Bustamante", fontpiepagina), 100, 55, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("info@laesperanza.gob.ec", fontpiepagina), 100, 45, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("+593 (5) 274-7029", fontpiepagina), 100, 35, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("www.laesperanza.gob.ec", fontpiepagina), 100, 25, 0);
                        documento.add(piepagina);

                        documento.close();

                    } else {

                    }

                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        } finally {

            //out.flush();
            out.close();
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
