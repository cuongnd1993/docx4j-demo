package research.service.impl;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import research.convert.HtmlConverter;
import research.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {

    @Autowired
    private HtmlConverter htmlConverter;

    private ServletContext context;
    private String realPathUploads;
    private ApplicationContext applicationContext;
    private String wordImageFolder;

    @Autowired
    public OriginServiceImpl(ServletContext context, ApplicationContext applicationContext) {
        this.context = context;
        this.applicationContext = applicationContext;
        this.wordImageFolder = this.applicationContext.getEnvironment().getProperty("folder.word.image");
        this.realPathUploads = this.context.getRealPath("resources/" + this.wordImageFolder);

        File file = new File(this.realPathUploads);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public String convertWordToHtml(MultipartFile file, HttpServletRequest request) throws IOException, Docx4JException {

        return this.htmlConverter.convertWordToHtmlByDocx4j(file, realPathUploads, request.getContextPath() + "/resources/" + this.wordImageFolder);
    }
}
