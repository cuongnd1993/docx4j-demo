package research.service;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface OriginService {

    String convertWordToHtml(MultipartFile file, HttpServletRequest request) throws IOException, Docx4JException;
}
