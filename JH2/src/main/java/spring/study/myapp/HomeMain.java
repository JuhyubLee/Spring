package spring.study.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import spring.study.myapp.service.HomeService;

@Component
public class HomeMain {

	@Autowired
	HomeService homeService;
	
	public void ViewName() {
		homeService.AdminName();
	}
	
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new GenericXmlApplicationContext("Jpplication.xml");
		HomeMain homeMain = context.getBean("homeMain", HomeMain.class);
		homeMain.ViewName();
		context.close();
	}
}
