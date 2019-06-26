package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_day_get")
public class UserDayGet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "get_day")
    private Date getDay;

    @Column(name = "get_money")
    private Float getMoney;

    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "yestoday_net")
    private Float yestodayNet;

    @Column(name = "today_net")
    private Float todayNet;

    @Column(name = "today_share")
    private Float todayShare;

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
     * @return get_day
     */
    public Date getGetDay() {
        return getDay;
    }

    /**
     * @param getDay
     */
    public void setGetDay(Date getDay) {
        this.getDay = getDay;
    }

    /**
     * @return get_money
     */
    public Float getGetMoney() {
        return getMoney;
    }

    /**
     * @param getMoney
     */
    public void setGetMoney(Float getMoney) {
        this.getMoney = getMoney;
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
     * @return yestoday_net
     */
    public Float getYestodayNet() {
        return yestodayNet;
    }

    /**
     * @param yestodayNet
     */
    public void setYestodayNet(Float yestodayNet) {
        this.yestodayNet = yestodayNet;
    }

    /**
     * @return today_net
     */
    public Float getTodayNet() {
        return todayNet;
    }

    /**
     * @param todayNet
     */
    public void setTodayNet(Float todayNet) {
        this.todayNet = todayNet;
    }

    /**
     * @return today_share
     */
    public Float getTodayShare() {
        return todayShare;
    }

    /**
     * @param todayShare
     */
    public void setTodayShare(Float todayShare) {
        this.todayShare = todayShare;
    }
}