package lzgene.newscreening.dao;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.entiry.SendApplication;
import lzgene.newscreening.model.Applyinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface SendApplicationDao {


    @Select("<script> SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY create_time DESC) AS num FROM applyinfo where 1=1 " +
            " <if test='a_name !=null &amp;&amp; a_name !=\"\"'> and a_name like '%${a_name}%' </if>"+
            " )" +
            "AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script>")
    List<Applyinfo> getTableApplyinfo(@Param(value = "a_name") String a_name,
                                      @Param(value = "pageNo") int pageNo,
                                      @Param(value = "pageSize") int pageSize);


    @Select("<script> with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId}\n" +
            "            union all\n" +
            "            select a.id,a.name,a.parentId\n" +
            "            from organization a\n" +
            "            inner join temp on a.parentId = temp.id\n" +
            "            )\n" +
            " SELECT * FROM ( select ROW_NUMBER() OVER(ORDER BY c.c_a_date DESC) AS num,c.c_id,p.p_name,c.c_in_no,p.p_bithday,c.c_ts_ycq_age,\n" +
            "            'c_ts_yzjs'  = case c.c_ts_yzjs \n" +
            "            when 1 then 'B超'\n" +
            "            when 2 then '末次月经'\n" +
            "            when 3 then '预产日期'\n" +
            "            when 4 then 'IVF'\n" +
            "            when 5 then 'CRL'\n" +
            "            when 6 then 'BPD'\n" +
            "            else '' end,\n" +
            "            c.c_tes,c.c_hospital,c.c_dept,c.c_doctor,c.c_lmp_date,p.p_tel1,\n" +
            "            p.p_tel2,c.c_sid,c.c_barcode,c.c_weigh,c.c_ts_bcdate,c.c_ts_bpd,c.c_ts_crl,\n" +
            "            c.c_ts_crl2,c.c_ts_nt,c.c_ts_nt2,\n" +
            "            c.c_sample_date,pregnancy=c.c_ts_sample_week  + '周' + c.c_ts_sample_day  + '天',\n" +
            "            c.c_a_date,c.c_w_name,c.c_a_name,c.c_res_ts,c.c_combine_code,c.c_rpt_flag \n" +
            "            from patients p LEFT JOIN checkinfo c on p.p_id = c.c_p_id\n" +
            "            INNER JOIN temp on c.c_hospital=name\n" +
            "            where 1=1 and \n" +
            "            isnull(c.c_rpt_flag,0)>0 \n" +
            "            <if test='p_name !=null &amp;&amp; p_name !=\"\"'> and p_name like '%${p_name}%' </if>"+
            "            <if test='c_a_date1 !=null '> and c_a_date between #{c_a_date1} and #{c_a_date2} </if>"+
            "            <if test='c_combine_code !=null &amp;&amp; c_combine_code !=\"\"'> and c_combine_code like '%${c_combine_code}%' </if>"+
            "            <if test='c_barcode !=null &amp;&amp; c_barcode !=\"\"'> and c_barcode like '%${c_barcode}%' </if>"+
          /*  "            <if test='c_hospital !=null &amp;&amp; c_hospital !=\"\"'> and c_hospital = #{c_hospital} </if>"+*/
            "            <if test='c_rpt_flag !=null &amp;&amp; c_rpt_flag !=\"\"'> and c_rpt_flag = #{c_rpt_flag} </if>"+
            "            )\n" +
            "            AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize}  </script>")
    List<SendApplication> getApplicationList(@Param(value = "organizationId")String organizationId,
                                             @Param(value = "p_name") String p_name,
                                             @Param(value = "pageNo") int pageNo,
                                             @Param(value = "pageSize") int pageSize,
                                             @Param(value = "c_a_date1") Timestamp c_a_date1,
                                             @Param(value = "c_a_date2") Timestamp c_a_date2,
                                             @Param(value = "c_combine_code") String c_combine_code,
                                             @Param(value = "c_barcode") String c_barcode,
                                             @Param(value = "c_rpt_flag") String c_rpt_flag);


    @Select("<script> with temp (id,name,parentId)\n" +
            "            as\n" +
            "            (\n" +
            "            select id,name,parentId\n" +
            "            from organization\n" +
            "            where id = #{organizationId}\n" +
            "            union all\n" +
            "            select a.id,a.name,a.parentId\n" +
            "            from organization a\n" +
            "            inner join temp on a.parentId = temp.id\n" +
            "            )\n" +
            "            SELECT count(1)\n" +
            "            from patients p LEFT JOIN checkinfo c on p.p_id = c.c_p_id\n" +
            "            INNER JOIN temp on c.c_hospital=name\n" +
            "            where 1=1 and \n" +
            "            isnull(c.c_rpt_flag,0)>0 " +
            "            <if test='p_name !=null &amp;&amp; p_name !=\"\"'> and p_name like '%${p_name}%' </if>"+
            "            <if test='c_a_date1 !=null '> and c_a_date between #{c_a_date1} and #{c_a_date2} </if>"+
            "            <if test='c_combine_code !=null &amp;&amp; c_combine_code !=\"\"'> and c_combine_code like '%${c_combine_code}%' </if>"+
            "            <if test='c_barcode !=null &amp;&amp; c_barcode !=\"\"'> and c_barcode like '%${c_barcode}%' </if>"+
            /*"            <if test='c_hospital !=null &amp;&amp; c_hospital !=\"\"'> and c_hospital = #{c_hospital} </if>"+*/
            "            <if test='c_rpt_flag !=null &amp;&amp; c_rpt_flag !=\"\"'> and c_rpt_flag = #{c_rpt_flag} </if>" +
            "            </script>" )
    long getReportCount(@Param(value = "organizationId")String organizationId,
                        @Param(value = "p_name") String p_name,
                        @Param(value = "pageNo") int pageNo,
                        @Param(value = "pageSize") int pageSize,
                        @Param(value = "c_a_date1") Timestamp c_a_date1,
                        @Param(value = "c_a_date2") Timestamp c_a_date2,
                        @Param(value = "c_combine_code") String c_combine_code,
                        @Param(value = "c_barcode") String c_barcode,
                        @Param(value = "c_rpt_flag") String c_rpt_flag);


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
            "SELECT p.p_name,c.c_hospital,c.c_a_date,c.c_combine_code,c.c_rpt_flag FROM " +
            "patients p INNER JOIN checkinfo c on p.p_id = c.c_p_id " +
            " <if test='a_lr_date1 !=null '> and c_a_date between #{a_lr_date1} and #{a_lr_date2} </if>"+
            " <if test='c_hospital !=null &amp;&amp; c_hospital !=\"\"'> and c_hospital = #{c_hospital} </if>"+
            " </script>")
    List<SendApplication> getStatisticsCheckInfoCombine(@Param(value = "organizationId") String organizationId,
                                                        @Param(value = "a_lr_date1")Timestamp a_lr_date1,
                                                        @Param(value = "a_lr_date2")Timestamp a_lr_date2,
                                                        @Param(value = "c_hospital")String c_hospital);





}
