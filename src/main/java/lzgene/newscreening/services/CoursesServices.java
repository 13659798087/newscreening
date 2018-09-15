package lzgene.newscreening.services;


import lzgene.newscreening.dao.CoursesDao;
import lzgene.newscreening.model.Courses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoursesServices {

    @Resource
    private CoursesDao coursesDao;

    public List<Courses> getcourse() {
        return coursesDao.getcourse();
    }
}
