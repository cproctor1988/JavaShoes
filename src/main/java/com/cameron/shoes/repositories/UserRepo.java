package com.cameron.shoes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cameron.shoes.models.User;



public interface UserRepo extends CrudRepository<User, Long> {
	List<User> findAll();
	
	User findByEmail(String email);
	User findByid(Long id);

	User findByFirstName(String name);
}
