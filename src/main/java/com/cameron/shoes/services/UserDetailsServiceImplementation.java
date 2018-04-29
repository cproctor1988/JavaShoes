package com.cameron.shoes.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cameron.shoes.models.User;
import com.cameron.shoes.repositories.UserRepo;



@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private UserRepo userRepo;
    
    public UserDetailsServiceImplementation(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("test");
        System.out.println(username);
        User user = userRepo.findByEmail(username);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("test");
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), getAuthorities(user));
    }
    private List<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
            authorities.add(grantedAuthority);
        
        return authorities;
    }
}
