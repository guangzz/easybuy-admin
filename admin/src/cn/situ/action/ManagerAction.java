package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.Manager;
import cn.situ.service.IManagerService;
import cn.situ.tools.JsonTools;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //继承包
public class ManagerAction extends ActionSupport {

    private JsonModel jsonModel;

    @Resource(name = "managerService")
    private IManagerService managerService;

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public ManagerAction(){
        jsonModel = new JsonModel();
    }

    @Action(value="/login",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","true","noCache","true"}))
//root，指返回对象 excludeNullProperties 指去除json数据中的空值 ，noCache 指无缓存，键值对形式配置
    public String login() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestPostData = JsonTools.getRequestPostData(request);
        Manager manager;
        try {
            manager = new Gson().fromJson(requestPostData, Manager.class);
        }catch (Exception e){
            manager = null;
        }
        if(manager!=null) {
            Manager login = managerService.login(manager);
            if(login!=null) {
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                jsonModel.setDate(login);
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("提示：用户不存在，或者密码错误");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("提示：错误的请求");
        return SUCCESS;
    }


}
