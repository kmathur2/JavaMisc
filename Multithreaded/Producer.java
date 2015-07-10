package ProducerConsumer;

public class Producer extends Thread {
	String s;
	BufferAccess b;
	Producer(BufferAccess b,String t){
		this.b=b;
		this.s=t;
	}
	@Override
	public void run(){
		int[] buff=b.getBuffer();
		for(int i=0; i< 100;i++){
			System.out.println(this.s+"-Producer "+i);
			buff[i]=i;
		}
		b.setBuffer(buff);
	}
}
