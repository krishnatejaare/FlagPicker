package com.asm.FlagPicker.model;

import java.util.List;

public class Continent {

	private String continent;

	private List<CountryFlag> countries;

	public Continent(String continent, List<CountryFlag> countries) {
		super();
		this.continent = continent;
		this.countries = countries;
	}

	public Continent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<CountryFlag> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryFlag> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return String.format("Continent [continent=%s, countries=%s]", continent, countries);
	}

}
