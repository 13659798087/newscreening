package lzgene.newscreening.services;

import lzgene.newscreening.dao.LogManagementDao;
import lzgene.newscreening.model.LogManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class LogManagementServices {

    @Resource
    private LogManagementDao logManagementDao;

    public void recordLog(String loggerName,String type,String userName,String ipAddress,String patients,String hospital,String combine,String barcode,String sid,String phone) {
        logManagementDao.recordLog(loggerName,type,userName,ipAddress,patients,hospital,combine,barcode,sid,phone);
    }

    public List<LogManagement> getLogger(int pageNo,int pageSize,Timestamp time_1,Timestamp time_2, String loggerName,
                                         String patients,String barcode,String organizationId,String combine,String type,String p_tel1) {
        return logManagementDao.getLogger(pageNo,pageSize,time_1,time_2,loggerName,patients,barcode,organizationId,combine,type,p_tel1);
    }

    public long getLoggerCount(int pageNo,int pageSize,Timestamp time_1,Timestamp time_2, String loggerName,
                               String patients,String barcode,String organizationId,String combine,String type,String p_tel1) {
        return logManagementDao.getLoggerCount(pageNo,pageSize,time_1,time_2,loggerName,patients,barcode,organizationId,combine,type,p_tel1);
    }

    public void deleteLog(String id) {
        logManagementDao.deleteLog(id);
    }


}
