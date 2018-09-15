package lzgene.newscreening.services;

import lzgene.newscreening.dao.UserRoleMenuDao;
import lzgene.newscreening.model.UserRoleMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleMenuServices {

    @Resource
    private UserRoleMenuDao userRoleMenuDao;

    public List<Map<String, Object>> getLoginMessage(String userName) {
        return userRoleMenuDao.getLoginMessage(userName);
    }


}
