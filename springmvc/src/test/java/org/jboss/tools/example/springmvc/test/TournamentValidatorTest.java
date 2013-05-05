package org.jboss.tools.example.springmvc.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.Assert;

import org.hibernate.validator.HibernateValidator;
import org.jboss.tools.example.springmvc.domain.Tournament;
import org.jboss.tools.example.springmvc.valid.TournamentValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class TournamentValidatorTest {

    private LocalValidatorFactoryBean localValidatorFactory;          
    
    @Before
    public void setup() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);     
        localValidatorFactory.afterPropertiesSet();
    }    
	
	@Test
	public void testValidators() throws Exception 
	{
  	   	Date now = new Date();   	   	
  	   	Tournament tournament = new Tournament();
    	tournament.setType(0l);
    	//tournament.setSite(1l);
    	tournament.setGame(1l);
    	tournament.setEntry(45l);
    	tournament.setFee(5l);  	    	
 		tournament.setStart(new Timestamp(now.getTime()));
		tournament.setEnd(new Timestamp(now.getTime()));   	    	
		tournament.setPlayers(2l);
		tournament.setPlace(1l);
		tournament.setWin(0l);
		
		
		// hibernate only test
        Set<ConstraintViolation<Tournament>> constraintViolations = localValidatorFactory.validate(tournament);              	
        System.out.println("***** " + constraintViolations.size() + " violations *****");        	
        for (ConstraintViolation<Tournament> violation : constraintViolations) {
        	System.out.println("***** " + violation.getMessage() + " *****");        	
        }              
        Assert.assertTrue("Expected hibernate validation error not found", constraintViolations.size() == 1);
		
		
        // hibernate + custom tests         
        Errors errors = new BeanPropertyBindingResult(tournament, "tournament");
      	TournamentValidator validator = new TournamentValidator();
      	validator.afterPropertiesSet();
      	validator.validate(tournament,errors);   	      	        
       	System.out.println("***** " + errors.getErrorCount() + " errors *****");
        for (ObjectError error : errors.getAllErrors()) {
        	System.out.println("***** " + error.getDefaultMessage() + " *****");        	
        }
        Assert.assertTrue("Expected custom validation error not found", errors.getErrorCount() == 2);
       	
       	      	
		
	}	
	
	
}
