package com.app.config;

import com.app.identity.TokenUser;
import com.app.identity.TokenUtil;

import static com.app.model.response.OperationResponse.*;

import com.app.model.session.SessionItem;
import com.app.model.session.SessionResponse;

import com.app.repo.UserRepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** This filter maps to /session and tries to validate the username and password */
@Slf4j
public class GenerateTokenForUserFilter extends AbstractAuthenticationProcessingFilter
{
	//~ Instance fields --------------------------
	/**  */
	private TokenUtil tokenUtil;

	/**  */
	@Autowired
	private UserRepo userRepo;
	//~ Constructors -----------------------------
	/**
	 * Creates a new GenerateTokenForUserFilter object.
	 *
	 * @param  urlMapping
	 * @param  authenticationManager
	 * @param  tokenUtil
	 */
	protected GenerateTokenForUserFilter(String urlMapping, AuthenticationManager authenticationManager,
		TokenUtil tokenUtil)
	{
		super(new AntPathRequestMatcher(urlMapping));
		setAuthenticationManager(authenticationManager);
		this.tokenUtil = tokenUtil;
	}
	//~ Methods ----------------------------------
	/**
	 * @see  org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 *       javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException, IOException, ServletException, JSONException
	{
		try
		{
			String jsonString = IOUtils.toString(request.getInputStream(), "UTF-8");

			/* using org.json */
			JSONObject userJSON = new JSONObject(jsonString);
			String username = userJSON.getString("username");
			String password = userJSON.getString("password");
			String browser = (request.getHeader("User-Agent") != null) ? request.getHeader("User-Agent") : "";
			String ip = request.getRemoteAddr();
			log.info("\nip:{} \nbrowser:{} \n----", ip, browser);
			// final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken("demo",
			// "demo");
			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
					password);

			return getAuthenticationManager().authenticate(authToken); // This will take to successfulAuthentication or
																	   // faliureAuthentication function
		}
		catch (JSONException | AuthenticationException e)
		{
			throw new AuthenticationServiceException(e.getMessage());
		}
	}

	/**
	 * @see  org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest,
	 *       javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
	 *       org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
		Authentication authToken) throws IOException, ServletException
	{
		SecurityContextHolder.getContext().setAuthentication(authToken);
		/*
		 * JSONObject jsonResp = new JSONObject(); TokenUser tokenUser = (TokenUser)authToken.getPrincipal(); String
		 * newToken = this.tokenUtil.createTokenForUser(tokenUser);
		 *
		 * jsonResp.put("token",newToken); jsonResp.put("firstName",tokenUser.getUser().getFirstName());
		 * jsonResp.put("lastName",tokenUser.getUser().getLastName());
		 * jsonResp.put("email",tokenUser.getUser().getEmail()); jsonResp.put("role",tokenUser.getRole());
		 */

		TokenUser tokenUser = (TokenUser) authToken.getPrincipal();
		SessionResponse resp = new SessionResponse();
		SessionItem respItem = new SessionItem();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String tokenString = this.tokenUtil.createTokenForUser(tokenUser);

		respItem.setFirstName(tokenUser.getUser().getFirstName());
		respItem.setLastName(tokenUser.getUser().getLastName());
		respItem.setUserId(tokenUser.getUser().getUserId());
		respItem.setEmail(tokenUser.getUser().getEmail());
		respItem.setToken(tokenString);

		resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
		resp.setOperationMessage("Login Success");
		resp.setItem(respItem);
		String jsonRespString = ow.writeValueAsString(resp);

		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().write(jsonRespString);
		// res.getWriter().write(jsonResp.toString());
		res.getWriter().flush();
		res.getWriter().close();

		// DONT call supper as it contine the filter chain super.successfulAuthentication(req, res, chain, authResult);
	}
}
