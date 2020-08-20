package com.george.test.inter.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class DigitSumHelper {
	
	private static Integer result;
	
	public static Integer getDigitAndSum(Integer digit, Integer times) {
		String digitToString = String.valueOf(digit);
		
		String repeatedString = String.join("", Collections.nCopies(times, digitToString));
		
		String[] stringList = repeatedString.split("");
		
		if (stringList.length == 1) {
			return digit;
		}
		
		int [] integerList = Arrays.asList(stringList).stream().mapToInt(Integer::parseInt).toArray();
		
		result = IntStream.of(integerList).sum();
		
		return result;
	}
}
