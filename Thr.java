import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Thr {
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

	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			// ArrayList<Integer> bigger = new ArrayList<Integer>();
			// ArrayList<Integer> smaller = new ArrayList<Integer>();
			TreeSet<Integer> bigger = new TreeSet<Integer>();
			TreeSet<Integer> smaller = new TreeSet<Integer>();

			for (int j = 0; j < x; j++) {
				bigger.add(sc.nextInt());
			}
			for (int j = 0; j < y; j++) {
				smaller.add(sc.nextInt());
			}

			int o = sc.nextInt();
			ArrayList<Integer> ar;
			Iterator<Integer> it;
			StringBuilder sb;
			switch (o) {
			case 1:
				ar = new ArrayList<Integer>();
				it = smaller.iterator();
				while (it.hasNext()) {
					int k = it.next();
					if (bigger.contains(k)) {
						ar.add(k);
					}
				}
				sb = new StringBuilder(ar.toString().replace("[", "{").replace("]", "}"));
				System.out.println(sb);
				break;
			case 2:

				it = smaller.iterator();

				while (it.hasNext()) {
					int k = it.next();
					if (!bigger.contains(k)) {
						bigger.add(k);
					}
				}
				it = bigger.iterator();
				sb = new StringBuilder("{");
				while (it.hasNext()) {
					int k = it.next();
					if (!it.hasNext()) {
						sb.append(k);
					} else {
						sb.append(k + ", ");
					}
				}
				sb.append("}");
				System.out.println(sb);
				break;
			case 3:
				it = smaller.iterator();
				while (it.hasNext()) {
					int k = it.next();
					if (bigger.contains(k)) {
						bigger.remove(k);
						smaller.remove(k);
						it = smaller.iterator();
					}
				}

				bigger.addAll(smaller);
				it = bigger.iterator();
				sb = new StringBuilder("{");
				while (it.hasNext()) {
					int k = it.next();
					if (!it.hasNext()) {
						sb.append(k);
					} else {
						sb.append(k + ", ");
					}
				}
				sb.append("}");
				System.out.println(sb);
				break;

			case 4:
				it = smaller.iterator();
				while (it.hasNext()) {
					int k = it.next();
					if (bigger.contains(k)) {
						bigger.remove(k);
					}
				}
				it = bigger.iterator();
				sb = new StringBuilder("{");
				while (it.hasNext()) {
					int k = it.next();
					if (!it.hasNext()) {
						sb.append(k);
					} else {
						sb.append(k + ", ");
					}
				}
				sb.append("}");
				System.out.println(sb);
				break;
			case 5:
				it = bigger.iterator();
				while (it.hasNext()) {
					int k = it.next();
					if (smaller.contains(k)) {
						smaller.remove(k);
					}
				}
				it = smaller.iterator();
				sb = new StringBuilder("{");
				while (it.hasNext()) {
					int k = it.next();
					if (!it.hasNext()) {
						sb.append(k);
					} else {
						sb.append(k + ", ");
					}
				}
				sb.append("}");
				System.out.println(sb);
				break;

			}
		}
	}
}