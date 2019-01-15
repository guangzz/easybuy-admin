package cn.situ.service.impl;

import cn.situ.bean.Manager;
import cn.situ.dao.IManagerDao;
import cn.situ.service.IManagerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("managerService")
public class ManagerServiceImpl implements IManagerService {

    @Resource(name = "managerDao")
    private IManagerDao managerDao;

    @Override
    public Manager login(Manager manager) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Manager.class);
        detachedCriteria.add(Restrictions.eq("mPhone",manager.getmPhone()));
        detachedCriteria.add(Restrictions.eq("mPwd",manager.getmPwd()));
        return managerDao.find(detachedCriteria);
    }
}
