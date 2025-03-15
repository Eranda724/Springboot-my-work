package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	@Autowired
	UserService us;
	
	public boolean passwordCheck(String p) {
	    int l = p.length();
	    int cap=0,sml=0,num=0;
	    for(int i=0;i<l;i++) {
	    	if(p.charAt(i)>='a' && p.charAt(i)<='z')
	    		sml = 1;
	    	if(p.charAt(i)>='A' && p.charAt(i)<='Z')
	    		cap=1;
	    	if(p.charAt(i)>='0' && p.charAt(i)<='9')
	    		num=1;
	    }
	    if(cap==1 && sml==1 && num==1)
	    	return true;
	    return false;
	}
	@GetMapping("/register")
	public String register() {
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public RedirectView registerform(@Param(value="email") String email, @Param(value="name") String name, @Param(value="pass") String pass,RedirectAttributes r) {
		User u = new User();
		if(!passwordCheck(pass)) {
			System.out.println("Weak Password"); r.addFlashAttribute("msg", "Weak Password");
			return new RedirectView("/register");
		}
		u.setEmail(email);
		u.setName(name);
		u.setPass(pass);
		us.saveUser(u);
		r.addFlashAttribute("name","the name of user");
		return new RedirectView("/register");
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public RedirectView loginform(@Param(value="email") String email, @Param(value="pass") String pass,RedirectAttributes r, HttpSession hs) {
		User u = us.finduser(email);
		if(u==null) {
			r.addFlashAttribute("msg", "No email exists");
			return new RedirectView("/login");
		}
		if(u.getPass().equals(pass)) {
			    hs.setAttribute("userobj", u);
				return new RedirectView("/dashboard");
		}
		r.addFlashAttribute("msg", "Password incorrect");
		return new RedirectView("/login");
	}
	
	@GetMapping("/dashboard")
	public String dahsboard(HttpSession hs) {
		if(hs.getAttribute("userobj")==null) {
			return "redirect:/login";
		}
		return "dashboard.jsp";
	}
}
