package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Config;
import lzgene.newscreening.services.ConfigServices;

import lzgene.newscreening.util.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
public class ConfigController {


    @Resource
    private ConfigServices configServices;

    @Value("${barcodeLength}")
    private String barcodeLength;

    @Value("${sampleDateValidation}")
    private String sampleDateValidation;

    private String view = "config/";

    @RequestMapping("/listConfig")
    public String templateList(){
        return view+"listConfig";
    }

    @ResponseBody
    @RequestMapping("/configTable")
    public Map<String,Object> configTable(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder, String cf_code, HttpServletRequest request){

        List<Config> config = new ArrayList<Config>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Config> pageResults = new PageResults<Config>();

        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        config = configServices.configTable(cf_code,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = configServices.getConfigCount(cf_code,(pageNo-1)*pageSize+1, pageNo*pageSize);

        pageResults.setResult(config);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    /**
    验证
     */
    @ResponseBody
    @RequestMapping("/validateName")
    public Integer validateName(String cf_code){
        int i = configServices.validateName(cf_code);//查询组别信息
        if(i>0)
            i=1;
        else
            i=0;
        return i;
    }

    /**
     创建新模板
     */
    @RequestMapping("/createConfig")
    public String createConfig(String cf_code, String cf_flag, String cf_val, String cf_explain,String type){
        String sign = "";
        Integer cf_flag1 = null;
        if(!StringUtils.isEmpty(cf_flag)){
            cf_flag1 = Integer.parseInt(cf_flag);
        }

        //新增
        if("a".equals(type)){
            configServices.createConfig(cf_code,cf_flag1,cf_val,cf_explain);
            sign = "add";
        }else{//修改
            configServices.updateConfig(cf_code,cf_flag1,cf_val,cf_explain);
            sign = "edit";
        }
        return "redirect:addConfig?type=a&sign="+sign; //重定向
    }

    @RequestMapping("/addConfig")
    public String addConfig(Model model, String cf_code, String cf_flag, String cf_val,String cf_explain,String type,String sign){
        model.addAttribute("cf_code",cf_code);
        model.addAttribute("cf_flag",cf_flag);
        model.addAttribute("cf_val",cf_val);
        model.addAttribute("cf_explain",cf_explain);
        model.addAttribute("type",type);
        model.addAttribute("sign",sign);//标识是修改还是编辑
        return view+"addConfig";
    }

    /***
     * @param cf_code
     */
    @ResponseBody
    @RequestMapping("/deleteRow")
    public void deleteRow(String cf_code){
        configServices.deleteRow(cf_code);
    }

    //查询配置的条码号的长度
    @ResponseBody
    @RequestMapping("/barcodeLength")
    public Map barcodeLength(){
        Map map = new HashMap();
        Config barConfig = configServices.getBarcodeLength(barcodeLength);//配置文件取的条码号长度
        Config dateConfig = configServices.getBarcodeLength(sampleDateValidation);//配置采样日期，校验日期输入

        map.put("barcodeLength",barConfig.getCf_val());
        map.put("sampleDateValidation",dateConfig.getCf_val());

        return map;
    }



}
