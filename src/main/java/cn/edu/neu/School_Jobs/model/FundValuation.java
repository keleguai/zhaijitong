package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "fund_valuation")
public class FundValuation {
    @Id
    @Column(name = "fund_id")
    private String fundId;

    @Column(name = "evaluate_fee")
    private String evaluateFee;

    private String time;

    @Column(name = "evaluate_fee_rate")
    private String evaluateFeeRate;

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
     * @return evaluate_fee
     */
    public String getEvaluateFee() {
        return evaluateFee;
    }

    /**
     * @param evaluateFee
     */
    public void setEvaluateFee(String evaluateFee) {
        this.evaluateFee = evaluateFee;
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
     * @return evaluate_fee_rate
     */
    public String getEvaluateFeeRate() {
        return evaluateFeeRate;
    }

    /**
     * @param evaluateFeeRate
     */
    public void setEvaluateFeeRate(String evaluateFeeRate) {
        this.evaluateFeeRate = evaluateFeeRate;
    }
}
