package lzgene.newscreening.dao;

import lzgene.newscreening.model.Organization;
import lzgene.newscreening.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select count(userId) from  userinfo where userName=#{userName} and password=#{password} ")
    int loginValidate(@Param(value = "userName")String userName,@Param(value = "password")String password);

    @Insert("INSERT INTO userinfo (userId,userName,password,createTime,organizationId,parentId) " +
            " VALUES (#{userId},#{userName},#{password},getdate(),#{organizationId},#{parentId} )")
    void creatUser(@Param(value = "userId")String userId,
                   @Param(value = "userName")String userName,
                   @Param(value = "password")String password,
                   @Param(value = "organizationId")String organizationId,
                   @Param(value = "parentId")String parentId);

    @Select("select count(userId) from  userinfo where userName=#{userName} and organizationId=#{organizationId} ")
    int validateUser(@Param(value = "userName")String userName,@Param(value = "organizationId")String organizationId);


    @Update("update userinfo set userName=#{userName},password=#{password},dayLoginError=#{dayLoginError},organizationId=#{organizationId} where userId=#{userId} ")
    void updateUser(@Param(value = "userId")String userId,
                    @Param(value = "userName")String userName,
                    @Param(value = "password")String password,
                    @Param(value = "dayLoginError")String dayLoginError,
                    @Param(value = "organizationId")String organizationId);

    @Select("select * from  userinfo where userName=#{userName} and password=#{password} ")
    UserInfo getUserInfo(@Param(value = "userName")String userName,@Param(value = "password")String password);

    @Select("select * from userinfo where parentId=#{userId} ")
    List<UserInfo> getUserByParentId(String userId);

    @Select("<script>  SELECT * FROM ( " +
            "SELECT ROW_NUMBER() OVER(ORDER BY u.createTime DESC) AS num, u.*,o.name, (SELECT '['+r.roleName+']' FROM userRole ur " +
            "left join  role r  on ur.roleId=r.roleId " +
            "and ur.userId=u.userId  FOR xml path ('') ) listRole " +
            "from userinfo u left join organization o on u.organizationId=o.id and o.isDelete=0  where 1=1 " +
            "<if test='userName !=null &amp;&amp; userName !=\"\"'> and userName like '%${userName}%' </if>"+
            "<if test='organizationName !=null &amp;&amp; organizationName !=\"\"'> and o.name like '%${organizationName}%' </if>"+
            ")AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} </script> ")
    List<UserInfo> getUserMessage(@Param(value = "pageNo") int pageNo,
                                  @Param(value = "pageSize") int pageSize,
                                  @Param(value = "userName")String userName,
                                  @Param(value = "organizationName")String organizationName);



    @Select("<script>  SELECT count(1) FROM ( " +
            "SELECT ROW_NUMBER() OVER(ORDER BY u.createTime DESC) AS num, u.*,o.name, (SELECT '['+r.roleName+']' FROM userRole ur " +
            "left join  role r  on ur.roleId=r.roleId " +
            "and ur.userId=u.userId  FOR xml path ('') ) listRole " +
            "from userinfo u left join organization o on u.organizationId=o.id and o.isDelete=0 where 1=1 " +
            "<if test='userName !=null &amp;&amp; userName !=\"\"'> and userName like '%${userName}%' </if>"+
            "<if test='organizationName !=null &amp;&amp; organizationName !=\"\"'> and o.name like '%${organizationName}%' </if>"+
            ")AS t </script> ")
    long getUserCount(@Param(value = "pageNo") int pageNo,
                      @Param(value = "pageSize") int pageSize,
                      @Param(value = "userName")String userName,
                      @Param(value = "organizationName")String organizationName);


    @Delete("delete from userinfo where userId = #{userId}")
    void deleteUser(String userId);

    @Select("select * from userinfo where userName=#{userName} ")
    UserInfo userloginMessage(String userName);

    //每天登录的错误次数加1
    @Update("update userinfo set dayLoginError = dayLoginError + 1 where userName = #{userName} ")
    void addOneCount(String userName);

    @Update("UPDATE userinfo set dayLoginError=0 where dayLoginError <= #{dayLimitLoginError} ")
    void resetDayLoginError(Integer dayLimitLoginError);

    @Update("UPDATE userinfo set password =#{newPassword},updateTime= getDate() where userName = #{userName} and password = #{oldPassword} ")
    int changePsw(@Param(value = "userName") String userName,
                  @Param(value = "newPassword") String newPassword,
                  @Param(value = "oldPassword") String oldPassword);

    @Select("select * from organization where id = #{organizationId} ")
    Organization getOrganizationMessage(String organizationId);

    @Select(" with cte as\n" +
            "(\n" +
            "select ID,ParentID,name,o.[level],o.name\n" +
            "from organization o\n" +
            "where id = #{organizationId} \n" +
            "union all\n" +
            "select h.ID,h.ParentID,h.name,h.[level],h.name\n" +
            "from organization h\n" +
            "inner join cte c on h.id=c.ParentID\n" +
            ")\n" +
            "select * \n" +
            "from cte where cte.[level]='1'\n" +
            "order by ParentID; ")
    Organization getAuthorizeName(String organizationId);

    /**
     * 查询所有用户数据
     * @return 用户数据列表
     */
    @Select(" select * from UserInfo ")
    List<UserInfo> getUsers();

    @Update("UPDATE userinfo set password =#{psw} where userId = #{userId}  ")
    void updatePsw(@Param(value = "userId")String userId,@Param(value = "psw")String psw);

}
