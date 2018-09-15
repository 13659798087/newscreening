package lzgene.newscreening.dao;

import lzgene.newscreening.model.Combine;
import lzgene.newscreening.model.Deptcombine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptcombineDao {
    @Select("select * from combine ")
    List<Combine> getDeptCombine(Integer dcdId);
}
