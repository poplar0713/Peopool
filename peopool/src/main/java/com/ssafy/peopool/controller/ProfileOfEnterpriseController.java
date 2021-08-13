package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.ProfileOfEnterprise;
import com.ssafy.peopool.model.service.ProfileOfEnterpriseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poe")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ProfileOfEnterpriseController {

	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	ProfileOfEnterpriseService profileOfEnterpriseService;
	
	@ApiOperation(value = "전체 프로필을 가져온다.", response = String.class)
	@GetMapping()
	public ResponseEntity<List<EntCard>> getAllProfile() throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getAllProfile(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로워 순으로 전체 프로필을 가져온다.", response = String.class)
	@GetMapping("/ByFollower")
	public ResponseEntity<List<EntCard>> getAllProfileByFollower() throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getAllProfileByFollower(), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/index/{index}")
	public ResponseEntity<EntCard> getProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getProfileOfEnterprise(index), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "name에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/name/{name}")
	public ResponseEntity<List<EntCard>> getNameOfEnterprise(@PathVariable("name")String name) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getNameOfEnterprise(name), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "기업소개에서 해당하는 단어가 있는 프로필을 가져온다.", response = String.class)
	@GetMapping("/intro")
	public ResponseEntity<List<EntCard>> getIntroOfEnterprise(@RequestParam("word")String word) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getIntroOfEnterprise(word), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "등록된 분류에서 해당하는 단어가 있는 프로필을 가져온다.", response = String.class)
	@GetMapping("/class")
	public ResponseEntity<List<EntCard>> getClassOfEnterprise(@RequestParam("word")String word) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getClassOfEnterprise(word), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileOfEnterprise profileOfEnterprise) throws SQLException{
		if(profileOfEnterpriseService.modifyProfileOfEnterprise(profileOfEnterprise)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 삭제한다.(사용안함)", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		if(profileOfEnterpriseService.deleteProfileOfEnterprise(index)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
}
