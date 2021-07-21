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
@RequestMapping("/vid")
public class VideoController {

	
	@Autowired
	VideoService videoService;
	
	@ApiOperation(value = "index에 해당하는 영상을 수정한다.", response = String.class)
	@PutMapping("{index}")
	public ResponseEntity<String> modifyVideo(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 영상을 수정한다.", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteVideo(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}
}
