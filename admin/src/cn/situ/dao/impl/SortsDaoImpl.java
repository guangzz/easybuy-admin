package cn.situ.dao.impl;

import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;
import cn.situ.dao.ISortsDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("sortsDao")
public class SortsDaoImpl extends BaseDaoImpl<Sorts> implements ISortsDao {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public LevelSort find(LevelSort levelSort) {
        return hibernateTemplate.get(LevelSort.class,levelSort.getLsId());
    }

    @Override
    public boolean update(LevelSort levelSort) {
        try{
            hibernateTemplate.update(levelSort);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(LevelSort levelSort) {
        try{
            hibernateTemplate.delete(levelSort);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean save(LevelSort levelSort) {
        try{
            hibernateTemplate.save(levelSort);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public PageBean<Sorts> getList(PageBean<Sorts> pageBean, Integer begin, Integer end) {
        List<Sorts> all = findAll();
        List<Sorts> sorts = new ArrayList<>();
        if(all.size()<pageBean.getPageSize()){
            sorts = all;
        }else {
            sorts.addAll(all.subList(begin + 1, end));
        }
        pageBean.setList(sorts);
        return  pageBean;
    }
}
