package com.web.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class SecurityUtil {
	/***
	 * MD5 加密
	 */
	public static String MD5(String str) {
		if (str == null)
			return null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte[] digest = md5.digest();
			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// ==Base64加解密=================================================================
	/**
	 * Base64加密
	 */
	public static String Base64Encode(String str) throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}

	/**
	 * 解密
	 */
	public static String Base64Decode(String str) throws UnsupportedEncodingException, IOException {
		// str = str.replaceAll(" ", "+");
		return new String(new BASE64Decoder().decodeBuffer(str), "UTF-8");
	}

	// ==Aes加解密=================================================================
	/**
	 * aes解密-128密
	 */
	public static String unlockdes(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(sSrc);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * aes加密-128密
	 */
	public static String lockdes(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	/**
	 * 将byte[] 转换成字符串
	 */
	public static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0x00ff & b);
			hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
		}
		return hexRetSB.toString();
	}

	/**
	 * 16进制字符串转为转换成字符
	 */
	public static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}

	/**
	 * DES加密
	 */
	public static String desEncrypt(String source, String desKey) throws Exception {
		try {
			// 从原始密匙数据创建DESKeySpec对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			// 现在，获取数据并加密
			byte[] destBytes = cipher.doFinal(source.getBytes());
			StringBuilder hexRetSB = new StringBuilder();
			for (byte b : destBytes) {
				String hexString = Integer.toHexString(0x00ff & b);
				hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
			}
			return hexRetSB.toString();
		} catch (Exception e) {
			throw new Exception("DES加密发生错误", e);
		}
	}

	/**
	 * DES解密
	 */
	public static String desDecrypt(String source, String desKey) throws Exception {
		// 解密数据
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			// 现在，获取数据并解密
			byte[] destBytes = cipher.doFinal(sourceBytes);
			return new String(destBytes);
		} catch (Exception e) {
			throw new Exception("DES解密发生错误", e);
		}
	}

	/**
	 * 3DES加密
	 */
	public static byte[] threeDesEncrypt(byte[] src, byte[] keybyte) throws Exception {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 加密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES加密发生错误", e);
		}
	}

	/**
	 * 3DES解密
	 */
	public static byte[] threeDesDecrypt(byte[] src, byte[] keybyte) throws Exception {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 解密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES解密发生错误", e);
		}
	}

	/**
	 * 加密3des
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String addlock(String src, String key) throws Exception {
		return byte2Hex(threeDesEncrypt(src.getBytes(), key.getBytes()));
	}

	/**
	 * 3DES解密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String unlock(String src, String key) throws Exception {
		return new String(threeDesDecrypt(hex2Bytes(src), key.getBytes()));
	}

	/**
	 * 
	 * 
	 * AES 128加密 解密 demo
	 * 
	 */

}
