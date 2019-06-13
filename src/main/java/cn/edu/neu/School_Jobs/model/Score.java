package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stock_id")
    private String stockId;

    private Integer grade;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "manager_id")
    private Integer managerId;

    private String comment;

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
     * @return stock_id
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * @param stockId
     */
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    /**
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
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
     * @return insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
     * @return manager_id
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * @param managerId
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}