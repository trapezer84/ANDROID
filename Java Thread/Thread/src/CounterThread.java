
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
				// 0.001�� ���� ������ �����. 
				// �ϳ��� �����尡 ���ԵǸ� ���� ������� ������ �ѱ��. 
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}		
	}
}