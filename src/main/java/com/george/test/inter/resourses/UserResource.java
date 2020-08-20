package com.george.test.inter.resourses;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.domain.User;
import com.george.test.inter.dtos.EncryptUserDto;
import com.george.test.inter.dtos.SingleDigitDto;
import com.george.test.inter.dtos.UserDto;
import com.george.test.inter.services.SingleDigitService;
import com.george.test.inter.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
@Api(value = "Users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private SingleDigitService singleDigitService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find user by id", response = User.class)
	public ResponseEntity<User> findOne(@PathVariable Integer id) {
		User user = service.findOne(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "List all users", response = User[].class)
	public ResponseEntity<List<User>> findAll() {
		List<User> user = service.findAll();
		
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Save user", response = User.class)
	public ResponseEntity<Void> save(@Valid @RequestBody UserDto dto) throws NoSuchAlgorithmException {
		User user = service.fromDto(dto);
		user = service.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Edit user", response = User.class)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDto dto, @PathVariable Integer id) {
		User user = service.fromDto(dto);
		user.setId(id);
		user = service.update(user);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete user", response = User.class)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "{id}/single-digits", method = RequestMethod.GET)
	@ApiOperation(value = "Find single digits by user id", response = SingleDigitDto[].class)
	public ResponseEntity<List<SingleDigit>> findUserSingleDigits(@PathVariable Integer id) {
		List<SingleDigit> singleDigits = singleDigitService.userSingleDigits(id);
		return ResponseEntity.ok().body(singleDigits);
	}
	
	@RequestMapping(value = "{id}/encrypt", method = RequestMethod.PUT)
	@ApiOperation(value = "Encrypt user data", response = User.class)
	public ResponseEntity<Void> encrypt(@PathVariable Integer id, @RequestBody EncryptUserDto dto) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
		User user = service.fromEncryptDto(dto);
		
		user.setId(id);
		user = service.encrypt(user);
		
		return ResponseEntity.noContent().build();
	}
}
