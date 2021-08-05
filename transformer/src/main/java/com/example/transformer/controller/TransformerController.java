package com.example.transformer.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.transformer.model.Transformer;
import com.example.transformer.service.TransformerService;

@RestController
public class TransformerController {
	
	@Autowired
	TransformerService service;
	
	@GetMapping("/transformers")
	List<Transformer> all() {
		return service.findAll();
	}
	
	@PostMapping("/transformers/battle")
	String battle(@RequestBody List<Transformer> transformers) {
		List<Transformer> Decepticons = new ArrayList<>();
		List<Transformer> Autobots = new ArrayList<>();
		transformers.stream().forEach(transformer -> {
			if (transformer.getType().equals("A"))
				Autobots.add(transformer);
			else if (transformer.getType().equals("D")) {
				Decepticons.add(transformer);
			}
		});
		
		//sort Autobots, Decepticons by rank
		Collections.sort(Autobots);
		Collections.sort(Decepticons);
		
		int m = Autobots.size();
		int n = Decepticons.size();
		int counter1 = 0;
		int counter2 = 0;
		int tie = 0;
		List<Transformer> winner1 = new ArrayList<>();
		List<Transformer> winner2 = new ArrayList<>();
		List<Transformer> survivors = new ArrayList<>();
		for(int i = 0, j = 0; i < m && j < n; i++, j++) {
			//if Autobots win
			if (fight(Autobots.get(i), Decepticons.get(j)) == 1) {
				counter1++;
				winner1.add(Autobots.get(i));
			}
			//if Decepticons win
			else if (fight(Autobots.get(i), Decepticons.get(j)) == 2) {
				counter2++;
				winner2.add(Decepticons.get(j));
			}
			//if both tie
			else if (fight(Autobots.get(i), Decepticons.get(j)) == 0) {
				tie++;
			}
		}
		int battleCounter = counter1 + counter2 + tie;
		String winner = "";
		String winningTeam = "";
		String losingTeam = "";
		String surv = "";
		if (counter1 > counter2) {
			winningTeam = "Autobots";
			losingTeam = "Decepticons";
			winner = winner1.stream().map(t -> t.getName()).reduce("", (t1, t2) -> t1 + t2);
			if (n > m) {
				survivors.addAll(Decepticons.subList(m, n));
				surv = survivors.stream().map(t -> t.getName()).reduce("", (t1, t2) -> t1 + t2);
			}
		}			 
		else if (counter1 < counter2) {
			winningTeam = "Decepticons";
			losingTeam = "Autobots";
			winner = winner2.stream().map(t -> t.getName()).reduce("", (t1, t2) -> t1 + t2);
			if (m > n) {
				survivors.addAll(Autobots.subList(n, m));
				surv = survivors.stream().map(t -> t.getName()).reduce("", (t1, t2) -> t1 + t2);
			}
		}
		
		return battleCounter + " battle Winning team (" + winningTeam + "): " + winner 
				+ " Survivors from the losing team (" + losingTeam + "): " + surv;
	}
	
	//return 1 if t1 win, return 2 if t2 win, return 0 if both tie
	int fight(Transformer t1, Transformer t2) {
		if (t1.getName().equals("Optimus Prime") || t1.getName().equals("Predaking")) {
			return 1;
		}
		else if (t2.getName().equals("Optimus Prime") || t2.getName().equals("Predaking")) {
			return 2;
		}
		else if (t1.getCourage() >= t2.getCourage() + 4 && t1.getStrength() >= t2.getStrength() + 3) {
			return 1;
		}
		else if (t2.getCourage() >= t1.getCourage() + 4 && t2.getStrength() >= t1.getStrength() + 3) {
			return 2;
		}		
		else if (t1.getSkill() >= t2.getSkill() + 3) {
			return 1;
		}
		else if (t2.getSkill() >= t1.getSkill() + 3) {
			return 2;
		}
		else {
			if (t1.getOverallRating() > t2.getOverallRating()) {
				return 1;
			}
			else if (t2.getOverallRating() > t1.getOverallRating()) {
				return 2;
			}
			else {
				return 0;
			}
		}

	}
	
	@PutMapping("/transformers")
	Transformer update(Transformer transformer) {
		return service.save(transformer);
	}
	
	@PostMapping("/transformers")
	Transformer create(Transformer transformer) {
		return service.save(transformer);
	}
		
	@DeleteMapping("/transformers")
	void delete(Transformer transformer) {
		 service.delete(transformer);
	}
}
