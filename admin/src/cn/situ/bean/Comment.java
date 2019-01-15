package cn.situ.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {
    private int cId;
    private Timestamp cTime;
    private String cVal;
    private Integer pId;
    private Integer uId;
    private Double cScore;

    @Id
    @Column(name = "c_id")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_time")
    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "c_val")
    public String getcVal() {
        return cVal;
    }

    public void setcVal(String cVal) {
        this.cVal = cVal;
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
    @Column(name = "c_score")
    public Double getcScore() {
        return cScore;
    }

    public void setcScore(Double cScore) {
        this.cScore = cScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return cId == comment.cId &&
                Objects.equals(cTime, comment.cTime) &&
                Objects.equals(cVal, comment.cVal) &&
                Objects.equals(pId, comment.pId) &&
                Objects.equals(uId, comment.uId) &&
                Objects.equals(cScore, comment.cScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cTime, cVal, pId, uId, cScore);
    }
}
