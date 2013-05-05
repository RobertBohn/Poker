package org.jboss.tools.example.springmvc.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/***********

CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `divisor` bigint(20) NOT NULL,
  `format` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `currency` WRITE;
INSERT INTO `currency` VALUES (1,'BTC','Bitcoins',1,'#'),(2,'USD','US Dollars',100,'#,##0.00');
UNLOCK TABLES;

***********/

@Entity
@Table(name="currency")
public class Currency implements Serializable
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

	@NotNull
	private Long divisor;
	
	@NotNull	  
	private String format;
	
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

	public Long getDivisor() {
		return divisor;
	}

	public void setDivisor(Long divisor) {
		this.divisor = divisor;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}	

	/** Calculated Fields **/	
	
	public String DisplayAmount(Long amount) {
		DecimalFormat decimalFormat = new DecimalFormat(format);				
		return decimalFormat.format((double)amount/(double)divisor);
	 }	
	
}
