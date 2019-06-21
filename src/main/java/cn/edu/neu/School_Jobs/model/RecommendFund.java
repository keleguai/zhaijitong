package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "recommend_fund")
public class RecommendFund {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fund_id")
    private String fundId;

    private Float grade;

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
     * @return grade
     */
    public Float getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(Float grade) {
        this.grade = grade;
    }
}
