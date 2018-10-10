package lzgene.newscreening.controller;

import lzgene.newscreening.model.Organization;
import lzgene.newscreening.model.UserInfo;
import lzgene.newscreening.services.OrganizationServices;
import lzgene.newscreening.services.UserRoleMenuServices;
import lzgene.newscreening.services.UserServices;
import lzgene.newscreening.util.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserServices userServices;
    @Resource
    private UserRoleMenuServices userRoleMenuServices;
    @Resource
    private OrganizationServices organizationServices;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private static String secretKey = "9ba45bfd500642328ec03ad8ef1b6e75";

    private String view = "login/";

    @Value("${errorFreezing}")
    private Integer errorFreezing;

    /**
     * shiro
     */
    //宜春登录地址：http://localhost:8083
    //赣州登录地址：http://localhost:8083/login/login?code=gzfy
    //赣州登录地址：http://localhost:8083?code=gzfy
    @RequestMapping("/login")
    public String login(String code,HttpServletRequest request,Model model) {

        //code = (String) request.getSession().getAttribute("projectCode");

        if(code==null){ //为了解决宜春正在用的系统不用更改访问地址为进行的判断
            request.getSession().setAttribute("projectCode","ycfy");
        }else{
            request.getSession().setAttribute("projectCode",code);
        }

        model.addAttribute("organizationName",organizationServices.organizationName());//筛查中心名称

        return view+"login";//返回登录页面
    }

    /*@RequestMapping("/login")
    public String login(String code,HttpServletRequest request,Model model) {
        request.getSession().setAttribute("projectCode",code);//到时宜春的登录地址还会更改，这个就开出来，前面的if else就注释掉
        model.addAttribute("organizationName",organizationServices.organizationName());//筛查中心名称
        return view+"login";//返回登录页面
    }*/

    @RequestMapping("/index")
    public String loginUser(Model model, String username,String password,HttpServletRequest request) {
        HttpSession session = request.getSession();

        //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
        //password = des.encode(password);//加密

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken); //完成登录
            UserInfo user=(UserInfo) subject.getPrincipal();
            session.setAttribute("user", user);

            //设置session有效期 以秒为单位
            session.setMaxInactiveInterval(60 * 60 * 12);//设置session有效时间为12小时，session有效时间单位是分
            //int a = request.getSession().getMaxInactiveInterval();//可以查看session时间

            Organization organization = new Organization();

            Organization authorizeorganization = null;

            String AuthorizeName="";
            String AuthorizeId="";
            if(user!=null){
                session.setAttribute("userId",user.getUserId());
                session.setAttribute("userName",user.getUserName());
                session.setAttribute("organizationId",user.getOrganizationId());
                session.setAttribute("parentId",user.getParentId());

                organization = userServices.getOrganizationMessage( user.getOrganizationId() );
                authorizeorganization = userServices.getAuthorizeName( user.getOrganizationId() );
                AuthorizeName = authorizeorganization.getName();
                AuthorizeId =authorizeorganization.getId();


                if(organization!=null){
                    session.setAttribute("organizationName",organization.getName());
                    session.setAttribute("level",organization.getLevel());
                    session.setAttribute("AuthorizeName",AuthorizeName);//授权单位,指筛查中心
                    session.setAttribute("AuthorizeId",AuthorizeId);//授权单位Id

                }

            }

            model.addAttribute("userName",username);
            model.addAttribute("organizationName",organization.getName());
            model.addAttribute("password",password);
            model.addAttribute("AuthorizeName",AuthorizeName);

            if(user!=null){
                if(StringUtils.isEmpty(user.getUpdateTime()) ){//如果更新时间为null,说明此用户没登录过，就跳到修改密码的页面
                    model.addAttribute("firstPassword","first");//如果没修改过密码
                }
            }

            return view+"index";

        } catch(Exception e) {
            return view+"login";//返回登录页面
        }
    }



    /**
     *登录成功获取用户菜单权限
    */
    @ResponseBody
    @RequestMapping("/getLoginMessage")
    public Map<String,Object> getLoginMessage(String userName,String password){
       // List<Map<String,Object>> listResult = new ArrayList<HashMap<String,Object>() ;
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String, Object>> userRoleMenu = userRoleMenuServices.getLoginMessage(userName);
        userRoleMenu = Util.DataToTree(userRoleMenu, "parentId", null, "menuId",
                "children");
        result.put("userRoleMenu",userRoleMenu);
        return result;
    }

    @RequestMapping("/createCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        CaptchaUtil util = CaptchaUtil.Instance();
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        request.getSession().setAttribute("code", code);
        // 输出打web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/loginValidate")
    public Map loginValidate(HttpServletRequest request,String username,String password,String validCode) {

        HttpSession session = request.getSession();
        Map map = new HashMap();
        int errorCount = 0;
        UserInfo us = userServices.userloginMessage(username);
        String codeSession = (String) session.getAttribute("code");

        if(!validCode.equalsIgnoreCase(codeSession)){
            map.put("msg",3);//验证码错误
        }
        else if(us==null){
            map.put("msg",1); //用户名不存在
        }
        else if(us!=null){
            //用户存在，判断该账号是否被锁定
            errorCount = us.getDayLoginError();
            if(errorCount >= errorFreezing){//每天登录错误次数与设定的10次对比
                map.put("msg",2);//账号被锁定
            }else{
                //EncryptUtil des = new EncryptUtil(secretKey, "utf-8");
                //password = des.encode(password);//加密再和数据库对比
                int i = userServices.loginValidate(username,password);
                if(i==0){//i=0,不存在此用户名密码，即用户名或密码不正确

                    UserInfo userlogin = userServices.userloginMessage(username);
                    if(null != userlogin){
                        userServices.addOneCount(username); //增加登录错误+1
                        int c = userServices.userloginMessage(username).getDayLoginError();//每天登录错误次数
                        if(c<=4){
                            map.put("msg",4);//用户名存在，密码输入不对，登录错误次数少于5，页面提示用户名或密码错误
                        }else if(c>4 && c<errorFreezing){
                            map.put("msg",5);//提示登录错误已c次，超过10次该账号将被锁定
                            map.put("errCount",c);
                        }
                    }

                }

            }
        }

        return map;
    }


    /**
     * 退出登录,session失效
     * @return
     */
  /*  @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        request.getSession().invalidate();
       // return "redirect:addUser?type=a"; //重定向
        return "redirect:login";

    }*/

    // 退出登录,session失效
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        String projectCode = SSO.getProjectCode();
        Subject subject = SecurityUtils.getSubject();
        //subject.logout();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }

        //request.getSession().invalidate();

        return "redirect:login?code="+projectCode;
    }

    /**
     * 验证码验证
     * @param session
     * @param code
     */
    /*private void checkCode(HttpSession session, String code) {
        String codeSession = (String) session.getAttribute("code");
        if (StringUtils.isEmpty(codeSession)) {
            log.error("没有生成验证码信息");
            throw new IllegalStateException("ERR-01000");
        }
        if (StringUtils.isEmpty(code)) {
            log.error("未填写验证码信息");
            throw new BussinessException("ERR-06018");
        }
        if (codeSession.equalsIgnoreCase(code)) {
            // 验证码通过
        } else {
            log.error("验证码错误");
            throw new BussinessException("ERR-06019");
        }
    }*/







}
