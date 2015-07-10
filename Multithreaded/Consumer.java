package ProducerConsumer;

public class Consumer extends Thread {
	String s;
	BufferAccess b;
	Consumer(BufferAccess b,String t){
		this.b=b;
		this.s=t;
	}
	
	@Override
	public void run(){
		int[] buff=b.getBuffer();
		for(int i=0; i< buff.length;i++){
			System.out.println(this.s+"-        Consumer "+i+" = "+buff[i]);
		}
	}
}
