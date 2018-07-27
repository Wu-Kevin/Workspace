package com.revature.model;

public class Player {
	private int pid;
	private String name; 
	private int salary;
	private int points;
	private String team;
	
	
	public Player(int pid, String name, int salary, int points, String team) {
		super();
		this.pid = pid;
		this.name = name;
		this.salary = salary;
		this.points = points;
		this.team = team;
	}
	
	@Override
	public String toString() {
		return "Player [pid=" + pid + ", name=" + name + ", salary=" + salary + ", points=" + points + ", team=" + team
				+ "]";
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	
}
