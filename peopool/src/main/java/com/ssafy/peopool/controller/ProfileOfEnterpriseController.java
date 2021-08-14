package com.ssafy.peopool.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.FileInfo;
import com.ssafy.peopool.model.ProfileOfEnterprise;
import com.ssafy.peopool.model.service.ProfileOfEnterpriseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poe")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ProfileOfEnterpriseController {

	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	ProfileOfEnterpriseService profileOfEnterpriseService;
	
	@ApiOperation(value = "전체 프로필을 가져온다.", response = String.class)
	@GetMapping()
	public ResponseEntity<List<EntCard>> getAllProfile() throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getAllProfile(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "팔로워 순으로 전체 프로필을 가져온다.", response = String.class)
	@GetMapping("/ByFollower")
	public ResponseEntity<List<EntCard>> getAllProfileByFollower() throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getAllProfileByFollower(), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/index/{index}")
	public ResponseEntity<EntCard> getProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getProfileOfEnterprise(index), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "name에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/name/{name}")
	public ResponseEntity<List<EntCard>> getNameOfEnterprise(@PathVariable("name")String name) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getNameOfEnterprise(name), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "기업소개에서 해당하는 단어가 있는 프로필을 가져온다.", response = String.class)
	@GetMapping("/intro")
	public ResponseEntity<List<EntCard>> getIntroOfEnterprise(@RequestParam("word")String word) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getIntroOfEnterprise(word), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "등록된 분류에서 해당하는 단어가 있는 프로필을 가져온다.", response = String.class)
	@GetMapping("/class")
	public ResponseEntity<List<EntCard>> getClassOfEnterprise(@RequestParam("word")String word) throws SQLException{
		return new ResponseEntity<>(profileOfEnterpriseService.getClassOfEnterprise(word), HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileOfEnterprise profileOfEnterprise) throws SQLException{
		if(profileOfEnterpriseService.modifyProfileOfEnterprise(profileOfEnterprise)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	
	@ApiOperation(value = "index에 해당하는 프로필을 삭제한다.(사용안함)", response = String.class)
	@DeleteMapping("{index}")
	public ResponseEntity<String> deleteProfileOfEnterprise(@PathVariable("index")int index) throws SQLException{
		if(profileOfEnterpriseService.deleteProfileOfEnterprise(index)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "index에 해당하는 사진을 수정한다.", response = String.class)
	@PostMapping("/photo/{index}")
	public ResponseEntity<String> modifyPhoto(@PathVariable("index")int index, @RequestParam("upfile") MultipartFile[] files) throws IllegalStateException, IOException, SQLException {
		ProfileOfEnterprise profileOfEnterprise = new ProfileOfEnterprise();
		if(!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
//			String realPath = servletContext.getRealPath("/resources/img");
			// 이미지 저장 경로
			String realPath = "/home/ubuntu/upload";
//			String realPath = "C:\\image\\";
//			String realPath = "C:\\image";
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = realPath + File.separator + today;
			String saveFolder = realPath + "/" + today;
			logger.info("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if(!folder.exists())
				folder.mkdirs();
			List<FileInfo> fileInfos = new ArrayList<FileInfo>();
			for (MultipartFile mfile : files) {
				FileInfo fileInfo = new FileInfo();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfo.setSaveFolder(today);
					fileInfo.setOriginFile(originalFileName);
					fileInfo.setSaveFile(saveFileName);
					logger.info("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfo);
			}
			EntCard ent = profileOfEnterpriseService.getProfileOfEnterprise(index);
			profileOfEnterprise.setEnt_index(index);
			profileOfEnterprise.setEnt_address(ent.getEnt_address());
			profileOfEnterprise.setEnt_ceo(ent.getEnt_ceo());
			profileOfEnterprise.setEnt_history(ent.getEnt_history());
			profileOfEnterprise.setEnt_introduce(ent.getEnt_introduce());
			profileOfEnterprise.setEnt_website(ent.getEnt_website());
			profileOfEnterprise.setFileInfos(fileInfos);
		}
		
		
		if(profileOfEnterpriseService.modifyPhoto(profileOfEnterprise)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
}
