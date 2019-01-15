package cn.situ.dao.impl;

import cn.situ.bean.PageBean;
import cn.situ.dao.IBaseDao;
import cn.situ.tools.GenericsUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDaoImpl<T> implements IBaseDao<T> {

    private final Class<T> persistentClass;

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public BaseDaoImpl(){
        persistentClass = GenericsUtils.getSuperClassGenricType(this.getClass());
    }

    @Override
    public List<T> findAll() {
        System.out.println(persistentClass);
        return (List<T>) hibernateTemplate.find("from "+persistentClass.getName());
    }

    @Override
    public T find(DetachedCriteria criteria) {
        List<T> objects = (List<T>) hibernateTemplate.findByCriteria(criteria);
        if(objects.size()>0){
            return objects.get(0);
        }
        return null;
    }

    @Override
    public Integer getCount(DetachedCriteria criteria) {
        List<T> objects = (List<T>) hibernateTemplate.findByCriteria(criteria);
        if(objects.size()>0){
            return objects.size();
        }
        return 0;
    }

    @Override
    public PageBean<T> getList(DetachedCriteria criteria,PageBean<T> pageBean,Integer begin,Integer end) {
        List<T> list = (List<T>) hibernateTemplate.findByCriteria(criteria,begin , end);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public T find(Serializable id) {
        return (T) hibernateTemplate.get(persistentClass.getName(),id);
    }

    @Override
    public boolean update(T t) {
        try{
            hibernateTemplate.update(t);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean save(T t) {
        try{
            hibernateTemplate.save(t);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<T> findList(DetachedCriteria detachedCriteria) {
        return (List<T>) hibernateTemplate.findByCriteria(detachedCriteria);
    }

    @Override
    public T get(String obj,Serializable id) {
        List<?> objects = hibernateTemplate.find("from " + persistentClass.getName()+" where "+obj+"=?0", id);
        if(objects.size()>0){
            return (T) objects.get(0);
        }
        return null;
    }

    @Override
    public boolean delete(T t) {
        try{
            hibernateTemplate.delete(t);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
