package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.*;
import lzgene.newscreening.services.SetmealServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//套餐
@Controller
@RequestMapping("/setmeal")
public class SetmealController {

    @Resource
    private SetmealServices setmealServices;

    private String view = "setmeal/";

    @RequestMapping("/listSetmeal")
    public String listSetmeal(){
        return view+"listSetmeal";
    }

    @ResponseBody
    @RequestMapping("/getSetmeal")
    public Map<String, Object> getSetmeal(String pageNumber,String rowNumber,String sortName,
                                          String sortOrder,String s_code,String s_name,HttpServletRequest request){

        List<Setmeal> listSetmeal = new ArrayList<Setmeal>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Setmeal> pageResults = new PageResults<Setmeal>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        listSetmeal = setmealServices.getSetmeal(s_code,s_name,(pageNo-1)*pageSize+1, pageNo*pageSize);//查询套餐信息

        long totalCount = setmealServices.getSetmealCount(s_code,s_name,(pageNo-1)*pageSize+1, pageNo*pageSize);
        pageResults.setResult(listSetmeal);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);
    }

    @RequestMapping("/createSetmeal")
    public String createSetmeal(String hide_code,String s_code, String s_name, String s_price, String s_order_no,String roles){
        String sign = "";

        BigDecimal price = null;
        if(!StringUtils.isEmpty(s_price)){
            price=new BigDecimal(s_price);
        }

        //新增
        if(StringUtils.isEmpty(hide_code)){
            setmealServices.createSetmeal(s_code,s_name,price,s_order_no);
            sign = "add";
        }else{//修改
            setmealServices.updateSetmeal(hide_code,s_code,s_name,price,s_order_no);
            //删除关系
            setmealServices.delRela(s_code);
            sign = "edit";
        }

        //建立关系表
        String[] a = roles.split(",");
        for(int i=0;i<a.length;i++){
            String sc_id = Guid.GenerateGUID();
            String c_code = a[i];
            setmealServices.insertRela(sc_id,c_code,s_code);
        }

        return "redirect:addSetmeal?type=a&sign="+sign; //重定向
    }

    @RequestMapping("/addSetmeal")
    public String addSetmeal(Model model, String s_code, String s_name, String s_price, String s_order_no,String type,String sign){
        String s_code1 = s_code;
        String s_name1 = s_name;

        if(!StringUtils.isEmpty(s_code) && s_code.contains(" ")){
            s_code1 = s_code.replaceAll(" ","+");
        }
        if(!StringUtils.isEmpty(s_name) && s_name.contains(" ")){
            s_name1 = s_name.replaceAll(" ","+");
        }

        model.addAttribute("s_code",s_code1);
        model.addAttribute("s_name",s_name1);
        model.addAttribute("s_price",s_price);
        model.addAttribute("s_order_no",s_order_no);
        model.addAttribute("type",type);
        model.addAttribute("sign",sign);//标识是修改还是编辑
        return view+"addSetmeal";
    }

    @ResponseBody
    @RequestMapping("/validateSetmeal")
    public Integer validateSetmeal(String s_code,String type){
        int i=0;
        if("1".equals(type)){
            i = setmealServices.validateSetmeal(s_code);//查询套餐信息
            if(i>0)
                i=1;
            else
                i=0;
        }
        if("2".equals(type)){
            i = setmealServices.validateSetmeal(s_code);//查询组别信息
            if(i>1)
                i=2;
            else
                i=0;
        }
        return i;
    }

    /***
     *  根据id删除行
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteSetmeal")
    public void deleteSetmeal(String s_code){
        setmealServices.deleteSetmeal(s_code);
    }

    /***
     *  根据s_code查询中间表所包含的组别的id
     */
    @ResponseBody
    @RequestMapping("/getBySetmeal")
    public List<Setmealcombine> getBySetmeal(String s_code){
        List<Setmealcombine> setmealcombine = new ArrayList<Setmealcombine>();
        setmealcombine = setmealServices.getBySetmeal(s_code);
        return setmealcombine;
    }

}
