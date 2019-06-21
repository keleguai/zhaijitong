package cn.edu.neu.School_Jobs.intercepter;

import cn.edu.neu.School_Jobs.model.Recommend;
import cn.edu.neu.School_Jobs.service.RecommendService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.TokenState;
import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class IntercepterInfo extends HandlerInterceptorAdapter {
    @Autowired
    private RecommendService recommendService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Recommend recommend = new Recommend();
        recommend.setUrl(request.getRequestURL().toString());
        recommend.setInsertTime(new Date());
        recommend.setToken(request.getParameter("token"));
        if (!request.getParameter("token").equals("null")) {
            String userId = JWSObject.parse(request.getParameter("token")).getPayload().toJSONObject().getAsString("userId");
            recommend.setUserId(Integer.parseInt(userId));
            recommend.setToken(request.getParameter("token"));
        }
        recommendService.save(recommend);
        return true;
    }
}
