package com.ssafy.peopool.model.service;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.IntCard;
import com.ssafy.peopool.model.Interview;
import com.ssafy.peopool.model.InterviewDays;
import com.ssafy.peopool.model.repo.InterviewRepo;

@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	InterviewRepo interviewRepo;
	
	
	@Override
	public boolean registerURL(Interview interview) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.registerURL(interview) == 1;
	}

	@Override
	public List<IntCard> getInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getInterviews(index);
	}

	@Override
	public List<IntCard> getLastInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getLastInterviews(index);
	}

	@Override
	public boolean finishInterview(Interview interview) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.finishInterview(interview) == 1;
	}

	@Override
	public boolean isInterview(Interview interview) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.isInterview(interview) == 1;
	}

	@Override
	public boolean registerInterview(Interview interview) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.registerInterview(interview) == 1;
	}

	@Override
	public List<IntCard> getEInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getEInterviews(index);
	}

	@Override
	public List<IntCard> getELastInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getELastInterviews(index);
	}

	@Override
	public List<InterviewDays> getEDInterviewGroupByDays(int index) throws SQLException {
		HashMap<String, InterviewDays> map = new HashMap<>();
		List<IntCard> tempList = interviewRepo.getEInterviews(index);
		List<InterviewDays> result = new ArrayList<InterviewDays>();
		StringTokenizer st;
		
		for(int i = 0; i < tempList.size(); i++) {
			st = new StringTokenizer(tempList.get(i).getInt_start()," ");
			String date = st.nextToken();
			if(map.containsKey(date)) {
				map.get(date).getInterviewers().add(tempList.get(i));
			}
			else {
				InterviewDays temp = new InterviewDays();
				temp.setDate(date);
				temp.getInterviewers().add(tempList.get(i));
				map.put(date, temp);
			}
		}
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
		    String key = iter.next();
		    result.add(map.get(key));
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	@Override
	public boolean modifyPass(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.modifyPass(index) == 1;
	}

	@Override
	public boolean modifyFail(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.modifyFail(index) == 1;
	}

}
