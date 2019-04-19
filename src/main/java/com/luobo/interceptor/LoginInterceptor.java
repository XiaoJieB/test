package com.luobo.interceptor;

import com.luobo.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
        throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        if(arg0.getRequestURI().indexOf("login.action")>0) {
            return true;
        }
//        System.out.println(arg0.getSession().getAttribute(Constants.USER_CONTEXT)+"<<<<<<<<");
        if(arg0.getSession().getAttribute(Constants.STUDENT_CONTEXT)!=null ||
            arg0.getSession().getAttribute(Constants.TEACHER_CONTEXT)!=null) {
            return true;
        }
        arg1.sendRedirect("/login/login.jsp");
        return false;
    }

}