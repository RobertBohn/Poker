package org.jboss.tools.example.springmvc.mvc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.jboss.tools.example.springmvc.domain.Tournament;
import org.jboss.tools.example.springmvc.repo.PokerDao;
import org.jboss.tools.example.springmvc.valid.TournamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TournamentController 
{	
    @Autowired
    private PokerDao pokerDao;      
    
    @Autowired
    TournamentValidator tournamentDatesValidator;    
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {    	
    	binder.setValidator(tournamentDatesValidator);
    }     
        
    // View Tournaments
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayTournaments(Model model)
    {   
    	List<Tournament> tournaments = pokerDao.Tournaments();
    	
    	int played = 0;
    	int won = 0;
    	for (Tournament tournament : tournaments) {
    		played++;
    		if (tournament.getPlace() == 1) won++;
    	}
    	
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("played", played);
        model.addAttribute("won", won);                             
        
        return "tournaments";
    }      
   
    // Add New Tournament
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String displayNewTournament(Model model)
    {  	        	
  	   	Date now = new Date();   	   	
  	   	Tournament tournament = new Tournament();
    	tournament.setType(new Long(0));
    	tournament.setSite(new Long(1));
    	tournament.setGame(new Long(1));
    	tournament.setEntry(new Long(45));
    	tournament.setFee(new Long(5));  	    	
 		tournament.setStart(new Timestamp(now.getTime()));
		tournament.setEnd(new Timestamp(now.getTime()));   	    	
    
        model.addAttribute("newTournament", tournament);
        return "add";
    }
   
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addNewTournament(@Valid @ModelAttribute("newTournament") Tournament newTournament, BindingResult result, Model model)
    {       	    	
        if (!result.hasErrors()) 
        {
        	pokerDao.persist(newTournament);   	        	        	
            return "redirect:/";
        }
        else 
        {
            return "add";
        }
    }

}
