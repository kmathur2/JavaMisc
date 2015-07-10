package ProducerConsumer;

public class BufferAccess {
	private int[] buffer=new int[100];

	public synchronized int[] getBuffer() {
		return this.buffer;
	}

	public synchronized void setBuffer(int[] buffer) {
		this.buffer = buffer;
	}
	

}
