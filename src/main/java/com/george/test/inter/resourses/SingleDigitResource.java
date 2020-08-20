package com.george.test.inter.resourses;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.george.test.inter.domain.SingleDigit;
import com.george.test.inter.dtos.SingleDigitDto;
import com.george.test.inter.services.SingleDigitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/single-digit")
@Api(value = "Single Digits")
public class SingleDigitResource {
	
	@Autowired
	private SingleDigitService service;
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Saves and single digit calculator", response = SingleDigitDto.class)
	public ResponseEntity<Void> save(@Valid @RequestBody SingleDigitDto dto) {
		SingleDigit singleDigit = service.fromDto(dto);
		singleDigit = service.save(singleDigit);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(singleDigit.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
