package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.dto.ProfileOfIndividual;
import com.ssafy.peopool.model.service.ProfileOfIndividualService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poi")
public class ProfileOfIndividualController {

	
	@Autowired
	ProfileOfIndividualService profileOfIndividualService;
	
	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/{index}")
	public ResponseEntity<String> getProfile(@RequestBody ProfileOfIndividual profileOfIndividual){
		return null;
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping("/{index}")
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileOfIndividual profileOfIndividual){
		return null;
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@DeleteMapping("/{index}")
	public ResponseEntity<String> deleteProfile(@RequestBody ProfileOfIndividual profileOfIndividual){
		return null;
		
	}
}
