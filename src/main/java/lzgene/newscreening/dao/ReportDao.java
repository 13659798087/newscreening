package lzgene.newscreening.dao;

import lzgene.newscreening.entiry.ConfigReport;
import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.entiry.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportDao {

    @Update(" update checkinfo set c_rpt_flag=2 where c_id=#{c_id}")
    void updatePrintFlag(String c_id);

    @Select("select * from configReport where flag=0")
    List<ConfigReport> getStatisticsMsg();

    @Select("select * from configReport where id=#{id} ")
    ConfigReport getConfigReportById(String id);

    @Delete("delete from resulto where r_combine_code=#{c_code} and r_sid = #{c_sid} ")
    void deleteResulto(@Param(value="c_sid")String c_sid,@Param(value="c_code") String c_code);

    @Delete("delete from checkinfo where c_combine_code=#{c_code} and c_sid = #{c_sid} ")
    void deleteCheckinfo(@Param(value="c_sid")String c_sid,@Param(value="c_code") String c_code);

    @Delete("delete from patients where p_id in (select c_p_id from checkinfo where c_combine_code=#{c_code} and c_sid = #{c_sid} ) ")
    void deletePatients(@Param(value="c_sid")String c_sid,@Param(value="c_code") String c_code);


}
