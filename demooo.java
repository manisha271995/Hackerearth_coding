import java.util.Arrays;
import java.util.Scanner;

public class demooo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String st = sc.next();
		long val = sc.nextLong();

		char[] ar = st.toCharArray();
		for (int i = 0; i < ar.length; i++) {
			int asci = (int) ar[i];
			long a = asci + val;
			//System.out.println("a" + a);

			if (a >= 123 && i != ar.length - 1 && ar[i] != 'a') {
				ar[i] = 'a';
				val = a - 123;
			}

			else {
				if (i == ar.length - 1 && val > 0)

				{
					long x = val % 26;
					// System.out.println("x" + x);
					int f = asci;
					for (int m = 1; m <= x; m++) {
						f = f + 1;
						//// System.out.println("f"+f);
						if (f == 123)
							f = 97;

					}

					char s = (char) (f);

					ar[i] = s;
				}
			}
		}
		System.out.println(new String(ar));
	}
}