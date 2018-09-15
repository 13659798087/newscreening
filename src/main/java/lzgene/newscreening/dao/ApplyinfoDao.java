package lzgene.newscreening.dao;

import lzgene.newscreening.entiry.ListApplication;
import lzgene.newscreening.entiry.SendApplication;
import lzgene.newscreening.model.Applyinfo;
import lzgene.newscreening.model.Organization;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ApplyinfoDao {

    @Insert("INSERT INTO applyinfo (a_id,a_name,a_bithday,a_sex,a_hospital,a_dept,a_doctor,a_in_no,a_bed_no,a_tel1,a_tel2,a_place," +
            "a_hk_city,a_hk_bd,a_address,a_barcode,a_tes,a_lr_date,a_lr_name,a_flag,a_setmeal_name,a_id_number,a_system_bbh,a_check_yq," +
            "a_free_flag,a_age,a_age_unit,a_lczd,a_stature,a_weigh,a_blycs,a_lmp_date,a_sample_date,a_sample_name,create_time ) " +
            "VALUES (#{a_id},#{a_name},#{a_bithday},#{a_sex},#{a_hospital},#{a_dept},#{a_doctor},#{a_in_no},#{a_bed_no},#{a_tel1},#{a_tel2},#{a_place}," +
            "#{a_hk_city},#{a_hk_bd},#{a_address},#{a_barcode},#{a_tes},#{a_lr_date},#{a_lr_name},1,#{a_setmeal_name},#{a_id_number},#{a_system_bbh},#{a_check_yq}," +
            "#{a_free_flag},#{a_age},#{a_age_unit},#{a_lczd},#{a_stature},#{a_weigh},#{a_blycs},#{a_lmp_date},#{a_sample_date},#{a_sample_name},getDate() )")
    void createApplication(Applyinfo application);

   /* @Select("<script> SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY a_lr_date DESC) AS num FROM applyinfo where 1=1 " +
            " <if test='a_name !=null &amp;&amp; a_name !=\"\"'> and a_name like '%${a_name}%' </if>"+
            " <if test='a_lr_date1 !=null '> and a_lr_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
            " <if test='a_barcode !=null &amp;&amp; a_barcode !=\"\"'> and a_barcode like '%${a_barcode}%' </if>"+
            " <if test='a_hospital !=null &amp;&amp; a_hospital !=\"\"'> and a_hospital like '%${a_hospital}%' </if>"+
            " <if test='a_in_no !=null &amp;&amp; a_in_no !=\"\"'> and a_in_no like '%${a_in_no}%' </if>"+
            " <if test='a_flag !=null &amp;&amp; a_flag !=\"\"'> and a_flag like '%${a_flag}%' </if>"+
            " )AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script>")
    List<Applyinfo> getTableApplyinfo(@Param(value = "a_name") String a_name,
                                      @Param(value = "pageNo") int pageNo,
                                      @Param(value = "pageSize") int pageSize,
                                      @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                      @Param(value = "a_lr_date2")Timestamp a_lr_date2,
                                      @Param(value = "a_barcode")String a_barcode,
                                      @Param(value = "a_hospital")String a_hospital,
                                      @Param(value = "a_in_no")String a_in_no,
                                      @Param(value = "a_flag")String a_flag);*/

    @Select("<script> with temp (id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = #{organizationId}\n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY t2.a_lr_date DESC) AS num FROM (SELECT a.* from applyinfo  a\n" +
            "INNER  JOIN  temp  on a.a_hospital=name) t2 where 1=1 \n" +
            " <if test='a_name !=null &amp;&amp; a_name !=\"\"'> and a_name like '%${a_name}%' </if>"+
            " <if test='a_lr_date1 !=null '> and a_lr_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
            " <if test='a_barcode !=null &amp;&amp; a_barcode !=\"\"'> and a_barcode like '%${a_barcode}%' </if>"+
           /* " <if test='a_hospital !=null &amp;&amp; a_hospital !=\"\"'> and a_hospital = #{a_hospital} </if>"+*/
            " <if test='a_in_no !=null &amp;&amp; a_in_no !=\"\"'> and a_in_no like '%${a_in_no}%' </if>"+
            " <if test='a_flag !=null &amp;&amp; a_flag !=\"\"'> and a_flag like '%${a_flag}%' </if>"+
            " )AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script>")
    List<Applyinfo> getTableApplyinfo(@Param(value = "organizationId") String organizationId,
                                      @Param(value = "a_name") String a_name,
                                      @Param(value = "pageNo") int pageNo,
                                      @Param(value = "pageSize") int pageSize,
                                      @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                      @Param(value = "a_lr_date2")Timestamp a_lr_date2,
                                      @Param(value = "a_barcode")String a_barcode,
                                      @Param(value = "a_in_no")String a_in_no,
                                      @Param(value = "a_flag")String a_flag);

    @Select("<script> with temp (id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = #{organizationId}\n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "SELECT count(1) FROM (SELECT a.* from applyinfo  a\n" +
            "INNER  JOIN  temp  on a.a_hospital=name) t2 where 1=1 \n" +
            " <if test='a_name !=null &amp;&amp; a_name !=\"\"'> and a_name like '%${a_name}%' </if>"+
            " <if test='a_lr_date1 !=null '> and a_lr_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
            " <if test='a_barcode !=null &amp;&amp; a_barcode !=\"\"'> and a_barcode like '%${a_barcode}%' </if>"+
           /* " <if test='a_hospital !=null &amp;&amp; a_hospital !=\"\"'> and a_hospital = #{a_hospital} </if>"+*/
            " <if test='a_in_no !=null &amp;&amp; a_in_no !=\"\"'> and a_in_no like '%${a_in_no}%' </if>"+
            " <if test='a_flag !=null &amp;&amp; a_flag !=\"\"'> and a_flag like '%${a_flag}%' </if>"+
            "  </script>")
    long getTableApplyinfoCount(@Param(value = "organizationId") String organizationId,
                                @Param(value = "a_name") String a_name,
                                @Param(value = "pageNo") int pageNo,
                                @Param(value = "pageSize") int pageSize,
                                @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                @Param(value = "a_lr_date2")Timestamp a_lr_date2,
                                @Param(value = "a_barcode")String a_barcode,
                                @Param(value = "a_in_no")String a_in_no,
                                @Param(value = "a_flag")String a_flag);


    @Select(" SELECT * from applyinfo where a_id = #{a_id} ")
    Applyinfo getRowById(String a_id);

    @Update("update applyinfo set a_name=#{a_name},a_bithday=#{a_bithday},a_sex=#{a_sex},a_hospital=#{a_hospital},a_dept=#{a_dept}," +
            "a_doctor=#{a_doctor},a_in_no=#{a_in_no},a_bed_no=#{a_bed_no},a_tel1=#{a_tel1},a_tel2=#{a_tel2},a_place=#{a_place},a_hk_city=#{a_hk_city}," +
            "a_hk_bd=#{a_hk_bd},a_address=#{a_address},a_barcode=#{a_barcode},a_tes=#{a_tes},a_lr_date=#{a_lr_date},a_lr_name=#{a_lr_name}," +
            "a_flag=#{a_flag},a_setmeal_name=#{a_setmeal_name},a_id_number=#{a_id_number},a_system_bbh=#{a_system_bbh},a_check_yq=#{a_check_yq}," +
            "a_free_flag=#{a_free_flag},a_age=#{a_age},a_age_unit=#{a_age_unit},a_lczd=#{a_lczd},a_stature=#{a_stature},a_weigh=#{a_weigh}," +
            "a_blycs=#{a_blycs},a_lmp_date=#{a_lmp_date},a_sample_date=#{a_sample_date},a_sample_name=#{a_sample_name},update_time=getDate() where a_id = #{a_id} " )
    void updateApplication(Applyinfo application);

    @Update("delete from applyinfo where a_id=#{a_id} ")
    void deleteRowById(String a_id);

    @Select(" with temp (id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = #{organizationId}\n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "SELECT count(1) from applyinfo  a\n" +
            "INNER  JOIN  temp  on a.a_hospital=name; ")
    long getApplyinfoCount(String organizationId);

    @Select("SELECT count(*) from applyinfo where a_barcode=#{a_barcode} ")
    int validateBarcode(String a_barcode);

    //查询所有当前登录用户所属的单位或单位下的单位的所有申请单信息
    @Select("<script> with temp (id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = #{organizationId} \n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "SELECT * FROM applyinfo \n" +
            "INNER JOIN temp on applyinfo.a_hospital=name where 1=1 " +
            " <if test='a_lr_date1 !=null '> and a_lr_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
            " <if test='a_hospital !=null &amp;&amp; a_hospital !=\"\"'> and a_hospital = #{a_hospital} </if>"+
            "</script> ")
    List<Applyinfo> getStatisticsApplyinfo(@Param(value = "organizationId") String organizationId,
                                           @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                           @Param(value = "a_lr_date2")Timestamp a_lr_date2,
                                           @Param(value = "a_hospital")String a_hospital);

    //没过滤删除的医院
   /* @Select("with temp (id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = #{organizationId}\n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "select * from temp ")
    List<Organization> getListHospital(String organizationId);*/

    @Select("with temp (id,name,parentId,isDelete)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId,organization.isDelete\n" +
            "            from organization\n" +
            "            where id = #{organizationId}\n" +
            "            union all\n" +
            "            select a.id,a.name,a.parentId,a.isDelete\n" +
            "            from organization a\n" +
            "            inner join temp on a.parentId = temp.id\n" +
            "            )\n" +
            "            select * from temp where isDelete = 0 ")
    List<Organization> getListHospital(String organizationId);

    @Select("<script> with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId} \n" +
            "            union all\n" +
            "            select o.id,o.name,o.parentId\n" +
            "            from organization o\n" +
            "            inner join temp on o.parentId = temp.id\n" +
            "            )\n" +
            "            SELECT  CONVERT(varchar(7), a.a_lr_date, 120 ) month,COUNT(1) count FROM applyinfo a\n" +
            "            INNER JOIN temp on a.a_hospital=name \n" +
         /*   " where CONVERT(varchar(7), a.a_lr_date, 120 ) LIKE DATENAME(YYYY,GETDATE())+'%' \n" +*/
            " where CONVERT(varchar(7), a.a_lr_date, 120 ) LIKE #{year}+'%' \n" +
        /*    " <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and a_hospital = #{hospital} </if> "+*/
            " GROUP BY CONVERT(varchar(7), a.a_lr_date, 120 ) </script> " )
    List<ListApplication> getYearApplication(@Param(value = "organizationId") String organizationId,
                                            @Param(value = "year")String year);

    @Select("with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId}\n" +
            "            union all\n" +
            "            select o.id,o.name,o.parentId\n" +
            "            from organization o\n" +
            "            inner join temp on o.parentId = temp.id\n" +
            "            )\n" +
            "            SELECT CONVERT(varchar(10), a.a_lr_date, 120 ) month,COUNT(1) count  FROM applyinfo a\n" +
            "            INNER JOIN temp on a.a_hospital=name \n" +
            "where CONVERT(varchar(7), a.a_lr_date, 120 ) LIKE CONVERT(varchar(7),GETDATE(),120)+'%' \n" +
            "GROUP BY CONVERT(varchar(10), a.a_lr_date, 120 ) ")
    List<ListApplication> getMonthApplication(String organizationId);

    @Select("<script> with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId} \n" +
            "            union all\n" +
            "            select o.id,o.name,o.parentId\n" +
            "            from organization o\n" +
            "            inner join temp on o.parentId = temp.id\n" +
            "            )\n" +
            "            SELECT a.a_name,a.a_hospital,a.a_setmeal_name,a.a_lr_date FROM applyinfo a\n" +
            "            INNER JOIN temp on a.a_hospital=name where 1=1 \n" +
            " <if test='a_lr_date1 !=null '> and a_lr_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
       /*     " <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and a_hospital = #{hospital} </if> " +*/
            "</script> " )
    List<Applyinfo> getBySemealData(@Param(value = "organizationId") String organizationId,
                                    @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                    @Param(value = "a_lr_date2")Timestamp a_lr_date2);


    @Select("<script> with temp (id,name,parentId)\n" +
            "             as\n" +
            "             (\n" +
            "             select id,name,parentId\n" +
            "             from organization\n" +
            "             where id =#{organizationId}\n" +
            "             union all\n" +
            "             select o.id,o.name,o.parentId\n" +
            "             from organization o\n" +
            "             inner join temp on o.parentId = temp.id\n" +
            "             )\n" +
            "             SELECT CONVERT(varchar(7), c.c_a_date, 120 ) month,COUNT(1) count FROM checkinfo c\n" +
            "             INNER JOIN temp on c.c_hospital=name \n" +
            "             where CONVERT(varchar(7), c.c_a_date, 120 ) LIKE #{year}+'%' \n" +
       /*     "             <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and c_hospital = #{hospital} </if> \n" +*/
            "             GROUP BY CONVERT(varchar(7), c.c_a_date, 120 ) </script> " )
    List<ListApplication> getYearReportData(@Param(value = "organizationId") String organizationId,@Param(value = "year")String year);


    @Select("<script> with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId} \n" +
            "            union all\n" +
            "            select o.id,o.name,o.parentId\n" +
            "            from organization o\n" +
            "            inner join temp on o.parentId = temp.id\n" +
            "            )\n" +
            "            SELECT c.c_hospital,c.c_combine_code,c.c_a_date FROM checkinfo c\n" +
            "            INNER JOIN temp on c.c_hospital=name where 1=1 \n" +
            " <if test='c_a_date1 !=null '> and c_a_date between #{c_a_date1} and #{c_a_date2} </if>"+
          /*  " <if test='hospital !=null &amp;&amp; hospital !=\"\"'> and c_hospital = #{hospital} </if> " +*/
            "</script> " )
    List<SendApplication> getReportCheckInfo(@Param(value = "organizationId") String organizationId,
                                             @Param(value = "c_a_date1")Timestamp c_a_date1,
                                             @Param(value = "c_a_date2")Timestamp c_a_date2);












}
