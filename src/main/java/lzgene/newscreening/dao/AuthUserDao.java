package lzgene.newscreening.dao;

import lzgene.newscreening.model.AuthUser;
import lzgene.newscreening.model.AuthUser;
import lzgene.newscreening.model.Mb;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthUserDao {

    @Select("SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY au_name DESC) AS num FROM authuser " +
            "where au_name like '%${au_name}%')AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} ")
    List<AuthUser> authUserTable(@Param(value="au_name")String au_name,
                                 @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select(" select count(1) from authuser where au_name like '%${au_name}%' ")
    long getAuthUserCount(@Param(value="au_name")String au_name,
                          @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

    @Select(" select count(*) from authuser where au_name = #{au_name} ")
    int validateAuthUser(String au_name);

    @Insert("INSERT into authuser (au_code,au_name,au_address,au_linkman,au_tel,au_position,au_registration_code,au_key,au_remarks) " +
            "VALUES (#{au_code},#{au_name},#{au_address},#{au_linkman},#{au_tel},#{au_position},#{au_registration_code},#{au_key},#{au_remarks} )")
    void createAuthUser(@Param(value="au_code")String au_code,
                        @Param(value="au_name")String au_name,
                        @Param(value="au_address")String au_address,
                        @Param(value="au_linkman")String au_linkman,
                        @Param(value="au_tel")String au_tel,
                        @Param(value="au_position")String au_position,
                        @Param(value="au_registration_code")String au_registration_code,
                        @Param(value="au_key")String au_key,
                        @Param(value="au_remarks")String au_remarks);

    @Update("update authuser set au_name=#{au_name},au_address=#{au_address},au_linkman=#{au_linkman},au_tel=#{au_tel}," +
            "au_position=#{au_position},au_registration_code=#{au_registration_code},au_key=#{au_key},au_remarks=#{au_remarks} where au_code=#{au_code} ")
    void updateAuthUser(@Param(value="au_code")String au_code,
                        @Param(value="au_name")String au_name,
                        @Param(value="au_address")String au_address,
                        @Param(value="au_linkman")String au_linkman,
                        @Param(value="au_tel")String au_tel,
                        @Param(value="au_position")String au_position,
                        @Param(value="au_registration_code")String au_registration_code,
                        @Param(value="au_key")String au_key,
                        @Param(value="au_remarks")String au_remarks);


    @Delete("DELETE from authuser where au_code=#{au_code} ")
    void deleteAuthUser(String au_code);


}
