package org.jboss.tools.example.springmvc.mvc;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.Valid;

import org.jboss.tools.example.springmvc.domain.RingGame;
import org.jboss.tools.example.springmvc.repo.PokerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RingGameController 
{		
    @Autowired
    private PokerDao pokerDao;
    
    // View Ring Games
    @RequestMapping(value="/ringgame", method=RequestMethod.GET)
    public String displayRingGames(Model model)
    {     	    	    	
        model.addAttribute("ringgames", pokerDao.RingGames());
        return "ringgames";
    }
        
    // Add New Ring Game
    @RequestMapping(value="/ringgameadd", method=RequestMethod.GET)
    public String displayNewRingGame(Model model)
    {  	        	
  	   	Date now = new Date();   	   	
  	   	RingGame ringGame = new RingGame();  	  
    	ringGame.setSite(new Long(1));
    	ringGame.setGame(new Long(3));    	  	    	
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
