/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Modelos.Coneccion;
import Modelos.Numeros_a_letras;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RomanList;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
@WebServlet(name = "Servlet_Cesion_Derechos", urlPatterns = {"/Servlet_Cesion_Derechos"})
public class Servlet_Cesion_Derechos extends HttpServlet {

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
        String[] datos_cliente = new String[0];
        ResultSet rs;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Calendar fecha = Calendar.getInstance();
        Date currentDate = new Date();

        simpleDateFormat = new SimpleDateFormat("MMMM");
        String mes = simpleDateFormat.format(currentDate);

        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes2 = fecha.get(Calendar.MONTH);
        try {
            cone = new Coneccion();
            if (cone != null) {

                try {

                    datos_contrato = cone.Datos_CesionDerechos(numero_contrato);
                    if (datos_contrato != null) {

                        /* 
                        datos_contrato[0] tipo
                        datos_contrato[1] puerta
                        datos_contrato[2] bloque
                        datos_contrato[3] cara
                        datos_contrato[4] cedula cliente
                        datos_contrato[5] nombres
                        datos_contrato[6] parroquia
                        datos_contrato[7] referencia
                        datos_contrato[8] cedula nuevo cl
                        datos_contrato[9] valortotal*/
                        String certificadoin = "CESION DERECHOS_" + numero_contrato + ".pdf";
                        response.setHeader("Content-Disposition", "filename=\"" + certificadoin + "\"");
                        Document documento = new Document();
                        documento.addTitle("CESION DE DERECHOS");

                        Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
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
                        image.setAbsolutePosition(0, 730);

                        documento.add(image);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        par1.add(new Phrase("DECLARACIÓN DE CESIÓN DE DERECHOS", fonttitulo));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        documento.add(par1);

                        par1.add(new Phrase(Chunk.NEWLINE));
                        String tipo = "";
                        if (datos_contrato[0].equals("Boveda")) {
                            tipo = "la Bóveda";
                        } else {
                            tipo = "el Lote";
                        }
                        Numeros_a_letras l = new Numeros_a_letras();
                        datos_cliente = cone.Datos_Cliente(datos_contrato[8]);
                        String certifica = "Se expone a continuación la cesión de derechos de " + tipo + ", de la puerta número " + datos_contrato[1]
                                + " en el bloque " + datos_contrato[2] + " cara " + datos_contrato[3] + ", que se encuentra bajo CONTRATO Nº " + numero_contrato
                                + " PARA LA DISPOSICIÓN Y CLÁUSULAS DE BÓVEDAS VISTAS, BÓVEDAS SUBTERRÁNEAS Y ESPACIOS; y posterior al diálogo que mantuvieron las partes, se dispone dar por inválido dicho contrato y generar un nuevo contrato vigente a nombre de la nueva persona titular de dicho bien.";
                        par1 = new Paragraph();
                        par1.add(new Phrase(certifica, fontnormal));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        String diaactual, mesactual, añoactual = "";
                        diaactual = l.Convertir(String.valueOf(dia), false);
                        añoactual = l.Convertir(String.valueOf(año), false);
                        par1.add(new Phrase("Para lo cual, a los " + dia + " días del mes de " + mes + " en el año " + añoactual + ", en la Parroquia La Esperanza; intervienen, ", fontnormal));
                        par1.add(new Phrase(datos_contrato[5], fontnegrita));
                        par1.add(new Phrase(" representante de " + tipo + ", de la puerta número " + datos_contrato[1] + " en el bloque " + datos_contrato[2] + " cara " + datos_contrato[3] + ", con número de cédula de ciudadanía " + datos_contrato[4] + ", quien en lo sucesivo será denominada como ", fontnormal));
                        par1.add(new Phrase("“EL CEDENTE”", fontnegrita));
                        par1.add(new Phrase("; y, por otro lado, ", fontnormal));
                        par1.add(new Phrase(datos_cliente[1], fontnegrita));
                        par1.add(new Phrase(" con número de cédula de ciudadanía", fontnormal));
                        par1.add(new Phrase(datos_cliente[0], fontnegrita));
                        par1.add(new Phrase(", quien será denominado en lo sucesivo como ", fontnormal));
                        par1.add(new Phrase("“EL CESIONARIO”", fontnegrita));
                        par1.add(new Phrase(" quienes se someten a lo establecido en las siguientes:", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase("DECLARACIONES:", fontnegrita));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.add(new Phrase("I. \"El Cedente\" declara:", fontnegrita));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);

                        par1.add(new Phrase("    a)       Ser una persona que goza de todos sus derechos como lo establecen las leyes del país.", fontnormal));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("    b)       El objeto de esta cesión es por los derechos sobre " + tipo + ", de la puerta número " + datos_contrato[1] + " en el ", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("              bloque " + datos_contrato[2] + " cara " + datos_contrato[3] + ".", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("    c)       Los “DERECHOS” a que se refiere esta declaración, son plenamente vigentes y legales.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("    d)       La cedente tiene plena facultad legal para transferir a favor de “EL CESIONARIO” el", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("              total (100%) de los derechos que tiene sobre la bóveda.", fontnormal));

                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("    e)       Encontrarse en plena posesión de sus facultades mentales, consciente y voluntaria.", fontnormal));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("II. \"EL Cesionario\" declara:", fontnegrita));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("     a)       Ser una persona que goza de todos sus derechos como lo establecen las leyes del país.", fontnormal));
                        par1.setLeading(15);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     b)       Tener su domicilio en la Parroquia " + datos_cliente[2] + ", " + datos_cliente[3] + ".", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     c)       Tener las facultades legales conforme a lo establecido en esta misma declaración y estar ", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("               conforme en recibir el total (100%) de los derechos.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     d)       Encontrarse en plena posesión de sus facultades mentales, consciente y voluntaria.", fontnormal));
                        documento.add(par1);

                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";

                        Image piepagina = Image.getInstance(path + imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 1 | 3", fontpiepagina), 500, 50, 0);
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

                        image = Image.getInstance(path + imagePath);
                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        // image.setAlignment(Element.ALIGN_CENTER);
                        image.setAbsolutePosition(0, 730);
                        documento.add(image);
                        par1 = new Paragraph();
                        par1.setAlignment(Element.ALIGN_LEFT);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        par1.add(new Phrase("III.   Declaran las partes que están de acuerdo y es su que es su deseo celebrar el presente contrato al tenor de las siguientes cláusulas:", fontnegrita));
                        documento.add(par1);

                        String valortotal = l.Convertir(String.valueOf(datos_contrato[9]), true);

                        par1 = new Paragraph();
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("Primera. - ", fontnegrita));
                        par1.add(new Phrase("“El Cedente” concede a favor de \"EL Cesionario\", todos sus derechos quien asumirá el 100% (cien por ciento) de los derechos y obligaciones sobre " + tipo + ", de la puerta número " + datos_contrato[1]
                                + " en el bloque " + datos_contrato[2] + " cara " + datos_contrato[3] + ", y también con cualquier parte derecho hecho o circunstancia que corresponda a lo mismo.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Segunda. - ", fontnegrita));
                        par1.add(new Phrase("El monto total de los derechos de acuerdo al contrato es de $" + datos_contrato[9] + " (" + valortotal + " 00/100) los cuales están totalmente pagados.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Tercera. - ", fontnegrita));
                        par1.add(new Phrase("“El Cesionario” está obligado a cubrir cualquier valor que se genere de los derechos cedidos a “EL CEDENTE” la cual se depositará en la cuenta bancaria institucional; en donde, el comprobante de depósito o ficha bancaria hará las veces de recibo de pago para los efectos legales a que haya lugar.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Cuarta. - ", fontnegrita));
                        par1.add(new Phrase("Las partes de este contrato convienen en:", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     a)       “El Cedente” queda obligada a proporcionar toda información que sea necesaria o", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("                requerida en lo relacionado a los “DERECHOS”, así como cualquier información que al ", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("                respecto se encuentre o sea necesaria.", fontnormal));

                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Quinta. - ", fontnegrita));
                        par1.add(new Phrase("“EL CEDENTE” se compromete a firmar todos los documentos necesarios para que se lleve a cabo el trámite requerido con objeto de cumplir con las formalidades legales para el cumplimiento de este documento.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Sexta. - ", fontnegrita));
                        par1.add(new Phrase("Las partes señalan en este inciso los domicilios legales para recibir cualesquier aviso o notificación:", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     a)      Por “EL CEDENTE”: " + datos_contrato[7] + ", Parroquia " + datos_contrato[6] + ".", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("     b)      Por “EL CESIONARIO”: " + datos_cliente[3] + ", Parroquia " + datos_cliente[2] + ".", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Séptima. - ", fontnegrita));
                        par1.add(new Phrase("“El Cedente” expedirá y entregará a “EL CESIONARIO” los comprobantes fiscales que acrediten la adquisición de los bienes a objeto.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase("Octavo. - ", fontnegrita));
                        par1.add(new Phrase("Las dos partes de este contrato, aseveran que no existe dolo, o mala fe, de ningún tipo que vicie las voluntades de las partes o puedan viciarlo.", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));

                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        documento.add(par1);

                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";

                        piepagina = Image.getInstance(path + imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 2 | 3", fontpiepagina), 500, 50, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Calle Ascencio Cabrera y Av. Rita Bustamante", fontpiepagina), 100, 55, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("info@laesperanza.gob.ec", fontpiepagina), 100, 45, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("+593 (5) 274-7029", fontpiepagina), 100, 35, 0);
                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("www.laesperanza.gob.ec", fontpiepagina), 100, 25, 0);
                        documento.add(piepagina);
                        Chapter chapter3 = new Chapter(3);

