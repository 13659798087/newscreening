package lzgene.newscreening.dao;

import lzgene.newscreening.model.LogManagement;
import lzgene.newscreening.model.Mb;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface LogManagementDao {


    @Insert(" INSERT INTO logManagement (loggerName,type,userName,ipAddress,patients,hospital,combine,barcode,sid,createTime,phone)\n" +
            " VALUES (#{loggerName},#{type},#{userName},#{ipAddress},#{patients},#{hospital},#{combine},#{barcode},#{sid},getDate(),#{phone}) ")
    void recordLog(@Param(value = "loggerName")String loggerName,@Param(value = "type")String type,
                   @Param(value = "userName")String userName,@Param(value = "ipAddress")String ipAddress,
                   @Param(value = "patients")String patients,@Param(value = "hospital")String hospital,
                   @Param(value = "combine")String combine,@Param(value = "barcode") String barcode,
                   @Param(value = "sid")String sid,@Param(value = "phone")String phone);


    @Select("<script> with temp ( id,name,parentId,isDelete)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId,isDelete\n" +
            "            from organization\n" +
            "            where id = #{organizationId} \n" +
            "            union all\n" +
            "            select a.id,a.name,a.parentId,a.isDelete\n" +
            "            from organization a\n" +
            "            inner join temp on a.parentId = temp.id\n" +
            "            )" +
            " SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY createTime asc) AS num FROM logManagement l " +
            " inner join temp t on t.name = l.hospital where 1=1 " +
            " <if test='time_1 !=null '> and createTime between #{time_1} and #{time_2} </if>"+
            " <if test='loggerName !=null &amp;&amp; loggerName !=\"\"'> and loggerName like '%${loggerName}%' </if>"+
            " <if test='patients !=null &amp;&amp; patients !=\"\"'> and patients like '%${patients}%' </if>"+
            " <if test='barcode !=null &amp;&amp; barcode !=\"\"'> and barcode like '%${barcode}%' </if>"+
           /* " <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and hospital = #{hospital} </if>"+*/
            " <if test='combine !=null &amp;&amp; combine !=\"\"'> and combine = #{combine} </if>"+
            " <if test='type !=null &amp;&amp; type !=\"\"'> and type like '%${type}%' </if>"+
            " <if test='p_tel1 !=null &amp;&amp; p_tel1 !=\"\"'> and phone like '%${p_tel1}%' </if>"+
            " )AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script> ")
    List<LogManagement> getLogger(@Param(value="pageNo")int pageNo,@Param(value="pageSize")int pageSize,
                                  @Param(value="time_1")Timestamp time_1, @Param(value="time_2")Timestamp time_2,
                                  @Param(value="loggerName")String loggerName,@Param(value="patients")String patients,
                                  @Param(value="barcode")String barcode,
                                  @Param(value="organizationId")String organizationId,
                                  @Param(value="combine")String combine,@Param(value="type")String type,
                                  @Param(value="p_tel1")String p_tel1);

    @Select("<script> with temp ( id,name,parentId,isDelete)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId,isDelete\n" +
            "            from organization\n" +
            "            where id = #{organizationId} \n" +
            "            union all\n" +
            "            select a.id,a.name,a.parentId,a.isDelete\n" +
            "            from organization a\n" +
            "            inner join temp on a.parentId = temp.id\n" +
            "            )" +
            " SELECT count(1) FROM logManagement l " +
            " inner join temp t on t.name = l.hospital where 1=1 " +
            " <if test='time_1 !=null '> and createTime between #{time_1} and #{time_2} </if>"+
            " <if test='loggerName !=null &amp;&amp; loggerName !=\"\"'> and loggerName like '%${loggerName}%' </if>"+
            " <if test='patients !=null &amp;&amp; patients !=\"\"'> and patients like '%${patients}%' </if>"+
            " <if test='barcode !=null &amp;&amp; barcode !=\"\"'> and barcode like '%${barcode}%' </if>"+
           /* " <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and hospital = #{hospital} </if>"+*/
            " <if test='combine !=null &amp;&amp; combine !=\"\"'> and combine = #{combine} </if>"+
            " <if test='type !=null &amp;&amp; type !=\"\"'> and type like '%${type}%' </if>"+
            " <if test='p_tel1 !=null &amp;&amp; p_tel1 !=\"\"'> and phone like '%${p_tel1}%' </if>"+
            "  </script> ")
    long getLoggerCount(@Param(value="pageNo")int pageNo,@Param(value="pageSize")int pageSize,
                        @Param(value="time_1")Timestamp time_1, @Param(value="time_2")Timestamp time_2,
                        @Param(value="loggerName")String loggerName,@Param(value="patients")String patients,
                        @Param(value="barcode")String barcode, @Param(value="organizationId")String organizationId,
                        @Param(value="combine")String combine,@Param(value="type")String type,
                        @Param(value="p_tel1")String p_tel1);


    @Delete("delete from logManagement where loggerId = #{id} ")
    void deleteLog(String id);


}
