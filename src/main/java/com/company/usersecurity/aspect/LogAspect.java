package com.company.usersecurity.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("execution(* com.company.usersecurity.controller.AuthController.*(..))" +
            "&& args(dto,..)")
    public void before(JoinPoint joinPoint, Object dto) throws Throwable{
        log.info("ASPECT :method start {} request - {}",joinPoint.getSignature().getName(),dto);
    }

    @AfterReturning(value = "execution(* com.company.usersecurity.controller.AuthController.*(..))" ,
            returning="response")
    public void signIN(JoinPoint joinPoint, Object response) throws Throwable{
        log.info("ASPECT :method finish {} response - {} ",joinPoint.getSignature().getName() ,response);
    }

}
