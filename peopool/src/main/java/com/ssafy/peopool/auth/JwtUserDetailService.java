package com.ssafy.peopool.auth;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ssafy.peopool.model.Individual;
import com.ssafy.peopool.model.service.IndividualService;


@Component
public class JwtUserDetailService implements UserDetailsService{
	@Autowired
	IndividualService individualService;
	
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
    		Individual individual = null;
			try {
				individual = individualService.getUserId(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(individual != null) {
    			JwtUserDetails userDetails = new JwtUserDetails(individual);
    			return userDetails;
    		}
    		return null;
    }
}
