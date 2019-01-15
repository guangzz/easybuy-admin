package cn.situ.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "level_sort", schema = "shop", catalog = "")
public class LevelSort implements Serializable {
    private int lsId;
    private String lsName;
    private Sorts sId;


    @Id
    @Column(name = "ls_id")
    public int getLsId() {
        return lsId;
    }

    public void setLsId(int lsId) {
        this.lsId = lsId;
    }

    @Basic
    @Column(name = "ls_name")
    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName;
    }

    @ManyToOne(targetEntity = Sorts.class)
    @JoinColumn(name = "s_id",referencedColumnName = "s_id")
    public Sorts getsId() {
        return sId;
    }

    public void setsId(Sorts sId) {
        this.sId = sId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelSort levelSort = (LevelSort) o;
        return lsId == levelSort.lsId &&
                Objects.equals(lsName, levelSort.lsName) &&
                Objects.equals(sId, levelSort.sId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lsId, lsName, sId);
    }

    @Override
    public String toString() {
        return "LevelSort{" +
                "lsId=" + lsId +
                ", lsName='" + lsName + '\'' +
                ", sId=" + sId +
                '}';
    }
}
