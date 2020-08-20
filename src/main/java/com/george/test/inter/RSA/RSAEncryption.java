package com.george.test.inter.RSA;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;

public class RSAEncryption {
	
	public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		
		KeyPair keyPair = generator.generateKeyPair();
		
		return keyPair;
	}
	
	public String encrypt(String string, PublicKey publicKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		return new String(cipher.doFinal(string.getBytes()));
	}
	
	public String decrypt(String string, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return new String(cipher.doFinal(string.getBytes()));
	}
	
	public String publicKeyToString(PublicKey publicKey) {
		byte[] encodePublicKey = publicKey.getEncoded();
		String pubKey = java.util.Base64.getEncoder().encodeToString(encodePublicKey);
		
		return pubKey;
	}
	
	public String privateKeyToString(PrivateKey privateKey) {
		byte[] encodePrivateKey = privateKey.getEncoded();
		String privKey = java.util.Base64.getEncoder().encodeToString(encodePrivateKey);
		
		return privKey;
	}
	
	public PublicKey stringToPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		
		return pubKey;
	}
	
	public PrivateKey stringToPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] privateBytes = Base64.decodeBase64(privateKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privateBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privKey = keyFactory.generatePrivate(keySpec);
		
		return privKey;
	}
}
