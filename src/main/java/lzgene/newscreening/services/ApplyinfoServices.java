package lzgene.newscreening.services;


import lzgene.newscreening.dao.ApplyinfoDao;
import lzgene.newscreening.entiry.ListApplication;
import lzgene.newscreening.entiry.SendApplication;
import lzgene.newscreening.model.Applyinfo;
import lzgene.newscreening.model.Organization;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ApplyinfoServices {

    @Resource
    private ApplyinfoDao applyinfoDao;

    public List<Applyinfo> getTableApplyinfo(String organizationId,String a_name, int pageNo, int pageSize, Timestamp a_lr_date1, Timestamp a_lr_date2,
                                            String a_barcode,String a_in_no,String a_flag) {
        return applyinfoDao.getTableApplyinfo(organizationId,a_name,pageNo,pageSize,a_lr_date1,a_lr_date2,a_barcode,a_in_no,a_flag);
    }

    public long getTableApplyinfoCount(String organizationId,String a_name, int pageNo, int pageSize, Timestamp a_lr_date1, Timestamp a_lr_date2,
                                       String a_barcode,String a_in_no,String a_flag) {

        return applyinfoDao.getTableApplyinfoCount(organizationId,a_name,pageNo,pageSize,a_lr_date1,a_lr_date2,a_barcode,a_in_no,a_flag);
    }

    public void createApplication(Applyinfo application) {
        applyinfoDao.createApplication(application);
    }

    public Applyinfo getRowById(String a_id) {
        return applyinfoDao.getRowById(a_id);
    }

    public void updateApplication(Applyinfo application) {
        applyinfoDao.updateApplication(application);
    }

    public void deleteRowById(String a_id) {
        applyinfoDao.deleteRowById(a_id);
    }

    public long getApplyinfoCount(String organizationId) {
        return applyinfoDao.getApplyinfoCount(organizationId);
    }

    public int validateBarcode(String a_barcode) {
        return applyinfoDao.validateBarcode(a_barcode);
    }

    public List<Applyinfo> getStatisticsApplyinfo(String organizationId, Timestamp a_lr_date_1, Timestamp a_lr_date_2, String a_hospital) {
        return applyinfoDao.getStatisticsApplyinfo(organizationId,a_lr_date_1,a_lr_date_2,a_hospital);
    }

    public List<Organization> getListHospital(String organizationId) {
        return applyinfoDao.getListHospital(organizationId);
    }


    public List<ListApplication> getYearApplication(String organizationId, String year) {
        return applyinfoDao.getYearApplication(organizationId,year);
    }

    public List<ListApplication> getMonthApplication(String organizationId) {
        return applyinfoDao.getMonthApplication(organizationId);
    }

    public List<Applyinfo> getBySemealData(String organizationId,Timestamp date_1, Timestamp date_2) {
        return applyinfoDao.getBySemealData(organizationId,date_1,date_2);
    }

    public List<ListApplication> getYearReportData(String organizationId, String year) {
        return applyinfoDao.getYearReportData(organizationId,year);
    }

    public List<SendApplication> getReportCheckInfo(String organizationId,Timestamp date_1, Timestamp date_2) {
        return applyinfoDao.getReportCheckInfo(organizationId,date_1,date_2);
    }


}
