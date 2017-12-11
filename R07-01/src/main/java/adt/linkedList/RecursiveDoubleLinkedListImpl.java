package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	
	@Override
	public void insert(T element) {
		if(element != null){
			if(isEmpty()){
				insertFirst(element);
			}else{
				this.getNext().insert(element);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			if(this.getData().equals(element)){
				removeFirst();
			}else{
				this.getNext().remove(element);
			}
		}
	}
	
	
	@Override
	public void insertFirst(T element) {
		if(element != null){
			if(isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
			}else{
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>(this.getData(), this.getNext(), this);
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				this.setData(element);
				this.setNext(node);
			
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
			this.setData(getNext().getData());
			if (getNext().isEmpty()) {
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				this.setNext(this.getNext().getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.getNext().isEmpty()){
				this.setData(null);
				this.setNext(null);
			}else{
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
