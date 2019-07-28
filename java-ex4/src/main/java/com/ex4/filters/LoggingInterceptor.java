package com.ex4.filters;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * class LoggingInterceptor implements {@link HandlerInterceptor}
 * that intercepts all requests and displays statistics: request processing duration
 */

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.print("-------- preHandle --- ");
        System.out.print("Request URL: " + request.getRequestURL());
        System.out.println("; Start Time: " + System.currentTimeMillis());

        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.print("-------- postHandle ---: ");
        System.out.println("Request URL: " + request.getRequestURL());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
        System.out.print("-------- afterCompletion ---: ");

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.print("Request URL: " + request.getRequestURL());
        System.out.println("; End Time: " + endTime);
        System.out.println("Time Taken: " + (endTime - startTime));
    }
}