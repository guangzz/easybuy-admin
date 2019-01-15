package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;
import cn.situ.service.ISortsService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.MidiDevice;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //¼Ì³Ð°ü
public class SortsAction extends ActionSupport implements ModelDriven<Sorts> {

    @Resource(name = "sortsService")
    private ISortsService sortsService;

    private JsonModel jsonModel;

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public SortsAction(){
        jsonModel = new JsonModel();
    }
    /**
     * ·ÖÀà
     * @return
     */
    @Action(value = "/sort/select",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getSortsList(){
        List<Sorts> all = sortsService.findAll();
        jsonModel.setCode(200);
        jsonModel.setDate(all);
        jsonModel.setMessage("success");
        return SUCCESS;
    }

    @Action(value = "/sort/list",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getSorts(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String page = request.getParameter("page");//Ò³Êý
        if(!(page==null||"".equals(page))){
            PageBean<Sorts> sortsList = sortsService.getSortsList(Integer.parseInt(page));
            jsonModel.setCode(200);
            jsonModel.setDate(sortsList);
            jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

        @Action(value = "/sort/update",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String updateSorts(){
            HttpServletRequest request = ServletActionContext.getRequest();
            String sId = request.getParameter("sId");
            String sName = request.getParameter("sName");
            if(!(sId==null||"".equals(sId))&&!(sName==null||"".equals(sName))){
                Sorts sorts = new Sorts();
                sorts.setsId(Integer.parseInt(sId));
                sorts.setsName(sName);
            boolean flag = sortsService.update(sorts);
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/sort/insert",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String insertSorts(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String sName = request.getParameter("sName");
        if(!(sName==null||"".equals(sName))){
            Sorts sorts = new Sorts();
            sorts.setsName(sName);
            boolean flag = false;
            try {
                flag = sortsService.insert(sorts);
            }catch (Exception e){
                flag = false;
            }
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/sort/delete",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String deleteSort(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String sId = request.getParameter("sId");
        String sName = request.getParameter("sName");
        if(!(sId==null||"".equals(sId))&&!(sName==null||"".equals(sName))){
            Sorts sorts = new Sorts();
            sorts.setsId(Integer.parseInt(sId));
            sorts.setsName(sName);
            boolean flag = false;
            try {
                flag = sortsService.delete(sorts);
            }catch (Exception e){
                flag = false;
            }
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/levelSorts/update",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String updateLevelSorts(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String lsId = request.getParameter("lsId");
        String lsName = request.getParameter("lsName");
        if(!(lsId==null||"".equals(lsId))&&!(lsName==null||"".equals(lsName))){
            LevelSort levelSort = new LevelSort();
            levelSort.setLsId(Integer.parseInt(lsId));
            levelSort.setLsName(lsName);
            boolean flag = sortsService.update(levelSort);
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/levelSorts/delete",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String deleteLevelSorts(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String lsId = request.getParameter("lsId");
        if(!(lsId==null||"".equals(lsId))){
            LevelSort levelSort = new LevelSort();
            levelSort.setLsId(Integer.parseInt(lsId));
            boolean flag = false;
            try {
                 flag = sortsService.delete(levelSort);
            }catch (Exception e){
                flag = false;
            }
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Action(value = "/levelSorts/insert",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String insertLevelSorts(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String sId = request.getParameter("sId");
        String lsName = request.getParameter("lsName");
        if(!(sId==null||"".equals(sId))&&!(lsName==null||"".equals(lsName))){
            LevelSort levelSort = new LevelSort();
            levelSort.setLsName(lsName);
            Sorts sorts = new Sorts();
            sorts.setsId(Integer.parseInt(sId));
            levelSort.setsId(sorts);
            boolean flag = sortsService.insert(levelSort);
            if(flag){
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("²Ù×÷Ê§°Ü");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("´íÎóµÄÇëÇó");
        return SUCCESS;
    }

    @Override
    public Sorts getModel() {
        return new Sorts();
    }
}
