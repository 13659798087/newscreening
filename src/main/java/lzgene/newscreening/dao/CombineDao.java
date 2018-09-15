package lzgene.newscreening.dao;

import lzgene.newscreening.model.Combine;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CombineDao {


    @Select("SELECT * from combine where c_flag=0 ORDER BY c_order_no ")
    List<Combine> getCombine();

    @Select("<script> SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY c_order_no asc) AS num FROM combine " +
            "where 1=1 and c_flag=0 " +
            " <if test='c_name !=null &amp;&amp; c_name !=\"\"'> and c_name like '%${c_name}%' </if>"+
            " <if test='c_rpt !=null &amp;&amp; c_rpt !=\"\"'> and c_rpt like '%${c_rpt}%' </if>"+
            ") " +
            " AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script> ")
    List<Combine> getCombineList(@Param(value="c_name")String c_name,
                                 @Param(value="c_rpt")String c_rpt,
                                 @Param(value="pageNo")int pageNo,
                                 @Param(value="pageSize")int pageSize);

    @Select("<script> SELECT count(*) from combine where 1=1 and c_flag=0 " +
            " <if test='c_name !=null &amp;&amp; c_name !=\"\"'> and c_name like '%${c_name}%' </if>"+
            " <if test='c_rpt !=null &amp;&amp; c_rpt !=\"\"'> and c_rpt like '%${c_rpt}%' </if> </script>" )
    long getCombineCount(@Param(value="c_name")String c_name,
                         @Param(value="c_rpt")String c_rpt,
                         @Param(value="pageNo")int pageNo,
                         @Param(value="pageSize")int pageSize);

    @Select("SELECT * from setmeal s inner join setmealcombine sc on s.s_code=sc.s_code\n" +
            "inner join combine c on c.c_code=sc.c_code where s.s_code= #{s_code} ")
    List<Combine> getCombineById(String s_code);

    @Select("select count(*) from combine where c_code = #{c_code} and c_flag=0 ")
    int validateCombine(String c_code);

    @Insert("INSERT into combine (c_code,c_name,c_price,c_order_no,c_rpt,c_rpt_title,c_rpt_bz1,c_rpt_bz2,create_time,paper_size) " +
            "VALUES (#{c_code},#{c_name},#{c_price},#{c_order_no},#{c_rpt},#{c_rpt_title},#{c_rpt_bz1},#{c_rpt_bz1},getdate(),#{paper_size} )")
    void createCombine(@Param(value="c_code") String c_code,@Param(value="c_name") String c_name,
                       @Param(value="c_price")BigDecimal c_price,@Param(value="c_order_no")String c_order_no,
                       @Param(value="c_rpt")String c_rpt,@Param(value="c_rpt_title") String c_rpt_title,
                       @Param(value="c_rpt_bz1")String c_rpt_bz1,@Param(value="c_rpt_bz2")String c_rpt_bz2,@Param(value="paper_size")String paper_size);

    @Update("update combine set c_code=#{c_code},c_name=#{c_name},c_price=#{c_price},c_order_no=#{c_order_no},c_rpt=#{c_rpt}," +
            "c_rpt_title=#{c_rpt_title},c_rpt_bz1=#{c_rpt_bz1},c_rpt_bz2=#{c_rpt_bz2},paper_size=#{paper_size} where c_code=#{hide_code} ")
    void updateCombine( @Param(value="hide_code") String hide_code,
                        @Param(value="c_code") String c_code,@Param(value="c_name") String c_name,
                        @Param(value="c_price")BigDecimal c_price,@Param(value="c_order_no")String c_order_no,
                        @Param(value="c_rpt")String c_rpt,@Param(value="c_rpt_title") String c_rpt_title,
                        @Param(value="c_rpt_bz1")String c_rpt_bz1,@Param(value="c_rpt_bz2")String c_rpt_bz2,@Param(value="paper_size")String paper_size);

    @Delete("update combine set c_flag = 1 where c_code = #{c_code} ")
    void deleteCombine(String c_code);

    @Select("SELECT * from combine where c_code = #{c_code} ")
    Combine getCombineByCode(String c_code);

}
