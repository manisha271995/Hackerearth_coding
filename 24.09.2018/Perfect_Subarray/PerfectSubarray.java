package Coding;

import java.util.Scanner;

public class Perfect_Subarray {
	static int n;
	static long arr[], sum_arr[];
	static long query = 0, sum = 0, sum1 = 0, count = 0;

	public static void main(String[] ar) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		arr = new long[n];
		sum_arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextLong();
			sum_arr[i] = sum + arr[i];
			sum = sum + arr[i];
		}
		answer();
		System.out.println(count);
	}

	private static void answer() {
		for (int j = 0; j < n; j++) {
			if (query == 0) {
				if (sum_arr[j] != 0 && isPerfectSquare(sum_arr[j])) {
					count++;
				}
				if (j == n - 1) {
					query++;
					j = 0;
				}
			} else {
				for (int k = j; k < n; k++) {
					sum_arr[k] = sum_arr[k] - arr[j - 1];
					if (sum_arr[k] != 0 && isPerfectSquare(sum_arr[k])) {
						count++;
					}
				}
			}
		}
	}

	static boolean isPerfectSquare(long x) {
		long sqrt = (long) Math.sqrt(x);
		if (sqrt * sqrt == x) {
			return true;
		} else {
			return false;
		}
	}
}
