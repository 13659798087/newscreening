package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.AuthUser;
import lzgene.newscreening.services.AuthUserServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authUser")
public class AuthUserController {

    @Resource
    private AuthUserServices authUserServices;

    private String view = "authUser/";

    @RequestMapping("/authUserList")
    public String getAuthUserList(){

        return view + "authUserList";
    }

    @ResponseBody
    @RequestMapping("/authUserTable")
    public Map<String,Object> authUserTable(String pageNumber, String rowNumber, String sortName,
                                            String sortOrder, String au_name, HttpServletRequest request){

        List<AuthUser> authUser = new ArrayList<AuthUser>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<AuthUser> pageResults = new PageResults<AuthUser>();

        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        authUser = authUserServices.authUserTable(au_name,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = authUserServices.getAuthUserCount(au_name,(pageNo-1)*pageSize+1, pageNo*pageSize);
        pageResults.setResult(authUser);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    //将数据带到 新增、编辑页面
    @RequestMapping("/addAuthUser")
    public String addAuthUser(Model model,String au_code,String au_name,String au_address,String au_linkman,String au_tel,
                              String au_position,String au_registration_code,String au_key,String au_remarks,String type){

        model.addAttribute("au_code",au_code);
        model.addAttribute("au_name",au_name);
        model.addAttribute("au_address",au_address);
        model.addAttribute("au_linkman",au_linkman);
        model.addAttribute("au_tel",au_tel);
        model.addAttribute("au_position",au_position);
        model.addAttribute("au_registration_code",au_registration_code);
        model.addAttribute("au_key",au_key);
        model.addAttribute("au_remarks",au_remarks);
        model.addAttribute("type",type);
        return view+"addAuthUser";

    }



    /**
     创建新模板
     */
    @RequestMapping("/createAuthUser")
    public String createAuthUser(Model model,String au_code,String au_name,String au_address,String au_linkman,String au_tel,
                                 String au_position,String au_registration_code,String au_key,String au_remarks){
        //新增
        if(StringUtils.isEmpty(au_code)){
            au_code = Guid.GenerateGUID();
            authUserServices.createAuthUser(au_code,au_name,au_address,au_linkman,au_tel,au_position,au_registration_code,au_key,au_remarks);
        }else{//修改
            authUserServices.updateAuthUser(au_code,au_name,au_address,au_linkman,au_tel,au_position,au_registration_code,au_key,au_remarks);
        }

        return "redirect:addAuthUser?type=a"; //重定向
    }


    /**
     删除
     */
    @ResponseBody
    @RequestMapping("/deleteAuthUser")
    public void deleteAuthUser(String au_code){
        authUserServices.deleteAuthUser(au_code);
    }

    /**
     验证
     */
    @ResponseBody
    @RequestMapping("/validateAuthUser")
    public Integer validateAuthUser(String au_name){
        int i = authUserServices.validateAuthUser(au_name);
        if(i>0)
            i=1;
        else
            i=0;
        return i;
    }


}
