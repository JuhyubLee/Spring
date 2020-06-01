package spring.study.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.study.myapp.dao.HomeRepo;

@Component
public class HomeService {

	@Autowired
	HomeRepo homeRepo;
	
	public void AdminName() {
		homeRepo.AdminName();
	}
	
	
	

}
