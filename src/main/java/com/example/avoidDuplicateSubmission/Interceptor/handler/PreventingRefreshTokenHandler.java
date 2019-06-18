package com.example.avoidDuplicateSubmission.Interceptor.handler;

import com.example.avoidDuplicateSubmission.annotation.StrategyAnnotation;
import com.example.avoidDuplicateSubmission.annotation.Token;
import com.example.avoidDuplicateSubmission.enums.TokenTypeEnum;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@StrategyAnnotation(type = TokenTypeEnum.PreventingRefreshCommit)
public class PreventingRefreshTokenHandler implements TokenHandler {

    /**
     * @param request
     * @param response
     * @param method
     * @return
     */
    @Override
    public boolean preHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod method) {
        HttpSession session = request.getSession();
        // 如果存在，表示请求正在处理。
        if (session.getAttribute(request.getRequestURI()) != null) {
            try {
                response.getOutputStream().write("重复提交".getBytes("GBK"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        session.setAttribute(request.getRequestURI(), 1);
        return true;
    }

    @Override
    public void postHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod method) {
        HttpSession session = request.getSession();
        if (session.getAttribute(request.getRequestURI()) != null) {
            session.removeAttribute(request.getRequestURI());
        }
    }



}
