package com.test.chat.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect注解表示:申明这是一个切面类
@Aspect
@Component
public class ControllerAspect {
    private static final Logger Log = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.test.chat.controller..*.*(..))")
    public void pointcut() {

    }

    @Around(value = "com.test.chat.aspect.ControllerAspect.pointcut()")
    public Object process(ProceedingJoinPoint jp) throws Throwable {
        Object rvt = null;
        long startTime = System.currentTimeMillis();
        String methodInfo = jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName();
        String paramInfo = JSON.toJSONString(jp.getArgs());
        Log.info("调用方法:{},  参数:{}}", methodInfo, paramInfo);
        rvt = jp.proceed();
        long endTime = System.currentTimeMillis();
        String returnInfo = JSON.toJSONString(rvt);
        long time = endTime - startTime;
        Log.info("{调用方法:{},  返回值:{},  耗时:{}毫秒}", methodInfo, returnInfo, time);
        return rvt;
    }
} 