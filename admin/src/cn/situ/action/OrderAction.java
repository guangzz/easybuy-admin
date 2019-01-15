package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.OrderInfo;
import cn.situ.bean.OrderProduct;
import cn.situ.bean.PageBean;
import cn.situ.service.IOrderService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //¼Ì³Ð°ü
public class OrderAction extends ActionSupport implements ModelDriven<OrderInfo> {

    @Resource(name = "orderService")
    private IOrderService orderService;

    private JsonModel jsonModel;

    private OrderInfo orderInfo = new OrderInfo();

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public OrderAction(){
        jsonModel = new JsonModel();
    }

    @Action(value = "/order/today",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getTodayOrder(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String page = request.getParameter("page");//Ò³Êý
        String value = request.getParameter("value");
        String select = request.getParameter("select");
        String oiState = request.getParameter("oiState");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (!(page == null || "".equals(page))) {
            OrderInfo info = new OrderInfo();
            if (!(value == null || "".equals(value))) {
                if ("oiName".equals(select)) {
                    info.setOiName(value);
                } else {
                    info.setOiPhone(value);
                }
            }
            if(oiState!=null&&!"".equals(oiState)){
                info.setOiState(oiState);
            }
            PageBean<OrderInfo> today = orderService.findToday(Integer.parseInt(page), info,beginTime,endTime);
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(today);
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/order/detailed",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getDetailedOrder(){
        System.out.println(getModel());
        List<OrderProduct> detailedOrder = orderService.findDetailedOrder(getModel());
        if(detailedOrder.size()>0){
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(detailedOrder);
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("error");
        return SUCCESS;
    }
    @Action(value = "/order/delivery",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String toDelivery(){
        boolean flag = orderService.update(getModel());
         if(flag){
             jsonModel.setCode(200);
             jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("²Ù×÷Ê§°Ü");
        return SUCCESS;
    }

    @Action(value = "/order/history",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getHistoryOrder() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String page = request.getParameter("page");//Ò³Êý
        String value = request.getParameter("value");
        String select = request.getParameter("select");
        String oiState = request.getParameter("oiState");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (!(page == null || "".equals(page))) {
                OrderInfo info = new OrderInfo();
                if (!(value == null || "".equals(value))) {
                    if ("oiName".equals(select)) {
                        info.setOiName(value);
                    } else {
                        info.setOiPhone(value);
                    }
                }
                if(oiState!=null&&!"".equals(oiState)){
                    info.setOiState(oiState);
                }
                PageBean<OrderInfo> today = orderService.findHistory(Integer.parseInt(page), info,beginTime,endTime);
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                jsonModel.setDate(today);
                return SUCCESS;
            }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Override
    public OrderInfo getModel() {
        return orderInfo;
    }
}
