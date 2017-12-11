package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return getData() == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (isEmpty()) {
			return size;
		} else {
			size += 1;
			return size + getNext().size();
		}
	}

	@Override
	public T search(T element) {
		T value = null;
		if (!isEmpty()) {
			if (getData().equals(element)) {
				value = element;
				return value;
			} else {
				if (getNext().getData() != null) {
					return getNext().search(element);
				}
			}
		}
		return value;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (getData().equals(element)) {
				setData(next.getData());
				setNext(next.getNext());
			} else {
				getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = this.size();
		int indice = 0;
		T[] array = (T[]) new Comparable[size];

		toArray(array, this, indice);

		return array;

	}

	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int indice) {
		if (indice < array.length) {
			array[indice] = node.getData();
			indice += 1;
			toArray(array, node.next, indice);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
