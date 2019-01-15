package cn.situ.dao.impl;

import cn.situ.bean.Users;
import cn.situ.dao.IUsersDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("usersDao")
public class UsersDaoImpl extends BaseDaoImpl<Users> implements IUsersDao {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
}
