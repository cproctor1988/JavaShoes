package com.cameron.shoes.models;



import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;







@Entity
public class Shoe {
	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	public String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User seller;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buyer_id")
	private User buyer;
	
	@Column(updatable=false)
    private Date createdAt;
	@Column 
	private Date soldAt;
	@Column
	private Long amount;
	
	@Column
	private boolean sold = false;
	


	public Shoe() {
		
	}



	public Long getId() {
		return id;
	}



	public Date getSoldAt() {
		return soldAt;
	}



	public void setSoldAt(Date soldAt) {
		this.soldAt = soldAt;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	



	public User getSeller() {
		return seller;
	}



	public void setSeller(User seller) {
		this.seller = seller;
	}



	public User getBuyer() {
		return buyer;
	}



	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Long getAmount() {
		return amount;
	}



	public void setAmount(Long amount) {
		this.amount = amount;
	}



	public boolean isSold() {
		return sold;
	}



	public void setSold(boolean sold) {
		this.sold = sold;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();

    }
    @PreUpdate
    protected void onUpdate(){
        this.soldAt = new Date();
    }
	
	
	
}
