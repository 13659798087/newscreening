package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Menu;
import lzgene.newscreening.services.MenuServices;
import lzgene.newscreening.util.Paging;
import lzgene.newscreening.util.SSO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuServices menuServices;

    private String  view ="menu/";

    @RequestMapping("/listMenu")
    public String listMenu(){
        //查出所有菜单，还有此用户是什么角色
        List<Menu> menu = menuServices.getAllMenu();
        return view + "listMenu";
    }

    @ResponseBody
    @RequestMapping("/getAllMenu")
    public List<Menu> getAllRole(){
        //查出所以角色，还有此用户是什么角色
        List<Menu> menu = menuServices.getAllMenu();
        return menu;
    }

    @ResponseBody
    @RequestMapping("/getTableMenu")
    public Map<String,Object> getUserRole(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder, String menuName, HttpServletRequest request){

        List<Menu> menu = new ArrayList<Menu>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Menu> pageResults = new PageResults<Menu>();
        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        menu = menuServices.getMenuByNum(menuName,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = menuServices.getMenuCount(menuName,(pageNo-1)*pageSize+1, pageNo*pageSize);

        pageResults.setResult(menu);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }




}
