package com.ssafy.peopool.controller;

import java.io.File;
import java.io.IOException;
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

import com.ssafy.peopool.model.FileInfo;
import com.ssafy.peopool.model.IndCard;
import com.ssafy.peopool.model.ProfileOfIndividual;
import com.ssafy.peopool.model.service.ProfileOfIndividualService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/poi")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ProfileOfIndividualController {

	private static final Logger logger = LoggerFactory.getLogger(ProfileOfIndividualController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	ProfileOfIndividualService profileOfIndividualService;
	
	

	@ApiOperation(value = "전체 프로필을 가져온다.", response = String.class)
	@GetMapping()
	public ResponseEntity<List<IndCard>> getAllProfile() {
		return new ResponseEntity<>(profileOfIndividualService.getAllProfile(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "index에 해당하는 프로필을 가져온다.", response = String.class)
	@GetMapping("/{index}")
	public ResponseEntity<IndCard> getProfile(@PathVariable("index")int index) {
		return new ResponseEntity<>(profileOfIndividualService.getProfile(index), HttpStatus.OK);

	}

	@ApiOperation(value = "index에 해당하는 프로필을 수정한다.", response = String.class)
	@PutMapping()
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileOfIndividual profileOfIndividual) {
		if (profileOfIndividualService.modifyProfile(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "프로필을 공개로 변경한다.", response = String.class)
	@PutMapping("/switchOn")
	public ResponseEntity<String> modifySwitchOn(@RequestBody ProfileOfIndividual profileOfIndividual ) {
		if (profileOfIndividualService.modifySwitchOn(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "프로필을 비공개로 변경한다.", response = String.class)
	@PutMapping("/switchOff")
	public ResponseEntity<String> modifySwitchOff(@RequestBody ProfileOfIndividual profileOfIndividual) {
		if (profileOfIndividualService.modifySwitchOff(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "프로필을 삭제한다.(사용안함)", response = String.class)
	@DeleteMapping("/{index}")
	public ResponseEntity<String> deleteProfile(@PathVariable("index")int index) {
		if (profileOfIndividualService.deleteProfile(index)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "index에 해당하는 이력서을 수정한다.", response = String.class)
	@PostMapping("/resume/{index}")
	public ResponseEntity<String> modifyResume(@PathVariable("index")int index, @RequestParam("upfile") MultipartFile[] files) throws IllegalStateException, IOException {
		ProfileOfIndividual profileOfIndividual = new ProfileOfIndividual();
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
			IndCard ind = profileOfIndividualService.getProfile(index);
			profileOfIndividual.setInd_index(index);
			profileOfIndividual.setInd_photo(ind.getInd_photo());
			profileOfIndividual.setInd_switch(ind.getInd_switch());
			profileOfIndividual.setInd_introduce(ind.getInd_introduce());
			profileOfIndividual.setInd_video(ind.getInd_video());
			profileOfIndividual.setFileInfos(fileInfos);
		}
		
//		profileOfIndividual.setUserid(memberDto.getUserid());
		
		if(profileOfIndividualService.modifyResume(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
	
	@ApiOperation(value = "index에 해당하는 영상을 수정한다.", response = String.class)
	@PostMapping("/video/{index}")
	public ResponseEntity<String> modifVideo(@PathVariable("index")int index, @RequestParam("upfile") MultipartFile[] files) throws IllegalStateException, IOException {
		ProfileOfIndividual profileOfIndividual = new ProfileOfIndividual();
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
			IndCard ind = profileOfIndividualService.getProfile(index);
			profileOfIndividual.setInd_index(index);
			profileOfIndividual.setInd_resume(ind.getInd_resume());
			profileOfIndividual.setInd_switch(ind.getInd_switch());
			profileOfIndividual.setInd_introduce(ind.getInd_introduce());
			profileOfIndividual.setInd_photo(ind.getInd_photo());
			profileOfIndividual.setFileInfos(fileInfos);
		}
		
//		profileOfIndividual.setUserid(memberDto.getUserid());
		
		if(profileOfIndividualService.modifyVideo(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
	
	@ApiOperation(value = "index에 해당하는 사진을 수정한다.", response = String.class)
	@PostMapping("/photo/{index}")
	public ResponseEntity<String> modifyPhoto(@PathVariable("index")int index, @RequestParam("upfile") MultipartFile[] files) throws IllegalStateException, IOException {
		ProfileOfIndividual profileOfIndividual = new ProfileOfIndividual();
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
			IndCard ind = profileOfIndividualService.getProfile(index);
			profileOfIndividual.setInd_index(index);
			profileOfIndividual.setInd_video(ind.getInd_video());
			profileOfIndividual.setInd_switch(ind.getInd_switch());
			profileOfIndividual.setInd_introduce(ind.getInd_introduce());
			profileOfIndividual.setInd_resume(ind.getInd_resume());
			profileOfIndividual.setFileInfos(fileInfos);
		}
		
//		profileOfIndividual.setUserid(memberDto.getUserid());
		
		if(profileOfIndividualService.modifyPhoto(profileOfIndividual)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
}
