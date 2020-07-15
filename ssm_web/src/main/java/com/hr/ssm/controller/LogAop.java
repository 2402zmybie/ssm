package com.hr.ssm.controller;

import com.hr.ssm.domain.SysLog;
import com.hr.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    //开始时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问的方法
    private Method method;

    //前置通知
    @Before("execution(* com.hr.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取开始时间  执行的类, 访问的是哪一个方法
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if(args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for(int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }

    }

    //后置通知
    @After("execution(* com.hr.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问的时长
        long time = new Date().getTime() - visitTime.getTime();
        //获取url
        String url = "";
        if(clazz != null && method !=null && clazz != LogAop.class) {
            //获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上@RequestMapping的值
                RequestMapping methodAnnotion = method.getAnnotation(RequestMapping.class);
                if(methodAnnotion != null) {
                    String[] methodValue = methodAnnotion.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }
        //获取访问的ip地址
        String ip = request.getRemoteAddr();
        //获取当前操作的用户
        //从上下文中获取当前登录的用户(Spring-Security)
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息封装
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        sysLogService.save(sysLog);

    }
}
