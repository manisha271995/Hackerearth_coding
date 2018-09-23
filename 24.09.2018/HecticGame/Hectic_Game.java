package Coding;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Hectic_Game {

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	static int n;
	static long alice = 0, bob = 0, count = 0, counta = 0, countb = 0;
	static Long[][] array;
	static boolean flag = false;
	static long times = 1, aliceVal = 0, bobVal = 0;

	public static void main(String[] ar) throws IOException {
		Reader s = new Reader();
		int t = s.nextInt();
		for (int j = 0; j < t; j++) {
			alice = 0;
			bob = 0;
			count = 0;
			counta = 0;
			countb = 0;
			flag = false;
			times = 1;
			aliceVal = 0;
			bobVal = 0;
			n = s.nextInt();
			array = new Long[n][2];
			for (int i = 0; i < n; i++) {
				for (int k = 0; k < 2; k++) {
					array[i][k] = s.nextLong();
				}
			}
			sort();
			int flag1 = 0, flag2 = 0;
			do {
				winner();
				if (times % 2 != 0) {
					counta = counta + count;
					if (flag1 == 0) {
						alice = count;
						aliceVal = alice;
						flag1++;
					} else {
						aliceVal = alice ^ count;
						alice = aliceVal;
					}
				} else {
					countb = countb + count;
					if (flag2 == 0) {
						bob = count;
						bobVal = bob;
						flag2++;
					} else {
						bobVal = bob ^ count;
						bob = bobVal;
					}
				}
				times++;
			} while (flag);
			if (aliceVal > bobVal)
				System.out.println("Alice");
			else if (aliceVal < bobVal)
				System.out.println("Bob");
			else
				System.out.println("Tie");
		}
	}

	private static void sort() {
		Arrays.sort(array, new Comparator<Long[]>() {
			@Override
			public int compare(Long[] i, Long[] j) {
				Long first = i[1];
				Long second = j[1];
				return first.compareTo(second);
			}
		});
	}

	private static void winner() {
		count = 0;
		int i = 0;
		long var1 = 0;
		long var2 = 0;
		flag = false;
		while (i < n) {
			if (array[i][0] != 0) {
				flag = true;
				var1 = array[i][0];
				var2 = array[i][1];
				array[i][0] = 0l;
				array[i][1] = 0l;
				count++;
				break;
			}
			i++;
		}
		if (flag) {
			for (int j = 1; j < n; j++) {

				if (array[j][0] != 0) {
					if (array[j][0] > var2) {
						var2 = array[j][1];
						array[j][0] = 0l;
						array[j][1] = 0l;
						count++;
					}
				}
			}
		}
	}
}
