package spring.jh.myapp.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.jh.myapp.member.model.MemberVO;



public class MemValidator implements Validator, InitializingBean{
	private javax.validation.Validator memvalidator;
	
	@Override
	public void afterPropertiesSet() throws Exception{
		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		memvalidator = vFactory.usingContext().getValidator();
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> violations = memvalidator.validate(target);
		for(ConstraintViolation<Object> violation:violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			errors.rejectValue(propertyPath, message, message);
		}
		MemberVO form = (MemberVO)target;
		if(form.getUserId() == null) {
			errors.rejectValue("userId", "mem.userId", "아이디를 입력하세요.");
		}
		if(form.getName() == null) {
			errors.rejectValue("name", "mem.name", "이름을 입력하세요.");
		}
		if(form.getPassword() == null) {
			errors.rejectValue("password", "mem.password","비밀번호를 입력하세요.");
		}
		
		if(!(form.getEmail().contains("@"))) {
			errors.rejectValue("email", "mem.email", "이메일 양식에 맞춰주세요.");
		}
		if(form.getAddress() == null) {
			errors.rejectValue("address", "mem.address", "주소를 입력하세요.");
		}

	}
}
