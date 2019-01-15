package cn.situ.dao.impl;

import cn.situ.bean.OrderProduct;
import cn.situ.dao.IOrderProductsDao;
import org.springframework.stereotype.Repository;

@Repository("orderProductsDao")
public class OrderProductsDaoImpl extends BaseDaoImpl<OrderProduct> implements IOrderProductsDao {
}
