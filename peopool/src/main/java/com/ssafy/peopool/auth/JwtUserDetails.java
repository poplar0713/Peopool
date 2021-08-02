package com.ssafy.peopool.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.peopool.model.Individual;


public class JwtUserDetails implements UserDetails {
	@Autowired
	Individual individual;
	
	boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    
    List<GrantedAuthority> roles = new ArrayList<>();
    
    public JwtUserDetails(Individual individual) {
    		super();
    		this.individual = individual;
    }
    
    public Individual getUser() {
    		return this.individual;
    }
	@Override
	public String getPassword() {
		return this.individual.getInd_password();
	}
	@Override
	public String getUsername() {
		return this.individual.getInd_id();
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
}
