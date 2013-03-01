package org.jboss.tools.example.springmvc.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveValidator implements ConstraintValidator<Positive, Long>  
{
	@Override
	public void initialize(Positive positive0) {}

	@Override
	public boolean isValid(Long object, ConstraintValidatorContext constraintContext) {
		if (object == null)
			return false;
		else
			return (object > 0);
	}
	         
}
