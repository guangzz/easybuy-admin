package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "keywords")
public class Keywords {
    private int kId;
    private String kName;

    @Id
    @Column(name = "k_id")
    public int getkId() {
        return kId;
    }

    public void setkId(int kId) {
        this.kId = kId;
    }

    @Basic
    @Column(name = "k_name")
    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keywords keywords = (Keywords) o;
        return kId == keywords.kId &&
                Objects.equals(kName, keywords.kName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kId, kName);
    }

    @Override
    public String toString() {
        return "Keywords{" +
                "kId=" + kId +
                ", kName='" + kName + '\'' +
                '}';
    }
}
