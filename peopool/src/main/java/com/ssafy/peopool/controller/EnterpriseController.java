package com.ssafy.peopool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.dto.Enterprise;
import com.ssafy.peopool.model.service.EnterpriseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ent")
public class EnterpriseController {
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value = "기업회원 회원 정보 조회", response = String.class)
	@GetMapping("/{index}")
	public ResponseEntity<String> getEnterprise(@RequestBody Enterprise enterprise){
		return null;
		
	}
	
	@ApiOperation(value = "기업회원 아이디 찾기", response = String.class)
	@GetMapping("/findid")
	public ResponseEntity<String> findEnterpriseID(@RequestParam("name")String name, @RequestParam("email")String email ){
		return null;
		
	}
	
	@ApiOperation(value = "기업회원 비밀번호 찾기", response = String.class)
	@GetMapping("/findpw")
	public ResponseEntity<String> findEnterprisePW(@RequestParam("id")String id, @RequestParam("email")String email ){
		return null;
		
	}
	
	@ApiOperation(value = "기업회원 회원가입", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerEnterprise(@RequestBody Enterprise enterprise){
		return null;
		
	}
	
	@ApiOperation(value = "기업회원 회원 정보 수정", response = String.class)
	@PutMapping("{index}")
	public ResponseEntity<String> modifyEnterprise(@RequestBody Enterprise enterprise){
		return null;
		
	}
	
	@ApiOperation(value = "기업회원 회원 탈퇴", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteEnterprise(@RequestBody Enterprise enterprise){
		return null;
		
	}

}
