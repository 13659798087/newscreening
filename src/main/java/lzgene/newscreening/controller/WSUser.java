package lzgene.newscreening.controller;


import lzgene.newscreening.dao.UserDao;
import lzgene.newscreening.datasource.DBIdentifier;
import lzgene.newscreening.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户数据访问接口。
 *
 * @author elon
 * @version 2018年2月26日
 */
@RestController
@RequestMapping(value="/user")
public class WSUser {

    @Resource
    private UserDao userDao;

    //要求每次查询都要带上projectCode参数。

    /**
     * 查询项目中所有用户信息
     *
     * @param projectCode 项目编码
     * @return 用户列表
     */
    @RequestMapping(value="/v1/users", method= RequestMethod.GET)
    public List<UserInfo> queryUser(@RequestParam(value="projectCode", required=true) String projectCode)
    {

        DBIdentifier.setProjectCode(projectCode);
        return userDao.getUsers();

    }
}