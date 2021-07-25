package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Enterprise;
import com.ssafy.peopool.model.service.EnterpriseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ent")
public class EnterpriseController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnterpriseController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ApiOperation(value = "기업 회원 전체 조회", response = String.class)
	@GetMapping()
	public ResponseEntity<List<Enterprise>> getAllEnterprise() throws SQLException{
		return new ResponseEntity<>(enterpriseService.getAllEnterprise(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "기업 회원 로그인", response = String.class)
	@GetMapping("/login")
	public ResponseEntity<Enterprise> loginCheckEnterprise(@RequestParam("id")String id) throws SQLException{
		return new ResponseEntity<>(enterpriseService.loginCheckEnterprise(id), HttpStatus.OK);	
	}
	
	
	@ApiOperation(value = "기업회원 회원 정보 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<Enterprise> getEnterprise(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(enterpriseService.getEnterprise(index), HttpStatus.OK);
	}
	
	@ApiOperation(value = "기업회원 아이디 찾기", response = String.class)
	@GetMapping("/findid")
	public ResponseEntity<Enterprise> findEnterpriseID(@RequestBody Enterprise enterprise) throws SQLException{
		return new ResponseEntity<>(enterpriseService.findEnterpriseID(enterprise), HttpStatus.OK);
	}
	
	@ApiOperation(value = "기업회원 비밀번호 찾기", response = String.class)
	@GetMapping("/findpw")
	public ResponseEntity<Enterprise> findEnterprisePW(@RequestBody Enterprise enterprise) throws SQLException{
		return new ResponseEntity<>(enterpriseService.findEnterprisePW(enterprise), HttpStatus.OK);
	}
	
	@ApiOperation(value = "기업회원 회원가입", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerEnterprise(@RequestBody Enterprise enterprise) throws SQLException{
		if(enterpriseService.registerEnterprise(enterprise)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "기업회원 회원 정보 수정", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyEnterprise(@RequestBody Enterprise enterprise) throws SQLException{
		if(enterpriseService.modifyEnterprise(enterprise)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "기업회원 회원 탈퇴", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteEnterprise(@PathVariable("index")int index) throws SQLException{
		if(enterpriseService.deleteEnterprise(index)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
