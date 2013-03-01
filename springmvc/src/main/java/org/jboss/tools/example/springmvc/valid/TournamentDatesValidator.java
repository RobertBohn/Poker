package org.jboss.tools.example.springmvc.valid;

import org.jboss.tools.example.springmvc.domain.Tournament;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class TournamentDatesValidator extends BaseBeanValidator {	
 	
	@Override
	public boolean supports(Class<?> clazz) {	
		return clazz.isAssignableFrom(Tournament.class);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		super.validate(target, errors);				
		Tournament tournament = (Tournament) target;			
		
		if (tournament.getMinutes() <= 0) {
            errors.rejectValue("end", null, "end must be after start");						
		}		
	}

}
