package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.CateList;
import com.ssafy.peopool.model.TagList;
import com.ssafy.peopool.model.service.TagListService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/taglist")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class TagListController {

	@Autowired
	TagListService taglistService;

	@ApiOperation(value = "등록된 태그 목록 받아오기", response = List.class)
	@GetMapping("/")
	public ResponseEntity<List<TagList>> getTagList() {
		return new ResponseEntity<>(taglistService.getTagList(), HttpStatus.OK);
	}

	@ApiOperation(value = "분류 인덱스에 따라 태그 목록 받아오기", response = List.class)
	@GetMapping("/{index}")
	public ResponseEntity<List<TagList>> getCTagList(@PathVariable("index") int index) throws SQLException {
		return new ResponseEntity<>(taglistService.getCTagList(index), HttpStatus.OK);
	}
	
	@ApiOperation(value = "분류 목록 받아오기", response = List.class)
	@GetMapping("/cat")
	public ResponseEntity<List<CateList>> getCategoryList() {
		return new ResponseEntity<>(taglistService.getCategoryList(), HttpStatus.OK);
	}
}
