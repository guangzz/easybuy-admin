package cn.situ.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sorts")
public class Sorts implements Serializable {
    private int sId;
    private String sName;
    private Set<LevelSort> levelSorts = new HashSet<LevelSort>(0);


    @OneToMany(targetEntity=LevelSort.class,mappedBy="sId",fetch = FetchType.EAGER)
    public Set<LevelSort> getLevelSorts() {
        return levelSorts;
    }

    public void setLevelSorts(Set<LevelSort> levelSorts) {
        this.levelSorts = levelSorts;
    }

    @Id
    @Column(name = "s_id")
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "s_name")
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorts sorts = (Sorts) o;
        return sId == sorts.sId &&
                Objects.equals(sName, sorts.sName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sId, sName);
    }

    @Override
    public String toString() {
        return "Sorts{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", levelSorts=" + levelSorts +
                '}';
    }
}
