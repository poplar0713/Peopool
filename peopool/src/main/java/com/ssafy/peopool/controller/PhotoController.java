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
@RequestMapping("/pho")
public class PhotoController {
	
	@Autowired
	PhotoService photoService;
	
	@ApiOperation(value = "index에 해당하는 사진을 수정한다.", response = String.class)
	@PutMapping("{index}")
	public ResponseEntity<String> modifyPhoto(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 사진을 수정한다.", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deletePhoto(@RequestBody ProfileOfIndividual profileOfIndividual){
		
	}

}