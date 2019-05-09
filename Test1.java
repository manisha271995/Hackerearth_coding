import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		String st = sc.next();
		char[] charArray = st.toCharArray();
		int array[] = new int[26];
		for (int i = 0; i < st.length(); i++) {

			int index = charArray[i] - (int) 'a';
			array[index] += 1;
		}
		int count[] = new int[26];
		int flag = 0, mainCount = 0;
		for (int i = 0; i < st.length(); i++) {

			int index = charArray[i] - (int) 'a';
			count[index] += 1;
			for (int j = 0; j <= 25; j++) {
				if ((double) array[j] / 2 == (double) count[j] && count[j] != 0) {
					flag++;
					if (flag >= k) {
						System.out.println("hi");
						flag = 0;
						mainCount++;
						break;
					}
				}
			}
			flag = 0;
		}
		System.out.println(mainCount);

	}

}
