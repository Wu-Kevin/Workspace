package com.revature.application;

import com.revature.model.Player;
import com.revature.service.PlayerService;

public class App {
	public static void main(String[] args) {
	//	System.out.println(PlayerService.getPlayerService().retrievePlayer(1));
//		
//		System.out.println(PlayerService.getPlayerService().deletePlayer(105));
		
//		System.out.println(PlayerService.getPlayerService().allPlayers());
//		
		Player p = new Player(105, "Mit Wobet", 150000, 3000, "The Gators");
		System.out.println(PlayerService.getPlayerService().addPlayer(p));
	}
	
	
}
