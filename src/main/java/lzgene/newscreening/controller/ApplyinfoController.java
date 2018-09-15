package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.*;
import lzgene.newscreening.services.*;
import lzgene.newscreening.util.*;
import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/applyinfo")
public class ApplyinfoController {

    @Resource
    private ApplyinfoServices applyinfoServices;

    @Resource
    private SetmealServices setmealServices;

    @Resource
    private OrganizationServices organizationServices;

    @Resource
    private ApplyinfoCombineServices applyinfoCombineServices;

    @Resource
    private MbServices mbServices;

    private String view ="applyinfo/";


    @Value("${system.version}")
    private String systemVersion; //系统版本

    @Value("${sjks}")
    private String sjks; //送检科室

    @Value("${sjys}")
    private String sjys; //送检医生

    @Value("${lczd}")
    private String lczd; //临床诊断

    @Value("${blycs}")
    private String blycs; //不良孕育使类型

    @Value("${hkhj}")
    private String hkhj; //戶口


    //自定义密钥
   // private static String secretKey = "9ba45bfd500642328ec03ad8ef1b6e75";

    @ResponseBody
    @RequestMapping("/getTableApplyinfo")
    public Map<String, Object> getTableApplyinfo(String pageNumber,String rowNumber,String sortName,String sortOrder,String a_lr_date1,String a_lr_date2, String a_flag,String a_name,
                                                 String a_barcode,String a_hospital,String a_in_no,HttpServletRequest request) {

                                                   //a_hospital传过来实际是医院id

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Applyinfo> pageResults = new PageResults<Applyinfo>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        Timestamp a_lr_date_1 = null;
        Timestamp a_lr_date_2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!StringUtils.isEmpty(a_lr_date1))   a_lr_date_1 = Timestamp.valueOf(a_lr_date1 +" 00:00:00");
        if(!StringUtils.isEmpty(a_lr_date2))   a_lr_date_2 = Timestamp.valueOf(a_lr_date2 +" 23:59:59");

        List<Applyinfo> applyinfo = new ArrayList<Applyinfo>(); //数据库所有录入的申请单信息

        HttpSession session = request.getSession();
        if(StringUtils.isEmpty(a_hospital)){ //a_hospital传过来是医院id
            a_hospital = (String) session.getAttribute("organizationId");
        }

        applyinfo = applyinfoServices.getTableApplyinfo(a_hospital,a_name,(pageNo-1)*pageSize+1,pageNo*pageSize,a_lr_date_1,a_lr_date_2,a_barcode, a_in_no,a_flag);

        for(Applyinfo a : applyinfo) {
            String tel1 = a.getA_tel1();
            String tel2 = a.getA_tel2();
            //对电话进行解密
            if(!StringUtils.isEmpty( tel1 ))   a.setA_tel1( PhoneUtil.decrypt(tel1) );
            if(!StringUtils.isEmpty( tel2 ))   a.setA_tel2(  PhoneUtil.decrypt(tel2) );
        }

        long totalCount =applyinfoServices.getTableApplyinfoCount(a_hospital,a_name,(pageNo-1)*pageSize+1,pageNo*pageSize,a_lr_date_1,a_lr_date_2,a_barcode, a_in_no,a_flag);

        pageResults.setResult(applyinfo);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    @RequestMapping("/applyinfo")
    public String applyinfo(Model model,HttpServletRequest request){
        return "applyinfo/applyinfo";
    }

    @RequestMapping("/cardEntry")
    public String cardEntry(Model model,HttpServletRequest request,Integer dcdId){
        HttpSession session = request.getSession();
        model.addAttribute("userName",session.getAttribute("userName"));
        model.addAttribute("organizationName",session.getAttribute("organizationName"));
        return "applyinfo/cardEntry";
    }

