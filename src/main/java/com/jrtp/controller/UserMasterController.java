package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.binding.User;
import com.jrtp.service.IUserMasterService;

@RestController
public class UserMasterController {

	@Autowired
	 private IUserMasterService userMasterService;
	
	@PostMapping("/user")
	 public ResponseEntity<String> addUserMaster(@RequestBody User user){
		if (userMasterService.save(user)) 
			return new ResponseEntity<String>("saved sucessfully", HttpStatus.ACCEPTED);
		
		return new ResponseEntity<String>("saved unsucessfull",HttpStatus.BAD_REQUEST);
			
		
	}
}
