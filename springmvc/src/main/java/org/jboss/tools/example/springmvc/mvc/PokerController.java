package org.jboss.tools.example.springmvc.mvc;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.Valid;

import org.jboss.tools.example.springmvc.domain.RingGame;
import org.jboss.tools.example.springmvc.domain.Tournament;
import org.jboss.tools.example.springmvc.repo.PokerDao;
import org.jboss.tools.example.springmvc.valid.TournamentDatesValidator;
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
public class PokerController 
{
	
    @Autowired
    private PokerDao pokerDao;  
    
    
    @Autowired
    TournamentDatesValidator tournamentDatesValidator;    
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {    	
    	binder.setValidator(tournamentDatesValidator);
    }     
        
    // View Tournaments
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayTournaments(Model model)
    {   	    	    	
        model.addAttribute("tournaments", pokerDao.Tournaments());
        return "tournaments";
    }    
    
    // View Ring Games
    @RequestMapping(value="/ringgame", method=RequestMethod.GET)
    public String displayRingGames(Model model)
    {     	    	    	
        model.addAttribute("ringgames", pokerDao.RingGames());
        return "ringgames";
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
        
    // Add New Ring Game
    @RequestMapping(value="/ringgameadd", method=RequestMethod.GET)
    public String displayNewRingGame(Model model)
    {  	        	
  	   	Date now = new Date();   	   	
  	   	RingGame ringGame = new RingGame();  	  
    	ringGame.setSite(new Long(1));
    	ringGame.setGame(new Long(2));    	  	    	
    	ringGame.setTables(new Long(1));    	  	    	
    	ringGame.setStart(new Timestamp(now.getTime()));
    	ringGame.setEnd(new Timestamp(now.getTime()));   	    	
    	
        model.addAttribute("newRingGame", ringGame);
        return "ringgameadd";
    }
    
    @RequestMapping(value="/ringgameadd", method=RequestMethod.POST)
    public String addNewRingGame(@Valid @ModelAttribute("newRingGame") RingGame newRingGame, BindingResult result, Model model)
    {       	    	
        if (!result.hasErrors()) 
        {
        	pokerDao.persist(newRingGame);   	        	        	
            return "redirect:/ringgame";
        }
        else 
        {           	
            return "ringgameadd";
        }
    }    
    
}
