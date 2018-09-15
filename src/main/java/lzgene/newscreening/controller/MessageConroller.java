package lzgene.newscreening.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageConroller {

    private String view = "message/";


    @RequestMapping("/getMessage")
    public String getMessage(){
        return view + "getMessage";

    }


}
