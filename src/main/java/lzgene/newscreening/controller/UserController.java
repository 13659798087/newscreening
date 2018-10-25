package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.UserInfo;
import lzgene.newscreening.model.UserRoleMenu;
import lzgene.newscreening.services.RoleServices;
import lzgene.newscreening.services.UserRoleMenuServices;
import lzgene.newscreening.services.UserServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/userPage")
public class UserController {

    @Resource
    private UserServices userServices;

    @Resource
    private UserRoleMenuServices userRoleMenuServices;

    @Resource
    private RoleServices roleServices;

    private String view = "userPage/";

    private static String secretKey = "9ba45bfd500642328ec03ad8ef1b6e75";

    //UserController.class必须针对当前类，定义一个当前类的全局静态日志操作对象
    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/getUserInfo")
    public String getUserInfo(){
        return view+"userInfo";
    }

    @ResponseBody
    @RequestMapping("/getUserRole")
    public Map<String,Object> getUserRole(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder,String userName,String organizationName,HttpServletRequest request){
        //在缓存session中取医院的id
        String organizationId = (String) request.getSession().getAttribute("organizationId");
        String userId = (String) request.getSession().getAttribute("userId");

        List<UserInfo> userInfo = new ArrayList<UserInfo>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<UserInfo> pageResults = new PageResults<UserInfo>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        userInfo = userServices.getUserMessage((pageNo-1)*pageSize+1, pageNo*pageSize,userName,organizationName);

        long totalCount = userServices.getUserCount((pageNo-1)*pageSize+1, pageNo*pageSize,userName,organizationName);

        pageResults.setResult(userInfo);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    @RequestMapping("/createUser")
    public String creatUser(HttpServletRequest request,String userId,String userName,String password,String dayLoginError,String organizationId,String roles) {
        String sign = "";

        if(StringUtils.isEmpty(organizationId)){
            organizationId = (String) request.getSession().getAttribute("organizationId");
        }

        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        //password = des.encode(password);//加密再存数据库

        //修改,不可以更改密码
        if(!StringUtils.isEmpty(userId)){
            userServices.updateUser(userId,userName,password,dayLoginError,organizationId);
            //删除之前的关系后建立
            roleServices.deleteRela(userId);
            sign = "edit";
        }else{
            //新增
            userId = Guid.GenerateGUID();

            //当前创建人的userId,作为新创建记录的parentId,request.getSession().getAttribute("key")返回的是Object类型，
            // 要强转成字符串类型，在前面+(数据类型)
            String parentId = (String) request.getSession().getAttribute("userId");

            userServices.creatUser(userId,userName,password,organizationId,parentId);
            sign = "add";
        }
        String[] a = roles.split(",");
        for(int i=0;i<a.length;i++){
            String id = Guid.GenerateGUID();
            String roleId = a[i];
            roleServices.insertRela(id,userId,roleId);
        }

        return "redirect:addUser?type=a&sign="+sign; //重定向
    }

    @RequestMapping("/addUser")
    public String addUser(Model model,String userName,String password,String type,String organizationId,
                          String userId,String dayLoginError,String sign){

        model.addAttribute("userName",userName);
        model.addAttribute("password",password);
        model.addAttribute("organizationId",organizationId);
        model.addAttribute("userId",userId);
        model.addAttribute("dayLoginError",dayLoginError);

        model.addAttribute("type",type);
        model.addAttribute("sign",sign);//标识是修改还是编辑
        return view+"addUser";
    }


    @ResponseBody
    @RequestMapping("/validateUser")
    public Integer validateUser(HttpServletRequest request,String userName, String organizationId){

        int i = userServices.validateUser(userName,organizationId);
        if(i>0)
            i=1;
        else
            i=0;
        return i;
    }

    @ResponseBody
    @RequestMapping("/getRoleByUserId")
    public List<UserRoleMenu> getRoleByUserId(String userId){

        List<UserRoleMenu> listRole = roleServices.getRoleByUserId(userId);

        return listRole;
    }

    /**
     * 根据userId删除用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public void deleteUser(String userId){
        userServices.deleteUser(userId);
    }

    //修改密码页面
    @RequestMapping("/updatePassword")
    public String updatePassword(Model model,HttpServletRequest request,String x) {
        String userName = (String) request.getSession().getAttribute("userName");
        UserInfo u = userServices.userloginMessage(userName);

        String password=u.getPassword();

        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        //password = des.decode(password);//解密

        model.addAttribute("password",password);
        model.addAttribute("userName",userName);

        if("0".equals(x)){
            model.addAttribute("signLogin","0");
        }

        return view+"updatePassword";
    }

    //确定修改密码
    @ResponseBody
    @RequestMapping("/sureChangePsw")
    public String sureChangePsw(Model model,String userName,String newPassword,String oldPassword) {

        //MD5对密码加密
        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        //newPassword = des.encode(newPassword);//加密
        //oldPassword = des.encode(oldPassword);//加密

        String a = "";
        int i =  userServices.changePsw(userName,newPassword,oldPassword);
        if(i==1)
            a="1";
        else
            a="0";
        return a;
    }

    @RequestMapping("/abc")
    public String abc( Model model) {
        /*List<UserInfo> us = userServices.getUsers();
        for(UserInfo u:us){
            EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
            userServices.updatePsw(u.getUserId(),des.encode(u.getPassword()));
        }*/
        return "数据库密码加密成功";
    }

}

