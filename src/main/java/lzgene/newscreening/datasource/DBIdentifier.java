package lzgene.newscreening.datasource;
//2.定义动态数据源
//        1)  首先增加一个数据库标识类，用于区分不同的数据库访问。
//
//        由于我们为不同的project创建了单独的数据库，所以使用项目编码作为数据库的索引。而微服务支持多线程并发的，采用线程变量。
public class DBIdentifier {

    /**
     * 用不同的工程编码来区分数据库
     */
    private static ThreadLocal<String> projectCode = new ThreadLocal<String>();
    public static String getProjectCode() {
        return projectCode.get();
    }
    public static void setProjectCode(String code) {
        projectCode.set(code);
    }


}
