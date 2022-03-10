package com.cc.simba.k;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;


@Configuration
@Component
@EnableAspectJAutoProxy
public class K5_AOP02 {


    public class UserService {
        public void action() {
            System.out.println("UserService . action" + new Date());
        }
    }

    @Aspect
    @Component
    public class LoggingAspect {

        @Before("execution(public * com.cc.simba.k.K5_AOP02.UserService.*(..))")
        public void doCheck() {
            System.out.println("[Before] do check ...");
        }

        @Around("execution(public * com.cc.simba.k.K5_AOP02.UserService.*(..))")
        public Object doLog(ProceedingJoinPoint pjp) throws Throwable {
            System.out.println("[Around] start log... ");
            Object retVal = pjp.proceed();
            System.out.println("[Around] over lob. ");
            return retVal;
        }

        /**
         * @Before：这种拦截器先执行拦截代码，再执行目标代码。如果拦截器抛异常，那么目标代码就不执行了；
         *
         * @After：这种拦截器先执行目标代码，再执行拦截器代码。无论目标代码是否抛异常，拦截器代码都会执行；
         *
         * @AfterReturning：和@After不同的是，只有当目标代码正常返回时，才执行拦截器代码；
         *
         * @AfterThrowing：和@After不同的是，只有当目标代码抛出了异常时，才执行拦截器代码；
         *
         * @Around：能完全控制目标代码是否执行，并可以在执行前后、抛异常后执行任意拦截代码，可以说是包含了上面所有功能。
         */
    }


//    public class AppConfig {
//    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(K5_AOP02.class);

        UserService userService = context.getBean(UserService.class);
        userService.action();

        System.out.println(userService.getClass().getName());

        System.out.println(" -- main over --");
    }
}
