package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isEmpty()){
			this.head += 1;
			this.tail += 1;
			this.array[head] = element;
		}else if(isFull()){
		
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T head() {
		if(!isEmpty()){
			return this.array[head];
		}else{
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1 && this.head == -1;
	}

	@Override
	public boolean isFull() {
		return (this.tail + 1) % this.array.length == this.head;
	}

}
