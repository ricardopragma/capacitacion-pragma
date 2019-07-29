package co.com.pragma.service;

import java.util.List;

import co.com.pragma.service.dto.UserDTO;

public interface UserService {

	void registerUser(UserDTO userDto);

	List<String> getAuthorities();
	
}
