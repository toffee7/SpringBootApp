package com.toffee.home.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.toffee.home.exception.EmployeeServiceException;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.citi.eqtds.controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		LOG.info("Employee service called for SOEID: {}", joinPoint.getArgs());
	}
	
	@After("execution(* com.citi.eqtds.controller.*.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		LOG.info("Employee Service executed for SOEID: {}", joinPoint.getArgs());
	}
	
	@AfterReturning(pointcut="execution(* com.citi.eqtds.controller.*.*(..))", returning="employeeName")
	public void getNameReturningAdvice(String employeeName){
		LOG.info("Employee Service returned: {}",employeeName);
	}
	
	@AfterThrowing(pointcut = "execution(* com.citi.eqtds.controller.*.*(..))", throwing = "e")
	public void afterThrowingAdvice(JoinPoint joinPoint, EmployeeServiceException e) {
		LOG.error("Employee Service encountered error for SOEID: {}:{}", joinPoint.getArgs(), e.getMessage());
	}
	
	@Around("execution(* com.citi.eqtds.controller.*.*(..))")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		LOG.info("Time taken to complete "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+" = "+ (endTime - startTime)+"ms");
		return result;
	}
}
