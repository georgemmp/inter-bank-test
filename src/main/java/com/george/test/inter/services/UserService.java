package com.george.test.inter.services;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.test.inter.RSA.RSAEncryption;
import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.domain.User;
import com.george.test.inter.dtos.EncryptUserDto;
import com.george.test.inter.dtos.UserDto;
import com.george.test.inter.repositories.UserRepository;
import com.george.test.inter.services.exceptions.BadRequestException;
import com.george.test.inter.services.exceptions.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		return user.orElseThrow(() -> new NotFoundException("Id not found: " + id + ". Type: " + User.class.getName()));
	}
	
	public User save(User user) throws NoSuchAlgorithmException {
		user.setId(null);
		
		RSAEncryption rsa = new RSAEncryption();
		KeyPair keyPair = rsa.generateKeyPair();
		
		String publicKey = rsa.publicKeyToString(keyPair.getPublic());
		String privateKey = rsa.privateKeyToString(keyPair.getPrivate());
		
		user.setPublicKey(publicKey);
		user.setPrivateKey(privateKey);
		user.setCrypt(false);
		
		return this.userRepository.save(user);
	}
	
	public User update(User user) {
		this.findOne(user.getId());
		
		return this.userRepository.save(user);
	}
	
	public void delete(Integer id) {
		User user = this.findOne(id);
		for (SingleDigit item : user.getSingleDigits()) {
			item.setUser(null);
		}
		this.userRepository.deleteById(id);
	}
	
	public User encrypt(User user) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
		User getUser = this.findOne(user.getId());
		
		if (!user.getPublicKey().equals(getUser.getPublicKey())) {
			throw new BadRequestException("Bad Request: Public Key not valid");
		}
		
		RSAEncryption rsa = new RSAEncryption();
		PublicKey publicKey = rsa.stringToPublicKey(user.getPublicKey());
		
		user.setName(rsa.encrypt(getUser.getName(), publicKey));
		user.setEmail(rsa.encrypt(getUser.getEmail(), publicKey));
		user.setCrypt(true);
		
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User fromDto(UserDto dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	
	public User fromEncryptDto(EncryptUserDto dto) {
		return new User(dto.getPublicKey());
	}
}
