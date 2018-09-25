package lzgene.newscreening.dao;

import lzgene.newscreening.model.Organization;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrganizationDao {

    @Select("SELECT id,parentId pId,name from organization where isDelete=0 ")
    List<Map<String,Object>> getAllOrganization();

    @Insert("INSERT INTO organization (id,name,parentId,level,isDelete) VALUES (#{id},#{name},#{parentId},#{level},0)")
    void createSon(Organization or);

    @Update("update organization set name= #{name} where id = #{id} ")
    void editName(@Param(value="id")String id, @Param(value="name")String name);

    @Update("update organization set isDelete= 1 where id = #{id} ")
    void removeNode(String id);

    @Select("SELECT * from organization where id=#{id} and isDelete = 0 ")
    Organization getOrg(String id);

    @Select("SELECT * from organization where isDelete=0 ")
    List<Organization> getOrganization();


    @Select("with temp ( id,name,parentId,isDelete)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId,isDelete\n" +
            "from organization\n" +
            "where id = #{organizationId} \n" +
            "union all\n" +
            "select a.id,a.name,a.parentId,a.isDelete\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "select id,parentId pId,name from temp where isDelete = 0 ")
    List<Map<String,Object>> getByOwn(String organizationId);

    //递归
    @Select("with temp ( id,name,parentId)\n" +
            "as\n" +
            "(\n" +
            "select id,name,parentId\n" +
            "from organization\n" +
            "where id = '10'\n" +
            "union all\n" +
            "select a.id,a.name,a.parentId\n" +
            "from organization a\n" +
            "inner join temp on a.parentId = temp.id\n" +
            ")\n" +
            "select name from temp ")
    List<String> listName(String organizationId);

    @Select("SELECT * from organization where isDelete=1 ")
    List<Organization> getDeleted();

    @Update("update organization set isDelete= 0 where id = #{id} ")
    void reduction(String id);

    @Select("SELECT * from organization where isDelete =0 and name = #{hospital}  ")
    Organization getHospitalLevel(String hospital);

    @Select("SELECT name from organization where  parentId = '0' ")
    String organizationName();


}