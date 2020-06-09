package spring.jh.myapp.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.jh.myapp.hr.model.EmpVO;

@Component
public class EmpValidator implements Validator{

@Override
public boolean supports(Class<?> clazz) {
	return EmpVO.class.isAssignableFrom(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	EmpVO form = (EmpVO)target;
	if(form.getEmployeeId() <= 206) {
		errors.rejectValue("employeeId", "emp.employeeId", "사원번호는 207번 이상");
	}
	if(form.getFirstName() == null) {
		errors.rejectValue("firstName", "emp.firstName", "이름 입력");
	}
	if(form.getLastName() == null) {
		errors.rejectValue("lastName", "emp.lastName", "성 입력");
	}
	if(form.getEmail() != "@") {
		errors.rejectValue("email", "emp.email", "이메일 양식에 맞춰주세요.");
	}
}
}
