package spring.jh.myapp.hr;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.jh.myapp.hello.controller.HelloController;
import spring.jh.myapp.hr.dao.IEmpService;

public class EmpMain {
	
	public static void main(String[] args) {
		AbstractApplicationContext context = 
		new GenericXmlApplicationContext( "application-config.xml" );
		IEmpService empService = context.getBean("empService",
				IEmpService.class);
		HelloController control = context.getBean("helloController", HelloController.class);
		control.hello("이주협");
		empService.updateEmp(empService.getEmpInfo(108));
		context.close();

	}
}