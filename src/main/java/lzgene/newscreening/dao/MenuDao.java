package lzgene.newscreening.dao;

import lzgene.newscreening.model.Menu;
import lzgene.newscreening.model.Signpic;
import lzgene.newscreening.model.UserRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDao {

    @Select("select * from menu")
    List<Menu> getAllMenu();


    @Select("SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY menuName DESC) AS num FROM menu " +
            "where menuName like '%${menuName}%' )AS t WHERE  t.num BETWEEN #{pageNo} AND #{pageSize} ")
    List<Menu> getMenuByNum(@Param(value = "menuName")String menuName,
                            @Param(value = "pageNo") int pageNo,
                            @Param(value = "pageSize") int pageSize);

    @Select("select count(1) from menu where menuName like '%${menuName}%' ")
    long getMenuCount(@Param(value = "menuName")String menuName,
                      @Param(value = "pageNo") int pageNo,
                      @Param(value = "pageSize") int pageSize);

    @Insert("INSERT INTO roleMenu (id,roleId,menuId) VALUES (#{id},#{roleId},#{menuId})")
    void insertMenu(@Param(value="id")String id,
                    @Param(value="roleId")String roleId,
                    @Param(value="menuId")String menuId);

    @Select("SELECT * from role r \n" +
            "LEFT JOIN roleMenu rm on r.roleId=rm.roleId\n" +
            "LEFT JOIN  menu m on rm.menuId=m.menuId where r.roleId=#{roleId} ")
    List<UserRoleMenu> getRoleByUserId(String roleId);

    @Select("select menuId id,menuName name,parentId pId from menu")
    List<Map<String,Object>> getMenuTree();


}
