package com.coderby.myapp.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Pointcut(value="execution(* com..*.sayHello(..))")
	private void helloPointcut() {}
	
	@Pointcut(value="execution(* com..*.sayGoodbye(..))")
	private void goodbyePointcut() {}
	
	@Pointcut(value="execution(* com..*.hello(..))")
	private void controllerPointcut() {}
	
	@Before("helloPointcut()")
	public void beforeLog(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
		Signature s = joinPoint.getSignature();
		System.out.println("클래스 : "+s.getDeclaringTypeName()+", 메서드 : "+s.getName()+" 시작");
	}
	
	@AfterReturning("helloPointcut()")
	public void AfterLog(JoinPoint joinPoint) {
		Signature s = joinPoint.getSignature();
		System.out.println("클래스 : "+s.getDeclaringTypeName()+", 메서드 : "+s.getName()+" 종료");
	}
	
}
