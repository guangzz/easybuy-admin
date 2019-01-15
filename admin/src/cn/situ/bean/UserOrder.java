package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_order", schema = "shop", catalog = "")
public class UserOrder {
    private int uoId;
    private Integer uId;
    private Long oiId;

    @Id
    @Column(name = "uo_id")
    public int getUoId() {
        return uoId;
    }

    public void setUoId(int uoId) {
        this.uoId = uoId;
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
    @Column(name = "oi_id")
    public Long getOiId() {
        return oiId;
    }

    public void setOiId(Long oiId) {
        this.oiId = oiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return uoId == userOrder.uoId &&
                Objects.equals(uId, userOrder.uId) &&
                Objects.equals(oiId, userOrder.oiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uoId, uId, oiId);
    }
}
