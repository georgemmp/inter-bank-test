package com.george.test.inter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.domain.User;
import com.george.test.inter.dtos.SingleDigitDto;
import com.george.test.inter.repositories.SingleDigitRepository;
import com.george.test.inter.utils.Util;

@Service
public class SingleDigitService {
	
	@Autowired
	private SingleDigitRepository singleDigitRepository;
	
	@Autowired
	private UserService userService;
	
	private Util util;
	
	public SingleDigit save(SingleDigit singleDigit) {
		singleDigit.setId(null);
		
		util = Util.getUnique();
		
		util.addInCache(singleDigit);
		
		return this.singleDigitRepository.save(singleDigit);
	}
	
	public List<SingleDigit> userSingleDigits(Integer userId) {
		userService.findOne(userId);
		List<SingleDigit> singleDigits = singleDigitRepository.findUserSingleDigits(userId);
		return singleDigits;
	}
	
	public SingleDigit fromDto(SingleDigitDto dto) {
		if (dto.getUserId() != null) {
			User user = userService.findOne(dto.getUserId());
			return new SingleDigit(dto.getId(), dto.getDigit(), dto.getTimes(), user);
		} 

		return new SingleDigit(dto.getId(), dto.getDigit(), dto.getTimes(), null);
		
	}
}
