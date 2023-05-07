package com.jrtp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jrtp.binding.ActivateAcc;
import com.jrtp.binding.User;
import com.jrtp.entity.UserMaster;
import com.jrtp.repository.UserMasterRepo;

@Service
public class UserMasterServiceImpl implements IUserMasterService {

	@Autowired
	private UserMasterRepo userMasterRepo;
	
	Logger log = LoggerFactory.getLogger(UserMasterServiceImpl.class);
	
	@Override
	public boolean save(User user) {
		UserMaster userMaster = new UserMaster();
		BeanUtils.copyProperties(user, userMaster);
		userMaster.setPassword(generatePass(6));
		userMaster.setAccStatus("In-Active");
		UserMaster save = userMasterRepo.save(userMaster);
		
		if(save.getEmail().equals(user.getEmail())) {
			
			//TODO emal sending via mail
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<User> findAllUsers() {
	
		List<UserMaster> userMasters = userMasterRepo.findAll();
		List<User> users = new ArrayList<>();
		User user ;
		
		for(UserMaster userMaster : userMasters) {
			user = new User();
			BeanUtils.copyProperties(userMaster, user);
			users.add(user);
		}
		
		return users;
	}

	@Override
	public User findUserById(Integer id) {
		 Optional<UserMaster> option = userMasterRepo.findById(id);
		 
		 if(option.isPresent()) {
			 
			 User user = new User();
			 UserMaster userMaster = option.get();
			 BeanUtils.copyProperties(userMaster, user);
			 
			 return user;
		 }
		 
		return null;
	}

	@Override
	public String setAcctivation(ActivateAcc activateAcc) {
		
		UserMaster userMaster = new UserMaster();
		userMaster.setEmail(activateAcc.getEmail());
		userMaster.setPassword(activateAcc.getOldPass());
		
		Example<UserMaster> of = Example.of(userMaster);	
		
		List<UserMaster> findAll = userMasterRepo.findAll(of);
		
		if(findAll.isEmpty())
			return "Enter Valid email";
		else {
			userMaster = findAll.get(0);
			if(userMaster.getPassword().equals(activateAcc.getOldPass())){
				userMaster.setPassword(activateAcc.getNewPass());
				userMaster.setAccStatus("Active");
				
				userMasterRepo.save(userMaster);
				return "Account Activated";
			}else {
				return "wrong old password";
			}
		}
	}

	@Override
	public boolean deleteUserById(Integer id) {
		try {
			userMasterRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String logIn(com.jrtp.binding.logIn logIn) {
		UserMaster userMaster = findByEmail(logIn.getEmail());
		
		if(userMaster == null)
			return "email not present please register";
		
		if(userMaster.getPassword().equals(logIn.getPass())) {
			return "login sucessfull";
		}
		return "password incurrect";
	}

	@Override
	public boolean acctivateUser(Integer Id, String status) {
	Optional<UserMaster> option = userMasterRepo.findById(Id);
	
	if(option.isPresent()) {
		UserMaster userMaster = option.get();
		userMaster.setAccStatus(status);
		userMasterRepo.save(userMaster);
		
		return true;
	}
		return false;
	}

	@Override
	public String forgotPass(String email) {
		UserMaster userMaster = findByEmail(email);
		
		if(userMaster != null) {
			// code to send password via email
		}
		return "error";
	}

	@Override
	public UserMaster findByEmail(String email) {
		try {
			
			UserMaster userMaster = userMasterRepo.findByEmail(email);
			return userMaster;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
 public String generatePass(int length) {
	 
	 String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";	
	 String sym = "~`!@#$%^&*()_+-=[]{}\\|;':\",.//<>?";
	 String num = "1234567890";
	 String all = alpha+sym+num;
	 
	 int size = all.length();
	 Random ran = new Random();
	 
	 String pass = "";
	 
	 for(int i=1;i<=length;i++) {
		 pass+=all.valueOf(ran.ints(size));
	 }
	 
	return pass;
	 
 }

}
