package com.cameron.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cameron.shoes.models.Shoe;
import com.cameron.shoes.models.User;


public interface ShoeRepo extends CrudRepository<Shoe, Long> {
	List<Shoe> findAll();
	List<Shoe> findAllBySeller(User currentUser);
	@Modifying
	@Query(value="DELETE FROM Shoe where id = ?")
	void deleteShoeById(long id);
	@Modifying
	@Query(value="UPDATE Shoe SET sold = true,buyer = ?2,sold_at=CURRENT_TIMESTAMP WHERE id = ?1")
	void buyShoe(Long id, User user);
	List<Shoe> findAllByBuyer(User currentUser);
	

}
