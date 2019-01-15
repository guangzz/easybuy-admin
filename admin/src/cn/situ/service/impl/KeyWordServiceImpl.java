package cn.situ.service.impl;

import cn.situ.bean.Keywords;
import cn.situ.bean.PageBean;
import cn.situ.bean.Users;
import cn.situ.dao.IKeywordsDao;
import cn.situ.service.IKeyWordService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("keyWordService")
public class KeyWordServiceImpl implements IKeyWordService {

    //通用写法不用管
    @Resource(name = "keywordsDao")
    private IKeywordsDao keywordsDao;

    @Override
    public PageBean<Keywords> findKeyWord(Integer currPage) {
        PageBean<Keywords> pageBean = new PageBean<Keywords>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Keywords.class);
        //分页PageBase设置
        Integer totalCount = keywordsDao.getCount(detachedCriteria);//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;//设置每页多少条
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * pageSize;//开始查询的条数
        int end = currPage * pageSize;//结束查询的条数
        //从万能dao中查询分页数据并返回
        PageBean<Keywords> list = keywordsDao.getList(detachedCriteria, pageBean, begin, end);
        return list;
    }

    @Override
    public boolean update(Keywords keywords) {
        Keywords keywords1 = keywordsDao.find(keywords.getkId());
        if(keywords1!=null){
            keywords1.setkName(keywords.getkName());
            return keywordsDao.update(keywords1);
        }
        return false;
    }

    @Override
    public boolean delete(Keywords keywords) {
        Keywords keywords1 = keywordsDao.find(keywords.getkId());
        if(keywords1!=null){
            return keywordsDao.delete(keywords1);
        }
        return false;
    }

    @Override
    public boolean insert(Keywords keywords) {
        return keywordsDao.save(keywords);
    }
}
