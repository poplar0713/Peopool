package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rec")
public class RecruitController {
	
	@Autowired
	RecruitService recruitService;

}
