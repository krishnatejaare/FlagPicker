package com.asm.FlagPicker.model;

public class CountryFlag {

	private String flag;
	private String name;

	public CountryFlag(String name, String flag) {
		super();
		this.flag = flag;
		this.name = name;
	}

	public CountryFlag() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
