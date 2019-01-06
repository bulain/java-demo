package com.bulain.aspectj.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.bulain.aspectj.service..*(..))")
	public void demoPointCut() {
		
	}

	@Before("demoPointCut()")
	public void demoBefore(JoinPoint joinPoint) {
		logger.info("@Before {}({})", joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	@After("demoPointCut()")
	public void demoAfter(JoinPoint joinPoint) {
		logger.info("@After {}({})", joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	@Around("demoPointCut()")
	public Object demoAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("@Around before {}({}) ", joinPoint.getSignature().getName(), joinPoint.getArgs());

		Object obj = joinPoint.proceed();

		logger.info("@Around after {}({}) ", joinPoint.getSignature().getName(), joinPoint.getArgs());

		return obj;
	}

	@AfterThrowing(value = "demoPointCut()", throwing = "e")
	public void demoAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.info("@AfterThrowing: {}", e.getMessage());
	}

}
