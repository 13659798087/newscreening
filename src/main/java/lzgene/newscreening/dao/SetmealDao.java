package lzgene.newscreening.dao;

import lzgene.newscreening.model.Combine;
import lzgene.newscreening.model.Setmeal;
import lzgene.newscreening.model.Setmealcombine;
import org.apache.ibatis.annotations.*;

import javax.management.ValueExp;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealDao {

    @Select("<script> SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY s_order_no asc) AS num FROM setmeal " +
            " where 1=1  and s_flag=0 " +
            " <if test='s_name !=null &amp;&amp; s_name !=\"\"'> and s_name like '%${s_name}%' </if>"+
            " <if test='s_code !=null &amp;&amp; s_code !=\"\"'> and s_code like '%${s_code}%' </if>"+
            ") " +
            " AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script> ")
    List<Setmeal> getSetmeal(@Param(value="s_code") String s_code,
                             @Param(value="s_name") String s_name,
                             @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select("<script> SELECT count(1) from setmeal \n" +
            " where 1=1  and s_flag=0 \n" +
            " <if test='s_name !=null &amp;&amp; s_name !=\"\"'> and s_name like '%${s_name}%' </if>\n" +
            " <if test='s_code !=null &amp;&amp; s_code !=\"\"'> and s_code like '%${s_code}%' </if> </script>")
    long getSetmealCount(@Param(value="s_code") String s_code,
                         @Param(value="s_name") String s_name,
                         @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select("select count(*) from setmeal where s_code=#{s_code} ")
    int validateSetmeal(String s_code);

    @Insert("insert into setmeal (s_code,s_name,s_price,s_order_no,s_flag,create_time,update_time) values" +
            " (#{s_code},#{s_name},#{s_price},#{s_order_no},0,getdate(),null)")
    void createSetmeal(@Param(value="s_code") String s_code,
                       @Param(value="s_name") String s_name,
                       @Param(value="s_price") BigDecimal s_price,
                       @Param(value="s_order_no") String s_order_no);

    @Insert("insert into setmealcombine (sc_id,c_code,s_code) values (#{sc_id},#{c_code},#{s_code} )")
    void insertRela(@Param(value="sc_id")String sc_id,@Param(value="c_code")String c_code,@Param(value="s_code")String s_code);

    @Delete("delete from setmealcombine where s_code=#{s_code} ")
    void delRela(String s_code);

    @Update("Update setmeal set s_code=#{s_code},s_name=#{s_name},s_price=#{s_price},s_order_no=#{s_order_no},update_time=getdate()" +
            " where s_code=#{hide_code} ")
    void updateSetmeal(@Param(value="hide_code")String hide_code,
                       @Param(value="s_code") String s_code,
                       @Param(value="s_name") String s_name,
                       @Param(value="s_price") BigDecimal s_price,
                       @Param(value="s_order_no") String s_order_no);


    @Delete("update setmeal set s_flag = 1 where s_code = #{s_code} ")
    void deleteSetmeal(String s_code);

    @Select("select * from setmeal where s_flag=0 ")
    List<Setmeal> getListSetmea();

    @Select("select sc.*,c.c_name from setmealcombine sc LEFT JOIN combine c on sc.c_code = c.c_code \n" +
            "left join setmeal s on sc.s_code = s.s_code where s.s_name = #{s_name} ")
    List<Setmealcombine> getBySetmeal(String s_name);
}
