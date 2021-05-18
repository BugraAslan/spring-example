package com.learning.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    @Before("execution(* com.learning.Service.UserService.update(..))")
    public void beforeUpdate(JoinPoint joinPoint) {
        System.out.println("Before update, request class: " + joinPoint.getArgs()[0].getClass().getName());
        System.out.println(joinPoint.getArgs()[0].getClass().getName());
    }

    @After("execution(* com.learning.Service.UserService.update(..))")
    public void afterUpdate(JoinPoint joinPoint) {
        System.out.println("After update");
    }

    //TODO ("execution(* com.learning.Service.UserService.*(..))") >>>>>>> any method
    //TODO ("execution(* com.learning.Service.*.*(..))") >>>>>>>any service any method
}
