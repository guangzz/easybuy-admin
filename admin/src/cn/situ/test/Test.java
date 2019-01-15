package cn.situ.test;

import cn.situ.bean.*;
import cn.situ.dao.IUsersDao;
import cn.situ.service.IOrderService;
import cn.situ.service.IProductsService;
import cn.situ.service.ISortsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
        IUsersDao usersDao = (IUsersDao) ac.getBean("usersDao");
    }

    @org.junit.Test
    public void test01(){
        String hql = "from User where ";
        Map<String,Object> map = new HashMap<>(0);

        map.put("mPhone","15673733303");



        Set<String> set=map.keySet();
        Integer count = 0;
        for(String key:set){
            String value= (String) map.get(key);
            hql+=key + "='"+value+"'  and ";
            count++;
        }
        System.out.println(hql.substring(0,hql.length()-6));//
    }

    @org.junit.Test
    public void Test02(){
        int currPage = 2;
        int pageSize = 20;
        int begin = (currPage - 1) * pageSize;
        int end = currPage * pageSize;
        System.out.println(begin+"___"+end);
    }

    @org.junit.Test
    public void test03(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
        IProductsService productsService = (IProductsService)ac.getBean("productsService");
        Products p = new Products();
        p.setpId(1000);
        boolean b = productsService.setProductsState(p);
        System.out.println(b);
    }

    @org.junit.Test
    public void test04(){
        String phone = "15673733303";
        System.out.println(phone.length());
    }

    @org.junit.Test
    public void test05(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
        ISortsService ss = (ISortsService)ac.getBean("sortsService");
        List<Sorts> all = ss.findAll();
        System.out.println(all);
    }

    @org.junit.Test
    public void test06(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String format = sdf.format(timestamp);
        Timestamp timestamp1 = Timestamp.valueOf(format);
        System.out.println( timestamp1);
    }

    @org.junit.Test
    public void test07(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
        IOrderService orderService = (IOrderService) ac.getBean("orderService");
        OrderInfo info = new OrderInfo();
        info.setOiId(Long.parseLong("20181218142956357"));
        List<OrderProduct> detailedOrder = orderService.findDetailedOrder(info);
        System.out.println(detailedOrder);
    }

    @org.junit.Test
    public void test08(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             Date today = new Date();
             String endDate = sdf.format(today);//当前日期
             Calendar theCa = Calendar.getInstance();
            theCa.setTime(today);
            for(int i=0;i<15;i++) {
                theCa.add(theCa.DATE, -i);//最后一个数字30可改，30天的意思
                Date start = theCa.getTime();
                String startDate = sdf.format(start);//三十天之前日期
                System.out.println(startDate);
            }
    }
}
