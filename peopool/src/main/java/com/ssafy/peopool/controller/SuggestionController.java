package com.ssafy.peopool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.ssafy.peopool.model.Suggestion;
import com.ssafy.peopool.model.service.SuggestionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sug")
public class SuggestionController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	SuggestionService suggestionService;

	@ApiOperation(value = "개인회원의 인덱스로 면접 요청 기록 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<List<Suggestion>> getSuggestion(@PathVariable("index") int index) {
		return new ResponseEntity<>(suggestionService.getSuggestion(index), HttpStatus.OK);

	}
	
	@ApiOperation(value = "기업회원의 인덱스로 면접 요청 기록 조회", response = String.class)
	@GetMapping("/ent/{index}")
	public ResponseEntity<List<Suggestion>> getEntSuggestion(@PathVariable("index") int index) {
		return new ResponseEntity<>(suggestionService.getEntSuggestion(index), HttpStatus.OK);

	}

	@ApiOperation(value = "면접 요청", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerSuggestion(@RequestBody Suggestion suggestion) {
		if (suggestionService.registerSuggestion(suggestion)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "개인회원의 면접 수락", response = String.class)
	@PutMapping("/accept")
	public ResponseEntity<String> acceptSuggestion(@RequestBody Suggestion suggestion) {
		if (suggestionService.acceptSuggestion(suggestion)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "개인회원의 면접 거절", response = String.class)
	@PutMapping("/reject")
	public ResponseEntity<String> rejectSuggestion(@RequestParam("index") int index) {
		if (suggestionService.rejectSuggestion(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "요청 기록 삭제", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteSuggestion(@PathVariable("index") int index) {
		if (suggestionService.deleteSuggestion(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
	
	
	
	
}
