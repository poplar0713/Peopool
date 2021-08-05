package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.FolCard;
import com.ssafy.peopool.model.Follow;
import com.ssafy.peopool.model.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fol")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class FollowController {
	

//follower : follow 당한 사람
//following  : follow 한 사람
//fol_type   : follow 한 사람의 형태
	
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	FollowService followService;
	
	
	@ApiOperation(value = "팔로잉 추가", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerFollow(@RequestBody Follow follow) throws SQLException{
		
		if(followService.registerFollow(follow)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "팔로잉 삭제", response = String.class)
	@DeleteMapping()
	public ResponseEntity<String> findEnterprisePW(@RequestBody Follow follow) throws SQLException{
		if(followService.deleteFollow(follow)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "팔로워 숫자 조회", response = String.class)
	@GetMapping("/counter")
	public ResponseEntity<Integer> getFollowerCount(@RequestParam("index")String index, @RequestParam("type")String type) throws SQLException{
		String temp = "";
		if(type.equals("0")) {
			temp = "1";
		}
		else {
			temp = "0";
		}
		return new ResponseEntity<>(followService.getFollowerCount(index, temp), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로잉 숫자 조회", response = String.class)
	@GetMapping("/counting")
	public ResponseEntity<Integer> getFollowingCount(@RequestParam("index")String index, @RequestParam("type")String type) throws SQLException{
		return new ResponseEntity<>(followService.getFollowingCount(index, type), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로잉 조회", response = String.class)
	@GetMapping("/following")
	public ResponseEntity<List<FolCard>> getEFollowing(@RequestParam("index")String index, @RequestParam("type")String type) throws SQLException{
		return new ResponseEntity<>(followService.getFollowing(index, type), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로워 조회", response = String.class)
	@GetMapping("/follower")
	public ResponseEntity<List<FolCard>> getEFollower(@RequestParam("index")String index, @RequestParam("type")String type) throws SQLException{
		String temp = "";
		if(type.equals("0")) {
			temp = "1";
		}
		else {
			temp = "0";
		}
		return new ResponseEntity<>(followService.getFollower(index, temp), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로우 했는지 안했는지 조회", response = String.class)
	@PostMapping("/check")
	public ResponseEntity<Integer> getCheck(@RequestBody Follow follow) throws SQLException{
		Follow fol = followService.getCheck(follow);
		return ResponseEntity.status(200).body(fol.getFol_index());
	}
	



}
