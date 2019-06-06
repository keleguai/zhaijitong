package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sell_order")
public class SellOrder {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "sell_time")
    private Date sellTime;

    @Column(name = "sell_share")
    private Float sellShare;

    @Column(name = "sure_net")
    private Float sureNet;

    @Column(name = "service_charge")
    private Float serviceCharge;

    @Column(name = "confirm_sign")
    private int confirmSign;

    @Column(name = "cancel")
    private Boolean cancel;

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
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
     * @return sell_time
     */
    public Date getSellTime() {
        return sellTime;
    }

    /**
     * @param sellTime
     */
    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    /**
     * @return sell_share
     */
    public Float getSellShare() {
        return sellShare;
    }

    /**
     * @param sellShare
     */
    public void setSellShare(Float sellShare) {
        this.sellShare = sellShare;
    }

    /**
     * @return sure_net
     */
    public Float getSureNet() {
        return sureNet;
    }

    /**
     * @param sureNet
     */
    public void setSureNet(Float sureNet) {
        this.sureNet = sureNet;
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
     * @return confirm_sign
     */
    public int getConfirmSign() {
        return confirmSign;
    }

    /**
     * @param confirmSign
     */
    public void setConfirmSign(int confirmSign) {
        this.confirmSign = confirmSign;
    }
}
