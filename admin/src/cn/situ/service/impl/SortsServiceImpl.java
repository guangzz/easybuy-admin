package cn.situ.service.impl;

import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;
import cn.situ.dao.ISortsDao;
import cn.situ.service.ISortsService;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sortsService")
public class SortsServiceImpl implements ISortsService {

    @Resource(name = "sortsDao")
    private ISortsDao sortsDao;

    @Override
    public List<Sorts> findAll() {
        List<Sorts> all = sortsDao.findAll();
        return all;
    }

    @Override
    public PageBean<Sorts> getSortsList(Integer currPage) {
        PageBean<Sorts> pageBean = new PageBean<Sorts>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sorts.class);
        Integer totalCount = sortsDao.findAll().size();//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * pageSize;
        int end = currPage * pageSize;
        PageBean<Sorts> list = sortsDao.getList(pageBean, begin, end);
        return list;
    }

    @Override
    public boolean update(Sorts sorts) {
        Sorts sort = sortsDao.find(sorts.getsId());
        if(sort!=null){
            sort.setsName(sorts.getsName());
            return sortsDao.update(sort);
        }
        return false;
    }

    @Override
    public boolean delete(Sorts sorts) {
        Sorts sorts1 = sortsDao.find(sorts.getsId());
        if(sorts1!=null){
            return sortsDao.delete(sorts1);
        }
        return false;
    }

    @Override
    public boolean update(LevelSort levelSort) {
        LevelSort levelSort1 = sortsDao.find(levelSort);
        if(levelSort1!=null){
            levelSort1.setLsName(levelSort.getLsName());
            return sortsDao.update(levelSort1);
        }
        return false;
    }

    @Override
    public boolean delete(LevelSort levelSort) {
        LevelSort levelSort1 = sortsDao.find(levelSort);
        if(levelSort1!=null){
            return sortsDao.delete(levelSort1);
        }
        return false;
    }

    @Override
    public boolean insert(LevelSort levelSort) {
        return sortsDao.save(levelSort);
    }

    @Override
    public boolean insert(Sorts sorts) {
        return sortsDao.save(sorts);
    }

}