                        chapter3.setNumberDepth(2);
                        documento.add(chapter3);
                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\encabezado.jpg";

                        image = Image.getInstance(path + imagePath);
                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        image.setTransparency(new int[]{0xF0, 0xFF});
                        // image.setAlignment(Element.ALIGN_CENTER);
                        image.setAbsolutePosition(0, 730);
                        documento.add(image);
                        par1 = new Paragraph();
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setAlignment(Element.ALIGN_JUSTIFIED);
                        par1.add(new Phrase("Después de haberse leído esta DECLARACIÓN DE CESIÓN DE DERECHOS, y estando unánimemente de acuerdo, para constancia de lo actuado; y, en fe de conformidad"
                                + " y aceptación se suscribe el presente con las personas que en ella han intervenido, con copia por duplicado en la Parroquia La Esperanza, Calle Asencio Cabrera y Avenida Rita Bustamante, el " + dia + " de " + mes + " del año " + año + ".", fontnormal));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.setLeading(15);
                        documento.add(par1);
                        PdfPTable tabla = new PdfPTable(2);
                        tabla.setPaddingTop(20);
                        tabla.setSpacingAfter(20);
                        tabla.setTotalWidth(530);
                        tabla.getDefaultCell().setBorder(0);// Establecer el ancho de la columna
                        //table.setTotalWidth (new float [] {100, 165, 100, 165}); // Establecer el ancho de la columna

                        PdfPCell celda = new PdfPCell(new Paragraph("CEDENTE", fontcelda));
                        PdfPCell celda2 = new PdfPCell(new Paragraph("CESIONARIO", fontcelda));
                        celda.setBorder(Rectangle.NO_BORDER);
                        celda.setBackgroundColor(new BaseColor(255, 255, 45));

                        tabla.addCell(celda);

                        tabla.getDefaultCell().setBorderWidth(0f);
                        documento.add(tabla);

                        path = request.getRealPath(split_path[0]);
                        imagePath = "\\images\\pie_pagina.png";

                        piepagina = Image.getInstance(path + imagePath);
                        piepagina.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        piepagina.setTransparency(new int[]{0xff, 0xff});
                        piepagina.setAbsolutePosition(0, 0);

                        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página 3 | 3", fontpiepagina), 500, 50, 0);
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
