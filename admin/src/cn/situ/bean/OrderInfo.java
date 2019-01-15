package cn.situ.bean;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_info", schema = "shop", catalog = "")
public class OrderInfo {
    private long oiId;
    private Timestamp oiTime;
    private BigDecimal oiMoney;
    private String oiPayment;
    private String oiState;
    private String oiProvince;
    private String oiCity;
    private String oiPhone;
    private String oiName;
    private String oiCounty;
    private String oiDetail;
    private String oiLogistics;
    @Id
    @Column(name = "oi_id")
    public long getOiId() {
        return oiId;
    }

    public void setOiId(long oiId) {
        this.oiId = oiId;
    }

    @Basic
    @Column(name = "oi_time")
    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Timestamp getOiTime() {
        return oiTime;
    }

    public void setOiTime(Timestamp oiTime) {
        this.oiTime = oiTime;
    }

    @Basic
    @Column(name = "oi_money")
    public BigDecimal getOiMoney() {
        return oiMoney;
    }

    public void setOiMoney(BigDecimal oiMoney) {
        this.oiMoney = oiMoney;
    }

    @Basic
    @Column(name = "oi_payment")
    public String getOiPayment() {
        return oiPayment;
    }

    public void setOiPayment(String oiPayment) {
        this.oiPayment = oiPayment;
    }

    @Basic
    @Column(name = "oi_state")
    public String getOiState() {
        return oiState;
    }

    public void setOiState(String oiState) {
        this.oiState = oiState;
    }

    @Basic
    @Column(name = "oi_province")
    public String getOiProvince() {
        return oiProvince;
    }

    public void setOiProvince(String oiProvince) {
        this.oiProvince = oiProvince;
    }

    @Basic
    @Column(name = "oi_city")
    public String getOiCity() {
        return oiCity;
    }

    public void setOiCity(String oiCity) {
        this.oiCity = oiCity;
    }

    @Basic
    @Column(name = "oi_phone")
    public String getOiPhone() {
        return oiPhone;
    }

    public void setOiPhone(String oiPhone) {
        this.oiPhone = oiPhone;
    }

    @Basic
    @Column(name = "oi_name")
    public String getOiName() {
        return oiName;
    }

    public void setOiName(String oiName) {
        this.oiName = oiName;
    }

    @Basic
    @Column(name = "oi_county")
    public String getOiCounty() {
        return oiCounty;
    }

    public void setOiCounty(String oiCounty) {
        this.oiCounty = oiCounty;
    }

    @Basic
    @Column(name = "oi_detail")
    public String getOiDetail() {
        return oiDetail;
    }

    public void setOiDetail(String oiDetail) {
        this.oiDetail = oiDetail;
    }

    @Basic
    @Column(name = "oi_logistics")
    public String getOiLogistics() {
        return oiLogistics;
    }

    public void setOiLogistics(String oiLogistics) {
        this.oiLogistics = oiLogistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return oiId == orderInfo.oiId &&
                Objects.equals(oiTime, orderInfo.oiTime) &&
                Objects.equals(oiMoney, orderInfo.oiMoney) &&
                Objects.equals(oiPayment, orderInfo.oiPayment) &&
                Objects.equals(oiState, orderInfo.oiState) &&
                Objects.equals(oiProvince, orderInfo.oiProvince) &&
                Objects.equals(oiCity, orderInfo.oiCity) &&
                Objects.equals(oiPhone, orderInfo.oiPhone) &&
                Objects.equals(oiName, orderInfo.oiName) &&
                Objects.equals(oiCounty, orderInfo.oiCounty) &&
                Objects.equals(oiDetail, orderInfo.oiDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oiId, oiTime, oiMoney, oiPayment, oiState, oiProvince, oiCity, oiPhone, oiName, oiCounty, oiDetail);
    }
}
