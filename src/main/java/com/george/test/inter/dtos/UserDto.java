package com.george.test.inter.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	private Integer id;

	@NotEmpty(message = "Name field is required")
	private String name;

	@NotEmpty(message = "E-mail field is required")
	@Email(message = "Enter a valid email")
	private String email;

	public UserDto() {
	}

	public UserDto(Integer id, @NotEmpty(message = "Name field is required") String name,
			@NotEmpty(message = "E-mail field is required") @Email(message = "Enter a valid email") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
