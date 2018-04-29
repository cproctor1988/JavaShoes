package com.cameron.shoes.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cameron.shoes.models.Shoe;
import com.cameron.shoes.models.User;
import com.cameron.shoes.services.ShoeService;
import com.cameron.shoes.services.UserService;





@Controller
public class HomeController {
	private final UserService userService;
	private final ShoeService shoeService;
	


public HomeController(UserService userService, ShoeService shoeService) {
	this.userService = userService;
	this.shoeService = shoeService;

}


@RequestMapping("/main")
public String Home(@Valid @ModelAttribute("user") User user) {
	return "main.jsp";

}
@PostMapping("/registration")
public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
    if (result.hasErrors()) {
        return "main.jsp";
    }
    userService.saveUser(user);
    return "redirect:/home";
}
@RequestMapping(value = {"/","/home"})
public String home(Principal principal, Model model) {
	String name = principal.getName();
	User currentUser = userService.findByFristName(name);
    model.addAttribute("currentUser", currentUser);
    model.addAttribute("notsold", shoeService.findAllByUserNotSold(currentUser));
    model.addAttribute("sold", shoeService.findAllByUserSold(currentUser));
    model.addAttribute("bought", shoeService.findAllByBuyer(currentUser));
    return "dashboard.jsp";

}
@PostMapping("/sell")
public String sell(@RequestParam("name")String name,@RequestParam ("amount")Long amount,@RequestParam ("user_id") String user_id) {
	User seller = userService.getById(Long.parseLong(user_id));
	Shoe shoeobj = new Shoe();
	shoeobj.setAmount(amount);
	shoeobj.setName(name);
	shoeobj.setSeller(seller);
	shoeService.sell(shoeobj);
	return "redirect:/home";
}

@RequestMapping("/products")
public String allProducts(Principal principal, Model model) {
	String name = principal.getName();
	User currentUser = userService.findByFristName(name);
    model.addAttribute("currentUser", currentUser);
	model.addAttribute("shoes", shoeService.findAllNotSold());
	return "shoes.jsp";
	}
@PostMapping("/remove/{id}")
public String removeShoe(@PathVariable String id) {
	shoeService.removeShoe(Long.parseLong(id));
	
	return "redirect:/home";
}
@PostMapping("/buy/{id}")
public String buyShoe(@PathVariable String id,Principal principal) {
	String name = principal.getName();
	User currentUser = userService.findByFristName(name);
	shoeService.buyShoe(Long.parseLong(id),currentUser);
	return "redirect:/products";
}
}









