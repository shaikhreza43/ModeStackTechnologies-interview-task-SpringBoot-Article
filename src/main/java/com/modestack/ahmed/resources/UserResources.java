package com.modestack.ahmed.resources;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modestack.ahmed.models.ArticleDto;
import com.modestack.ahmed.models.AuthenticationResponse;
import com.modestack.ahmed.models.LoginDto;
import com.modestack.ahmed.models.UserDto;
import com.modestack.ahmed.services.ArticleService;
import com.modestack.ahmed.services.UserService;
import com.modestack.ahmed.util.JwtUtil;

@Controller
@RequestMapping("/api")
public class UserResources {

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto login) throws Exception {

		System.out.println("Authenticate API called...");

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
		} catch (BadCredentialsException e) {

			throw new Exception("Incorrect username or password", e);

		}

		final UserDetails userDetails = userService.loadUserByUsername(login.getUserName());

		final String accessToken = JwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(accessToken, 200, "Success"));
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) {

		System.out.println("Inside saveUser method...");
		if (user != null) {
			UserDto responseFromService = userService.saveUser(user);

			 JSONObject obj = new JSONObject();
			 obj.put("statusCode", "201");
			 obj.put("message", "New User Created");
			
			 JSONArray data = new JSONArray();
			 data.put(obj);
			
			 JSONObject json = new JSONObject();
			 json.put("response", data);
			 System.out.println(json);

			return ResponseEntity.ok(new AuthenticationResponse(null, 201, "New User Created"));

		} else {
			 JSONObject obj = new JSONObject();
			 obj.put("statusCode", "401");
			 obj.put("message", "Bad Request");
			
			 JSONArray data = new JSONArray();
			 data.put(obj);
			
			 JSONObject json = new JSONObject();
			 json.put("response", data);
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
	}

	@RequestMapping(value = "/create-article", method = RequestMethod.POST)
	public ResponseEntity<?> createArticle(@RequestBody ArticleDto article) {

		System.out.println(article);
		if (article != null) {
			ArticleDto response = articleService.createArticle(article);
			return ResponseEntity.ok(new AuthenticationResponse(null, 201, "New Article Created"));
		} else {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
	}

	@RequestMapping(value = "/list-articles", method = RequestMethod.GET)
	public ResponseEntity<?> listAllArticles() {

		List<ArticleDto> articles = articleService.fetchAllArticles();
		System.out.println(articles);

		return new ResponseEntity(articles, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/list-articles/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<?> listPaginatedArticles(@PathVariable(name="pageNo") int pageNo,@PathVariable(name="pageSize") int pageSize) {
		
		System.out.println(pageNo+" "+pageSize);
		List<ArticleDto> paginatedArticles = articleService.fetchPaginatedArticles(pageNo, pageSize);
		System.out.println(paginatedArticles);

		return new ResponseEntity(paginatedArticles, HttpStatus.OK);

	}
}
