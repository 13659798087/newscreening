/*
package lzgene.newscreening.servlet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

@Controller
@RequestMapping("/print")
public class HelloSwingApplet extends JApplet {

        private URL url = null;
        private JasperPrint jasperPrint = null;

        public HelloSwingApplet() {

        }

        public void init() {

            String strUrl = getParameter("REPORT_URL");
            if (strUrl != null)
            {
                try
                {
                    url = new URL(getCodeBase(), strUrl);
                }
                catch (Exception e)
                {
                    StringWriter swriter = new StringWriter();
                    PrintWriter pwriter = new PrintWriter(swriter);
                    e.printStackTrace(pwriter);
                    JOptionPane.showMessageDialog(this, swriter.toString());
                }
            }
            if (url != null)
            {
                try
                {
                    if (jasperPrint == null)
                    {
                        jasperPrint = (JasperPrint) JRLoader.loadObject(url);
                    }
                    if (jasperPrint != null)
                    {
                    //ViewerFrame viewerFrame = new ViewerFrame(this.getAppletContext(), jasperPrint);
                    //viewerFrame.show();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Empty report.");
                    }
                }
                catch (Exception e)
                {
                    StringWriter swriter = new StringWriter();
                    PrintWriter pwriter = new PrintWriter(swriter);
                    e.printStackTrace(pwriter);
                    JOptionPane.showMessageDialog(this, swriter.toString());
                }
            }

        }


        @RequestMapping("/printReport")
        public void printReport() throws Exception{
            try {
                //JRSaver.saveObject(jasperPrint, "PrintServiceReport.jrprint");
                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                printRequestAttributeSet.add(MediaSizeName.ISO_A4);

                PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
                //指定打印机，不会弹出打印机选择框（PDFill PDF&Image Writer是我的打印机）
                printServiceAttributeSet.add(new PrinterName("PDFill PDF&Image Writer", null));

                JRPrintServiceExporter exporter = new JRPrintServiceExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

                exporter.exportReport();
            } catch (JRException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }

*/
