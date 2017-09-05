package com.yatou.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	private static final char BRACKET1 = '[';
	private static final char BRACKET2 = ']';
	private static final char EQUAL = '=';
	private static final char COMMA = ',';
	private static final String EMPTY = "";
	private static final String AT = "@";

	private StringUtil() {
	}

	public static String toString(Object o) {
		if (o == null) {
			return EMPTY;
		}
		Class<?> clazz = o.getClass();
		StringBuilder sb = new StringBuilder();
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			Field[] fds = superClass.getDeclaredFields();
			AccessibleObject.setAccessible(fds, true);
			for (Field field : fds) {
				fieldList.add(field);
			}
		}
		sb.append(clazz.getSimpleName());
		sb.append(AT);
		sb.append(Integer.toHexString(o.hashCode()));
		sb.append(BRACKET1);
		try {
			for (int i = 0; i < fieldList.size(); i++) {
				if (i != 0) {
					sb.append(COMMA);
				}
				Field fd = fieldList.get(i);
				fd.setAccessible(true);
				sb.append(fd.getName() + EQUAL);
				sb.append(fd.get(o));
			}
		} catch (IllegalAccessException e) {
			LOG.error("str:" + o, e);
		}
		sb.append(BRACKET2);
		return sb.toString();
	}

	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if ((cs == null) || ((strLen = cs.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 *
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 补齐不足长度,右补0
	 * @param length 长度
	 * @param number 数字
	 * @return
	 */
	public static String rpad(int length, String number) {
		Integer remainLength = length - number.length();
		StringBuilder pat = new StringBuilder(remainLength);
		for (int i = 0; i < remainLength; i++) {
			pat.append("0");
		}
		return number+pat.toString();
	}

	/**
	 * @return
	 */
	public static String decorateImageBase64(String imageStr) {
	    String image = "<img  src=\"data:image/png;base64,imageStr\" >";
		return image.replace("imageStr",imageStr);
	}
	/**
	 * 通过逗号将String分隔成List
	 *
	 * @param str  数据源
	 * @param destinationClass 分隔后的数据转换类型
	 * @return
	 */
	public static <T> List<T> splitByComma(String str,Class<T> destinationClass) {
		String[] strings = str.split(",");
		List<T> result = new ArrayList(strings.length);
		for (int i = 0; i < strings.length; i++) {
			if (strings[i] != null && strings[i] != "") {
				result.add((T)strings[i]);
			}

		}
		return result;
	}

	/**
	 * 将double转换成String（因为double在大于10位后直接调用toString方法会用科学计数法表示）
	 *
	 * @param bigDouble
	 * @return 保留2位小数的String
	 */
	public static String double2String(Double bigDouble) {

		if(bigDouble != 0.00){
			java.text.DecimalFormat df = new java.text.DecimalFormat("#########.00");
			return df.format(bigDouble);
		}else{
			return "0.00";
		}
	}
	/**
	 * 将List<T>数据根据分隔符拼接成String数据
	 * @param source     数据源
	 * @param separator  分隔符
	 * @param method  T 对象的方法名
	 * @return
	 */
	public static <T> String splice2String(List<T> source, String separator,String method){
		StringBuffer stringBuffer = new StringBuffer();
		String result = "";
		if (method == null) {
			for (T t : source) {
				stringBuffer.append(t.toString()+separator);
			}
			if (stringBuffer.length() != 0) {
				result = stringBuffer.substring(0,stringBuffer.length() - 1);
			}
			return result;
		}

		//首字母转大写
		String newStr = method.substring(0, 1).toUpperCase()+method.replaceFirst("\\w","");
		//获取需要排序字段的“get方法名”
		String methodStr = "get"+newStr;
		try {
			for (T t : source) {
				Method method1 = (t).getClass().getMethod(methodStr);
				String methodResult = method1.invoke(t, null).toString();
				stringBuffer.append(methodResult+separator);
			}
			if (stringBuffer.length() != 0) {
				result = stringBuffer.substring(0,stringBuffer.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 将List<T>数据根据分隔符拼接成String数据
	 * @param source     数据源
	 * @param separator  分隔符
	 * @return
	 */
	public static <T> String splice2String(List<T> source, String separator){
		return splice2String(source,separator,null);
	}
	/**
	 * 将List<T>数据根据默认分隔符（,）拼接成String数据
	 * @param source     数据源
	 * @return
	 */
	public static <T> String splice2String(List<T> source){
		return splice2String(source,",");
	}


	/**
	 * Capitalize a {@code String}, changing the first letter to
	 * upper case as per {@link Character#toUpperCase(char)}.
	 * No other letters are changed.
	 * @param str the {@code String} to capitalize, may be {@code null}
	 * @return the capitalized {@code String}, or {@code null} if the supplied
	 * string is {@code null}
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * Uncapitalize a {@code String}, changing the first letter to
	 * lower case as per {@link Character#toLowerCase(char)}.
	 * No other letters are changed.
	 * @param str the {@code String} to uncapitalize, may be {@code null}
	 * @return the uncapitalized {@code String}, or {@code null} if the supplied
	 * string is {@code null}
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}
	/**
	 * 下划线格式字符串转换成驼峰格式字符串
	 * eg: player_id -> playerId;<br>
	 * player_name -> playerName;
	 */
	public static String underScore2CamelCase(String strs) {

		if (strs == null || !strs.contains("_")) {
			return strs;
		}
		StringBuilder sb = new StringBuilder("");
		String[] elems = strs.split("_");
		for (int i = 0; i < elems.length; i++) {
			String elem = elems[i].toLowerCase();
			if (i != 0) {
				char first = elem.toCharArray()[0];
				sb.append((char) (first - 32)).append(elem.substring(1));
			} else {
				sb.append(elem);
			}
		}
		return sb.toString();
	}

	/**
	 * @param param 待转换字符串
	 *              驼峰格式字符串 转换成 下划线格式字符串
	 *              eg: playerId -> player_id;<br>
	 *              playerName -> player_name;
	 */
	public static String camelCase2Underscore(String param) {
		Pattern p = Pattern.compile("[A-Z]");
		if (param == null || param.equals("")) {
			return "";
		}
		StringBuilder builder = new StringBuilder(param);
		Matcher mc = p.matcher(param);
		int i = 0;
		while (mc.find()) {
			builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
			i++;
		}
		if ('_' == builder.charAt(0)) {
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		String s = "153,gh";
		List<String> strings = StringUtil.splitByComma(s, String.class);
		System.out.println(strings);
	}
}
