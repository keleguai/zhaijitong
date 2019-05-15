package cn.edu.neu.School_Jobs.util;

/**
 * 枚举，定义token的三种状态
 *
 * @author running@vip.163.com
 */
public enum TokenState {
    /**
     * 过期
     */
    EXPIRED("EXPIRED"),
    EXPIRED_SUBACCOUNT("EXPIRED_SUBACCOUNT"),
    /**
     * 无效(token不合法)
     */
    INVALID("INVALID"),
    INVALID_SUBACCOUNT("INVALID_SUBACCOUNT"),
    /**
     * 有效的
     */
    VALID("VALID");

    private String state;

    private TokenState(String state) {
        this.state = state;
    }

    /**
     * 根据状态字符串获取token状态枚举对象
     *
     * @param tokenState
     * @return
     */
    public static TokenState getTokenState(String tokenState) {
        TokenState[] states = TokenState.values();
        TokenState ts = null;
        for (TokenState state : states) {
            if (state.toString().equals(tokenState)) {
                ts = state;
                break;
            }
        }
        return ts;
    }

    public String toString() {
        return this.state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}  
