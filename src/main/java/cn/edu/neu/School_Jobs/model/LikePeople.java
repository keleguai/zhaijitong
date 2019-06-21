package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "like_people")
public class LikePeople {
    @Id
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