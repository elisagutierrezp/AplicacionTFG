package main.java.model;

import java.io.FileOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.util.Log;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;

public class GenerarPDF {

    private String path;

    public GenerarPDF() {

    }

    /**
     * Genera el informe con los datos solicitados en formato pdf
     *
     * @param lista
     * @param lista2
     * @param fecha
     * @return String con la ruta del informe
     */
    public String generarPDF(ArrayList<String> lista, ArrayList<String> lista2, String fecha) {
        try {

            Document documento = new Document();
            path = System.getProperty("user.dir");
            //Directorio de tomcat
            FileOutputStream informePDF = new FileOutputStream(path + "/Informe.pdf");

            PdfWriter.getInstance(documento, informePDF).setInitialLeading(30);
            documento.open();

            addMetaData(documento);
            //Crea el titulo del pdf
            Paragraph paragraphTitle = new Paragraph();
            paragraphTitle.add("Ingenieros al peso S.A.");
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
            paragraphTitle.getFont().setSize(26);
            paragraphTitle.getFont().setStyle(Font.BOLD);
            documento.add(paragraphTitle);

            //Titulo de la seccion
            documento.add(new Paragraph(" "));
            Paragraph paragraphSubTitle = new Paragraph();
            paragraphSubTitle.add("Trabajado " + fecha + " :");
            paragraphSubTitle.getFont().setSize(12);
            paragraphSubTitle.getFont().setStyle(Font.UNDERLINE);
            documento.add(paragraphSubTitle);
            documento.add(new Paragraph(" "));

            //Crea la lista de lo trabajado en la seccion
            createList(lista, documento);

            //Titulo de la seccion
            documento.add(new Paragraph(" "));
            Paragraph paragraphSubTitle2 = new Paragraph();
            paragraphSubTitle2.add("Dias/Horas libres  " + fecha + " :");
            paragraphSubTitle2.getFont().setSize(12);
            paragraphSubTitle2.getFont().setStyle(Font.UNDERLINE);
            documento.add(paragraphSubTitle2);
            documento.add(new Paragraph(" "));

            //Crea la lista de las peticiones
            createList(lista2, documento);
            //Logo de la empresa en caso de error comentar hasta linea 83
//            String pathfoto = System.getProperty("user.dir") + "\\webapps\\ProyectoWebGrupo3\\images\\logo.png";
//            Image foto = Image.getInstance(pathfoto);
//            foto.scaleToFit(100, 100);
//            foto.setAlignment(Chunk.ALIGN_MIDDLE);
//            documento.add(foto);
            
            
            documento.close();

        } catch (Exception e) {
            Log.log.info("Error al generar informe " + e);
            e.printStackTrace();
        }
        return (path + "/Informe.pdf");
    }

    /**
     * Describe el contenido, calidad, condiciones y otras caracter�sticas del
     * documento.
     *
     * @param document
     */
    private static void addMetaData(Document document) {
        document.addTitle("Informe generado");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText, Web");
        document.addAuthor("Grupo3");
        document.addCreator("Grupo3");
    }

    /**
     * Crea una lista con los datos que se le pasan por parametro y la escribe
     * en el pdf.
     *
     * @param lista
     * @param document
     * @throws DocumentException
     */
    private static void createList(ArrayList<String> lista, Document document) throws DocumentException {
        List list = new List(true, false, 10);
        for (int i = 0; i < lista.size(); i++) {
            list.add(new ListItem(" " + lista.get(i)));
        }
        document.add(list);
    }

}
