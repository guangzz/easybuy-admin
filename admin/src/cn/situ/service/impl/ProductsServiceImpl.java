package cn.situ.service.impl;

import cn.situ.bean.PageBean;
import cn.situ.bean.Products;
import cn.situ.bean.Users;
import cn.situ.dao.IProductsDao;
import cn.situ.service.IProductsService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("productsService")
public class ProductsServiceImpl implements IProductsService {

    @Resource(name = "productsDao")
    private IProductsDao productsDao;


    @Override
    public PageBean<Products> getProductsList(Integer currPage, Products products) {
        PageBean<Products> pageBean = new PageBean<Products>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Products.class);
        if(!"".equals(products.getpTitle())){
            detachedCriteria.add(Restrictions.ilike("pTitle","%"+products.getpTitle()+"%"));
        }
        if(products.getLsId()!=null&&products.getLsId().getsId()!=null&&products.getLsId().getsId().getsId()!=0){
            detachedCriteria.createAlias("lsId", "s");
            detachedCriteria.add(Restrictions.eq("s.sId.sId",products.getLsId().getsId().getsId()));
        }
        if(products.getLsId()!=null&&products.getLsId().getLsId()!=0){
            detachedCriteria.add(Restrictions.eq("lsId.lsId",products.getLsId().getLsId()));
        }
        //分页PageBase设置
        Integer totalCount = productsDao.getCount(detachedCriteria);//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * pageSize;
        int end = currPage * pageSize;
        PageBean<Products> list = productsDao.getList(detachedCriteria, pageBean, begin, end);
        return pageBean;
    }

    @Override
    public boolean setProductsState(Products products) {
        Products p_result = productsDao.find(products.getpId());
        if(p_result!=null){
            if(p_result.getpState()==1){
                p_result.setpState(0);
            }else{
                p_result.setpState(1);
            }
            return productsDao.update(p_result);
        }
        return false;
    }

    @Override
    public boolean updateProducts(Products products) {
        Products products1 = productsDao.find(products.getpId());
        if(products1!=null){
            products1.setpState(products.getpState());
            products1.setLsId(products.getLsId());
            products1.setpDescription(products.getpDescription());
            products1.setpDiscount(products.getpDiscount());
            products1.setpImages(products.getpImages());
            products1.setpPrice(products.getpPrice());
            products1.setpQuantity(products.getpQuantity());
            products1.setpStock(products.getpStock());
            products1.setpTitle(products.getpTitle());
            return productsDao.update(products1);
        }
        return false;
    }

    @Override
    public boolean addProducts(Products products) {
        return productsDao.save(products);
    }
}
