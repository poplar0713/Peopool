package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.dto.ProfileOfIndividual;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/intro")
public class IntroduceController {

	
	@Autowired
	IntroduceService introduceService;
	
	@ApiOperation(value = "index에 해당하는 자기소개를 수정한다.", response = String.class)
	@PutMapping("{index}")
	public ResponseEntity<String> modifyIntroduce(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 자기소개를 수정한다.", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteIntroduce(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}
}
