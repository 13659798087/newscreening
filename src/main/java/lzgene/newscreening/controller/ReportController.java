package lzgene.newscreening.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import lzgene.newscreening.entiry.Report;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.services.CombineServices;
import lzgene.newscreening.services.LogManagementServices;
import lzgene.newscreening.services.ReportServices;
import lzgene.newscreening.util.JasperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/reportQuery")
public class ReportController {

    @Resource
    private ReportServices reportServices;

    @Resource
    private CombineServices combineServices;

    @Resource
    private JasperUtil jasperUtil;

    @Resource
    private LogManagementServices logManagementServices;


    @Value("${filePath}")
    private String filePath;

    @RequestMapping("/report")
    public String report(){
        return "reportQuery/report";
    }

    @RequestMapping("/reportManage")
    public String reportManage(){
        return "reportQuery/reportManage";
    }

    @RequestMapping("/printReport")
    public void myprint(HttpServletResponse response,String c_id,String c_code,String authorHospital) throws Exception {
        String fileName = "";

        List<Combine> combine = combineServices.getCombine();
        for(Combine c : combine){
            if(c_code.equals(c.getC_code())){
                fileName = c.getC_rpt()+".jasper";//拼接文件后缀pdf,此处是下载pdf文件
            }
        }

        InputStream in = genPdf(c_id,fileName,authorHospital);
        response.setContentType("application/pdf; charset=UTF-8");
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[512];
        if( in!=null ){
            while ((in.read(b))!=-1) {
                out.write(b);
            }
        }
        out.flush();
        in.close();
        out.close();
    }


    public InputStream genPdf(String c_id,String fileName,String authorHospital) {
        List<Report> userLs = new ArrayList<Report>();
        Map<String, Object> map = new HashMap<String, Object>();

        /*map.put("cCombine", "ct"); map.put("cSid", "C1700530");*//*map.put("c_combine", "cl"); map.put("c_sid", "20170967");*///map.put("c_id", "ts180529001");//map.put("c_id", " wc20180529002");// map.put("c_id", "dp20180529001");
        map.put("c_id", c_id);
        map.put("authorHospital", authorHospital);


        return jasperUtil.exportPdfDir(fileName, map, userLs);
    }


    @ResponseBody
    @RequestMapping("/downLoadZipFile")
    public void downLoadZipFile(String list,String authorHospital,HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=pdf.zip");
        List<Combine> combine = combineServices.getCombine();
        String[] array1 = list.split(",");
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(response.getOutputStream());
            for(int i=0;i<array1.length;i++) {
                String[] array2 = array1[i].split(":");
                String c_id = array2[0];
                String c_code = array2[1];
                String c_sid = array2[2];
                String p_name = array2[3];
                String fileName="";
                for (Combine c : combine) {
                    if (c_code.equals(c.getC_code())) {
                        fileName = c.getC_rpt() + ".jasper";
                    }
                }
                InputStream input = genPdf(c_id, fileName,authorHospital);
                zos.putNextEntry(new ZipEntry(c_sid+p_name+".pdf")) ; //压缩文件名称 设置ZipEntry对象
                //zos.setComment("www.mldnjava.cn") ;  // 设置注释
                int temp = 0 ;
                while((temp=input.read())!=-1){ // 读取内容
                    zos.write(temp) ;    // 压缩输出
                }

                input.close() ; // 关闭输入流
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != zos){ // 关闭流
                    zos.flush();
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //批量下载pdf文件
    /*@ResponseBody
    @RequestMapping("/batchPrint")
    public void batchPrint(HttpServletResponse response,String list) throws Exception {
        String fileName = "";
        List<Combine> combine = combineServices.getCombine();
        String[] array1 = list.split(",");
        for(int i=0;i<array1.length;i++){
            String[] array2=array1[i].split(":");
            String c_id = array2[0];
            String c_code = array2[1];
            for(Combine c : combine){
                if(c_code.equals(c.getC_code())){
                    fileName = c.getC_rpt()+".jasper";//拼接文件后缀pdf,此处是下载pdf文件
                }
            }
            InputStream in = genPdf(c_id,fileName);//得到流
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
            reportServices.updatePrintFlag(c_id);//打印之后更改状态
        }

    }*/

    @ResponseBody
    @RequestMapping("/dpPrint")
    public void dpPrint( OutputStream outputStream, boolean paginate,String list,String authorHospital) {
        List<InputStream> streamOfPDFFiles = new ArrayList<InputStream>();

        String fileName = "";
        List<Combine> combine = combineServices.getCombine();

        String[] array1 = list.split(",");

        String paper_size="";

        for(int i=0;i<array1.length;i++){
            String[] array2=array1[i].split(":");
            String c_id = array2[0];
            String c_code = array2[1];

            paper_size = array2[2];

            for(Combine c : combine){
                if(c_code.equals(c.getC_code())){
                    fileName = c.getC_rpt()+".jasper";//拼接文件后缀pdf,此处是下载pdf文件
                }
            }
            InputStream in = genPdf(c_id,fileName,authorHospital);//得到流
            streamOfPDFFiles.add(in);
        }

        //Document document = new Document();
        //Document document = new Document(PageSize.A5.rotate());//rotate()  横向打印
        Document document = null;
        switch (paper_size){
            case "A4":
                document = new Document();
                break;
            case "A5":
                document = new Document(PageSize.A5.rotate());//rotate()  横向打印
                break;
        }

        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 10);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,"" +currentPageNumber + " of " + totalPages, 598,0, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }


        //打印之后更改状态
        for(int i=0;i<array1.length;i++){
            String[] array2=array1[i].split(":");
            String c_id = array2[0];
            reportServices.updatePrintFlag(c_id);
        }

    }



    @ResponseBody
    @RequestMapping("/deleteReport")
    public void deleteReport(HttpServletRequest request,String list,String type,String ipAddress,String loggerName){

        String userName =(String) request.getSession().getAttribute("userName");

        String[] array = list.split(",");
        for(int i=0;i<array.length;i++){

            String[] array1=array[i].split(":");
            String c_sid = array1[0];
            String c_code = array1[1];
            String patients = array1[2];
            String hospital = array1[3];
            String combine = array1[4];
            String barcode = array1[5];
            String sid = array1[6];
            String phone =  array1[7];

            reportServices.deleteResulto(c_sid,c_code);
            reportServices.deleteCheckinfo (c_sid,c_code);
            reportServices.deletePatients (c_sid,c_code);


            if(barcode.equals("null")){
                barcode = null;
            }
            if(phone.equals("null")){
                phone = null;
            }
            logManagementServices.recordLog(loggerName,type,userName,ipAddress,patients,hospital,combine,barcode,sid,phone);

        }
    }



}
