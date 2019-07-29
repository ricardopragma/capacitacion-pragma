package co.com.pragma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.pragma.security.AuthoritiesConstants;
import co.com.pragma.service.UserService;
import co.com.pragma.service.dto.UserDTO;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {
		userService.registerUser(userDto);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/user/authorities", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<?> getAuthorities() {

		List<String> authorities = userService.getAuthorities();

		return new ResponseEntity<>(authorities, HttpStatus.OK);
	}

}
