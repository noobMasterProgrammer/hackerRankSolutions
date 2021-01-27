package com.easy.pickingNumbers;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'pickingNumbers' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY a as parameter.
	 */

	public static int pickingNumbers(List<Integer> a) {
		int maxSize = 0;
		int flagSize = 0;
		int maxFlagSize = 0;
		boolean flag[];
		Map<Integer, Integer> occurances = new HashMap<>();
		for (Integer i : a) {
			if (!occurances.containsKey(i)) {
				occurances.put(i, 1);
			} else {
				occurances.put(i, occurances.get(i) + 1);
			}
			if (maxFlagSize < occurances.get(i)) {
				maxFlagSize = occurances.get(i);
			}
		}
		flag = new boolean[maxFlagSize + 1];
		for (Map.Entry<Integer, Integer> occurance : occurances.entrySet()) {
			flag[occurance.getValue()] = true;
			if (occurances.containsKey(occurance.getKey() + 1)) {
				int tempSize = occurance.getValue() + occurances.get(occurance.getKey() + 1);
				if (maxSize < tempSize) {
					maxSize = tempSize;
				}
			}
		}
		for (int i = 1; i <= maxFlagSize; i++) {
			if (flag[i]) {
				flagSize = i;
			}
		}
		return maxSize >= flagSize ? maxSize : flagSize;
	}
}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		int result = Result.pickingNumbers(a);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
