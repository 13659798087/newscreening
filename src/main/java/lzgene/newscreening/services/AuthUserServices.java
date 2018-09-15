package lzgene.newscreening.services;

import lzgene.newscreening.dao.AuthUserDao;
import lzgene.newscreening.model.AuthUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthUserServices {

    @Resource
    private AuthUserDao authUserDao;

    public List<AuthUser> authUserTable(String au_name, int pageNo, int pageSize) {
        return authUserDao.authUserTable(au_name,pageNo,pageSize);
    }

    public long getAuthUserCount(String au_name, int pageNo, int pageSize) {
        return authUserDao.getAuthUserCount(au_name,pageNo,pageSize);
    }

    public int validateAuthUser(String au_name) {
        return authUserDao.validateAuthUser(au_name);
    }

    public void createAuthUser(String au_code, String au_name, String au_address, String au_linkman, String au_tel, String au_position, String au_registration_code,String au_key,String au_remarks) {
        authUserDao.createAuthUser(au_code,au_name,au_address,au_linkman,au_tel,au_position,au_registration_code,au_key,au_remarks);
    }

    public void updateAuthUser(String au_code, String au_name, String au_address, String au_linkman, String au_tel, String au_position, String au_registration_code,String au_key, String au_remarks) {
        authUserDao.updateAuthUser(au_code,au_name,au_address,au_linkman,au_tel,au_position,au_registration_code,au_key,au_remarks);
    }

    public void deleteAuthUser(String au_code) {
        authUserDao.deleteAuthUser(au_code);
    }
}
