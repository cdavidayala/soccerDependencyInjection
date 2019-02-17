package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(path = "/soccerController") 
public class SoccerController {
	@Autowired 
	private SoccerService soccerService;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;

	
	@PostMapping("/team")
	@ResponseBody ResponseEntity<?> team(@RequestBody Team team, UriComponentsBuilder ucBuilder) {
		teamRepository.save(team);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/soccerController/team/{id}").buildAndExpand(team.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "/all/{id}")
	public ResponseEntity<?> getAllUsers(@PathVariable("id") Long teamId) {
		// This returns a JSON or XML with the users
		return new ResponseEntity<Iterable<String>>(soccerService.getAllTeamPlayers(teamId), HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/team/{id}")
	public @ResponseBody String getTeam(@PathVariable("id") Long teamId) {
		// This returns a JSON or XML with the users
		return soccerService.getTeam(teamId);
		
	}
	
	@GetMapping(path = "/allPlayer/{id}")
	public @ResponseBody Integer getAllPlayers(@PathVariable("id") Long teamId) {
		// This returns a JSON or XML with the users
		return playerRepository.findByTeamId(teamId).size();
		
	}
}
