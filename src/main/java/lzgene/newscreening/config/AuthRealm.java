package lzgene.newscreening.config;

import lzgene.newscreening.model.UserInfo;
import lzgene.newscreening.services.UserServices;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class AuthRealm extends AuthorizingRealm{

    @Resource
    private UserServices userServices;
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String userName = utoken.getUsername();
       // String password = utoken.getPassword();
        //UserInfo user = userServices.getUserInfo(userName,password);
        UserInfo user;
        user = userServices.userloginMessage(userName);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        /*User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Module> modules = role.getModules();
                if(modules.size()>0) {
                    for(Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }*/
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermissions(permissions);//将权限放入shiro中.
        info.addStringPermissions(null);//改后之后替换上面的.
        return info;
    }



}