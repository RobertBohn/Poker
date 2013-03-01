package org.jboss.tools.example.springmvc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/***********

CREATE TABLE `game` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `game` WRITE;
INSERT INTO `game` VALUES (1,'NL Hold\'em 400 Guarantee',NULL),(2,'NLHE 9max 1/2',NULL);
UNLOCK TABLES;

***********/

@Entity
@Table
public class Game implements Serializable
{	
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;
	   	   
	@Id
	@GeneratedValue
	private Long id;	   
	   
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
	   
}
