package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	InterviewService interviewService;
	
	@ApiOperation(value = "면접 일정 등록", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerInterview(@RequestBody Interview interview) throws SQLException{
		if(interviewService.registerInterview(interview)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "면접 URL 등록", response = String.class)
	@PutMapping("{url}")
	public ResponseEntity<String> registerURL(@PathVariable("url")String url) throws SQLException{
		if(interviewService.registerURL(url)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "면접 일정 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<List<Interview>> getInterviews(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(interviewService.getInterviews(index), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "지난 면접 일정 조회", response = String.class)
	@GetMapping("/last/{index}")
	public ResponseEntity<List<Interview>> getLastInterviews(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(interviewService.getLastInterviews(index), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "면접 종료", response = String.class)
	@PutMapping("/finish")
	public ResponseEntity<String> finishInterview(@RequestBody Interview interview) throws SQLException{
		if(interviewService.finishInterview(interview)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "면접 노쇼", response = String.class)
	@PutMapping("/show")
	public ResponseEntity<String> isInterview(@RequestBody Interview interview) throws SQLException{
		if(interviewService.isInterview(interview)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
}
