package com.ssafy.peopool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.CareerList;
import com.ssafy.peopool.model.service.CareerListService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/career")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class CareerListContorller {
	
	@Autowired
	CareerListService careerListService;
	
	@ApiOperation(value = "커리어 목록 받아오기", response = List.class)
	@GetMapping("/")
	public ResponseEntity<List<CareerList>> getCareerList() {
		return new ResponseEntity<>(careerListService.getCareerList(), HttpStatus.OK);
	}
}
