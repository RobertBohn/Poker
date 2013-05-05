package org.jboss.tools.example.springmvc.test;

import junit.framework.Assert;

import org.jboss.tools.example.springmvc.domain.Game;
import org.jboss.tools.example.springmvc.repo.PokerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml","classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class PokerTest 
{	
    @Autowired
    private PokerDao pokerDao;
		 
	@Test
	public void testGetGame() 
	{
        Game game = pokerDao.getGame(1l);
        Assert.assertEquals("John Smith", game.getName());        
	}
}
