package com.easy.dayOfTheProgrammer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	// Complete the dayOfProgrammer function below.
	static String dayOfProgrammer(int year) {
		boolean leap = false;
		if (year == 1918) {
			return "26.09.1918";
		}
		if (year % 4 == 0) {
			if (year >= 1700 && year <= 1917) {
				leap = true;
			} else {
				if (year % 100 == 0) {
					if (year % 400 == 0) {
						leap = true;
					} else {
						leap = false;
					}
				} else {
					leap = true;
				}
			}

		} else {
			leap = false;
		}
		if (leap) {
			return "12.09." + year;
		}
		return "13.09." + year;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int year = Integer.parseInt(bufferedReader.readLine().trim());

		String result = dayOfProgrammer(year);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
