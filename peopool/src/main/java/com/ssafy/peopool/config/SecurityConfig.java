package com.ssafy.peopool.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.peopool.auth.JwtAuthenticationFilter;
import com.ssafy.peopool.auth.JwtUserDetailService;
import com.ssafy.peopool.model.service.IndividualService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    
    @Autowired
    private IndividualService individualService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.jwtUserDetailService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	// HTTP 기본 인증 비활성화
    	.httpBasic()
    		.disable()
    	
    	// 개발 편의성을 위해 CSRF 프로텍션 비활성화
        .csrf()
        	.disable()
        
        // stateless한 세션 정책 설정
        .sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
        	.and()
        
        // 유효성 체크
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), individualService))

        // 리소스 별 허용 범위 설정
        .authorizeRequests()
        
       	// 특정 경로 지정
        // 로그인, 로그아웃, 회원가입에서 접근 가능
      	.antMatchers("/auth/**","/ind/**","/ent/**","/user/interviewroom/**").permitAll()
      	
       	// 설정한 경로 외에 모든 경로를 뜻함.
      	// 설정한 도메인 외에 JWT 체크 후 접근
	    .anyRequest().authenticated() // 접근 권한 수정 필요

	    // 설정한 도메인 외에 JWT 체크 없이 모두 접근 가능
//	    .anyRequest().permitAll()
        .and().cors();
    }
}