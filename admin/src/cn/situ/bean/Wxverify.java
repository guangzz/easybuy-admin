package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wxverify")
public class Wxverify {
    private int wId;
    private Integer wKey;
    private String wOpenid;
    private Integer wState;

    @Id
    @Column(name = "w_id")
    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    @Basic
    @Column(name = "w_key")
    public Integer getwKey() {
        return wKey;
    }

    public void setwKey(Integer wKey) {
        this.wKey = wKey;
    }

    @Basic
    @Column(name = "w_openid")
    public String getwOpenid() {
        return wOpenid;
    }

    public void setwOpenid(String wOpenid) {
        this.wOpenid = wOpenid;
    }

    @Basic
    @Column(name = "w_state")
    public Integer getwState() {
        return wState;
    }

    public void setwState(Integer wState) {
        this.wState = wState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wxverify wxverify = (Wxverify) o;
        return wId == wxverify.wId &&
                Objects.equals(wKey, wxverify.wKey) &&
                Objects.equals(wOpenid, wxverify.wOpenid) &&
                Objects.equals(wState, wxverify.wState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wId, wKey, wOpenid, wState);
    }
}
