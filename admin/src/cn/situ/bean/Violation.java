package cn.situ.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Violation {
    private int vId;
    private String vValue;

    @Id
    @Column(name = "v_id")
    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    @Basic
    @Column(name = "v_value")
    public String getvValue() {
        return vValue;
    }

    public void setvValue(String vValue) {
        this.vValue = vValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return vId == violation.vId &&
                Objects.equals(vValue, violation.vValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vId, vValue);
    }
}
