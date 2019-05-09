
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

 class LetsTransport {
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

	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		int t = sc.nextInt();
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			int arr[] = new int[n];
			int val = Integer.MIN_VALUE;
			int maxindex = 0;
			int mid11 = n / 2;
			int diff = Math.abs(maxindex - mid11);
			for (int j = 0; j < n; j++) {
				arr[j] = sc.nextInt();
				if (arr[j] >= val) {
					if (val == arr[j] && Math.abs(j - mid11) <= diff) {
						val = arr[j];
						diff = Math.abs(j - mid11);
						maxindex = j;
					} else if (arr[j] > val) {
						diff = Math.abs(j - mid11);
						val = arr[j];
						maxindex = j;
					}

				}
			}
			if (n == 1 || n % 2 != 0) {
				sb.append(Math.abs(maxindex - mid11)+"\n");
				//System.out.println(Math.abs(maxindex - mid11));
			} else {
				int min = Math.min(Math.abs(maxindex - mid11), Math.abs(maxindex - (mid11-1)));
				sb.append(min+"\n");
			}

		}
		System.out.println(sb);
	}

}
