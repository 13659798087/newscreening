package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.services.CombineServices;
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

@Controller
@RequestMapping("/combine")
public class CombineController {

    @Resource
    private CombineServices combineServices;

    private String view = "combine/";

    @RequestMapping("/listCombine")
    public String listSetmeal(){
        return view+"listCombine";
    }

    @ResponseBody
    @RequestMapping("/getTableCombine")
    public Map<String, Object> getTableCombine(String pageNumber,String rowNumber,String sortName,
                                               String sortOrder,String c_name,String c_rpt,HttpServletRequest request){

        List<Combine> listCombine = new ArrayList<Combine>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Combine> pageResults = new PageResults<Combine>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        listCombine = combineServices.getCombineList(c_name,c_rpt,(pageNo-1)*pageSize+1, pageNo*pageSize);//查询组别信息

        long totalCount = combineServices.getCombineCount(c_name,c_rpt,(pageNo-1)*pageSize+1, pageNo*pageSize);
        pageResults.setResult(listCombine);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);
    }

    @RequestMapping("/createCombine")
    public String createCombine(String hide_code,String c_code,String c_name,String c_price,String c_order_no,String c_rpt,String c_rpt_title,
                                String c_rpt_bz1,String c_rpt_bz2,String paper_size){
        String sign = "";
        BigDecimal price = null;
        if(!StringUtils.isEmpty(c_price)){
            price = new BigDecimal(c_price);
        }

        //新增
        if(StringUtils.isEmpty(hide_code)){
            combineServices.createCombine(c_code,c_name,price,c_order_no,c_rpt,c_rpt_title,c_rpt_bz1,c_rpt_bz2,paper_size);
            sign = "add";
        }else{//修改
            combineServices.updateCombine(hide_code,c_code,c_name,price,c_order_no,c_rpt,c_rpt_title,c_rpt_bz1,c_rpt_bz2,paper_size);
            sign = "edit";
        }

        return "redirect:addCombine?type=a&sign="+sign; //重定向
    }

    @RequestMapping("/addCombine")
    public String addCombine(Model model, String c_code, String c_name, String c_price, String c_order_no,String c_rpt,
                              String c_rpt_title, String c_rpt_bz1, String c_rpt_bz2,String paper_size,String type,String sign){

        model.addAttribute("c_code",c_code);
        model.addAttribute("c_name",c_name);
        model.addAttribute("c_price",c_price);
        model.addAttribute("c_order_no",c_order_no);
        model.addAttribute("c_rpt",c_rpt);
        model.addAttribute("c_rpt_title",c_rpt_title);
        model.addAttribute("c_rpt_bz1",c_rpt_bz1);
        model.addAttribute("c_rpt_bz2",c_rpt_bz2);
        model.addAttribute("paper_size",paper_size);

        model.addAttribute("type",type);
        model.addAttribute("sign",sign);//标识是修改还是编辑
        return view+"addCombine";
    }


    @ResponseBody
    @RequestMapping("/getCombine")
    public List<Combine> getCombine(){
        List<Combine> listCombine = combineServices.getCombine();
        return listCombine;
    }


    @ResponseBody
    @RequestMapping("/getCombineByCode")
    public Combine getCombineByCode(String c_code){
        Combine combine = combineServices.getCombineByCode(c_code);
        return combine;
    }

    @ResponseBody
    @RequestMapping("/getCombineById")
    public List<Combine> getCombineById(String s_code){
        List<Combine> list = combineServices.getCombineById(s_code);
        return list;
    }

    @ResponseBody
    @RequestMapping("/validateCombine")
    public Integer validateCombine(String c_code,String type){
        int i = 0;
        if("1".equals(type)){
            i = combineServices.validateCombine(c_code);//查询组别信息
            if(i>0)
                i=1;
            else
                i=0;
        }
        if("2".equals(type)){
            i = combineServices.validateCombine(c_code);//查询组别信息
            if(i>1)
                i=2;
            else
                i=0;
        }
        return i;
    }

    /***
     *  根据id删除行
     * @param c_code
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCombine")
    public void deleteCombine(String c_code){
        combineServices.deleteCombine(c_code);
    }



}
