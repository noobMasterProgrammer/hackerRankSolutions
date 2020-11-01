package com.easy.electronicsShop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	/*
	 * Complete the getMoneySpent function below.
	 */
	static int getMoneySpent(int[] keyboards, int[] drives, int b) {// takes less time and less space
		int keyboardsSize = keyboards.length;
		int drivesSize = drives.length;
		boolean isSelected = false;
		sort(keyboards, 0, keyboards.length - 1);
		sort(drives, 0, drives.length - 1);
		List<Integer> list = new ArrayList<>();
		if (drives[0] + keyboards[0] > b) {
			return -1;
		}
		for (int i = keyboardsSize - 1; i >= 0; i--) {
			isSelected = false;
			for (int j = drivesSize - 1; j >= 0; j--) {
				if ((keyboards[i] + drives[j]) <= b && !isSelected) {
					isSelected = true;
					list.add(keyboards[i] + drives[j]);
				}
			}
		}
		Collections.sort(list);
		return list.get(list.size() - 1);
	}

	static void merge(int arr[], int beg, int mid, int end) {

		int l = mid - beg + 1;
		int r = end - mid;

		int leftArray[] = new int[l];
		int rightArray[] = new int[r];

		for (int i = 0; i < l; ++i)
			leftArray[i] = arr[beg + i];

		for (int j = 0; j < r; ++j)
			rightArray[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = beg;
		while (i < l && j < r) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}
		while (i < l) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		while (j < r) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	static void sort(int arr[], int beg, int end) {
		if (beg < end) {
			int mid = (beg + end) / 2;
			sort(arr, beg, mid);
			sort(arr, mid + 1, end);
			merge(arr, beg, mid, end);
		}
	}

	static int getMoneySpent2(int[] keyboards, int[] drives, int b) {// time Complexity is high
		int keyboardsSize = keyboards.length;
		int drivesSize = drives.length;
		List<Integer> list = new ArrayList<>();
		for (int i = keyboardsSize - 1; i >= 0; i--) {
			for (int j = drivesSize - 1; j >= 0; j--) {
				if ((keyboards[i] + drives[j]) <= b) {
					list.add(keyboards[i] + drives[j]);
				}
			}
		}
		if (!list.isEmpty()) {
			Collections.sort(list);
			return list.get(list.size() - 1);
		} else {
			return -1;
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] bnm = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		int b = Integer.parseInt(bnm[0]);

		int n = Integer.parseInt(bnm[1]);

		int m = Integer.parseInt(bnm[2]);

		int[] keyboards = new int[n];

		String[] keyboardsItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		for (int keyboardsItr = 0; keyboardsItr < n; keyboardsItr++) {
			int keyboardsItem = Integer.parseInt(keyboardsItems[keyboardsItr]);
			keyboards[keyboardsItr] = keyboardsItem;
		}

		int[] drives = new int[m];

		String[] drivesItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		for (int drivesItr = 0; drivesItr < m; drivesItr++) {
			int drivesItem = Integer.parseInt(drivesItems[drivesItr]);
			drives[drivesItr] = drivesItem;
		}

		/*
		 * The maximum amount of money she can spend on a keyboard and USB drive, or -1
		 * if she can't purchase both items
		 */

		int moneySpent = getMoneySpent(keyboards, drives, b);

		bufferedWriter.write(String.valueOf(moneySpent));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
