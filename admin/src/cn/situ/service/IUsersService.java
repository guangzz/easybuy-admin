package cn.situ.service;

import cn.situ.bean.PageBean;
import cn.situ.bean.Users;

import java.util.List;

public interface IUsersService {
    /**
     * 分页
     * @param currPage
     * @param u
     * @return
     */
    PageBean<Users> getUserList(Integer currPage, Users u);

    /**
     * 操作状态
     * @param u
     * @return
     */
    boolean setUserState(Users u);

    /**
     * 更新数据
     * @param u
     * @return
     */
    boolean update(Users u);
}
