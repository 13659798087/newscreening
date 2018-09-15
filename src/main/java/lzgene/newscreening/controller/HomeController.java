package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.*;
import lzgene.newscreening.model.Applyinfo;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.model.Organization;
import lzgene.newscreening.model.Setmeal;
import lzgene.newscreening.services.ApplyinfoServices;

import lzgene.newscreening.services.CombineServices;
import lzgene.newscreening.services.OrganizationServices;
import lzgene.newscreening.services.SetmealServices;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Resource
    private ApplyinfoServices applyinfoServices;

    @Resource
    private SetmealServices setmealServices;

    @Resource
    private CombineServices combineServices;

    @Resource
    private OrganizationServices organizationServices;

    private String view = "home/";

    @RequestMapping("/pageHome")
    public String getHome(){
        return view+"pageHome";
    }


    //根据单位、时间查询申请单每年的数据统计
    @ResponseBody
    @RequestMapping("/getYearApplicationData")
    public List<ListApplication> getYearApplicationData(HttpServletRequest request,String organizationId,String year){
        if(StringUtils.isEmpty(year)){//如果不传年份过来，就获取当前年份的数据
            year = String.valueOf( Calendar.getInstance().get(Calendar.YEAR) ) ;
        }

        List<ListApplication> list = new ArrayList<ListApplication>();
        HttpSession session = request.getSession();
        if(StringUtils.isEmpty(organizationId)){
            organizationId = (String)session.getAttribute("organizationId");
        }

        list = applyinfoServices.getYearApplication(organizationId,year);
        //这里把“类别名称”和“销量”作为两个属性封装在一个Product类里，每个Product类的对象都可以看作是一个类别（X轴坐标值）与销量（Y轴坐标值）的集合
       /* list.add(new Product("衬衣", 10));
        list.add(new Product("短袖", 20));
        list.add(new Product("大衣", 30));*/
        return list;
    }

    //根据单位、时间查询申请单每年的项目数据统计
    @ResponseBody
    @RequestMapping("/getBySemealData")
    public  List<ApplicationGroupSetmeal> getBySemealData(HttpServletRequest request,String organizationId,String a_lr_date1,String a_lr_date2){

        Timestamp date_1 = null;
        Timestamp date_2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String d1 = format2.format(date);//获取今年yyyy
        String d2 = format.format(date);//得到字符串类型的yyyy-MM-dd

        //如果不传日期过来，就查今年
        if(StringUtils.isEmpty(a_lr_date1)){
            date_1 = Timestamp.valueOf(d1 +"-01-01 00:00:00");//今年第一天
        }
        if(StringUtils.isEmpty(a_lr_date2)){
            date_2 = Timestamp.valueOf(d2 +" 23:59:59"); //今天
        }

        //传日期过来，就查传进来的时间范围
        if(!StringUtils.isEmpty(a_lr_date1)){
            date_1 = Timestamp.valueOf(a_lr_date1 +" 00:00:00");
        }
        if(!StringUtils.isEmpty(a_lr_date2)){
            date_2 = Timestamp.valueOf(a_lr_date2 +" 23:59:59");
        }

        HttpSession session = request.getSession();
        if(StringUtils.isEmpty(organizationId)){
            organizationId = (String)session.getAttribute("organizationId");
        }

        List<Applyinfo> listApplication = applyinfoServices.getBySemealData(organizationId,date_1,date_2);

        List<Setmeal> listSetmeal = setmealServices.getListSetmea();//查询所有套餐

        List<ApplicationGroupSetmeal> groupSetmeal = new ArrayList<ApplicationGroupSetmeal>();

        for(Setmeal s : listSetmeal){
            ApplicationGroupSetmeal oneSetmeal = new ApplicationGroupSetmeal();

            Integer count = 0;//套餐x的总数

            for(Applyinfo a : listApplication){
                if( s.getS_name().equals(a.getA_setmeal_name()) ){//套餐名
                    count+=1;//循环各个套餐的个数
                }
            }

            oneSetmeal.setName(s.getS_name());
            oneSetmeal.setValue(count);

            if(count>0){
                groupSetmeal.add(oneSetmeal);
            }

        }

        return groupSetmeal;
    }

    //根据单位、时间查询每年报告单的数据统计
    @ResponseBody
    @RequestMapping("/getYearReportData")
    public List<ListApplication> getYearReportData(HttpServletRequest request,String organizationId,String year){
        if(StringUtils.isEmpty(year)){//如果不传年份过来，就获取当前年份的数据
            year = String.valueOf( Calendar.getInstance().get(Calendar.YEAR) ) ;
        }

        HttpSession session = request.getSession();
        if(StringUtils.isEmpty(organizationId)){
            organizationId = (String)session.getAttribute("organizationId");
        }
        List<ListApplication> list = new ArrayList<ListApplication>();
        list = applyinfoServices.getYearReportData(organizationId,year);

        return list;
    }


    //根据单位、时间查询每年的组别项目统计
    @ResponseBody
    @RequestMapping("/getByCombinelData")
    public  List<ApplicationGroupSetmeal> getByCombinelData(HttpServletRequest request,String organizationId,String c_a_date1,String c_a_date2){

        Timestamp date_1 = null;
        Timestamp date_2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String d1 = format2.format(date);//获取今年yyyy
        String d2 = format.format(date);//得到字符串类型的yyyy-MM-dd

        //如果不传日期过来，就查今年
        if(StringUtils.isEmpty(c_a_date1)){
            date_1 = Timestamp.valueOf(d1 +"-01-01 00:00:00");//今年第一天
        }
        if(StringUtils.isEmpty(c_a_date2)){
            date_2 = Timestamp.valueOf(d2 +" 23:59:59"); //今天
        }

        //传日期过来，就查传进来的时间范围
        if(!StringUtils.isEmpty(c_a_date1)){
            date_1 = Timestamp.valueOf(c_a_date1 +" 00:00:00");
        }
        if(!StringUtils.isEmpty(c_a_date2)){
            date_2 = Timestamp.valueOf(c_a_date2 +" 23:59:59");
        }

        HttpSession session = request.getSession();
        if(StringUtils.isEmpty(organizationId)){
            organizationId = (String)session.getAttribute("organizationId");
        }

        List<SendApplication> checkInfo = new ArrayList<SendApplication>(); //报告信息

        checkInfo = applyinfoServices.getReportCheckInfo(organizationId,date_1,date_2);

        List<Combine> listCombine = combineServices.getCombine();//查询所有组别项目

        List<ApplicationGroupSetmeal> groupSetmeal = new ArrayList<ApplicationGroupSetmeal>();

        for(Combine b : listCombine){
            ApplicationGroupSetmeal oneSetmeal = new ApplicationGroupSetmeal();

            Integer count = 0;//套餐x的总数

            for(SendApplication c : checkInfo){
                if( b.getC_code().equals(c.getC_combine_code()) ){//套餐名
                    count+=1;//循环各个套餐的个数
                }
            }

            oneSetmeal.setName(b.getC_name());
            oneSetmeal.setValue(count);

            if(count>0){
                groupSetmeal.add(oneSetmeal);
            }

        }

        return groupSetmeal;
    }


    //查询每月每天的数据
    @ResponseBody
    @RequestMapping("/getMonthData")
    public List<ListApplication> getData(HttpServletRequest request){

        HttpSession session = request.getSession();
        String organizationId = (String)session.getAttribute("organizationId");

        List<ListApplication> list = new ArrayList<ListApplication>();

        list = applyinfoServices.getMonthApplication(organizationId);

        return list;
    }


}