    //isNewDept标识用户输入法人科室是否是新的，如果是则插入数据库
    @ResponseBody
    @RequestMapping(value="/createApplication",method = RequestMethod.POST)
    public void saveApplication(Model model,Applyinfo application,String isNewDept,String isNewDoctor,String isNewLczd,
                                String isNewBlycs,String isNewPlace,HttpServletRequest request) {
        HttpSession session = request.getSession();

        String a_id = Guid.GenerateGUID();
        application.setA_id(a_id);

        String tel1 = application.getA_tel1();
        String tel2 = application.getA_tel2();

        if(!StringUtils.isEmpty(tel1)){
            application.setA_tel1( PhoneUtil.encryption(tel1) );
        }
        if(!StringUtils.isEmpty(tel2)){
            application.setA_tel2( PhoneUtil.encryption(tel2) );
        }
        //保存录入时软件版本号
        application.setA_system_bbh( systemVersion );

        applyinfoServices.createApplication(application);

        //插入新增的送检科室
        String organizationName = (String) session.getAttribute("organizationName");
        if("0".equals(isNewDept)){ //不存在则在模板表插入新增的送检科室  isNewDept（1，0）
            mbServices.createDeptMb(Guid.GenerateGUID(),application.getA_dept(),sjks,organizationName);
        }
        //插入新增的送检医生
        if("0".equals(isNewDoctor)){ //不存在则在模板表插入新增的送检医生  isNewDoctor（1，0）
            mbServices.createDeptMb(Guid.GenerateGUID(),application.getA_doctor(),sjys,organizationName);
        }
        //插入新增的临床诊断
        if("0".equals(isNewLczd)){ //不存在则在模板表插入新增的送检科室  isNewLczd（1，0）
            mbServices.createDeptMb(Guid.GenerateGUID(),application.getA_lczd(),lczd,null);
        }
        //插入新增的不良孕产史
        if("0".equals(isNewBlycs)){ //不存在则在模板表插入新增的送检医生  isNewBlycs（1，0）
            mbServices.createDeptMb(Guid.GenerateGUID(),application.getA_blycs(),blycs,null);
        }
        //插入新增的户籍地点
        if("0".equals(isNewPlace)){ //不存在则在模板表插入新增的户籍地点  isNewPlace（1，0）
            mbServices.createDeptMb(Guid.GenerateGUID(),application.getA_place(),hkhj,null);
        }

        if(!StringUtils.isEmpty( application.getA_setmeal_name() )){
            List<Setmealcombine> setmealcombine = new ArrayList<Setmealcombine>();
            setmealcombine = setmealServices.getBySetmeal( application.getA_setmeal_name() );
            //选择的套餐包含哪些项目，建立个人申请表和项目的关系
            for(Setmealcombine s : setmealcombine){
                String id = Guid.GenerateGUID();
                applyinfoCombineServices.CreateApplyinfoCombine(id,a_id,s.getC_code(),s.getC_name());
            }

        }
    }

