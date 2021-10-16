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
@WebServlet(name = "Servlet_GenerarContrato", urlPatterns = {"/Servlet_GenerarContrato"})
public class Servlet_GenerarContrato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ public int margin_left = 3;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        String numero_contrato = request.getParameter("numero_contrato");
        String contrato = "001-CGLE-GADPRLE-C-2021.pdf";
        //String directorio2 = new File(".").getAbsolutePath() + "\\src\\Imagenes\\encabezado.jpg";
        //String directorio = System.getProperty("user.dir") + "\\src\\Imagenes\\encabezado.jpg";
        response.setHeader("Content-Disposition", "filename=\"" + numero_contrato + "\"");
        Coneccion cone;

        String[] clausulas = new String[0];
        String[] datos_contrato = new String[0];
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

                    clausulas = cone.Clausulas();

                    if (clausulas != null) {
                        datos_contrato = cone.Datos_Contrato(numero_contrato);
                        /*
                        datos_contrato [0] cedula
                        datos_contrato [1] nombres
                        datos_contrato [2] valor total
                        datos_contrato [3] valor entrada
                        datos_contrato [4] saldo
                        datos_contrato [5] tipo propiedad
                        datos_contrato [6] puerta
                        datos_contrato [7] bloque
                        datos_contrato [8] cara
                        datos_contrato [9] cuenta
                        datos_contrato [10] fecha
                         */
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha_contrato = sd.parse(datos_contrato[10]);
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        String mes_contrato = simpleDateFormat.format(fecha_contrato);
                        simpleDateFormat = new SimpleDateFormat("dd");
                        String dia_contrato = simpleDateFormat.format(fecha_contrato);
                        simpleDateFormat = new SimpleDateFormat("yyyy");
                        String año_contrato = simpleDateFormat.format(fecha_contrato);

                        Document documento = new Document();
                        documento.addTitle("Contrato");

                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
                        Font fontnormal = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
                        Font fontnegrita = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
                        Font fontpiepagina = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
                   
                        PdfWriter writer = PdfWriter.getInstance(documento, out);

                        documento.setMargins(60, 60, 0, 50);
                        documento.open();
                        // (Creemos el primer capítulo)
                        Chapter chapter = new Chapter(1);
                        chapter.setNumberDepth(0);
                        documento.add(chapter);
                        Paragraph par1 = new Paragraph();
                        String path = request.getContextPath();

                        String split_path[] = path.split("/");
                        path = request.getRealPath(split_path[0]);
                        String imagePath = "\\images\\encabezado.jpg";

                        //Image image = Image.getInstance("C:\\Users\\Angel\\Documents\\VinculaciónAEV\\Desarrollo\\CamposantoLaEsperanza (4)\\CamposantoLaEsperanza\\src\\Imagenes\\encabezado.jpg");
                        Image image = Image.getInstance(path + imagePath);

                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        image.setAlignment(Element.ALIGN_CENTER);

                        documento.add(image);

                        par1.add(new Phrase("CONTRATO Nº " + numero_contrato, fonttitulo));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("PARA LA DISPOSICIÓN Y CLÁUSULAS DE  \n"
                                + "BÓVEDAS VISTAS, BÓVEDAS SUBTERRÁNEAS Y ESPACIOS.", fonttitulo));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1.add(new Phrase(Chunk.NEWLINE));

                        String vendedor = "Señora Licenciada Evelina Elisa Sevillano Basantes";
                        String vendedora = "Lic. Evelina Elisa Sevillano Basantes";
                        String cedulavendedor = "120453809-2 ";
                        String certifica = "Ing. Silvana Romero Villarreal";
                        String cargocertifica = "SECRETARIA-TESORERA";
                        String diacontrato = "13";
                        String mescontrato = "septiembre";
                        String añocontrato = "2021";
                        par1 = new Paragraph();
                        par1.add(new Phrase("PARA LA DISPOSICIÓN GENERAL. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[0], fontnormal));

                        par1.setAlignment(Element.ALIGN_JUSTIFIED);

                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);

                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("CLÁUSULA I.- DENOMINACIONES. - ", fontnegrita));
                        par1.add(new Phrase("El Gobierno Autónomo Descentralizado Parroquial Rural la Esperanza a través de su representante legal ", fontnormal));
                        par1.add(new Phrase(vendedor, fontnegrita));
                        par1.add(new Phrase(", con cédula de identidad ", fontnormal));
                        par1.add(new Phrase(cedulavendedor, fontnegrita));
                        par1.add(new Phrase(" en calidad de Vendedora; y, por otra parte, ", fontnormal));
                        par1.add(new Phrase(datos_contrato[1], fontnegrita));
                        par1.add(new Phrase(", con cédula de identidad ", fontnormal));
                        par1.add(new Phrase(datos_contrato[0], fontnegrita));
                        par1.add(new Phrase(" en calidad de Comprador.", fontnormal));
                        par1.setLeading(15);
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));

                        par1.add(new Phrase("CLÁUSULA II.- ASPECTOS GENERALES. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[2], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);

                        par1.setLeading(15);
                        par1.add(new Phrase("CLÁUSULA III.- VALOR Y CONDICIONES DE PAGO. – ", fontnegrita));
                        par1.add(new Phrase("La bóveda se la adquiere, por el valor de ", fontnormal));
                        par1.add(new Phrase("$" + datos_contrato[2], fontnegrita));
                        par1.add(new Phrase(", con una entrada de ", fontnormal));
                        par1.add(new Phrase("$" + datos_contrato[3], fontnegrita));
                        par1.add(new Phrase(", el saldo de ", fontnormal));
                        par1.add(new Phrase("$" + datos_contrato[4], fontnegrita));
                        par1.add(new Phrase("; el mismo que deberá ser pagado antes de los 30 días contados a partir de la suscripción del presente, valores que serán depositados en la cuenta rotativa a nombre del Gobierno Autónomo Descentralizado Parroquial Rural La Esperanza, en el Banco Bolivariano con ", fontnormal));
                        par1.add(new Phrase("N° " + datos_contrato[9], fontnegrita));
                        par1.add(new Phrase(", siendo dicho documento de depósito el único comprobante que justifique su pago, el mismo que será presentado en la Secretaria del Gobierno Parroquial, para la obtención del comprobante o factura correspondiente.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);

                        par1.add(new Phrase("CLÁUSULA IV.- DETERMINACIÓN DE ÁREA. - ", fontnegrita));
                        par1.add(new Phrase("El Comprador realiza la adquisición de una bóveda", fontnormal));
                        //par1.add(new Phrase("N° " + boveda, fontnegrita));
                        par1.add(new Phrase(", en la puerta ", fontnormal));
                        par1.add(new Phrase("N° " + datos_contrato[6], fontnegrita));
                        par1.add(new Phrase(" del bloque ", fontnormal));
                        par1.add(new Phrase("N° " + datos_contrato[7], fontnegrita));
                        par1.add(new Phrase(" cara ", fontnormal));
                        par1.add(new Phrase(datos_contrato[8], fontnegrita));
                        par1.add(new Phrase("; en el Camposanto General La Esperanza.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);

                        par1.add(new Phrase("CLÁUSULA V.- DE LA UTILIZACIÓN Y VENTA. – ", fontnegrita));
                        par1.add(new Phrase("El Comprador para realizar la construcción de bóvedas  y  mausoleos,  etc.,  en  un  espacio  del  cementerio,  deberá  cumplir  con  los  materiales  y especificaciones técnicas que serán entregadas en un documento físico por el Gobierno Parroquial; previa a la construcción notificar mediante solicitud el inicio de la construcción. Para la verificación respectiva, se comprobará que la altura guarde proporción con el área adquirida y diseño; terminada la construcción de ", fontnormal));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(par1);
                        
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";
                        
                        //Image piepagina = Image.getInstance("C:\\Users\\Angel\\Documents\\VinculaciónAEV\\Desarrollo\\CamposantoLaEsperanza (4)\\CamposantoLaEsperanza\\src\\Imagenes\\pie_pagina.png");
                        
                        Image piepagina = Image.getInstance(path+imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 1 | 2", fontpiepagina), 500, 50, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Calle Ascencio Cabrera y Av. Rita Bustamante", fontpiepagina), 100, 55, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("info@laesperanza.gob.ec", fontpiepagina), 100, 45, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("+593 (5) 274-7029", fontpiepagina), 100, 35, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("www.laesperanza.gob.ec", fontpiepagina), 100, 25, 0);
                        documento.add(piepagina);

                        Chapter chapter2 = new Chapter(2);

                        chapter2.setNumberDepth(1);
                        documento.add(chapter2);
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\encabezado.jpg";
                        
                        image = Image.getInstance(path+imagePath);
                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        // image.setAlignment(Element.ALIGN_CENTER);
                        image.setAbsolutePosition(0, 730);
                        documento.add(image);
                        par1 = new Paragraph();
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("las bóvedas, la Comisión que establezca el Reglamento correspondiente y/o el Orgánico por Procesos remitirá su informe al Ejecutivo del GAD Parroquial Rural La Esperanza para su aprobación y posterior autorización.", fontnormal));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("CLÁUSULA VI. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[6], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase("CLÁUSULA VII. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[7], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.setLeading(15);
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("CLÁUSULA VIII. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[8], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase("CLÁUSULA IX. - ", fontnegrita));
                        par1.add(new Phrase(clausulas[9], fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Para constancia del presente contrato, firman las partes en unidad de acto, en la Parroquia la Esperanza a los " + dia_contrato + " días del mes de " + mes_contrato + " del " + año_contrato + ". ", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.add(new Phrase(vendedora + "                                       " + datos_contrato[1], fontnormal));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        par1.add(new Phrase("   C.I.: " + cedulavendedor + "                                                              C.I.: " + datos_contrato[0], fontnegrita));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("       VENDEDORA                                                              COMPRADOR", fontnegrita));
                        documento.add(par1);
                        par1 = new Paragraph();
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.add(new Phrase("Lo Certifica,", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(certifica, fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(cargocertifica, fontnegrita));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("GADPRLE LA ESPERANZA", fontnegrita));
                        documento.add(par1);
                        
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";
                        
                        piepagina = Image.getInstance(path+imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(5, -5);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 2 | 2", fontpiepagina), 500, 50, 0);
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
