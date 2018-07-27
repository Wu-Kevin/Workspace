package com.revature.dao;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.jdbcutil.jdbcconnection;
import com.revature.model.Player;

public class PlayerDaoImpl implements PlayerDao {

	public static PlayerDaoImpl playerDao;

	private PlayerDaoImpl() {

	}

	public static PlayerDaoImpl getDao() {
		if (playerDao == null) {
			playerDao = new PlayerDaoImpl();
		}
		return playerDao;
	}

	public Player getPlayer(int pid) {
		try {
			Connection conn = jdbcconnection.getConnection();
			String sql = "select * from player where p_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid); //changes first question mark

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new Player(rs.getInt("p_id"), 
						rs.getString("name"), 
						rs.getInt("salary"), 
						rs.getInt("points"),
						rs.getString("team"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean insertPlayer(Player p) {
		try {
			Connection conn = jdbcconnection.getConnection();
			String sql = "call add_player (?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			System.out.println(p.getName() + p.getPoints());
			cs.setString(1, p.getName());
			cs.setInt(2, p.getSalary());
			cs.setInt(3, 0);
			cs.setInt(4, p.getPoints());
			cs.setString(5, p.getTeam());

			if (cs.executeUpdate()>0) {
				//as long as it inserts more than 1, then success
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Player> allPlayers() {

		try {
			Connection conn = jdbcconnection.getConnection();

			String sql = "select * from player";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Player> players = new ArrayList<Player>();
			while (rs.next()) {
				players.add(new Player(rs.getInt("p_id"), rs.getString("name"), rs.getInt("salary"),
						rs.getInt("points"), rs.getString("team")));
			}
			return players;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deletePlayer(int pid) {
		try {
			Connection conn = jdbcconnection.getConnection();
			String sql = "delete from player where p_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			
			ps.executeQuery();
			
			if (ps.executeUpdate()>0) {
				//as long as it inserts more than 1, then success
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePlayer(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

}
