package cn.edu.neu.School_Jobs.vo;

import cn.edu.neu.School_Jobs.model.Fund;

/**
 * Created by fzb on 2019/6/20
 */
public class RecommendWIthFundVo extends Fund {
    private float grade;
    private String fundName;

    @Override
    public String getFundName() {
        return fundName;
    }

    @Override
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
