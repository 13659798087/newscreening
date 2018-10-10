package lzgene.newscreening.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro的配置类
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfiguration {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login/login");
        //bean.setUnauthorizedUrl("/login/login");
        bean.setSuccessUrl("/login/index");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //filterChainDefinitionMap.put("/login/**", "anon");

        //filterChainDefinitionMap.put("/login/login", "anon");
        filterChainDefinitionMap.put("/login/createCode", "anon");//验证
        filterChainDefinitionMap.put("/login/loginValidate", "anon");//验证
        //filterChainDefinitionMap.put("/login/index", "anon");//验证
        filterChainDefinitionMap.put("/login/getLoginMessage", "anon");//验证

        filterChainDefinitionMap.put("/logout*","anon");
        filterChainDefinitionMap.put("/jsp/error.jsp*","anon");
        filterChainDefinitionMap.put("/jsp/index.jsp*","authc");

        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/Content/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");

        /*anon:匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤
        authc:如果没有登录会跳到相应的登录页面登录
        user:用户拦截器，用户已经身份验证/记住我登录的都可*/

        filterChainDefinitionMap.put("/", "authc");//

        //filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
        /*filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*.*", "authc");*/


        filterChainDefinitionMap.put("/home/**", "authc");
        filterChainDefinitionMap.put("/combine/**", "authc");
        filterChainDefinitionMap.put("/setmeal/**", "authc");
        filterChainDefinitionMap.put("/mb/**", "authc");
        filterChainDefinitionMap.put("/menu/**", "authc");
        filterChainDefinitionMap.put("/organization/**", "authc");
        filterChainDefinitionMap.put("/reportQuery/**", "authc");
        filterChainDefinitionMap.put("/role/**", "authc");
        filterChainDefinitionMap.put("/sendApplication/**", "authc");
        filterChainDefinitionMap.put("/signpic/**", "authc");
        filterChainDefinitionMap.put("/dataStatistics/**", "authc");
        filterChainDefinitionMap.put("/config/**", "authc");
        filterChainDefinitionMap.put("/authUser/**", "authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        //System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}