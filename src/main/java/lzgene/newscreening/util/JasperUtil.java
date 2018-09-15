package lzgene.newscreening.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import lzgene.newscreening.datasource.DDSHolder;
import lzgene.newscreening.datasource.DynamicDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 打印工具类
 *
 * @author haojiahong
 *
 * @createtime：2015-8-13 下午4:40:27
 *
 *
 */
@Component
public class JasperUtil {

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${filePath}")
    private String filePath;


    public InputStream exportPdfDir(String fileName, Map<String, Object> maps, List<?> ls) {
        try {

            JRDataSource ds = new JRBeanCollectionDataSource(ls, false);

            //String filenurl = "D:\\download\\iReport-5.6.0\\iReport-5.6.0\\platform9\\lib\\" + fileName;

            String filenurl = filePath  + fileName;

            InputStream file = new FileInputStream(filenurl);

            Connection conn;

            Class.forName(driverName);//加载驱动


            //去获取url
            DataSource dds = DDSHolder.instance().getDDS(SSO.getProjectCode());
            String url = dds.getUrl();

            conn = DriverManager.getConnection(url,username,password);//连接数据库

            JasperPrint jasperPrint = JasperFillManager.fillReport(file, maps,conn);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            conn.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
