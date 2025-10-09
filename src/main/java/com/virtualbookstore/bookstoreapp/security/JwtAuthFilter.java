package com.virtualbookstore.bookstoreapp.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.virtualbookstore.bookstoreapp.Services.JWTService;
import com.virtualbookstore.bookstoreapp.Services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JWTService jwtService;
	
	public JwtAuthFilter(UserDetailsServiceImpl userDetailsServiceImpl, JWTService jwtService) {
		
		this.userDetailsServiceImpl=userDetailsServiceImpl;
		this.jwtService = jwtService;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getTokenFromRequest(request);
		
		if(StringUtils.hasText(token) && jwtService.validateToken(token)) {
			
			String userName = jwtService.getUserNameFromJwt(token);
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
					);
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7);
			
		}
		
		return null;
		
	}

}
