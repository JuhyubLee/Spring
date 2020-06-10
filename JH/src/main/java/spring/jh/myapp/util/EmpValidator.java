package spring.jh.myapp.util;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.jh.myapp.hr.model.EmpVO;

@Component
public class EmpValidator implements Validator, InitializingBean{
private javax.validation.Validator validator;
@Override
public void afterPropertiesSet() throws Exception{
	ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
	validator = vFactory.usingContext().getValidator();
}
@Override
public boolean supports(Class<?> clazz) {
	return EmpVO.class.isAssignableFrom(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	Set<ConstraintViolation<Object>> violations = validator.validate(target);
	for(ConstraintViolation<Object> violation:violations) {
		String propertyPath = violation.getPropertyPath().toString();
		String message = violation.getMessage();
		errors.rejectValue(propertyPath, message, message);
	}
	EmpVO form = (EmpVO)target;
	if(form.getEmployeeId() <= 206) {
		errors.rejectValue("employeeId", "emp.employeeId", "사원번호는 207번 이상");
	}
	if(form.getFirstName() == null) {
		errors.rejectValue("firstName", "emp.firstName", "이름 입력");
	}
	ValidationUtils.rejectIfEmpty(errors, "lastName", "emp.lastName.empty", "성을 입력하세요.");
	
	if(!(form.getEmail().contains("@"))) {
		errors.rejectValue("email", "emp.email", "이메일 양식에 맞춰주세요.");
	}
	if(!(form.getPhoneNumber().contains("-"))) {
		errors.rejectValue("phoneNumber", "emp.phoneNumber","핸드폰 전화번호 양식에 맞춰주세요.");
	}
	if(form.getJobId() == null) {
		errors.rejectValue("jobId", "emp.jobId", "must be in the past");
	}
	int length = (int)(Math.log10(form.getSalary()+1));
	if(length > 6) {
		errors.rejectValue("salary", "emp.salary", "잘못된 급여액(6자리 이상 불가)");
	}
	if(form.getCommissionPct() > 1) {
		errors.rejectValue("commissionPct", "emp.commissionPct", "보너스율은 1 미만입니다.");
	}

}
}
