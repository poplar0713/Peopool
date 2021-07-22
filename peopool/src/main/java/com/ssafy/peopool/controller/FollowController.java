package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.dto.Follow;
import com.ssafy.peopool.model.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fol")
public class FollowController {
	
	@Autowired
	FollowService followService;
	
	
	@ApiOperation(value = "팔로잉 추가", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerFollow(@RequestParam("follower")String follower, @RequestParam("following")String following, @RequestParam("followtype")String followtype){
		return null;
		
	}
	
	@ApiOperation(value = "팔로잉 삭제", response = String.class)
	@DeleteMapping()
	public ResponseEntity<String> findEnterprisePW(@RequestParam("follower")String follower, @RequestParam("following")String following, @RequestParam("followtype")String followtype){
		return null;
		
	}
	
	@ApiOperation(value = "팔로워, 팔로잉 숫자 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<String> getCount(@RequestBody Follow follow){
		return null;
		
	}


}
