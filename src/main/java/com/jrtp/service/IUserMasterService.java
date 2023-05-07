package com.jrtp.service;

import java.util.List;

import com.jrtp.binding.ActivateAcc;
import com.jrtp.binding.User;
import com.jrtp.binding.logIn;
import com.jrtp.entity.UserMaster;

public interface IUserMasterService {
	public boolean save(User user);
	public List<User> findAllUsers();
	public User findUserById(Integer id);
	public String setAcctivation(ActivateAcc activateAcc);
	public boolean deleteUserById(Integer id);
	public String logIn(logIn logIn);
	public boolean acctivateUser(Integer Id , String status);
	public String forgotPass(String email);
	public UserMaster findByEmail(String email);
	
	
}
