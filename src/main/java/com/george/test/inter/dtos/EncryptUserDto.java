package com.george.test.inter.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class EncryptUserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Public key is required")
	private String publicKey;

	public EncryptUserDto() {
		super();
	}

	public EncryptUserDto(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
