package research.controller.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import research.common.ResponseDescription;
import research.service.OriginService;
import research.transform.CoreResponse;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RestController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private OriginService originService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String submit(@RequestParam("docFile") MultipartFile file, HttpServletRequest request) {

        try {
            return this.originService.convertWordToHtml(file, request);
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return e.getMessage();
        }
    }
}
