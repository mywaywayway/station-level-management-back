package com.example.station_level_management_back;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        System.out.println(request);
        String token = request.getHeader("Authorization");
        if (token==null) {
            wrapper.sendRedirect("fail/token/null");
            System.out.println("null token");
            return false;
        }
        if (!JwtUtil.verity(token)) {
            wrapper.sendRedirect("fail/token/invalid");
            System.out.println("invalid token");
            return false;
        }
        System.out.println("valid token");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("拦截器处理结束...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("请求结束...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}