package cn.situ.service.impl;

import cn.situ.bean.PageBean;
import cn.situ.bean.Users;
import cn.situ.dao.IUsersDao;
import cn.situ.service.IUsersService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl implements IUsersService {

    @Resource(name = "usersDao")
    private IUsersDao usersDao;

    @Override
    public PageBean<Users> getUserList(Integer currPage, Users u) {
        PageBean<Users> pageBean = new PageBean<Users>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Users.class);

        if(u!=null) {
            if(u.getuPhone()!=null) {
                if (u.getuPhone().length() == 11) {
                    detachedCriteria.add(Restrictions.eq("uPhone", u.getuPhone()));
                } else {
                    detachedCriteria.add(Restrictions.ilike("uPhone", "%" + u.getuPhone() + "%"));
                }
            }else{
                detachedCriteria.add(Restrictions.ilike("uName","%" + u.getuName() + "%"));
            }
        }
        //分页PageBase设置
        Integer totalCount = usersDao.getCount(detachedCriteria);//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());//Double转int
        int begin = (currPage - 1) * pageSize;//开始的条数
        int end = currPage * pageSize;//结束的条数
        PageBean<Users> list = usersDao.getList(detachedCriteria, pageBean, begin, end);
        return list;
    }

    @Override
    public boolean setUserState(Users u) {
        Users users = usersDao.find(u.getuId());
        //当查询出user存在时
        if(users!=null){
            if(users.getuState()==1){
                users.setuState(new Short("0"));
            }else{
                users.setuState(new Short("1"));
            }
            boolean update = usersDao.update(users);
            if(update){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Users u) {
        Users users = usersDao.find(u.getuId());
        //当查询出的user存在时
        if(users!=null){
            users.setuPhone(u.getuPhone());
            users.setuName(u.getuName());
            users.setuState(u.getuState());
            boolean update = usersDao.update(users);
            if(update){
                return true;
            }
        }
        return false;
    }
}
