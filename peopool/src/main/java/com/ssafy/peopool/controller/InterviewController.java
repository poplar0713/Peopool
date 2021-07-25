package com.ssafy.peopool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Interview;
import com.ssafy.peopool.model.service.InterviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/int")
public class InterviewController {

	
	@Autowired
	InterviewService interviewService;
	
	@ApiOperation(value = "면접 일정 등록", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerInterview(@RequestBody Interview interview){
		return null;
		
	}
	
	@ApiOperation(value = "면접 URL 등록", response = String.class)
	@PutMapping("{url}")
	public ResponseEntity<String> registerURL(@PathVariable("url")String url){
		return null;
		
	}
	
	@ApiOperation(value = "면접 일정 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<List<Interview>> getInterviews(@PathVariable("index")String index){
		return null;
		
	}
	
	@ApiOperation(value = "지난 면접 일정 조회", response = String.class)
	@GetMapping("/last/{index}")
	public ResponseEntity<List<Interview>> getLastInterviews(@PathVariable("index")String index){
		return null;
		
	}
	
	@ApiOperation(value = "면접 종료", response = String.class)
	@PutMapping("{index}")
	public ResponseEntity<String> finishInterview(@PathVariable("index")String index){
		return null;
		
	}
	
	@ApiOperation(value = "면접 노쇼", response = String.class)
	@PutMapping("/is/{index}")
	public ResponseEntity<String> isInterview(@PathVariable("index")String index){
		return null;
		
	}
	
}
