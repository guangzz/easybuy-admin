package cn.situ.service;

import cn.situ.bean.OrderInfo;
import cn.situ.bean.OrderProduct;
import cn.situ.bean.PageBean;

import java.util.List;

public interface IOrderService {
    /**
     * 查询今日订单
     * @return
     */
    PageBean<OrderInfo> findToday(Integer currPage, OrderInfo info,String beginTime,String endTime);

    /**
     * 根据订单号查询所购买的商品
     * @param info
     * @return
     */
    List<OrderProduct> findDetailedOrder(OrderInfo info);

    /**
     * 物流单号更新
     * @param info
     * @return
     */
    boolean update(OrderInfo info);

    /**
     * 历史订单
     * @param currPage
     * @param info
     * @return
     */
    PageBean<OrderInfo> findHistory(Integer currPage, OrderInfo info,String beginTime,String endTime);


    List<OrderInfo> findRecently();
}
