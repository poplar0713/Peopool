package com.ssafy.peopool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.TagList;
import com.ssafy.peopool.model.service.TagListService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/taglist")
public class TagListController {

	@Autowired
	TagListService taglistService;

	@ApiOperation(value = "등록된 태그 목록 받아오기", response = List.class)
	@GetMapping("/")
	public ResponseEntity<List<TagList>> getTagList() {
		return new ResponseEntity<>(taglistService.getTagList(), HttpStatus.OK);
	}

}
