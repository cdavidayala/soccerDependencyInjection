package com.example.demo;

import java.util.List;

public interface SoccerService {
	public List<String> getAllTeamPlayers(long teamId);

	public void addPlayerToTeam(String name, String position, int number, Long teamId);
	
	public String getTeam(Long teamId);
}
