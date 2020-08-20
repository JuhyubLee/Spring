package com.coderby.myapp.hello.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService implements IHelloService{

	@Override
	public String sayHello(String name){
		System.out.println("HelloService.sayHello() 메서드 실행");
		String message = "Hello~~~" + name;
		return message;
	}

	@Override
	public String sayGoodbye(String name) {
		String message = "Goodbye~~~" + name;
		return message;
	}

}
