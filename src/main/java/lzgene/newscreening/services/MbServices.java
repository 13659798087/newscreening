package lzgene.newscreening.services;

import lzgene.newscreening.dao.MbDao;
import lzgene.newscreening.model.Mb;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MbServices {


    @Resource
    private MbDao mbDao;

    public List<Mb> templateTable(String mb_name, int pageNo, int pageSize) {
        return mbDao.templateTable(mb_name,pageNo,pageSize);
    }

    public long getSignCount(String mb_name, int pageNo, int pageSize) {
        return mbDao.getSignCount(mb_name,pageNo,pageSize);
    }

    public int validateName(String mb_name) {
        return mbDao.validateName(mb_name);
    }

    public void createCombine(String mb_id, String mb_code, String mb_name, Integer mb_order_no, Integer mb_type,
                              String mb_lb_code, String mb_lb_name,String mb_flag) {
        mbDao.createCombine(mb_id,mb_code,mb_name,mb_order_no,mb_type,mb_lb_code,mb_lb_name,mb_flag);
    }

    public void updateCombine(String mb_id, String mb_code, String mb_name, Integer mb_order_no, Integer mb_type,
                              String mb_lb_code, String mb_lb_name,String mb_flag) {
        mbDao.updateCombine(mb_id,mb_code,mb_name,mb_order_no,mb_type,mb_lb_code,mb_lb_name,mb_flag);
    }

    public void deleteRow(String mb_id) {
        mbDao.deleteRow(mb_id);
    }

    public List<Mb> getDept(String organizationName, String sjks) {
        return mbDao.getDept(organizationName,sjks);
    }

    public List<Mb> getDoctor(String organizationName, String sjys) {
        return mbDao.getDoctor(organizationName,sjys);
    }

    public void createDeptMb(String id, String a_dept, String mb_type, String organizationName) {
        mbDao.createDeptMb(id,a_dept,mb_type,organizationName);
    }

    public List<Mb> getdzAndycs(String lczd) {
        return mbDao.getdzAndycs(lczd);
    }

    public List<Mb> getAllMb() {
        return mbDao.getAllMb();
    }

    public void changeFlag(String mb_id, String mb_flag) {
        mbDao.changeFlag(mb_id,mb_flag);
    }

}
