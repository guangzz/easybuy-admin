package cn.situ.bean;

import java.io.Serializable;

public class JsonModel implements Serializable {
    /** * */
    private static final long serialVersionUID = -7830500053112045585L;
    private Integer code;
    private String message;
    private Object data;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getDate() {
        return data;
    }
    public void setDate(Object data) {
        this.data = data;
    }

}
