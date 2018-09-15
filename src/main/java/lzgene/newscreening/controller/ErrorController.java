package lzgene.newscreening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return  "error/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
