package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_product", schema = "shop", catalog = "")
public class OrderProduct {
    private int opId;
    private Long oiId;
    private Products pId;
    private Integer opCont;

    @Id
    @Column(name = "op_id")
    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    @Basic
    @Column(name = "oi_id")
    public Long getOiId() {
        return oiId;
    }

    public void setOiId(Long oiId) {
        this.oiId = oiId;
    }

    @ManyToOne(targetEntity = Products.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id", referencedColumnName = "p_id")
    public Products getpId() {
        return pId;
    }

    public void setpId(Products pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "op_cont")
    public Integer getOpCont() {
        return opCont;
    }

    public void setOpCont(Integer opCont) {
        this.opCont = opCont;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return opId == that.opId &&
                Objects.equals(oiId, that.oiId) &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(opCont, that.opCont);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opId, oiId, pId, opCont);
    }
}
