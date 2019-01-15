package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.PageBean;
import cn.situ.bean.Products;
import cn.situ.service.IProductsService;
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

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //继承包
public class ProductsAction extends ActionSupport implements ModelDriven<Products> {

    @Resource(name = "productsService")
    private IProductsService productsService;

    private Products products = new Products();

    private JsonModel jsonModel;

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public ProductsAction(){
        jsonModel = new JsonModel();
    }

    /**
     * 商品列表
     * @return
     */
    @Action(value = "/product/list",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getProductsList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String page = request.getParameter("page");
        if(!(page==null||"".equals(page))){
            PageBean<Products> productsList = productsService.getProductsList(Integer.parseInt(page), getModel());
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(productsList);
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("错误的请求");
        return SUCCESS;
    }

    /**
     * 下架/上架 商品
     * @return
     */
    @Action(value = "/product/state",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String setProductsState(){
        boolean flag = productsService.setProductsState(getModel());
        if(flag){
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("操作失败");
        return SUCCESS;
    }

    /**
     * 更新
     * @return
     */
    @Action(value = "/product/update",results = @Result(type = "json",name = "success",params = {"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String updateProducts(){
        boolean flag = productsService.updateProducts(getModel());
        if(flag){
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("操作失败");
        return SUCCESS;
    }

    @Action(value = "/product/add",results = @Result(type = "json",name = "success",params = {"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String addProducts(){
        boolean flag = productsService.addProducts(getModel());
        if(flag){
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("操作失败");
        return SUCCESS;
    }
    @Override
    public Products getModel() {
        return products;
    }
}
