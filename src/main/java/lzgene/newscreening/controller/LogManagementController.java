package lzgene.newscreening.controller;


import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.LogManagement;
import lzgene.newscreening.model.UserInfo;
import lzgene.newscreening.services.LogManagementServices;
import lzgene.newscreening.util.Paging;
import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/logManagement")
public class LogManagementController {

   @Resource
   private LogManagementServices logManagementServices;

   private String view = "logManagement/";

   @RequestMapping("/logger")
   public String logger(){
      return view + "logger";
   }

   @ResponseBody
   @RequestMapping("/getLogger")
   public Map<String,Object> getLogger(String pageNumber, String rowNumber, String sortName,String sortOrder,
                                       String time1,String time2,String loggerName,String patients,String barcode,
                                       String hospital,String combine,String type,String p_tel1,HttpServletRequest request){

       Timestamp time_1 = null;
       Timestamp time_2 = null;
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       if(!StringUtils.isEmpty(time1)){
           time_1 = Timestamp.valueOf(time1 +" 00:00:00");
       }
       if(!StringUtils.isEmpty(time2)){
           time_2 = Timestamp.valueOf(time2 +" 23:59:59");
       }

      List<LogManagement> logManagement = new ArrayList<LogManagement>();
      int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
      int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
      String orderBy = sortName == null  ? "updaterTime":sortName;
      String order = sortOrder == null  ? PageResults.DESC:sortOrder;

      PageResults<LogManagement> pageResults = new PageResults<LogManagement>();
      pageResults.setPageNo(pageNo);
      pageResults.setPageSize(pageSize);
      pageResults.setOrderBy(orderBy);
      pageResults.setOrder(order);

      HttpSession session = request.getSession();
      if(StringUtils.isEmpty(hospital)){ //hospital传过来是医院id
          hospital = (String) session.getAttribute("organizationId");
      }

      logManagement = logManagementServices.getLogger((pageNo-1)*pageSize+1,
              pageNo*pageSize,time_1,time_2,loggerName,patients,barcode,hospital,combine,type,p_tel1);

      long totalCount = logManagementServices.getLoggerCount((pageNo-1)*pageSize+1,
              pageNo*pageSize,time_1,time_2,loggerName,patients,barcode,hospital,combine,type,p_tel1);

      pageResults.setResult(logManagement);
      pageResults.setTotalCount(totalCount);

      return Paging.ajaxGrid(pageResults);
   }

 /*  public void recordLog (String loggerName,String type,String userName,String IPAddress,String patients,
                          String hospital,String combine,String barcode,String sid,String phone){
      logManagementServices.recordLog(loggerName,type,userName,IPAddress,patients,hospital,combine,barcode,sid,phone);
   }*/

    @ResponseBody
    @RequestMapping("/deleteLog")
    public void deleteLog(HttpServletRequest request,String list){

        String[] array = list.split(",");
        for(String id : array) {
            logManagementServices.deleteLog(id);
        }

    }


}
