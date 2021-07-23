package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.dto.Suggestion;
import com.ssafy.peopool.model.service.SuggestionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sug")
public class SuggestionController {

	
	@Autowired
	SuggestionService suggestionService;
	
	@ApiOperation(value = "면접 요청 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<Suggestion> getSuggestion(@PathVariable("index")String index){
		return null;
		
	}
	
	@ApiOperation(value = "면접 요청", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerSuggestion(@RequestBody Suggestion suggestion){
		return null;
		
	}
	
	@ApiOperation(value = "면접 수락", response = String.class)
	@PutMapping("/accept")
	public ResponseEntity<String> acceptSuggestion(@RequestParam("time")String time, @RequestParam("index")String index){
		return null;
		
	}
	
	@ApiOperation(value = "면접 거절", response = String.class)
	@PutMapping("/reject")
	public ResponseEntity<String> rejectSuggestion(@RequestParam("index")String index){
		return null;
		
	}
	
	
	@ApiOperation(value = "요청 기록 삭제", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteSuggestion(@PathVariable("index")String index){
		return null;
		
	}
}
