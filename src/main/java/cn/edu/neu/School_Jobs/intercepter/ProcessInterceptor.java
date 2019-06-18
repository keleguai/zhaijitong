package cn.edu.neu.School_Jobs.intercepter;

import cn.edu.neu.School_Jobs.model.Recommend;
import cn.edu.neu.School_Jobs.service.RecommendService;
import cn.edu.neu.School_Jobs.util.Jwt;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * created by fzb on 2018/1/2.
 */
//解决axios跨域请求 http://blog.csdn.net/brave_coder/article/details/77103899
public class ProcessInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RecommendService recommendService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");

        httpServletResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        httpServletResponse.setHeader("X-Powered-By", "Jetty");


        String method = httpServletRequest.getMethod();
        Recommend recommend = new Recommend();
        recommend.setUrl(httpServletRequest.getRequestURL().toString());
        recommend.setInsertTime(new Date());
        recommend.setToken(httpServletRequest.getParameter("token"));
        if (!httpServletRequest.getParameter("token").equals("null")) {
            String userId = JWSObject.parse(httpServletRequest.getParameter("token")).getPayload().toJSONObject().getAsString("userId");
            recommend.setUserId(Integer.parseInt(userId));
            recommend.setToken(httpServletRequest.getParameter("token"));
        } else {
            recommend.setUserId(null);
            recommend.setToken(null);
        }
        recommend.setId(null);
//        System.out.println(recommend.getInsertTime()+recommend.getToken()+"777"+recommend.getId()+recommend.getUserId());
//        recommendService.save(recommend);
        System.out.println(httpServletRequest.getRequestURL() + "2333" + httpServletRequest.getPathInfo() + "1111" + httpServletRequest.getParameterMap() + "4444" + httpServletRequest.getQueryString());
        if (method.equals("OPTIONS")) {
            httpServletResponse.setStatus(200);
            return false;
        }

        return true;
    }


}
