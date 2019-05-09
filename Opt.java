
class test implements Runnable {
	
	static int f =5;

	@Override
	public void run() {
		System.out.println("hi");
		System.out.println("hi man");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

public class Opt {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("hello");
		System.out.println("hello world");
		System.out.println(test.f);
		test t = new test();
		Thread thread = new Thread(t);
		thread.start();
		thread.join();
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");
		System.out.println("hello_man");



	}

}
