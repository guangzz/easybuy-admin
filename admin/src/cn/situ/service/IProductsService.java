package cn.situ.service;

import cn.situ.bean.PageBean;
import cn.situ.bean.Products;

public interface IProductsService {
    /**
     * 分页
     * @param currPage 当前页
     * @param products 分页的数据
     * @return
     */
    PageBean<Products> getProductsList(Integer currPage, Products products);

    /**
     * 上架/下架 商品
     * @param products
     * @return
     */
    boolean setProductsState(Products products);

    /**
     * 更新商品
     * @param products
     * @return
     */
    boolean updateProducts(Products products);

    boolean addProducts(Products products);
}
