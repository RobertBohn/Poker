package org.jboss.tools.example.springmvc.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/***********

CREATE TABLE `site` (
  `id` bigint(20) NOT NULL,
  `currency` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `site` WRITE;
INSERT INTO `site` VALUES (1,1,'Seals With Clubs',NULL);
UNLOCK TABLES;

***********/

@Entity
@Table
public class Site implements Serializable
{	
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;
	   	   
	@Id
	@GeneratedValue
	private Long id;	   
	   
	@NotNull
	private Long currency;
	
	@NotNull	  
	private String name;
	   
	@Column
	private String description;

	/** Getters and Setters **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCurrency() {
		return currency;
	}

	public void setCurrency(Long currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/** Joined Tables **/   
    
	@OneToOne(cascade=CascadeType.ALL,targetEntity=Currency.class)
	@JoinColumn(name="currency",insertable=false,updatable=false) 
	private Currency siteCurrency;
	
	public Currency getSiteCurrency() {
		return siteCurrency;
	}    
		
	/** Calculated Fields **/	

	public String DisplayAmount(Long amount) {
		return this.getSiteCurrency().DisplayAmount(amount);		
	}	
	
}
