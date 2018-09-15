package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Role;
import lzgene.newscreening.model.UserRoleMenu;
import lzgene.newscreening.services.MenuServices;
import lzgene.newscreening.services.RoleServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import lzgene.newscreening.util.SSO;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleServices roleServices;

    @Resource
    private MenuServices menuServices;

    private String view = "role/";

    @RequestMapping("/listRole")
    public String listRole(){
        //查出所以角色，还有此用户是什么角色
        List<Role> role = roleServices.getAllRole();
        return view+"listRole";
    }

    @ResponseBody
    @RequestMapping("/getTableRole")
    public Map<String,Object> getUserRole(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder, String roleName, HttpServletRequest request){

        List<Role> role = new ArrayList<Role>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Role> pageResults = new PageResults<Role>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        role = roleServices.getRoleByNum(roleName,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = roleServices.getRoleCount(roleName,(pageNo-1)*pageSize+1, pageNo*pageSize);

        pageResults.setResult(role);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    @ResponseBody
    @RequestMapping("/getAllRole")
    public List<Role> getAllRole(){
        //查出所以角色，还有此用户是什么角色
        List<Role> role = roleServices.getAllRole();
        return role;
    }

    @RequestMapping("/createRole")
    public String createRole(HttpServletRequest request,String roleId,String menus,String roleName,String roleLevel,String type){
        String sign = "";

        if(!StringUtils.isEmpty(roleId)){  //修改
            roleServices.updateRole(roleId,roleName,roleLevel);
            //删除之前的关系后建立
            roleServices.deleteRoleMune(roleId);
            sign = "edit";
        }else{ //新增
            roleId = Guid.GenerateGUID();
            roleServices.creatRole(roleId,roleName,roleLevel);
            sign = "add";
        }

        //插入中间表
        String[] a = menus.split(",");
        for(int i=0;i<a.length;i++){
            String id = Guid.GenerateGUID();
            String menuId = a[i];
            menuServices.insertMenu(id,roleId,menuId);
        }

        //重定向
        return "redirect:addRole?sign="+sign;
    }

    @RequestMapping("/addRole")
    public String addUser(Model model,String roleId,String type,String roleName,String roleLevel,String sign){
        model.addAttribute("roleName",roleName);
        model.addAttribute("roleLevel",roleLevel);
        model.addAttribute("roleId",roleId);
        model.addAttribute("type",type);

        model.addAttribute("sign",sign);

        return view+"addRole";
    }

    @ResponseBody
    @RequestMapping("/getMenuByRoleId")
    public List<UserRoleMenu> getRoleByUserId(String roleId){

        List<UserRoleMenu> listRole = menuServices.getRoleByUserId(roleId);

        return listRole;
    }

    //删除角色
    @ResponseBody
    @RequestMapping("/deleteRole")
    public void deleteRole(String roleId){
        // 删除角色
        roleServices.deleteRole(roleId);//根据id删除角色表的记录
        roleServices.deleteUserRole(roleId);//根据id删除用户角色关系表的记录
        roleServices.deleteRoleMenu(roleId);//根据id删除角色菜单关系表的记录
    }

    // 得到树形结构的数据
    @ResponseBody
    @RequestMapping("/getMenuTree")
    public List<Map<String, Object>> getMenuTree(Model model, HttpServletRequest request){
        String organizationId = (String)request.getSession().getAttribute("organizationId");

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        resultList = menuServices.getMenuTree();

        return resultList;
    }


}
