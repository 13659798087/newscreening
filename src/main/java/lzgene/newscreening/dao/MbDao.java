package lzgene.newscreening.dao;

import lzgene.newscreening.model.Mb;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MbDao {

    @Select("SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY mb_order_no asc) AS num FROM mb " +
            "where mb_name like '%${mb_name}%')AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} ")
    List<Mb> templateTable(@Param(value="mb_name")String mb_name,
                           @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select("select count(*) from mb where mb_name like '%${mb_name}%' ")
    long getSignCount(@Param(value="mb_name")String mb_name,
                      @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select("select count(*) from mb where mb_name = #{mb_name} ")
    int validateName(String mb_name);

    @Insert("INSERT into mb (mb_id,mb_code,mb_name,mb_order_no,mb_type,mb_lb_code,mb_lb_name,mb_flag) " +
            "VALUES (#{mb_id},#{mb_code},#{mb_name},#{mb_order_no},#{mb_type},#{mb_lb_code},#{mb_lb_name},#{mb_flag} )")
    void createCombine(@Param(value="mb_id")String mb_id,
                       @Param(value="mb_code")String mb_code,
                       @Param(value="mb_name")String mb_name,
                       @Param(value="mb_order_no")Integer mb_order_no,
                       @Param(value="mb_type")Integer mb_type,
                       @Param(value="mb_lb_code")String mb_lb_code,
                       @Param(value="mb_lb_name")String mb_lb_name,
                       @Param(value="mb_flag")String mb_flag);

    @Update("update mb set mb_code=#{mb_code},mb_name=#{mb_name},mb_order_no=#{mb_order_no},mb_type=#{mb_type}," +
            "mb_lb_code=#{mb_lb_code},mb_lb_name=#{mb_lb_name},mb_flag=#{mb_flag} where mb_id=#{mb_id} ")
    void updateCombine(@Param(value="mb_id")String mb_id,
                       @Param(value="mb_code")String mb_code,
                       @Param(value="mb_name")String mb_name,
                       @Param(value="mb_order_no")Integer mb_order_no,
                       @Param(value="mb_type")Integer mb_type,
                       @Param(value="mb_lb_code")String mb_lb_code,
                       @Param(value="mb_lb_name")String mb_lb_name,
                       @Param(value="mb_flag")String mb_flag);

    @Delete("delete from mb where mb_id = #{mb_id} ")
    void deleteRow(String mb_id);

    @Insert("INSERT into mb (mb_id,mb_name,mb_type,mb_lb_name) VALUES (#{id},#{a_dept},#{mb_type},#{organizationName} )")
    void createDeptMb(@Param(value="id")String id,@Param(value="a_dept")String a_dept,
                      @Param(value="mb_type")String mb_type,@Param(value="organizationName")String organizationName);

    @Select("SELECT * FROM mb where mb_lb_name=#{organizationName} and mb_type=#{sjks} and mb_flag=0 order by mb_order_no asc ")
    List<Mb> getDept(@Param(value="organizationName")String organizationName,@Param(value="sjks")String sjks);

    @Select("SELECT * FROM mb where mb_lb_name=#{organizationName} and mb_type=#{sjys} and mb_flag=0 order by mb_order_no asc ")
    List<Mb> getDoctor(@Param(value="organizationName")String organizationName,@Param(value="sjys")String sjys);

    @Select("SELECT * FROM mb where mb_type=#{lczd} and mb_flag=0 order by mb_order_no asc ")
    List<Mb> getdzAndycs(String lczd);

    @Select("SELECT * FROM mb where mb_flag=0 order by mb_order_no asc ")
    List<Mb> getAllMb();

    @Delete("update mb set mb_flag =#{mb_flag} where mb_id = #{mb_id} ")
    void changeFlag(@Param(value="mb_id")String mb_id,@Param(value="mb_flag")String mb_flag);

}
