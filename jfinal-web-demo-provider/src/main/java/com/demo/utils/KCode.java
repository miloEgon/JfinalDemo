package com.demo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Codec utils
 */
public class KCode {

  /**
   * @return an UUID String
   */
  public static String UUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * @return an UUID String
   */
  public static String GUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /**
   * Encode a String to base64
   *
   * @param value The plain String
   * @return The base64 encoded String
   */
  public static String encodeBASE64(String value) {
    try {
      return new String(Base64.encodeBase64(value.getBytes("utf-8")));
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Encode binary data to base64
   *
   * @param value The binary data
   * @return The base64 encoded String
   */
  public static String encodeBASE64(byte[] value) {
    return new String(Base64.encodeBase64(value));
  }

  /**
   * Decode a base64 value
   *
   * @param value The base64 encoded String
   * @return decoded binary data
   */
  public static byte[] decodeBASE64(String value) {
    try {
      return Base64.decodeBase64(value.getBytes("utf-8"));
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Build an hexadecimal MD5 hash for a String
   *
   * @param value The String to hash
   * @return An hexadecimal Hash
   */
  public static String hexMD5(String value) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(value.getBytes("utf-8"));
      byte[] digest = messageDigest.digest();
      return byteToHexString(digest);
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Build an hexadecimal SHA1 hash for a String
   *
   * @param value The String to hash
   * @return An hexadecimal Hash
   */
  public static String hexSHA1(String value) {
    try {
      MessageDigest md;
      md = MessageDigest.getInstance("SHA-1");
      md.update(value.getBytes("utf-8"));
      byte[] digest = md.digest();
      return byteToHexString(digest);
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Write a byte array as hexadecimal String.
   */
  public static String byteToHexString(byte[] bytes) {
    return String.valueOf(Hex.encodeHex(bytes));
  }

  /**
   * Transform an hexadecimal String to a byte array.
   */
  public static byte[] hexStringToByte(String hexString) {
    try {
      return Hex.decodeHex(hexString.toCharArray());
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }

  public final static String MD5(String s) {
    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    try {
      byte[] btInput = s.getBytes();
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      mdInst.update(btInput);
      byte[] md = mdInst.digest();
      int j = md.length;
      char str[] = new char[j * 2];
      int k = 0;
      for ( int i = 0; i < j; i++ ) {
        byte byte0 = md[i];
        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
        str[k++] = hexDigits[byte0 & 0xf];
      }
      return new String(str);
    } catch ( NoSuchAlgorithmException e ) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 加密用方法
   *
   * @param aesKey 加密秘钥
   * @param aesIv  偏移向量
   * @param data   原数据
   * @param enc    编码格式
   * @return
   * @throws Exception
   */
  public static String AESCrypto(String aesKey, String aesIv, String data, String enc) {
    IvParameterSpec zeroIv = new IvParameterSpec(aesIv.getBytes());
    SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
      byte[] encryptedData = cipher.doFinal(data.getBytes(enc));
      return new String(Base64.encodeBase64(encryptedData));
    } catch ( NoSuchAlgorithmException e ) {
      e.printStackTrace();
    } catch ( NoSuchPaddingException e ) {
      e.printStackTrace();
    } catch ( InvalidKeyException e ) {
      e.printStackTrace();
    } catch ( InvalidAlgorithmParameterException e ) {
      e.printStackTrace();
    } catch ( IllegalBlockSizeException e ) {
      e.printStackTrace();
    } catch ( BadPaddingException e ) {
      e.printStackTrace();
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 解密
   *
   * @param aesKey 加密秘钥
   * @param aesIv  偏移向量
   * @param data   原数据
   * @param enc    编码格式
   * @return
   * @throws Exception
   */
  public static String AESDecrypt(String aesKey, String aesIv, String data, String enc) {
    byte[] byteMi = Base64.decodeBase64(data);
    IvParameterSpec zeroIv = new IvParameterSpec(aesIv.getBytes());
    SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
      byte[] decryptedData = cipher.doFinal(byteMi);
      return new String(decryptedData, enc);
    } catch ( NoSuchAlgorithmException e ) {
      e.printStackTrace();
    } catch ( NoSuchPaddingException e ) {
      e.printStackTrace();
    } catch ( InvalidKeyException e ) {
      e.printStackTrace();
    } catch ( InvalidAlgorithmParameterException e ) {
      e.printStackTrace();
    } catch ( IllegalBlockSizeException e ) {
      e.printStackTrace();
    } catch ( BadPaddingException e ) {
      e.printStackTrace();
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    }
    return null;
  }
}