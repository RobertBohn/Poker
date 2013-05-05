package org.jboss.tools.example.springmvc.mvc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.tools.example.springmvc.domain.Tournament;
import org.jboss.tools.example.springmvc.repo.PokerDao;
import org.jboss.tools.example.springmvc.valid.PokerDateEditor;
import org.jboss.tools.example.springmvc.valid.TournamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TournamentController 
{	
	private static Log log = LogFactory.getLog(TournamentController.class.getSimpleName());
	
    @Autowired
    private PokerDao pokerDao;        
       
    @Resource(name="tournamentType")
    HashMap<String, String> tournamentType;    
    
    @Autowired
    TournamentValidator tournamentValidator;    
    
    @InitBinder("newTournament")
    protected void initBinder(WebDataBinder binder) 
    {   
    	final PokerDateEditor editor = new PokerDateEditor(new SimpleDateFormat("M/d/yyyy hh:mm a"), false);
    	binder.registerCustomEditor(Timestamp.class, editor);
    	binder.setValidator(tournamentValidator);
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
    	tournament.setGame(new Long(4));
    	tournament.setEntry(new Long(47));
    	tournament.setFee(new Long(3));  	    	
 		tournament.setStart(new Timestamp(now.getTime()));
		tournament.setEnd(new Timestamp(now.getTime())); 	    	    		
		
        model.addAttribute("newTournament", tournament);
        model.addAttribute("dao", pokerDao);
        model.addAttribute("type", tournamentType);
        
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
        	for (ObjectError error : result.getAllErrors()) 
        	{
        		log.info(error.toString());           		
           	}       	        	
        	
            model.addAttribute("dao", pokerDao);
            model.addAttribute("type", tournamentType);
            return "add";
        }
    }

}
