package com.revature.dao;
import java.util.List;

import com.revature.model.Player;

public interface PlayerDao {
	
	public Player getPlayer(int pid);
	public boolean insertPlayer(Player p);
	public boolean deletePlayer(int pid); 
	public List<Player> allPlayers();
	public boolean updatePlayer(Player p);
	
}
