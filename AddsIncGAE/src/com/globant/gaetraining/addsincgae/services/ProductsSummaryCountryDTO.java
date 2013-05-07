package com.globant.gaetraining.addsincgae.services;


public class ProductsSummaryCountryDTO {

	private String country;
	private String name;
	private int totalHits;
	private int totalViews;

	public ProductsSummaryCountryDTO(String country, String name,
			int totalHits, int totalViews) {
		super();
		this.country = country;
		this.name = name;
		this.totalHits = totalHits;
		this.totalViews = totalViews;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}

	public int getTotalViews() {
		return totalViews;
	}

	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}

}
