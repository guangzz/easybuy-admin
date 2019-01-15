package cn.situ.dao;

import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;

public interface ISortsDao extends IBaseDao<Sorts> {

    LevelSort find(LevelSort levelSort);

    boolean update(LevelSort levelSort);

    boolean delete(LevelSort levelSort);

    boolean save(LevelSort levelSort);

    PageBean<Sorts> getList(PageBean<Sorts> pageBean,Integer begin,Integer end);
}
