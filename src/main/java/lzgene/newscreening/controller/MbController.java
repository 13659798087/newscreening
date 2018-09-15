package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Mb;
import lzgene.newscreening.model.MbType;
import lzgene.newscreening.services.MbServices;
import lzgene.newscreening.services.MbTypeServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mb")
public class MbController {


    @Resource
    private MbServices mbServices;

    @Resource
    private MbTypeServices mbTypeServices;

    private String view = "mb/";

    @RequestMapping("/templateList")
    public String templateList(){
        return view+"templateList";
    }

    @ResponseBody
    @RequestMapping("/templateTable")
    public Map<String,Object> templateTable(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder, String mb_name, HttpServletRequest request){

        List<Mb> mb = new ArrayList<Mb>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Mb> pageResults = new PageResults<Mb>();

        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        mb = mbServices.templateTable(mb_name,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = mbServices.getSignCount(mb_name,(pageNo-1)*pageSize+1, pageNo*pageSize);
        pageResults.setResult(mb);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    /**
    验证
     */
    @ResponseBody
    @RequestMapping("/validateName")
    public Integer validateName(String mb_name){
        int i = mbServices.validateName(mb_name);//查询组别信息
        if(i>0)
            i=1;
        else
            i=0;
        return i;
    }

    /**
     创建新模板
     */
    @RequestMapping("/createTemplate")
    public String createTemplate(String mb_id, String mb_code, String mb_name, String mb_order_no,String mb_type,String mb_lb_code,
                                String mb_lb_name,String mb_flag){
        String sign = "";
        Integer mb_order_no1 = null;
        if(!StringUtils.isEmpty(mb_order_no)){
            mb_order_no1 = Integer.parseInt(mb_order_no);
        }

        Integer mb_type1 = null;
        if(!StringUtils.isEmpty(mb_type)){
            mb_type1 = Integer.parseInt(mb_type);
        }

        //新增
        if(StringUtils.isEmpty(mb_id)){
            mb_id = Guid.GenerateGUID();
            mbServices.createCombine(mb_id,mb_code,mb_name,mb_order_no1,mb_type1,mb_lb_code,mb_lb_name,mb_flag);
            sign = "add";

        }else{//修改
            mbServices.updateCombine(mb_id,mb_code,mb_name,mb_order_no1,mb_type1,mb_lb_code,mb_lb_name,mb_flag);
            sign = "edit";

        }
        return "redirect:addTemplate?type=a&sign="+sign; //重定向
    }


    @RequestMapping("/addTemplate")
    public String addTemplate(HttpServletRequest request,Model model,String mb_flag,String mb_id,String mb_code,String mb_name,String mb_order_no,
                              String mb_type,String mb_lb_code,String mb_lb_name,String type,String sign){

        HttpSession session = request.getSession();

        model.addAttribute("mb_id",mb_id);
        model.addAttribute("mb_code",mb_code);
        model.addAttribute("mb_name",mb_name);
        model.addAttribute("mb_order_no",mb_order_no);
        model.addAttribute("mb_type",mb_type);
        model.addAttribute("mb_lb_code",mb_lb_code);
        model.addAttribute("mb_flag",mb_flag);

        if( "a".equals(type) ){
            model.addAttribute("mb_lb_name",session.getAttribute("organizationName"));
        }
        if( "e".equals(type) ){
            model.addAttribute("mb_lb_name",mb_lb_name);
        }
        model.addAttribute("type",type);
        model.addAttribute("sign",sign);//标识是修改还是编辑

        List<MbType> listMbType = mbTypeServices.getMbType();
        model.addAttribute("listMbType",listMbType);

        return view+"addTemplate";

    }


    //根据id删除行
    @ResponseBody
    @RequestMapping("/deleteRow")
    public void deleteRow(String mb_id){
         mbServices.deleteRow(mb_id);
    }

    //根据id删启用模板
    @ResponseBody
    @RequestMapping("/changeFlag")
    public void changeFlag(String mb_id,String mb_flag){
        mbServices.changeFlag(mb_id,mb_flag);
    }




}
