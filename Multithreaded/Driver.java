package ProducerConsumer;

public class Driver {
	public static void main(String[] args){
		BufferAccess b=new BufferAccess();
		Producer p1=new Producer(b,"p1");
		Producer p2=new Producer(b,"p2");
		Consumer c1=new Consumer(b,"c1");
		Consumer c2=new Consumer(b,"c2");
	
		p1.start();
		p2.start();
		
		try {
			p1.join();
			p2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c1.start();
		c2.start();
	}

}
