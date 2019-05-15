package cn.edu.neu.School_Jobs.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class HistoryJoinFundVo {
    @Id
    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "fund_name")
    private String fundName;

    @Column(name = "manager_ids")
    private String managerIds;

    @Column(name = "manager_names")
    private String managerNames;

    private String time;

    @Column(name = "sell_rate")
    private String sellRate;

    @Column(name = "buying_rate")
    private Float buyingRate;

    @Column(name = "hosting_fees")
    private Float hostingFees;

    private String type;

    private Integer priority;

    private Boolean disappear;

    private String photo;


    @Column(name = "history_price")
    private String historyPrice;

    private String date;

    @Column(name = "history_rate")
    private String historyRate;


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
     * @return fund_name
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * @param fundName
     */
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    /**
     * @return manager_ids
     */
    public String getManagerIds() {
        return managerIds;
    }

    /**
     * @param managerIds
     */
    public void setManagerIds(String managerIds) {
        this.managerIds = managerIds;
    }

    /**
     * @return manager_names
     */
    public String getManagerNames() {
        return managerNames;
    }

    /**
     * @param managerNames
     */
    public void setManagerNames(String managerNames) {
        this.managerNames = managerNames;
    }

    /**
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return sell_rate
     */
    public String getSellRate() {
        return sellRate;
    }

    /**
     * @param sellRate
     */
    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }

    /**
     * @return buying_rate
     */
    public Float getBuyingRate() {
        return buyingRate;
    }

    /**
     * @param buyingRate
     */
    public void setBuyingRate(Float buyingRate) {
        this.buyingRate = buyingRate;
    }

    /**
     * @return hosting_fees
     */
    public Float getHostingFees() {
        return hostingFees;
    }

    /**
     * @param hostingFees
     */
    public void setHostingFees(Float hostingFees) {
        this.hostingFees = hostingFees;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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

    /**
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
