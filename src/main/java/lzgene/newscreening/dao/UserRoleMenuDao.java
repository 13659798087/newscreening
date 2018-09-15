package lzgene.newscreening.dao;

import lzgene.newscreening.model.UserRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleMenuDao {


    @Select("SELECT * from roleMenu rm inner join menu m on m.menuId = rm.menuId\n" +
            " inner join role r on r.roleId = rm.roleId\n" +
            " inner join userRole ur on ur.roleId = r.roleId\n" +
            " inner join  userinfo u on u.userId = ur.userId\n" +
            " where userName = #{userName} ")
    List<Map<String, Object>> getLoginMessage(String userName);

    @Select("SELECT count(*) from userinfo ")
    long getUserCount();


}
