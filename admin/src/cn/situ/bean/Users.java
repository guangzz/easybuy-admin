package cn.situ.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    private int uId;
    private String uName;
    private String uPwd;
    private String uPhone;
    private Short uState;
    private String uOpenid;
    private Timestamp uTime;

    public Users(){

    }

    public Users(String uPhone){
        this.uPhone = uPhone;
    }

    @Id
    @Column(name = "u_id")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_name")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_pwd")
    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    @Basic
    @Column(name = "u_phone")
    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    @Basic
    @Column(name = "u_state")
    public Short getuState() {
        return uState;
    }

    public void setuState(Short uState) {
        this.uState = uState;
    }

    @Basic
    @Column(name = "u_openid")
    public String getuOpenid() {
        return uOpenid;
    }

    public void setuOpenid(String uOpenid) {
        this.uOpenid = uOpenid;
    }

    @Basic
    @Column(name = "u_time")
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Timestamp getuTime() {
        return uTime;
    }

    public void setuTime(Timestamp uTime) {
        this.uTime = uTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return uId == users.uId &&
                Objects.equals(uName, users.uName) &&
                Objects.equals(uPwd, users.uPwd) &&
                Objects.equals(uPhone, users.uPhone) &&
                Objects.equals(uState, users.uState) &&
                Objects.equals(uOpenid, users.uOpenid) &&
                Objects.equals(uTime, users.uTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uId, uName, uPwd, uPhone, uState, uOpenid, uTime);
    }
}
