package spring.study.myapp.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheck {
	
	@Pointcut(value="execution(* spring..*.*Name(..))")
	private void FirstPointcut() {}
	
	@Pointcut(value="execution(* spring..*.*Name(..))")
	private void LastPointcut() {}
	
	
	
	
	
	@Before("FirstPointcut()")
	public void beforeLog(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
		Signature s = joinPoint.getSignature();
		System.out.println("클래스: "+s.getDeclaringTypeName()+", 메서드: "+s.getName()+" 시작");
	}
	
	@AfterReturning("LastPointcut()")
	public void afterLog(JoinPoint joinPoint) {
		Signature s = joinPoint.getSignature();
		System.out.println("클래스: "+s.getDeclaringTypeName()+", 메서드: "+s.getName()+" 종료");
	}
	@Around(value="within(spring.study.myapp.service.*)")
	public Object nowTime(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		long startTime = System.nanoTime();
		Object result = null;
		result = joinPoint.proceed();
		long endTime = System.nanoTime();
		System.out.println("[Log]"+ methodName +
				"Processing time is "+(endTime-startTime)+"ns");
		return result;
	}
}
