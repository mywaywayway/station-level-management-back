package com.example.station_level_management_back;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "token");

        // tribute alexanoid to https://stackoverflow.com/a/36821971/19534459
        if ("OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.setStatus(HttpServletResponse.SC_OK);
            // do not continue filter chain, return immediately
            // otherwise error handling would take place, rejecting the request
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
