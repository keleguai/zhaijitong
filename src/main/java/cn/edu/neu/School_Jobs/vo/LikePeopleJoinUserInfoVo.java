package cn.edu.neu.School_Jobs.vo;

import cn.edu.neu.School_Jobs.model.UserInfo;

import javax.persistence.Column;

/**
 * Created by fzb on 2019/6/19
 */
public class LikePeopleJoinUserInfoVo extends UserInfo {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "like_id")
    private Integer likeId;

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
     * @return like_id
     */
    public Integer getLikeId() {
        return likeId;
    }

    /**
     * @param likeId
     */
    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
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
