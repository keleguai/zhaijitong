package cn.edu.neu.School_Jobs.vo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buy_order")
public class BuyFundJoinVo {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "time_buying")
    private Date timeBuying;

    @Column(name = "transaction_amount")
    private Float transactionAmount;

    @Column(name = "confirm_the_net")
    private Float confirmTheNet;

    @Column(name = "service_charge")
    private Float serviceCharge;

    @Column(name = "residual_share")
    private Float residualShare;

    @Column(name = "time_confirm")
    private Date timeConfirm;

    @Column(name = "confirm_sign")
    private Boolean confirmSign;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return time_buying
     */
    public Date getTimeBuying() {
        return timeBuying;
    }

    /**
     * @param timeBuying
     */
    public void setTimeBuying(Date timeBuying) {
        this.timeBuying = timeBuying;
    }

    /**
     * @return transaction_amount
     */
    public Float getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @param transactionAmount
     */
    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @return confirm_the_net
     */
    public Float getConfirmTheNet() {
        return confirmTheNet;
    }

    /**
     * @param confirmTheNet
     */
    public void setConfirmTheNet(Float confirmTheNet) {
        this.confirmTheNet = confirmTheNet;
    }

    /**
     * @return service_charge
     */
    public Float getServiceCharge() {
        return serviceCharge;
    }

    /**
     * @param serviceCharge
     */
    public void setServiceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    /**
     * @return residual_share
     */
    public Float getResidualShare() {
        return residualShare;
    }

    /**
     * @param residualShare
     */
    public void setResidualShare(Float residualShare) {
        this.residualShare = residualShare;
    }

    /**
     * @return time_confirm
     */
    public Date getTimeConfirm() {
        return timeConfirm;
    }

    /**
     * @param timeConfirm
     */
    public void setTimeConfirm(Date timeConfirm) {
        this.timeConfirm = timeConfirm;
    }

    /**
     * @return confirm_sign
     */
    public Boolean getConfirmSign() {
        return confirmSign;
    }

    /**
     * @param confirmSign
     */
    public void setConfirmSign(Boolean confirmSign) {
        this.confirmSign = confirmSign;
    }

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
