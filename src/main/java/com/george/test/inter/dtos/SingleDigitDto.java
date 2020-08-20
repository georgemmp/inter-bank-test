package com.george.test.inter.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SingleDigitDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	private Integer id;

	@NotNull(message = "Digit is required")
	private Integer digit;

	@NotNull(message = "Time to repeat is required")
	private Integer times;

	private Integer userId;

	public SingleDigitDto(Integer id, @NotNull(message = "Digit is required") Integer digit,
			@NotNull(message = "Time to repeat is required") Integer times, Integer userId) {
		super();
		this.id = id;
		this.digit = digit;
		this.times = times;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDigit() {
		return digit;
	}

	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
