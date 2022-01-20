package com.asianfo.springsamples;

import com.sitech.desc.crypto.api.CryptoManager;
import com.sitech.desc.crypto.api.DefaultCryptoManager;

import java.net.URL;

/** 
* @author 作者 Your-Name: tony
* @version 创建时间：2021年9月29日 下午5:08:27 
* 类说明 
*/
/**
 * @author tony
 * @date 2021年9月29日
 *
 */
public class DBPasswordCoder {
	
	/**
	 *  加密
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String password) {
		String result = null;
		try {
			CryptoManager clientCryptoManager = new DefaultCryptoManager();
	        clientCryptoManager.init();
	        
	        result = clientCryptoManager.encryptBySM4("SM4_sjzc", password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 解密
	 * @param encryptStr
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptStr) {
		String result = null;
		try {
			CryptoManager clientCryptoManager = new DefaultCryptoManager();
	        clientCryptoManager.init();
	        
	        result = clientCryptoManager.decryptBySM4("SM4_sjzc", encryptStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static void main(String[] args) {
		String str = encrypt("abc");
		System.out.println(str);
	}
}
