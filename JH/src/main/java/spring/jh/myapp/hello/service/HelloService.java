package spring.jh.myapp.hello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import spring.jh.myapp.util.HelloTime;



@Component
public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
		HelloTime.timeLog();
		System.out.println("HelloService.sayHello() 메서드 실행");
		String message = "Hello~" + name;
		return message;
	}
}

