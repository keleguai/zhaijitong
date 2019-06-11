package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buy_order")
public class BuyOrder {
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

    @Column(name = "cancel")
    private Boolean cancel;

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    @Column(name = "sell_out")
    private Boolean sellOut;

    private int fixOrder;

    public int getFixOrder() {
        return fixOrder;
    }

    public void setFixOrder(int fixOrder) {
        this.fixOrder = fixOrder;
    }

    public Boolean getSellOut() {
        return sellOut;
    };

    public void setSellOut(Boolean sellOut) {
        this.sellOut = sellOut;
    };

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
}
