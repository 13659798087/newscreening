package lzgene.newscreening.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApplyinfoCombineDao {

    @Insert("INSERT into applyinfocombine (id,a_id,a_combine_code,a_combine_name)" +
            " VALUES (#{id},#{a_id},#{c_dode},#{c_name} )")
    void CreateApplyinfoCombine(@Param(value = "id")String id,@Param(value = "a_id")String a_id,
                                @Param(value = "c_dode")String c_dode,@Param(value = "c_name")String c_name);

    @Delete("delete from applyinfocombine where a_id=#{a_id} and a_combine_code=#{c_combine_code}")
    void deleteApplyinfoCombine(@Param(value = "a_id")String a_id,@Param(value = "c_combine_code")String c_combine_code);

    @Delete("delete from applyinfocombine where a_id=#{a_id} ")
    void deleteCombineRelation(String a_id);

}
