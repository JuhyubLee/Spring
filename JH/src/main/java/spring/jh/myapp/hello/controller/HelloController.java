package spring.jh.myapp.hello.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import spring.jh.myapp.hello.service.*;

@Controller
public class HelloController {
	
	@Autowired
	@Qualifier("helloService")
	IHelloService helloService = new HelloService();
	
	public void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}
	

	
	public void hello(String name) {
		System.out.println("HelloController : " + helloService.sayHello(name));
	}
}
