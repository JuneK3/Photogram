package com.rootlab.photogram.handler.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ValidationAdvice {

    @Around("execution(* com.rootlab.photogram.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("web 컨트롤러");
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.rootlab.photogram.controller.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("web api 컨트롤러");
        return proceedingJoinPoint.proceed();
    }
}
