package com.easy.betweenTwoSets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'getTotalX' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts following
	 * parameters: 1. INTEGER_ARRAY a 2. INTEGER_ARRAY b
	 */

	public static int getTotalX(List<Integer> a, List<Integer> b) {
		int count = 0;
		for (int i = a.get(a.size() - 1); i <= b.get(0); i++) {
			if (allFactor(a, i) && allDivisor(b, i)) {
				count++;
			}
		}
		return count;
	}

	private static boolean allFactor(List<Integer> list, int factor) {
		return list.size() == list.parallelStream().filter(item -> (factor % item) == 0).collect(Collectors.toList())
				.size();
	}

	private static boolean allDivisor(List<Integer> list, int divisor) {
		return list.size() == list.parallelStream().filter(item -> (item % divisor) == 0).collect(Collectors.toList())
				.size();
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int total = Result.getTotalX(arr, brr);

		bufferedWriter.write(String.valueOf(total));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
