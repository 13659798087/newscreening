package lzgene.newscreening.services;

import lzgene.newscreening.entiry.ConfigReport;
import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.entiry.Report;
import lzgene.newscreening.dao.ReportDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportServices {

    @Resource
    private ReportDao reportDao;

    public void updatePrintFlag(String c_id) {
        reportDao.updatePrintFlag(c_id);
    }


    public List<ConfigReport> getStatisticsMsg() {
        return reportDao.getStatisticsMsg();
    }


    public ConfigReport getConfigReportById(String id) {
        return reportDao.getConfigReportById(id);
    }

    public void deleteResulto(String c_sid, String c_code) {
        reportDao.deleteResulto(c_sid,c_code);
    }

    public void deleteCheckinfo(String c_sid, String c_code) {
        reportDao.deleteCheckinfo(c_sid,c_code);
    }

    public void deletePatients(String c_sid, String c_code) {
        reportDao.deletePatients(c_sid,c_code);
    }


}
