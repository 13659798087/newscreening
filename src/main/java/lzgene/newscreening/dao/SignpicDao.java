package lzgene.newscreening.dao;


import lzgene.newscreening.model.Signpic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SignpicDao {

    @Insert(" insert into signpic (id,sp_name,sp_pic,create_time) values (#{id},#{sp_name},#{sp_pic},getDATE() ) ")
    void uploadPicture(Signpic signpic);

    @Select("select * from signpic where id=#{id}")
    Signpic Pictureshows(String id);

    @Select("SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY create_time DESC) AS num FROM signpic " +
            "where sp_name like '%${sp_name}%') AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} ")
    List<Signpic> getSignpic(@Param(value = "sp_name")String sp_name,
                             @Param(value = "pageNo") int pageNo,
                             @Param(value = "pageSize") int pageSize);

    @Select("select count(*) from signpic where sp_name=#{sp_name}")
    int validateSign(String sp_name);

    @Select("select count(*) from signpic where sp_name like '%${sp_name}%' ")
    long getSignCount(@Param(value = "sp_name")String sp_name,
                      @Param(value = "pageNo") int pageNo,
                      @Param(value = "pageSize") int pageSize);

    @Update("update signpic set sp_name=#{sp_name},sp_pic=#{sp_pic} where id = #{id} ")
    void updateSignpic(@Param(value = "id")String id,
                       @Param(value = "sp_name")String sp_name,
                       @Param(value = "sp_pic")byte[] sp_pic);

    @Delete("delete from signpic where id = #{id} ")
    void deleteSignpic(String id);


}
