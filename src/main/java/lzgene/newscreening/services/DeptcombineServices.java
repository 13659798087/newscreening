package lzgene.newscreening.services;

import lzgene.newscreening.dao.DeptcombineDao;
import lzgene.newscreening.model.Combine;
import lzgene.newscreening.model.Deptcombine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptcombineServices {

    @Resource
    private DeptcombineDao deptcombineDao;


    public List<Combine> getDeptCombine(Integer dcdId) {
        return deptcombineDao.getDeptCombine(dcdId);
    }

}
