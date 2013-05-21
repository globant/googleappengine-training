package com.globant.gaetraining.addsincgae.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.globant.gaetraining.addsincgae.services.UserService;

public class RoleFilter implements Filter {
	
	private String role;
	
	@Autowired
	UserService userService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Principal principal = request.getUserPrincipal();
		
		if(principal==null){
			throw new SecurityException("Illegal access, you must be logged in!");
		}
				
		if(userService.getUserRoles(principal.getName()).contains(role)){
			chain.doFilter(req, resp);
			}
		else{
			throw new SecurityException("User "+principal+" has not the requiered role "+role);
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		role = filterConfig.getInitParameter("role");
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
