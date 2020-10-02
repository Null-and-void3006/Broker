package com.Tim401_6.Broker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Tim401_6.Broker.filters.JwcRequestFilter;
import com.Tim401_6.Broker.services.MyUserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwcRequestFilter jwcRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(myUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		
		//we need to disable any authentication for the /authentication endpoint
		//but to use authentication for any others
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate", "/register-user").permitAll().anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors();
		
		//also, because we are using jwc, we do not want to remember states, thus sessionCreationPolicy is 
		//stateless, which is the very point of jwc
		
		
		//Because we are not remembering states
		//there must exist a mechanism that will enable secure use of our broker, and that
		//is achieved by using jwc with our filter

		http.addFilterBefore(jwcRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance(); 
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.unmodifiableList(Arrays.asList("*")));
		configuration.setAllowedMethods(Collections.unmodifiableList(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE", "PATCH")));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Collections.unmodifiableList(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
