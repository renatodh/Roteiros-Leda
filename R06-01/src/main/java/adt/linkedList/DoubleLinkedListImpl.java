package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override

	public void insert(T element) {

		if (element != null) {

			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
					new DoubleLinkedListNode<>());
			if (isEmpty()) {
				this.head = newNode;
				this.last = (DoubleLinkedListNode<T>) this.head;
				this.last.setPrevious( new DoubleLinkedListNode<>());

			} else {
				newNode.setPrevious(getLast());
				getLast().setNext(newNode);
				setLast(newNode);

			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {

			DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) head;

			if (this.head.getData().equals(element)) {
				this.head = head.next;
				((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<>());

			} else {
				while (!node.getNext().isNIL()) {
					if (node.getNext().getData().equals(element)) {
						node.setNext(node.getNext().getNext());
						((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node);
						break;
					}
					node = (DoubleLinkedListNode<T>) node.getNext();
				}

			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) this.getHead();
				DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(element, head,
						new DoubleLinkedListNode<T>());
				super.setHead(node);
				this.setLast(node);
			} else {
				DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) this.getHead();
				DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(element, head,
						new DoubleLinkedListNode<T>());
				((DoubleLinkedListNode<T>) getHead()).setPrevious(node);
				super.setHead(node);

			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			super.head = super.head.getNext();
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (super.size() == 1) {
				super.setHead(new DoubleLinkedListNode<>());
				this.setLast(new DoubleLinkedListNode<>());

			} else if (super.size() == 2) {
				super.head.setNext(new DoubleLinkedListNode<>());
				this.setLast((DoubleLinkedListNode<T>) super.head);

			} else {
				DoubleLinkedListNode<T> node = getLast().getPrevious();
				this.setLast(node);
				this.last.setNext(new DoubleLinkedListNode<>());

			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
