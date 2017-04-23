package com.bltucker.transitiontutorial.data;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PlayerListResponse{

	@SerializedName("players")
	private List<PlayersItem> players;

	@SerializedName("count")
	private int count;

	public void setPlayers(List<PlayersItem> players){
		this.players = players;
	}

	public List<PlayersItem> getPlayers(){
		return players;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"PlayerListResponse{" + 
			"players = '" + players + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}