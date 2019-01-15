package cn.situ.dao.impl;

import cn.situ.bean.Manager;
import cn.situ.dao.IManagerDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("managerDao")
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements IManagerDao {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

}
