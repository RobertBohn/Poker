package org.jboss.tools.example.springmvc.valid;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.util.StringUtils;

public class PokerDateEditor extends PropertyEditorSupport 
{		
	private final DateFormat dateFormat;
	private final boolean allowEmpty;
	private final int exactDateLength;

	public PokerDateEditor(DateFormat dateFormat, boolean allowEmpty) 
	{
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public PokerDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) 
	{
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	public void setAsText(String text) throws IllegalArgumentException 
	{		
		if (this.allowEmpty && !StringUtils.hasText(text)) 
		{
			setValue(null);
		}
		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) 
		{
			throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		}
		else 
		{
			try 
			{
				setValue(new Timestamp(this.dateFormat.parse(text).getTime()));	
			}
			catch (ParseException ex) 
			{											
				IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}			
		}
	}

	public String getAsText() 
	{
		Timestamp value = (Timestamp) getValue();
		return (value != null ? this.dateFormat.format(value) : "");
	}

}
