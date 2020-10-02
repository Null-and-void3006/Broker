package com.Tim401_6.Broker.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Tim401_6.Broker.services.MyUserDetailsService;
import com.Tim401_6.Broker.utils.JwtUtil;



@Component
public class JwcRequestFilter extends OncePerRequestFilter{

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//we need to examine the received JWT and see if it is valid
		//if so, we will save it in the security context

		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		
		 if (authorizationHeader != null && authorizationHeader.startsWith(("Bearer ")))
				 {
			 		jwt = authorizationHeader.substring(7);
			 		username = jwtUtil.extractUsername(jwt);
				 }
		 
		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		 {
			 UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			  if (jwtUtil.validateToken(jwt, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
		 }

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(request, response);
		}
	}
	

}
