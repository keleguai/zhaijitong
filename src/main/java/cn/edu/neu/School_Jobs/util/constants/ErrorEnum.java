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
    E_780("780", "购买基金的钱不可以小于10哦"),
    E_781("781", "购买基金的钱不可以是字符串哦"),
    E_782("782", "卖出的份额竟然超过了所有的份额！"),
    E_783("783","卖出基金的钱不可以是字符串哦"),
    E_784("784","支付密码错啦"),
    E_785("785","权限不足撒"),
    E_786("785","暂无该基金编号的信息"),
    E_787("787","卖出基金的份额不可以小于1哦"),
    E_788("788","文本参数错误"),
    E_789("789","您已经评价过了哦"),
    E_790("790", "不要添加自己哦"),
    E_791("791", "支付密码错误1次，错误次数超过3次将会锁定一天"),
    E_792("792", "支付密码错误2次，错误次数超过3次将会锁定一天"),
    E_793("793", "支付密码错误3次，错误次数超过3次将会锁定一天"),
    E_794("794", "支付密码错误已达到上限，请联系管理员"),
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
