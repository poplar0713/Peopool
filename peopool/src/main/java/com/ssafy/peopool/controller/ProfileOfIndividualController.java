package com.ssafy.peopool.controller;

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

import com.ssafy.peopool.model.ProfileOfIndividual;
import com.ssafy.peopool.model.service.ProfileOfIndividualService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poi")
public class ProfileOfIndividualController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	ProfileOfIndividualService profileOfIndividualService;

	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/{index}")
	public ResponseEntity<ProfileOfIndividual> getProfile(@PathVariable("index") int index) {
		return new ResponseEntity<ProfileOfIndividual>(profileOfIndividualService.getProfile(index), HttpStatus.OK);

	}

	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyProfile(@PathVariable("profile") ProfileOfIndividual file,
			@PathVariable("index") int index) {
		if (profileOfIndividualService.modifyProfile(file, index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "index에 해당하는 프로필을 공개로 변경한다.", response = String.class)
	@PutMapping("/switchOn/{index}")
	public ResponseEntity<String> modifySwitchOn(@PathVariable("index") int index) {
		if (profileOfIndividualService.modifySwitchOn(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "index에 해당하는 프로필을 공개로 변경한다.", response = String.class)
	@PutMapping("/switchOff/{index}")
	public ResponseEntity<String> modifySwitchOff(@PathVariable("index") int index) {
		if (profileOfIndividualService.modifySwitchOff(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
