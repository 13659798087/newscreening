package lzgene.newscreening.dbmgr;

import lzgene.newscreening.util.SSO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目数据库管理。提供根据项目编码查询数据库名称和IP的接口。
 * @author elon
 * @version 2018年2月25日
 */

public class ProjectDBMgr {

    /**
     * 保存项目编码与数据名称的映射关系。这里是硬编码，实际开发中这个关系数据可以保存到redis缓存中；
     * 新增一个项目或者删除一个项目只需要更新缓存。到时这个类的接口只需要修改为从缓存拿数据。
     */


    private Map<String, String> dbNameMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库IP的映射关系。
     */
    private Map<String, String> dbIPMap = new HashMap<String, String>();

    private ProjectDBMgr() {
    /*
        dataCode =ycfy,gzfy
        dataName = lzcqwsycfy,lzcqwsgzfy
        dataIp = 127.0.0.1
    */
        String[] aa = SSO.getDataCode().split(",");
        String[] bb = SSO.getDataName().split(",");

        for(int i=0;i<aa.length;i++ ){
            dbNameMap.put(aa[i],bb[i]);
            dbIPMap.put(aa[i],SSO.getDataIp());
        }


        //本地
//         dbNameMap.put("ycfy", "lzcqwsycfy");
//         dbNameMap.put("gzfy", "lzcqwsgzfy");
//         dbIPMap.put("ycfy", "127.0.0.1");
//         dbIPMap.put("gzfy", "127.0.0.1");


        //192.168.1.167
        /*dbNameMap.put("ycfy", "lzcqwsycfy");
        dbNameMap.put("gzfy", "lzcqwsgzfy");
        dbIPMap.put("ycfy", "192.168.1.167");
        dbIPMap.put("gzfy", "192.168.1.167");*/



    }
    public static ProjectDBMgr instance() {
        return ProjectDBMgrBuilder.instance;
    }
    // 实际开发中改为从缓存获取
    public String getDBName(String projectCode) {
        if (dbNameMap.containsKey(projectCode)) {
            return dbNameMap.get(projectCode);
        }
        return "";
    }
    //实际开发中改为从缓存中获取
    public String getDBIP(String projectCode) {
        if (dbIPMap.containsKey(projectCode)) {
            return dbIPMap.get(projectCode);
        }
        return "";
    }
    private static class ProjectDBMgrBuilder {
        private static ProjectDBMgr instance = new ProjectDBMgr();
    }
}
