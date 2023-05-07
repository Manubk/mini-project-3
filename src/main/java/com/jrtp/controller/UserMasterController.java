package com.jrtp.controller;

import java.util.List;

import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.binding.ActivateAcc;
import com.jrtp.binding.User;
import com.jrtp.binding.logIn;
import com.jrtp.service.IUserMasterService;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import jakarta.annotation.Generated;

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
	
	@PostMapping("/activation")
	public ResponseEntity<String> activateAccount(@RequestBody  ActivateAcc activateAcc){
		String msg = userMasterService.setAcctivation(activateAcc);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody logIn logIn){
		String msg = userMasterService.logIn(logIn);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/alldetails")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> findAllUsers = userMasterService.findAllUsers();
		return new ResponseEntity<List<User>>(findAllUsers,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{uID}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer uId){
		System.out.println("deleting");
		boolean deleteUserById = userMasterService.deleteUserById(uId);
		
		if(deleteUserById)
			return new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<String>("deleted Sucessfull", HttpStatus.OK);
	}
	
	@PutMapping("/activate/{uId}/{val}")
	public ResponseEntity<String> activateStatus(@PathVariable Integer uId , @PathVariable String val){
		boolean acctivateUser = userMasterService.acctivateUser(uId, val);
		if(acctivateUser)
			return new ResponseEntity<String>("status changed ",HttpStatus.OK);
		else
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
}
