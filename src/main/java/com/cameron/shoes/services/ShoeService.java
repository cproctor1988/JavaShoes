package com.cameron.shoes.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.cameron.shoes.models.Shoe;
import com.cameron.shoes.models.User;
import com.cameron.shoes.repositories.ShoeRepo;

@Service
public class ShoeService {
	private ShoeRepo shoeRepo;
	
	public ShoeService(ShoeRepo shoeRepo) {
		this.shoeRepo = shoeRepo;
	}
	public List<Shoe> allShoes(){
		return shoeRepo.findAll();
	}
	
	public void sell(Shoe shoeobj) {
		shoeRepo.save(shoeobj);
		
	}
	public Object findAllByUserNotSold(User currentUser) {
		List<Shoe> forsale = shoeRepo.findAllBySeller(currentUser);
		int x = 0;
		while(x < forsale.size()) {
			if(forsale.get(x).isSold() == true){
				forsale.remove(x);
			}
			else {
				x++;
			}
				
		}
		return forsale;
	}
	public Object findAllByUserSold(User currentUser) {
		List<Shoe> forsale = shoeRepo.findAllBySeller(currentUser);
		int x = 0;
		while(x < forsale.size()) {
			if(forsale.get(x).isSold() == false){
			forsale.remove(x);
			}
			else {
			x++;
			}		
		}
	return forsale;
	}
	public Object findAllNotSold() {
		
		List<Shoe> forsale = shoeRepo.findAll();
		int x = 0;
		while(x < forsale.size()) {
			if(forsale.get(x).isSold() == true){
				forsale.remove(x);
			}
			else {
				x++;
			}
				
		}
		return forsale;
	}
	@Transactional
	public void removeShoe(Long id) {
		shoeRepo.deleteShoeById(id);
	}
	@Transactional
	public void buyShoe(Long id, User user) {
		
		shoeRepo.buyShoe(id,user);
		
		
	}
	public Object findAllByBuyer(User buyer) {
		return shoeRepo.findAllByBuyer(buyer);
	}
			
}
