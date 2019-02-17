package com.example.demo;

import java.util.ArrayList;
import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SoccerServiceImpl implements SoccerService {
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;

	public List<String> getAllTeamPlayers(long teamId) {
		List<String> result = new ArrayList<String>();
		List<Player> players = playerRepository.findByTeamId(teamId);
		for (Player player : players) {
			result.add(player.getName());
		}
		return result;
	}

	public void addPlayerToTeam(String name, String position, int number, Long teamId){
		Team team = teamRepository.findById(teamId).get(); 
		Player newPlayer = new Player();
		newPlayer.setName(name);
		newPlayer.setPosition(position);
		newPlayer.setNum(number);
		newPlayer.setTeam(team);
		playerRepository.save(newPlayer);
	}

	@Override
	public String getTeam(Long teamId) {
		return teamRepository.findById(teamId).get().getName();
	}
}
