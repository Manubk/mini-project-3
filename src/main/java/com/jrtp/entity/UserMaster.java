package com. jrtp.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_MASETER")
public class UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "NAME")
	private String name ;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASS")
	private String password;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "SSN")
	private String ssn;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "CREATED_AT")
	private LocalDate createdAt;
	
	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;
	

}
