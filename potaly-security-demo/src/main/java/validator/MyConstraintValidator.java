package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.potaly.service.HelloService;
/**
 * @author potaly
 * @date 2019年8月13日
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	@Autowired
	HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.Greeting( String.valueOf(value));
		System.out.println("当前值是"+value);		
		return false;
	}

}
