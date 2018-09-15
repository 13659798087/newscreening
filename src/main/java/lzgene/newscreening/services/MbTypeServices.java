package lzgene.newscreening.services;

import lzgene.newscreening.dao.MbTypeDao;
import lzgene.newscreening.model.MbType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MbTypeServices {

    @Resource
    private MbTypeDao mbTypeDao;

    public List<MbType> getMbType() {
        return mbTypeDao.getMbType();
    }

    public MbType getMbTypeById(String id) {
        return mbTypeDao.getMbTypeById(id);
    }


}
