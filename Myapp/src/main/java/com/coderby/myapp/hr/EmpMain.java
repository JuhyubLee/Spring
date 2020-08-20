package com.coderby.myapp.hr;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.coderby.myapp.hr.dao.IEmpService;
import com.coderby.myapp.hr.model.JobVO;
import com.coderby.myapp.util.PropertyEnc;

public class EmpMain {

	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("application-config.xml");
		IEmpService empService = context.getBean("empService", IEmpService.class);
		System.out.println(empService.getEmpCount());
		System.out.println(empService.getEmpCount(30));
		System.out.println(empService.getEmpList());
		System.out.println(empService.getAllManagerId());
		empService.updateEmp(empService.getEmpInfo(108));
		System.out.println(new JobVO());
		context.close();
	}

}