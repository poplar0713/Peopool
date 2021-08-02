package com.ssafy.peopool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Follow;
import com.ssafy.peopool.model.service.HashtagService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/has")
public class HashtagController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	HashtagService hashtagService;

	@ApiOperation(value = "사용자별 등록한 태그목록", response = String.class)
	@GetMapping("/tag")
	public ResponseEntity<Map<Integer, String>> tagByUser() {
		return new ResponseEntity<Map<Integer, String>>(hashtagService.tagByUser(), HttpStatus.OK);

	}

	@ApiOperation(value = "태그를 등록한 사용자 수", response = String.class)
	@GetMapping("/count")
	public ResponseEntity<Map<String, Integer>> countByTag() {
		return new ResponseEntity<Map<String, Integer>>(hashtagService.countByTag(), HttpStatus.OK);

	}

	@ApiOperation(value = "태그 등록", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerHashtag(@RequestParam("tag_index") int tag_index,
			@RequestParam("ind_index") int ind_index) {
		if (hashtagService.registerHashtag(tag_index, ind_index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "태그 삭제", response = String.class)
	@DeleteMapping("{tag_index}")
	public ResponseEntity<String> deleteHashtag(@PathVariable("tag_index") int tag_index) {
		if (hashtagService.deletedHashtag(tag_index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}

}
