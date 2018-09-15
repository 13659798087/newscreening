package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.ConfigReport;
import lzgene.newscreening.entiry.Report;
import lzgene.newscreening.model.Organization;
import lzgene.newscreening.services.OrganizationServices;
import lzgene.newscreening.services.ReportServices;
import lzgene.newscreening.util.JasperUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dataStatistics")
public class DataStatisticsController {

    @Resource
    private ReportServices reportServices;
    @Resource
    private JasperUtil jasperUtil;
    @Resource
    private OrganizationServices organizationServices;


    private String view ="dataStatistics/";

    @RequestMapping("/dataStatistics")
    public String dataStatistics(){
        return view+"dataStatistics";
    }

    @ResponseBody
    @RequestMapping("/getStatisticsMsg")
    public List<ConfigReport> getStatisticsMsg(){
        List<ConfigReport> listStatistics = reportServices.getStatisticsMsg();
        return listStatistics;
    }


    @RequestMapping("/applicationReport")
    public void applicationReport(HttpServletResponse response, HttpServletRequest request, String id,
                                  String a_hospital,String a_lr_date1,String a_lr_date2 ) throws IOException {

        //Map<String, String[]> a = request.getParameterMap();获取前端传过来的参数集合
        ConfigReport configReport = reportServices.getConfigReportById(id);

        String sqlStr = ""; //传到ireport的拼接sql

        if(StringUtils.isEmpty(a_hospital) || "undefined".equals(a_hospital) ){//不传a_hospital，则查询该登录单位（筛查中心）下的信息
            a_hospital = (String) request.getSession().getAttribute("organizationId");
        }

        int type = configReport.getType();
        if(!StringUtils.isEmpty(a_lr_date1)){
            switch (type){
                case 1:
                    sqlStr = " and a_lr_date BETWEEN " +"'"+a_lr_date1 + "'"+" and "+"'"+a_lr_date2+"'";
                    break;
                case 2:
                    sqlStr = " and c_a_date BETWEEN " +"'"+a_lr_date1 + "'"+" and "+"'"+a_lr_date2+"'";
                default:
                    break;
            }
        }

        String fileName = configReport.getReport_name() + ".jasper";//拼接文件后缀pdf,此处是下载pdf文件

        InputStream in = genPdf(a_hospital,sqlStr,fileName);
        response.setContentType("application/pdf; charset=UTF-8");
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[512];
        if( in!=null ){
            while ((in.read(b))!=-1) {
                out.write(b);
            }
        }
        out.flush();
        in.close();
        out.close();
    }

    public InputStream genPdf(String organizationId,String sqlStr,String fileName) {
        List<Report> userLs = new ArrayList<Report>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("organizationId", organizationId);
        map.put("sqlStr", sqlStr);

        return jasperUtil.exportPdfDir(fileName, map, userLs);
    }






}
