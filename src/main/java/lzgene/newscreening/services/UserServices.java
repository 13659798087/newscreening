package lzgene.newscreening.services;

import lzgene.newscreening.dao.UserDao;
import lzgene.newscreening.model.Organization;
import lzgene.newscreening.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServices {

    @Resource
    private UserDao userDao;

    public int loginValidate(String userName, String password) {
       return userDao.loginValidate(userName,password);
    }

    public void creatUser(String userId,String userName, String password,String organizationId,String parentId) {
        userDao.creatUser(userId,userName,password,organizationId,parentId);
    }

    public int validateUser(String userName, String organizationId) {
        return userDao.validateUser(userName,organizationId);
    }

    public void updateUser(String userId,String userName,String dayLoginError,String organizationId) {
        userDao.updateUser(userId,userName,dayLoginError,organizationId);
    }


    public UserInfo getUserInfo(String userName, String password) {
        return userDao.getUserInfo(userName,password);
    }

    public List<UserInfo> getUserByParentId(String userId) {
        return userDao.getUserByParentId(userId);
    }

    public List<UserInfo> getUserMessage( int pageNo, int pageSize, String userName, String organizationName) {
        return userDao.getUserMessage(pageNo,pageSize,userName,organizationName);
    }

    public long getUserCount(int pageNo, int pageSize, String userName, String organizationName) {
        return userDao.getUserCount(pageNo,pageSize,userName,organizationName);
    }

    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    public UserInfo userloginMessage(String userName) {
        return userDao.userloginMessage(userName);
    }

    public void addOneCount(String userName) {
        userDao.addOneCount(userName);
    }

    public void resetDayLoginError(Integer dayLimitLoginError) {
        userDao.resetDayLoginError(dayLimitLoginError);
    }

    public int changePsw(String userName, String newPassword, String oldPassword) {
        return userDao.changePsw(userName, newPassword, oldPassword);
    }

    public Organization getOrganizationMessage(String organizationId) {
        return userDao.getOrganizationMessage(organizationId);
    }

    public Organization getAuthorizeName(String organizationId) {
        return userDao.getAuthorizeName(organizationId);
    }


    public List<UserInfo> getUsers() {
        return userDao.getUsers();
    }

    public void updatePsw(String userId, String psw) {
        userDao.updatePsw(userId,psw);
    }


}
