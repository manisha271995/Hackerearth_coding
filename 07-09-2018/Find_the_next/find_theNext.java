package test_25.th;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class find_theNext {

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

	static long arr[];
	static int n, q;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		long val = 0;
		Reader s = new Reader();
		n = s.nextInt();
		q = s.nextInt();
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextLong();
		}
		Arrays.sort(arr);
		sb = new StringBuilder("");
		for (int i = 0; i < q; i++) {
			val = s.nextLong();
			int index = binarySearch(arr, 0, n, val);
			if (index >= n) {
				sb.append((val + 1) + "\n");
			} else {
				calculate(index, val);
			}
		}
		System.out.println(sb);

	}

	private static void calculate(int index, long val) {
		int flag = 0;
		long greater = 0, nextval = val + 1;
		int ind = 0;
		if (arr[index] < val) {
			greater = arr[index + 1];
			ind = index + 1;

		}
		if (arr[index] > val) {
			greater = arr[index];
			ind = index;
		}
		if (arr[index] == val) {
			if (index == n - 1) {
				sb.append(nextval + "\n");
				flag++;
				ind = n;
			} else {
				greater = arr[index + 1];
				ind = index + 1;
			}
		}
		for (; ind < n; ind++) {

			if ((nextval) != greater) {
				sb.append(nextval + "\n");
				flag++;
				break;
			} else {
				if (ind != n - 1) {
					greater = arr[ind + 1];
				}
				nextval++;
				// ind++;
			}
		}
		if (flag == 0) {
			sb.append(nextval + "\n");
		}
	}

	static int binarySearch(long arr[], int low, int high, long key) {
		if (high <= low)
			return low;
		int mid = (low + high) / 2;
		if (key == arr[mid])
			return mid;
		if (key > arr[mid])
			return binarySearch(arr, (mid + 1), high, key);
		return binarySearch(arr, low, (mid - 1), key);
	}
}
