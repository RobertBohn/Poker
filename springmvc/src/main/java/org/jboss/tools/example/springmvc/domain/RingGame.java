package org.jboss.tools.example.springmvc.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.jboss.tools.example.springmvc.valid.Positive;

/***********

CREATE TABLE `ringgame` (
  `buyin` bigint(20) NOT NULL,
  `cashout` bigint(20) NOT NULL,
  `end` datetime NOT NULL,
  `game` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site` bigint(20) NOT NULL,
  `start` datetime NOT NULL,
  `tables` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ringgame` (`id`),
  UNIQUE KEY `idx_ringgame_start` (`start`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

***********/

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "start"))
public class RingGame implements Serializable
{
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L; 

   @Id
   @GeneratedValue
   private Long id;	   

   @NotNull   
   private Long site;

   @NotNull
   private Long game;

   @Positive
   private Long tables;
	   
   @NotNull
   private Timestamp start;

   @NotNull
   private Timestamp end;

   @Positive
   private Long buyin;
	   
   @NotNull
   private Long cashout;

   public Long getId() {
	   return id;
   }

   public void setId(Long id) {
	   this.id = id;
   }

   public Long getSite() {
	   return site;
   }

   public void setSite(Long site) {
	   this.site = site;
   }

   public Long getGame() {
	   return game;
   }

   public void setGame(Long game) {
	   this.game = game;
   }

   public Long getTables() {
	   return tables;
   }

   public void setTables(Long tables) {
	   this.tables = tables;
   }

   public Timestamp getStart() {
	   return start;
   }

   public void setStart(Timestamp start) {
	   this.start = start;
   }

   public Long getBuyin() {
	   return buyin;
   }

   public void setBuyin(Long buyin) {
	   this.buyin = buyin;
   }

   public Long getCashout() {
	   return cashout;
   }

   public void setCashout(Long cashout) {
	   this.cashout = cashout;
   }

   public Timestamp getEnd() {
	   return end;
   }

   public void setEnd(Timestamp end) {
	   this.end = end;
   }	   
	   
   /** Joined Tables **/   
   
   @OneToOne(cascade=CascadeType.ALL,targetEntity=Game.class)
   @JoinColumn(name="game",insertable=false,updatable=false) 
   private Game ringGameGame;
   public Game getRingGameGame() {
	   return ringGameGame;
   }     
 
   @OneToOne(cascade=CascadeType.ALL,targetEntity=Site.class)
   @JoinColumn(name="site",insertable=false,updatable=false) 
   private Site ringGameSite;
   public Site getRingGameSite() {
	   return ringGameSite;
   }     
   
   /** Calculated Fields **/	
	
 	public Long getNet() {
 		return cashout - buyin;
 	}	
   
 	public int getMinutes() {
 		return (int)((end.getTime()/60000) - (start.getTime()/60000));
 	} 	
	
    public String DisplayAmount(Long amount) {
 	   return this.getRingGameSite().getSiteCurrency().DisplayAmount(amount);		
    }   
 	
}
