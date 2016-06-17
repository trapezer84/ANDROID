
public class Main {
	
	// CounterThread2가 증가시킨다. 
	public int count = 0; 
	
	public static void main(String[] args) {
//		int numberOne = 0; 
//		numberOne = 10; 
//		numberOne = 20; 
//		
//		// 결과값 : 0 
//		System.out.println(numberOne);
		
		// 잘 못된 사용법 
//		Thread counter1 = new CounterThread("1번");
//		Thread counter2 = new CounterThread("2번");
//		Thread counter3 = new CounterThread("3번");
//		
//		// Thread에는 start()라는 것이 있다. 그래서 내부적으로 그냥 run이라는 method를 실행시켜준다.
//		counter1.start();
//		counter2.start();
//		counter3.start();
		
		// 잘 된 사용법 
		Main main = new Main();
		Thread counter1 = new Thread(new CounterThread2(main));
		Thread counter2 = new Thread(new CounterThread2(main));
		Thread counter3 = new Thread(new CounterThread2(main));
		counter1.start();
		counter2.start();
		counter3.start();
	}

}
