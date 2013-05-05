package org.jboss.tools.example.springmvc.valid;

import org.jboss.tools.example.springmvc.domain.Tournament;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class TournamentValidator extends BaseBeanValidator 
{ 	
	@Override
	public boolean supports(Class<?> clazz) 
	{		
		return clazz.isAssignableFrom(Tournament.class);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{		
		super.validate(target, errors);				
		Tournament tournament = (Tournament) target;					
		
		// end must be after start
		if (tournament.getStart() != null && tournament.getEnd() != null) 
		{			
			if (tournament.getMinutes() <= 0) 
			{
				errors.rejectValue("end", "newTournament.badEnd");						
			}	
		}
		
		// place can not exceed players
		if (tournament.getPlace() != null && tournament.getPlayers() != null) 
		{			
			if (tournament.getPlace() > tournament.getPlayers()) 
			{
				errors.rejectValue("place", "newTournament.badPlace");
			}
		}				
	}

}
