package cn.edu.neu.School_Jobs.util;

import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.constants.Constants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author running@vip.163.com
 */
public class Jwt {


    /**
     * 秘钥
     */
    private static final byte[] SECRET = "3d990d2276917dfac04467df11fff26d".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     * "alg":"HS256",
     * "type":"JWT"
     * }
     */
    private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    @Autowired
    public static UserService userService;

    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * @param /Map集合，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串, 若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString = null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }


    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     *
     * @param token
     * @return Map<String, Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);
            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenState.VALID.toString());
                // 若payload包含expiryTime字段，则校验是否过期
                if (jsonOBj.containsKey("expiryTime")) {
                    long extTime = Long.valueOf(jsonOBj.get("expiryTime").toString());
                    long curTime = new Date().getTime();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        if (jsonOBj.containsKey("subAccountId")) {
                            resultMap.put("state", TokenState.EXPIRED_SUBACCOUNT.toString());
                        } else {
                            resultMap.put("state", TokenState.EXPIRED.toString());
                        }
                    }
                }
                resultMap.put("data", jsonOBj);
            } else {
                JSONObject jsonOBj = payload.toJSONObject();
                // 校验失败
                if (jsonOBj.containsKey("subAccountId")) {
                    resultMap.put("state", TokenState.INVALID_SUBACCOUNT.toString());
                } else {
                    resultMap.put("state", TokenState.INVALID.toString());
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

    public static String updateToken(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
        Date date = new Date();
        System.out.println(date.getTime());
        if (payload != null) {
            payload.put("startTime", date.getTime());//生成时间
            payload.put("expiryTime", date.getTime() + Constants.EXPIRY_TIME);
            return createToken(payload);
        }
        return null;
    }

    public static Integer getUserId(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
//		return (Integer) payload.get("userId");
//        if (payload.containsKey("userId")){
        Number num = (Number) payload.get("userId");
        return num.intValue();
//        }else
//            throw new Exception("not user");
    }

    public static Boolean isStudent(HttpServletRequest request) {
        int type = getUserType(request);
        if (type == 1) {
            return true;
        }
        return false;

    }

    public static Boolean isSchool(HttpServletRequest request) {
        int type = getUserType(request);
        if (type == 2) {
            return true;
        }
        return false;
    }

    public static Boolean isCompany(HttpServletRequest request) {
        int type = getUserType(request);
        if (type == 3) {
            return true;
        }
        return false;
    }

    public static Integer getUserType(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
        Number num = (Number) payload.get("type");
        return num.intValue();
    }


    public static Integer getSubAccountId(HttpServletRequest request) throws Exception {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
//		return (Integer) payload.get("userId");
        if (payload.containsKey("subAccountId")) {
            Number num = (Number) payload.get("subAccountId");
            return num.intValue();
        } else
            throw new Exception("not subAccount");
    }


}


