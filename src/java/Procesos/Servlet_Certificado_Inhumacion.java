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
import java.io.FileInputStream;
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
@WebServlet(name = "Servlet_Certificado_Inhumacion", urlPatterns = {"/Servlet_Certificado_Inhumacion"})
public class Servlet_Certificado_Inhumacion extends HttpServlet {

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
        response.setContentType("APPLICATION/pdf");
        OutputStream out = response.getOutputStream();

        String numero_contrato = request.getParameter("numero_contrato");

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

                    datos_contrato = cone.Datos_Inhumacion(numero_contrato);
                    if (datos_contrato != null) {

                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha_defuncion = sd.parse(datos_contrato[2]);
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        String mes_defuncion = simpleDateFormat.format(fecha_defuncion);
                        simpleDateFormat = new SimpleDateFormat("dd");
                        String dia_defuncion = simpleDateFormat.format(fecha_defuncion);
                        simpleDateFormat = new SimpleDateFormat("yyyy");
                        String año_defuncion = simpleDateFormat.format(fecha_defuncion);
                        /*
                        datos_contrato[0] cedula
                        datos_contrato[1] nombres
                        datos_contrato[2] fecha_defuncion
                        datos_contrato[3] puerta
                        datos_contrato[4] bloque
                        datos_contrato[5] cara
                         */
                        String certificadoin = "C_INHUMACION_" + datos_contrato[0] + "_" + datos_contrato[1] + ".pdf";
                        response.setHeader("Content-Disposition", "filename=\"" + certificadoin + "\"");
                        Document documento = new Document();
                        documento.addTitle("CERTIFICADO INHUMACION");

                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, BaseColor.BLACK);
                        Font fontnormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
                        Font fontnegrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
                        Font fontpiepagina = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
                        Font fonttitulom = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
                        Font fontcelda = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
                        Font fontsubcelda = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);

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

                        par1.add(new Phrase("CERTIFICADO DE INHUMACIÓN", fonttitulo));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        documento.add(par1);

                        par1.add(new Phrase(Chunk.NEWLINE));

                        String certifica = "Ing. Silvana Romero Villarreal";

                        String cargocertifica2 = "PRESIDENTA DEL GAD";

                        par1 = new Paragraph();
                        par1.add(new Phrase("El Camposanto General del Gobierno Autónomo Descentralizado Parroquial Rural La Esperanza, perteneciente al Cantón Quevedo, Provincia de Los Ríos.", fontnormal));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("CERTIFICA", fonttitulo));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("Que, el cuerpo de " + datos_contrato[1] + " con cédula de identidad número " + datos_contrato[0]
                                + ", se encuentra sepultado en el Camposanto General del Gobierno Autónomo Descentralizado Parroquial Rural La Esperanza, "
                                + "bloque número " + datos_contrato[4] + ", puerta número " + datos_contrato[3] + ", cara número " + datos_contrato[5] + " con fecha de fallecimiento correspondiente al "
                                + dia_defuncion + " de " + mes_defuncion + " del " + año_defuncion + ", información obtenida en los folios disponibles en archivo de la institución antes mencionada.", fontnormal));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Se expide el presente CERTIFICADO DE INHUMACIÓN a solicitud de la parte interesada para realizar los trámites correspondientes a la exhumación de cadáveres de acuerdo al Reglamento Establecimientos Servicios Funerarios y Manejo de Cadáveres vigente en el país.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("La validez del certificado es de tres meses a contar de la fecha de emisión.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_RIGHT);
                        par1.add(new Phrase("La Esperanza, " + dia + " de " + mes + " del " + año, fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
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
                        par1.add(new Phrase(certifica, fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(cargocertifica2, fontnegrita));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("PARROQUIAL RURAL LA ESPERANZA", fontnegrita));
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
