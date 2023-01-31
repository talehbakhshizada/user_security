package com.company.usersecurity.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class GreetingControllerAspect {

    private final Map<String, ResponseEntity<String>> cache=new HashMap<>();

    @Around("execution(* com.company.usersecurity.controller.GreetingController.greeting(..))" +
            "&& args(id,..)")
    public Object getCache(ProceedingJoinPoint joinPoint, String id) throws Throwable {
        var s=cache.get(id);
        if(s == null){
            log.info("id {} not found in cache ",id);
            var proceed=(ResponseEntity<String>) joinPoint.proceed();
            cache.put(id,proceed);
            return proceed;
        }
        log.info("id {}  found in cache {} ",id,s);
        return s;
    }
}
