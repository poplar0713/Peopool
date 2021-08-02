package com.ssafy.peopool.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Enterprise;
import com.ssafy.peopool.model.Individual;
import com.ssafy.peopool.model.service.EnterpriseService;
import com.ssafy.peopool.model.service.IndividualService;
import com.ssafy.peopool.request.LoginReq;
import com.ssafy.peopool.response.BaseResponseBody;
import com.ssafy.peopool.response.LoginRes;
import com.ssafy.peopool.util.JwtTokenUtil;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	IndividualService individualService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = LoginRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<LoginRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) Individual individual) throws SQLException {
		String userId = individual.getInd_id();
		String password = individual.getInd_password();
		
		
		Individual ind = individualService.getUserId(userId);
		int index = ind.getInd_index();
		
		if(passwordEncoder.matches(password, ind.getInd_password())) {
			return ResponseEntity.ok(LoginRes.of(200, "Success", JwtTokenUtil.getToken(userId, index)));
		}
		return ResponseEntity.status(401).body(LoginRes.of(401, "Invalid Password", null));
	}
	
	@PostMapping("/loginent")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = LoginRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<LoginRes> loginEnt(@RequestBody @ApiParam(value="로그인 정보", required = true) Enterprise enterprise) throws SQLException {
		String userId = enterprise.getEnt_id();
		String password = enterprise.getEnt_password();
		
		
		Enterprise ent = enterpriseService.getUserId(userId);
		int index = ent.getEnt_index();
		
		if(passwordEncoder.matches(password, ent.getEnt_password())) {
			return ResponseEntity.ok(LoginRes.of(200, "Success", JwtTokenUtil.getToken(userId, index)));
		}
		return ResponseEntity.status(401).body(LoginRes.of(401, "Invalid Password", null));
	}
}
