package com.jrtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.jrtp.entity.UserMaster;

@Repository
public interface UserMasterRepo extends JpaRepository<UserMaster, Integer> {

	public UserMaster findByEmail(String email);
}
