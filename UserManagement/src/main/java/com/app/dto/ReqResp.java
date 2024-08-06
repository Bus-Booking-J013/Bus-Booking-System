package com.app.dto;
//5.ReqResp Dto Class created

import java.util.List;

import com.app.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqResp {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshTime;
	private String expirtaionTime;
	private String firstName;
	private String lastName;
	private String password;
	private String city;
	private String role;
	private String email;
	private User user;
	private List<User> userList;
	
	
}
