
public class CounterThread extends Thread {

	private String name; 
	
	public CounterThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i + " "+ name);
			try {
				// 0.001초 동안 쉬도록 만든다. 
				// 하나의 쓰레드가 쉬게되면 다음 쓰레드로 순서를 넘긴다. 
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}		
	}
}
