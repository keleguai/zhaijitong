package cn.edu.neu.School_Jobs.model;

import java.util.Date;
import javax.persistence.*;

public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "friend_id")
    private Integer friendId;

    private Byte be;

    private Byte del;

    @Column(name = "insert_time")
    private Date insertTime;

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
     * @return friend_id
     */
    public Integer getFriendId() {
        return friendId;
    }

    /**
     * @param friendId
     */
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    /**
     * @return be
     */
    public Byte getBe() {
        return be;
    }

    /**
     * @param be
     */
    public void setBe(Byte be) {
        this.be = be;
    }

    /**
     * @return del
     */
    public Byte getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Byte del) {
        this.del = del;
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
}