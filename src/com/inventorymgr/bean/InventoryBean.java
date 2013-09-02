package com.inventorymgr.bean;

/**
 * Bean object for Wikipedia's data about a movie quote
 * 
 * @author gene
 *
 */
public class InventoryBean {

	private String quote;
	private String character;
	private String actor;
	private String film;
	private int year;
	private int wikiRank;
	
	public InventoryBean(
		String quote, 
		String character, 
		String actor, 
		String film,
		int year, 
		int wikiRank
		) 
	{
		super();
		this.quote = quote;
		this.character = character;
		this.actor = actor;
		this.film = film;
		this.year = year;
		this.wikiRank = wikiRank;
	}

	public String getQuote() {
		return quote;
	}

	public String getCharacter() {
		return character;
	}

	public String getActor() {
		return actor;
	}

	public String getFilm() {
		return film;
	}

	public int getYear() {
		return year;
	}

	public int getWikiRank() {
		return wikiRank;
	}

	
	
}
