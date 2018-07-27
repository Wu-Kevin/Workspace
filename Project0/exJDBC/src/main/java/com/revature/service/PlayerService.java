package com.revature.service;

import java.util.List;
import java.util.ArrayList;

import com.revature.dao.PlayerDaoImpl;
import com.revature.model.Player;

public class PlayerService {
	private static PlayerService playerService;
	
	private PlayerService() {
		
	}
	
	public static PlayerService getPlayerService() {
		if(playerService == null) {
			playerService = new PlayerService(); 
		}
		return playerService;
	}
	
	public static Player retrievePlayer(int pid) {
		return PlayerDaoImpl.getDao().getPlayer(pid);
	}
	
	public static boolean deletePlayer(int pid) {
		return PlayerDaoImpl.getDao().deletePlayer(pid);
	}
	
	public static List<Player> allPlayers() {
		return PlayerDaoImpl.getDao().allPlayers();
	}
	
	public static boolean addPlayer(Player p) {
		return PlayerDaoImpl.getDao().insertPlayer(p);
	}
}
