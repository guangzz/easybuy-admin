package cn.situ.dao;

import cn.situ.bean.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
    /**
     * 查询全部
     */
    List<T> findAll();

    /**
     * 条件查询
     * @param criteria
     * @return
     */
    T find(DetachedCriteria criteria);


    /**
     * 获取总条数
     * @param criteria
     * @return
     */
     Integer getCount(DetachedCriteria criteria);

    /**
     * 分页
     * @param criteria
     * @param pageBean
     * @param begin
     * @param end
     * @return
     */
     PageBean<T> getList(DetachedCriteria criteria,PageBean<T> pageBean,Integer begin,Integer end);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
     T find(Serializable id);

    /**
     * 更新
     * @param t
     * @return
     */
     boolean update(T t);

    /**
     * 保存
     * @param t
     * @return
     */
     boolean save(T t);

    /**
     * 查询全部带条件
     * @param detachedCriteria
     * @return
     */
     List<T> findList(DetachedCriteria detachedCriteria);

    /**
     * 查询单个
     * @param obj
     * @param id
     * @return
     */
     T get(String obj,Serializable id);

     boolean delete(T t);
}
