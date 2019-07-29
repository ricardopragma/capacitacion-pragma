package co.com.pragma.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.pragma.domain.Authority;
import co.com.pragma.domain.User;
import co.com.pragma.repository.AuthorityRepository;
import co.com.pragma.repository.UserRepository;
import co.com.pragma.service.UserService;
import co.com.pragma.service.dto.UserDTO;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	@Override
	public void registerUser(UserDTO userDto) {
		if (userRepository.findByLogin(userDto.getLogin()).isPresent()
				|| userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			logger.info("El usuario o email ya se encuentran registrados");
		} else {
			String passwordEncoded = passwordEncoder.encode(userDto.getPassword());
			User user = mapper.map(userDto, User.class);
			user.setPassword(passwordEncoded);
			userRepository.save(user);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> getAuthorities() {
		return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
	}

}
