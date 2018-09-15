package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.ApplicationSetmealTotle;
import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Applyinfo;
import lzgene.newscreening.model.Organization;
import lzgene.newscreening.model.Setmeal;
import lzgene.newscreening.services.ApplyinfoServices;
import lzgene.newscreening.services.SetmealServices;
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
@RequestMapping("/applicationStatistics")
public class ApplicationStatisticsController {

    @Resource
    private ApplyinfoServices applyinfoServices;

    @Resource
    private SetmealServices setmealServices;


    private String view="applicationStatistics/";

    @RequestMapping("/applicationForm")
    public String applicationForm(){
        return view+"applicationForm";
    }



    @ResponseBody
    @RequestMapping("/getStatisticsApplyinfo")
    public Map<String, Object> getStatisticsApplyinfo(HttpServletRequest request, String a_lr_date1, String a_lr_date2, String a_hospital){

        PageResults<ApplicationSetmealTotle> pageResults = new PageResults<ApplicationSetmealTotle>();
        Timestamp a_lr_date_1 = null;
        Timestamp a_lr_date_2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!StringUtils.isEmpty(a_lr_date1))   a_lr_date_1 = Timestamp.valueOf(a_lr_date1 +" 00:00:00");
        if(!StringUtils.isEmpty(a_lr_date2))   a_lr_date_2 = Timestamp.valueOf(a_lr_date2 +" 23:59:59");

        List<Applyinfo> applyinfo = new ArrayList<Applyinfo>(); //数据库所有录入的申请单信息

        HttpSession session = request.getSession();
        String organizationId = (String) session.getAttribute("organizationId");

        //根据条件查询申请单
        applyinfo = applyinfoServices.getStatisticsApplyinfo(organizationId,a_lr_date_1,a_lr_date_2,a_hospital);

        List<Setmeal> listSetmeal = new ArrayList<Setmeal>();
        listSetmeal = setmealServices.getListSetmea();//查询所有套餐

        List<ApplicationSetmealTotle> applicationTotle = new ArrayList<ApplicationSetmealTotle>();

        Integer entryCount1 = 0;//录入总数
        Integer inspection1 = 0;//送检总数
        Integer signInCount1 = 0;//签收总数
        Integer reportCount1 = 0;//报告总数
        Integer setmealTotal1 = 0;//汇总总数

        for(Setmeal s : listSetmeal){
            Integer setmealTotal = 0;//套餐x列的总数
            Integer entryCount = 0;//套餐x列的录入总数
            Integer inspection = 0;//套餐x列的送检总数
            Integer signInCount = 0;//套餐x列的签收总数
            Integer reportCount = 0;//套餐x列的报告总数
            ApplicationSetmealTotle t = new ApplicationSetmealTotle();
            for(Applyinfo a : applyinfo){

                if( s.getS_name().equals(a.getA_setmeal_name()) ){//套餐名
                    if( a.getA_flag() == 1 ){
                        entryCount+=1;//录入
                    }
                    if( a.getA_flag() == 2 ){
                        inspection+=1;//送检
                    }
                    if( a.getA_flag() == 4 ){
                        signInCount+=1;//签收
                    }
                    if( a.getA_flag() == 5 ){
                        reportCount+=1;//报告
                    }
                }

            }

            t.setSetmealName( s.getS_name() );//套餐A

            setmealTotal = entryCount + inspection + signInCount + reportCount;//套餐A对应的总数
            t.setSetmealTotal(setmealTotal);
            t.setEntryCount(entryCount);//套餐A录入的个数
            t.setInspection(inspection);//套餐A送检的个数
            t.setSignInCount(signInCount);//套餐A签收的个数
            t.setReportCount(reportCount);//套餐A报告的个数

            applicationTotle.add(t);

            entryCount1 += entryCount; //录入总数
            inspection1 += inspection;//送检总数
            signInCount1+= signInCount;//签收总数
            reportCount1+= reportCount;//报告总数
            setmealTotal1 += setmealTotal;//汇总总数

        }

        ApplicationSetmealTotle t1 = new ApplicationSetmealTotle();//汇总列数据
        t1.setSetmealName("汇总");
        t1.setSetmealTotal(setmealTotal1);
        t1.setEntryCount(entryCount1);
        t1.setInspection(inspection1);
        t1.setSignInCount(signInCount1);
        t1.setReportCount(reportCount1);
        applicationTotle.add(t1);

        pageResults.setResult(applicationTotle);
        return Paging.ajaxGrid(pageResults);
    }


    //根据id删除行
    @ResponseBody
    @RequestMapping("/getListHospital")
    public List<Organization> getListHospital(HttpServletRequest request){
        HttpSession session = request.getSession();
        String organizationId = (String) session.getAttribute("organizationId");

        List<Organization> listHospital =applyinfoServices.getListHospital(organizationId);

        return listHospital;
    }





}
