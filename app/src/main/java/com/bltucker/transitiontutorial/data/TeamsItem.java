package com.bltucker.transitiontutorial.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TeamsItem{

	@SerializedName("squadMarketValue")
	private String squadMarketValue;

	@SerializedName("crestUrl")
	private String crestUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("shortName")
	private String shortName;

	public void setSquadMarketValue(String squadMarketValue){
		this.squadMarketValue = squadMarketValue;
	}

	public String getSquadMarketValue(){
		return squadMarketValue;
	}

	public void setCrestUrl(String crestUrl){
		this.crestUrl = crestUrl;
	}

	public String getCrestUrl(){
		return crestUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}
}