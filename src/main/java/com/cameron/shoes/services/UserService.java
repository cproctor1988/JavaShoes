package com.cameron.shoes.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cameron.shoes.models.User;
import com.cameron.shoes.repositories.UserRepo;



@Service
public class UserService {
	private UserRepo userRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	public void saveUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    userRepo.save(user);
	    System.out.println(user);
	}
	public User findByEmail(String email) {
	    return userRepo.findByEmail(email);
	}

	public User getById(long id) {
		return userRepo.findByid(id);
		
	}
	public User findByFristName(String name) {
		return userRepo.findByFirstName(name);
	}

	
}
