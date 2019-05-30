package com.demo.utils;

/**
 * 字符编码名工具类
 * @author hyh
 * @date 2018/05/10
 */
public class CharSetUtil {
	
	private final static String ISO_8859_1 = "ISO-8859-1";
	
	private final static String GB2312 = "GB2312";
	
	private final static String BIG5 = "BIG5";
	
	private final static String GBK = "GBK";
	
	private final static String UNICODE = "Unicode";
	
	private final static String UCS2 = "UCS-2";
	
	private final static String UCS4 = "UCS-4";
	
	private final static String UTF8 = "UTF-8";
	
	private final static String UTF16 = "UTF-16";
	
	private final static String UTF32 = "UTF-32";

	public static String getIso88591() {
		return ISO_8859_1;
	}

	public static String getGb2312() {
		return GB2312;
	}

	public static String getBig5() {
		return BIG5;
	}

	public static String getGbk() {
		return GBK;
	}

	public static String getUnicode() {
		return UNICODE;
	}

	public static String getUcs2() {
		return UCS2;
	}

	public static String getUcs4() {
		return UCS4;
	}

	public static String getUtf8() {
		return UTF8;
	}

	public static String getUtf16() {
		return UTF16;
	}

	public static String getUtf32() {
		return UTF32;
	}
	
	/**
	 * 获取默认字符编码名，默认字符编码名为UTF-8
	 * @return ：返回UTF-8编码名
	 */
	public static String defCharSet() {
		return UTF8;
	}
}
