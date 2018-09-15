package lzgene.newscreening.services;

import lzgene.newscreening.dao.SignpicDao;
import lzgene.newscreening.model.Signpic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SignpicServices {

    @Resource
    private SignpicDao signpicDao;

    public void uploadPicture(Signpic signpic) {
        signpicDao.uploadPicture(signpic);
    }

    public Signpic Pictureshows(String id) {
        return signpicDao.Pictureshows(id);
    }


    public List<Signpic> getSignpic(String sp_name, int pageNo, int pageSize) {
        return signpicDao.getSignpic(sp_name,pageNo,pageSize);
    }

    public long getSignCount(String sp_name, int pageNo, int pageSize) {
        return signpicDao.getSignCount(sp_name,pageNo,pageSize);
    }

    public int validateSign(String sp_name) {
        return signpicDao.validateSign(sp_name);
    }

    public void updateSignpic(String id, String sp_name, byte[] sp_pic) {
        signpicDao.updateSignpic(id,sp_name,sp_pic);
    }

    public void deleteSignpic(String id) {
        signpicDao.deleteSignpic(id);
    }


}
