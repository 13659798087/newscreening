/*
package lzgene.newscreening.servlet;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JasperPrintServlet extends HttpServlet {

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

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext context = this.getServletConfig().getServletContext();

        File reportFile = new File(context.getRealPath("D:\\download\\iReport-5.6.0\\iReport-5.6.0\\platform9\\lib\\dp-yichun.jasper"));
        if (!reportFile.exists())
            throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");

        Map parameters = new HashMap();
        parameters.put("ReportTitle", "Address Report");
        parameters.put("BaseDir", reportFile.getParentFile());

        JasperPrint jasperPrint = null;

        try
        {
            String filenurl = "D:\\download\\iReport-5.6.0\\iReport-5.6.0\\platform9\\lib\\dp-yichun.jasper";
            InputStream file = new FileInputStream(filenurl);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

            Connection conn;
            Class.forName(driverName);//加载驱动
            conn = DriverManager.getConnection(url,username,password);//连接数据库

            jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, conn);
        }
        catch (JRException e)
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JasperReports - Web Application Sample</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");

            out.println("<body bgcolor=\"white\">");

            out.println("<span class=\"bnew\">JasperReports encountered this error :</span>");
            out.println("<pre>");

            e.printStackTrace(out);

            out.println("</pre>");

            out.println("</body>");
            out.println("</html>");

            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (jasperPrint != null)
        {
            response.setContentType("application/octet-stream");
            ServletOutputStream ouputStream = response.getOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
            oos.writeObject(jasperPrint);
            oos.flush();
            oos.close();

            ouputStream.flush();
            ouputStream.close();
        }
        else
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JasperReports - Web Application Sample</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");

            out.println("<body bgcolor=\"white\">");

            out.println("<span class=\"bold\">Empty response.</span>");

            out.println("</body>");
            out.println("</html>");
        }
    }


}*/
