package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.Report;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.services.CombineServices;
import lzgene.newscreening.services.ReportServices;
import lzgene.newscreening.util.JasperUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageConroller {



    @Resource
    private ReportServices reportServices;

    @Resource
    private CombineServices combineServices;

    @Resource
    private JasperUtil jasperUtil;
    private String view = "message/";


    @RequestMapping("/getMessage")
    public String getMessage(){
        return view + "getMessage";

    }

    @RequestMapping("/photo")
    public String photo(){
        return view + "photo";

    }

    @RequestMapping("/printReport")
    public void myprint(HttpServletResponse response, String c_id, String c_code, String authorHospital) throws Exception {
        String fileName = "";

        List<Combine> combine = combineServices.getCombine();
        for(Combine c : combine){
            if(c_code.equals(c.getC_code())){
                fileName = c.getC_rpt()+".jasper";//拼接文件后缀pdf,此处是下载pdf文件
            }
        }
        response.setContentType("multipart/form-data");
        BufferedImage bufferedImage = genPdf(c_id,fileName,authorHospital);

        ServletOutputStream out = response.getOutputStream();//新建流。

        //ImageIO.write(bufferedImage, "JPEG", new File("d:/aa.jpg"));
        ImageIO.write(bufferedImage, "jpg",out );

        out.flush();
        out.close();
    }


    public BufferedImage genPdf(String c_id, String fileName, String authorHospital) {
        List<Report> userLs = new ArrayList<Report>();
        Map<String, Object> map = new HashMap<String, Object>();

        /*map.put("cCombine", "ct"); map.put("cSid", "C1700530");*//*map.put("c_combine", "cl"); map.put("c_sid", "20170967");*///map.put("c_id", "ts180529001");//map.put("c_id", " wc20180529002");// map.put("c_id", "dp20180529001");
        map.put("c_id", c_id);
        map.put("authorHospital", authorHospital);


        return jasperUtil.exportPdfDir1(fileName, map, userLs);
    }

}
