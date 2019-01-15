package cn.situ.service;

import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;

import java.util.List;

public interface ISortsService {
    /**
     * 查询全部
     * @return
     */
    List<Sorts> findAll();

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    PageBean<Sorts> getSortsList(Integer currPage);

    /**
     * 更新一级分类
     * @param sorts
     * @return
     */
    boolean update(Sorts sorts);

    /**
     * 删除一级分类
     * @param sorts
     * @return
     */
    boolean delete(Sorts sorts);

    /**
     * 更新二级分类
     * @param levelSort
     * @return
     */
    boolean update(LevelSort levelSort);

    /**
     * 删除二级分类
     * @param levelSort
     * @return
     */
    boolean delete(LevelSort levelSort);

    /**
     * 二级分类保存
     * @param levelSort
     * @return
     */
    boolean insert(LevelSort levelSort);

    /**
     * 一级分类保存
     * @param sorts
     * @return
     */
    boolean insert(Sorts sorts);
}
