/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.sun.faces.lifecycle.Phase;
import java.awt.Color;
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
@WebServlet(name = "Servlet_PDF", urlPatterns = {"/Servlet_PDF"})
public class Servlet_PDF extends HttpServlet {

    public int margin_left = 3;
    /**
     * Margen derecho
     */
    public int margin_right = 2;
    /**
     * Margen superior
     */
    public int margin_top = 4;
    /**
     * Margen inferior
     */
    public int margin_bottom = 3;

    public void OnEndPage(PdfWriter writer, Document document) throws IOException, DocumentException {
        PdfContentByte cb = writer.getDirectContent();
        ColumnText ct = new ColumnText(cb);

        cb.beginText();
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12.0f);
        cb.setTextMatrix(document.leftMargin(), document.bottomMargin());
        cb.showText(String.format("{0} {1}", "Testing Text", "Like this"));
        cb.endText();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        Coneccion cone;
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

                    rs = cone.InformeDiario();
                    if (rs != null) {
                        Document documento = new Document(new Rectangle(PageSize.A4), margin_left, margin_right, margin_top, margin_bottom);
                        documento.addTitle("Informe Diario");
                        
                        //documento.addSubject("Using iText (usando iText)");
                        //documento.addKeywords("Java, PDF, iText");
                        //documento.addAuthor("ADMIN");
                        //documento.addCreator("Código Xules");
                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
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

                        par1.add(new Phrase("Oficio Circular Nro. 001-2021-GADPRLE-CGPLE-T-OC", fonttitulo));
                        par1.setAlignment(Element.ALIGN_RIGHT);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("La Esperanza, " + dia + " de " + mes + " del " + año, fontnormal));
                        documento.add(par1);
                        Paragraph par2 = new Paragraph();
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));

                        par2.add(new Phrase("Lic.", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("Evelina Sevillano Basantes", fontnormal));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("PRESIDENTA DEL GOBIERNO AUTÓNOMO DESCENTRALIZADO", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("PARROQUIAL RURAL LA ESPERANZA", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("Ing.", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("Silvana Romero Villarreal", fontnormal));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("SECRETARIA TESORERA DEL GOBIERNO AUTÓNOMO", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("DESCENTRALIZADO PARROQUIAL RURAL LA ESPERANZA", fontnegrita));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase("De mis consideraciones:", fontnormal));
                        par2.setAlignment(Element.ALIGN_LEFT);
                        documento.add(par2);
                        Paragraph par3 = new Paragraph();
                        par3.setAlignment(Element.ALIGN_JUSTIFIED);
                        par3.add(new Phrase(Chunk.NEWLINE));
                        par3.add(new Phrase("Tengo el agrado de dirigirme a usted para denotar un afectuoso y fraterno saludo, a la vez de desearle los más sinceros deseos de éxito en las importantes actividades que realiza diariamente.", fontnormal));
                        par3.add(new Phrase(Chunk.NEWLINE));
                        par3.setSpacingAfter(8);
                        documento.add(par3);
                        Paragraph par4 = new Paragraph();

                        par4.setAlignment(Element.ALIGN_JUSTIFIED);
                        par4.add(new Phrase("Distinguida, por medio de la presente efectúo la entrega del informe correspondiente de las papeletas recibidas en la presente fecha con los valores ingresados al día " + dia + " de " + mes + " del " + año + ", de las personas que han adquirido bóvedas vistas y lotes de terrenos en el Camposanto General de la Parroquia La Esperanza; y, que han realizado los respectivos depósitos en la cuenta vigente del Banco Bolivariano.", fontnormal));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.setSpacingAfter(8);

                        Paragraph par5 = new Paragraph();
                        par5.setAlignment(Element.ALIGN_JUSTIFIED);
                        par5.add(new Phrase("Para dar efecto al cumplimiento, adjunto copia del comprobante de ingreso con su respectiva boleta de depósito.", fontnormal));
                        par5.add(new Phrase(Chunk.NEWLINE));
                        par5.setSpacingAfter(8);

                        Paragraph par6 = new Paragraph();
                        par6.setAlignment(Element.ALIGN_JUSTIFIED);
                        par6.setSpacingAfter(8);
                        par6.add(new Phrase("Sin más por el momento, agradeciendo la atención mostrada, me despido expresándole la muestra de mi consideración y estima personal, quedo a sus órdenes para cualquier duda o aclaración.", fontnormal));
                        par6.add(new Phrase(Chunk.NEWLINE));
                        par6.add(new Phrase(Chunk.NEWLINE));

                        Paragraph par7 = new Paragraph();
                        par7.setAlignment(Element.ALIGN_LEFT);
                        par7.add(new Phrase("Atentamente,", fontnormal));
                        par7.setSpacingAfter(8);
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase("              Ing. Jandrith Garzón Chávez", fontnormal));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase("         TÉCNICO DE CEMENTERIO", fontnegrita));
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";

                        Image piepagina = Image.getInstance(path + imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 1 | 2", fontpiepagina), 500, 50, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Calle Ascencio Cabrera y Av. Rita Bustamante", fontpiepagina), 100, 55, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("info@laesperanza.gob.ec", fontpiepagina), 100, 45, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("+593 (5) 274-7029", fontpiepagina), 100, 35, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("www.laesperanza.gob.ec", fontpiepagina), 100, 25, 0);
                        documento.add(piepagina);

                        Paragraph par8 = new Paragraph();
                        par8.add(new Phrase(Chunk.NEWLINE));
                        par8.setLeading(8);
                        par8.setAlignment(Element.ALIGN_TOP);
                        // par8.setAlignment(10);
                        par8.add(new Phrase("           Calle Ascencio Cabrera y Av. Rita Bustamante\n          info@laesperanza.gob.ec\n           +593 (5) 274-7029\n"
                                + "         www.laesperanza.gob.ec", fontpiepagina));
