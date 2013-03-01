package org.jboss.tools.example.springmvc.valid;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;

/**
 * Must be thread safe as the validator is created at load time.
 * <p/>
 * This implementation allows use of both JSR-303 annotation/validations on the entity,
 * while still providing 'validate()' for more sophisticated interfield or other business rule validations.
 * By extending this superclass with your validator, you can get both the JSR-303 and the spring Validator "validate()"
 * automatically invoked with the @Valid annotation
 * <p/>
 * One alternative to this approach would be to code custom JSR-303 validations instead of a spring-specific
 * custom validator.  That approach would probably be preferable if the target validations were more generic;
 * however the custom validations/business rule checks performed for an invoice are probably too specific to 
 * be worth generalizing into new JSR-303's, but keep that option in mind if you find yourself generating 
 * similar validation logic in separate implementations of XXXValidator classes.
 * <p/>
 * @author rleuthner
 *
 */

public abstract class BaseBeanValidator implements org.springframework.validation.Validator, InitializingBean {

	private javax.validation.Validator validator;

	@Override
	public void validate( Object target, Errors errors ) {
		Set<ConstraintViolation<Object>>constraintViolations = validator.validate( target );
        for ( ConstraintViolation<Object>constraintViolation : constraintViolations ) {        	
            errors.rejectValue( constraintViolation.getPropertyPath().toString(), null, constraintViolation.getMessage() );
        }
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {		
		validator = javax.validation.Validation.buildDefaultValidatorFactory().usingContext().getValidator();
	}
}
