package cn.situ.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Products implements Serializable {
    private int pId;
    private String pTitle;
    private BigDecimal pPrice;
    private Double pDiscount;
    private String pDescription;
    private String pImages;
    private Integer pStock;
    private LevelSort lsId;
    private Integer pState;
    private Integer pQuantity;

    @Id
    @Column(name = "p_id")
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "p_title")
    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    @Basic
    @Column(name = "p_price")
    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    @Basic
    @Column(name = "p_discount")
    public Double getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(Double pDiscount) {
        this.pDiscount = pDiscount;
    }

    @Basic
    @Column(name = "p_description")
    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    @Basic
    @Column(name = "p_images")
    public String getpImages() {
        return pImages;
    }

    public void setpImages(String pImages) {
        this.pImages = pImages;
    }

    @Basic
    @Column(name = "p_stock")
    public Integer getpStock() {
        return pStock;
    }

    public void setpStock(Integer pStock) {
        this.pStock = pStock;
    }

    @ManyToOne(targetEntity = LevelSort.class)
    @JoinColumn(name = "ls_id", referencedColumnName = "ls_id")
    public LevelSort getLsId() {
        return lsId;
    }

    public void setLsId(LevelSort lsId) {
        this.lsId = lsId;
    }

    @Basic
    @Column(name = "p_state")
    public Integer getpState() {
        return pState;
    }

    public void setpState(Integer pState) {
        this.pState = pState;
    }

    @Basic
    @Column(name = "p_quantity")
    public Integer getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(Integer pQuantity) {
        this.pQuantity = pQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return pId == products.pId &&
                Objects.equals(pTitle, products.pTitle) &&
                Objects.equals(pPrice, products.pPrice) &&
                Objects.equals(pDiscount, products.pDiscount) &&
                Objects.equals(pDescription, products.pDescription) &&
                Objects.equals(pImages, products.pImages) &&
                Objects.equals(pStock, products.pStock) &&
                Objects.equals(lsId, products.lsId) &&
                Objects.equals(pState, products.pState) &&
                Objects.equals(pQuantity, products.pQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId, pTitle, pPrice, pDiscount, pDescription, pImages, pStock, lsId, pState, pQuantity);
    }

}
