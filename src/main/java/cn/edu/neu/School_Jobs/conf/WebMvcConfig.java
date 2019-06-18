package cn.edu.neu.School_Jobs.conf;

import cn.edu.neu.School_Jobs.intercepter.AuthorizationInterceptor;
import cn.edu.neu.School_Jobs.intercepter.ProcessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by fzb on 2017/12/7.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProcessInterceptor());
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/score/list/**")
                .excludePathPatterns("/reward_info/list/**")
                .excludePathPatterns("/user_wallet/**")
                .excludePathPatterns("/reward_info/get_last")
                .excludePathPatterns("/comment/fund/**")
                .excludePathPatterns("/comment/manager/**")
                .excludePathPatterns("/comment/rank/**")
                .excludePathPatterns("/manager/*")
                .excludePathPatterns("/fund_manager/**")
                .excludePathPatterns("/fund_valuation/**")
                .excludePathPatterns("/historical_fund/**")
                .excludePathPatterns("/historical_fund/***")
                .excludePathPatterns("/fund_holding/**")
                .excludePathPatterns("/stock/**")
                .excludePathPatterns("/general/**")
                .excludePathPatterns("/fund/general/**")
                .excludePathPatterns("/upload/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/activation")
                .excludePathPatterns("/user/submit-password-reset")
                .excludePathPatterns("/user/request-password-reset")
                .excludePathPatterns("/user/reset-password")
                .excludePathPatterns("/user/sub-account/login")
                .excludePathPatterns("/resume/**")
                .excludePathPatterns("/user/add")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/user/**");
    }

//    @Bean
//    public WebMvcConfigurationSupport forwardToIndex() {
//        return new WebMvcConfigurationSupport() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/back").setViewName(
//                        "forward:/back/index");
//                registry.addViewController("/").setViewName(
//                        "forward:/back/index");
//            }
//        };
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/upload/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + System.getProperty("user.dir") + "/upload/");
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
