package com.ssafy.peopool.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.ProfileOfEnterprise;
import com.ssafy.peopool.model.service.ProfileOfEnterpriseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poe")
public class ProfileOfEnterpriseController {

	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	ProfileOfEnterpriseService profileOfEnterpriseService;
	
	@ApiOperation(value = "전체 프로필을 가져온다.", response = String.class)
	@GetMapping()
	public ResponseEntity<ProfileOfEnterprise> getAllProfile() throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getAllProfile(), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/index/{index}")
	public ResponseEntity<ProfileOfEnterprise> getProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getProfileOfEnterprise(index), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "name에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/name/{name}")
	public ResponseEntity<ProfileOfEnterprise> getNameOfEnterprise(@PathVariable("name")String name) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getNameOfEnterprise(name), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileOfEnterprise profileOfEnterprise) throws SQLException{
		if(profileOfEnterpriseService.modifyProfileOfEnterprise(profileOfEnterprise)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 삭제한다.", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		if(profileOfEnterpriseService.deleteProfileOfEnterprise(index)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
}
