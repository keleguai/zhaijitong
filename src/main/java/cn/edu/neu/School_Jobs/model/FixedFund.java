package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fixed_fund")
public class FixedFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "week_day")
    private Integer weekDay;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "confirm_time")
    private Date confirmTime;

    @Column(name = "loop_time")
    private Integer loopTime;

    private Byte cancel;

    private Float money;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return week_day
     */
    public Integer getWeekDay() {
        return weekDay;
    }

    /**
     * @param weekDay
     */
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    /**
     * @return send_time
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return confirm_time
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * @param confirmTime
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * @return loop_time
     */
    public Integer getLoopTime() {
        return loopTime;
    }

    /**
     * @param loopTime
     */
    public void setLoopTime(Integer loopTime) {
        this.loopTime = loopTime;
    }

    /**
     * @return cancel
     */
    public Byte getCancel() {
        return cancel;
    }

    /**
     * @param cancel
     */
    public void setCancel(Byte cancel) {
        this.cancel = cancel;
    }

    /**
     * @return money
     */
    public Float getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Float money) {
        this.money = money;
    }
}