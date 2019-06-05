package cn.edu.neu.School_Jobs.vo;

import cn.edu.neu.School_Jobs.model.Comment;

import javax.persistence.Column;

/**
 * Created by fzb on 2019/6/2
 */
public class CommentWithUserInfo extends Comment {
    @Column(name = "question_result")
    private String questionResult;

    private Integer age;

    private String sex;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "identity_card")
    private String identityCard;

    @Column(name = "bank_card_number")
    private String bankCardNumber;

    @Column(name = "pay_password")
    private String payPassword;

    private String name;

    private String autograph;

    @Column(name = "show_me")
    private Boolean showMe;

    @Column(name = "user_name")
    private String userName;

    private String firstText;

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * @return question_result
     */
    public String getQuestionResult() {
        return questionResult;
    }

    /**
     * @param questionResult
     */
    public void setQuestionResult(String questionResult) {
        this.questionResult = questionResult;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return photo_url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return identity_card
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * @param identityCard
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * @return bank_card_number
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }

    /**
     * @param bankCardNumber
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    /**
     * @return pay_password
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     * @param payPassword
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return autograph
     */
    public String getAutograph() {
        return autograph;
    }

    /**
     * @param autograph
     */
    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    /**
     * @return show_me
     */
    public Boolean getShowMe() {
        return showMe;
    }

    /**
     * @param showMe
     */
    public void setShowMe(Boolean showMe) {
        this.showMe = showMe;
    }
}
