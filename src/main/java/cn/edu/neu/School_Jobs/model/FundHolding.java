package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "fund_holding")
public class FundHolding {
    @Id
    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "stock_ids")
    private String stockIds;

    @Column(name = "worth_percent")
    private String worthPercent;

    /**
     * @return fund_id
     */
    public String getFundId() {
        return fundId;
    }

    /**
     * @param fundId
     */
    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    /**
     * @return stock_ids
     */
    public String getStockIds() {
        return stockIds;
    }

    /**
     * @param stockIds
     */
    public void setStockIds(String stockIds) {
        this.stockIds = stockIds;
    }

    /**
     * @return worth_percent
     */
    public String getWorthPercent() {
        return worthPercent;
    }

    /**
     * @param worthPercent
     */
    public void setWorthPercent(String worthPercent) {
        this.worthPercent = worthPercent;
    }
}