//                par8.add(new Phrase(Chunk.NEWLINE));
//                par8.add(new Phrase("info@laesperanza.gob.ec", fontpiepagina));
//                par8.add(new Phrase(Chunk.NEWLINE));
//                par8.add(new Phrase("+593 (5) 274-7029", fontpiepagina));
//                par8.add(new Phrase(Chunk.NEWLINE));
//                par8.add(new Phrase("www.laesperanza.gob.ec", fontpiepagina));

                        documento.add(par4);
                        documento.add(par5);
                        documento.add(par6);
                        documento.add(par7);
                        //documento.add(par8);
                        Chapter chapSecond = new Chapter(2);
                        documento.setPageSize(new Rectangle(PageSize.A4.rotate()));
                        documento.setMargins(60, 60, 0, 30);
                        documento.add(chapSecond);
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\gad.png";

                        image = Image.getInstance(path + imagePath);
                        image.scaleToFit(800, 150);
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        //piepagina.setAbsolutePosition(300, 750);
                        //piepagina.setAlignment(Element.ALIGN_LEFT);

                        documento.add(image);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("INFORME DE INGRESOS", fonttitulom), 430, 500, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("DEL CAMPOSANTO GENERAL DE LA", fonttitulom), 430, 480, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("PARROQUIA LA ESPERANZA", fonttitulom), 430, 460, 0);

                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\gad2.png";

                        image = Image.getInstance(path + imagePath);
                        image.scaleToFit(800, 150);
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        image.setAbsolutePosition(570, 430);
                        image.setAlignment(Element.ALIGN_RIGHT);
                        documento.add(image);
                        PdfPTable tabla = new PdfPTable(11);

                        tabla.setLockedWidth(true);
                        tabla.setPaddingTop(20);

                        PdfPCell celda = new PdfPCell(new Paragraph("N", fontcelda));
                        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("FECHA", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("APELLIDOS Y NOMBRES", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("CÉDULA", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("NRO. PAPELETA", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("FECHA DEPÓ.", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("PRECIO VENTA", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("VALOR DEPÓS.", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("SALDO", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("COMPR. INGRESO", fontcelda));
                        tabla.addCell(celda);
                        celda.setPhrase(new Paragraph("NRO. FACTURA", fontcelda));
                        tabla.addCell(celda);
                        celda.setBackgroundColor(BaseColor.WHITE);

                        while (rs.next()) {
                            celda.setPhrase(new Paragraph(rs.getString(1), fontsubcelda));
                            //celda.setColspan(3); 
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(2), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(3), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(4), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(5), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(6), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(7), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(8), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(9), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(10), fontsubcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(11), fontsubcelda));

                            tabla.addCell(celda);
                        }
                        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        celda.setPhrase(new Paragraph("TOTAL", fontcelda));
                        celda.setColspan(6);
                        tabla.addCell(celda);
                        rs = cone.ValoresTotalesInforme();
                        while (rs.next()) {
                            celda = new PdfPCell(new Paragraph(rs.getString(1), fontcelda));
                            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(2), fontcelda));
                            tabla.addCell(celda);
                            celda.setPhrase(new Paragraph(rs.getString(3), fontcelda));
                            tabla.addCell(celda);
                        }

                        celda.setColspan(9);
                        celda.setPhrase(new Paragraph("", fontcelda));
                        tabla.addCell(celda);

                        //tabla.setLockedWidth(true); 
                        tabla.setTotalWidth(730f);
                        documento.add(tabla);
                        par7 = new Paragraph();
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.setAlignment(Element.ALIGN_CENTER);
                        par7.add(new Phrase("Ing. Jandrith Garzón Chávez", fontnormal));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase("TÉCNICO DE CEMENTERIO", fontnegrita));
                        documento.add(par7);
                        documento.close();
                    } else {

                    }

                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        } finally {
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
