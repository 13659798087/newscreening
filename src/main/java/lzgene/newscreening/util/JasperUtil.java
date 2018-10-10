package lzgene.newscreening.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import lzgene.newscreening.datasource.DDSHolder;
import lzgene.newscreening.datasource.DynamicDataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;

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





    public BufferedImage exportPdfDir1(String fileName, Map<String, Object> maps, List<?> ls) {
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


            //生成图片
            JRGraphics2DExporter exporter = new JRGraphics2DExporter();//创建graphics输出器
            //创建一个影像对象
            BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth() * 4, jasperPrint.getPageHeight() * 4, BufferedImage.TYPE_INT_RGB);
            //取graphics
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            //设置相应参数信息
            exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
            exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, Float.valueOf(4));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.exportReport();
            g.dispose();//释放资源信息
            //这里的bufferedImage就是最终的影像图像信息,可以通过这个对象导入到cm中了.
            //ImageIO.write(bufferedImage, "JPEG", new File("d:/aa.jpg"));


            //ImageIO.write(bufferedImage,"jpg",out);


            out.close();


            conn.close();
            //return new ByteArrayInputStream(out.toByteArray());

            return bufferedImage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
