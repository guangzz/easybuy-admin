package cn.situ.dao.impl;

import cn.situ.bean.OrderInfo;
import cn.situ.dao.IOrderInfoDao;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements IOrderInfoDao {
}
