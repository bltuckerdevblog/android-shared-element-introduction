package com.bltucker.transitiontutorial.data;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TeamListResponse{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	@SerializedName("count")
	private int count;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}
}