package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			top.insert(element);
		} else {
			throw new StackOverflowException();
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T topo = top();
			this.top.removeLast();
			return topo;
		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		T topo = null;
		if (!isEmpty()) {
			topo = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
		}
		return topo;

		
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
