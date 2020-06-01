package spring.jh.myapp.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.jh.myapp.hello.controller.HelloController;


public class HelloMain {
	public static void main(String[] args) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		HelloController control = con.getBean("helloController", HelloController.class);
		control.hello("이주협");
		con.close();
	}
}