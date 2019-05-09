import java.util.Arrays;
import java.util.Scanner;

class a {

	private static int count;

	public static void main(String args[]) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ar[] = new int[n];
		long sum[] = new long[n];
		for (int i = 0; i < n; i++) {
			ar[i] = sc.nextInt();
			if (i == 0) {
				sum[i] = ar[i];
			} else {
				sum[i] = sum[i - 1] + ar[i];
			}
		}
		System.out.println(Arrays.toString(sum));
		int index = 0;
		long totalsum = sum[n - 1];
		long constotalsum = totalsum;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < sum.length; j++) {
				double val = totalsum - sum[j];
				if (val != 0) {
					System.out.println("{");
					System.out.println("  val is " + val);
					double avg = val / (n - (j + 1));
					System.out.println("  avg is " + avg);
					double remavg = (double) sum[j] / ((j + 1));
					System.out.println("  remavg is " + remavg);
					System.out.println("}");
					if (avg > remavg) {
						System.out.println(j + 1 + "      " + (n));
						count++;
					}
					if (remavg > avg) {
						System.out.println((i + 1) + "   " + (j + 1));
						count++;
					}
				} else {
					System.out.println((i + 1) + "   " + (n));
					count++;
				}
			}
			
		}
		System.out.println(count + 1);
	}
}