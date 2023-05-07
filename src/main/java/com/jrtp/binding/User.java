package com.jrtp.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	
	private String userName;
	private String email;
	private String phone;
	private LocalDate dob;
	private String ssn;
	private String gender;

}
