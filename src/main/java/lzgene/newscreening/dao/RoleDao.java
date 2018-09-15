package lzgene.newscreening.dao;

import lzgene.newscreening.model.Role;
import lzgene.newscreening.model.UserRoleMenu;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;

@Mapper
public interface RoleDao {

    @Select("SELECT * from role ")
    List<Role> getAllRole();

    @Insert("INSERT INTO userRole (id,userId,roleId) VALUES (#{id},#{userId},#{roleId});")
    void insertRela(@Param(value="id") String id,
                    @Param(value="userId")String userId,
                    @Param(value="roleId")String roleId);

    @Select("SELECT * FROM userRole ur \n" +
            "LEFT JOIN userinfo u on ur.userId=u.userId\n" +
            "LEFT JOIN role r on ur.roleId=r.roleId\n" +
            "where  u.userId=#{userId}")
    List<UserRoleMenu> getRoleByUserId(String userId);


    @Delete("delete from userRole where userId=#{userId} ")
    void deleteRela(String userId);

    @Insert("insert into role (roleId,roleName,roleLevel) VALUES (#{roleId},#{roleName},#{roleLevel})")
    void creatRole(@Param(value="roleId")String roleId,
                   @Param(value="roleName")String roleName,
                   @Param(value="roleLevel")String roleLevel);

    @Update("update role set roleName=#{roleName},roleLevel=#{roleLevel} where roleId=#{roleId} ")
    void updateRole(@Param(value="roleId")String roleId,
                    @Param(value="roleName")String roleName,
                    @Param(value="roleLevel")String roleLevel);

    @Delete("delete from roleMenu where roleId=#{roleId} ")
    void deleteRoleMune(String roleId);

    @Select("SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY roleName DESC) AS num FROM role " +
            "where roleName like '%${roleName}%')AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} ")
    List<Role> getRoleByNum(@Param(value = "roleName")String roleName,
                            @Param(value = "pageNo") int pageNo,
                            @Param(value = "pageSize") int pageSize);

    @Select("SELECT count(1) FROM role where roleName like '%${roleName}%' ")
    long getRoleCount(@Param(value = "roleName")String roleName,
                      @Param(value = "pageNo") int pageNo,
                      @Param(value = "pageSize") int pageSize);

    @Delete("delete from role where roleId=#{roleId} ")
    void deleteRole(String roleId);

    @Delete("delete from userRole where roleId=#{roleId} ")
    void deleteUserRole(String roleId);

    @Delete("delete from roleMenu where roleId=#{roleId} ")
    void deleteRoleMenu(String roleId);


}
