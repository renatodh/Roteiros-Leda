package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			int size = 1;
			SingleLinkedListNode<T> node = getHead();
			while (!(node.getNext().isNIL())) {
				size += 1;
				node = node.getNext();
			}
			return size;

		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else {
			SingleLinkedListNode<T> node = getHead();
			while (!(node.isNIL() || node.getData().equals(element))) {
				node = node.getNext();
			}
			return node.getData();
		}
	}

	@Override
	public void insert(T element) {

		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
		if (isEmpty()) {
			this.head = newNode;

		} else {

			SingleLinkedListNode<T> node = getHead();

			while (!(node.getNext().isNIL())) {
				node = node.getNext();
			}

			node.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {

			SingleLinkedListNode<T> node = getHead();

			if (this.head.getData().equals(element)) {
				this.head = node.getNext();

			} else {
				while (!node.getNext().isNIL()) {
					if (node.getNext().getData().equals(element)) {
						node.setNext(node.getNext().getNext());
						break;
					}
					node = node.getNext();
				}

				

			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[size()];

		int i = 0;
		SingleLinkedListNode<T> node = getHead();

		while (!node.isNIL()) {
			array[i] = node.getData();
			i++;
			node = node.getNext();
		}
		return array;

	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
