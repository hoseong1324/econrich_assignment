package com.econrich.assignment.common.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SystemTimeService {

    @Around("execution(* com.econrich.assignment..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("START: {}", joinPoint);
        try{
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("END: {} ||  TIME: {}ms", joinPoint, endTime - startTime);
        }
    }
}
