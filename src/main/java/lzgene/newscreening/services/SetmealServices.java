package lzgene.newscreening.services;

import lzgene.newscreening.dao.SetmealDao;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.model.Setmeal;
import lzgene.newscreening.model.Setmealcombine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class SetmealServices {


    @Resource
    private SetmealDao setmealDao;

    public List<Setmeal> getSetmeal(String s_code,String s_name,int pageNo, int pageSize) {
        return setmealDao.getSetmeal(s_code,s_name,pageNo,pageSize);
    }

    public int validateSetmeal(String s_code) {
        return setmealDao.validateSetmeal(s_code);
    }

    public void createSetmeal(String s_code, String s_name, BigDecimal s_price, String s_order_no) {
        setmealDao.createSetmeal(s_code, s_name, s_price, s_order_no);
    }

    public void insertRela(String sc_id, String c_code, String s_code) {
        setmealDao.insertRela(sc_id, c_code, s_code);
    }

    public void delRela(String s_code) {
        setmealDao.delRela(s_code);
    }

    public void updateSetmeal(String hide_code,String s_code, String s_name, BigDecimal s_price, String s_order_no) {
        setmealDao.updateSetmeal(hide_code,s_code,s_name,s_price,s_order_no);
    }

    public long getSetmealCount(String s_code,String s_name,int pageNo, int pageSize) {
        return setmealDao.getSetmealCount(s_code,s_name,pageNo,pageSize);
    }

    public void deleteSetmeal(String s_code) {
        setmealDao.deleteSetmeal(s_code);
    }

    public List<Setmeal> getListSetmea() {
        return setmealDao.getListSetmea();
    }


    public List<Setmealcombine> getBySetmeal(String s_name) {
        return setmealDao.getBySetmeal(s_name);
    }

}
