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

CREATE TABLE `tournament` (
  `id` bigint(20) NOT NULL,
  `end` datetime NOT NULL,
  `entry` bigint(20) NOT NULL,
  `fee` bigint(20) NOT NULL,
  `game` bigint(20) NOT NULL,
  `place` bigint(20) NOT NULL,
  `players` bigint(20) NOT NULL,
  `site` bigint(20) NOT NULL,
  `start` datetime NOT NULL,
  `type` bigint(20) NOT NULL,
  `win` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `start` (`start`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

***********/

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "start"))
public class Tournament implements Serializable
{
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /** Tournament Types **/
   public enum Type { TOURNAMENT, SITANDGO, HEADSUP };  // 0, 1, 2 
       
   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   private Long type;

   @NotNull
   private Long site;

   @NotNull
   private Long game;
   
   @NotNull
   private Timestamp start;
	
   @NotNull
   private Timestamp end;
   
   @NotNull
   private Long entry;
   
   @NotNull
   private Long fee;
   
   @Positive
   private Long players;
   
   @Positive
   private Long place;
   
   @NotNull
   private Long win;    
   
   
/** Getters and Setters **/

   public Long getId() {
	   return id;
   }

   public void setId(Long id) {
	   this.id = id;
   }

   public Long getType() {
	   return type;  	   	   
   }

   public void setType(Long type) {   	   
	   this.type = type;
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

   public Timestamp getStart() {
	   return start;
   }

   public void setStart(Timestamp start) {
	   this.start = start;
   }

   public Timestamp getEnd() {
	   return end;
   }

   public void setEnd(Timestamp end) {
	   this.end = end;
   }

   public Long getEntry() {
	   return entry;
   }

   public void setEntry(Long entry) {
	   this.entry = entry;
   }

   public Long getFee() {
	   return fee;
   }

   public void setFee(Long fee) {
	   this.fee = fee;
   }

   public Long getPlayers() {
	   return players;
   }

   public void setPlayers(Long players) {
	   this.players = players;
   }

   public Long getPlace() {
	   return place;
   }

   public void setPlace(Long place) {
	   this.place = place;
   }

   public Long getWin() {
	   return win;
   }

   public void setWin(Long win) {
	   this.win = win;
   }
     
   /** Joined Tables **/   
    
   @OneToOne(cascade=CascadeType.ALL,targetEntity=Game.class)
   @JoinColumn(name="game",insertable=false,updatable=false) 
   private Game tournamentGame;
   public Game getTournamentGame() {
	   return tournamentGame;
   }     
 
   @OneToOne(cascade=CascadeType.ALL,targetEntity=Site.class)
   @JoinColumn(name="site",insertable=false,updatable=false) 
   private Site tournamentSite;
   public Site getTournamentSite() {
	   return tournamentSite;
   }     
    
   /** Calculated Fields **/	
	
   public Long getNet() {
	   return win - entry - fee;
   }	
   
 	public int getMinutes() {
 		return (int)((end.getTime()/60000) - (start.getTime()/60000));
 	} 	
	
   public String DisplayAmount(Long amount) {
	   return this.getTournamentSite().getSiteCurrency().DisplayAmount(amount);		
   }   
   
}