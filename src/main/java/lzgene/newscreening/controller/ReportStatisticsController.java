package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.CheckInfoCombineTotle;
import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.entiry.SendApplication;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.services.CombineServices;
import lzgene.newscreening.services.SendApplicationServices;
import lzgene.newscreening.util.Paging;
import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reportStatistics")
public class ReportStatisticsController {

    @Resource
    private SendApplicationServices sendApplicationServices;

    @Resource
    private CombineServices combineServices;

    private String view = "reportStatistics/";

    @RequestMapping("/reportForm")
    public String applicationForm(){
        return view+"reportForm";
    }




    @ResponseBody
    @RequestMapping("/getStatisticsReport")
    public Map<String, Object> getStatisticsApplyinfo(HttpServletRequest request, String a_lr_date1, String a_lr_date2, String c_hospital){

        PageResults<CheckInfoCombineTotle> pageResults = new PageResults<CheckInfoCombineTotle>();
        Timestamp a_lr_date_1 = null;
        Timestamp a_lr_date_2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!StringUtils.isEmpty(a_lr_date1))   a_lr_date_1 = Timestamp.valueOf(a_lr_date1 +" 00:00:00");
        if(!StringUtils.isEmpty(a_lr_date2))   a_lr_date_2 = Timestamp.valueOf(a_lr_date2 +" 23:59:59");

        List<SendApplication> checkInfoCombine = new ArrayList<SendApplication>(); //报告信息

        HttpSession session = request.getSession();
        String organizationId = (String) session.getAttribute("organizationId");

        checkInfoCombine = sendApplicationServices.getStatisticsCheckInfoCombine(organizationId,a_lr_date_1,a_lr_date_2,c_hospital);

        List<Combine> listCombine = new ArrayList<Combine>();
        listCombine = combineServices.getCombine();//查询所有组别项目

        List<CheckInfoCombineTotle> checkInfoCombineTotle = new ArrayList<CheckInfoCombineTotle>();

        Integer noPrintCount1 = 0;//未打印总数
        Integer printCount1 = 0;//打印总数
        Integer combineTotal1 = 0;//汇总总数

        for(Combine c : listCombine){
            Integer combineTotal = 0;//组别x列的总数
            Integer noPrintCount = 0;//组别x列的未打印总数
            Integer printCount = 0;//组别x列的已打印总数

            CheckInfoCombineTotle t = new CheckInfoCombineTotle();
            for(SendApplication a : checkInfoCombine){
                if( c.getC_code().equals(a.getC_combine_code()) ){ //组别名
                    if( a.getC_rpt_flag() == 1 ){
                        noPrintCount+=1;//未打印
                    }
                    if( a.getC_rpt_flag() == 2 ){
                        printCount+=1;//已打印
                    }
                }
            }

            t.setCombineName(c.getC_name());//组别A

            combineTotal = noPrintCount + printCount ;//组别A对应的总数
            t.setCombineTotal(combineTotal);
            t.setNoPrintCount(noPrintCount);
            t.setPrintCount(printCount);
            checkInfoCombineTotle.add(t);

            combineTotal1 += combineTotal; //录入总数
            noPrintCount1 += noPrintCount;//未打印总数
            printCount1+= printCount;//已打印总数
        }

        CheckInfoCombineTotle t1 = new CheckInfoCombineTotle();//汇总列数据
        t1.setCombineName("汇总");
        t1.setCombineTotal(combineTotal1);
        t1.setNoPrintCount(noPrintCount1);
        t1.setPrintCount(printCount1);

        checkInfoCombineTotle.add(t1);

        pageResults.setResult(checkInfoCombineTotle);
        return Paging.ajaxGrid(pageResults);
    }



}
