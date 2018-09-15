package lzgene.newscreening.services;

import lzgene.newscreening.dao.CombineDao;
import lzgene.newscreening.model.Combine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CombineServices {

    @Resource
    private CombineDao combineDao;

    public List<Combine> getCombineList(String c_name, String c_rpt, int pageNo, int pageSize) {
        return combineDao.getCombineList(c_name,c_rpt,pageNo,pageSize);
    }

    public long getCombineCount(String c_name, String c_rpt, int pageNo, int pageSize) {
        return combineDao.getCombineCount(c_name,c_rpt,pageNo,pageSize);
    }

    public List<Combine> getCombineById(String s_code) {
        return combineDao.getCombineById(s_code);
    }

    public int validateCombine(String c_code) {
        return combineDao.validateCombine(c_code);
    }

    public void createCombine(String c_code, String c_name, BigDecimal c_price, String c_order_no, String c_rpt, String c_rpt_title, String c_rpt_bz1, String c_rpt_bz2,String paper_size) {
        combineDao.createCombine(c_code,c_name,c_price,c_order_no,c_rpt,c_rpt_title,c_rpt_bz1,c_rpt_bz2,paper_size);
    }

    public void updateCombine(String hide_code,String c_code, String c_name, BigDecimal c_price, String c_order_no, String c_rpt, String c_rpt_title, String c_rpt_bz1, String c_rpt_bz2,String paper_size) {
        combineDao.updateCombine(hide_code,c_code,c_name,c_price,c_order_no,c_rpt,c_rpt_title,c_rpt_bz1,c_rpt_bz2,paper_size);
    }

    public List<Combine> getCombine() {
        return combineDao.getCombine();
    }

    public void deleteCombine(String c_code) {
        combineDao.deleteCombine(c_code);
    }

    public Combine getCombineByCode(String c_code) {
        return combineDao.getCombineByCode(c_code);
    }
}
