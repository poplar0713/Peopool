package com.ssafy.peopool.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.peopool.model.Individual;
import com.ssafy.peopool.model.ProfileOfEnterprise;
//import com.ssafy.api.request.UserLoginPostReq;
//import com.ssafy.api.request.UserRegiserIdGetReq;
//import com.ssafy.api.request.UserRegisterPostReq;
//import com.ssafy.api.response.UserLoginPostRes;
//import com.ssafy.api.response.UserRes;
//import com.ssafy.api.service.UserService;
//import com.ssafy.common.auth.SsafyUserDetails;
//import com.ssafy.common.model.response.BaseResponseBody;
//import com.ssafy.common.util.JwtTokenUtil;
//import com.ssafy.db.entity.User;
//import com.ssafy.db.repository.UserRepositorySupport;
import com.ssafy.peopool.model.service.IndividualService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/ind")
public class IndividualController {
	
	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	IndividualService individualService;
	
	@ApiOperation(value = "개인 회원 전체 조회", response = String.class)
	@GetMapping()
	public ResponseEntity<List<Individual>> getAllUser() throws SQLException{
		return new ResponseEntity<>(individualService.getAllUser(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "개인 로그인", response = String.class)
	@GetMapping("/login")
	public ResponseEntity<Individual> loginCheckIndividual(@RequestParam("id")String id, @RequestParam("pw") String pw) throws SQLException{
		return new ResponseEntity<>(individualService.loginCheckIndividual(id,pw), HttpStatus.OK);
	}
	
	@ApiOperation(value = "개인 회원 정보 조회", response = String.class)
	@GetMapping("{index}")
	public ResponseEntity<Individual> getIndividual(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(individualService.getIndividual(index), HttpStatus.OK);
	}
	
	@ApiOperation(value = "개인 회원 가입", response = String.class)
	@PostMapping()
	public ResponseEntity<String> registerIndividual(@RequestBody Individual individual) throws SQLException{
		if(individualService.registerIndividual(individual)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "개인 회원 정보 수정", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyIndividual(@RequestBody Individual individual) throws SQLException{
		if(individualService.modifyIndividual(individual)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "개인 회원 탈퇴", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteIndividual(@PathVariable("index")int index) throws SQLException{
		if(individualService.deleteIndividual(index)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "아이디 찾기", response = String.class)
	@GetMapping("/findid")
	public ResponseEntity<Individual> findIndividualID(@RequestParam("name")String name, @RequestParam("phone")String phone) throws SQLException{
		return new ResponseEntity<>(individualService.findIndividualID(name, phone), HttpStatus.OK);
	}
	
	@ApiOperation(value = "비밀번호 찾기", response = String.class)
	@GetMapping("/findpw")
	public ResponseEntity<Individual> findIndividualPW(@RequestParam("id")String id, @RequestParam("phone")String phone) throws SQLException{
		return new ResponseEntity<>(individualService.findIndividualPW(id, phone), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "아이디 중복 체크", response = String.class)
	@PostMapping("/checkid")
	public ResponseEntity<String> getUserId(@RequestBody Individual individual) throws SQLException{
		Individual ind = individualService.getUserId(individual.getInd_id());
		return ResponseEntity.status(200).body(ind.getInd_id());
		
//		if(individualService.getUserId(id) != null) {
//            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        }
//        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping()
//	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<? extends BaseResponseBody> register(
//			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
//		
//		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
//		User user = userService.createUser(registerInfo);
//		
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}
//	
//	@GetMapping("/me")
//	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
//		/**
//		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
//		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
//		 */
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
//		User user = userService.getUserByUserId(userId);
//		
//		return ResponseEntity.status(200).body(UserRes.of(user));
//	}
//	
//	@PostMapping("/checkId")
//	@ApiOperation(value = "아이디 조회", notes = "회원 가입 전 중복된 아이디가 존재하는지를 확인한다.")
//	@ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<UserRes> getUserId(@RequestBody @ApiParam(value="사용하고자 하는 ID", required = true) UserRegiserIdGetReq id) {
//		
//		String userId = id.getId();
//		User user = userService.getUserByUserId(userId);
//		System.out.println(user.getUserId());
//		return ResponseEntity.status(200).body(UserRes.of(user));
//	}
}
