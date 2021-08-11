package com.ssafy.peopool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Classification;
import com.ssafy.peopool.model.service.ClassificationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cla")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ClassificationController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ClassificationService classificationService;
	
	@ApiOperation(value = "기업별 등록한 분류목록", response = String.class)
	@GetMapping("/list")
	public ResponseEntity<List<Map<Object, Object>>> getClass(@RequestParam("ent_index")int index) {
		return new ResponseEntity<>(classificationService.getClass(index), HttpStatus.OK);

	}
	
	@ApiOperation(value = "분류별 등록한 기업목록", response = String.class)
	@GetMapping("/ent")
	public ResponseEntity<List<Map<Object, Object>>> getEnt(@RequestParam("name")String name ) {
		return new ResponseEntity<>(classificationService.getEnt(name), HttpStatus.OK);

	}
	
	@ApiOperation(value = "분류 등록", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerClass(@RequestBody Classification classification) {
		if (classificationService.registerClass(classification)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "분류 삭제", response = String.class)
	@DeleteMapping("{cla_index}")
	public ResponseEntity<String> deleteClass(@PathVariable("cla_index") int index) {
		if (classificationService.deleteClass(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}

}
