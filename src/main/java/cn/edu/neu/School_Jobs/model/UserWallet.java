package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "user_wallet")
public class UserWallet {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String share;

    @Column(name = "fund_id")
    private String fundId;

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
     * @return share
     */
    public String getShare() {
        return share;
    }

    /**
     * @param share
     */
    public void setShare(String share) {
        this.share = share;
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
}
