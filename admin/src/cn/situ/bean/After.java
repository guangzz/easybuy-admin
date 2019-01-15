package cn.situ.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "after")
public class After {
    private int aId;
    private Integer uId;
    private String aContent;
    private Timestamp aTime;

    @Id
    @Column(name = "a_id")
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
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
    @Column(name = "a_content")
    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    @Basic
    @Column(name = "a_time")
    public Timestamp getaTime() {
        return aTime;
    }

    public void setaTime(Timestamp aTime) {
        this.aTime = aTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        After after = (After) o;
        return aId == after.aId &&
                Objects.equals(uId, after.uId) &&
                Objects.equals(aContent, after.aContent) &&
                Objects.equals(aTime, after.aTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aId, uId, aContent, aTime);
    }
}