    /**
     * 根据id查询对于的申请单信息
     * @param
     * @param a_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRowById")
    public Applyinfo getRowById(String a_id) {
        Applyinfo applyinfo = applyinfoServices.getRowById(a_id);

        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        String tel1 = applyinfo.getA_tel1();
        String tel2 = applyinfo.getA_tel2();
        //对电话进行解密
        if(!StringUtils.isEmpty( tel1 )){
            applyinfo.setA_tel1( PhoneUtil.decrypt(tel1) );
        }
        if(!StringUtils.isEmpty( tel2 )){
            applyinfo.setA_tel2( PhoneUtil.decrypt(tel2) );
        }

        return applyinfo;
    }

    @ResponseBody
    @RequestMapping(value="/updateApplication",method = RequestMethod.POST)
    public void updateApplication(Model model,Applyinfo application,String notUpdatedSetmeal) {

        String tel1 = application.getA_tel1();
        String tel2 = application.getA_tel2();

        //对电话号码加密
        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        if(!StringUtils.isEmpty(tel1)){
            application.setA_tel1( PhoneUtil.encryption(tel1) );
        }
        if(!StringUtils.isEmpty(tel2)){
            application.setA_tel2( PhoneUtil.encryption(tel2) );
        }

        applyinfoServices.updateApplication(application);

        if(!StringUtils.isEmpty( application.getA_setmeal_name() )){

            List<Setmealcombine> setmealcombine1 = new ArrayList<Setmealcombine>();
            List<Setmealcombine> setmealcombine2 = new ArrayList<Setmealcombine>();

            //查询出之前建立的关系
            setmealcombine1 = setmealServices.getBySetmeal( notUpdatedSetmeal );
            //循环删除之前的关系
            for(Setmealcombine s1 : setmealcombine1){
                applyinfoCombineServices.deleteApplyinfoCombine(application.getA_id(),s1.getC_code());
            }

            //查询出更新之后的套餐包含的项目
            setmealcombine2 = setmealServices.getBySetmeal( application.getA_setmeal_name() );
            //选择的套餐包含哪些项目，建立个人申请表和项目的关系
            for(Setmealcombine s2 : setmealcombine2){
                String id = Guid.GenerateGUID();
                applyinfoCombineServices.CreateApplyinfoCombine(id,application.getA_id(),s2.getC_code(),s2.getC_name());
            }

        }
    }


    @ResponseBody
    @RequestMapping(value="/getApplyinfoCard",method = RequestMethod.GET)
    public Map<String,Object> getApplyinfoCard(Model model,Integer dcdId,HttpServletRequest request){
        HttpSession session = request.getSession();

        String organizationName = (String) session.getAttribute("organizationName");

        Map<String,Object> result = new HashMap<String,Object>();
        //医院检查项目套餐的种类
        dcdId=55;//赣州市妇幼保健院
       // List<Combine> deptcombine = deptcombineServices.getDeptCombine(dcdId);
        List<Setmeal> setmealName = setmealServices.getListSetmea();
        List<Organization> organization = organizationServices.getOrganization();

       /* List<Mb> deptMb = mbServices.getDept(organizationName,sjks);//送检科室
        List<Mb> doctorMb = mbServices.getDept(organizationName,sjys);//送检医生
        List<Mb> lczdMb = mbServices.getdzAndycs(lczd);//临床诊断和不良孕产史，传入额类型不一样
        List<Mb> blycsMb = mbServices.getdzAndycs(blycs);//临床诊断和不良孕产史，传入额类型不一样
        List<Mb> placeMb = mbServices.getdzAndycs(hkhj);//户口户籍，传入额类型不一样*/

        List<Mb> deptMb = new ArrayList<Mb>();//送检科室
        List<Mb> doctorMb = new ArrayList<Mb>();//送检医生
        List<Mb> lczdMb = new ArrayList<Mb>();//临床诊断和不良孕产史，传入额类型不一样
        List<Mb> blycsMb = new ArrayList<Mb>();//临床诊断和不良孕产史，传入额类型不一样
        List<Mb> placeMb = new ArrayList<Mb>();//户口户籍，传入额类型不一样*/

        //查询所有启用的模板
        List<Mb> mb = mbServices.getAllMb();
        for(Mb m : mb){
            String t = m.getMb_type().toString();//类型
            if(!StringUtils.isEmpty(t)){
                String mb_lb_name = m.getMb_lb_name();//医院名称

                if(organizationName.equals(mb_lb_name) && sjks.equals(t)){
                    deptMb.add(m);
                }
                if(organizationName.equals(mb_lb_name) && sjys.equals(t)){
                    doctorMb.add(m);
                }
                if( lczd.equals(t) ){
                    lczdMb.add(m);
                }
                if( blycs.equals(t)){
                    blycsMb.add(m);
                }
                if( hkhj.equals(t)){
                    placeMb.add(m);
                }

            }
        }

        result.put("setmealName",setmealName);
        //result.put("deptcombine",deptcombine);
        result.put("organization",organization);

        result.put("deptMb",deptMb);
        result.put("doctorMb",doctorMb);
        result.put("lczdMb",lczdMb);
        result.put("blycsMb",blycsMb);
        result.put("placeMb",placeMb);

        result.put("userName",session.getAttribute("userName"));
        result.put("organizationName",session.getAttribute("organizationName"));

        return result;
    }



    //根据id删除信息
    @ResponseBody
    @RequestMapping("/deleteRowById")
    public void deleteRowById(String a_id){
        applyinfoServices.deleteRowById(a_id);
        applyinfoCombineServices.deleteCombineRelation(a_id);
    }

    //验证输入条码号是否存在
    @ResponseBody
    @RequestMapping("/validateBarcode")
    public String validateBarcode(String a_barcode){
        String a="";
        int i = applyinfoServices.validateBarcode(a_barcode);
        if(i>0)
            a="1";
        else
            a="0";

        return a;
    }


}
