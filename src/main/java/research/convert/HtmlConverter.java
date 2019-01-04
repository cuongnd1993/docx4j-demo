package research.convert;

import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.HTMLConversionImageHandler;
import org.docx4j.convert.out.html.SdtToListSdtTagHandler;
import org.docx4j.convert.out.html.SdtWriter;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class HtmlConverter {

    public String convertWordToHtmlByDocx4j(MultipartFile file, String imageDirPath, String targetUri) throws IOException, Docx4JException {
        WordprocessingMLPackage wordMLPackage;
        wordMLPackage = Docx4J.load(file.getInputStream());

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setImageHandler(new HTMLConversionImageHandler(imageDirPath, targetUri, true));
        htmlSettings.setWmlPackage(wordMLPackage);

        String userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td " +
                "{ margin: 0; padding: 0; border: 0;}" +
                "body {line-height: 1;} ";
        htmlSettings.setUserCSS(userCSS);

        SdtWriter.registerTagHandler("HTML_ELEMENT", new SdtToListSdtTagHandler());

        // output to an OutputStream.
        OutputStream os;
        os = new ByteArrayOutputStream();

        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);
        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        // Clean up, so any ObfuscatedFontPart temp files can be deleted
        if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }

        wordMLPackage.reset();
        return os.toString().replaceAll("Â ", "");
    }
}
