package com.example.station_level_management_back;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("*")//指的是前端哪些域名被允许跨域
                .allowedOriginPatterns("*")
                //需要带cookie等凭证时，设置为true，就会把cookie的相关信息带上
                .allowCredentials(true)
                //指的是允许哪些方法
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                //cookie的失效时间，单位为秒（s），若设置为-1，则关闭浏览器就失效
                .maxAge(3600);

    }
    /**
     * 重写addInterceptors()实现拦截器
     * 配置：要拦截的路径以及不拦截的路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册Interceptor拦截器(Interceptor这个类是我们自己写的拦截器类)
        InterceptorRegistration registration = registry.addInterceptor(new TokenInterceptor());
        //addPathPatterns()方法添加需要拦截的路径
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //excludePathPatterns()方法添加不拦截的路径
        //添加不拦截路径
        registration.excludePathPatterns(
                //登录
                "/user-entity/login",
                "/user-entity//addUser",
                "/user-entity/test",
                "/ticket-entity/test"
        );
    }
}
