package com.nagarro.statements.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.nagarro.statements.constant.Constants;
import com.nagarro.statements.exception.ExceptionResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
/**
 * @author 
 * Danish Ansari
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {


	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public static String role;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		logger.debug("inside doFilterInternal method");
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				jwtTokenUtil.isTokenExpired(jwtToken);
				Claims  claims =jwtTokenUtil.getAllClaimsFromToken(jwtToken);
				role=claims.get("ROLE").toString();
				chain.doFilter(request, response);

			} catch (IllegalArgumentException e) {
				logger.warn("Unable to get JWT Token");
				response(response);
			} catch (ExpiredJwtException e) {
				logger.warn("JWT Token has expired");
				response(response);
			}catch (SignatureException e) {
				logger.warn("Invalid JWT Token ");
				response(response);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			response(response);
		}
	}

	void response( HttpServletResponse res) throws IOException{
		ExceptionResponse resp=new ExceptionResponse();
		resp.setErrorMessage(Constants.UNAUTHORIZED_ACCESS);
		resp.setStatus(HttpStatus.UNAUTHORIZED.value());

		Gson json=new Gson();

		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		res.setContentType("application/json");
		res.getWriter().write(json.toJson(resp));
		return; 
	}

}
