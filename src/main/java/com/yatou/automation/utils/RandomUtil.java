package com.yatou.automation.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 工具类-随机数
 * 
 * @author xx
 * @version 1.0
 */
public class RandomUtil {

	/**
	 * 生成指定位数的随机数
	 *
	 * @param digits 随机数位数
	 * @return
     */
	public static String code(int digits) {
		List<Integer> list = getRandomNumber(digits);
		Iterator<Integer> iterator = list.iterator();
		StringBuffer temp = new StringBuffer(digits);
		while (iterator.hasNext()) {
			temp.append(iterator.next());
		}
		return temp.toString();
	}

	private static List<Integer> getRandomNumber(int digits) {
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		while (list.size() < digits) {
			int randomInt = random.nextInt(10);
			list.add(randomInt);
		}
		return list;
	}

	public static void main(String[] args) {
		String code = RandomUtil.code(10);
		System.out.println(code);
	}
}
