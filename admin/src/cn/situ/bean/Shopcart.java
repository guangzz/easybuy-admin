package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shopcart")
public class Shopcart {
    private int spId;
    private Integer spNum;
    private Integer pId;
    private Integer uId;
    private Integer spState;

    @Id
    @Column(name = "sp_id")
    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    @Basic
    @Column(name = "sp_num")
    public Integer getSpNum() {
        return spNum;
    }

    public void setSpNum(Integer spNum) {
        this.spNum = spNum;
    }

    @Basic
    @Column(name = "p_id")
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "u_id")
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "sp_state")
    public Integer getSpState() {
        return spState;
    }

    public void setSpState(Integer spState) {
        this.spState = spState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopcart shopcart = (Shopcart) o;
        return spId == shopcart.spId &&
                Objects.equals(spNum, shopcart.spNum) &&
                Objects.equals(pId, shopcart.pId) &&
                Objects.equals(uId, shopcart.uId) &&
                Objects.equals(spState, shopcart.spState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spId, spNum, pId, uId, spState);
    }
}
