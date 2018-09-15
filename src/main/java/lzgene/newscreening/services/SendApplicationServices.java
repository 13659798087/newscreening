package lzgene.newscreening.services;

import lzgene.newscreening.dao.SendApplicationDao;
import lzgene.newscreening.entiry.SendApplication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class SendApplicationServices {

    @Resource
    private SendApplicationDao sendApplicationDao;

    public List<SendApplication> getApplicationList(String organizationId, String p_name, int pageNo, int pageSize, Timestamp c_a_date1, Timestamp c_a_date2,
                                                    String c_combine_code, String c_barcode, String c_rpt_flag) {
        return sendApplicationDao.getApplicationList(organizationId,p_name, pageNo, pageSize,c_a_date1,c_a_date2,c_combine_code,c_barcode,c_rpt_flag);
    }

    public List<SendApplication> getStatisticsCheckInfoCombine(String organizationId, Timestamp a_lr_date_1, Timestamp a_lr_date_2, String c_hospital) {
        return sendApplicationDao.getStatisticsCheckInfoCombine(organizationId,a_lr_date_1,a_lr_date_2,c_hospital);
    }

    public long getReportCount(String organizationId, String p_name, int pageNo, int pageSize, Timestamp c_a_date1, Timestamp c_a_date2, String c_combine_code, String c_barcode, String c_rpt_flag) {
        return sendApplicationDao.getReportCount(organizationId,p_name, pageNo, pageSize,c_a_date1,c_a_date2,c_combine_code,c_barcode,c_rpt_flag);
    }


}
