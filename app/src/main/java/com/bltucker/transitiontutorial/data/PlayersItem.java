package com.bltucker.transitiontutorial.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PlayersItem{

	@SerializedName("nationality")
	private String nationality;

	@SerializedName("name")
	private String name;

	@SerializedName("jerseyNumber")
	private int jerseyNumber;

	@SerializedName("dateOfBirth")
	private String dateOfBirth;

	@SerializedName("contractUntil")
	private String contractUntil;

	@SerializedName("id")
	private int id;

	@SerializedName("position")
	private String position;

	public void setNationality(String nationality){
		this.nationality = nationality;
	}

	public String getNationality(){
		return nationality;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setJerseyNumber(int jerseyNumber){
		this.jerseyNumber = jerseyNumber;
	}

	public int getJerseyNumber(){
		return jerseyNumber;
	}

	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
	}

	public void setContractUntil(String contractUntil){
		this.contractUntil = contractUntil;
	}

	public String getContractUntil(){
		return contractUntil;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPosition(String position){
		this.position = position;
	}

	public String getPosition(){
		return position;
	}

	@Override
 	public String toString(){
		return 
			"PlayersItem{" + 
			"nationality = '" + nationality + '\'' + 
			",name = '" + name + '\'' + 
			",jerseyNumber = '" + jerseyNumber + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",contractUntil = '" + contractUntil + '\'' + 
			",id = '" + id + '\'' + 
			",position = '" + position + '\'' + 
			"}";
		}
}