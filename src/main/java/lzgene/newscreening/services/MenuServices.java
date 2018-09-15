package lzgene.newscreening.services;


import lzgene.newscreening.dao.MenuDao;
import lzgene.newscreening.model.Menu;
import lzgene.newscreening.model.UserRoleMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MenuServices {

    @Resource
    private MenuDao menuDao;

    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }


    public List<Menu> getMenuByNum(String menuName, int pageNo, int pageSize) {
        return menuDao.getMenuByNum(menuName,pageNo,pageSize);
    }

    public long getMenuCount(String menuName, int pageNo, int pageSize) {
        return menuDao.getMenuCount(menuName,pageNo,pageSize);
    }

    public void insertMenu(String id, String roleId, String menuId) {
        menuDao.insertMenu(id,roleId,menuId);
    }

    public List<UserRoleMenu> getRoleByUserId(String roleId) {
        return menuDao.getRoleByUserId(roleId);
    }

    public List<Map<String,Object>> getMenuTree() {
        return menuDao.getMenuTree();
    }


}
