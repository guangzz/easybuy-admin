package cn.situ.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "manager")
public class Manager {
    private int mId;
    private String mName;
    private String mPwd;
    private String mSex;
    private Integer mAge;
    private String mPhone;

    @Id
    @Column(name = "m_id")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Basic
    @Column(name = "m_name")
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Basic
    @Column(name = "m_pwd")
    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    @Basic
    @Column(name = "m_sex")
    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    @Basic
    @Column(name = "m_age")
    public Integer getmAge() {
        return mAge;
    }

    public void setmAge(Integer mAge) {
        this.mAge = mAge;
    }

    @Basic
    @Column(name = "m_phone")
    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return mId == manager.mId &&
                Objects.equals(mName, manager.mName) &&
                Objects.equals(mPwd, manager.mPwd) &&
                Objects.equals(mSex, manager.mSex) &&
                Objects.equals(mAge, manager.mAge) &&
                Objects.equals(mPhone, manager.mPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mPwd, mSex, mAge, mPhone);
    }
}
