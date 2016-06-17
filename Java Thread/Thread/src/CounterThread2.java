
public class CounterThread2 implements Runnable{

	private Main main; 
	
	public CounterThread2(Main main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		for(int i = 0; i< 100; i++) {
			main.count++;
			System.out.println(main.count);
		}
		
	}

}
