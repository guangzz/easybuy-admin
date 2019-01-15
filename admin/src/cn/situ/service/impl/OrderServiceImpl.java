package cn.situ.service.impl;

import cn.situ.bean.OrderInfo;
import cn.situ.bean.OrderProduct;
import cn.situ.bean.PageBean;
import cn.situ.dao.IOrderInfoDao;
import cn.situ.dao.IOrderProductsDao;
import cn.situ.service.IOrderService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Resource(name = "orderDao")
    private IOrderInfoDao orderDao;

    @Resource(name = "orderProductsDao")
    private IOrderProductsDao orderProductsDao;

    @Override
    @Transactional
    public PageBean<OrderInfo> findToday(Integer currPage,OrderInfo info,String beginTime,String endTime) {
        PageBean<OrderInfo> pageBean = new PageBean<OrderInfo>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderInfo.class);
        if(info!=null){
            if(info.getOiName()!=null){
                detachedCriteria.add(Restrictions.ilike("oiName","%"+info.getOiName()+"%"));
            }
            if(info.getOiPhone()!=null){
                detachedCriteria.add(Restrictions.ilike("oiPhone","%"+info.getOiPhone()+"%"));
            }
            if(info.getOiState()!=null&&!"".equals(info.getOiState())){
                detachedCriteria.add(Restrictions.eq("oiState",info.getOiState()));
            }
        }
        if(beginTime!=null&&!"".equals(beginTime)){
            detachedCriteria.add(Restrictions.ge("oiTime", Date.valueOf(beginTime)));
        }
        if(endTime!=null&&!"".equals(endTime)){
            detachedCriteria.add(Restrictions.le("oiTime",Date.valueOf(endTime)));
        }

        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String format = sdf.format(timestamp);
        Timestamp time = Timestamp.valueOf(format);

        detachedCriteria.add(Restrictions.ge("oiTime",time));
        //分页PageBase设置
        Integer totalCount = orderDao.getCount(detachedCriteria);//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * pageSize;
        int end = currPage * pageSize;
        PageBean<OrderInfo> list = orderDao.getList(detachedCriteria, pageBean, begin, end);
        return list;
    }

    @Override
    public List<OrderProduct> findDetailedOrder(OrderInfo info) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderProduct.class);
        detachedCriteria.add(Restrictions.eq("oiId",info.getOiId()));
        List<OrderProduct> list = orderProductsDao.findList(detachedCriteria);
        return list;
    }

    @Override
    public boolean update(OrderInfo info) {
        OrderInfo orderInfo = orderDao.get("oiId",info.getOiId());
        if(orderInfo!=null){
            orderInfo.setOiLogistics(info.getOiLogistics());
            orderInfo.setOiState(info.getOiState());
            return orderDao.update(orderInfo);
        }
        return false;
    }

    @Override
    @Transactional
    public PageBean<OrderInfo> findHistory(Integer currPage, OrderInfo info,String beginTime,String endTime) {
        PageBean<OrderInfo> pageBean = new PageBean<OrderInfo>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderInfo.class);
        if(info!=null){
            if(info.getOiName()!=null){
                detachedCriteria.add(Restrictions.ilike("oiName","%"+info.getOiName()+"%"));
            }
            if(info.getOiPhone()!=null){
                detachedCriteria.add(Restrictions.ilike("oiPhone","%"+info.getOiPhone()+"%"));
            }
            if(info.getOiState()!=null&&!"".equals(info.getOiState())){
                detachedCriteria.add(Restrictions.eq("oiState",info.getOiState()));
            }
        }
        if(beginTime!=null&&!"".equals(beginTime)){
            detachedCriteria.add(Restrictions.ge("oiTime", Date.valueOf(beginTime)));
        }
        if(endTime!=null&&!"".equals(endTime)){
            detachedCriteria.add(Restrictions.le("oiTime",Date.valueOf(endTime)));
        }

        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String format = sdf.format(timestamp);
        Timestamp time = Timestamp.valueOf(format);

        detachedCriteria.add(Restrictions.le("oiTime",time));
        //分页PageBase设置
        Integer totalCount = orderDao.getCount(detachedCriteria);//总条数
        pageBean.setCurrPage(currPage);//设置当前页数
        Integer pageSize = 20;
        pageBean.setPageSize(pageSize);//设置每页
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * pageSize;
        int end = currPage * pageSize;
        PageBean<OrderInfo> list = orderDao.getList(detachedCriteria, pageBean, begin, end);
        return list;
    }

    @Override
    public List<OrderInfo> findRecently() {
        return null;
    }
}
