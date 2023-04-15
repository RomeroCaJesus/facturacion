package servlets;

import dao.ClienteDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.Cliente;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListadoClientes", urlPatterns = {"/ListadoClientes"})
public class ListadoClientes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {
            try {

                try {

                    Document miPDF = new Document(PageSize.A4.rotate());
                    PdfWriter.getInstance(miPDF, out);

                    miPDF.open();

                    // Título del documento
                    String titulo = "REGISTRO DE CLIENTES" + "\n";
                    Font fonTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
                    Paragraph primero = new Paragraph(titulo, fonTitulo);
                    miPDF.add(new Paragraph(" ")); // Línea en blanco
                    miPDF.add(primero);

                    // Se crea una tabla de 3 columnas
                    PdfPTable tablaPdf = new PdfPTable(4);
                    float[] medidaCeldas = {1.20f, 1.20f, 1.20f, 1.20f};
                    tablaPdf.setWidths(medidaCeldas);
                    tablaPdf.setWidthPercentage(100);
                    miPDF.add(new Paragraph(" "));
                    PdfPCell cabecera;

                    for (int col = 1; col <= 4; col++) {
                        String c[] = new String[5];
                        c[1] = "CÓDIGO";
                        c[2] = "AP. PATERNO";
                        c[3] = "AP. MATERNO";
                        c[4] = "NOMBRE";
                        c[5] = "RAZÓN SOCIAL";

                        Font fontCabecera = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);

                        cabecera = new PdfPCell(new Phrase(c[col], fontCabecera));
                        cabecera.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cabecera.setBackgroundColor(BaseColor.GREEN);
                        cabecera.setBorderColor(BaseColor.BLACK);
                        tablaPdf.addCell(cabecera);

                    }
                    tablaPdf.setHeaderRows(1);

                    Font fontDatos = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
                    List<Cliente> lista = ClienteDAO.listar();

                   for (Cliente cliente : lista) {
                       PdfPCell cell;
                        cell = new PdfPCell(new Phrase((cliente.getCodiClie()).toString(), fontDatos));
                        tablaPdf.addCell(cell).setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell = new PdfPCell(new Phrase(cliente.getAppaClie(), fontDatos));
                        tablaPdf.addCell(cell).setHorizontalAlignment(Element.ALIGN_CENTER);
                       cell = new PdfPCell(new Phrase(cliente.getApmaClie(), fontDatos));
                       tablaPdf.addCell(cell).setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell = new PdfPCell(new Phrase(cliente.getNombClie(), fontDatos));
                       tablaPdf.addCell(cell).setHorizontalAlignment(Element.ALIGN_CENTER);
                       cell = new PdfPCell(new Phrase(cliente.getRaznSociClie(), fontDatos));
                       tablaPdf.addCell(cell).setHorizontalAlignment(Element.ALIGN_CENTER);
                    }

                    miPDF.add(tablaPdf);

                    miPDF.add(new Paragraph(" "));
//                    Font fuenteCierre = new Font(Font.FontFamily.COURIER, 10, Font.ITALIC, BaseColor.RED);
                    miPDF.close();

                } catch (Exception e) {
                    e.getMessage();
                }

            } catch (Exception e) {
                e.getMessage();
            }

        } finally {
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
