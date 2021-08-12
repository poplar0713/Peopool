package com.ssafy.peopool.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LoginRequest")
public class LoginReq {
	@ApiModelProperty(name="유저 ID", example="peopool")
	String id;
	@ApiModelProperty(name="유저 Password", example="password")
	String password;
}
