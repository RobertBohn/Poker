package org.jboss.tools.example.springmvc.repo;

import java.util.List;

import org.jboss.tools.example.springmvc.domain.Game;
import org.jboss.tools.example.springmvc.domain.RingGame;
import org.jboss.tools.example.springmvc.domain.Tournament;

public interface PokerDao {
    public List<Game> Games();
    public List<Tournament> Tournaments();
    public List<RingGame> RingGames();
    public Game getGame(Long id);
    
    public void persist(Tournament tournament);      
    public void persist(RingGame ringGame);    
}
