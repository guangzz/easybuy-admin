package cn.situ.dao.impl;

import cn.situ.bean.Products;
import cn.situ.dao.IProductsDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("productsDao")
public class ProductsDaoImpl extends BaseDaoImpl<Products> implements IProductsDao {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
}
