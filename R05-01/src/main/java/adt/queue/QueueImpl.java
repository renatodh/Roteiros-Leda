package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}
	
	@Override
	public T head() {
		if(!isEmpty()){
			return this.array[0];
		}else{
			return (T) null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail - 1; i++) {
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}else{
			this.tail += 1;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}else{
			T retorno = array[0];
			shiftLeft();
			this.tail -= 1;
			return retorno;
		}
	}

}
