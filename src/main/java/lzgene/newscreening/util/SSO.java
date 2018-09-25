package lzgene.newscreening.util;

import lzgene.newscreening.datasource.DBIdentifier;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SSO {

    public static void setProjectCode() {
        //得到request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        DBIdentifier.setProjectCode( (String) request.getSession().getAttribute("projectCode"));
    }

    public static String getProjectCode() {
        //得到request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return  (String) request.getSession().getAttribute("projectCode");
    }

    public static String getDataCode() {
        return "ycfy,gzfy,test";//登录带的code,不同筛查中心带的code不同
    }

    public static String getDataName() {
        return "lzcqwsycfy,lzcqwsgzfy,lzcqwstest";//数据库名
    }

    public static String getDataIp() {
        return "localhost";//数据库ip  本地,167也是这样配置
    }

}
