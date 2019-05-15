package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

public class Stock {
    @Id
    @Column(name = "stock_id")
    private String stockId;

    private String name;

    @Column(name = "opening_price")
    private Float openingPrice;

    @Column(name = "stock_price")
    private Float stockPrice;

    private Boolean disappear;

    /**
     * @return stock_id
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * @param stockId
     */
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return opening_price
     */
    public Float getOpeningPrice() {
        return openingPrice;
    }

    /**
     * @param openingPrice
     */
    public void setOpeningPrice(Float openingPrice) {
        this.openingPrice = openingPrice;
    }

    /**
     * @return stock_price
     */
    public Float getStockPrice() {
        return stockPrice;
    }

    /**
     * @param stockPrice
     */
    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    /**
     * @return disappear
     */
    public Boolean getDisappear() {
        return disappear;
    }

    /**
     * @param disappear
     */
    public void setDisappear(Boolean disappear) {
        this.disappear = disappear;
    }
}