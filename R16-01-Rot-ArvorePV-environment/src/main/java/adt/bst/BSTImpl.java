package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			return Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) +1;
		}
	}

	@Override

	public BSTNode<T> search(T element) {
		if (isEmpty()) {
			return new BSTNode<T>();
		} else {

			BSTNode<T> node = getRoot();

			if (element.compareTo(node.getData()) == 0) {
				return node;

			} else if (element.compareTo(node.getData()) > 0) {
				return search((BSTNode<T>) node.getRight(), element);

			} else {
				return search((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (!node.isEmpty()) {
			if (element.compareTo(node.getData()) == 0) {
				return node;

			} else if (element.compareTo(node.getData()) > 0) {
				return search((BSTNode<T>) node.getRight(), element);

			} else {
				return search((BSTNode<T>) node.getLeft(), element);
			}
		}
		return new BSTNode<T>();
	}

	public void insert(T element) {
		if (element != null) {
			this.insert(this.getRoot(), element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			this.setNewNode(node, element);
		} else if (node.getData().compareTo(element) < 0) {
			this.insert((BSTNode<T>) node.getRight(), element);
		} else if (node.getData().compareTo(element) > 0) {
			this.insert((BSTNode<T>) node.getLeft(), element);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setNewNode(BSTNode<T> node, T element) {
		node.setParent(node.getParent());
		node.setData(element);
		node.setLeft(new BSTNode.Builder().parent(node).build());
		node.setRight(new BSTNode.Builder().parent(node).build());
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) {
			return null;
		} else if (getRoot().isLeaf() || getRoot().getRight().isEmpty()) {
			return getRoot();
		} else {
			return maximum(getRoot());
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isLeaf() || node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) {
			return null;
		} else if (getRoot().isLeaf() || getRoot().getLeft().isEmpty()) {
			return getRoot();
		} else {
			return minimum(getRoot().getLeft());
		}
	}

	private BSTNode<T> minimum(BTNode<T> node) {
		if (node.isLeaf() || node.getLeft().isEmpty()) {
			return (BSTNode<T>) node;
		} else {
			return minimum(node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if(element == null){
			return null;
		}else{
			
			BSTNode<T> node = this.search(element);
			
			if (node.isEmpty()) {
				return null;
			}
			return sucessor(node);
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		
			
			BSTNode<T> returnNode = minimum((BSTNode<T>) node.getRight());
			
			if (returnNode != null) {
				return returnNode;
			} else {
				returnNode = (BSTNode<T>) node.getParent();
				while (returnNode != null && returnNode.getData().compareTo(node.getData()) < 0) {
					returnNode = (BSTNode<T>) returnNode.getParent();
				}
				
				return returnNode;
			}
		
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if(element == null){
			return null;
		}else{
			
			BSTNode<T> node = this.search(element);

			if (node.isEmpty()) {
				return null;
			}
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if(!node.isEmpty() && !node.getLeft().isEmpty()){
			
			BSTNode<T> returnNode = maximum((BSTNode<T>) node.getLeft());
			
			if (returnNode != null) {
				return returnNode;
			} else {
				returnNode = (BSTNode<T>) node.getParent();
				while (returnNode != null && returnNode.getData().compareTo(node.getData()) > 0) {
					returnNode = (BSTNode<T>) returnNode.getParent();
				}
				
				return returnNode;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty())
			remove(node);

	}

	protected BSTNode<T> remove(BSTNode<T> node) {
		BSTNode<T> removed = new BSTNode<>();
		if (!node.isEmpty()) {
			
			removed.setData(node.getData());
			removed.setParent(node.getParent());
			removed.setLeft(node.getLeft());
			removed.setRight(node.getRight());
			
			if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> newNode = sucessor(node.getData());
				node.setData(newNode.getData());
				removed = remove(newNode);
			} else {
				
				BSTNode<T> newNode = (BSTNode<T>) node.getLeft();

				if (newNode.isEmpty())
					newNode = (BSTNode<T>) node.getRight();

				node.setData(newNode.getData());
				node.setRight(newNode.getRight());
				node.setLeft(newNode.getLeft());

				if (!node.isEmpty() && node.getRight() != null)
					node.getRight().setParent(node);
				if (!node.isEmpty() && node.getLeft() != null)
					node.getLeft().setParent(node);
			}
		}
		return removed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		preOrder(this.getRoot(), returnArray);

		return returnArray;
	}

	private void preOrder(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			addElement(returnArray, node.getData());
			preOrder((BSTNode<T>) node.getLeft(), returnArray);
			preOrder((BSTNode<T>) node.getRight(), returnArray);
		}
	}

	private void addElement(T[] returnArray, T data) {
		int i = 0;
		while (returnArray[i] != null) {
			i++;
		}

		returnArray[i] = data;

	}

	@Override
	public T[] order() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		order(this.getRoot(), returnArray);

		return returnArray;
	}

	private void order(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), returnArray);
			addElement(returnArray, node.getData());
			order((BSTNode<T>) node.getRight(), returnArray);
		}
	}

	@Override
	public T[] postOrder() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		postOrder(this.getRoot(), returnArray);

		return returnArray;
	}

	private void postOrder(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), returnArray);
			postOrder((BSTNode<T>) node.getRight(), returnArray);
			addElement(returnArray, node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
