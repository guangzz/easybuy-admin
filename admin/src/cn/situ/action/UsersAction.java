package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.Manager;
import cn.situ.bean.PageBean;
import cn.situ.bean.Users;
import cn.situ.dao.IUsersDao;
import cn.situ.service.IUsersService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //继承包
public class UsersAction extends ActionSupport implements ModelDriven<Users>{

    private JsonModel jsonModel;

    @Resource(name = "usersService")
    private IUsersService usersService;

    private Users users = new Users();

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public UsersAction(){
        jsonModel = new JsonModel();
    }

    /**
     * 用户列表
     *
     * @return
     */
    @Action(value = "/user/list",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getUserList(){
        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获取客户端发送过来的参数
        String page = request.getParameter("page");//页数
        String value = request.getParameter("value");
        String select = request.getParameter("select");

        //判断page不为空
        if(!(page==null||"".equals(page))){
            Users u = null;
            //判断value不为空
            if(!(value==null||"".equals(value))) {
                u = new Users();
                //判断选择 姓名/手机
                if("uName".equals(select)){
                    u.setuName(value);
                }else{
                    u.setuPhone(value);
                }
            }
            PageBean<Users> userList = usersService.getUserList(Integer.parseInt(page), u);

            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(userList);
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("错误的请求");
        return SUCCESS;
    }

    /**
     * 封禁/恢复 用户
     * @return
     */
    @Action(value = "/user/state",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String setUserState(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        if(!(id==null||"".equals(id))){
            Users u = new Users();
            u.setuId(Integer.parseInt(id));
            boolean flag = usersService.setUserState(u);
            if(flag){//true
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("操作失败");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("错误的请求");
        return SUCCESS;
    }

    /**
     * 修改用户
     * @return
     */
    @Action(value = "/user/update",results = @Result(type = "json",name = "success",params = {"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String updateUser(){
        boolean flag = usersService.update(getModel());
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
    public Users getModel() {
        return users;
    }
}
