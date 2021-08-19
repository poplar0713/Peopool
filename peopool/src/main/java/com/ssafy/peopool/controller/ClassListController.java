package com.ssafy.peopool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.ClassList;
import com.ssafy.peopool.model.service.ClassListService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/classlist")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ClassListController {
	
	@Autowired
	ClassListService classListService;

	@ApiOperation(value = "등록된 분류 목록 받아오기", response = List.class)
	@GetMapping("/")
	public ResponseEntity<List<ClassList>> getTagList() {
		return new ResponseEntity<>(classListService.getClassList(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "name을 포함한 분류 목록 받아오기", response = List.class)
	@GetMapping("/name")
	public ResponseEntity<List<ClassList>> getTagList(@RequestParam("name") String name) {
		return new ResponseEntity<>(classListService.getName(name), HttpStatus.OK);
	}

}
