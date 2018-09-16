import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class hgj {

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
	static long arr[];
	static long query;
	static StringBuilder sb;

	public static void main(String[] ar) throws IOException {
		Reader s = new Reader();
		n = s.nextInt();
		arr = new long[n];
		long sum = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextLong();
			sum = sum + arr[i];
		}
		sb = new StringBuilder("");
		Arrays.sort(arr);
		int q = s.nextInt();
		for (int i = 0; i < q; i++) {
			query = s.nextLong();
			if (query > sum) {
				sb.append("-1" + "\n");
			} else {
				if (query <= arr[i]) {
					double q1 = query;
					double N = n;
					sb.append((int) Math.ceil(q1 / N) + "\n");
				} else {
					calculate();
				}
			}
		}
		System.out.println(sb);

	}

	private static void calculate() {
		long start_index = 0, sum = 0;
		if ((query % n) == 0) {
			start_index = query / n;
		} else {
			start_index = (query / n) + 1;
		}
		Boolean cond = true;
		long start = nextelement(start_index);
		long init_sum = 0;
		for (int i = 0; i < start; i++) {
			init_sum = init_sum + arr[i];
		}
		while (cond) {
			sum = 0;
			long totalsum = 0;
			for (int i = (int) start; i < n; i++) {
				if (arr[i] < start_index) {
					sum = sum + arr[i];
				} else {
					sum = sum + start_index;
				}
			}

			totalsum = init_sum + sum;
			if (totalsum >= query) {
				sb.append(start_index + "\n");
				break;
			} else {
				start_index++;
			}
		}
	}

	private static long nextelement(long start_index) {
		int index = binarySearch(arr, 0, n, start_index);
		long greater = 0;
		long ind = 0;
		if (arr[index] < start_index) {
			greater = arr[index + 1];
			ind = index + 1;
		}
		if (arr[index] > start_index) {
			greater = arr[index];
			ind = index;
		}
		if (arr[index] == start_index) {
			ind = index + 1;
		}
		return ind;
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
