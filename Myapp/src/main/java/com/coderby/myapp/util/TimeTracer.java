package com.coderby.myapp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTracer {

	@Around(value="within(com..IEmpService+)")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log]Before: "+ methodName +" time check start");
		long startTime = System.nanoTime();
		Object result = null;
		result = joinPoint.proceed();
		System.out.println("[Log]Finally: "+methodName+" End.");
		long endTime = System.nanoTime();
		System.out.println("[Log]After: "+ methodName +" time check end");
		System.out.println("[Log]"+ methodName +
				" Processing time is "+(endTime-startTime)+"ns");
		return result;
	}
	
}