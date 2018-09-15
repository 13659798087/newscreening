package lzgene.newscreening.services;

import lzgene.newscreening.dao.ApplyinfoCombineDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApplyinfoCombineServices {
    @Resource
    private ApplyinfoCombineDao applyinfoCombineDao;

    public void CreateApplyinfoCombine(String id, String a_id, String c_dode,String c_name) {
        applyinfoCombineDao.CreateApplyinfoCombine(id, a_id, c_dode,c_name);
    }

    public void deleteApplyinfoCombine(String a_id, String c_combine_code) {
        applyinfoCombineDao.deleteApplyinfoCombine( a_id, c_combine_code);
    }

    public void deleteCombineRelation(String a_id) {
        applyinfoCombineDao.deleteCombineRelation(a_id);
    }


}
