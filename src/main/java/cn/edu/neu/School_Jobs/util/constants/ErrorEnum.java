package cn.edu.neu.School_Jobs.util.constants;

/**
 * @author: fzb
 * @date: 2019/05/10
 */
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_777("777", "该手机号已经注册"),
    E_778("778", "用户名或密码错误"),
    E_779("779", "用户名资料未填写"),
    E_780("780", "购买基金的钱不可以小于1哦"),
    E_781("781", "购买基金的钱不可以是字符串哦"),
    E_782("782", "卖出的份额竟然超过了所有的份额！"),
    E_783("783","卖出基金的钱不可以是字符串哦"),
    E_90003("90003", "缺少必填参数");

    private String errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
