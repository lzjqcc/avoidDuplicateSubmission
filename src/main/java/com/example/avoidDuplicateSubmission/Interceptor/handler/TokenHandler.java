package com.example.avoidDuplicateSubmission.Interceptor.handler;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface TokenHandler {
    boolean preHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod method);

    void postHandler(HttpServletRequest request,HttpServletResponse response, HandlerMethod method);
}
