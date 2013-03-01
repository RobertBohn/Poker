package org.jboss.tools.example.springmvc.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.example.springmvc.domain.Game;
import org.jboss.tools.example.springmvc.domain.RingGame;
import org.jboss.tools.example.springmvc.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PokerDaoImpl implements PokerDao {

	@Autowired
    private EntityManager em;

	@Override
	public List<Tournament> Tournaments() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Tournament> criteria = cb.createQuery(Tournament.class);
	    Root<Tournament> tournament = criteria.from(Tournament.class);
        criteria.select(tournament).orderBy(cb.asc(tournament.get("start")));
        return em.createQuery(criteria).getResultList();             		
	}

	@Override
	public List<RingGame> RingGames() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<RingGame> criteria = cb.createQuery(RingGame.class);
	    Root<RingGame> ringGame = criteria.from(RingGame.class);
        criteria.select(ringGame).orderBy(cb.asc(ringGame.get("start")));
        return em.createQuery(criteria).getResultList();             		
	}

	@Override
	public List<Game> Games() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Game> criteria = cb.createQuery(Game.class);
	    Root<Game> game = criteria.from(Game.class);
        criteria.select(game).orderBy(cb.asc(game.get("name")));
        return em.createQuery(criteria).getResultList();         		
	}
	
	@Override
	public Game getGame(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Game> criteria = builder.createQuery(Game.class);
        Root<Game> game = criteria.from(Game.class);
        criteria.select(game).where(builder.equal(game.get("id"), id));
        return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public void persist(Tournament tournament) {
        em.persist(tournament);       
	}

	@Override
	public void persist(RingGame ringGame) {
        em.persist(ringGame);      
	}
	
}
