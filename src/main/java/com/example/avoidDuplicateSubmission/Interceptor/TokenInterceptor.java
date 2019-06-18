package com.example.avoidDuplicateSubmission.Interceptor;

import com.example.avoidDuplicateSubmission.Interceptor.handler.PreventingRefreshTokenHandler;
import com.example.avoidDuplicateSubmission.Interceptor.handler.TokenHandler;
import com.example.avoidDuplicateSubmission.annotation.StrategyAnnotation;
import com.example.avoidDuplicateSubmission.annotation.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private DefaultListableBeanFactory beanFactory;

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getMethod();
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            return getTokenHandler(method).preHandler(request, response, method);
        }
        return super.preHandle(request, response, handler);
    }

    // 业务处理成功 删除token
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            TokenHandler tokenHandler = getTokenHandler(handlerMethod);
            tokenHandler.postHandler(request, response, handlerMethod);
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    private TokenHandler getTokenHandler(HandlerMethod method) {
        Token token = method.getMethod().getAnnotation(Token.class);
        if (token == null) {
            return beanFactory.getBean(PreventingRefreshTokenHandler.class);
        }
        Map<String, Object> beanMap = beanFactory.getBeansWithAnnotation(StrategyAnnotation.class);
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            StrategyAnnotation strategyAnnotation = beanFactory.findAnnotationOnBean(entry.getKey(), StrategyAnnotation.class);
            assert strategyAnnotation != null;
            if (strategyAnnotation.type() == token.type()) {
                return (TokenHandler) entry.getValue();
            }
        }
        throw new IllegalArgumentException();
    }
}
