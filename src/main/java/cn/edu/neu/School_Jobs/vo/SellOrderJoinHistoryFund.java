package cn.edu.neu.School_Jobs.vo;

import cn.edu.neu.School_Jobs.model.SellOrder;

import javax.persistence.Column;
import javax.persistence.Id;

public class SellOrderJoinHistoryFund extends SellOrder {
    @Id
    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "history_price")
    private String historyPrice;

    private String date;

    @Column(name = "history_rate")
    private String historyRate;

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
     * @return history_price
     */
    public String getHistoryPrice() {
        return historyPrice;
    }

    /**
     * @param historyPrice
     */
    public void setHistoryPrice(String historyPrice) {
        this.historyPrice = historyPrice;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return history_rate
     */
    public String getHistoryRate() {
        return historyRate;
    }

    /**
     * @param historyRate
     */
    public void setHistoryRate(String historyRate) {
        this.historyRate = historyRate;
    }
}
