package test_25.th;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class balanced_string1 {

	static StringBuilder sb;
	static long c1, c2, c3, c4, c5, c6;
	static char ar[] = { '(', '{', '[', ')', '}', ']' };
	static int c[] = new int[6];

	public static void main(String[] args) throws IOException {
		long res = 0, res1 = 0, res2 = 0;
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		String str[] = new String[t];
		sb = new StringBuilder("");
		for (int i = 0; i < t; i++) {
			str[i] = sc.next();
		}
		for (int i = 0; i < t; i++) {
			char[] car = str[i].toCharArray();
			Arrays.sort(car);
			String string = String.valueOf(car);
			for (int j = 0; j < 6; j++) {

				c[j] = find(string, ar[j]);

			}
			if (c[0] == c[3]) {
				res = 2 * c[0];
			} else {
				if (c[0] < c[3])
					res = 2 * c[0];
				else
					res = 2 * c[3];
			}
			if (c[1] == c[4]) {
				res1 = 2 * c[1];
			} else {
				if (c[1] < c[4])
					res1 = 2 * c[1];
				else
					res1 = 2 * c[4];
			}
			if (c[2] == c[5]) {
				res2 = 2 * c[2];
			} else {
				if (c[2] < c[5])
					res2 = 2 * c[2];
				else
					res2 = 2 * c[5];
			}
			long fres = res + res1 + res2;
			sb.append(fres + "\n");
		}
		System.out.println(sb);
	}

	private static int find(String string, char c) {
		int start = 0, end = 0, coun = 0;
		start = string.indexOf(c);
		if (start == -1) {
			start = 0;
			end = 0;
			coun = 0;
		} else {
			end = string.lastIndexOf(c);
			coun = end - start + 1;
		}
		return coun;
	}

}
